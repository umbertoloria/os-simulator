package com.umbertoloria.mips_project.cpu;

import com.umbertoloria.archs.Binary;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class CPU/* extends Daemon*/ {

	private Queue<Boolean> coda = new LinkedBlockingQueue<>();
	private int arch;
	private int instr_length;
	private boolean processing = false;
	private int overflows = 0;
	private int ignored_instructions = 0;
	private boolean[] ar;
	private boolean[] lr;

	public CPU(int arch) {
		//super("CPU");
		this.arch = arch;
		this.instr_length = 2 + 2 * arch;
		ar = new boolean[arch];
		lr = new boolean[arch];
		//start();
	}

	// Operations

	public void clock() {
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
		String instr = Binary.toStr(bits);

		boolean[] first = Binary.toBools(instr.substring(2, arch + 2));
		boolean[] second = Binary.toBools(instr.substring(2 + arch, instr_length));

		if (instr.startsWith("00")) {
			if (!Binary.and(first, second, lr)) {
				overflows++;
			}
		} else if (instr.startsWith("01")) {
			if (!Binary.or(first, second, lr)) {
				overflows++;
			}
		} else if (instr.startsWith("10")) {
			if (!Binary.add(first, second, ar)) {
				overflows++;
			}
		} else {
			ignored_instructions++;
		}
	}

	public void push(boolean bit) {
		coda.add(bit);
	}

	// Getters

	public int getArch() {
		return arch;
	}

	public boolean isProcessing() {
		return processing;
	}

	public int getOverflows() {
		return overflows;
	}

	public int getIgnoredInstructions() {
		return ignored_instructions;
	}

	public boolean[] getAR() {
		while (processing) ;
		// TODO: return r1.clone();
		return ar;
	}

	public boolean[] getLR() {
		while (processing) ;
		// TODO: return r1.clone();
		return lr;
	}
}
