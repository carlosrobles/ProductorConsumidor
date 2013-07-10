package com.creativaestudios.productorconsumidor.queue;

import java.util.ArrayList;

//Aunque puede ser tentador, porque si pensamos en cola y stack, las funciones son practicamente las mismas, 
// No hago una clase madre, por ejemplo, SynchronizedQueue porque no habria ninguna funcion 
//que fuese realmente comun, ya que tanto queue como dequeue dependen del tipo de lista
public abstract class SynchronizedQueue<T> extends BaseQueue<T> {

	public SynchronizedQueue() {
		// El tipo de lista deberia decidirse en la clase concreta, (y no en una
		// abstracta),
		// porque puede ser determinante en la implementacion.
		// ahora lo hago aqui porque es una bunea base, en las dos sublcases que
		// estoy creando nos sirve con el mismo
		buffer = new ArrayList<T>();
	}

	// cerramos para modificacion (para garantizar que cualquier clase hija
	// sigue siendo sincronizada)
	// pero abrimos ara extension,a traves de doDequeu
	@Override
	public final synchronized T dequeue() {
		// mientras no haya datos que leer, colocar el hilo que llama en espera
		if (buffer.size() == 0) {
			try {
				wait();
			} catch (InterruptedException excepcion) {
				excepcion.printStackTrace();
			}

		}

		// En el caso de que si que hubiese, o que se haya recibido un notify,
		// podemos leer tranuilamente, porque sabemos que al estar syncronized,
		// no va a haber dos accesos simultaneos
		return doDequeue();

	}

	// La hago astracta porque cada subtipo deberia decidir como sacar el
	// elemento solicitado
	// no deberia obviarse una forma normal (primero o ultimo)
	protected abstract T doDequeue();

	@Override
	public final synchronized void enqueue(T item) {

		/*
		 * //CASO Z: Si quisiesemos garantizar que encolamos de uno en uno...
		 * while (buffer.size()!=0) {try { wait(); } catch (InterruptedException
		 * excepcion) { excepcion.printStackTrace(); }
		 * 
		 * }
		 */
		doEnqueue(item);

		notify();

	}

	// esta no la hago abstracta porque si que hay una forma conceptualmente
	// normal de añadir algo a una lista, que es ponerla al final, por tanto la
	// dejamos como funcionalidad por defecto
	protected void doEnqueue(T item) {

		buffer.add(item);

	}
	
	public synchronized int count() { //lo sincronizamos tb para que no haya lios
		if (buffer != null)
			return buffer.size();
		return 0;

	}

}