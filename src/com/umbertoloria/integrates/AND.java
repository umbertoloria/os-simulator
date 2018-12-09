package com.umbertoloria.integrates;

import com.umbertoloria.bittings.Bit;
import com.umbertoloria.interfaces.Clockable;

public class AND implements Clockable {

	private Bit[] in;
	private Bit out;

	public AND(int size) {
		in = new Bit[size];
	}

	public void set(int index, Bit val) {
		in[index] = val;
	}

	public void clock() {
		for (Bit b : in) {
			if (!b.get()) {
				out.disable();
				return;
			}
		}
		out.enable();
	}

	public Bit get() {
		return out;
	}

}
