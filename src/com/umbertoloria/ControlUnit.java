package com.umbertoloria;

import com.umbertoloria.bittings.Bit;
import com.umbertoloria.bittings.Bite;
import com.umbertoloria.utils.ICB;
import com.umbertoloria.utils.InstructionUtils;

public class ControlUnit {

	private Bit[] instr;
	private Bit readFlag1 = new Bit();
	private Bit readFlag2 = new Bit();
	private Bit writeFlag = new Bit();
	private Bit aluSrc1 = new Bit();
	private Bit aluSrc2 = new Bit();
	//private Bit memToReg = new Bit();
	private Bit[] op1 = new Bit[64];
	private Bit[] op2 = new Bit[64];
	private Bit[] writeRegister = new Bit[3];
	private Bit[] aluMode = new Bit[4];

	/**
	 * Sets the Instruction to execute.
	 * @param instr is the instruction
	 */
	public void set(Bit[] instr) {
		this.instr = instr;
	}

	/**
	 * Prepares all the flags and data that will pass through the Processor.
	 */
	public void clock() {

		ICB iib = InstructionUtils.getICB(Bite.toBools(Bite.truncate(instr, InstructionUtils.INSTR_CODE_SIZE)));

		int operandsCount = iib.getOperandsCount();

		if (operandsCount == 2) {
			Bite.linkSub(op1, instr, 6, 6 + 64);
			Bite.linkSub(op2, instr, 6 + 64, 6 + 64 + 64);
		} else if (operandsCount == 1) {
			Bite.linkSub(op1, instr, 6, 6 + 64);
		} else if (operandsCount == 0) {

		} else {
			throw new RuntimeException("Qualcosa è sfuggita.");
		}

		boolean[] instr_flags = iib.getFlags();
		readFlag1.set(instr_flags[0]);
		readFlag2.set(instr_flags[1]);
		writeFlag.set(instr_flags[2]);
		aluSrc1.set(instr_flags[3]);
		aluSrc2.set(instr_flags[4]);

		boolean[] tmpWriteRegister = iib.getRegister();
		if (tmpWriteRegister != null) {
			Bite.initSet(writeRegister, tmpWriteRegister);
		}
		boolean[] tmpAluMode = iib.getAluMode();
		if (tmpAluMode != null) {
			Bite.initSet(aluMode, tmpAluMode);
		}
	}

	/**
	 * Gets the First Operator that transports Registers, Constants, Memory or Instruction Addresses.
	 * @return the first operator
	 */
	Bit[] getOP1() {
		return op1;
	}

	/**
	 * Gets the Second Operator that transports Registers, Constants, Memory or Instruction Addresses.
	 * @return the second operator
	 */
	Bit[] getOP2() {
		return op2;
	}

	/**
	 * Gets the First Read Flag value that will be passed to the Register.
	 * @return the first read flag
	 */
	Bit getReadFlag1() {
		return readFlag1;
	}

	/**
	 * Gets the Second Read Flag value that will be passed to the Register.
	 * @return the second read flag
	 */
	Bit getReadFlag2() {
		return readFlag2;
	}

	/**
	 * Gets the Write Flag value that will be passed to the Register.
	 * @return the write flag
	 */
	Bit getWriteFlag() {
		return writeFlag;
	}

	/**
	 * Gets the Write Register Address that will be passed to the Register.
	 * @return the write register address
	 */
	Bit[] getWriteRegister() {
		return writeRegister;
	}

	/**
	 * Gets the ALU-SRC Selector Bit of the First ALU-SRC MUX.
	 * @return the first alu-src bit
	 */
	Bit getALUSRC1() {
		return aluSrc1;
	}

	/**
	 * Gets the ALU-SRC Selector Bit of the Second ALU-SRC MUX.
	 * @return the second alu-src bit
	 */
	Bit getALUSRC2() {
		return aluSrc2;
	}

	/**
	 * Gets the ALU-MODE that will be passed to the ALU.
	 * @return the alu-mode
	 */
	Bit[] getALUMODE() {
		return aluMode;
	}

}
