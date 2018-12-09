package com.umbertoloria;

import com.umbertoloria.bittings.Bit;

import java.awt.*;
import java.util.ArrayList;

public class BitGroup {

	private String name;
	private ArrayList<Bit> bits = new ArrayList<>();

	public BitGroup(String name) {
		this.name = name;
	}

	public void add(Bit bit) {
		bits.add(bit);
	}

	public void draw(Graphics g, int cols, int size) {
		g.setColor(Color.GRAY);
		g.drawString(name, 0, 0);
		g.translate(0, 10);
		for (int i = 0; i < bits.size(); i++) {
			g.setColor(bits.get(i).get() ? Color.RED : Color.BLACK);
			g.fillRect(size * (i % cols), size * (i / cols), size, size);
			g.setColor(Color.GRAY);
			g.drawRect(size * (i % cols), size * (i / cols), size, size);
		}
	}
}
