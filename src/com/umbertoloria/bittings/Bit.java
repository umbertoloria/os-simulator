package com.umbertoloria.bittings;

public class Bit {

	private static int[] numNews = new int[100];
	private static String[] scopeNames = new String[100];
	private static int instructions = 0;
	private static boolean watching = false;
	private static boolean output = false;

	private static void newBit() {
		numNews[instructions]++;
		if (output) {
			System.out.println("new Bit");
		}
	}

	public static void WATCH(String name) {
		if (instructions == 0 || watching) {
			if (instructions == 0) {
				scopeNames[instructions] = "Init";
			}
			eWATCH();
		}
		scopeNames[instructions] = name;
		watching = true;
	}

	public static void eWATCH() {
		watching = false;
		System.err.printf("%4d bits created on [ %-33s ]\n", numNews[instructions], scopeNames[instructions]);
		instructions++;
	}

	public static void enableOutput () {
		output = true;
	}

	private boolean val;

	public Bit() {
		this.val = false;
		newBit();
	}

	public Bit(boolean val) {
		this.val = val;
		newBit();
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
