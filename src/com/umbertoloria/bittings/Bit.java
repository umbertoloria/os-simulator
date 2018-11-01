package com.umbertoloria.bittings;

public class Bit {

	private boolean val;

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

	public boolean equals(Bit b) {
		return get() == b.get();
	}

	public static Bit[] array(String str) {
		Bit[] bits = new Bit[str.length()];
		for (int i = 0; i < bits.length; i++) {
			bits[i] = new Bit(str.charAt(i) == '1');
		}
		return bits;
	}

	public String toString() {
		return val ? "1" : "0";
	}

}
