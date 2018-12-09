package com.umbertoloria;

import com.umbertoloria.bittings.Bit;
import com.umbertoloria.bittings.Bite;
import com.umbertoloria.integrates.MUX;
import com.umbertoloria.interfaces.Clockable;
import com.umbertoloria.utils.RegistersUtils;

import java.util.Scanner;

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

	void newInstr(String instr, int index) {
		// Aggiungo alla mia istruzione di partenza
		// i restanti bit per raggiungere la lunghezza 192.
		StringBuilder extendedInstr = new StringBuilder();
		extendedInstr.append(instr);
		while (extendedInstr.length() < 64 * 3) {
			extendedInstr.append("0");
		}
		// Carico l'istruzione completa in memoria.
		memory.addInstr(Bite.toBits(extendedInstr.toString()), index * 3);
	}

	void registerStatus(boolean hex) {
		String AR = regs.getRegContent(RegistersUtils.AR_C, hex);
		String LR = regs.getRegContent(RegistersUtils.LR_C, hex);
		String MR = regs.getRegContent(RegistersUtils.MR_C, hex);
		String CR = regs.getRegContent(RegistersUtils.CR_C, hex);
		String OR1 = regs.getRegContent(RegistersUtils.OR1_C, hex);
		String OR2 = regs.getRegContent(RegistersUtils.OR2_C, hex);
		System.out.print("+---------------------------");
		if (hex) {
			System.out.println("-----------------+");
		} else {
			System.out.println("-----------------------------------------------------------------+");
		}
		System.out.println("| Arithmetical Register   | " + AR + " |");
		System.out.println("|      Logical Register   | " + LR + " |");
		System.out.println("|       Memory Register   | " + MR + " |");
		System.out.println("|    Condition Register   | " + CR + " |");
		System.out.println("|        Other Register 1 | " + OR1 + " |");
		System.out.println("|        Other Register 2 | " + OR2 + " |");
		System.out.print("+---------------------------");
		if (hex) {
			System.out.println("-----------------+");
		} else {
			System.out.println("-----------------------------------------------------------------+");
		}
	}

	Computer() {
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
		Bit[] WRITEREGISTER = controlUnit.getWriteRegister();
		Bit[] ALUMODE = controlUnit.getALUMODE();
		// Read REGs
		regs.setReadFlag1(READFLAG1);
		regs.setReadFlag2(READFLAG2);
		regs.setReadReg1(OPERATOR1);
		regs.setReadReg2(OPERATOR2);
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
		// Write REGs
		Bit[] ALURESULT = arithmeticLogicUnit.getResult();
		regs.setWriteFlag(WRITEFLAG);
		regs.setWriteReg(WRITEREGISTER);
		regs.setWriteData(ALURESULT);
	}

	private Clockable lastClocked;

	private void clock(Clockable c) {
		c.clock();
		lastClocked = c;
		System.out.println("Clocked " + c);
		s.nextLine();
	}

	private static Scanner s = new Scanner(System.in);

	private void clockBack(Clockable c) {
		c.clockBack();
		lastClocked = c;
		System.out.println("Clocked (back) " + c);
		s.nextLine();
	}

	public void clock() {
		clock(programCounter);
		clock(memory);
		clock(controlUnit);
		clock(regs);
		clock(aluSrc1Mux);
		clock(aluSrc2Mux);
		clock(arithmeticLogicUnit);
		// clock(memory);
		clockBack(regs);
	}

	/*private static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}*/

	void draw(Renderer r) {
		r.translate(30, 30);
		programCounter.draw(r, programCounter == lastClocked);
//		System.out.println("prima " + (programCounter == lastClocked));

		r.translate(0, 120);
		memory.draw(r, memory == lastClocked);
//		System.out.println("dopo " + (memory == lastClocked));

		r.translate(0, 200);
		controlUnit.draw(r, controlUnit == lastClocked);

		r.translate(0, 150);
		regs.draw(r, regs == lastClocked);

		r.translate(0, 200);
		aluSrc1Mux.draw(r, aluSrc1Mux == lastClocked);

		r.translate(0, 130);
		aluSrc2Mux.draw(r, aluSrc2Mux == lastClocked);

		r.translate(0, 130);
		arithmeticLogicUnit.draw(r, arithmeticLogicUnit == lastClocked);
	}

}
