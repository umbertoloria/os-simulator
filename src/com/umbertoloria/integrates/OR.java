package com.umbertoloria.integrates;

public class OR {

	private boolean[] in;
	private boolean out;

	public OR(int size) {
		in = new boolean[size];
	}

	public void set(int index, boolean val) {
		in[index] = val;
	}

	public boolean get() {
		return out;
	}

	/* TODO: maybe it's useful?
	public void reset () {

	}*/

	public int size() {
		return in.length;
	}

	public void clock() {
		for (boolean b : in) {
			if (b) {
				out = true;
				return;
			}
		}
		out = false;
	}

}
