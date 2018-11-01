package com.umbertoloria;

import com.umbertoloria.utils.BinaryUtils;

public class Main {

	public static void main(String[] args) {

		String instrCode = "000111";
		String op1 = "0000000000000000001000000000000010000000000000000000000000000111";
		String op2 = "0000000000000000001001000000000000000000000000000000000000000011";
		boolean[] machineCode = BinaryUtils.toRawBools(instrCode + op1 + op2);
		Computer pc = new Computer();
		pc.setInstr(machineCode);
		pc.clock();

	}

}
