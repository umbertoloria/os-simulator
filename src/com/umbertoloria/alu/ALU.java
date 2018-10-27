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

	// Operations

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

	void push(boolean bit) {
		coda.add(bit);
	}

	// Getters

	int getArch() {
		return arch;
	}

	boolean[] getAR() {
		while (processing) {
			Thread.onSpinWait();
		}
		// FIXME: return r1.clone();
		return ar;
	}

	boolean[] getLR() {
		while (processing) {
			Thread.onSpinWait();
		}
		// FIXME: return r1.clone();
		return lr;
	}
}
