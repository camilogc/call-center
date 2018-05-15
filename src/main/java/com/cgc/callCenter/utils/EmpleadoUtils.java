package com.cgc.callCenter.utils;

import java.util.function.Predicate;

import com.cgc.callCenter.main.Dispatcher;
import com.cgc.callCenter.main.Empleado;
import com.cgc.callCenter.main.EstadoEmpleado;
import com.cgc.callCenter.main.LlamadaCliente;
import com.cgc.callCenter.main.TipoEmpleado;

/**
 * @author camilogc
 * 
 * Clase de utilidad para comparar predicados por Estado o Tipo de empleado
 *
 */

public class EmpleadoUtils {

	/**
	 * Método que compara por estado Empleado
	 * @param estadoEmpleado
	 * @return
	 */
	public static Predicate<Empleado> filtroEstado(EstadoEmpleado estadoEmpleado) {
		 
        return (Empleado emp) -> {
            return emp.getEstadoEmpleado().equals(estadoEmpleado);
        };
    }
	
	/**
	 * Método que compara por tipo Empleado
	 * @param filtroTipo
	 * @return
	 */
	public static Predicate<Empleado> filtroTipo(TipoEmpleado tipoEmpleado) {
		 
        return (Empleado emp) -> {
            return emp.getTipoEmpleado().equals(tipoEmpleado);
        };
    }
}
