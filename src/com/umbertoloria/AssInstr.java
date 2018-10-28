package com.umbertoloria;

public class AssInstr {

	private static final int MR = 0;
	private static final int AR = 0;
	private static final int LR = 0;
	private static final int OR1 = 0;
	private static final int OR2 = 0;

	private String instr;
	private Assembler from;

	public AssInstr(String instr, Assembler from) {
		this.instr = instr;
		this.from = from;
	}

	public String getInstr() {
		return instr;
	}

	private void use(String first) {
		int size = Integer.parseInt(first);
		from.setSizeMemory(size);
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

	public void execute(boolean verboose) {
		String[] p = instr.split(" ");
		for (int i = 0; i < p.length; i++) {
			p[i] = p[i].trim();
		}
		String a = "", b = "";
		if (p.length >= 2) {
			a = p[1];
			b = "";
			if (p.length >= 3) {
				b = p[2];
				if (verboose) {
					System.out.println(p[0] + " " + p[1] + " " + p[2]);
				}
			} else {
				if (verboose) {
					System.out.println(p[0] + " " + p[1]);
				}
			}
		}

		if (p[0].equals("use")) {
			use(a);
		} else if (p[0].equals("set")) {
			set(a, b);
		} else if (p[0].equals("add")) {
			add(a, b);
		} else if (p[0].equals("sub")) {
			sub(a, b);
		} else if (p[0].equals("goto")) {
			fgoto(a);
		} else if (p[0].equals("gotv")) {
			fgotv(a, b);
		} else if (p[0].equals("gotf")) {
			fgotf(a, b);
		} else if (p[0].equals("low")) {
			low(a, b);
		} else if (p[0].equals("loweq")) {
			loweq(a, b);
		} else if (p[0].equals("gre")) {
			gre(a, b);
		} else if (p[0].equals("greeq")) {
			greeq(a, b);
		} else if (p[0].equals("load")) {
			load(a, b);
		} else if (p[0].equals("store")) {
			store(a, b);
		} else if (p[0].equals("print")) {
			print(a);
		} else if (p[0].equals("exit")) {
			from.exit();
		} else {
			System.err.println("Impossibile interpretare: " + instr);
		}
	}

	public int getRegisterOrConst(String token) throws RuntimeException {
		if (token.equals("MR")) {
			return from.getMR();
		} else if (token.equals("AR")) {
			return from.getAR();
		} else if (token.equals("LR")) {
			return from.getLR();
		} else if (token.equals("OR1")) {
			return from.getOR1();
		} else if (token.equals("OR2")) {
			return from.getOR2();
		}
		int res;
		try {
			res = Integer.parseInt(token);
		} catch (NumberFormatException e) {
			throw new RuntimeException("Lettera che non è nè numero nè registro.");
		}
		return res;
	}

	public void storeRegister(String reg, int val) throws RuntimeException {
		if (reg.equals("MR")) {
			from.setMR(val);
		} else if (reg.equals("AR")) {
			from.setAR(val);
		} else if (reg.equals("LR")) {
			from.setLR(val);
		} else if (reg.equals("OR1")) {
			from.setOR1(val);
		} else if (reg.equals("OR2")) {
			from.setOR2(val);
		} else {
			throw new RuntimeException("Registro incorretto: " + reg);
		}
	}

	public static int getInstruction(String token) throws RuntimeException {
		if (token.startsWith("@")) {
			try {
				return Integer.parseInt(token.substring(1));
			} catch (NumberFormatException e) {
				throw new RuntimeException("Indirizzo istruzione senza @");
			}
		}
		return 0;
	}

	public static int getAddress(String token) throws RuntimeException {
		if (token.startsWith("#")) {
			try {
				return Integer.parseInt(token.substring(1));
			} catch (NumberFormatException e) {
				throw new RuntimeException("Indirizzo RAM senza #");
			}
		}
		return 0;
	}

}
