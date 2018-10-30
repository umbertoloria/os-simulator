package com.umbertoloria;

public class Main {

	public static void main(String[] args) {

		Compiler ulac = new Compiler();
		Computer pc = new Computer();

		ulac.add("add 13 11");
		pc.setInstr(ulac.getTMP());
		pc.clock();

	}

}
