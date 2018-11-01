package com.umbertoloria;

import com.umbertoloria.bittings.Bit;
import com.umbertoloria.bittings.BitStream;
import com.umbertoloria.utils.InstructionUtils;

// TODO: non più gets!
public class CU {

	private BitStream instruction = new BitStream(InstructionUtils.INSTRUCTION_CODE_SIZE);
	private Bit readFlag1, readFlag2, writeFlag, aluSrc1, aluSrc2;
	// MemToReg: scrivo su writeregister il dato che viene dall'alu o che viene dalla memoria dati?
	private BitStream op1, op2;
	private BitStream writeRegister;// = new boolean[RegistersUtils.REGISTERS_SIZE];
	private BitStream aluMode;// = new boolean[ALUUtils.ALUMODE_SIZE];
	//private boolean[] writeData = new boolean[Computer.ARCH];

	public void set(BitStream instruction) {
		this.instruction = instruction;
		/*this.instrCode = BitsUtils.truncate(instr, InstructionUtils.INSTRUCTION_CODE_SIZE);
		this.data = BitsUtils.startFrom(instr, InstructionUtils.INSTRUCTION_CODE_SIZE);*/
	}

	public void clock() {

		InstructionUtils.IIB iib =
				InstructionUtils.getAllData(instruction.truncate(InstructionUtils.INSTRUCTION_CODE_SIZE));

		int[] instr_type = iib.getOperandsDimensions(); // InstructionUtils.getOperandsDimensions(instrCode);
		if (instr_type == null) { // TODO: verificare se è codice morto.
			throw new RuntimeException("Istruzione senza tipo");
		}
		int op1e = instr_type[0];
		op1 = instruction.sub(6, 6 + op1e);
		int op2e;
		if (instr_type.length == 2) {
			op2e = instr_type[1];
			op2 = instruction.sub(6 + op1e, 6 + op1e + op2e);
		}
		Bit[] instr_flags = iib.getFlags(); // InstructionUtils.getFlags(instrCode);
		readFlag1 = instr_flags[0];
		readFlag2 = instr_flags[1];
		writeFlag = instr_flags[2];
		aluSrc1 = instr_flags[3];
		aluSrc2 = instr_flags[4];
		BitStream tmpWriteRegister = iib.getRegister(); // InstructionUtils.getWriteRegister(instrCode);
		if (tmpWriteRegister != null) {
			//BitsUtils.set(writeRegister, tmp1);
			writeRegister = tmpWriteRegister;
		}
		BitStream tmpAluMode = iib.getAluMode(); // InstructionUtils.getAluMode(instrCode);
		if (tmpAluMode != null) {
			//BitsUtils.set(aluMode, tmp2);
			aluMode = tmpAluMode;
		}
	}

	Bit getReadFlag1() {
		return readFlag1;
	}

	Bit getReadFlag2() {
		return readFlag2;
	}

	Bit getWriteFlag() {
		return writeFlag;
	}

	Bit getAluSrc1() {
		return aluSrc1;
	}

	Bit getAluSrc2() {
		return aluSrc2;
	}

	BitStream getOP1() {
		return op1;//.clone();
	}

	BitStream getOP2() {
		return op2;//.clone();
	}

	BitStream getWriteRegister() {
		return writeRegister;//.clone();
	}

	BitStream getAluMode() {
		return aluMode;//.clone();
	}

}
