package com.umbertoloria;

public class Main {

	public static void main(String[] args) {

		/*Program p = new Program(FileLoader.getContent("programs/add_test.u"));
		VirtualMachine vm = new VirtualMachine(10);
		vm.execute(p, false);*/

		//Assembler a = new Assembler(FileLoader.getContent("check_valori.ula"));

		/*ALU a = new ALU(5);
		a.setA(BinaryUtils.convert(4, 5));
		a.setB(BinaryUtils.convert(1, 5));
		a.setMode(BinaryUtils.toRawBools("0100"));
		a.clock();
		a.print(a.out);*/


		/*Registers r = new Registers(5);
		r.setReadFlag(true);
		r.setWriteFlag(true);
		r.setReadReg1(Registers.AR_CODE);
		r.clock();

		boolean[] out = r.getData1();
		System.out.println(BinaryUtils.toStr(out));

		r.setWriteReg(Registers.AR_CODE);
		r.setWriteData(BinaryUtils.convert(3, 5));
		r.clockBack();

		r.setReadReg1(Registers.AR_CODE);
		r.clock();

		out = r.getData1();
		System.out.println(BinaryUtils.toStr(out));*/

		String ins = "";
		ins += "00101"; // ADD
		ins += "00";
		ins += "00100"; // 4
		ins += "00001"; // 1
		Computer c = new Computer(5, ins);
		c.clock();

		/*System.out.println(Registers.endsWith(new boolean[]{true, true, false, false}, new boolean[]{true, false,
				false}));*/

	}

}
