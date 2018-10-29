package com.umbertoloria;

import com.umbertoloria.utils.BinaryUtils;

import java.util.Arrays;

public class Computer {

	//private int arch;
	private Registers regs;
	private ALU alu;
	private boolean[] instr;
	private int instr_length;

	public Computer(int arch) {
		//this.arch = arch;
		regs = new Registers(arch);
		alu = new ALU(arch);
		//instr = BinaryUtils.toRawBools(ins);
		instr_length = 7 + 2 * arch;
	}

	public void setInstr (String ins) {
		instr = BinaryUtils.toRawBools(ins);
	}

	/*public void add_cc (int a, int b) {
		regs.setReadFlag(false);
		regs.clock();
		alu.setA(BinaryUtils.convert(a, arch));
		alu.setB(BinaryUtils.convert(b, arch));
		alu.setMode(ALU.ADD);
		alu.clock();
		boolean[] alu_out = alu.getOUT();
		regs.setWriteFlag(true);
		regs.setWriteReg(Registers.AR_CODE);
		regs.setWriteData(alu_out);
		regs.clockBack();
		/*regs.setReadReg2(Registers.AR_CODE);
		regs.setReadFlag(true);
		regs.clock();
		System.out.println(BinaryUtils.toStr(regs.getData2()));*
	}*/

	public void clock() {
		if (Arrays.equals(instr, 0, 5, BinaryUtils.toRawBools("00101"), 0, 5)) {
			System.out.println("add");

			regs.setReadFlag1(instr[5]);
			regs.setReadFlag2(instr[6]);
			System.out.println(instr[5] ? "reg" : "const");
			System.out.println(instr[6] ? "reg" : "const");

			regs.setReadReg1(new boolean[]{instr[7], instr[8], instr[9], instr[10], instr[11]});
			regs.setReadReg2(new boolean[]{instr[12], instr[13], instr[14], instr[15], instr[16]});

			regs.clock();

			System.out.print(BinaryUtils.toStr(regs.getData1()));
			System.out.print(" + ");
			System.out.print(BinaryUtils.toStr(regs.getData2()) + " = ");

			alu.setA(regs.getData1());
			alu.setB(regs.getData2());
			alu.setMode(ALU.ADD);
			alu.clock();
			alu.print(alu.out);

			regs.setWriteFlag(true);
			regs.setWriteReg(Registers.AR_CODE);
			regs.setWriteData(alu.out);

			regs.clockBack();


		}
	}

}
