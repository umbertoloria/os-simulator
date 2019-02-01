package com.umbertoloria.bitting;

public class BitUse {

	public static void set(Bit[] dest, boolean[] save) {
		if (dest.length != save.length) {
			throw new RuntimeException("Different sizes...");
		}
		for (int i = 0; i < dest.length; i++) {
			dest[i].set(save[i]);
		}
	}

	public static void set(Bit[] dest, Bit[] save) {
		if (dest.length != save.length) {
			throw new RuntimeException("Different sizes...");
		}
		for (int i = 0; i < dest.length; i++) {
			dest[i].set(save[i].get());
		}
	}

	public static void sub(Bit[] dest, Bit[] save, int from, int to) {
		if (dest.length != to - from) {
			throw new RuntimeException("Strange dimensions");
		}
		for (int i = 0; i < dest.length; i++) {
			dest[i].set(save[i + from].get());
		}
	}

	public static boolean[] toBools(Bit[] from) {
		boolean[] to = new boolean[from.length];
		for (int i = 0; i < to.length; i++) {
			to[i] = from[i].get();
		}
		return to;
	}

}
