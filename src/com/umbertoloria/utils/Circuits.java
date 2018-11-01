package com.umbertoloria.utils;

public class Circuits {

	public static boolean[] adder(boolean a, boolean b, boolean carryIn) {
		boolean carryOut = (!a && b && carryIn) || (a && (b || carryIn));
		boolean s = (!a && !b && carryIn) || (!a && b && !carryIn) || (a && !b && !carryIn) || (a && b && carryIn);
		return new boolean[]{s, carryOut};
	}

	public static boolean and(boolean a, boolean b) {
		return a && b;
	}

	public static boolean or(boolean a, boolean b) {
		return a || b;
	}

	public static boolean not(boolean a) {
		return !a;
	}

	/*

	public static Bit[] adder(Bit a, Bit b, Bit carryIn) {
		Bit carryOut = new Bit((!a.get() && b.get() && carryIn.get()) || (a.get() && (b.get() || carryIn.get())));
		Bit s = new Bit((!a.get() && !b.get() && carryIn.get()) || (!a.get() && b.get() && !carryIn.get()) ||
				(a.get() && !b.get() && !carryIn.get()) || (a.get() && b.get() && carryIn.get()));
		return new Bit[]{s, carryOut};
	}

	public static Bit and(Bit a, Bit b) {
		return new Bit(a.get() && b.get());
	}

	public static Bit or(Bit a, Bit b) {
		return new Bit(a.get() || b.get());
	}

	public static Bit not(Bit a) {
		return new Bit(!a.get());
	}

	 */

}
