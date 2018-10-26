package com.umbertoloria.cpu;

import com.umbertoloria.archs.Binaries;

import java.util.Random;

public class CPUDriver {

	public final static int ADD = 1;
	public final static int AND = 2;
	public final static int OR = 3;

	private CPU cpu;
	private Random r = new Random();

	public CPUDriver(CPU cpu) {
		this.cpu = cpu;
	}

	public void pushBits(boolean[] bits) {
		for (boolean bit : bits) {
			cpu.push(bit);
		}
	}

	public void pushString(String str) {
		pushBits(Binaries.toBools(str));
	}

	public void pushNumber(int number) {
		pushBits(Binaries.convert(number, cpu.getArch()));
	}

	public void pushRandom(int length) {
		for (int i = 0; i < length; i++) {
			cpu.push(r.nextBoolean());
		}
	}

	public void pushRandomNumber() {
		pushRandom(cpu.getArch());
	}

	public void instr(int type, int first, int second) {
		if (type == AND) {
			pushString("00");
		} else if (type == OR) {
			pushString("01");
		} else if (type == ADD) {
			pushString("10");
		}
		pushNumber(first);
		pushNumber(second);
		cpu.clock();
	}

	public int getAN() {
		return Binaries.toInt(cpu.getAR());
	}

	public int getLN() {
		return Binaries.toInt(cpu.getLR());
	}

}
