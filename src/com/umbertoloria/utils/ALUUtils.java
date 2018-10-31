package com.umbertoloria.utils;

public class ALUUtils {

	public static boolean[] AND = BinaryUtils.convertAbs(0, 4);
	public static boolean[] OR = BinaryUtils.convertAbs(1, 4);
	public static boolean[] NOT = BinaryUtils.convertAbs(2, 4);
	public static boolean[] ADD = BinaryUtils.convertAbs(3, 4);
	public static boolean[] SUB = BinaryUtils.convertAbs(4, 4);
	public static boolean[] GOTV = BinaryUtils.convertAbs(5, 4);
	public static boolean[] GOTF = BinaryUtils.convertAbs(6, 4);
	public static boolean[] EQA = BinaryUtils.convertAbs(7, 4);
	public static boolean[] DIFF = BinaryUtils.convertAbs(8, 4);
	public static boolean[] LOW = BinaryUtils.convertAbs(9, 4);
	public static boolean[] LOWEQ = BinaryUtils.convertAbs(10, 4);
	public static boolean[] GRE = BinaryUtils.convertAbs(11, 4);
	public static boolean[] GREEQ = BinaryUtils.convertAbs(12, 4);

	private static final boolean[][][] modesOrder = new boolean[][][]{
			{InstructionUtils.AND}, {InstructionUtils.OR}, {InstructionUtils.NOT},
			{InstructionUtils.ADD, InstructionUtils.LOAD, InstructionUtils.STORE}, {InstructionUtils.SUB},
			{InstructionUtils.GOTV}, {InstructionUtils.GOTF}, {InstructionUtils.EQA}, {InstructionUtils.DIFF},
			{InstructionUtils.LOW}, {InstructionUtils.LOWEQ}, {InstructionUtils.GRE}, {InstructionUtils.GREEQ}};
	// TODO: capire se serve -> private static int ALU_MODE_SIZE = 4;

	public static boolean[] getALUMode(boolean[] instr) {
		for (int i = 0; i < modesOrder.length; i++) {
			for (int j = 0; j < modesOrder[i].length; j++) {
				if (BitsUtils.equals(instr, modesOrder[i][j])) {
					return BinaryUtils.convertAbs(i, 4);
				}
			}
		}
		return null;
	}

}
