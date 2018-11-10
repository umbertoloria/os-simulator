package com.umbertoloria;

import com.umbertoloria.bittings.Bit;
import com.umbertoloria.bittings.Bite;
import com.umbertoloria.utils.RegistersUtils;

class Registers {

	private Bit[] AR, LR, MR, CR, OR1, OR2;
	private Bit readFlag1, readFlag2, writeFlag;
	private Bit[] readReg1, readReg2, writeReg, writeData;
	private Bit[] readData1 = new Bit[64];
	private Bit[] readData2 = new Bit[64];

	Registers() {
		Bit.WATCH("Registri");
		AR = Bite.initSet(Computer.ARCH, false);
		LR = Bite.initSet(Computer.ARCH, false);
		MR = Bite.initSet(Computer.ARCH, false);
		CR = Bite.initSet(Computer.ARCH, false);
		OR1 = Bite.initSet(Computer.ARCH, false);
		OR2 = Bite.initSet(Computer.ARCH, false);
		Bit.eWATCH();
	}

	/**
	 Sets the First Read Register Flag.
	 @param set will be the new first read register flag
	 */
	void setReadFlag1(Bit set) {
		this.readFlag1 = set;
	}

	/**
	 Sets the Second Read Register Flag.
	 @param set will be the new second read register flag
	 */
	void setReadFlag2(Bit set) {
		this.readFlag2 = set;
	}

	/**
	 Sets the First Read Register Address.
	 @param save will be the new read register address
	 */
	void setReadReg1(Bit[] save) {
		readReg1 = save;
	}

	/**
	 Sets the Second Read Register Address.
	 @param save will be the new read register address
	 */
	void setReadReg2(Bit[] save) {
		readReg2 = save;
	}

	/**
	 Checks the Read Flags and sets the data from the Read Registers on the Read Data.
	 */
	void clock() {
		if (readFlag1.get()) {
			Bite.linkLeft(readData1, getRegisterData(readReg1));
		}
		if (readFlag2.get()) {
			Bite.linkLeft(readData2, getRegisterData(readReg2));
		}
	}

	/**
	 Gets the content of the First Read Register.
	 @return the first read register
	 */
	Bit[] getData1() {
		return readData1;
	}

	/**
	 Gets the content of the Second Read Register.
	 @return the second read register
	 */
	Bit[] getData2() {
		return readData2;
	}

	/**
	 Sets the Write Register Flag.
	 @param set will be the new write register flag
	 */
	void setWriteFlag(Bit set) {
		this.writeFlag = set;
	}

	/**
	 Sets the Write Register Address.
	 @param save will be the new write register address
	 */
	void setWriteReg(Bit[] save) {
		writeReg = save;
	}

	/**
	 Sets the Write Data that will be saved on the Write Register.
	 @param data will replace the data to write
	 */
	void setWriteData(Bit[] data) {
		if (data.length == Computer.ARCH) {
			writeData = data;
		}
	}

	/**
	 Checks the Write Flag and writes the content of the Write Data in the Write Register.
	 */
	void clockBack() {
		if (writeFlag.get()) {
			Bite.linkLeft(getRegisterData(writeReg), writeData);
		}
	}

	private Bit[] getRegisterData(Bit[] reg) {
		if (Bite.endsWith(reg, RegistersUtils.AR_C)) {
			return AR;
		} else if (Bite.endsWith(reg, RegistersUtils.LR_C)) {
			return LR;
		} else if (Bite.endsWith(reg, RegistersUtils.MR_C)) {
			return MR;
		} else if (Bite.endsWith(reg, RegistersUtils.CR_C)) {
			return CR;
		} else if (Bite.endsWith(reg, RegistersUtils.OR1_C)) {
			return OR1;
		} else if (Bite.endsWith(reg, RegistersUtils.OR2_C)) {
			return OR2;
		}
		throw new RuntimeException("Abbiamo un non-registro in writeReg");
	}

	private Bit[] getRegisterData(boolean[] reg) {
		if (reg == RegistersUtils.AR_C) {
			return AR;
		} else if (reg == RegistersUtils.LR_C) {
			return LR;
		} else if (reg == RegistersUtils.MR_C) {
			return MR;
		} else if (reg == RegistersUtils.CR_C) {
			return CR;
		} else if (reg == RegistersUtils.OR1_C) {
			return OR1;
		} else if (reg == RegistersUtils.OR2_C) {
			return OR2;
		}
		throw new RuntimeException("Abbiamo un non-registro in writeReg");
	}

	/**
	 Gets what's stored in a Register in a easy-to-read format.
	 @param reg we want to be told about
	 @param hex if true returns in 16 hex, otherwise in 64 bin.
	 @return the easy-to-read string
	 */
	String getRegContent(boolean[] reg, boolean hex) {
		Bit[] content = getRegisterData(reg);
		int val;
		StringBuilder sb = new StringBuilder();
		if (hex) {
			for (int i = 0; i < content.length / 4; i++) {
				val = 0;
				for (int j = 0; j < 4; j++) {
					if (content[i * 4 + j].get()) {
						val += Math.pow(2, 3 - j);
					}
				}
				sb.append(Integer.toHexString(val));
			}
		} else {
			for (Bit bit : content) {
				sb.append(bit.get() ? '1' : '0');
			}
		}
		return sb.toString();
	}

}
