package com.umbertoloria.components;

import com.umbertoloria.bitting.Bit;
import com.umbertoloria.bitting.BitAlloc;
import com.umbertoloria.bitting.BitLink;
import com.umbertoloria.bitting.BitUse;
import com.umbertoloria.graphics.Renderer;
import com.umbertoloria.interfaces.Clockable;
import com.umbertoloria.utils.ALUUtils;
import com.umbertoloria.utils.ICB;

import java.awt.*;

public class ControlUnit implements Clockable {

	private Bit[] instr;
	private Bit readFlag1, readFlag2, writeFlag, aluSrc1, aluSrc2, jumpFlag;
	//private Bit memToReg = new Bit();
	private Bit[] op1, op2;
	private Bit[] writeRegister;
	private Bit[] aluMode;
	private Bit[] nextInstr;

	ControlUnit() {
		readFlag1 = new Bit();
		readFlag2 = new Bit();
		writeFlag = new Bit();
		aluSrc1 = new Bit();
		aluSrc2 = new Bit();
		jumpFlag = new Bit();
		op1 = BitAlloc.create(64, false);
		op2 = BitAlloc.create(64, false);
		writeRegister = BitAlloc.create(3, false);
		aluMode = BitAlloc.create(4, false);
		nextInstr = BitAlloc.create(64, false);
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
		ICB iib = ICB.getICB(BitUse.toBools(BitAlloc.truncate(instr, 6)));

		// Scarico gli operandi dall'istruzione
		int operandsCount = iib.getOperandsCount();
		if (operandsCount == 2) {
			BitUse.sub(op1, instr, 6, 6 + 64);
			BitUse.sub(op2, instr, 6 + 64, 6 + 64 + 64);
		} else if (operandsCount == 1) {
			BitUse.sub(op1, instr, 6, 6 + 64);
		} else if (operandsCount == 0) {
			// Non ho ancora implementato un'istruzione senza operandi...
			System.err.println("Non ho ancora implementato un'istruzione senza operandi...");
		} else {
			throw new RuntimeException("Qualcosa è sfuggito");
		}

		// Configurazione dei flags
		boolean[] instr_flags = iib.getFlags();
		readFlag1.set(instr_flags[0]);
		readFlag2.set(instr_flags[1]);
		writeFlag.set(instr_flags[2]);
		aluSrc1.set(instr_flags[3]);
		aluSrc2.set(instr_flags[4]);
		jumpFlag.set(instr_flags[5]);

		// Registri di scrittura (opzionale)
		boolean[] tmpWriteRegister = iib.getRegister();
		if (tmpWriteRegister != null) {
			BitUse.set(writeRegister, tmpWriteRegister);
		} else {
			// non so dove scrivere, forse per un istruzione "set"
			// prendo il registro che sta scritto nel primo operando dell'istruzione
			// TODO: sistemare questa cosa
			Bit[] app = new Bit[3];
			BitLink.linkSub(app, op1, 61, 64);
			BitUse.set(writeRegister, app);
		}

		// AluMode (opzionale)
		boolean[] tmpAluMode = iib.getAluMode();
		if (tmpAluMode != null) {
			BitUse.set(aluMode, tmpAluMode);
		} else {
			// imposto la ALU mode ad ADD (questa cosa la uso solo per "set", per ora...)
			// edit: adesso anche per il GOTO
			BitUse.set(aluMode, ALUUtils.ADD);
		}

		// Link nuova istruzione (solo per GOT*): codice temporaneo
		if (iib.getAluMode() == ALUUtils.GOTO) {
			BitLink.link(nextInstr, op1);
		} else if (iib.getAluMode() == ALUUtils.GOTV || iib.getAluMode() == ALUUtils.GOTF) {
			BitLink.link(nextInstr, op2);
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

	Bit getJUMPFLAG() {
		return jumpFlag;
	}

	Bit[] getNEXTINSTR() {
		return nextInstr;
	}

	void draw(Renderer r, boolean lastClocked) {
		if (lastClocked) {
			r.box(0, 0, 652, 92, Color.darkGray, true);
		}
		r.box(0, 0, 652, 92, Color.green, false);
		int x = Renderer.bs;
		int oy = 10 - x;
		r.write("Instruction", 10, (oy += x));
		r.drawInstr(instr, 130, oy);

		r.write("Operator 1", 10, (oy += 2 * x));
		r.drawBits(op1, 130, oy);
		r.write("Operator 2", 10, (oy += 2 * x));
		r.drawBits(op2, 130, oy);

		r.write("Read Flag 1", 10, (oy += 2 * x));
		r.drawBit(readFlag1, 90, oy);
		r.write("Read Flag 2", 120, oy);
		r.drawBit(readFlag2, 200, oy);
		r.write("Write Register", 230, oy);
		r.drawBits(writeRegister, 320, oy);
		r.write("Write Flag", 390, oy);
		r.drawBit(writeFlag, 460, oy);

		r.write("ALU Src 1", 10, (oy += 2 * x));
		r.drawBit(aluSrc1, 90, oy);
		r.write("ALU Src 2", 120, oy);
		r.drawBit(aluSrc2, 200, oy);
		r.write("ALU Mode", 230, oy);
		r.drawBits(aluMode, 320, oy);
	}
}
