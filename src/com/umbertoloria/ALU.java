package com.umbertoloria;

import com.umbertoloria.integrates.OR;
import com.umbertoloria.utils.ALUUtils;
import com.umbertoloria.utils.BinaryUtils;
import com.umbertoloria.utils.BitsUtils;
import com.umbertoloria.utils.Circuits;

import java.util.Arrays;

public class ALU {

	private boolean[] mode = new boolean[4];
	private boolean[] a = new boolean[Computer.ARCH];
	private boolean[] b = new boolean[Computer.ARCH];
	private boolean[] result = new boolean[Computer.ARCH];
	private boolean[] zero = new boolean[Computer.ARCH];
	private OR orGate = new OR(Computer.ARCH);
	// TODO: maybe the eigth register is zero?

	/**
	 Sets the ALU Mode.
	 @param mode will be the new ALU Mode
	 */
	void setMode(boolean[] mode) {
		int n = BinaryUtils.toAbsInt(mode);
		if (n <= 12) {
			System.arraycopy(mode, 0, this.mode, 0, 4);
		} else {
			throw new RuntimeException("ALU mode non valida.");
		}
	}

	/**
	 Sets the first operand of the ALU.
	 @param a will be set on the first operand
	 */
	public void setA(boolean[] a) {
		if (a.length != Computer.ARCH) {
			throw new RuntimeException();
		}
		BitsUtils.set(this.a, a);
	}

	/**
	 Sets the second operand of the ALU.
	 @param b will be set on the second operand
	 */
	void setB(boolean[] b) {
		if (b.length != Computer.ARCH) {
			throw new RuntimeException();
		}
		BitsUtils.set(this.b, b);
	}

	/**
	 Gets the last output produced by the ALU after a clock call.
	 @return the result
	 @see ALU
	 */
	boolean[] getResult() {
		return result.clone();
	}

	/**
	 Executes the older of all the instructions on the queue.
	 */
	void clock() {
		if (Arrays.equals(mode, ALUUtils.AND)) { // <- e mo?
			and(a, b, result);
		} else if (Arrays.equals(mode, ALUUtils.OR)) {
			or(a, b, result);
		} else if (Arrays.equals(mode, ALUUtils.NOT)) {
			not(a, result);
		} else if (Arrays.equals(mode, ALUUtils.ADD)) {
			System.out.println();
			add(a, b, result);
			System.out.println();
		} else if (Arrays.equals(mode, ALUUtils.SUB)) {
			sub(a, b, result);
		}/* else if (Arrays.equals(mode, ALUUtils.GOTV)) {

		} else if (Arrays.equals(mode, ALUUtils.GOTF)) {

		}*/ else if (Arrays.equals(mode, ALUUtils.EQA)) {
			sub(a, b, result);
			if (!thereAreAnyTrues(result, 0)) {
				BitsUtils.setEnd(result, true);
			} else {
				BitsUtils.set(result, zero);
			}
		} else if (Arrays.equals(mode, ALUUtils.DIFF)) { // TODO: forse togliere l'istruzione diff o eqa?
			sub(a, b, result);
			// tutti i bit messi in or. se esce zero allora erano tutti zero
			if (thereAreAnyTrues(result, 0)) {
				BitsUtils.set(result, zero);
				BitsUtils.setEnd(result, true);
			} else {
				BitsUtils.set(result, zero);
			}
		} else if (Arrays.equals(mode, ALUUtils.LOW)) {
			sub(a, b, result);
			if (result[0]) {
				BitsUtils.set(result, zero);
				BitsUtils.setEnd(result, true);
			} else {
				BitsUtils.set(result, zero);
			}
		} else if (Arrays.equals(mode, ALUUtils.LOWEQ)) {
			sub(a, b, result);
			// Short-Circuit Evaluation: thereAreAnyTrues viene chiamata solo se out[0] = false
			if (result[0] || !thereAreAnyTrues(result, 1)) {
				BitsUtils.set(result, zero);
				BitsUtils.setEnd(result, true);
			} else {
				BitsUtils.set(result, zero);
			}
		} else if (Arrays.equals(mode, ALUUtils.GRE)) {
			sub(a, b, result);
			if (!result[0] && thereAreAnyTrues(result, 1)) {
				BitsUtils.set(result, zero);
				BitsUtils.setEnd(result, true);
			} else {
				BitsUtils.set(result, zero);
			}
		} else if (Arrays.equals(mode, ALUUtils.GREEQ)) {
			sub(a, b, result);
			if (!result[0]) {
				BitsUtils.set(result, zero);
				BitsUtils.setEnd(result, true);
			} else {
				BitsUtils.set(result, zero);
			}
		}/* else if (Arrays.equals(mode, ALUUtils.LOAD)) {

		} else if (Arrays.equals(mode, ALUUtils.STORE)) {

		}*/ else {
			throw new RuntimeException("Istruzione ALU non capita.");
		}
	}

	// FIXME: fare questo meglio!
	private boolean thereAreAnyTrues(boolean[] in, int offset) {
		for (int i = offset; i < in.length; i++) {
			orGate.set(i - offset, in[i]);
		}
		for (int i = in.length - offset; i < orGate.size(); i++) {
			orGate.set(i, false);
		}
		orGate.clock();
		return orGate.get();
	}

	private static void and(boolean[] a, boolean[] b, boolean[] res) {
		for (int i = 0; i < a.length; i++) {
			res[i] = Circuits.and(a[i], b[i]);
		}
	}

	private static void or(boolean[] a, boolean[] b, boolean[] res) {
		for (int i = 0; i < a.length; i++) {
			res[i] = Circuits.or(a[i], b[i]);
		}
	}

	private static void not(boolean[] a, boolean[] res) {
		for (int i = 0; i < a.length; i++) {
			res[i] = Circuits.not(a[i]);
		}
	}

	private static void add(boolean[] a, boolean[] b, boolean[] res) {
		boolean carryIn = false;
		for (int i = a.length - 1; i >= 0; i--) {
			boolean[] added = Circuits.adder(a[i], b[i], carryIn);
			res[i] = added[0];
			carryIn = added[1];
		}
	}

	private static void sub(boolean[] a, boolean[] b, boolean[] res) {
		boolean carryIn = true;
		for (int i = a.length - 1; i >= 0; i--) {
			boolean[] added = Circuits.adder(a[i], Circuits.not(b[i]), carryIn);
			res[i] = added[0];
			carryIn = added[1];
		}
	}

}
