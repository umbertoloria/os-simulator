package com.umbertoloria;

import com.umbertoloria.bittings.Bit;

import java.awt.*;

public class Renderer {

	private Graphics g;

	void attach(Graphics g) {
		this.g = g;
	}

	public void write(String str, int ox, int oy, Color color) {
		g.setColor(color);
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
			box(ox, oy, 10, 10, Color.gray, true);
			/*g.setColor(Color.GREEN);
			g.fillRect(ox, oy, 10, 10);*/
		}
		box(ox, oy, 10, 10, Color.gray, false);
		/*g.setColor(Color.GRAY);
		g.drawRect(ox, oy, 10, 10);*/
	}

	public void drawBits(Bit[] bits, int ox, int oy) {
		if (bits[0] == null) {
			box(ox, oy, 640, 10, Color.gray, false);
		} else {
			for (int i = 0; i < bits.length; i++) {
				drawBit(bits[i], ox + i * 10, oy);
			}
		}
	}

	public void drawInstr(Bit[] instr, int ox, int oy) {
		if (instr[0] != null) {
			for (int i = 0; i < instr.length; i++) {
				if (instr[i].get()) {
					box(ox + i * 3, oy, 3, 10, Color.blue, true);
				}
			}
		}
		box(ox, oy, instr.length * 3, 10, Color.gray, false);
	}

	public void translate(int tx, int ty) {
		g.translate(tx, ty);
	}
}
