package com.umbertoloria.components;

import com.umbertoloria.bitting.Bit;
import com.umbertoloria.bitting.BitAlloc;
import com.umbertoloria.bitting.BitUse;
import com.umbertoloria.graphics.Renderer;
import com.umbertoloria.interfaces.Clockable;
import com.umbertoloria.utils.BinUtils;

import java.awt.*;

public class ProgramCounter implements Clockable {

	private Bit startExecution;
	private Bit jumpFlag;
	private Bit[] nextInstr;
	private Bit[] out;

	ProgramCounter() {
		startExecution = new Bit(true);
		out = BitAlloc.create("0000000000000000000000000000000000000000000000000000000000000000");
	}

	/**
	 Gets the Instruction Address to execute.
	 @return the instruction address
	 */
	public Bit[] get() {
		return out;
	}

	/**
	 After the execution of the instruction, it takes the next Instruction Address.
	 */
	public void clock() {
		// Al primo clock, non incrementare l'indirizzo
		if (startExecution.get()) {
			startExecution.disable();
		} else {
			// TODO: Inserire dell'hardware per procedurizzare queste operazioni!
			int newInstr;
			if (jumpFlag.get()) {
				// Imposta come prossimo indirizzo quello preso in input (normalizza l'indirizzo).
				newInstr = BinUtils.toAbsInt(BitUse.toBools(nextInstr)) * 3;
			} else {
				// Avanza linearmente indirizzo.
				newInstr = BinUtils.toAbsInt(BitUse.toBools(out)) + 3;
			}
			BitUse.set(out, BinUtils.convertAbs(newInstr, 64));
		}
	}

	public void clockBack() {

	}

	void setJumpFlag(Bit jumpFlag) {
		this.jumpFlag = jumpFlag;
	}

	void setNextInstr(Bit[] nextInstr) {
		this.nextInstr = nextInstr;
	}

	void draw(Renderer r, boolean lastClocked) {
		if (lastClocked) {
			r.box(0, 0, 652, 60, Color.darkGray, true);
		}
		r.box(0, 0, 652, 60, Color.red, false);
		int x = Renderer.bs;
		int oy = 10 - x;
		r.write("Start Execution Bit", 10, (oy += x));
		r.drawBit(startExecution, 130, oy);
		r.write("Next Instruction", 10, (oy += 2 * x));
		r.drawBits(nextInstr, 130, oy);
		r.write("Out", 10, (oy += 2 * x));
		r.drawBits(out, 130, oy);
	}

}
