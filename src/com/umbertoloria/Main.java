package com.umbertoloria;

import com.umbertoloria.utils.BinaryUtils;

public class Main {

	public static void main(String[] args) {

		String instrCode = "001101";
		String op1 = "0000000000000000000000000000000000000000000000000000000000000001";
		String op2 = "0000000000000000000000000000000000000000000000000000000000000001";
		boolean[] mc1 = BinaryUtils.toRawBools(instrCode + op1 + op2);

		instrCode = "001111";
		op1 = "001";
		op2 = "0000000000000000000000000000000000000000000000000000000000001010";
		boolean[] mc2 = BinaryUtils.toRawBools(instrCode + op1 + op2);

		Computer pc = new Computer();
		pc.setInstr(mc1);
		pc.clock();
		pc.setInstr(mc2);
		pc.clock();

	}

}
