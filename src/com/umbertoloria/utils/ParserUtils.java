package com.umbertoloria.utils;

import java.util.ArrayList;

public class ParserUtils {

	private static String getFirstAlphabeticToken(String str) throws Exception {
		int i;
		char tmp;
		for (i = 0; i < str.length(); i++) {
			tmp = str.charAt(i);
			if ('A' > tmp || tmp > 'Z') {
				if ('a' > tmp || tmp > 'z') {
					break;
				}
			}
		}
		if (i == 0) {
			throw new Exception("Parola non valida");
		}
		return str.substring(0, i);
	}

	public static String getFirstVariableToken(String str) throws Exception {
		if (str.charAt(0) != '@') {
			throw new Exception("@ non presente");
		}
		try {
			return "@" + getFirstAlphabeticToken(str.substring(1));
		} catch (Exception e) {
			throw new Exception("Nome variabile vuoto");
		}
	}

	public static int getFirstNumericToken(String str) throws Exception {
		int i;
		char tmp;
		for (i = 0; i < str.length(); i++) {
			tmp = str.charAt(i);
			if ('0' > tmp || tmp > '9') {
				if (tmp != '-') {
					break;
				}
			}
		}
		try {
			return Integer.parseInt(str.substring(0, i));
		} catch (NumberFormatException e) {
			throw new Exception("Intero non valido");
		}
	}

	/*public static ArrayList<Instruction> getExplodedInstructions(StringBuilder src) {
		int from = 0;
		int istart = 0;
		int iend = 0;
		int index;
		ArrayList<Instruction> instructions = new ArrayList<>();
		String instruction_string;
		while ((index = src.indexOf("\n", from)) >= 0) {
			if (src.charAt(index - 1) != '\\') {
				iend = index - 1;
				instruction_string = src.substring(istart, iend + 1);
				if (!instruction_string.isEmpty()) {
					instructions.add(Instruction.load(instruction_string));
				}
				istart = index + 1;
			}
			from = index + 1;
		}
		if (iend < src.length() - 2) {
			instructions.add(Instruction.load(src.substring(istart, src.length())));
		}
		return instructions;
	}*/

}
