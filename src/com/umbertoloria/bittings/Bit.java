package com.umbertoloria.bittings;

public class Bit {

	public static int numNews[] = new int[100];
	public static int instructions = 0;

	private boolean val;

	public Bit() {
		this.val = false;
		numNews[instructions]++;
	}

	public Bit(boolean val) {
		this.val = val;
		numNews[instructions]++;
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
