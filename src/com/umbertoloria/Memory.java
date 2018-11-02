package com.umbertoloria;

import com.umbertoloria.bittings.Bit;
import com.umbertoloria.bittings.Bite;
import com.umbertoloria.utils.BinUtils;
import com.umbertoloria.utils.InstructionUtils;

public class Memory {

	private Bit[][] ram = new Bit[100][64];

	private Bit[] addr;
	private Bit[] out = new Bit[InstructionUtils.MAX_INSTRUCTION_SIZE];

	public void set(Bit[] addr) {
		this.addr = addr;
	}

	void clock() {
		// Un giorno preleverò davvero dalla memoria istruzioni... :(
		int index = BinUtils.toAbsInt(Bite.toBools(addr));
		System.arraycopy(ram[index], 0, out, 0, 64);
		System.arraycopy(ram[index + 1], 0, out, 64, 64);
		System.arraycopy(ram[index + 2], 0, out, 128, 64);
	}

	/**
	 * Scrittura variabili da registri o costanti...
	 */
	void clockBack() {
	}

	Bit[] get() {
		return out;
	}

	void addInstr(Bit[] instr, int numInstr) {
		Bit[] instrA = new Bit[64];
		Bit[] instrB = new Bit[64];
		Bit[] instrC = new Bit[64];
		Bite.linkSub(instrA, instr, 0, 64);
		Bite.linkSub(instrB, instr, 64, 64 * 2);
		Bite.linkSub(instrC, instr, 64 * 2, 64 * 3);
		Bite.linkLeft(ram[numInstr], instrA);
		Bite.linkLeft(ram[numInstr + 1], instrB);
		Bite.linkLeft(ram[numInstr + 2], instrC);
	}

}
