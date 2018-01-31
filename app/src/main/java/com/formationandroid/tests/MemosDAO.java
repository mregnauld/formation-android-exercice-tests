package com.formationandroid.tests;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MemosDAO
{
	
	/**
	 * Retourne la liste de mémos.
	 * @param context Context
	 * @return Liste de MemoDTO
	 */
	public List<MemoDTO> getListeMemos(Context context)
	{
		// projection (colonnes utilisées après la requète) :
		String[] projection = {BaseContrat.MemosContrat._ID,
				BaseContrat.MemosContrat.COLONNE_INTITULE};
		
		// tri :
		String tri = BaseContrat.MemosContrat.COLONNE_INTITULE + " ASC " ;
		
		// accès en lecture (query) :
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase db = databaseHelper.getReadableDatabase();
		
		// requête :
		Cursor cursor = db.query(
				BaseContrat.MemosContrat.TABLE_MEMOS,	// table sur laquelle faire la requète
				projection,								// colonnes à retourner
				null,							// colonnes pour la clause WHERE (inactif)
				null,						// valeurs pour la clause WHERE (inactif)
				null,							// GROUP BY (inactif)
				null,							// HAVING (inactif)
				tri,									// ordre de tri
				null);								// LIMIT (inactif)
		
		// construction de la liste de mémos
		List<MemoDTO> listeMemos = new ArrayList<>();
		if (cursor != null)
		{
			try
			{
				cursor.moveToFirst();
				while (!cursor.isAfterLast())
				{
					// conversion des données remontées en un objet métier :
					listeMemos.add(new MemoDTO(cursor.getString(cursor.getColumnIndex(BaseContrat.MemosContrat.COLONNE_INTITULE))));
					cursor.moveToNext();
				}
			}
			catch (Exception exception)
			{
				exception.printStackTrace();
			}
			finally
			{
				cursor.close();
			}
		}
		
		return listeMemos;
	}
	
	/**
	 * Ajout d'un mémo en base de données.
	 * @param context Context
	 * @param intitule Intitulé
	 * @return ID mémo
	 */
	public long ajouterMemo(Context context, String intitule)
	{
		// accès en écriture (insert, update, delete) :
		DatabaseHelper databaseHelper = new DatabaseHelper(context);
		SQLiteDatabase db = databaseHelper.getWritableDatabase();
		
		// objet de valeurs :
		ContentValues values = new ContentValues();
		values.put(BaseContrat.MemosContrat.COLONNE_INTITULE, intitule);
		
		// ajout :
		return db.insert(BaseContrat.MemosContrat.TABLE_MEMOS, null, values);
	}
	
}
