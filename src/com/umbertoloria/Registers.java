package com.umbertoloria;

import com.umbertoloria.utils.BinaryUtils;

public class Registers {

	public static final boolean[] PC_CODE = BinaryUtils.toRawBools("000");
	public static final boolean[] AR_CODE = BinaryUtils.toRawBools("001");
	public static final boolean[] LR_CODE = BinaryUtils.toRawBools("010");
	public static final boolean[] MR_CODE = BinaryUtils.toRawBools("011");
	public static final boolean[] CR_CODE = BinaryUtils.toRawBools("100");
	public static final boolean[] OR1_CODE = BinaryUtils.toRawBools("101");
	public static final boolean[] OR2_CODE = BinaryUtils.toRawBools("110");

	private boolean[] readReg1, readData1;
	private boolean[] readReg2, readData2;
	private boolean[] writeReg, writeData;
	private boolean[] PC, AR, LR, MR, CR, OR1, OR2;
	private boolean readFlag1, readFlag2, writeFlag;
	private int arch;

	public Registers(int arch) {
		this.arch = arch;
		PC = new boolean[arch];
		AR = new boolean[arch];
		LR = new boolean[arch];
		MR = new boolean[arch];
		CR = new boolean[arch];
		OR1 = new boolean[arch];
		OR2 = new boolean[arch];
		readReg1 = new boolean[arch];
		readReg2 = new boolean[arch];
		writeReg = new boolean[arch];
		readData1 = new boolean[arch];
		readData2 = new boolean[arch];
		writeData = new boolean[arch];
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

	public void setReadReg1(boolean[] a) {
		System.arraycopy(a, 0, readReg1, 0, arch);
	}

	public void setReadReg2(boolean[] a) {
		System.arraycopy(a, 0, readReg2, 0, arch);
	}

	public void setWriteReg(boolean[] a) {
		if (isRegisterCode(a)) {
			System.arraycopy(a, 0, writeReg, arch - a.length, a.length);
		}
	}

	public void setWriteData(boolean[] a) {
		if (a.length == arch) {
			System.arraycopy(a, 0, writeData, 0, arch);
		}
	}

	public void clock() {
		if (readFlag1) {
			setReference(readReg1, readData1);
		} else {
			System.out.println(BinaryUtils.toStr(readReg1) + " to data");
			System.arraycopy(readReg1, 0, readData1, 0, arch);
		}
		if (readFlag2) {
			setReference(readReg2, readData2);
		} else {
			System.out.println(BinaryUtils.toStr(readReg2) + " to data");
			System.arraycopy(readReg2, 0, readData2, 0, arch);
		}
	}

	public void clockBack() {
		if (writeFlag) {
			boolean[] pointer = null;
			if (endsWith(writeReg, PC_CODE)) {
				pointer = PC;
			} else if (endsWith(writeReg, AR_CODE)) {
				pointer = AR;
			} else if (endsWith(writeReg, LR_CODE)) {
				pointer = LR;
			} else if (endsWith(writeReg, MR_CODE)) {
				pointer = MR;
			} else if (endsWith(writeReg, CR_CODE)) {
				pointer = CR;
			} else if (endsWith(writeReg, OR1_CODE)) {
				pointer = OR1;
			} else if (endsWith(writeReg, OR2_CODE)) {
				pointer = OR2;
			} else {
				System.out.println("registro sconosciuto: ");
				for (boolean b : writeReg) {
					System.out.println(b);
				}
				System.out.println();
			}
			System.out.println("nuovo valore");
			System.arraycopy(writeData, 0, pointer, 0, arch);
		}
	}

	public boolean[] getData1() {
		return readData1.clone();
	}

	public boolean[] getData2() {
		return readData2.clone();
	}

	private void setReference(boolean[] type, boolean[] pointer) {
		if (endsWith(type, PC_CODE)) {
			System.arraycopy(PC, 0, pointer, 0, arch);
		} else if (endsWith(type, AR_CODE)) {
			System.arraycopy(AR, 0, pointer, 0, arch);
		} else if (endsWith(type, LR_CODE)) {
			System.arraycopy(LR, 0, pointer, 0, arch);
		} else if (endsWith(type, MR_CODE)) {
			System.arraycopy(MR, 0, pointer, 0, arch);
		} else if (endsWith(type, CR_CODE)) {
			System.arraycopy(CR, 0, pointer, 0, arch);
		} else if (endsWith(type, OR1_CODE)) {
			System.arraycopy(OR2, 0, pointer, 0, arch);
		} else if (endsWith(type, OR2_CODE)) {
			System.arraycopy(OR2, 0, pointer, 0, arch);
		}
	}

	private static boolean isRegisterCode(boolean[] a) {
		return endsWith(a, PC_CODE) || endsWith(a, AR_CODE) || endsWith(a, LR_CODE) || endsWith(a, MR_CODE) ||
				endsWith(a, CR_CODE) || endsWith(a, OR1_CODE) || endsWith(a, OR2_CODE);
	}

	public static boolean endsWith(boolean[] a, boolean[] check) {
		int offset = a.length - check.length;
		int i;
		for (i = 0; i < check.length; i++) {
			if (a[offset + i] != check[i]) {
				break;
			}
		}
		return i >= check.length;
	}

}
