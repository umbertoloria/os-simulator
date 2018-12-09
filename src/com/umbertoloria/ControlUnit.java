package com.umbertoloria;

import com.umbertoloria.bittings.Bit;
import com.umbertoloria.bittings.Bite;
import com.umbertoloria.interfaces.Clockable;
import com.umbertoloria.utils.ICB;
import com.umbertoloria.utils.InstructionUtils;

import java.awt.*;

public class ControlUnit implements Clockable {

	private Bit[] instr;
	private Bit readFlag1, readFlag2, writeFlag, aluSrc1, aluSrc2;
	//private Bit memToReg = new Bit();
	private Bit[] op1 = new Bit[64];
	private Bit[] op2 = new Bit[64];
	private Bit[] writeRegister;
	private Bit[] aluMode;

	ControlUnit() {
		Bit.WATCH("Control Unit");
		readFlag1 = new Bit();
		readFlag2 = new Bit();
		writeFlag = new Bit();
		aluSrc1 = new Bit();
		aluSrc2 = new Bit();
		writeRegister = Bite.initSet(3, false);
		aluMode = Bite.initSet(4, false);
		Bit.eWATCH();
	}

	/**
	 Sets the Instruction to execute.
	 @param instr is the instruction
	 */
	public void set(Bit[] instr) {
		this.instr = instr;
	}

	/**
	 Prepares all the flags and data that will pass through the Processor.
	 */
	public void clock() {

		// Prelevo informazioni dai bit iniziali dell'istruzione
		ICB iib = InstructionUtils.getICB(Bite.toBools(Bite.truncate(instr, InstructionUtils.INSTR_CODE_SIZE)));

		// Scarico gli operandi dall'istruzione
		int operandsCount = iib.getOperandsCount();
		if (operandsCount == 2) {
			Bite.linkSub(op1, instr, 6, 6 + 64);
			Bite.linkSub(op2, instr, 6 + 64, 6 + 64 + 64);
		} else if (operandsCount == 1) {
			Bite.linkSub(op1, instr, 6, 6 + 64);
		} else if (operandsCount == 0) {
			// Non ho ancora implementato un'istruzione senza operandi...
		} else {
			throw new RuntimeException("Qualcosa è sfuggita.");
		}

		// Configurazione dei flags
		boolean[] instr_flags = iib.getFlags();
		readFlag1.set(instr_flags[0]);
		readFlag2.set(instr_flags[1]);
		writeFlag.set(instr_flags[2]);
		aluSrc1.set(instr_flags[3]);
		aluSrc2.set(instr_flags[4]);

		// Registri di scrittura (opzionale)
		boolean[] tmpWriteRegister = iib.getRegister();
		if (tmpWriteRegister != null) {
			Bite.set(writeRegister, tmpWriteRegister);
		}

		// AluMode (opzionale)
		boolean[] tmpAluMode = iib.getAluMode();
		if (tmpAluMode != null) {
			Bite.set(aluMode, tmpAluMode);
		}
	}

	/**
	 Gets the First Operator that transports Registers, Constants, Memory or Instruction Addresses.
	 @return the first operator
	 */
	Bit[] getOP1() {
		return op1;
	}

	/**
	 Gets the Second Operator that transports Registers, Constants, Memory or Instruction Addresses.
	 @return the second operator
	 */
	Bit[] getOP2() {
		return op2;
	}

	/**
	 Gets the First Read Flag value that will be passed to the Register.
	 @return the first read flag
	 */
	Bit getReadFlag1() {
		return readFlag1;
	}

	/**
	 Gets the Second Read Flag value that will be passed to the Register.
	 @return the second read flag
	 */
	Bit getReadFlag2() {
		return readFlag2;
	}

	/**
	 Gets the Write Flag value that will be passed to the Register.
	 @return the write flag
	 */
	Bit getWriteFlag() {
		return writeFlag;
	}

	/**
	 Gets the Write Register Address that will be passed to the Register.
	 @return the write register address
	 */
	Bit[] getWriteRegister() {
		return writeRegister;
	}

	/**
	 Gets the ALU-SRC Selector Bit of the First ALU-SRC MUX.
	 @return the first alu-src bit
	 */
	Bit getALUSRC1() {
		return aluSrc1;
	}

	/**
	 Gets the ALU-SRC Selector Bit of the Second ALU-SRC MUX.
	 @return the second alu-src bit
	 */
	Bit getALUSRC2() {
		return aluSrc2;
	}

	/**
	 Gets the ALU-MODE that will be passed to the ALU.
	 @return the alu-mode
	 */
	Bit[] getALUMODE() {
		return aluMode;
	}

	void draw(Renderer r, boolean lastClocked) {
		if (lastClocked) {
			r.box(0, 0, 780, 130, Color.darkGray, true);
		}
		r.box(0, 0, 780, 130, Color.green, false);
		r.write("Control Unit", 10, 10, Color.gray);
		r.write("Instruction", 10, 30, Color.gray);
		r.drawInstr(instr, 130, 30);

		r.write("Operator 1", 10, 50, Color.gray);
		r.drawBits(op1, 130, 50);
		r.write("Operator 2", 10, 70, Color.gray);
		r.drawBits(op2, 130, 70);

		r.write("Read Flag 1", 10, 90, Color.gray);
		r.drawBit(readFlag1, 90, 90);
		r.write("Read Flag 2", 120, 90, Color.gray);
		r.drawBit(readFlag2, 200, 90);
		r.write("Write Register", 230, 90, Color.gray);
		r.drawBits(writeRegister, 320, 90);
		r.write("Write Flag", 390, 90, Color.gray);
		r.drawBit(writeFlag, 460, 90);

		r.write("ALU Src 1", 10, 110, Color.gray);
		r.drawBit(aluSrc1, 90, 110);
		r.write("ALU Src 2", 120, 110, Color.gray);
		r.drawBit(aluSrc2, 200, 110);
		r.write("ALU Mode", 230, 110, Color.gray);
		r.drawBits(aluMode, 320, 110);

		/*for (int i = 0; i < ram.length; i++) {
			r.write("Box #" + (i + 1), 10, 30 + i * 20, Color.gray);
			r.drawBits(ram[i], 130, 30 + i * 20);
		}
		r.write("In", 10, 30 + ram.length * 20 + 10, Color.gray);
		r.drawBits(in, 130, 30 + ram.length * 20 + 10);
		r.write("Out", 10, 30 + ram.length * 20 + 40, Color.gray);
		r.drawBits(out, 130, 30 + ram.length * 20 + 40);*/
	}
}
