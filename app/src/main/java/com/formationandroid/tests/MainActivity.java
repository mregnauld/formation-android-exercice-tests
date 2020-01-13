package com.formationandroid.tests;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener
{
	
	// Constantes :
	private static final String TAG = MainActivity.class.getSimpleName();
	private static final String LIEN = "https://httpbin.org/post";
	private static final String CLE_FORM = "form";
	private static final String CLE_MEMO = "memo";
	private static final String CLE_POSITION = "position";
	
	// Vues :
	private RecyclerView recyclerView = null;
	private EditText editTextMemo = null;
	
	// Adapter :
	private MemosAdapter memosAdapter = null;
	
	// Gesture detector :
	private GestureDetector gestureDetector = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// init :
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// création de la base de données, si inexistante :
		DatabaseHelper databaseHelper = new DatabaseHelper(this);
		databaseHelper.getReadableDatabase();
		
		// accès à la base de données :
		MemosDAO memosDAO = new MemosDAO();
		List<MemoDTO> listeMemoDTO = memosDAO.getListeMemos(this);
		
		// vues :
		recyclerView = findViewById(R.id.liste_memos);
		editTextMemo = findViewById(R.id.saisie_memo);
		
		// à ajouter pour de meilleures performances :
		recyclerView.setHasFixedSize(true);
		
		// layout manager, décrivant comment les items sont disposés :
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(layoutManager);
		
		// adapter :
		memosAdapter = new MemosAdapter(listeMemoDTO);
		recyclerView.setAdapter(memosAdapter);
		
		// listener :
		recyclerView.addOnItemTouchListener(this);
		gestureDetector = new GestureDetector(this,
				new GestureDetector.SimpleOnGestureListener()
				{
					@Override
					public boolean onSingleTapUp(MotionEvent event)
					{
						return true;
					}
				});
		
		// affichage de la dernière position cliquée :
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		int dernierePosition = preferences.getInt(CLE_POSITION, -1);
		if (dernierePosition > -1)
		{
			Toast.makeText(this, getString(R.string.main_message_derniere_position, dernierePosition), Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent motionEvent)
	{
		if (gestureDetector.onTouchEvent(motionEvent))
		{
			// récupération de l'item cliqué :
			View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
			if (child != null)
			{
				// position dans la liste d'objets métier (position à partir de zéro !) :
				int position = recyclerView.getChildAdapterPosition(child);
				
				// sauvegarde de la position en shared preferences :
				SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
				SharedPreferences.Editor editor = preferences.edit();
				editor.putInt(CLE_POSITION, position);
				editor.apply();
				
				// mémo :
				MemoDTO memoDTO = memosAdapter.getMemoDTOParPosition(position);
				
				// client HTTP :
				AsyncHttpClient client = new AsyncHttpClient();
				
				// paramètres :
				RequestParams requestParams = new RequestParams();
				requestParams.put(CLE_MEMO, memoDTO.getIntitule());
				
				// appel :
				client.post(LIEN, requestParams, new AsyncHttpResponseHandler()
				{
					@Override
					public void onSuccess(int statusCode, Header[] headers,
										  byte[] response)
					{
						String retour = new String(response);
						try
						{
							JSONObject jsonObject = new JSONObject(retour);
							JSONObject jsonObjectForm = jsonObject.getJSONObject(CLE_FORM);
							Toast.makeText(MainActivity.this, jsonObjectForm.getString(CLE_MEMO), Toast.LENGTH_LONG).show();
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
					}
					
					@Override
					public void onFailure(int statusCode, Header[] headers,
										  byte[] errorResponse, Throwable e)
					{
						Log.e(TAG, e.toString());
					}
				});
				
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {}
	
	@Override
	public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}
	
	/**
	 * Listener clic bouton valider.
	 * @param view Bouton valider
	 */
	public void onClickBoutonValider(View view)
	{
		// intitulé du mémo :
		String intitule = editTextMemo.getText().toString();
		
		// ajout du mémo en base :
		MemosDAO memosDAO = new MemosDAO();
		memosDAO.ajouterMemo(this, intitule);
		
		// rechargement de la liste de mémos depuis la base de données (car le mémo n'est pas forcément ajouté en première position, la liste étant triée par ordre alphabétique) :
		List<MemoDTO> listeMemoDTO = memosDAO.getListeMemos(this);
		
		// mise à jour des mémos :
		memosAdapter.actualiserMemos(listeMemoDTO);
		
		// animation de repositionnement de la liste :
		recyclerView.smoothScrollToPosition(0);
		
		// on efface le contenu de la zone de saisie :
		editTextMemo.setText("");
	}
	
}
