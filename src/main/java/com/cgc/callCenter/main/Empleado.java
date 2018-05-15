package com.cgc.callCenter.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.TimeUnit;

/**
 * Clase encargada de gestionar las funciones del empleado
 * @author camilogc
 *
 */

public class Empleado implements Runnable{

	private TipoEmpleado tipoEmpleado;
	private EstadoEmpleado estadoEmpleado;
	private ConcurrentLinkedDeque<LlamadaCliente> llamadaClienteEntrante;
	private ConcurrentLinkedDeque<LlamadaCliente> llamadaClienteAtendida;


	//Constructor de la clase
	public Empleado(TipoEmpleado tipoEmpleado){
		if(!tipoEmpleado.equals(null)){
			this.tipoEmpleado = tipoEmpleado;
			this.estadoEmpleado = EstadoEmpleado.DISPONIBLE;
			this.llamadaClienteEntrante = new ConcurrentLinkedDeque<>();
			this.llamadaClienteAtendida = new ConcurrentLinkedDeque<>();
		}
	}

	//Creación de roles para los empleados
	public static Empleado tipoOperador(){
		return new Empleado(TipoEmpleado.OPERADOR);
	}

	public static Empleado tipoSupervisor(){
		return new Empleado(TipoEmpleado.SUPERVISOR);
	}

	public static Empleado tipoDirector(){
		return new Empleado(TipoEmpleado.DIRECTOR);
	}

	public TipoEmpleado getTipoEmpleado() {
		return tipoEmpleado;
	}

	public void setTipoEmpleado(TipoEmpleado tipoEmpleado) {
		this.tipoEmpleado = tipoEmpleado;
	}

	public synchronized EstadoEmpleado getEstadoEmpleado() {
		return estadoEmpleado;
	}

	public synchronized void setEstadoEmpleado(EstadoEmpleado estadoEmpleado) {
		System.out.println("El Empleado con id " + Thread.currentThread().getId() + " ahora esta en estado " + estadoEmpleado);
		this.estadoEmpleado = estadoEmpleado;
	}

	public synchronized List<LlamadaCliente> getLlamadaClienteEntrante() {
		return new ArrayList<>(llamadaClienteEntrante);
	}

	public void setLlamadaClienteEntrante(
			ConcurrentLinkedDeque<LlamadaCliente> llamadaClienteEntrante) {
		this.llamadaClienteEntrante = llamadaClienteEntrante;
	}

	public synchronized List<LlamadaCliente> getLlamadaClienteAtendida() {
		return new ArrayList<>(llamadaClienteAtendida);
	}

	public void setLlamadaClienteAtendida(
			ConcurrentLinkedDeque<LlamadaCliente> llamadaClienteAtendida) {
		this.llamadaClienteAtendida = llamadaClienteAtendida;
	}

	//Método que atiende una llamda entrante 
	public synchronized void atenderLlamada(LlamadaCliente llamadaEntrante){
		this.llamadaClienteEntrante.add(llamadaEntrante);
	}

	
	/**
	 * Metodo que se ejecuta para cambiar el estado del empleado segun la cantidad de llamadas en espera
	 * si existen llamadas en espera se cambia el estado del empleado de DISPONIBLE A OCUPADO 
	 * y cuando termina la llamada vuelve a cambiar el estado de OCUPADO a DISPONIBLE
	 */
	public void run() {
		System.out.println("El empleado con id " + Thread.currentThread().getId() + " empezó a trabajar");
		while(true){
			if (!this.llamadaClienteEntrante.isEmpty()) {
				LlamadaCliente llamadaCliente = this.llamadaClienteEntrante.poll();
				this.setEstadoEmpleado(EstadoEmpleado.OCUPADO);
				System.out.println("El empleado con id " + Thread.currentThread().getId() + " atiende una llamada de  " + llamadaCliente.getTiempoLlamadaSeg() + " segundos");
				try {
					TimeUnit.SECONDS.sleep(llamadaCliente.getTiempoLlamadaSeg());
				} catch (InterruptedException e) {
					System.out.println("El empleado con id " + Thread.currentThread().getId() + " fue interrumpido en una llamada de " + llamadaCliente.getTiempoLlamadaSeg() + " segundos");
				} finally {
					this.setEstadoEmpleado(EstadoEmpleado.DISPONIBLE);
				}
				this.llamadaClienteAtendida.add(llamadaCliente);
				System.out.println("El empleado con id " + Thread.currentThread().getId() + " termino una llamada de  " + llamadaCliente.getTiempoLlamadaSeg() + " segundos");
			}
		}
	}

}
