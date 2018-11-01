package com.umbertoloria;

import com.umbertoloria.bittings.Bit;
import com.umbertoloria.bittings.BitStream;
import com.umbertoloria.utils.RegistersUtils;

// TODO: togliere funzionalità di trasporto passivo dei dati!
public class Registers {

	private BitStream PC = new BitStream(Computer.ARCH); // = new boolean[Computer.ARCH];
	private BitStream AR = new BitStream(Computer.ARCH); // = new boolean[Computer.ARCH];
	private BitStream LR = new BitStream(Computer.ARCH); // = new boolean[Computer.ARCH];
	private BitStream MR = new BitStream(Computer.ARCH); // = new boolean[Computer.ARCH];
	private BitStream CR = new BitStream(Computer.ARCH); // = new boolean[Computer.ARCH];
	private BitStream OR1 = new BitStream(Computer.ARCH); // = new boolean[Computer.ARCH];
	private BitStream OR2 = new BitStream(Computer.ARCH); // = new boolean[Computer.ARCH];

	private Bit readFlag1;
	private BitStream readReg1;// = new boolean[Computer.ARCH];
	private BitStream readData1;

	private Bit readFlag2;
	private BitStream readReg2;// = new boolean[Computer.ARCH];
	private BitStream readData2;

	private Bit writeFlag;
	private BitStream writeReg;// = new boolean[RegistersUtils.REGISTERS_SIZE];
	private BitStream writeData;// = new boolean[Computer.ARCH];

	/**
	 Sets the First Register Read Flag.
	 @param set will be the new first register read flag
	 */
	void setReadFlag1(Bit set) {
		this.readFlag1 = set;
	}

	/**
	 Sets the Second Register Read Flag.
	 @param set will be the new second register read flag
	 */
	void setReadFlag2(Bit set) {
		this.readFlag2 = set;
	}

	/**
	 Sets the Register Write Flag.
	 @param set will be the new register write flag
	 */
	void setWriteFlag(Bit set) {
		this.writeFlag = set;
	}

	void setReadReg1(BitStream save) {
		if (RegistersUtils.isRegisterCode(save)) {
			//BitsUtils.setEnd(readReg1, save);
			readReg1 = save;
		} else {
			throw new RuntimeException("read reg 1 deve essere un reg");
		}
	}

	void setReadReg2(BitStream save) {
		if (RegistersUtils.isRegisterCode(save)) {
			//BitsUtils.setEnd(readReg2, save);
			readReg2 = save;
		} else {
			throw new RuntimeException("read reg 2 deve essere un reg");
		}
	}

	void setWriteReg(BitStream save) {
		if (RegistersUtils.isRegisterCode(save)) {
			//BitsUtils.set(writeReg, save);
			writeReg = save;
		} else {
			throw new RuntimeException("write reg deve essere un reg");
		}
	}

	void setWriteData(BitStream a) {
		if (a.size() == Computer.ARCH) {
			//BitsUtils.set(writeData, a);
			writeData = a;
		}
	}

	void clock() {
		BitStream save1 = readReg1;
		if (readFlag1.get()) {
			save1 = getRegisterData(save1);
		}
		//BitsUtils.set(readData1, save1);
		readData1 = save1;
		BitStream save2 = readReg2;
		if (readFlag2.get()) {
			save2 = getRegisterData(save2);
		}
		//BitsUtils.set(readData2, save2);
		readData2 = save2;
		if (readFlag1.get()) {
			readData1 = getRegisterData(readReg1);
		} else {
			readData1 = readReg1;
		}
		if (readFlag2.get()) {
			readData2 = getRegisterData(readReg2);
		} else {
			readData2 = readReg2;
		}
	}

	void clockBack() {
		if (writeFlag.get()) {
			// TODO: fonderli?
			//BitsUtils.set(getRegisterData(writeReg), writeData);
			getRegisterData(writeReg).set(writeData);
			System.out.println("Setting " + writeData + " on register " + RegistersUtils.getRegisterName(writeReg));
		}
	}

	BitStream getData1() {
		return readData1;//.clone();
	}

	BitStream getData2() {
		return readData2;//.clone();
	}

	private BitStream getRegisterData(BitStream reg) {
		if (reg.equals(RegistersUtils.PC_C)) {
			return PC;
		} else if (reg.equals(RegistersUtils.AR_C)) {
			return AR;
		} else if (reg.equals(RegistersUtils.LR_C)) {
			return LR;
		} else if (reg.equals(RegistersUtils.MR_C)) {
			return MR;
		} else if (reg.equals(RegistersUtils.CR_C)) {
			return CR;
		} else if (reg.equals(RegistersUtils.OR1_C)) {
			return OR1;
		} else if (reg.equals(RegistersUtils.OR2_C)) {
			return OR2;
		}
		throw new RuntimeException("Abbiamo un non-registro in writeReg");
	}

}
