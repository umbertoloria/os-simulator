package com.umbertoloria;

import com.umbertoloria.bittings.Bit;
import com.umbertoloria.bittings.Bite;
import com.umbertoloria.integrates.MUX;
import com.umbertoloria.utils.RegistersUtils;

public class Computer {

	public static final int ARCH = 64;
	private boolean[] instr; // = new boolean[InstructionUtils.MAX_INSTRUCTION_SIZE];

	private ProgramCounter programCounter = new ProgramCounter();
	private Memory memory = new Memory();
	private ControlUnit controlUnit = new ControlUnit();
	private Registers regs = new Registers();
	private MUX aluSrc1Mux = new MUX();
	private MUX aluSrc2Mux = new MUX();
	private ArithmeticLogicUnit arithmeticLogicUnit = new ArithmeticLogicUnit();
	// TODO: Implement a Control Unit!

	void newInstr(String instr, int index) {
		StringBuilder extendedInstr = new StringBuilder();
		extendedInstr.append(instr);
		while (extendedInstr.length() <= 64 * 3) {
			extendedInstr.append("0");
		}
		memory.addInstr(Bite.toBits(extendedInstr.toString()), index * 3);
	}

	void registerStatus(boolean hex) {
		String AR = regs.getRegContent(RegistersUtils.AR_C, hex);
		String LR = regs.getRegContent(RegistersUtils.LR_C, hex);
		String CR = regs.getRegContent(RegistersUtils.CR_C, hex);
		String MR = regs.getRegContent(RegistersUtils.MR_C, hex);
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
		System.out.println("|    Condition Register   | " + CR + " |");
		System.out.println("|       Memory Register   | " + MR + " |");
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
//		TODO: MEMTOREG?
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

	void clock() {
		programCounter.clock();
		memory.clock();
		controlUnit.clock();
		regs.clock();
		aluSrc1Mux.clock();
		aluSrc2Mux.clock();
		arithmeticLogicUnit.clock();
		// memory
		regs.clockBack();
		Bit.instructions++;
	}

}
