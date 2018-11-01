package com.umbertoloria;

import com.umbertoloria.utils.BitsUtils;
import com.umbertoloria.utils.InstructionUtils;

public class IM {

	boolean[] instr = new boolean[InstructionUtils.MAX_INSTRUCTION_SIZE];
	// boolean[] op1, op2; FIXME: definire bene le cose.

	// TODO: Dopo prenderà un indirizzo di memoria anzicchè l'istruzione pronta.
	public void set(boolean[] instr) {
		BitsUtils.set(this.instr, instr);
	}

	void clock () {
		// TODO: un giorno preverò davvero dalla memoria istruzioni... :(
	}

	boolean[] getInstr() {
		return instr.clone(); // TODO: verificare se clonare o no.
	}

}
