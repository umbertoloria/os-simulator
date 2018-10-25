package com.umbertoloria.program;

import com.umbertoloria.utils.ParserUtils;

import java.util.ArrayList;

public class Program {

	private StringBuilder src;
	private boolean editing = true;
	private ArrayList<Instruction> instructions;
	private int pc = 0;

	public Program(String program) {
		program = program.replaceAll("\r", "");
		program = program.replaceAll("\t", "");
		src = new StringBuilder(program);
		editing = false;
	}

	public boolean parse(boolean verboose) {
		editing = false;

		if (verboose) {
			System.out.println("-------------------------------");
			System.out.println("        Starting PARSE");
			System.out.println("-------------------------------");
		}

		instructions = ParserUtils.instructionsExploding(src);

		if (instructions.size() == 0) {
			if (verboose) {
				System.out.println("[FAIL] Instruction exploding");
			}
			return false;
		}

		if (verboose) {
			System.out.println("[DONE] Instruction exploding");
			System.out.println("-------------------------------");
		}

		if (verboose) {
			boolean instruction_parsed;
			for (int i = 0; i < instructions.size(); i++) {
				instruction_parsed = instructions.get(i).parse();
				System.out.print("[" + (instruction_parsed ? "DONE" : "FAIL") + "] ");
				System.out.printf("Parsing instruction %4d\n", i);
			}
		} else {
			for (Instruction instruction : instructions) {
				instruction.parse();
			}
		}

		if (verboose) {
			System.out.println("-------------------------------");
			System.out.println("         Ending PARSE");
			System.out.println("-------------------------------\n");
		}

		return true;
	}

	public Instruction nextInstr() {
		if (!editing && instructions.size() > pc) {
			return instructions.get(pc++);
		}
		return null;
	}

	@Override
	public String toString() {
		return src.toString();
	}

}
