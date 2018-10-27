package com.umbertoloria.alu;

import com.umbertoloria.utils.BinaryUtils;

import java.util.Random;

class ALUDriver {

	final static int ADD = 1;
	final static int AND = 2;
	final static int OR = 3;

	private ALU alu;
	private Random r = new Random();

	ALUDriver(ALU alu) {
		this.alu = alu;
	}

	private void pushBits(boolean[] bits) {
		for (boolean bit : bits) {
			alu.push(bit);
		}
	}

	private void pushString(String str) {
		pushBits(BinaryUtils.toRawBools(str));
	}

	private void pushNumber(int number) {
		pushBits(BinaryUtils.convert(number, alu.getArch()));
	}

	void instr(int type, int first, int second) {
		if (type == AND) {
			pushString("00");
		} else if (type == OR) {
			pushString("01");
		} else if (type == ADD) {
			pushString("10");
		}
		pushNumber(first);
		pushNumber(second);
		alu.clock();
	}

	int getAN() {
		return BinaryUtils.toInt(alu.getAR());
	}

	int getLN() {
		return BinaryUtils.toInt(alu.getLR());
	}

}
