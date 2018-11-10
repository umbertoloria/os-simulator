package com.umbertoloria.utils;

public class BinUtils {

	public static boolean[] convertAbs(int digit, int size) throws RuntimeException {
		boolean[] res = new boolean[size];
		System.arraycopy(convert(digit, size + 1), 1, res, 0, res.length);
		return res;
	}

	private static boolean[] convert(int digit, int size) throws RuntimeException {
		boolean[] bits = new boolean[size];
		if (digit < -Math.pow(2, size - 1) || digit > Math.pow(2, size - 1) - 1) {
			throw new RuntimeException(digit + " non rappresentabile in " + size + " bit.");
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

	/*public static String toStr(boolean[] n) {
		StringBuilder str = new StringBuilder();
		for (boolean c : n) {
			str.append(c ? '1' : '0');
		}
		return str.toString();
	}

	public static int toInt(boolean[] bits) {
		int res = -(int) Math.pow(2, bits.length - 1) * (bits[0] ? 1 : 0);
		for (int i = 1; i < bits.length; i++) {
			res += (int) Math.pow(2, bits.length - 1 - i) * (bits[i] ? 1 : 0);
		}
		return res;
	}

	public static boolean[] addZeros(String str, int size) {
		boolean[] bits = new boolean[size];
		boolean[] toadd = toRawBools(str);
		int offset = size - toadd.length;
		for (int i = 0; i < toadd.length; i++) {
			bits[offset + i] = toadd[i];
		}
		return bits;
	}

	public static void add(boolean[] a, boolean[] b, boolean[] res) {
		boolean carryIn = false;
		for (int i = a.length - 1; i >= 0; i--) {
			boolean[] added = ALUUtils.adder(a[i], b[i], carryIn);
			res[i] = added[0];
			carryIn = added[1];
		}
	}

	public static void and(boolean[] a, boolean[] b, boolean[] res) {
		for (int i = 0; i < a.length; i++) {
			res[i] = ALUUtils.and(a[i], b[i]);
		}
	}

	public static void or(boolean[] a, boolean[] b, boolean[] res) {
		for (int i = 0; i < a.length; i++) {
			res[i] = ALUUtils.or(a[i], b[i]);
		}
	}

	public static boolean not(boolean[] a, boolean[] res) {
		for (int i = 0; i < a.length; i++) {
			res[i] = ArithmeticLogicUnit.not(a[i]);
		}
		return true;
	}*/

}
