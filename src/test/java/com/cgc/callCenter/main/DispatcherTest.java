package com.cgc.callCenter.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.cgc.callCenter.main.Dispatcher;
import com.cgc.callCenter.main.Empleado;
import com.cgc.callCenter.main.LlamadaCliente;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test dispatcher
 * @author camilogc
 *
 */

public class DispatcherTest {

	private static final int MIN_SEG_LLAMADA = 5; // Cantidad minima de segundos que puede durar una llamada
	private static final int MAX_SEG_LLAMADA = 10;// Cantidad maxima de segundos que puede durar una llamada
	private static final int MAX_LLAMADAS = 10;   // Cantidad maxima de llamadas que entran al callcenter

	/**
	 * Lista de empleados para realizar el test
	 * @return
	 */
	private static List<Empleado> listaTestEmpleados() {
		Empleado op = Empleado.tipoOperador();
		Empleado op2 = Empleado.tipoOperador();
		Empleado op3 = Empleado.tipoOperador();
		Empleado sup = Empleado.tipoSupervisor();
		Empleado sup2 = Empleado.tipoSupervisor();
		Empleado sup3 = Empleado.tipoSupervisor();
		Empleado sup4 = Empleado.tipoSupervisor();
		Empleado dir = Empleado.tipoDirector();

		return Arrays.asList(op, op2, op3, sup, sup2, sup3, sup4, dir);
	}

	private static List<LlamadaCliente> listaTestllamadas() {
		return LlamadaCliente.listaRandomLlamadas(MAX_LLAMADAS, MIN_SEG_LLAMADA, MAX_SEG_LLAMADA);
	}

	@Test
	public void testDispatchLlamadasAEmpleados() throws InterruptedException {
		List<Empleado> listaEmpelados = listaTestEmpleados();
		Dispatcher dispatcher = new Dispatcher(listaEmpelados);
		dispatcher.inicioDespachar();
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(dispatcher);

		for(LlamadaCliente llamada : listaTestllamadas()){
			dispatcher.dispatchCall(llamada);
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				fail();
			}
		}

		executorService.awaitTermination(MAX_SEG_LLAMADA * 2, TimeUnit.SECONDS);
		assertEquals(MAX_LLAMADAS, listaEmpelados.stream().mapToInt(
				emp -> emp.getLlamadaClienteAtendida().size()).sum());
	}
}
