package com.umbertoloria.utils;

public class ALUUtils {

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

	/*public static boolean not(boolean a) {
		return !a;
	}*/

}
