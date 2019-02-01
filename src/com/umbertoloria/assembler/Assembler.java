package com.umbertoloria.assembler;

import com.umbertoloria.utils.BinUtils;
import com.umbertoloria.utils.ICB;
import com.umbertoloria.utils.RegistersUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assembler {

	/**
	 Compiles an Assembly Source Code into an array of machine code instructions.
	 @param src is the assembly source code
	 @return the machine code instruction array
	 */
	public String[] compile(String src) {
		ICB.init();
		ArrayList<String> instructions = new ArrayList<>();
		Scanner in = new Scanner(src);
		String line;
		String app;
		try {
			while (in.hasNextLine()) {
				line = in.nextLine().trim();
				if (!line.isEmpty()) {
					app = compileInstruction(line);
					if (app != null) {
						instructions.add(app);
					}
				}
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			in.close();
			return null;
		}
		return instructions.toArray(new String[0]);
	}

	/**
	 Takes the Assembly Instructon and transforms it into a binary instruction.
	 @param ai is the Assembly Instruction
	 */
	private static String compileInstruction(String ai) {
		char[] c = ai.toCharArray();
		ArrayList<Object> tokens = new ArrayList<>();
		rec(c, 0, tokens);
		if (tokens.size() <= 1) {
			// Per elaborare un'istruzione, essa deve avere almeno 2 tokens.
			return null;
		}

		// Controllo semantico
		check(tokens);

		// Assemblaggio
		boolean[] op1Binaries, op2Binaries = null;
		op1Binaries = assembleInstructionComponent(tokens.get(1));
		if (tokens.size() >= 3) {
			op2Binaries = assembleInstructionComponent(tokens.get(2));
		}

		// Recupero l'Instruction Face
		String instrType = ((String) tokens.get(0)).toLowerCase();
		boolean[] instrFace;
		if (instrType.startsWith("got")) {
			instrFace = ICB.getInstrCode(instrType, true);
		} else if (tokens.size() == 2) {
			instrFace = ICB.getInstrCode(instrType, tokens.get(1) instanceof Integer);
		} else if (instrType.equals("set")) {
			instrFace = ICB.getInstrCode(instrType, tokens.get(2) instanceof Integer);
		} else if (tokens.size() == 3) {
			instrFace = ICB.getInstrCode(instrType, tokens.get(1) instanceof Integer,
					tokens.get(2) instanceof Integer);
		} else {
			System.out.println("Dimensioni istruzione non valide");
			return null;
		}

		// Costruzione binari
		StringBuilder sb = new StringBuilder();
		for (boolean b : instrFace) {
			sb.append(b ? '1' : '0');
		}
		for (boolean b : op1Binaries) {
			sb.append(b ? '1' : '0');
		}
		if (op2Binaries != null) {
			for (boolean b : op2Binaries) {
				sb.append(b ? '1' : '0');
			}
		}
		return sb.toString();
	}

	private static boolean[] assembleInstructionComponent(Object op) {
		// Se è una costante
		if (op instanceof Integer) {
			return BinUtils.convert((Integer) op, 64);
		}
		String ops = (String) op;
		if (ops.startsWith("@")) {
			return BinUtils.convert(Integer.parseInt(ops.substring(1)), 64);
		}
		// Dopo controllare anche con #
		else {
			// estendo in 64 bits l'indirizzo del registro
			return BinUtils.convertAbs(BinUtils.toAbsInt(RegistersUtils.getAddressOf((String) op)), 64);
		}
	}

	private static void check(List<Object> tokens) throws RuntimeException {
		if (tokens.get(0) instanceof String) {
			String instr = (String) tokens.get(0);
			if (instr.equals("set")) {
				if (tokens.get(1) instanceof Integer) {
					throw new RuntimeException("Istruzione SET: insensata");
				}
			}
			// oppure #
			else if (instr.startsWith("got")) {
				String instrAddress;
				if (instr.startsWith("goto")) {
					instrAddress = (String) tokens.get(1);
				} else {
					instrAddress = (String) tokens.get(2);
				}
				try {
					if (!instrAddress.startsWith("@")) {
						throw new NumberFormatException();
					}
					Integer.parseInt(instrAddress.substring(1));
				} catch (NumberFormatException e) {
					throw new RuntimeException("Istruzione GOTO: indirizzo malformato");
				}
			}
		} else {
			throw new RuntimeException("Comando non valido");
		}
	}

	/**
	 Takes all the tokens in the char-array from a given starting point to the end of the array.
	 @param c      is the char-array
	 @param x      is the starting point
	 @param tokens is the tokens list where the tokens will be saved
	 */
	private static void rec(char[] c, int x, List<Object> tokens) {
		if (x < c.length) {
			if (c[x] == ' ') {
				rec(c, x + 1, tokens);
			} else {
				if (c[x] == '/' && c[x + 1] == '/') {
					return;
				}
				int i;
				for (i = x + 1; i < c.length; i++) {
					if (c[i] == ' ') {
						break;
					}
				}
				String op = new String(c, x, i - x);
				try {
					tokens.add(Integer.parseInt(op));
				} catch (NumberFormatException e) {
					tokens.add(op);
				}
				rec(c, i + 1, tokens);
			}
		}
	}

}
