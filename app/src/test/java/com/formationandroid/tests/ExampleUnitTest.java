package com.formationandroid.tests;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest
{
	@Test
	public void verifierIntituleMemoDTO() throws Exception
	{
		MemoDTO memoDTO = new MemoDTO("test intitulé");
		assertEquals("test intitulé", memoDTO.getIntitule());
	}
}