package com.umbertoloria;

import com.umbertoloria.utils.ALUUtils;
import com.umbertoloria.utils.BinaryUtils;
import com.umbertoloria.utils.BitsUtils;

import java.util.Arrays;

public class ALU {

	private boolean[] mode = new boolean[4];
	private boolean[] a = new boolean[Computer.ARCH];
	private boolean[] b = new boolean[Computer.ARCH];
	private boolean[] out = new boolean[Computer.ARCH];
	private boolean[] zero = new boolean[Computer.ARCH];

	void setMode(boolean[] mode) {
		int n = BinaryUtils.toAbsInt(mode);
		if (n <= 12) {
			System.arraycopy(mode, 0, this.mode, 0, 4);
		} else {
			throw new RuntimeException("ALU mode non valida.");
		}
	}

	// A
	public void setA(boolean[] a) {
		if (a.length != Computer.ARCH) {
			throw new RuntimeException();
		}
		BitsUtils.set(this.a, a);
	}

	// B
	void setB(boolean[] b) {
		if (b.length != Computer.ARCH) {
			throw new RuntimeException();
		}
		BitsUtils.set(this.b, b);
	}

	// Out
	boolean[] getOUT() {
		return out.clone();
	}

	// Clock
	void clock() { // TODO: add eqa and diff
		if (Arrays.equals(mode, ALUUtils.AND)) { // <- e mo?
			and(a, b, out);
		} else if (Arrays.equals(mode, ALUUtils.OR)) {
			or(a, b, out);
		} else if (Arrays.equals(mode, ALUUtils.NOT)) {
			not(a, out);
		} else if (Arrays.equals(mode, ALUUtils.ADD)) {
			add(a, b, out);
		} else if (Arrays.equals(mode, ALUUtils.SUB)) {
			sub(a, b, out);
		}/* else if (Arrays.equals(mode, ALUUtils.GOTV)) {

		} else if (Arrays.equals(mode, ALUUtils.GOTF)) {

		}*/ else if (Arrays.equals(mode, ALUUtils.EQA)) {
			sub(a, b, out);
			if (Arrays.equals(out, zero)) {
				BitsUtils.setEnd(out, true); // TODO: test this!
			} else {
				BitsUtils.set(out, zero);
			}
		}/* else if (Arrays.equals(mode, ALUUtils.DIF)) {

		} else if (Arrays.equals(mode, ALUUtils.LOW)) {

		} else if (Arrays.equals(mode, ALUUtils.LOWEQ)) {

		} else if (Arrays.equals(mode, ALUUtils.GRE)) {

		} else if (Arrays.equals(mode, ALUUtils.GREEQ)) {

		} else if (Arrays.equals(mode, ALUUtils.LOAD)) {

		} else if (Arrays.equals(mode, ALUUtils.STORE)) {

		} */ else {
			throw new RuntimeException("Istruzione ALU non capita.");
		}
	}

	private static void and(boolean[] a, boolean[] b, boolean[] res) {
		for (int i = 0; i < a.length; i++) {
			res[i] = ALUUtils.and(a[i], b[i]);
		}
	}

	private static void or(boolean[] a, boolean[] b, boolean[] res) {
		for (int i = 0; i < a.length; i++) {
			res[i] = ALUUtils.or(a[i], b[i]);
		}
	}

	private static void not(boolean[] a, boolean[] res) {
		for (int i = 0; i < a.length; i++) {
			res[i] = ALUUtils.not(a[i]);
		}
	}

	public static void add(boolean[] a, boolean[] b, boolean[] res) {
		boolean carryIn = false;
		for (int i = a.length - 1; i >= 0; i--) {
			boolean[] added = ALUUtils.adder(a[i], b[i], carryIn);
			res[i] = added[0];
			carryIn = added[1];
		}
	}

	private static void sub(boolean[] a, boolean[] b, boolean[] res) {
		boolean carryIn = true;
		for (int i = a.length - 1; i >= 0; i--) {
			boolean[] added = ALUUtils.adder(a[i], ALUUtils.not(b[i]), carryIn);
			res[i] = added[0];
			carryIn = added[1];
		}
	}

}
