package com.umbertoloria.vecchi.ram;

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

}
