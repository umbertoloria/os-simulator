package com.umbertoloria.bitting;

public class BitLink {

	public static void link(Bit[] dest, Bit[] link) {
		if (dest.length != link.length) {
			throw new RuntimeException("Different sizes");
		}
		System.arraycopy(link, 0, dest, 0, link.length);
	}

	public static void linkSub(Bit[] dest, Bit[] bits, int from, int to) {
		if (dest.length != to - from) {
			throw new RuntimeException("Wrong size");
		}
		System.arraycopy(bits, from, dest, 0, dest.length);
	}

}
