package com.umbertoloria.integrates;

import com.umbertoloria.bittings.Bit;

public class NOT {

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
