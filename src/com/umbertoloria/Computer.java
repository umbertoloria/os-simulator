package com.umbertoloria;

import com.umbertoloria.utils.InstructionUtils;

class Computer {

	static final int ARCH = 64;
	private boolean[] instr = new boolean[InstructionUtils.MAX_INSTRUCTION_SIZE];
	private Registers regs = new Registers();
	private ALU alu = new ALU();
	private CU cu = new CU();
	private IM im = new IM();
	// TODO: Implement a Control Unit!

	void setInstr(boolean[] instr) {
		this.instr = instr.clone();
	}

	void clock() {

		// Fase Instruction Memory
		im.set(instr);
		im.clock();

		// Fase Control Unit
		cu.set(im.getInstr());
		cu.clock();
		boolean READFLAG1 = cu.getReadFlag1();
		boolean READFLAG2 = cu.getReadFlag2();
		boolean WRITEFLAG = cu.getWriteFlag();
		boolean ALUSRC1 = cu.getAluSrc1();
		boolean ALUSRC2 = cu.getAluSrc2();
		boolean[] OPERATOR1 = cu.getOP1();
		boolean[] OPERATOR2 = cu.getOP2();
		boolean[] WRITEREGISTER = cu.getWriteRegister();
		boolean[] ALUMODE = cu.getAluMode();
		// TODO: MEMOTOREG?

		// Fase 1 Registri
		regs.setReadFlag1(READFLAG1);
		if (READFLAG1) {
			regs.setReadReg1(OPERATOR1);
		}
		regs.setReadFlag1(READFLAG2);
		if (READFLAG2) {
			regs.setReadReg2(OPERATOR2);
		}
		regs.clock();
		boolean[] READDATA1 = regs.getData1();
		boolean[] READDATA2 = regs.getData2();

		// Fase Arithmetic-Logic Unit
		if (ALUSRC1) {
			alu.setA(OPERATOR1);
		} else {
			alu.setA(READDATA1);
		}
		if (ALUSRC2) {
			alu.setB(OPERATOR2);
		} else {
			alu.setB(READDATA2);
		}
		alu.setMode(ALUMODE);
		alu.clock();
		boolean[] ALURESULT = alu.getResult();
		// Memory?

		// Fase 2 Registri
		regs.setWriteFlag(WRITEFLAG);
		regs.setWriteReg(WRITEREGISTER);
		regs.setWriteData(ALURESULT);
		regs.clockBack();
		System.exit(1);



















/*
		if (InstructionUtils.isALUSTDInstructionCode(instrCode)) {
			boolean roc1 = instr[0];
			boolean roc2 = instr[1];
			boolean[] d1, d2;
			instr = BitsUtils.startFrom(instr, 2);
			if (roc1) {
				regs.setReadFlag1(true);
				regs.setReadReg1(BitsUtils.truncate(instr, 3));
				instr = BitsUtils.startFrom(instr, 3);
			} else {
				regs.setReadFlag1(true);
				/*regs.setReadReg1(BitsUtils.truncate(instr, 64));*
				d1 = BitsUtils.truncate(instr, 64);
				instr = BitsUtils.startFrom(instr, 64);
			}
			if (roc2) {
				regs.setReadFlag2(true);
				regs.setReadReg2(BitsUtils.truncate(instr, 3));
				instr = BitsUtils.startFrom(instr, 3);
			} else {
				regs.setReadFlag2(true);
				d2 = BitsUtils.truncate(instr, 64);
				/*regs.setReadReg2(BitsUtils.truncate(instr, 64));*
				// TODO: forse non serve instr = BitsUtils.startFrom(instr, 64);
			}
			regs.setWriteFlag(true);
		}

		regs.clock();

		if (cu.aluSrc1()) {
			alu.setA(regs.getData1());
		} else {

		}

		alu.setA(regs.getData1());
		alu.setB(regs.getData2());
		// TODO: uscire l'ALU mode
		alu.setMode(ALUUtils.getALUMode(instrCode));
		alu.clock();
		// FIXME: configurare bene il registro di salvataggio
		regs.setWriteReg(RegistersUtils.getRegisterCode("AR"));
		regs.setWriteData(alu.getOUT());
		regs.clockBack();
*/
	}

}
