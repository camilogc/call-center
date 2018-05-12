/**
 * 
 */
package com.cgc.callCenter.main;

/**
 * @author camilogc
 *
 */
public enum TipoEmpleado {
	OPERADOR, //El primero que recibe una llamada
	SUPERVISOR, //Recibe llamada si el operador esta ocupado
	DIRECTOR //Recibe llamada si el supervisor o el operador estan ocupados
}
