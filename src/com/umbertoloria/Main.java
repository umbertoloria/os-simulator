package com.umbertoloria;

import com.umbertoloria.mips_project.cpu.CPUDriversManager;
import com.umbertoloria.program.Program;
import com.umbertoloria.virtual_machine.VirtualMachine;

import java.util.Random;

public class Main {

	private static Random r = new Random();

	public static void main(String[] args) {
		Program p = new Program();
		p.addInstr("add 5 3;");
		p.addInstr("add 7 1;");
		p.addInstr("add 2 3;");

		VirtualMachine vm = new VirtualMachine();
		vm.setCoresCount(1);
		vm.setRAMSize(10);

		vm.init();

		vm.execute(p);
	}

	private static void sleep() {
		try {
			Thread.sleep(10L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
