package com.umbertoloria.bittings;

import com.umbertoloria.BitGroup;

import java.util.ArrayList;

public class Bit {

	public static ArrayList<BitGroup> groups = new ArrayList<>();
	//private static LinkedList<String> scopeNames = new LinkedList<>();
	private static int instructions = 0;
	private static boolean watching = false;
	private static boolean output = false;

	private static void newBit(Bit bit) {
		/*numNews[instructions]++;
		Main.w.newBit(bit, instructions);
		if (output) {
			System.out.println("new Bit");
		}*/
		/*numNews.get(instructions).add(bit);
		//Main.w.newBit(bit, instructions);
		if (output) {
			System.out.println("new Bit");
		}*/
		if (watching) {
			groups.get(groups.size() - 1).add(bit);
		}
	}

	public static void WATCH(String name) {
		/*if (instructions == 0 || watching) {
			if (instructions == 0) {
				//scopeNames[instructions] = "Init";
				if (instructions < scopeNames.size()) {
					scopeNames.set(instructions, "Init");
				} else {
					scopeNames.add(instructions, "Init");
				}
			}
			eWATCH();
		}
		//scopeNames[instructions] = name;
		//scopeNames.set(instructions, name);*/
		watching = true;
		groups.add(new BitGroup(name));
	}

	public static void eWATCH() {
		watching = false;
		//System.err.printf("%4d bits created on [ %-33s ]\n", numNews[instructions], scopeNames[instructions]);
/*		System.err.printf("%4d bits created on [ %-33s ]\n", numNews.get(instructions).size(),
				scopeNames.get(instructions));*/
		//instructions++;
	}

	public static void enableOutput() {
		output = true;
	}

	private boolean val;

	public Bit() {
		this.val = false;
		newBit(this);
	}

	public Bit(boolean val) {
		this.val = val;
		newBit(this);
	}

	public void set(boolean val) {
		this.val = val;
	}

	public void enable() {
		val = true;
	}

	public void disable() {
		val = false;
	}

	public boolean get() {
		return val;
	}

	public boolean equals(Bit b) {
		return get() == b.get();
	}

	public static Bit[] array(String str) {
		Bit[] bits = new Bit[str.length()];
		for (int i = 0; i < bits.length; i++) {
			bits[i] = new Bit(str.charAt(i) == '1');
		}
		return bits;
	}

	public String toString() {
		return val ? "1" : "0";
	}

}
