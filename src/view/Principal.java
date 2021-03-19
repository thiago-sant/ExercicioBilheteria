package view;

import java.util.concurrent.Semaphore;

import controller.ThreadBilheteria;

public class Principal {
	public static void main(String[] args) {
		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		for (int idComprador = 0; idComprador < 301; idComprador++) {
			Thread tBilheteria = new ThreadBilheteria(idComprador, semaforo);
			tBilheteria.start();
		}
	}
}
