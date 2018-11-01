package com.umbertoloria;

import com.umbertoloria.bittings.Bit;
import com.umbertoloria.bittings.BitStream;
import com.umbertoloria.integrates.OR;
import com.umbertoloria.utils.ALUUtils;
import com.umbertoloria.utils.Circuits;

public class ALU {

	private BitStream mode;
	private BitStream a, b;
	private BitStream result = new BitStream(Computer.ARCH);// = new boolean[Computer.ARCH];
	private BitStream zero;// = new boolean[Computer.ARCH];
	// Non riferire a zero!
	private OR orGate = new OR(Computer.ARCH);
	// TODO: maybe the eigth register is zero?

	/**
	 Sets the ALU Mode.
	 @param mode will be the new ALU Mode
	 */
	void setMode(BitStream mode) {
		if (ALUUtils.isAluMode(mode)) {
			//System.arraycopy(mode, 0, this.mode, 0, 4);
			this.mode = mode;
		} else {
			throw new RuntimeException("ALU mode non valida.");
		}
	}

	/**
	 Sets the first operand of the ALU.
	 @param a will be set on the first operand
	 */
	public void setA(BitStream a) {
		if (a.size() != Computer.ARCH) {
			throw new RuntimeException();
		}
		//BitsUtils.set(this.a, a);
		this.a = a;
	}

	/**
	 Sets the second operand of the ALU.
	 @param b will be set on the second operand
	 */
	void setB(BitStream b) {
		if (b.size() != Computer.ARCH) {
			throw new RuntimeException();
		}
		//BitsUtils.set(this.b, b);
		this.b = b;
	}

	/**
	 Gets the last output produced by the ALU after a clock call.
	 @return the result
	 @see ALU
	 */
	BitStream getResult() {
		return result;
	}

	/**
	 Executes the older of all the instructions on the queue.
	 */
	void clock() {
		if (mode.equals(ALUUtils.AND)) { // <- e mo?
			and(a, b, result);
		} else if (mode.equals(ALUUtils.OR)) {
			or(a, b, result);
		} else if (mode.equals(ALUUtils.NOT)) {
			not(a, result);
		} else if (mode.equals(ALUUtils.ADD)) {
			add(a, b, result);
		} else if (mode.equals(ALUUtils.SUB)) {
			sub(a, b, result);
		}/* else if (Arrays.equals(mode, ALUUtils.GOTV)) {

		} else if (Arrays.equals(mode, ALUUtils.GOTF)) {

		}*/ else if (mode.equals(ALUUtils.EQU)) {
			sub(a, b, result);
			if (!thereAreAnyTrues(result, 0)) {
				//BitsUtils.setEnd(result, true);
				result.set(result.size() - 1, true);
			} else {
				//BitsUtils.set(result, zero);
				result = zero.clone();
			}
		} else if (mode.equals(ALUUtils.DIFF)) { // TODO: forse togliere l'istruzione diff o equ?
			sub(a, b, result);
			// tutti i bit messi in or. se esce zero allora erano tutti zero
			if (thereAreAnyTrues(result, 0)) {
				//BitsUtils.set(result, zero);
				result = zero.clone();
				//BitsUtils.setEnd(result, true);
				result.set(result.size() - 1, true);
			} else {
				//BitsUtils.set(result, zero);
				result = zero.clone();
			}
		} else if (mode.equals(ALUUtils.LOW)) {
			sub(a, b, result);
			if (result.get(0).get()) {
				//BitsUtils.set(result, zero);
				result = zero.clone();
				//BitsUtils.setEnd(result, true);
				result.set(result.size() - 1, true);
			} else {
				//BitsUtils.set(result, zero);
				result = zero.clone();
			}
		} else if (mode.equals(ALUUtils.LOWEQ)) {
			sub(a, b, result);
			// Short-Circuit Evaluation: thereAreAnyTrues viene chiamata solo se result[0] = false
			if (result.get(0).get() || !thereAreAnyTrues(result, 1)) {
				//BitsUtils.set(result, zero);
				result = zero.clone();
				//BitsUtils.setEnd(result, true);
				result.set(result.size() - 1, true);
			} else {
				//BitsUtils.set(result, zero);
				result = zero.clone();
			}
		} else if (mode.equals(ALUUtils.GRE)) {
			sub(a, b, result);
			if (!result.get(0).get() && thereAreAnyTrues(result, 1)) {
				//BitsUtils.set(result, zero);
				result = zero.clone();
				//BitsUtils.setEnd(result, true);
				result.set(result.size() - 1, true);
			} else {
				//BitsUtils.set(result, zero);
				result = zero.clone();
			}
		} else if (mode.equals(ALUUtils.GREEQ)) {
			sub(a, b, result);
			if (!result.get(0).get()) {
				//BitsUtils.set(result, zero);
				result = zero.clone();
				//BitsUtils.setEnd(result, true);
				result.set(result.size() - 1, true);
			} else {
				// BitsUtils.set(result, zero);
				result.set(result.size() - 1, true);
			}
		}/* else if (Arrays.equals(mode, ALUUtils.LOAD)) {

		} else if (Arrays.equals(mode, ALUUtils.STORE)) {

		}*/ else {
			throw new RuntimeException("Istruzione ALU non capita.");
		}
	}

	// FIXME: fare questo meglio!
	private boolean thereAreAnyTrues(BitStream in, int offset) {
		for (int i = offset; i < in.size(); i++) {
			orGate.set(i - offset, in.get(i));
		}
		for (int i = in.size() - offset; i < orGate.size(); i++) {
			orGate.set(i, new Bit(false));
		}
		orGate.clock();
		return orGate.get().get();
	}

	private static void and(BitStream a, BitStream b, BitStream res) {
		for (int i = 0; i < a.size(); i++) {
			res.set(i, Circuits.and(a.get(i).get(), b.get(i).get()));
		}
	}

	private static void or(BitStream a, BitStream b, BitStream res) {
		for (int i = 0; i < a.size(); i++) {
			res.set(i, Circuits.or(a.get(i).get(), b.get(i).get()));
		}
	}

	private static void not(BitStream a, BitStream res) {
		for (int i = 0; i < a.size(); i++) {
			res.set(i, Circuits.not(a.get(i).get()));
		}
	}

	private static void add(BitStream a, BitStream b, BitStream res) {
		boolean carryIn = false;
		for (int i = a.size() - 1; i >= 0; i--) {
			boolean[] added = Circuits.adder(a.get(i).get(), b.get(i).get(), carryIn);
			res.set(i, added[0]);
			carryIn = added[1];
		}
	}

	private static void sub(BitStream a, BitStream b, BitStream res) {
		boolean carryIn = true;
		for (int i = a.size() - 1; i >= 0; i--) {
			boolean[] added = Circuits.adder(a.get(i).get(), Circuits.not(b.get(i).get()), carryIn);
			res.set(i, added[0]);
			carryIn = added[1];
		}
	}

}
