package com.umbertoloria;

import com.umbertoloria.bittings.Bit;
import com.umbertoloria.bittings.Bite;
import com.umbertoloria.interfaces.Clockable;
import com.umbertoloria.utils.BinUtils;
import com.umbertoloria.utils.InstructionUtils;

import java.awt.*;

public class Memory implements Clockable {

	private Bit[][] ram = new Bit[10][64];
	private Bit[] in;
	private Bit[] out = new Bit[InstructionUtils.MAX_INSTRUCTION_SIZE];

	Memory() {
		Bit.WATCH("RAM");
		for (int i = 0; i < ram.length; i++) {
			for (int j = 0; j < ram[i].length; j++) {
				ram[i][j] = new Bit();
			}
		}
		Bit.eWATCH();
	}

	public void set(Bit[] in) {
		this.in = in;
	}

	public void clock() {
		// Un giorno preleverò davvero dalla memoria istruzioni... :(
		int index = BinUtils.toAbsInt(Bite.toBools(in));
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
		Bite.linkSub(instrA, instr, 0, 64);
		Bite.linkSub(instrB, instr, 64, 64 * 2);
		Bite.linkSub(instrC, instr, 64 * 2, 64 * 3);
		// Carico l'istruzione spezzettata in memoria
		Bite.set(ram[numInstr], instrA);
		Bite.set(ram[numInstr + 1], instrB);
		Bite.set(ram[numInstr + 2], instrC);
	}

	void draw(Renderer r, boolean lastClocked) {
		if (lastClocked) {
			r.box(0, 0, 780, 180, Color.darkGray, true);
		}
		r.box(0, 0, 780, 180, Color.blue, false);
		r.write("Memory", 10, 10, Color.gray);
		double add;
		int magic;
		for (int i = 0; i < ram.length; i++) {
			add = (System.currentTimeMillis() % 2000) / 2000d;
			magic = (int) (Math.cos((add + i / 9f) * 2 * Math.PI) * 50);
			r.write(i + "", 10 + 50 + magic, 30 + i * 10, Color.gray);
			r.drawBits(ram[i], 130, 30 + i * 10);
		}
		r.write("In", 10, 30 + ram.length * 10 + 10, Color.gray);
		r.drawBits(in, 130, 30 + ram.length * 10 + 10);
		r.write("Out", 10, 30 + ram.length * 10 + 30, Color.gray);
		r.drawInstr(out, 130, 30 + ram.length * 10 + 30);
	}

}
