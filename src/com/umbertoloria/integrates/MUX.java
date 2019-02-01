package com.umbertoloria.integrates;

import com.umbertoloria.components.Computer;
import com.umbertoloria.graphics.Renderer;
import com.umbertoloria.bitting.Bit;
import com.umbertoloria.bitting.BitLink;
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
		BitLink.link(out, in[s.get() ? 1 : 0]);
	}

	public Bit[] get() {
		return out;
	}

	public void draw(Renderer r, boolean lastClocked) {
		if (lastClocked) {
			r.box(0, 0, 652, 76, Color.darkGray, true);
		}
		r.box(0, 0, 652, 76, Color.orange, false);
		int x = Renderer.bs;
		int oy = 10 - x;
		r.write("Control Bit", 10, (oy += x));
		r.drawBit(s, 130, oy);
		r.write("Input 0", 10, (oy += 2 * x));
		r.drawBits(in[0], 130, oy);
		r.write("Input 1", 10, (oy += 2 * x));
		r.drawBits(in[1], 130, oy);
		r.write("Out", 10, (oy += 2 * x));
		r.drawBits(out, 130, oy);
	}
}
