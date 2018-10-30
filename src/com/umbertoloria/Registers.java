package com.umbertoloria;

import com.umbertoloria.utils.BinaryUtils;
import com.umbertoloria.utils.BitsUtils;
import com.umbertoloria.utils.RegistersUtils;

public class Registers {

	private boolean[] readReg1, readData1;
	private boolean[] readReg2, readData2;
	private boolean[] writeReg, writeData;
	private boolean[] PC, AR, LR, MR, CR, OR1, OR2;
	private boolean readFlag1, readFlag2, writeFlag;

	public Registers() {
		PC = new boolean[Computer.ARCH];
		AR = new boolean[Computer.ARCH];
		LR = new boolean[Computer.ARCH];
		MR = new boolean[Computer.ARCH];
		CR = new boolean[Computer.ARCH];
		OR1 = new boolean[Computer.ARCH];
		OR2 = new boolean[Computer.ARCH];
		readReg1 = new boolean[Computer.ARCH];
		readReg2 = new boolean[Computer.ARCH];
		writeReg = new boolean[RegistersUtils.REGISTERS_SIZE];
		readData1 = new boolean[Computer.ARCH];
		readData2 = new boolean[Computer.ARCH];
		writeData = new boolean[Computer.ARCH];
	}

	public void setReadFlag1(boolean set) {
		this.readFlag1 = set;
	}

	public void setReadFlag2(boolean set) {
		this.readFlag2 = set;
	}

	public void setWriteFlag(boolean writeFlag) {
		this.writeFlag = writeFlag;
	}

	public void setReadReg1(boolean[] save) {
		BitsUtils.setEnd(readReg1, save);
	}

	public void setReadReg2(boolean[] save) {
		BitsUtils.setEnd(readReg2, save);
	}

	public void setWriteReg(boolean[] save) {
		if (RegistersUtils.isRegisterCode(save)) {
			BitsUtils.set(writeReg, save);
		} else {
			throw new RuntimeException("write reg deve essere un reg");
		}
	}

	public void setWriteData(boolean[] a) {
		if (a.length == Computer.ARCH) {
			BitsUtils.set(writeData, a);
		}
	}

	public void clock() {
		boolean[] save1 = readReg1;
		if (readFlag1) {
			save1 = getRegisterData(save1);
		}
		BitsUtils.set(readData1, save1);
		boolean[] save2 = readReg2;
		if (readFlag2) {
			save2 = getRegisterData(save2);
		}
		BitsUtils.set(readData2, save2);
	}

	public void clockBack() {
		if (writeFlag) {
			BitsUtils.set(getRegisterData(writeReg), writeData);
			/*System.out.println("scrivendo ");
			BitsUtils.print(writeData);
			System.out.println("in un registro che non so quale");
			BitsUtils.print(writeReg);
			System.out.println("questo penso");
			System.err.println(BinaryUtils.toInt(writeData));*/
		}
	}

	public boolean[] getData1() {
		return readData1.clone();
	}

	public boolean[] getData2() {
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
