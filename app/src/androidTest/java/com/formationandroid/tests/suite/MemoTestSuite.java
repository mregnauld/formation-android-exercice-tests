package com.formationandroid.tests.suite;

import com.formationandroid.tests.MemosDAOTest;
import com.formationandroid.tests.NavigationTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({MemosDAOTest.class, NavigationTest.class})
public class MemoTestSuite
{
}
