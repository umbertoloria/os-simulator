package com.umbertoloria;

public class Main {

	public static void main(String[] args) {

		/*a.add("gotf AR @12");
		a.add("load #5 AR");
		a.add("use 4");
		a.add("set MR 5");
		a.add("not 5");
		a.add("goto @40");*/
		/*BitsUtils.print(a.get());

		a.add("add 1 1");
		BitsUtils.print(a.get());

		a.add("gotf CR @1");
		BitsUtils.print(a.get());*/

		Compiler ulac = new Compiler();
		Computer pc = new Computer();

		//ulac.add("add 7551 4654");
		ulac.add("add 7 7");
		//ulac.add("add LR MR");
		pc.setInstr(ulac.get());
		pc.clock();



	}

}
