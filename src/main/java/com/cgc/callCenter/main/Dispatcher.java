package com.cgc.callCenter.main;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Clase encargada del manejo de las llamadas
 * @author camilogc
 * 
 */

public class Dispatcher implements Runnable{

	public static final int MAXIMO_LLAMADAS = 10;

	private ExecutorService executorService;
	private ConcurrentLinkedDeque<Empleado> empleados;
	private ConcurrentLinkedDeque<LlamadaCliente> llamadaEntranteCliente;
	private CentroLlamada centroLlamada;
	private boolean banderaHilo;

	public Dispatcher(List<Empleado> empleados) {
		this(empleados, new GestionCentroLlamada());
	}

	public Dispatcher(List<Empleado> empleados,
			CentroLlamada centroLlamada) {
		if(empleados != null && centroLlamada != null){
			this.empleados = new ConcurrentLinkedDeque(empleados);
			this.centroLlamada = centroLlamada;
			this.llamadaEntranteCliente = new ConcurrentLinkedDeque<>();
			this.executorService = Executors.newFixedThreadPool(MAXIMO_LLAMADAS);
		}

	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void setExecutorService(ExecutorService executorService) {
		this.executorService = executorService;
	}

	public CentroLlamada getCentroLlamada() {
		return centroLlamada;
	}

	public synchronized boolean getBanderaHilo() {
		return banderaHilo;
	}

	public synchronized void dispatchCall(LlamadaCliente llamadaCliente) {
		System.out.println("Entra al call center una llamada de " + llamadaCliente.getTiempoLlamadaSeg() + " segundos");
		this.llamadaEntranteCliente.add(llamadaCliente);
	}

	public synchronized void inicioDespachar() {
		this.banderaHilo = true;
		for (Empleado empleado : this.empleados) {
			this.executorService.execute(empleado);
		}
	}

	public synchronized void stopDespachar() {
		this.banderaHilo = false;
		this.executorService.shutdown();
	}

	/**
	 * Metodo que busca un empleado disponible para atender una llamada que esta en espera
	 */
	public void run() {
		while(getBanderaHilo()){
			if(!this.llamadaEntranteCliente.isEmpty()){
				Empleado empleado = this.centroLlamada.buscarEmpleado(this.empleados);
				if(empleado == null){
					continue;
				}
				LlamadaCliente llamadaCliente = this.llamadaEntranteCliente.poll();
				try{
					empleado.atenderLlamada(llamadaCliente);
				}catch(Exception e) {
					this.llamadaEntranteCliente.addFirst(llamadaCliente);
				}
			}else{
				continue;
			}
		}
	}

}
