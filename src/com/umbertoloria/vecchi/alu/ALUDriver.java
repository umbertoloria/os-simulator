package com.umbertoloria.vecchi.alu;

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

	/**
	 Composes and puts a complete instruction into the Instruction Memory Area of the ALU and simulates the clock.
	 @param type   of the instruction
	 @param first  instruction operand
	 @param second instruction operand
	 */
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

	/**
	 Gets the value of the Arithmetical Register of the ALU.
	 @return number contained in the AR
	 */
	int getAN() {
		return BinaryUtils.toInt(alu.getAR());
	}

	/**
	 Gets the value of the Logical Register of the ALU.
	 @return number contained in the LR
	 */
	int getLN() {
		return BinaryUtils.toInt(alu.getLR());
	}

	/**
	 Pushes a bit sequence in the Instruction Memory Area of the ALU.
	 @param bits will be put
	 */
	private void pushBits(boolean[] bits) {
		for (boolean bit : bits) {
			alu.push(bit);
		}
	}

	/**
	 Pushes a bit sequence in the Intruction Memory Area of the ALU.
	 @param str contains the bits to put
	 */
	private void pushString(String str) {
		pushBits(BinaryUtils.toRawBools(str));
	}

	/**
	 Pushes a number converted into a bit sequence in the Instruction Memory Area of the ALU.
	 @param number will be first converted and then put
	 */
	private void pushNumber(int number) {
		pushBits(BinaryUtils.convert(number, alu.getArch()));
	}

}
