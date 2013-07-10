package com.creativaestudios.productorconsumidor.queue;

import java.util.List;

// La interfaz Bufer especifica los métodos llamados por el Productor y el Consumidor.

public abstract class BaseQueue<T> implements IQueue<T> {
	// extends ArrayList<T>
	// prefiero composition (vs inheritance), porque no me interesa exponer los
	// metodos de List.
	List<T> buffer; 

	public int count() {
		if (buffer != null)
			return buffer.size();
		return 0;

	}
}