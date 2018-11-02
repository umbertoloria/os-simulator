package com.umbertoloria;

import com.umbertoloria.bittings.Bit;

public class Main {

	public static void main(String[] args) {

		String instrCode, op1, op2;
		Computer pc = new Computer();

		// add 3 4
		instrCode = "001101";
		op1 = "0000000000000000000000000000000000000000000000000000000000000011";
		op2 = "0000000000000000000000000000000000000000000000000000000000000100";
		pc.newInstr(instrCode + op1 + op2, 0);

		// add AR 1
		instrCode = "001111";
		op1 = "0000000000000000000000000000000000000000000000000000000000000000";
		op2 = "0000000000000000000000000000000000000000000000000000000000000001";
		pc.newInstr(instrCode + op1 + op2, 1);

		// sub AR 1
		instrCode = "010011";
		op1 = "0000000000000000000000000000000000000000000000000000000000000000";
		op2 = "0000000000000000000000000000000000000000000000000000000000000001";
		pc.newInstr(instrCode + op1 + op2, 2);

		// add 1101 AR
		instrCode = "001110";
		op1 = "0000000000000000000000000000000000000000000000000000000000001101";
		op2 = "0000000000000000000000000000000000000000000000000000000000000000";
		pc.newInstr(instrCode + op1 + op2, 3);

		pc.clock();
		pc.clock();
		pc.clock();
		pc.clock();

		System.out.println("Bit creati per numero istruzione:");
		for (int i = 0; i < Bit.instructions; i++) {
			System.out.println(i + ": " + Bit.numNews[i]);
		}

		pc.registerStatus(false);

	}

}
