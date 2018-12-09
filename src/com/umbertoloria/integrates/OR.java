package com.umbertoloria.integrates;

import com.umbertoloria.bittings.Bit;
import com.umbertoloria.interfaces.Clockable;

public class OR implements Clockable {

	private Bit[] in;
	private Bit out;

	public OR(int size) {
		in = new Bit[size];
		Bit.WATCH("OR");
		out = new Bit();
		Bit.eWATCH();
	}

	public void set(int index, Bit val) {
		in[index] = val;
	}

	/*public int size() {
		return in.length;
	}*/

	public void clock() {
		for (Bit b : in) {
			if (b.get()) {
				out.enable();
				return;
			}
		}
		out.disable();
	}

	public Bit get() {
		return out;
	}

}
