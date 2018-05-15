package com.cgc.callCenter.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

/**
 * @author camilogc
 * Clase encargada de crear las llamadas y los randoms de tiempos de cada una
 */

public class LlamadaCliente {

	private Integer tiempoLlamadaSeg;

	/**
	 * Metodo que crea una nueva llamada
	 *
	 * @param tiempoLlamada tiene que ser mayor a cero
	 */
	public LlamadaCliente(Integer tiempoLlamada) {
		if(tiempoLlamada < 0 || tiempoLlamada == null){
			throw new IllegalArgumentException("El tiempo de llamada no puede ser menor a 0"); 
		}
		this.tiempoLlamadaSeg = tiempoLlamada;
	}

	/**
	 * Metodo que crea una llamada con un tiempo minimo y maximo
	 * @param maxSeg
	 * @param minSeg
	 * @return
	 */
	public static LlamadaCliente randomLlamada(Integer minSeg, Integer maxSeg) {
		if(maxSeg >= minSeg && minSeg >= 0){
			return new LlamadaCliente(ThreadLocalRandom.current().nextInt(minSeg, maxSeg + 1));
		}else{
			throw new IllegalArgumentException("Error en los tiempos min y max de llamada"); 
		}

	}

	/**
	 * Método que crea una lista de llamadas con un random de timepo
	 * @param listaR
	 * @param minSeg
	 * @param maxSeg
	 * @return
	 */
	public static List<LlamadaCliente> listaRandomLlamadas(Integer listaR, Integer minSeg, Integer maxSeg) {
		if(listaR >= 0){
			List<LlamadaCliente> listaLlamada = new ArrayList<>();
			for (int i = 0; i < listaR; i++) {
				listaLlamada.add(randomLlamada(minSeg, maxSeg));
			}
			return listaLlamada;
		}else{
			throw new IllegalArgumentException("Error la listaR es menor a 0"); 
		}		
	}

	public Integer getTiempoLlamadaSeg() {
		return tiempoLlamadaSeg;
	}

}
