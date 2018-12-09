package com.umbertoloria.integrates;

import com.umbertoloria.Computer;
import com.umbertoloria.Renderer;
import com.umbertoloria.bittings.Bit;
import com.umbertoloria.bittings.Bite;
import com.umbertoloria.interfaces.Clockable;

import java.awt.*;

public class MUX implements Clockable {

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

	public void draw(Renderer r, boolean lastClocked) {
		if (lastClocked) {
			r.box(0, 0, 780, 110, Color.darkGray, true);
		}
		r.box(0, 0, 780, 110, Color.blue, false);
		r.write("MUX", 10, 10, Color.gray);
		r.write("Control Bit", 10, 30, Color.gray);
		r.drawBit(s, 130, 30);
		r.write("Input 0", 10, 50, Color.gray);
		r.drawBits(in[0], 130, 50);
		r.write("Input 1", 10, 70, Color.gray);
		r.drawBits(in[1], 130, 70);
		r.write("Out", 10, 90, Color.gray);
		r.drawBits(out, 130, 90);
	}
}
