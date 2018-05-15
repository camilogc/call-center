package com.cgc.callCenter.main;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;

public class GestionCentroLlamadaTest {

	private CentroLlamada centroLlamada;

    public GestionCentroLlamadaTest() {
    	this.centroLlamada = new GestionCentroLlamada();
	}
 
	@Test
    public void testAsignarLlamadaOperador() {
        Empleado operador = Empleado.tipoOperador();
        List<Empleado> listaEmpleados = Arrays.asList(operador);
        Empleado empleado = this.centroLlamada.buscarEmpleado(listaEmpleados);
        assertEquals(TipoEmpleado.OPERADOR, empleado.getTipoEmpleado());
    }

    @Test
    public void testAsignarLlamadaSupervisor() {
        Empleado supervisor = Empleado.tipoSupervisor();
        List<Empleado> listaEmpleados = Arrays.asList(supervisor);
        Empleado empleado = this.centroLlamada.buscarEmpleado(listaEmpleados);
        assertEquals(TipoEmpleado.SUPERVISOR, empleado.getTipoEmpleado());
    }

    @Test
    public void testAsignarLlamadaDirector() {
    	Empleado director = Empleado.tipoDirector();
        List<Empleado> listaEmpleados = Arrays.asList(director);
        Empleado empleado = this.centroLlamada.buscarEmpleado(listaEmpleados);
        assertEquals(TipoEmpleado.DIRECTOR, empleado.getTipoEmpleado());
    }

}
