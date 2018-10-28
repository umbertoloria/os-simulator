package com.umbertoloria.vecchi.alu;

public class ALUManager {

	private ALUDriver ad;

	public ALUManager(int architecture) {
		ad = new ALUDriver(new ALU(architecture));
	}

	public int add(int a, int b) {
		ad.instr(ALUDriver.ADD, a, b);
		return ad.getAN();
	}

	public int and(int a, int b) {
		ad.instr(ALUDriver.AND, a, b);
		return ad.getLN();
	}

	public int or(int a, int b) {
		ad.instr(ALUDriver.OR, a, b);
		return ad.getLN();
	}

}
