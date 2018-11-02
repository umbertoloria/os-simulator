package com.umbertoloria;

import com.umbertoloria.bittings.Bit;
import com.umbertoloria.bittings.Bite;
import com.umbertoloria.utils.BinUtils;

public class ProgramCounter {

	private Bit startExecution = new Bit(true);
	private Bit[] addr = Bite.toBits("0000000000000000000000000000000000000000000000000000000000000000");

	private Bit[] out = addr;

	/**
	 * Gets the Instruction Address to execute.
	 * @return the instruction address
	 */
	public Bit[] get() {
		return out;
	}

	/**
	 * After the execution of the insturction, it takes the next Instruction Address.
	 */
	public void clock() {
		if (startExecution.get()) {
			startExecution.disable();
		} else {
			int index = BinUtils.toAbsInt(Bite.toBools(addr));
			Bite.set(addr, BinUtils.convertAbs(index + 3, 64));
		}
	}

}
