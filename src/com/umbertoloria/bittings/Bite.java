package com.umbertoloria.bittings;

public class Bite {

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

	public static Bit[] initSet(int length, boolean save) {
		Bit[] res = new Bit[length];
		for (int i = 0; i < res.length; i++) {
			res[i] = new Bit(save);
		}
		return res;
	}

	public static void linkLeft(Bit[] dest, Bit[] link) {
		if (dest.length < link.length) {
			throw new RuntimeException("Left array smaller than right array");
		}
		System.arraycopy(link, 0, dest, 0, link.length);
	}

	public static void linkSub(Bit[] dest, Bit[] bits, int from, int to) {
		if (dest.length != to - from) {
			throw new RuntimeException("Strange dimensions");
		}
		System.arraycopy(bits, from, dest, 0, dest.length);
	}

	public static Bit[] truncate(Bit[] from, int length) {
		Bit[] res = new Bit[length];
		System.arraycopy(from, 0, res, 0, length);
		return res;
	}

	public static boolean[] toBools(Bit[] from) {
		boolean[] to = new boolean[from.length];
		for (int i = 0; i < to.length; i++) {
			to[i] = from[i].get();
		}
		return to;
	}

	public static Bit[] toBits(String str) {
		Bit[] bits = new Bit[str.length()];
		for (int i = 0; i < str.length(); i++) {
			bits[i] = new Bit(str.charAt(i) == '1');
		}
		return bits;
	}

	public static boolean equals(Bit[] a, boolean[] b) {
		if (a.length != b.length) {
			return false;
		}
		for (int i = 0; i < a.length; i++) {
			if (a[i].get() != b[i]) {
				return false;
			}
		}
		return true;
	}

	public static boolean endsWith(Bit[] a, boolean[] check) {
		int offset = a.length - check.length;
		int i;
		for (i = 0; i < check.length; i++) {
			if (a[offset + i].get() != check[i]) {
				break;
			}
		}
		return i >= check.length;
	}

}
