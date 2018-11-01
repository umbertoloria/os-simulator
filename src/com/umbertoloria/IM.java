package com.umbertoloria;

import com.umbertoloria.bittings.BitStream;
import com.umbertoloria.utils.BitsUtils;
import com.umbertoloria.utils.InstructionUtils;

public class IM {

	private boolean[] in = new boolean[InstructionUtils.MAX_INSTRUCTION_SIZE];
	private BitStream out = new BitStream(InstructionUtils.MAX_INSTRUCTION_SIZE);

	// TODO: Dopo prenderà un indirizzo di memoria anzicchè l'istruzione pronta.
	public void set(boolean[] addr) {
		BitsUtils.set(this.in, addr);
	}

	void clock() {
		// TODO: un giorno preverò davvero dalla memoria istruzioni... :(
		out.set(in);
	}

	BitStream get() {
		return out;
	}

}
