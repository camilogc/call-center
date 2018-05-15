package com.cgc.callCenter.main;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cgc.callCenter.utils.EmpleadoUtils;

/**
 * @author camilogc
 * Clase que gestiona los empleados para recibir las llamadas
 */

public class GestionCentroLlamada implements CentroLlamada{

	/**
	 * Metodo que busca un empleado disponible dentro de una lista de empleados de tipo
	 * OPERADOR, SUPERVISOR, DIRECTOR
	 * Si no encuentra operadores disponibles coloca música de fondo mientras busca un
	 * empleado disponible
	 */
	@Override
	public Empleado buscarEmpleado(Collection<Empleado> listaEmpleados) {
		List<Empleado> empleadosDisponibles = listaEmpleados.stream().filter(
				EmpleadoUtils.filtroEstado(EstadoEmpleado.DISPONIBLE)).collect(Collectors.toList());
		System.out.println("Operadores Disponibles: " + empleadosDisponibles.size());
		Optional<Empleado> empleado = empleadosDisponibles.stream().filter(
				EmpleadoUtils.filtroTipo(TipoEmpleado.OPERADOR)).findAny();
		if (!empleado.isPresent()) {
			System.out.println("No hay OPERADORES disponibles");
			empleado = empleadosDisponibles.stream().filter(
					EmpleadoUtils.filtroTipo(TipoEmpleado.SUPERVISOR)).findAny();
			if (!empleado.isPresent()) {
				System.out.println("No hay SUPERVISORES disponibles");
				empleado = empleadosDisponibles.stream().filter(
						EmpleadoUtils.filtroTipo(TipoEmpleado.DIRECTOR)).findAny();
				if (!empleado.isPresent()) {
					System.out.println("No hay DIRECTORES disponibles");
					System.out.println("Espere mientras se desocupan nuestros operadores - MUSICA DE FONDO");
					return null;
				}
			}
		}
		System.out.println("Se encontro un Empleado de tipo " + empleado.get().getTipoEmpleado());
		return empleado.get();
	}

}
