package com.umbertoloria;

import com.umbertoloria.mips_project.mips.CPUDriversManager;

import java.util.Random;

public class Main {

	private static Random r = new Random();

	public static void main(String[] args) {
		CPUDriversManager a = new CPUDriversManager(1);
		for (int i = 0; i < 10; i++) {
			int res = a.add(r.nextInt(5), -5);
			System.out.println(res);
		}
	}

	private static void sleep() {
		try {
			Thread.sleep(10L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
