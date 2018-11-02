package com.umbertoloria;

import com.umbertoloria.bittings.Bit;
import com.umbertoloria.bittings.Bite;
import com.umbertoloria.integrates.OR;
import com.umbertoloria.utils.ALUUtils;
import com.umbertoloria.utils.Circuits;

class ArithmeticLogicUnit {

	private Bit[] mode;
	private Bit[] a = new Bit[Computer.ARCH];
	private Bit[] b = new Bit[Computer.ARCH];
	private Bit[] result = new Bit[Computer.ARCH];
	// Forse è l'ottavo registro? TODO: Non riferire a zero!
	private Bit[] zero = Bite.toBits("0000000000000000000000000000000000000000000000000000000000000000");
	private OR orGate = new OR(Computer.ARCH - 1);
//	private ADDER[] adder = new ADDER[Computer.ARCH];
//	private AND[] andGate = new AND[2];

	/**
	 * Sets the Arithmetic-Logic Unit Mode.
	 * @param mode will be the new alu-mode
	 */
	void setMode(Bit[] mode) {
		this.mode = mode;
	}

	/**
	 * Sets the First Operand of the Arithmetic-Logic Unit.
	 * @param a will be the new first operand
	 */
	void setA(Bit[] a) {
		this.a = a;
	}

	/**
	 * Sets the Second Operand of the Arithmetic-Logic Unit.
	 * @param b will be the new second operand
	 */
	void setB(Bit[] b) {
		/*if (b.length != Computer.ARCH) {
			throw new RuntimeException();
		}*/
		//Bite.linkLeft(this.b, b);
		this.b = b;
	}

	/**
	 * Gets the output of the Arithmetic-Logic Unit.
	 * @return the result of the alu
	 */
	Bit[] getResult() {
		return result;
	}

	/**
	 * Executes the operator defined by the ALU-Mode.
	 */
	void clock() {
		if (Bite.equals(mode, ALUUtils.AND)) {
			and(a, b, result);
		} else if (Bite.equals(mode, ALUUtils.OR)) {
			or(a, b, result);
		} else if (Bite.equals(mode, ALUUtils.NOT)) {
			not(a, result);
		} else if (Bite.equals(mode, ALUUtils.ADD)) {
			add(a, b, result);
		} else if (Bite.equals(mode, ALUUtils.SUB)) {
			sub(a, b, result);
		}/* else if (Arrays.equals(mode, ALUUtils.GOTV)) {

		} else if (Arrays.equals(mode, ALUUtils.GOTF)) {

		}*/ else if (Bite.equals(mode, ALUUtils.EQU)) {
			sub(a, b, result);
			if (!thereAreAnyTrues(result, 0)) {
				Bite.fillOf(result, result.length - 1, true);
			} else {
				result = zero.clone();
			}
		} else if (Bite.equals(mode, ALUUtils.DIFF)) { // TODO: forse togliere l'istruzione diff o equ?
			sub(a, b, result);
			// tutti i bit messi in or. se esce zero allora erano tutti zero
			if (thereAreAnyTrues(result, 0)) {
				result = zero.clone();
				Bite.fillOf(result, result.length - 1, true);
			} else {
				result = zero.clone();
			}
		} else if (Bite.equals(mode, ALUUtils.LOW)) {
			sub(a, b, result);
			if (result[0].get()) {
				result = zero.clone();
				Bite.fillOf(result, result.length - 1, true);
			} else {
				result = zero.clone();
			}
		} else if (Bite.equals(mode, ALUUtils.LOWEQ)) {
			sub(a, b, result);
			// S.C.E.: thereAreAnyTrues viene chiamata solo se result[0] = false
			if (result[0].get() || !thereAreAnyTrues(result, 1)) {
				result = zero.clone();
				Bite.fillOf(result, result.length - 1, true);
			} else {
				result = zero.clone();
			}
		} else if (Bite.equals(mode, ALUUtils.GRE)) {
			sub(a, b, result);
			if (!result[0].get() && thereAreAnyTrues(result, 1)) {
				result = zero.clone();
				Bite.fillOf(result, result.length - 1, true);
			} else {
				result = zero.clone();
			}
		} else if (Bite.equals(mode, ALUUtils.GREEQ)) {
			sub(a, b, result);
			if (!result[0].get()) {
				result = zero.clone();
				Bite.fillOf(result, result.length - 1, true);
			} else {
				Bite.fillOf(result, result.length - 1, true);
			}
		}/* else if (Arrays.equals(mode, ALUUtils.LOAD)) {

		} else if (Arrays.equals(mode, ALUUtils.STORE)) {

		}*/ else {
			throw new RuntimeException("Istruzione ArithmeticLogicUnit non capita." + mode);
		}
	}

	// FIXME: fare questo meglio!
	private boolean thereAreAnyTrues(Bit[] in, int offset) {
		for (int i = offset; i < in.length; i++) {
			orGate.set(i - offset, in[i]);
		}
		for (int i = in.length - offset; i < orGate.size(); i++) {
			orGate.set(i, new Bit());
		}
		orGate.clock();
		return orGate.get().get();
	}

	private static void and(Bit[] a, Bit[] b, Bit[] res) {
		for (int i = 0; i < a.length; i++) {
			res[i].set(Circuits.and(a[i].get(), b[i].get()));
		}
	}

	private static void or(Bit[] a, Bit[] b, Bit[] res) {
		for (int i = 0; i < a.length; i++) {
			res[i].set(Circuits.or(a[i].get(), b[i].get()));
		}
	}

	private static void not(Bit[] a, Bit[] res) {
		for (int i = 0; i < a.length; i++) {
			res[i].set(Circuits.not(a[i].get()));
		}
	}

	private static void add(Bit[] a, Bit[] b, Bit[] res) {
		boolean carryIn = false;
		for (int i = a.length - 1; i >= 0; i--) {
			boolean[] added = Circuits.adder(a[i].get(), b[i].get(), carryIn);
			res[i] = new Bit(added[0]);
			carryIn = added[1];
		}
	}

	private static void sub(Bit[] a, Bit[] b, Bit[] res) {
		boolean carryIn = true;
		for (int i = a.length - 1; i >= 0; i--) {
			boolean[] added = Circuits.adder(a[i].get(), Circuits.not(b[i].get()), carryIn);
			res[i].set(added[0]);
			carryIn = added[1];
		}
	}

}
