package com.umbertoloria.utils;

import com.umbertoloria.program.Instruction;

import java.util.ArrayList;

/**
 * Le stringhe che si passato ai getTokens non devono avere spazi a sinistra.
 * // TODO: check se anche a destra (penso di no).
 */
public class ParserUtils {

	public static String getFirstAlphanumericToken(String str) {
		int i;
		char tmp;
		for (i = 0; i < str.length(); i++) {
			tmp = str.charAt(i);
			if ('0' > tmp || tmp > '9') {
				if ('A' > tmp || tmp > 'Z') {
					if ('a' > tmp || tmp > 'z') {
						break;
					}
				}
			}
		}
		return str.substring(0, i);
	}

	public static String getFirstAlphabeticToken(String str) {
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
		return str.substring(0, i);
	}

	public static String getFirstVariableToken(String str) {
		if (str.charAt(0) != '@') {
			return "";
		}
		String restName = getFirstAlphabeticToken(str.substring(1));
		if (restName.isEmpty()) {
			return "";
		}
		return "@"+restName;
	}

	public static String getFirstNumericToken(String str) {
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
		return str.substring(0, i);
	}

	public static ArrayList<Instruction> getExplodedInstructions(StringBuilder src) {
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
	}

}
