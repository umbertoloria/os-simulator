package com.umbertoloria.virtual_machine;

import com.umbertoloria.memory.RAM;
import com.umbertoloria.mips_project.cpu.CPUDriversManager;
import com.umbertoloria.program.Program;

public class VirtualMachine {

	private int cores_count;
	private int ram_size;

	private CPUDriversManager cdm;
	private RAM ram;

	// Inits

	public void setCoresCount(int cores_count) {
		this.cores_count = cores_count;
	}

	public void setRAMSize(int ram_size) {
		this.ram_size = ram_size;
	}

	public void init() {
		cdm = new CPUDriversManager(cores_count);
		ram = new RAM(ram_size);
	}

	// Operations

	public void execute(Program p) {
		if (p.parse()) {
			String instr;
			while ((instr = p.nextInstr()) != null) {
				interpretate(instr);
			}
		}
	}

	private void interpretate(String instr) {
		int first_space = instr.indexOf(" ");
		String cmd = instr.substring(0, first_space);
		int index = instr.indexOf(" ", first_space + 1);
		String o1 = instr.substring(first_space + 1, index);
		String o2 = instr.substring(index + 1);
		int res = 0;
		if (cmd.equals("add")) {
			res = cdm.add(Integer.parseInt(o1), Integer.parseInt(o2));
		} else if (cmd.equals("and")) {
			res = cdm.and(Integer.parseInt(o1), Integer.parseInt(o2));
		} else if (cmd.equals("or")) {
			res = cdm.or(Integer.parseInt(o1), Integer.parseInt(o2));
		}
		System.out.println(res);
	}

}
