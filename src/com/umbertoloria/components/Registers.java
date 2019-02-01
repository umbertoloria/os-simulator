package com.umbertoloria.components;

import com.umbertoloria.bitting.*;
import com.umbertoloria.graphics.Renderer;
import com.umbertoloria.interfaces.Clockable;
import com.umbertoloria.utils.RegistersUtils;

import java.awt.*;

class Registers implements Clockable {

	private Bit[] AR, LR, MR, CR, OR1, OR2;
	private Bit readFlag1, readFlag2, writeFlag;
	private Bit[] readReg1, readReg2, writeReg, writeData;
	private Bit[] readData1 = new Bit[64];
	private Bit[] readData2 = new Bit[64];
	private Bit[] zero;

	Registers() {
		AR = BitAlloc.create(Computer.ARCH, false);
		LR = BitAlloc.create(Computer.ARCH, false);
		MR = BitAlloc.create(Computer.ARCH, false);
		CR = BitAlloc.create(Computer.ARCH, false);
		OR1 = BitAlloc.create(Computer.ARCH, false);
		OR2 = BitAlloc.create(Computer.ARCH, false);
		zero = BitAlloc.create("0000000000000000000000000000000000000000000000000000000000000000");
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
	public void clock() {
		if (readFlag1.get()) {
			BitLink.link(readData1, getRegisterData(readReg1));
		} else {
			BitLink.link(readData1, zero);
		}
		if (readFlag2.get()) {
			BitLink.link(readData2, getRegisterData(readReg2));
		} else {
			BitLink.link(readData2, zero);
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
	public void clockBack() {
		if (writeFlag.get()) {
			BitUse.set(getRegisterData(writeReg), writeData);
		}
	}

	private Bit[] getRegisterData(Bit[] reg) {
		if (BitCheck.endsWith(reg, RegistersUtils.AR)) {
			return AR;
		} else if (BitCheck.endsWith(reg, RegistersUtils.LR)) {
			return LR;
		} else if (BitCheck.endsWith(reg, RegistersUtils.MR)) {
			return MR;
		} else if (BitCheck.endsWith(reg, RegistersUtils.CR)) {
			return CR;
		} else if (BitCheck.endsWith(reg, RegistersUtils.OR1)) {
			return OR1;
		} else if (BitCheck.endsWith(reg, RegistersUtils.OR2)) {
			return OR2;
		}
		throw new RuntimeException("Abbiamo un non-registro in writeReg");
	}

	private Bit[] getRegisterData(boolean[] reg) {
		if (reg == RegistersUtils.AR) {
			return AR;
		} else if (reg == RegistersUtils.LR) {
			return LR;
		} else if (reg == RegistersUtils.MR) {
			return MR;
		} else if (reg == RegistersUtils.CR) {
			return CR;
		} else if (reg == RegistersUtils.OR1) {
			return OR1;
		} else if (reg == RegistersUtils.OR2) {
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

	void draw(Renderer r, boolean lastClocked) {
		if (lastClocked) {
			r.box(0, 0, 652, 100, Color.darkGray, true);
		}
		r.box(0, 0, 652, 100, Color.magenta, false);
		int x = Renderer.bs;
		int oy = 10 - x;
		r.write("Arithmetic Register", 10, (oy += x));
		r.drawBits(AR, 130, oy);
		r.write("Logical Register", 10, (oy += x));
		r.drawBits(LR, 130, oy);
		r.write("Memory Register", 10, (oy += x));
		r.drawBits(MR, 130, oy);
		r.write("Condition Register", 10, (oy += x));
		r.drawBits(CR, 130, oy);
		r.write("Other Register 1", 10, (oy += x));
		r.drawBits(OR1, 130, oy);
		r.write("Other Register 2", 10, (oy += x));
		r.drawBits(OR2, 130, oy);

		r.write("Write Data", 10, (oy += 2 * x));
		r.drawBits(writeData, 130, oy);

		r.write("Read Register 1", 10, (oy += 2 * x));
		r.drawBits(readReg1, 110, oy);
		r.write("Read Register 2", 160, oy);
		r.drawBits(readReg2, 260, oy);
		r.write("Write Register", 310, oy);
		r.drawBits(writeReg, 410, oy);
	}

}
