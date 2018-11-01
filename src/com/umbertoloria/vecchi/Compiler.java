package com.umbertoloria;

import com.umbertoloria.utils.BinaryUtils;
import com.umbertoloria.utils.BitsUtils;
import com.umbertoloria.utils.InstructionUtils;
import com.umbertoloria.utils.RegistersUtils;

import java.util.ArrayList;

public class Compiler {

	private ArrayList<boolean[]> instructions = new ArrayList<>();
	private int index = 0;
	// FIXME: vedere di togliere queste costanti da quì
	private static final int CONSTANT_SIZE = 64;
	private static final int INSTRUCTION_ADDRESS_SIZE = 64;
	private static final int MEMORY_ADDRESS_SIZE = 64;
	private boolean[] instruction = new boolean[InstructionUtils.MAX_INSTRUCTION_SIZE];

	public void add(String instr) {
		// FIXME: not 7 7 allowd
		String[] p = instr.split(" ");
		String first = "", second = "";
		if (p.length >= 2) {
			first = p[1].trim();
			second = "";
			if (p.length >= 3) {
				second = p[2].trim();
			}
		}

		//index = BitsUtils.append(instruction, index, InstructionUtils.getInstructionCode(p[0]));
		boolean[] partone = new boolean[InstructionUtils.INSTRUCTION_CODE_SIZE];

		if (InstructionUtils.isALUSTDInstructionName(p[0])) {

			boolean[] a = getRegisterOrConst(first);
			index = BitsUtils.append(instruction, index, a.length == RegistersUtils.REGISTERS_SIZE);
			boolean[] b = getRegisterOrConst(second);
			index = BitsUtils.append(instruction, index, b.length == RegistersUtils.REGISTERS_SIZE);
			index = BitsUtils.append(instruction, index, a);
			index = BitsUtils.append(instruction, index, b);

		} else if (InstructionUtils.isGOTCInstructionName(p[0])) {

			index = BitsUtils.append(instruction, index, RegistersUtils.getRegisterCode(first));
			index = BitsUtils.append(instruction, index, getInstruction(second));

		} else if (InstructionUtils.isMEMInstructionName(p[0])) {

			boolean[] a = getRegisterOrConst(second);
			index = BitsUtils.append(instruction, index, a.length == RegistersUtils.REGISTERS_SIZE);
			index = BitsUtils.append(instruction, index, getMemory(first));
			index = BitsUtils.append(instruction, index, a);

		} else if (p[0].equals("use")) {

			index = BitsUtils.append(instruction, index, getConst(first));

		} else if (p[0].equals("set")) {

			boolean[] a = getRegisterOrConst(second);
			index = BitsUtils.append(instruction, index, a.length == RegistersUtils.REGISTERS_SIZE);
			index = BitsUtils.append(instruction, index, RegistersUtils.getRegisterCode(first));
			index = BitsUtils.append(instruction, index, a);

		} else if (p[0].equals("not")) {

			boolean[] a = getRegisterOrConst(first);
			index = BitsUtils.append(instruction, index, a.length == RegistersUtils.REGISTERS_SIZE);
			index = BitsUtils.append(instruction, index, a);

		} else if (p[0].equals("goto")) {

			index = BitsUtils.append(instruction, index, getInstruction(first));

		} else if (p[0].equals("print")) {
			System.out.println("print bypassata");
		}

		push();
	}

	private void push() {
		instructions.add(BitsUtils.truncate(instruction, index));
		index = 0;
	}

	// TODO: remove this function.
	public boolean[] getTMP() {
		return instructions.get(instructions.size() - 1);
	}

	private boolean[] getConst(String data) {
		return BinaryUtils.convert(Integer.parseInt(data), CONSTANT_SIZE);
	}

	private boolean[] getRegisterOrConst(String data) {
		try {
			return RegistersUtils.getRegisterCode(data);
		} catch (RuntimeException e) {
			return BinaryUtils.convert(Integer.parseInt(data), CONSTANT_SIZE);
		}
	}

	private boolean[] getInstruction(String token) throws RuntimeException {
		if (token.startsWith("@")) {
			return BinaryUtils.convert(Integer.parseInt(token.substring(1)), INSTRUCTION_ADDRESS_SIZE);
		}
		throw new RuntimeException();
	}

	private boolean[] getMemory(String token) throws RuntimeException {
		if (token.startsWith("#")) {
			return BinaryUtils.convert(Integer.parseInt(token.substring(1)), MEMORY_ADDRESS_SIZE);
		}
		throw new RuntimeException();
	}

}
