package com.umbertoloria;

public class Main {

	public static void main(String[] args) {

		String instrCode, op1, op2;

		Computer pc = new Computer();

		new Watcher(pc);

		// add 3 4
		instrCode = "001101";
		op1 = "0000000000000000000000000000000000000000000000000000000000000011";
		op2 = "0000000000000000000000000000000000000000000000000000000000000100";

//		Bit.WATCH("Inserimento Prima Instruzione");
		pc.newInstr(instrCode + op1 + op2, 0);

//		Bit.WATCH("Primo CLOCK");
		pc.clock();

/*		// add AR 1
		instrCode = "001111";
		op1 = "0000000000000000000000000000000000000000000000000000000000000000";
		op2 = "0000000000000000000000000000000000000000000000000000000000000001";

		Bit.WATCH("Inserimento Seconda Instruzione");
		pc.newInstr(instrCode + op1 + op2, 1);

		Bit.WATCH("Secondo CLOCK");
		pc.clock();

		// sub AR 1
		instrCode = "010011";
		op1 = "0000000000000000000000000000000000000000000000000000000000000000";
		op2 = "0000000000000000000000000000000000000000000000000000000000000001";

		Bit.WATCH("Inserimento Terza Instruzione");
		pc.newInstr(instrCode + op1 + op2, 2);

		Bit.WATCH("Terzo CLOCK");
		pc.clock();

		// add 3 AR
		instrCode = "001110";
		op1 = "0000000000000000000000000000000000000000000000000000000000000011";
		op2 = "0000000000000000000000000000000000000000000000000000000000000000";

		Bit.WATCH("Inserimento Quarta Instruzione");
		pc.newInstr(instrCode + op1 + op2, 3);

		Bit.WATCH("Quarto CLOCK");
		pc.clock();
*/
/*
		// greeq 4 4
		instrCode = "101100";
		op1 = "0000000000000000000000000000000000000000000000000000000000000100";
		op2 = "0000000000000000000000000000000000000000000000000000000000000100";

		Bit.WATCH("Inserimento Prima Instruzione");
		pc.newInstr(instrCode + op1 + op2, 0);

		Bit.WATCH("Primo CLOCK");
		pc.clock();
*/
//		Bit.eWATCH();
		// Bit.enableOutput();
//		pc.registerStatus(false);

	}

}
