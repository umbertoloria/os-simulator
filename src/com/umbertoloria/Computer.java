package com.umbertoloria;

import com.umbertoloria.bittings.Bit;
import com.umbertoloria.bittings.BitStream;
import com.umbertoloria.integrates.MUX;
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
		this.instr = instr;
	}

	void clock() {

		// Fase Instruction Memory
		im.set(instr);
		im.clock();

		// Fase Control Unit
		cu.set(im.get());
		cu.clock();
		Bit READFLAG1 = cu.getReadFlag1();
		Bit READFLAG2 = cu.getReadFlag2();
		Bit WRITEFLAG = cu.getWriteFlag();
		Bit ALUSRC1 = cu.getAluSrc1();
		Bit ALUSRC2 = cu.getAluSrc2();
		BitStream OPERATOR1 = cu.getOP1();
		BitStream OPERATOR2 = cu.getOP2();
		BitStream WRITEREGISTER = cu.getWriteRegister();
		BitStream ALUMODE = cu.getAluMode();
		// TODO: MEMTOREG?

		// Fase Lettura Registri
		regs.setReadFlag1(READFLAG1);
		if (READFLAG1.get()) { // TODO: togliere
			regs.setReadReg1(OPERATOR1);
		}

		regs.setReadFlag2(READFLAG2);
		if (READFLAG2.get()) { // TODO: togliere
			regs.setReadReg2(OPERATOR2);
		}
		regs.clock();
		BitStream READDATA1 = regs.getData1();
		BitStream READDATA2 = regs.getData2();

		// Fase Arithmetic-Logic Unit


		MUX alusrc1mux = new MUX(ALUSRC1);
		alusrc1mux.set(0, READDATA1);
		alusrc1mux.set(1, OPERATOR1);
		alusrc1mux.clock();
		alu.setA(alusrc1mux.get());
		/*if (ALUSRC1.get()) {
			alu.setA(OPERATOR1);
		} else {
			alu.setA(READDATA1);
		}*/
		MUX alusrc2mux = new MUX(ALUSRC2);
		alusrc2mux.set(1, OPERATOR2);
		alusrc2mux.set(0, READDATA2);
		alusrc2mux.clock();
		alu.setB(alusrc2mux.get());
		/*if (ALUSRC2.get()) {
			alu.setB(OPERATOR2);
		} else {
			alu.setB(READDATA2);
		}*/
		alu.setMode(ALUMODE);
		alu.clock();
		BitStream ALURESULT = alu.getResult();
		// Memory?

		// Fase Scrittura Registri
		regs.setWriteFlag(WRITEFLAG);
		regs.setWriteReg(WRITEREGISTER);
		regs.setWriteData(ALURESULT);
		regs.clockBack();



















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
