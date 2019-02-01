package com.umbertoloria.components;

import com.umbertoloria.bitting.Bit;
import com.umbertoloria.bitting.BitAlloc;
import com.umbertoloria.bitting.BitLink;
import com.umbertoloria.graphics.Renderer;
import com.umbertoloria.integrates.MUX;
import com.umbertoloria.interfaces.Clockable;

public class Computer implements Clockable {

	public static final int ARCH = 64;
	// private boolean[] instr = new boolean[InstructionUtils.MAX_INSTRUCTION_SIZE];

	private ProgramCounter programCounter = new ProgramCounter();
	private Memory memory = new Memory();
	private ControlUnit controlUnit = new ControlUnit();
	private Registers regs = new Registers();
	private MUX aluSrc1Mux = new MUX();
	private MUX aluSrc2Mux = new MUX();
	private ArithmeticLogicUnit arithmeticLogicUnit = new ArithmeticLogicUnit();
	private int lastInstrIndex = 0;
	private int frequency;

	public void newInstr(String instr) {
		// Aggiungo alla mia istruzione di partenza
		// i restanti bit per raggiungere la lunghezza 192.
		StringBuilder extendedInstr = new StringBuilder();
		extendedInstr.append(instr);
		while (extendedInstr.length() < 64 * 3) {
			extendedInstr.append("0");
		}
		// Carico l'istruzione completa in memoria.
		memory.addInstr(BitAlloc.create(extendedInstr.toString()), lastInstrIndex++ * 3);
	}

	public Computer(int frequency) {
		this.frequency = frequency;
		// PC
		Bit[] INSTRUCTION_ADDRESS = programCounter.get();
		// IM
		memory.set(INSTRUCTION_ADDRESS);
		Bit[] INSTR = memory.get();
		controlUnit.set(INSTR);
		// CU
		Bit READFLAG1 = controlUnit.getReadFlag1();
		Bit READFLAG2 = controlUnit.getReadFlag2();
		Bit WRITEFLAG = controlUnit.getWriteFlag();
		Bit ALUSRC1 = controlUnit.getALUSRC1();
		Bit ALUSRC2 = controlUnit.getALUSRC2();
		Bit[] OPERATOR1 = controlUnit.getOP1();
		Bit[] OPERATOR2 = controlUnit.getOP2();
		Bit[] READREGISTER1 = new Bit[3];
		Bit[] READREGISTER2 = new Bit[3];
		BitLink.linkSub(READREGISTER1, OPERATOR1, 61, 64);
		BitLink.linkSub(READREGISTER2, OPERATOR2, 61, 64);
		Bit[] WRITEREGISTER = controlUnit.getWriteRegister();
		Bit[] ALUMODE = controlUnit.getALUMODE();
		Bit JUMPFLAG = controlUnit.getJUMPFLAG();
		Bit[] NEXTINSTR = controlUnit.getNEXTINSTR();
		// Read REGs
		regs.setReadFlag1(READFLAG1);
		regs.setReadFlag2(READFLAG2);
		regs.setReadReg1(READREGISTER1);
		regs.setReadReg2(READREGISTER2);
		Bit[] READDATA1 = regs.getData1();
		Bit[] READDATA2 = regs.getData2();
		// ALU
		aluSrc1Mux.setS(ALUSRC1);
		aluSrc1Mux.set(0, READDATA1);
		aluSrc1Mux.set(1, OPERATOR1);
		aluSrc2Mux.setS(ALUSRC2);
		aluSrc2Mux.set(0, READDATA2);
		aluSrc2Mux.set(1, OPERATOR2);
		Bit[] ALUSRC1OUT = aluSrc1Mux.get();
		Bit[] ALUSRC2OUT = aluSrc2Mux.get();
		arithmeticLogicUnit.setA(ALUSRC1OUT);
		arithmeticLogicUnit.setB(ALUSRC2OUT);
		arithmeticLogicUnit.setMode(ALUMODE);
		Bit[] ALURESULT = arithmeticLogicUnit.getResult();
		// Write REGs
		regs.setWriteFlag(WRITEFLAG);
		regs.setWriteReg(WRITEREGISTER);
		regs.setWriteData(ALURESULT);
		// PC
		programCounter.setJumpFlag(JUMPFLAG);
		programCounter.setNextInstr(NEXTINSTR);
	}

	private Clockable lastClocked;

	private void clock(Clockable c) {
		c.clock();
		lastClocked = c;
		sleep((int) (1000d / frequency));
	}

	private void clockBack(Clockable c) {
		c.clockBack();
		lastClocked = c;
		sleep((int) (1000d / frequency));
	}

	public void clock() {
		clock(programCounter);
		clock(memory);
		clock(controlUnit);
		clock(regs);
		clock(aluSrc1Mux);
		clock(aluSrc2Mux);
		clock(arithmeticLogicUnit);
		// clockBack(memory);
		clockBack(regs);
		clockBack(programCounter);
	}

	private static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void draw(Renderer r) {
		r.translate(30, 30);
		programCounter.draw(r, programCounter == lastClocked);

		r.translate(0, 104);
		memory.draw(r, memory == lastClocked);

		r.translate(682, -104);
		controlUnit.draw(r, controlUnit == lastClocked);

		r.translate(0, 122);
		regs.draw(r, regs == lastClocked);

		r.translate(0, 130);
		aluSrc1Mux.draw(r, aluSrc1Mux == lastClocked);

		r.translate(0, 106);
		aluSrc2Mux.draw(r, aluSrc2Mux == lastClocked);

		r.translate(0, 106);
		arithmeticLogicUnit.draw(r, arithmeticLogicUnit == lastClocked);
	}

}
