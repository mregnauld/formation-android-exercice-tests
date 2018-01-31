package com.formationandroid.tests;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class MemosDAOTest
{
	
	@Test
	public void ajoutMemo() throws Exception
	{
		// context de l'application :
		Context context = InstrumentationRegistry.getTargetContext();
		
		// accès à la base de données :
		MemosDAO memosDAO = new MemosDAO();
		List<MemoDTO> listeMemoDTOs = memosDAO.getListeMemos(context);
		
		// nombre initial d'éléments dans la liste :
		int nombreInitialMemos = listeMemoDTOs.size();
		
		// test d'ajout :
		long idMemo = memosDAO.ajouterMemo(context, "Item test");
		
		// on vérifie que l'id retourné est bien strictement positif :
		Assert.assertTrue(idMemo > 0);
		
		// on vérifie que le nom d'éléments dans la liste a bien augmenté de 1 :
		Assert.assertEquals(memosDAO.getListeMemos(context).size(), nombreInitialMemos + 1);
	}
	
}
