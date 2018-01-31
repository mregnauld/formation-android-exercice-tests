package com.formationandroid.tests;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class NavigationTest
{
	
	@Rule
	public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);
	
	@Before
	public void init() {}
	
	@Test
	public void lancerNavigation()
	{
		try
		{
			// attente initiale :
			Thread.sleep(500);
			
			// saisie dans le champ de saisie :
			Espresso.onView(ViewMatchers.withId(R.id.saisie_memo)).perform(ViewActions.typeText("Item test"));
			
			// clic sur le bouton OK :
			Espresso.onView(ViewMatchers.withId(R.id.bouton_valider)).perform(ViewActions.click());
			
			// attente :
			Thread.sleep(500);
			
			// slide vers le bas de la liste :
			Espresso.onView(ViewMatchers.withId(R.id.liste_memos)).perform(ViewActions.swipeUp());
			
			// attente :
			Thread.sleep(1000);
			
			// slide à une position spécifique :
			Espresso.onView(ViewMatchers.withId(R.id.liste_memos)).perform(RecyclerViewActions.scrollToPosition(10));
			
			// attente de fin (pour avoir un peu le temps de voir le résultat) :
			Thread.sleep(1500);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@After
	public void finish() {}
	
}
