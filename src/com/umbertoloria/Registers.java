package com.umbertoloria;

import com.umbertoloria.utils.BitsUtils;
import com.umbertoloria.utils.RegistersUtils;

public class Registers {

	private boolean[] PC = new boolean[Computer.ARCH];
	private boolean[] AR = new boolean[Computer.ARCH];
	private boolean[] LR = new boolean[Computer.ARCH];
	private boolean[] MR = new boolean[Computer.ARCH];
	private boolean[] CR = new boolean[Computer.ARCH];
	private boolean[] OR1 = new boolean[Computer.ARCH];
	private boolean[] OR2 = new boolean[Computer.ARCH];

	private boolean readFlag1;
	private boolean[] readReg1 = new boolean[Computer.ARCH];
	private boolean[] readData1;

	private boolean readFlag2;
	private boolean[] readReg2 = new boolean[Computer.ARCH];
	private boolean[] readData2;

	private boolean writeFlag;
	private boolean[] writeReg = new boolean[RegistersUtils.REGISTERS_SIZE];
	private boolean[] writeData = new boolean[Computer.ARCH];

	void setReadFlag1(boolean set) {
		this.readFlag1 = set;
	}

	void setReadFlag2(boolean set) {
		this.readFlag2 = set;
	}

	void setWriteFlag(boolean writeFlag) {
		this.writeFlag = writeFlag;
	}

	void setReadReg1(boolean[] save) {
		BitsUtils.setEnd(readReg1, save);
	}

	void setReadReg2(boolean[] save) {
		BitsUtils.setEnd(readReg2, save);
	}

	void setWriteReg(boolean[] save) {
		if (RegistersUtils.isRegisterCode(save)) {
			BitsUtils.set(writeReg, save);
		} else {
			throw new RuntimeException("write reg deve essere un reg");
		}
	}

	void setWriteData(boolean[] a) {
		if (a.length == Computer.ARCH) {
			BitsUtils.set(writeData, a);
		}
	}

	void clock() {
		/*boolean[] save1 = readReg1;
		if (readFlag1) {
			save1 = getRegisterData(save1);
		}
		BitsUtils.set(readData1, save1);
		boolean[] save2 = readReg2;
		if (readFlag2) {
			save2 = getRegisterData(save2);
		}
		BitsUtils.set(readData2, save2);*/
		if (readFlag1) {
			readData1 = getRegisterData(readReg1);
		} else {
			readData1 = readReg1;
		}
		if (readFlag2) {
			readData2 = getRegisterData(readReg2);
		} else {
			readData2 = readReg2;
		}
	}

	void clockBack() {
		if (writeFlag) {
			BitsUtils.set(getRegisterData(writeReg), writeData);
		}
	}

	boolean[] getData1() {
		return readData1.clone();
	}

	boolean[] getData2() {
		return readData2.clone();
	}

	private boolean[] getRegisterData(boolean[] reg) {
		if (BitsUtils.endsWith(reg, RegistersUtils.PC_CODE)) {
			return PC;
		} else if (BitsUtils.endsWith(reg, RegistersUtils.AR_CODE)) {
			return AR;
		} else if (BitsUtils.endsWith(reg, RegistersUtils.LR_CODE)) {
			return LR;
		} else if (BitsUtils.endsWith(reg, RegistersUtils.MR_CODE)) {
			return MR;
		} else if (BitsUtils.endsWith(reg, RegistersUtils.CR_CODE)) {
			return CR;
		} else if (BitsUtils.endsWith(reg, RegistersUtils.OR1_CODE)) {
			return OR1;
		} else if (BitsUtils.endsWith(reg, RegistersUtils.OR2_CODE)) {
			return OR2;
		}
		throw new RuntimeException("Abbiamo un non-registro in writeReg");
	}

}
