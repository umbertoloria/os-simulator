package com.umbertoloria.memory;

import java.util.Hashtable;

public class Ram {

	private boolean[] memory;

	public Ram(int size) {
		memory = new boolean[size];
	}

	public boolean[] read(int addr, int size) {
		boolean[] res = new boolean[size];
		for (int i = 0; i < size; i++) {
			res[i] = memory[addr + i];
		}
		return res;
	}

	public void write(int addr, boolean[] save) {
		for (int i = 0; i < save.length; i++) {
			memory[addr + i] = save[i];
		}
	}

	public int size() {
		return memory.length;
	}

	/*public void createVariables(String varName, int length) {
		if (firstAddr + architecture * length < memory.length) {
			variables.put(varName, new int[]{firstAddr, architecture * length});
			firstAddr += architecture * length;
		} else {
			// TODO: error! verificare se è davvero sempre così!
		}
	}

	public boolean[] readVariable(String varName) {
		int[] variableControlBlock = variables.get(varName);
		if (variableControlBlock == null) {
			return null;
		}
		boolean[] res = new boolean[variableControlBlock[1]];
		for (int i = 0; i < res.length; i++) {
			res[i] = memory[variableControlBlock[0] + i];
		}
		return res;
	}

	public void writeVariable(String varName, int offset, boolean[] save) {
		int[] variableControlBlock = variables.get(varName);
		if (variableControlBlock != null) {
			for (int i = 0; i < architecture; i++) {
				memory[variableControlBlock[0] + offset * architecture + i] = save[i];
			}
		}
	}*/

	public void print() {
		for (boolean b : memory) {
			System.out.print(b ? "1" : "0");
		}
		System.out.println();
	}

	public void print(int start, int size) {
		for (int i = 0; i < start; i++) {
			System.out.print(" ");
		}
		for (int i = 0; i < size; i++) {
			System.out.print(memory[start + i] ? "1" : "0");
		}
		System.out.println();
	}

	/*public void printVariable(String variable) {
		int[] data = variables.get(variable);
		if (data != null) {
			print(data[0], data[0] + data[1]);
		}
	}*/

}
