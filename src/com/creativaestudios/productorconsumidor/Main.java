/**
 * 
 */
package com.creativaestudios.productorconsumidor;

import com.creativaestudios.productorconsumidor.consumidor.StringConsumidor;
import com.creativaestudios.productorconsumidor.productor.IProductor;
import com.creativaestudios.productorconsumidor.productor.StringProductor;
import com.creativaestudios.productorconsumidor.queue.IQueue;
import com.creativaestudios.productorconsumidor.queue.SynchronizedFIFO;

/**
 * @author Carlos
 * @since 09/07/2013
 */

// crea los subprocesos productor y consumidor.

public class Main {

	public static void main(String[] args) {

		// Puede que en una aplicacion real, el tipo de lista no se decidiese
		// aqui,
		// sino que viniese inferido y lo obtendriamos por
		// ejemplo con un patron factory.

		IQueue<String> buffer = new SynchronizedFIFO<String>();
		// para pprobar los distintos tipos de lista, es comodo aumentar el
		// rango de tiempo del sleep de los consumidores, asi en la lista se acumulan items
		// IQueue<String> buffer = new SynchronizedLIFO<String>();

		// crear objetos productor y consumidor, todos usan proxy al buffer.
		IProductor productor = new StringProductor(buffer);
		// Los consumidores no tiene mas funciones que las propias de Thread, no
		// definimos interface en este caso.
		// Si tuviesemos distintos tipos de consumidor con distinta
		// implementacion ,
		// habria que refactorizar (crear interfaz, y depender de la
		// abstraccion,
		// o crear un unico tipo pero usar patron strategy, prototype, o
		// similar)
		StringConsumidor consumidor1 = new StringConsumidor(
				Constants.NOMBRE_CONSUMIDOR1, buffer);
		StringConsumidor consumidor2 = new StringConsumidor(
				Constants.NOMBRE_CONSUMIDOR2, buffer);
		consumidor1.start();
		consumidor2.start();
		productor.startProducing();

	}

}
