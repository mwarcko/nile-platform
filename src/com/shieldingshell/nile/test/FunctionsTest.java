package com.shieldingshell.nile.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.shieldingshell.nile.functions.Functions;

public class FunctionsTest {
	
Functions fct = new Functions();
	
	@Test
	public void createDateReturnLong() {
		assertTrue(fct.createDate() instanceof Long);
	}
}
