package com.umbertoloria;

import com.umbertoloria.utils.BinaryUtils;

import java.util.ArrayList;

public class Compiler {

	private static final String[] instrOrder = new String[]{"use", "set", "and", "or", "not", "add", "sub", "goto",
			"gotv", "gotf", "eqa", "diff", "low", "loweq", "gre", "greeq", "load", "store", "print", "exit"};

	private static final String[] tipoALU = new String[]{instrOrder[2], instrOrder[3], instrOrder[5], instrOrder[6],
			instrOrder[10], instrOrder[11], instrOrder[12], instrOrder[13], instrOrder[14], instrOrder[15]};
	private static final String[] tipoGotC = new String[]{instrOrder[8], instrOrder[9]};
	private static final String[] tipoMem = new String[]{instrOrder[16], instrOrder[17]};

	private int arch;
	private ArrayList<boolean[]> instructions = new ArrayList<>();

	private boolean[] instruction;

	public Compiler(int arch) {
		this.arch = arch;
		this.instruction = new boolean[7 + 2 * arch];
	}


	public void add(String instr) {
		String[] p = instr.split(" ");
		String first = "", second = "";
		if (p.length >= 2) {
			first = p[1].trim();
			second = "";
			if (p.length >= 3) {
				second = p[2].trim();
			}
		}

		setInstr(p[0]);
		if (getPosOf(p[0], tipoALU) >= 0) {

			boolean[] a = new boolean[arch];
			boolean[] b = new boolean[arch];
			setRoC1(computeBitSection(first, a));
			setRoC2(computeBitSection(second, b));
			setA(a);
			setB(b);

		} else if (getPosOf(p[0], tipoGotC) >= 0) {

			setRoC1(true);
			setRoC2(false);
			setA(getRegister(first));
			setB(getInstruction(second));

		}

		push();
	}

	public void push() {
		BinaryUtils.printInstr(instruction);
	}

	public void setInstr(String name) {
		System.arraycopy(BinaryUtils.convertAbs(getPosOf(name, instrOrder), 5), 0, instruction, 0, 5);
	}

	public void setRoC1(boolean set) {
		instruction[5] = set;
	}

	public void setRoC2(boolean set) {
		instruction[6] = set;
	}

	public void setA(boolean[] set) {
		System.arraycopy(set, 0, instruction, 7, arch);
	}

	public void setB(boolean[] set) {
		System.arraycopy(set, 0, instruction, 12, arch);
	}

	public int getPosOf(String ago, String[] pagliaio) {
		for (int i = 0; i < pagliaio.length; i++) {
			if (ago.equals(pagliaio[i])) {
				return i;
			}
		}
		return -1;
	}

	public boolean computeBitSection(String data, boolean[] array) {
		boolean[] toput = getRegister(data);
		boolean reg = true;
		if (toput == null) {
			toput = BinaryUtils.convert(Integer.parseInt(data), arch);
			reg = false;
		}
		System.arraycopy(toput, 0, array, 0, arch);
		return reg;
	}

	public boolean[] getRegister(String token) throws RuntimeException {
		if (token.equals("PC")) {
			return BinaryUtils.addZeros(BinaryUtils.toStr(Registers.PC_CODE), arch);
		} else if (token.equals("AR")) {
			return BinaryUtils.addZeros(BinaryUtils.toStr(Registers.AR_CODE), arch);
		} else if (token.equals("LR")) {
			return BinaryUtils.addZeros(BinaryUtils.toStr(Registers.LR_CODE), arch);
		} else if (token.equals("MR")) {
			return BinaryUtils.addZeros(BinaryUtils.toStr(Registers.MR_CODE), arch);
		} else if (token.equals("CR")) {
			return BinaryUtils.addZeros(BinaryUtils.toStr(Registers.CR_CODE), arch);
		} else if (token.equals("OR1")) {
			return BinaryUtils.addZeros(BinaryUtils.toStr(Registers.OR1_CODE), arch);
		} else if (token.equals("OR2")) {
			return BinaryUtils.addZeros(BinaryUtils.toStr(Registers.OR2_CODE), arch);
		}
		return null;
	}

	public boolean[] getInstruction(String token) throws RuntimeException {
		if (token.startsWith("@")) {
			return BinaryUtils.convert(Integer.parseInt(token.substring(1)), arch);
		}
		return null;
	}









	/*private void use(String first) {
		boolean[] a = BinaryUtils.convert(Integer.parseInt(first), arch);
		boolean[] res = BinaryUtils.toRawBools("0000000" + BinaryUtils.toStr(a) + "00000");
		System.out.println(BinaryUtils.toStr(res));
		instrs.add(res);
	}

	private void set(String first, String second) {
		boolean[] a = getRegister(first);
		if (a == null) {
			System.err.println("Syntax error");
			System.exit(1);
		}
		boolean[] b = getRegister(second);
		String roc = "1";
		if (b == null) {
			roc = "0";
			b = BinaryUtils.convert(Integer.parseInt(second), arch);
		}
		String flag1 = "1";
		String flag2 = "1";
		boolean[] res = BinaryUtils.toRawBools("000011" + roc + BinaryUtils.toStr(a) + BinaryUtils.toStr(b));
		System.out.println(BinaryUtils.toStr(res));
		instrs.add(res);
	}

	private void set(String first, String second) {
		int val = getRegisterOrConst(second);
		storeRegister(first, val);
	}

	private void add(String first, String second) {
		int val1 = getRegisterOrConst(first);
		int val2 = getRegisterOrConst(second);
		storeRegister("AR", val1 + val2);
	}

	private void sub(String first, String second) {
		int val1 = getRegisterOrConst(first);
		int val2 = getRegisterOrConst(second);
		storeRegister("AR", val1 - val2);
	}

	private void fgoto(String first) {
		int newInstr = getInstruction(first);
		if (newInstr > 0) {
			from.setPC(newInstr);
		} else {
			System.err.println("indirizzo goto senza @");
		}
	}

	private void fgotv(String first, String second) {
		if (first.equals("CR")) {
			if (from.getCR() == 1) {
				int newInstr = getInstruction(second);
				if (newInstr > 0) {
					from.setPC(newInstr);
				} else {
					System.err.println("indirizzo gotv senza @");
				}
				from.setPC(newInstr);
			}
		}
	}

	private void fgotf(String first, String second) {
		if (first.equals("CR")) {
			if (from.getCR() == 0) {
				int newInstr = getInstruction(second);
				if (newInstr > 0) {
					from.setPC(newInstr);
				} else {
					System.err.println("indirizzo gotf senza @");
				}
				from.setPC(newInstr);
			}
		}
	}

	private void low(String first, String second) {
		int a = getRegisterOrConst(first);
		int b = getRegisterOrConst(second);
		from.setCR((a < b) ? 1 : 0);
	}

	private void loweq(String first, String second) {
		int a = getRegisterOrConst(first);
		int b = getRegisterOrConst(second);
		from.setCR((a <= b) ? 1 : 0);
	}

	private void gre(String first, String second) {
		int a = getRegisterOrConst(first);
		int b = getRegisterOrConst(second);
		from.setCR((a > b) ? 1 : 0);
	}

	private void greeq(String first, String second) {
		int a = getRegisterOrConst(first);
		int b = getRegisterOrConst(second);
		from.setCR((a >= b) ? 1 : 0);
	}

	private void load(String first, String second) {
		int addr = getAddress(first);
		int offset = getRegisterOrConst(second);
		from.setMR(from.getMemoryBlock(addr + offset));
	}

	private void store(String first, String second) {
		int addr = getAddress(first);
		int offset = Integer.parseInt(second);
		from.setMemoryBlock(addr + offset, from.getMR());
	}

	private void print(String first) {
		String output;
		System.err.print("OUTPUT: ");
		if (first.startsWith("\"") && first.endsWith("\"")) {
			output = first.substring(1, first.length() - 1);
			System.out.println(output);
		} else {
			System.out.println(getRegisterOrConst(first));
		}
	}
	 */

}
