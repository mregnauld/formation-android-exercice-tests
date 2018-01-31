package com.formationandroid.tests;

import android.provider.BaseColumns;

public class BaseContrat
{
	
	/**
	 * Constructeur privé afin de ne pas instancier la classe.
	 */
	private BaseContrat() {}
	
	/**
	 * Contenu de la table "memos"
	 */
	public static class MemosContrat implements BaseColumns
	{
		public static final String TABLE_MEMOS = "memos";
		public static final String COLONNE_INTITULE = "intitule";
	}

}
