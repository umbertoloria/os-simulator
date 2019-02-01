package com.umbertoloria.components;

import com.umbertoloria.bitting.Bit;
import com.umbertoloria.bitting.BitAlloc;
import com.umbertoloria.bitting.BitLink;
import com.umbertoloria.bitting.BitUse;
import com.umbertoloria.graphics.Renderer;
import com.umbertoloria.interfaces.Clockable;
import com.umbertoloria.utils.BinUtils;
import com.umbertoloria.utils.ICB;

import java.awt.*;

public class Memory implements Clockable {

	private Bit[][] ram = new Bit[48][64];
	private Bit[] in;
	private Bit[] out = new Bit[64 * 3];

	Memory() {
		for (int i = 0; i < ram.length; i++) {
			ram[i] = BitAlloc.create(ram[i].length, false);
		}
	}

	public void set(Bit[] in) {
		this.in = in;
	}

	public void clock() {
		// Un giorno preleverò davvero dalla memoria istruzioni... :(
		int index = BinUtils.toAbsInt(BitUse.toBools(in));
		// OUT diventa un riferimento all'istruzione in memoria
		System.arraycopy(ram[index], 0, out, 0, 64);
		System.arraycopy(ram[index + 1], 0, out, 64, 64);
		System.arraycopy(ram[index + 2], 0, out, 128, 64);
	}

	/**
	 Scrittura variabili da registri o costanti...
	 */
	public void clockBack() {
	}

	Bit[] get() {
		return out;
	}

	void addInstr(Bit[] instr, int numInstr) {
		// Creo tre sequenze di 64 bit
		Bit[] instrA = new Bit[64];
		Bit[] instrB = new Bit[64];
		Bit[] instrC = new Bit[64];
		// Suddivido l'istruzione data nelle tre sequenze
		BitLink.linkSub(instrA, instr, 0, 64);
		BitLink.linkSub(instrB, instr, 64, 64 * 2);
		BitLink.linkSub(instrC, instr, 64 * 2, 64 * 3);
		// Carico l'istruzione spezzettata in memoria
		BitUse.set(ram[numInstr], instrA);
		BitUse.set(ram[numInstr + 1], instrB);
		BitUse.set(ram[numInstr + 2], instrC);
	}

	void draw(Renderer r, boolean lastClocked) {
		int x = Renderer.bs;
		if (lastClocked) {
			r.box(0, 0, 652, (4 + ram.length) * x + 20, Color.darkGray, true);
		}
		r.box(0, 0, 652, (4 + ram.length) * x + 20, Color.blue, false);
		double add = (System.currentTimeMillis() % 2000) / 2000d;
		int magic;

		int oy = 10;

		for (int i = 0; i < ram.length; i++) {
			magic = (int) (Math.cos((add + i / 9f) * 2 * Math.PI) * 50);
			r.write(i + "", 10 + 50 + magic, oy);
			r.drawBits(ram[i], 130, oy);
			oy += x;
		}
		r.write("In", 10, (oy += x));
		r.drawBits(in, 130, oy);
		r.write("Out", 10, (oy += 2 * x));
		r.drawInstr(out, 130, oy);
	}

}
