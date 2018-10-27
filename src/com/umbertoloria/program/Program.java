package com.umbertoloria.program;

import com.umbertoloria.utils.ParserUtils;

import java.util.ArrayList;
import java.util.Iterator;

public class Program implements Iterator<Instruction> {

	private StringBuilder src;
	private ArrayList<Instruction> instructions;
	private int pc = 0;

	public Program(String program) {
		program = program.replaceAll("\r", "");
		program = program.replaceAll("\t", "");
		src = new StringBuilder(program);
	}

	public boolean parse(boolean verboose) {
		boolean parse_operation_status = true;

		if (verboose) {
			System.out.println("-------------------------------");
			System.out.println("        Starting PARSE");
			System.out.println("-------------------------------");
		}

		instructions = ParserUtils.getExplodedInstructions(src);

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
				if (instruction_parsed) {
					System.out.print("[DONE] ");
				} else {
					System.out.print("[");
					System.err.print("FAIL");
					System.out.print("] ");
				}
				System.out.printf("Parsing instruction %4d\n", i);
				if (!instruction_parsed) {
					parse_operation_status = false;
					break;
				}
			}
		} else {
			for (Instruction instruction : instructions) {
				if (!instruction.parse()) {
					parse_operation_status = false;
					break;
				}
			}
		}

		if (verboose) {
			if (parse_operation_status) {
				System.out.println("-------------------------------");
				System.out.println("         Ending PARSE");
				System.out.println("-------------------------------\n");
			} else {
				System.out.println("-------------------------------");
				System.err.println("       PARSE interruption");
				System.out.println("-------------------------------\n");
			}
		}

		return parse_operation_status;
	}

	public boolean hasNext() {
		return pc < instructions.size();
	}

	public Instruction next() {
		return instructions.get(pc++);
	}

	public String toString() {
		StringBuilder res = new StringBuilder();
		for (Instruction instruction : instructions) {
			res.append(instruction.toString());
			res.append("\n");
		}
		return res.toString();
	}


}
