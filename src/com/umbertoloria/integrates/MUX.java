package com.umbertoloria.integrates;

import com.umbertoloria.bittings.Bit;
import com.umbertoloria.bittings.BitStream;

public class MUX {

	private BitStream[] in = new BitStream[2];
	//private boolean[] s;
	private Bit s;
	private BitStream out;

	public MUX (Bit s) {
		this.s = s;
	}

	public void set(int index, BitStream set) {
		in[index] = set;
	}

	public void clock() {
		out = in[s.get() ? 1 : 0];
	}

	public BitStream get() {
		return out;
	}

}
