package com.umbertoloria;

import com.umbertoloria.utils.ALUUtils;
import com.umbertoloria.utils.BinaryUtils;
import com.umbertoloria.utils.BitsUtils;

import java.util.Arrays;

public class ALU {

	private static final boolean[] AND = BinaryUtils.toRawBools("0000");
	private static final boolean[] OR = BinaryUtils.toRawBools("0001");
	private static final boolean[] NOT = BinaryUtils.toRawBools("0010");

	static final boolean[] ADD = BinaryUtils.toRawBools("0011");
	private static final boolean[] SUB = BinaryUtils.toRawBools("0100");

	//public static final boolean[] GOTV = BinaryUtils.toRawBools("0101");
	//public static final boolean[] GOTF = BinaryUtils.toRawBools("0110");

	private static final boolean[] EQA = BinaryUtils.toRawBools("0111");
	//public static final boolean[] DIF = BinaryUtils.toRawBools("1000");

	//public static final boolean[] LOW = BinaryUtils.toRawBools("1001");
	//public static final boolean[] LOWEQ = BinaryUtils.toRawBools("1010");
	//public static final boolean[] GRE = BinaryUtils.toRawBools("1011");
	//static final boolean[] GREEQ = BinaryUtils.toRawBools("1100");

	//public static final boolean[] LOAD = ADD;
	//public static final boolean[] STORE = ADD;

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
	void clock() { // TODO: equals and different
		if (Arrays.equals(mode, AND)) {
			and(a, b, out);
		} else if (Arrays.equals(mode, OR)) {
			or(a, b, out);
		} else if (Arrays.equals(mode, NOT)) {
			not(a, out);
		} else if (Arrays.equals(mode, ADD)) {
			add(a, b, out);
		} else if (Arrays.equals(mode, SUB)) {
			sub(a, b, out);
		}/* else if (Arrays.equals(mode, GOTV)) {

		} else if (Arrays.equals(mode, GOTF)) {

		}*/ else if (Arrays.equals(mode, EQA)) {
			sub(a, b, out);
			if (Arrays.equals(out, zero)) {
				BitsUtils.setEnd(out, true); // TODO: test this!
			} else {
				BitsUtils.set(out, zero);
			}
		}/* else if (Arrays.equals(mode, DIF)) {

		} else if (Arrays.equals(mode, LOW)) {

		} else if (Arrays.equals(mode, LOWEQ)) {

		} else if (Arrays.equals(mode, GRE)) {

		} else if (Arrays.equals(mode, GREEQ)) {

		}

		else if (Arrays.equals(mode, LOAD)) {

		} else if (Arrays.equals(mode, STORE)) {

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
