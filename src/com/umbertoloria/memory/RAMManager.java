package com.umbertoloria.memory;

import com.umbertoloria.archs.Binary;

public class RAMManager {

	private int architecture;
	private RAM ram;

	public RAMManager(int architecture, int ram_size) {
		this.architecture = architecture;
		ram = new RAM(architecture, ram_size);
	}

	public void createVariable(String varName) {
		ram.createVariables(varName, 1);
	}

	public void createArray(String arrayName, int length) {
		if (length > 1) {
			ram.createVariables(arrayName, length);
		}
	}

	public void set(String varName, int value) {
		ram.writeVariable(varName, 0, Binary.convert(value, architecture));
	}

	public void set(String varName, int addr, int value) {
		ram.writeVariable(varName, addr, Binary.convert(value, architecture));
	}

	public int get(String varName) {
		return Binary.toInt(ram.readVariable(varName));
	}

}
