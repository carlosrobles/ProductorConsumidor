package com.creativaestudios.productorconsumidor.consumidor;

import com.creativaestudios.productorconsumidor.Constants;
import com.creativaestudios.productorconsumidor.queue.IQueue;

public class StringConsumidor extends Thread {
	private IQueue<String> buffer;

	public StringConsumidor(String nombre, IQueue<String> buffer) {
		super(nombre);
		this.buffer = buffer;
	}

	public void run() {
		String temp;
		while (true) {
			try {
				// Llamamos  dequeue directamente, porque el consumidor no tiene que
				// organizarse con nadie, y ni si quiera debe conocer a otros
				// consumidores o al productor, y mucho menos su implementacion,
				// solo debe conocer a la cola de la que consume, y saber como
				// comunicarse con ella. Es la cola la que decide como gestionar
				// sus recursos por tanto, el consumidor llama, y si cola
				// decide si sirve, lo pone en espera, o lo que haga falta.
				temp = buffer.dequeue();
				System.out.println(getName() + ": " + temp);
				Thread.sleep(Constants.MINIMO_PAUSA_CONSUMIDOR
						+ ((int) (Math.random() * (Constants.MAXIMO_PAUSA_CONSUMIDOR - Constants.MINIMO_PAUSA_CONSUMIDOR))));
			} catch (InterruptedException excepcion) {
				excepcion.printStackTrace();
			}
		}

	}
}
