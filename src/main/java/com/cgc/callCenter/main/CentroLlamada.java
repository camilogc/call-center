package com.cgc.callCenter.main;

import java.util.Collection;

/**
 * @author camilogc
 * Interface Centro de llamadas
 */

public interface CentroLlamada {

	/**
	 * Metodo que busca un empleado disponible de una lista de empleados
	 * @param listaEmpleados
	 * @return 
	 */
	Empleado buscarEmpleado(Collection<Empleado> listaEmpleados);
}
