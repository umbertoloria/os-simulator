package com.umbertoloria.utils;

import com.umbertoloria.program.Instruction;

import java.util.ArrayList;

public class ParserUtils {

	public static String getFirstAlphabeticToken(String str) {
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

	public static ArrayList<Instruction> instructionsExploding(StringBuilder src) {
		int from = 0;
		int istart = 0;
		int iend = 0;
		int index;
		ArrayList<Instruction> instructions = new ArrayList<>();
		while ((index = src.indexOf("\n", from)) >= 0) {
			if (src.charAt(index - 1) != '\\') {
				iend = index - 1;
				instructions.add(new Instruction(src.substring(istart, iend + 1)));
				istart = index + 1;
			}
			from = index + 1;
		}
		if (iend < src.length() - 2) {
			instructions.add(new Instruction(src.substring(istart, src.length())));
		}
		return instructions;
	}

}
