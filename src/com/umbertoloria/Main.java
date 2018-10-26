package com.umbertoloria;

import com.umbertoloria.program.Program;
import com.umbertoloria.utils.FileLoader;
import com.umbertoloria.virtual_machine.VirtualMachine;

public class Main {

	public static void main(String[] args) {

		Program p = new Program(FileLoader.getContent("add_test.u"));

		VirtualMachine vm = new VirtualMachine(10);
		vm.execute(p, false);

	}

}
