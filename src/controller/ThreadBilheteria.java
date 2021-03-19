package controller;

import java.util.concurrent.Semaphore;

public class ThreadBilheteria extends Thread {
	static private int ingressos = 100;
	private int ingressosCompros;
	private int idComprador;
	private Semaphore semaforo;
	
	public ThreadBilheteria(int idComprador, Semaphore semaforo){
		this.idComprador = idComprador;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		Login();
	}

	private void Login() {
		int tempoLogin = (int)((Math.random()* 1951)+ 50);
		try {
			sleep(tempoLogin);
			if (tempoLogin > 1000) {
				System.out.println("Tempo de opera��o excedido.(TIMEOUT)");
			}
			else {
				ProcessoCompra();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	private void ProcessoCompra() {
		int tempoProcesso = (int)((Math.random()* 2001) + 1000);
		try {
			sleep(tempoProcesso);
			if (tempoProcesso > 2500) {
				System.out.println("Tempo de sess�o excedido. A compra n�o poder� ser realizada.");
			}
			else {
				semaforo.acquire();
				ValidacaoCompra();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	private void ValidacaoCompra() {
		ingressosCompros = (int)((Math.random() * 4) + 1);
		if (ingressos >= ingressosCompros) {
			ingressos -= ingressosCompros;
			System.out.println("O comprador #" +idComprador+ " comprou " +ingressosCompros+ " ingressos!. Restam "
					+ingressos+ " ingressos.");
		}
		else {
			System.out.println("O comprador #" +idComprador+ " tentou comprar "+ingressosCompros+ " ingressos. "
					+ "Quantidade n�o dispon�vel.");
		}
	}
}
