package com.creativaestudios.productorconsumidor.queue;

//Esta   implemenctacion sobra, pero la incluyo 
//para poner un ejemplo de como de facil seria crear otro tipo de lista
public class SynchronizedLIFO<T> extends SynchronizedQueue<T> {

	@Override
	public   T doDequeue() {

		return buffer.remove(buffer.size() - 1);

	}
}