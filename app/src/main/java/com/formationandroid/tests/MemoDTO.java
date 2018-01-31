package com.formationandroid.tests;

public class MemoDTO
{
	
	// Attributs :
	private String intitule = null;
	
	/**
	 * Constructeur.
	 * @param intitule Intitulé du mémo
	 */
	public MemoDTO(String intitule)
	{
		this.intitule = intitule;
	}
	
	/**
	 * Getter intitulé.
	 * @return Intitulé du mémo
	 */
	public String getIntitule()
	{
		return intitule;
	}
	
}
