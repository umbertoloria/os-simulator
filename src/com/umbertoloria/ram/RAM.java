package com.umbertoloria.ram;

class RAM {

	private boolean[] memory;

	RAM(int size) {
		memory = new boolean[size];
	}

	boolean[] read(int addr, int size) {
		boolean[] res = new boolean[size];
		System.arraycopy(memory, addr, res, 0, size);
		return res;
	}

	void write(int addr, boolean[] save) {
		System.arraycopy(save, 0, memory, addr, save.length);
	}

	int size() {
		return memory.length;
	}

	// TODO: Rimuovere

	void print() {
		for (boolean b : memory) {
			System.out.print(b ? "1" : "0");
		}
		System.out.println();
	}

	void print(int start, int size) {
		for (int i = 0; i < start; i++) {
			System.out.print(" ");
		}
		for (int i = 0; i < size; i++) {
			System.out.print(memory[start + i] ? "1" : "0");
		}
		System.out.println();
	}

}
