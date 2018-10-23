package com.umbertoloria;

import com.umbertoloria.program.Program;
import com.umbertoloria.utils.FileLoader;
import com.umbertoloria.virtual_machine.VirtualMachine;

import java.io.IOException;
import java.io.InputStream;

public class Main {

	//private static Random r = new Random();

	public static void main(String[] args) {
		Program p = new Program();
		p.addCompleteSource(FileLoader.getContent("program.u"));

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
