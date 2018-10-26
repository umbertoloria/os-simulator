package com.umbertoloria.memory;

import com.umbertoloria.archs.Binaries;

import java.util.Hashtable;

public class RamDriver {

	private int arch;
	private Ram ram;
	private Hashtable<String, int[]> variables = new Hashtable<>();
	private int firstAddr = 0;

	public RamDriver(int arch, int blocks) {
		this.arch = arch;
		ram = new Ram(arch * blocks);
	}

	public void create(String var) {
		if (firstAddr <= ram.size() - arch) {
			variables.put(var, new int[]{firstAddr, arch});
			firstAddr += arch;
		} else {
			// TODO: error! verificare se è davvero sempre così!
		}
	}

	public void create(String var, int length) {
		if (firstAddr <= ram.size() - arch * length) {
			variables.put(var, new int[]{firstAddr, arch * length});
			firstAddr += arch * length;
		} else {
			// TODO: error! verificare se è davvero sempre così!
		}
	}

	public void set(String var, int value) {
		int[] info = variables.get(var);
		boolean[] save = Binaries.convert(value, arch);
		if (info != null) {
			ram.write(info[0], save);
		} else {
			// TODO: error! sincronizzare size save con arch
		}
	}

	public void set(String var, int offset, int value) {
		int[] info = variables.get(var);
		boolean[] save = Binaries.convert(value, arch);
		if (info != null) {
			int addr = info[0] + offset * arch;
			if (addr < info[1]) {
				ram.write(addr, save);
			}
		} else {
			// TODO: error! sincronizzare size save con arch
		}
	}

	public int get(String var) {
		int[] info = variables.get(var);
		if (info != null) {
			boolean[] val = ram.read(info[0], arch);
			return Binaries.toInt(val);
		}
		// TODO: capire bene cosa fare se non legge
		return 0;
	}

	public int get(String var, int offset) {
		int[] info = variables.get(var);
		if (info != null) {
			boolean[] val = ram.read(info[0] + offset * arch, arch);
			return Binaries.toInt(val);
		}
		// TODO: capire bene cosa fare se non legge
		return 0;
	}

	/*public void createArray(String arrayName, int length) {
		if (length > 1) {
			ram.createVariables(arrayName, length);
		}
	}

	public void set(String varName, int value) {
		ram.writeVariable(varName, 0, Binaries.convert(value, architecture));
	}

	public void set(String varName, int addr, int value) {
		ram.writeVariable(varName, addr, Binaries.convert(value, architecture));
	}

	public int get(String varName) {
		return Binaries.toInt(ram.readVariable(varName));
	}*/

	public void printAll() {
		ram.print();
	}

	public void printVariable(String var) {
		int[] info = variables.get(var);
		if (info != null) {
			ram.print(info[0], info[1]);
		}
	}

}
