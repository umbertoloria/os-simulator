package com.umbertoloria;

import java.util.ArrayList;
import java.util.Scanner;

public class Assembler {

	private ArrayList<AssInstr> instructions = new ArrayList<AssInstr>();
	private int[] memory;
	private boolean running;
	private int PC = 1;
	private int OR1, OR2, CR;
	private int MR, AR, LR;
	// Condition Register   CR
	// Program Counter      PC
	// Other Register 1     OR1
	// Other Register 2     OR2 TODO: vedere se serve!
	// Memory Register      MR

	public Assembler(String src) {
		Scanner in = new Scanner(src);
		for (int i = 1; in.hasNextLine(); i++) {
			instructions.add(new AssInstr(in.nextLine(), this));
		}
		execute(false);
	}

	private void execute(boolean verboose) {
		running = true;
		PC = 1;
		while (running) {
			if (verboose) {
				System.out.printf("%-3d: ", PC);
				for (int i = 0; i < PC; i++) {
					System.out.print("-");
				}
				System.out.println();
			}
			//instructions.get(PC - 1).execute(verboose);
			PC++;
		}
	}

	public void setSizeMemory(int size) {
		memory = new int[size];
	}

	public void setMemoryBlock(int addr, int value) {
		memory[addr] = value;
	}

	public int getMemoryBlock(int addr) {
		return memory[addr];
	}

	// ORA
	public void setOR1(int val) {
		OR1 = val;
	}

	public int getOR1() {
		return OR1;
	}

	// ORB
	public void setOR2(int val) {
		OR2 = val;
	}

	public int getOR2() {
		return OR2;
	}

	// CR
	public void setCR(int val) {
		CR = val;
	}

	public int getCR() {
		return CR;
	}

	// PC
	public void setPC(int val) {
		PC = val - 1;
	}

	// MR
	public void setMR(int val) {
		MR = val;
	}

	public int getMR() {
		return MR;
	}

	// MR
	public void setAR(int val) {
		AR = val;
	}

	public int getAR() {
		return AR;
	}

	// MR
	public void setLR(int val) {
		LR = val;
	}

	public int getLR() {
		return LR;
	}

	// EXIT
	public void exit() {
		running = false;
	}

}
