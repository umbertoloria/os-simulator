package com.umbertoloria.integrates;

import com.umbertoloria.Computer;
import com.umbertoloria.bittings.Bit;
import com.umbertoloria.bittings.Bite;

public class MUX {

	private Bit[][] in = new Bit[2][Computer.ARCH];
	private Bit s;
	private Bit[] out = new Bit[Computer.ARCH];

	public void setS(Bit s) {
		this.s = s;
	}

	public void set(int index, Bit[] set) {
		in[index] = set;
	}

	public void clock() {
		//out = in[s.get() ? 1 : 0];
		Bite.linkLeft(out, in[s.get() ? 1 : 0]);
	}

	public Bit[] get() {
		return out;
	}

}
