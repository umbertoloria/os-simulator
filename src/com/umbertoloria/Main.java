package com.umbertoloria;

import com.umbertoloria.program.Program;
import com.umbertoloria.virtual_machine.VirtualMachine;

public class Main {

	//private static Random r = new Random();

	public static void main(String[] args) {
		Program p = new Program();
		p.addInstr("or 6 4;");

		VirtualMachine vm = new VirtualMachine();
		vm.setCoresCount(1);
		//vm.setRAMSize(10);

		vm.init();

		vm.execute(p);
	}

	/*private static void sleep() {
		try {
			Thread.sleep(10L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}*/
}
