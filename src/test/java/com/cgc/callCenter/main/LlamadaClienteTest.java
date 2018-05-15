package com.cgc.callCenter.main;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.cgc.callCenter.main.LlamadaCliente;

/**
 * @author camilogc
 *
 */

public class LlamadaClienteTest {

	@Test
	public void testLlamadaCliente(){
		int num = 5;
		new LlamadaCliente(num);
	}
	
	@Test
	public void testRandomLlamada(){
		int minSeg = 1;
		int maxSeg = 10;
		LlamadaCliente.randomLlamada(minSeg, maxSeg);		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConParametroIncorrecto(){
		new LlamadaCliente(-4);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testRandomConValoresEnOrdenErroneos(){
		int minSeg = 5;
		int maxSeg = 1;
		LlamadaCliente.randomLlamada(minSeg, maxSeg);
	}
	
	@Test(expected = NullPointerException.class)
	public void testLlamadaClienteParametroNull(){
		new LlamadaCliente(null);
	}
	
}
