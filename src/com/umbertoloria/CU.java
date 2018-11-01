package com.umbertoloria;

import com.umbertoloria.utils.ALUUtils;
import com.umbertoloria.utils.BitsUtils;
import com.umbertoloria.utils.InstructionUtils;
import com.umbertoloria.utils.RegistersUtils;

// TODO: non più gets!
public class CU {

	private boolean[] instrCode; // TODO: poi fare new boolean[...]
	private boolean[] data; // TODO: poi fare new boolean[...]
	private boolean readFlag1, readFlag2, writeFlag, aluSrc1, aluSrc2;
	// MemToReg: scrivo su writeregister il dato che viene dall'alu o che viene dalla memoria dati?
	private boolean[] op1 = new boolean[Computer.ARCH];
	private boolean[] op2 = new boolean[Computer.ARCH];
	private boolean[] writeRegister = new boolean[RegistersUtils.REGISTERS_SIZE];
	private boolean[] aluMode = new boolean[ALUUtils.ALUMODE_SIZE];
	//private boolean[] writeData = new boolean[Computer.ARCH];

	public void set(boolean[] instr) {
		this.instrCode = BitsUtils.truncate(instr, InstructionUtils.INSTRUCTION_CODE_SIZE);
		this.data = BitsUtils.startFrom(instr, InstructionUtils.INSTRUCTION_CODE_SIZE);
	}

	public void clock() {
		int[] instr_type = InstructionUtils.getSizes(instrCode);
		if (instr_type == null) { // TODO: verificare se è codice morto.
			throw new RuntimeException("Istruzione senza tipo");
		}
		int op1e = instr_type[0];
		BitsUtils.sub(data, 0, op1e, op1);
		int op2e;
		if (instr_type.length == 2) {
			op2e = instr_type[1];
			BitsUtils.sub(data, op1e, op1e + op2e, op2);
		}
		boolean[] instr_flags = InstructionUtils.getFlags(instrCode);
		readFlag1 = instr_flags[0];
		readFlag2 = instr_flags[1];
		writeFlag = instr_flags[2];
		aluSrc1 = instr_flags[3];
		aluSrc2 = instr_flags[4];
		boolean[] tmp = InstructionUtils.getWriteRegister(instrCode);
		if (tmp != null) {
			BitsUtils.set(writeRegister, tmp);
		}
		tmp = InstructionUtils.getAluMode(instrCode);
		if (tmp != null) {
			BitsUtils.set(aluMode, tmp);
		}
	}

	boolean getReadFlag1() {
		return readFlag1;
	}

	boolean getReadFlag2() {
		return readFlag2;
	}

	boolean getWriteFlag() {
		return writeFlag;
	}

	boolean getAluSrc1() {
		return aluSrc1;
	}

	boolean getAluSrc2() {
		return aluSrc2;
	}

	boolean[] getOP1() {
		return op1.clone();
	}

	boolean[] getOP2() {
		return op2.clone();
	}

	boolean[] getWriteRegister() {
		return writeRegister.clone();
	}

	boolean[] getAluMode() {
		return aluMode.clone();
	}

}
