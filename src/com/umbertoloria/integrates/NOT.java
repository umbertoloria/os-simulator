package com.umbertoloria.integrates;

import com.umbertoloria.bittings.Bit;
import com.umbertoloria.interfaces.Clockable;

public class NOT implements Clockable {

	private Bit in, out;

	public NOT(Bit in) {
		this.in = in;
	}

	public void clock() {
		out.set(!in.get());
	}

	public Bit get() {
		return out;
	}

}
