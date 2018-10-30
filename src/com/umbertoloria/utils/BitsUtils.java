package com.umbertoloria.utils;

public class BitsUtils {

	public static void set(boolean[] set, boolean[] save) {
		if (save.length <= set.length) {
			System.arraycopy(save, 0, set, 0, save.length);
			// TODO: VEDERE SE MI SERVE VERAMENTE!
			for (int i = save.length; i < set.length; i++) {
				set[i] = false;
			}
		} else {
			throw new RuntimeException("save non ci va in set");
		}
	}

	public static void setEnd(boolean[] set, boolean[] save) {
		if (save.length <= set.length) {
			System.arraycopy(save, 0, set, set.length - save.length, save.length);
			// TODO: VEDERE SE MI SERVE VERAMENTE!
			for (int i = 0; i < set.length - save.length; i++) {
				set[i] = false;
			}
		} else {
			throw new RuntimeException("save non ci va in set");
		}
	}

	public static void setEnd(boolean[] set, boolean save) {
		set[set.length - 1] = save;
	}

	public static int append(boolean[] stream, int offset, boolean x) {
		stream[offset] = x;
		return offset + 1;
	}

	public static int append(boolean[] stream, int offset, boolean[] x) {
		System.arraycopy(x, 0, stream, offset, x.length);
		/*for (int i = 0; i < x.length; i++) {
			stream[offset + i] = x[i];
		}*/
		return offset + x.length;
	}

	public static boolean[] truncate(boolean[] x, int length) {
		boolean[] res = new boolean[length];
		System.arraycopy(x, 0, res, 0, length);
		/*for (int i = 0; i < length; i++) {
			res[i] = x[i];
		}*/
		return res;
	}

	public static boolean[] startAt(boolean[] x, int start) {
		boolean[] res = new boolean[x.length - start];
		System.arraycopy(x, start, res, 0, res.length);
		/*for (int i = 0; i < length; i++) {
			res[i] = x[i];
		}*/
		return res;
	}

	public static boolean endsWith(boolean[] a, boolean[] check) {
		int offset = a.length - check.length;
		int i;
		for (i = 0; i < check.length; i++) {
			if (a[offset + i] != check[i]) {
				break;
			}
		}
		return i >= check.length;
	}

	public static void print(boolean[] x) {
		for (boolean b : x) {
			System.out.print(b ? '1' : '0');
		}
		System.out.println();
	}

}