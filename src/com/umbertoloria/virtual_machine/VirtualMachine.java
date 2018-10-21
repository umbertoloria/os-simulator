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

	public void interpretate(String instr) {
		String cmd = instr.substring(0, 3);
		int index = instr.indexOf(" ", 4);
		String o1 = instr.substring(4, index);
		String o2 = instr.substring(index + 1);

		if (cmd.equals("add")) {
			int res = cdm.add(Integer.parseInt(o1), Integer.parseInt(o2));
			System.out.println(res);
		}
	}

}
