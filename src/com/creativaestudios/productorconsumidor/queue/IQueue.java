package com.creativaestudios.productorconsumidor.queue;

// La interfaz Bufer especifica los métodos llamados por el Productor y el Consumidor.

public interface IQueue<T> {

	public void enqueue(T item);// adds an item

	public T dequeue();// Returns and removes an item
	
	public int count();

}