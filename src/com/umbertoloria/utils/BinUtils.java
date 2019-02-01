package com.umbertoloria.utils;

public class BinUtils {

	public static boolean[] convertAbs(int digit, int size) throws RuntimeException {
		boolean[] res = new boolean[size];
		System.arraycopy(convert(digit, size + 1), 1, res, 0, res.length);
		return res;
	}

	public static boolean[] convert(int digit, int size) throws RuntimeException {
		boolean[] bits = new boolean[size];
		if (digit < -Math.pow(2, size - 1) || digit > Math.pow(2, size - 1) - 1) {
			throw new RuntimeException(digit + " non rappresentabile in " + size + " bit");
		}
		boolean complemento = digit < 0;
		if (complemento) {
			digit = -digit;
		}
		int i;
		for (i = size - 1; i >= 0; i--) {
			bits[i] = (digit % 2) == 1;
			digit = digit / 2;
		}
		if (complemento) {
			for (i = size - 1; i >= 0; i--) {
				if (bits[i]) {
					break;
				}
			}
			for (i -= 1; i >= 0; i--) {
				bits[i] = !bits[i];
			}
		}
		return bits;
	}

	static boolean[] toRawBools(String str) {
		boolean[] bits = new boolean[str.length()];
		for (int i = 0; i < bits.length; i++) {
			bits[i] = str.charAt(i) == '1';
		}
		return bits;
	}

	public static int toAbsInt(boolean[] bits) {
		int res = 0;
		for (int i = 0; i < bits.length; i++) {
			res += (int) Math.pow(2, bits.length - 1 - i) * (bits[i] ? 1 : 0);
		}
		return res;
	}

	public static String toStr(boolean[] from) {
		StringBuilder sb = new StringBuilder();
		for (boolean b : from) {
			sb.append(b ? '1' : '0');
		}
		return sb.toString();
	}

}
