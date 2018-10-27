package com.umbertoloria.alu;

import com.umbertoloria.utils.BinaryUtils;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class ALU {

	private Queue<Boolean> coda = new LinkedBlockingQueue<>();
	private int arch;
	private int instr_length;
	private volatile boolean processing = false;
	private boolean[] ar;
	private boolean[] lr;

	public ALU(int arch) {
		this.arch = arch;
		this.instr_length = 2 + 2 * arch;
		ar = new boolean[arch];
		lr = new boolean[arch];
	}

	/**
	 Interprets an ARCH-sized instruction taken from the top of the Instruction Queue thanks to parseInstruction.
	 */
	void clock() {
		if (coda.size() >= instr_length) {
			boolean[] bits = new boolean[instr_length];
			for (int i = 0; i < instr_length; i++) {
				bits[i] = coda.remove();
			}
			processing = true;
			parseInstruction(bits);
			processing = false;
		}
	}

	/**
	 Interprets an ARCH-sized instruction.
	 @param bits composes the instruction
	 */
	private void parseInstruction(boolean[] bits) {
		String instr = BinaryUtils.toStr(bits);
		boolean[] first = BinaryUtils.toRawBools(instr.substring(2, arch + 2));
		boolean[] second = BinaryUtils.toRawBools(instr.substring(2 + arch, instr_length));
		if (instr.startsWith("00")) {
			BinaryUtils.and(first, second, lr);
		} else if (instr.startsWith("01")) {
			BinaryUtils.or(first, second, lr);
		} else if (instr.startsWith("10")) {
			BinaryUtils.add(first, second, ar);
		}
	}

	/**
	 Puts a bit on the Instruction Queue.
	 @param bit to put
	 */
	void push(boolean bit) {
		coda.add(bit);
	}

	/**
	 Gets the value of the Arithmetic Register.
	 @return bits contained in the AR
	 */
	boolean[] getAR() {
		while (processing) {
			Thread.onSpinWait();
		}
		return ar.clone();
	}

	/**
	 Gets the value of the Logic Register.
	 @return bits contained in the LR
	 */
	boolean[] getLR() {
		while (processing) {
			Thread.onSpinWait();
		}
		return lr.clone();
	}

	/**
	 Gets the ARCH of the ALU.
	 @return arch
	 */
	int getArch() {
		return arch;
	}

}
