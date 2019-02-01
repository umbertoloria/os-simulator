package com.umbertoloria.bitting;

public class Bit {

	private boolean val;

	public Bit() {
		this.val = false;
	}

	public Bit(boolean val) {
		this.val = val;
	}

	public void set(boolean val) {
		this.val = val;
	}

	public void enable() {
		val = true;
	}

	public void disable() {
		val = false;
	}

	public boolean get() {
		return val;
	}

	public String toString() {
		return val ? "1" : "0";
	}

}
