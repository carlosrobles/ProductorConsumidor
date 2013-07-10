package com.creativaestudios.productorconsumidor.queue;

//Esta es la implemenctacion que se ha pedido en el ejercico.
public class SynchronizedFIFO<T> extends SynchronizedQueue<T> {

	@Override
	public synchronized T doDequeue() {
 
		return buffer.remove(0);

	}
}