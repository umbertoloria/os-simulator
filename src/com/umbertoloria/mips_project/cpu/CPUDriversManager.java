package com.umbertoloria.mips_project.cpu;

import java.util.ArrayList;
import java.util.List;

public class CPUDriversManager {

	private List<CPUDriver> drivers = new ArrayList<CPUDriver>();
	private CPUDriver pref;

	public CPUDriversManager (int cores_count) {
		CPU cpu;
		for (int i = 0; i < cores_count; i++) {
			cpu = new CPU(5);
			drivers.add(new CPUDriver(cpu));
		}
		pref = drivers.get(0);
	}

	public CPUDriver getFree () {
		return pref;
	}

	// Operations

	public int add (int a, int b) {
		CPUDriver cd = getFree();
		cd.instr(CPUDriver.ADD, a, b);
		return cd.getAN();
	}

}
