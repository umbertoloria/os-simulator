package com.umbertoloria.bitting;

public class BitCheck {

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
