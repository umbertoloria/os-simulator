package com.umbertoloria.cpu;

import java.util.ArrayList;

public class CPUDriversManager {

	private ArrayList<CPUDriver> drivers = new ArrayList<>();

	public CPUDriversManager(int architecture, int cores_count) {
		CPU cpu;
		for (int i = 0; i < cores_count; i++) {
			cpu = new CPU(architecture);
			drivers.add(new CPUDriver(cpu));
		}
	}

	private CPUDriver getFree() {
		return drivers.get(0);
	}

	// Operations

	public int add(int a, int b) {
		CPUDriver cd = getFree();
		cd.instr(CPUDriver.ADD, a, b);
		return cd.getAN();
	}

	public int and(int a, int b) {
		CPUDriver cd = getFree();
		cd.instr(CPUDriver.AND, a, b);
		return cd.getLN();
	}

	public int or(int a, int b) {
		CPUDriver cd = getFree();
		cd.instr(CPUDriver.OR, a, b);
		return cd.getLN();
	}

}
