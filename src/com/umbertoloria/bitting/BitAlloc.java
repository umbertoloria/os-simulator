package com.umbertoloria.bitting;

public class BitAlloc {

	public static Bit[] create(int length, boolean save) {
		Bit[] res = new Bit[length];
		for (int i = 0; i < res.length; i++) {
			res[i] = new Bit(save);
		}
		return res;
	}

	public static Bit[] create(String str) {
		Bit[] bits = new Bit[str.length()];
		for (int i = 0; i < str.length(); i++) {
			bits[i] = new Bit(str.charAt(i) == '1');
		}
		return bits;
	}

	public static Bit[] truncate(Bit[] from, int length) {
		Bit[] res = new Bit[length];
		System.arraycopy(from, 0, res, 0, length);
		return res;
	}

}
