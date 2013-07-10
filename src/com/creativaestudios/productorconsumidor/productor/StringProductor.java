package com.creativaestudios.productorconsumidor.productor;

import com.creativaestudios.productorconsumidor.Constants;
import com.creativaestudios.productorconsumidor.queue.IQueue;

public class StringProductor implements IProductor {
	private IQueue<String> buffer;
	private int counter = 0;

	public StringProductor(IQueue<String> buffer) {

		this.buffer = buffer;
	}

	public void startProducing() {
		String temp;

		// TODO, en el mundo real, seguramente le pasariamos tambien un objeto
		// con la estrategia de produccion, siguiendo el patron Strategy, en
		// lugar
		// de que el productor hicie temp = new String("item " + counter);
		// haria temp = strategy.produceNextItem(), pero de momento aqui tampoco
		// quiero enredarlo demasiado
		while (true) {
			try {
				counter++;
				temp = new String("item " + counter);
				 
				 System.out.println("____Produced " + temp + " ___"); 
				buffer.enqueue(temp);
				Thread.sleep(Constants.PAUSA_PRODUCTOR);

			} catch (InterruptedException excepcion) {
				System.out.println("Error en Produced :");
				excepcion.printStackTrace();
			}

		}

	}
}