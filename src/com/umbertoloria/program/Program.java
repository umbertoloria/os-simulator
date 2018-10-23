package com.umbertoloria.program;

import java.util.LinkedList;
import java.util.List;

public class Program {

	private StringBuilder src = new StringBuilder();
	private boolean editing = true;
	private List<String> ins = new LinkedList<>();
	private int pc = 0;

	public void addCompleteSource(String program) {
		if (editing) {
			program = program.replaceAll("\n", "");
			program = program.replaceAll("\r", "");
			src.append(program);
			editing = false;
		}
	}

	public void addInstr(String instr) {
		if (editing) {
			src.append(instr);
		}
	}

	public boolean parse() {
		editing = false;

		int from = 0;
		int istart = 0;
		int iend = 0;
		int index;

		while ((index = src.indexOf(";", from)) >= 0) {
			if (src.charAt(index - 1) != '\\') {
				iend = index - 1;
				ins.add(src.substring(istart, iend + 1));
				istart = index + 1;
			}
			from = index + 1;
		}
		if (iend < src.length() - 2) {
			ins.add(src.substring(istart, src.length()));
		}

		return true;
	}

	public String nextInstr() {
		if (!editing && ins.size() > pc) {
			return ins.get(pc++);
		}
		return null;
	}

}
