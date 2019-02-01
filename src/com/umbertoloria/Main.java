package com.umbertoloria;

import com.umbertoloria.assembler.Assembler;
import com.umbertoloria.components.Computer;
import com.umbertoloria.graphics.Watcher;
import com.umbertoloria.utils.FileLoader;

public class Main {

	private static final Computer pc = new Computer(10);
	private static final Assembler a = new Assembler();
	private static final Watcher w = new Watcher(pc);

	public static void main(String[] args) {

		String src = FileLoader.getContent("tests/basics.ula");

		String[] instructions = a.compile(src);
		if (instructions == null) {
			System.exit(1);
		}

		for (String instruction : instructions) {
			pc.newInstr(instruction);
		}

		while (!w.isClosing()) {
			pc.clock();
			System.out.println("Clock");
		}

		System.out.println("Chiudendo.");

	}

}
