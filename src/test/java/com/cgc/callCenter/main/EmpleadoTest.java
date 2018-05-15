package com.cgc.callCenter.main;

import static org.junit.Assert.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class EmpleadoTest {

	@Test
	public void testEstadosEmpleadoAtendiendo() throws InterruptedException {
		Empleado empleado = Empleado.tipoOperador();
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		executorService.execute(empleado);
		assertEquals(EstadoEmpleado.DISPONIBLE, empleado.getEstadoEmpleado());
		TimeUnit.SECONDS.sleep(1);
		empleado.atenderLlamada(LlamadaCliente.randomLlamada(2, 3));
		empleado.atenderLlamada(LlamadaCliente.randomLlamada(0, 1));
		TimeUnit.SECONDS.sleep(1);
		assertEquals(EstadoEmpleado.OCUPADO, empleado.getEstadoEmpleado());
		executorService.awaitTermination(5, TimeUnit.SECONDS);
		assertEquals(2, empleado.getLlamadaClienteAtendida().size());
	}

	@Test
	public void testEmpleadoDisponibleLlamada() throws InterruptedException {
		Empleado empleado = Empleado.tipoOperador();
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.execute(empleado);
		empleado.atenderLlamada(LlamadaCliente.randomLlamada(0, 1));
		executorService.awaitTermination(5, TimeUnit.SECONDS);
		assertEquals(1, empleado.getLlamadaClienteAtendida().size());
	}

	@Test
	public void testEmpleadoDirector() {
		Empleado empleado = Empleado.tipoDirector();
		assertEquals(TipoEmpleado.DIRECTOR, empleado.getTipoEmpleado());
		assertEquals(EstadoEmpleado.DISPONIBLE, empleado.getEstadoEmpleado());
	}
	@Test
	public void testEmpleadoSupervisor() {
		Empleado empleado = Empleado.tipoSupervisor();
		assertEquals(TipoEmpleado.SUPERVISOR, empleado.getTipoEmpleado());
		assertEquals(EstadoEmpleado.DISPONIBLE, empleado.getEstadoEmpleado());
	}
	@Test
	public void testEmpleadoOperador() {
		Empleado empleado = Empleado.tipoOperador();
		assertEquals(TipoEmpleado.OPERADOR, empleado.getTipoEmpleado());
		assertEquals(EstadoEmpleado.DISPONIBLE, empleado.getEstadoEmpleado());
	}
}
