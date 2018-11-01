package com.umbertoloria.integrates;

import com.umbertoloria.bittings.Bit;

public class OR {

	private Bit[] in;
	private Bit out;

	public OR(int size) {
		in = new Bit[size];
	}

	public void set(int index, Bit val) {
		in[index] = val;
	}

	public Bit get() {
		return out;
	}

	/* TODO: maybe it's useful?
	public void reset () {

	}*/

	public int size() {
		return in.length;
	}

	public void clock() {
		for (Bit b : in) {
			if (b.get()) {
				out.enable();
				return;
			}
		}
		out.disable();
	}

}
