package com.umbertoloria.utils;

import com.umbertoloria.bittings.BitStream;

public class ALUUtils {

	public static final int ALUMODE_SIZE = 4;
	public static final BitStream AND = new BitStream("0000");
	public static final BitStream OR = new BitStream("0001");
	public static final BitStream NOT = new BitStream("0010");
	public static final BitStream ADD = new BitStream("0011");
	public static final BitStream SUB = new BitStream("0100");
	public static final BitStream GOTV = new BitStream("0101");
	public static final BitStream GOTF = new BitStream("0110");
	public static final BitStream EQU = new BitStream("0111");
	public static final BitStream DIFF = new BitStream("1000");
	public static final BitStream LOW = new BitStream("1001");
	public static final BitStream LOWEQ = new BitStream("1010");
	public static final BitStream GRE = new BitStream("1011");
	public static final BitStream GREEQ = new BitStream("1100");
	public static final BitStream LOAD = ADD;
	public static final BitStream STORE = ADD;

	public static boolean isAluMode (BitStream mode) {
		return mode.size() == 4 && BinaryUtils.toAbsInt(mode.toArray()) <= 14;
	}

	/*private static final BitStream[][] modesOrder = new BitStream[][]{
			{InstructionUtils.AND},
			{InstructionUtils.OR},
			{InstructionUtils.NOT},
			{InstructionUtils.ADD, InstructionUtils.LOAD, InstructionUtils.STORE},
			{InstructionUtils.SUB},
			{InstructionUtils.GOTV},
			{InstructionUtils.GOTF},
			{InstructionUtils.EQU},
			{InstructionUtils.DIFF},
			{InstructionUtils.LOW},
			{InstructionUtils.LOWEQ},
			{InstructionUtils.GRE},
			{InstructionUtils.GREEQ}
	};
	// TODO: capire se serve -> private static int ALU_MODE_SIZE = 4;

	public static BitStream getALUMode(BitStream instr) {
		for (int i = 0; i < modesOrder.length; i++) {
			for (int j = 0; j < modesOrder[i].length; j++) {
				if (BitsUtils.equals(instr, modesOrder[i][j])) {
					return BinaryUtils.convertAbs(i, 4);
				}
			}
		}
		return null;
	}*/

}
