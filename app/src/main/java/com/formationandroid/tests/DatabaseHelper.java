package com.formationandroid.tests;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
	
	// Constantes :
	private static final String NOM_BASE = "memos.db";
	private static final int VERSION = 1;
	private static final String CREATE_TABLE_MEMOS = "CREATE TABLE " + BaseContrat.MemosContrat.TABLE_MEMOS + " ("
			+ BaseContrat.MemosContrat._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ BaseContrat.MemosContrat.COLONNE_INTITULE + " TEXT NOT NULL "
			+ ")";
	
	
	/**
	 * Constructeur.
	 * @param context Context
	 */
	public DatabaseHelper(Context context)
	{
		super(context, NOM_BASE, null, VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(CREATE_TABLE_MEMOS);
		db.execSQL("INSERT INTO " + BaseContrat.MemosContrat.TABLE_MEMOS + " VALUES (NULL, 'Patates')");
		db.execSQL("INSERT INTO " + BaseContrat.MemosContrat.TABLE_MEMOS + " VALUES (NULL, 'Avocat')");
		db.execSQL("INSERT INTO " + BaseContrat.MemosContrat.TABLE_MEMOS + " VALUES (NULL, 'Shampooing')");
		db.execSQL("INSERT INTO " + BaseContrat.MemosContrat.TABLE_MEMOS + " VALUES (NULL, 'Riz')");
		db.execSQL("INSERT INTO " + BaseContrat.MemosContrat.TABLE_MEMOS + " VALUES (NULL, 'Chocolat')");
		db.execSQL("INSERT INTO " + BaseContrat.MemosContrat.TABLE_MEMOS + " VALUES (NULL, 'Oeufs')");
		db.execSQL("INSERT INTO " + BaseContrat.MemosContrat.TABLE_MEMOS + " VALUES (NULL, 'Lait')");
		db.execSQL("INSERT INTO " + BaseContrat.MemosContrat.TABLE_MEMOS + " VALUES (NULL, 'Pain')");
		db.execSQL("INSERT INTO " + BaseContrat.MemosContrat.TABLE_MEMOS + " VALUES (NULL, 'Pâtes')");
		db.execSQL("INSERT INTO " + BaseContrat.MemosContrat.TABLE_MEMOS + " VALUES (NULL, 'Viande')");
		db.execSQL("INSERT INTO " + BaseContrat.MemosContrat.TABLE_MEMOS + " VALUES (NULL, 'Beurre')");
		db.execSQL("INSERT INTO " + BaseContrat.MemosContrat.TABLE_MEMOS + " VALUES (NULL, 'Salade')");
		db.execSQL("INSERT INTO " + BaseContrat.MemosContrat.TABLE_MEMOS + " VALUES (NULL, 'Oignons')");
		db.execSQL("INSERT INTO " + BaseContrat.MemosContrat.TABLE_MEMOS + " VALUES (NULL, 'Raisins')");
		db.execSQL("INSERT INTO " + BaseContrat.MemosContrat.TABLE_MEMOS + " VALUES (NULL, 'Sauce')");
		db.execSQL("INSERT INTO " + BaseContrat.MemosContrat.TABLE_MEMOS + " VALUES (NULL, 'Croissant')");
		db.execSQL("INSERT INTO " + BaseContrat.MemosContrat.TABLE_MEMOS + " VALUES (NULL, 'Pizza')");
		db.execSQL("INSERT INTO " + BaseContrat.MemosContrat.TABLE_MEMOS + " VALUES (NULL, 'Chips')");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		db.execSQL("DROP TABLE IF EXISTS " + BaseContrat.MemosContrat.TABLE_MEMOS);
		onCreate(db);
	}

}
