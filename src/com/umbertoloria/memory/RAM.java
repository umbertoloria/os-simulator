package com.umbertoloria.memory;

import java.util.Hashtable;
import java.util.LinkedList;

public class RAM {

	private int length;
	private int architecture;

	private boolean[] memory;
	private Hashtable<String, int[]> variables = new Hashtable<>();
	private int firstAddr = 0;


	public RAM(int architecture, int length) {
		this.architecture = architecture;
		this.length = length;
		memory = new boolean[architecture * length];
	}

	public void createVariables(String varName, int length) {
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
	}

	public void print() {
		for (boolean b : memory) {
			System.out.print(b ? "1" : "0");
		}
		System.out.println();
	}

	private void print(int start, int end) {
		for (int i = 0; i < start; i++) {
			System.out.print(" ");
		}
		for (int i = start; i < end; i++) {
			System.out.print(memory[i] ? "1" : "0");
		}
		System.out.println();
	}

	public void printVariable(String variable) {
		int[] data = variables.get(variable);
		if (data != null) {
			print(data[0], data[0] + data[1]);
		}
	}

}


/*class VariableControlBlock {

	private String variable;
	private int firstAddr;
	private int length;

	VariableControlBlock(String variable, int firstAddr, int length) {
		this.variable = variable;
		this.firstAddr = firstAddr;
		this.length = length;
	}

}

class FreeMemoryControlBlock {

	private int start, end;

	FreeMemoryControlBlock (int start, int end) {
		this.start = start;
		this.end = end;
	}

	public void
}*/

// TODO: test se sono visibilit fuori dalla classe (o dal package)
