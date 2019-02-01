package com.umbertoloria.graphics;

import com.umbertoloria.bitting.Bit;

import java.awt.*;

public class Renderer {

	private Graphics g;
	public static final int bs = 8;
	private static final int mbs = 2;

	void attach(Graphics g) {
		this.g = g;
		g.setFont(new Font("Arial", Font.BOLD, 10));
	}

	public void write(String str, int ox, int oy) {
		g.setColor(Color.gray);
		g.drawString(str, ox, oy + g.getFont().getSize() - 3);
	}

	public void box(int ox, int oy, int width, int height, Color color, boolean filled) {
		g.setColor(color);
		if (filled) {
			g.fillRect(ox, oy, width, height);
		} else {
			g.drawRect(ox, oy, width, height);
		}
	}

	public void drawBit(Bit bit, int ox, int oy) {
		if (bit.get()) {
			box(ox, oy, bs, bs, Color.gray, true);
		}
		box(ox, oy, bs, bs, Color.gray, false);
	}

	public void drawBits(Bit[] bits, int ox, int oy) {
		if (bits[0] == null) {
			box(ox, oy, 64 * bs, bs, Color.gray, false);
		} else {
			for (int i = 0; i < bits.length; i++) {
				drawBit(bits[i], ox + i * bs, oy);
			}
		}
	}

	public void drawInstr(Bit[] instr, int ox, int oy) {
		if (instr[0] != null) {
			for (int i = 0; i < instr.length; i++) {
				if (instr[i].get()) {
					box(ox + i * mbs, oy, mbs, bs, Color.blue, true);
				}
			}
		}
		box(ox, oy, instr.length * mbs, bs, Color.gray, false);
	}

	public void translate(int tx, int ty) {
		g.translate(tx, ty);
	}
}
