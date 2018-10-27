package com.umbertoloria.ram;

import com.umbertoloria.utils.BinaryUtils;

import java.util.Hashtable;

public class RAMDriver {

	private int arch;
	private RAM ram;
	private Hashtable<String, int[]> variables = new Hashtable<>();
	private int firstAddr = 0;

	public RAMDriver(int arch, int blocks) {
		this.arch = arch;
		ram = new RAM(arch * blocks);
	}

	public void create(String var) {
		if (firstAddr <= ram.size() - arch) {
			variables.put(var, new int[]{firstAddr, arch});
			firstAddr += arch;
		} else {
			// FIXME: error! verificare se è davvero sempre così!
		}
	}

	public void create(String var, int length) {
		if (firstAddr <= ram.size() - arch * length) {
			variables.put(var, new int[]{firstAddr, arch * length});
			firstAddr += arch * length;
		} else {
			// FIXME: error! verificare se è davvero sempre così!
		}
	}

	public void set(String var, int value) {
		int[] info = variables.get(var);
		boolean[] save = BinaryUtils.convert(value, arch);
		if (info != null) {
			ram.write(info[0], save);
		} else {
			// FIXME: error! sincronizzare size save con arch
		}
	}

	public void set(String var, int offset, int value) {
		int[] info = variables.get(var);
		boolean[] save = BinaryUtils.convert(value, arch);
		if (info != null) {
			int addr = info[0] + offset * arch;
			if (addr < info[1]) {
				ram.write(addr, save);
			}
		} else {
			// FIXME: error! sincronizzare size save con arch
		}
	}

	public int get(String var) {
		int[] info = variables.get(var);
		if (info != null) {
			boolean[] val = ram.read(info[0], arch);
			return BinaryUtils.toInt(val);
		}
		// FIXME: capire bene cosa fare se non legge
		return 0;
	}

	public int get(String var, int offset) {
		int[] info = variables.get(var);
		if (info != null) {
			boolean[] val = ram.read(info[0] + offset * arch, arch);
			return BinaryUtils.toInt(val);
		}
		// FIXME: capire bene cosa fare se non legge
		return 0;
	}

}
