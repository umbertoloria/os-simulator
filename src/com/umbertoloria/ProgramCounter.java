package com.umbertoloria;

import com.umbertoloria.bittings.Bit;
import com.umbertoloria.bittings.Bite;
import com.umbertoloria.interfaces.Clockable;
import com.umbertoloria.utils.BinUtils;

import java.awt.*;

public class ProgramCounter implements Clockable {

	private Bit startExecution;
	private Bit[] addr;
	private Bit[] out;

	ProgramCounter() {
		Bit.WATCH("Program Counter");
		startExecution = new Bit(true);
		addr = Bite.toBits("0000000000000000000000000000000000000000000000000000000000000000");
		Bit.eWATCH();
		out = addr;
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
			int index = BinUtils.toAbsInt(Bite.toBools(addr));
			Bite.set(addr, BinUtils.convertAbs(index + 3, 64));
		}
	}

	void draw(Renderer r, boolean lastClocked) {
		if (lastClocked) {
			r.box(0, 0, 780, 100, Color.darkGray, true);
		}
		r.box(0, 0, 780, 100, Color.RED, false);
		r.write("Program Counter", 10, 10, Color.GRAY);
		r.write("Start Execution Bit", 10, 40, Color.GRAY);
		r.drawBit(startExecution, 130, 40);
		r.write("Address", 10, 60, Color.GRAY);
		r.drawBits(addr, 130, 60);
		r.write("Out", 10, 80, Color.GRAY);
		r.drawBits(out, 130, 80);
	}

}
