package com.umbertoloria.utils;

public abstract class Daemon implements Runnable {

	private Thread th = new Thread(this);
	private boolean run = false;
	private String title;

	public void start() {
		if (!run) {
			run = true;
			th.setName(title);
			th.start();
		}
	}

	public void stop() {
		if (run) {
			run = false;
		}
	}

	@Override
	public void run() {
		while (run) {
			loop();
			/*try {
				Thread.sleep(millis);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
	}

	protected abstract void loop();

	public Daemon(String title) {
		this.title = title;
	}
}
