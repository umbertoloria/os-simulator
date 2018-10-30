package com.umbertoloria;

import com.umbertoloria.utils.BitsUtils;
import com.umbertoloria.utils.InstructionUtils;
import com.umbertoloria.utils.RegistersUtils;

public class Computer {

	public static final int ARCH = 64;
	private boolean[] instr = new boolean[InstructionUtils.MAX_INSTRUCTION_SIZE];
	private Registers regs = new Registers();
	private ALU alu = new ALU();
	private Controls controls = new Controls();

	public void setInstr(boolean[] instr) {
		this.instr = instr.clone();
	}

	public void clock() {
		boolean[] instrCode = BitsUtils.truncate(instr, 5);
		instr = BitsUtils.startAt(instr, 5);

		if (InstructionUtils.isALUInstruction(instrCode)) {
			boolean roc1 = instr[0];
			boolean roc2 = instr[1];
			instr = BitsUtils.startAt(instr, 2);
			regs.setReadFlag1(roc1);
			regs.setReadFlag2(roc2);
			if (roc1) {
				regs.setReadReg1(BitsUtils.truncate(instr, 3));
				instr = BitsUtils.startAt(instr, 3);
			} else {
				regs.setReadReg1(BitsUtils.truncate(instr, 64));
				instr = BitsUtils.startAt(instr, 64);
			}
			if (roc2) {
				regs.setReadReg2(BitsUtils.truncate(instr, 3));
				instr = BitsUtils.startAt(instr, 3);
			} else {
				regs.setReadReg2(BitsUtils.truncate(instr, 64));
				instr = BitsUtils.startAt(instr, 64);
			}
			regs.setWriteFlag(true);
		}

		regs.clock();

		alu.setA(regs.getData1());
		alu.setB(regs.getData2());
		// TODO: uscire l'ALU mode
		alu.setMode(ALU.ADD);
		alu.clock();
		// FIXME: configurare bene il registro di salvataggio
		regs.setWriteReg(RegistersUtils.getRegisterCode("AR"));
		regs.setWriteData(alu.getOUT());
		regs.clockBack();
		BitsUtils.print(alu.getOUT());

	}

}
