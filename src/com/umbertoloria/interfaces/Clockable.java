package com.umbertoloria.interfaces;

public interface Clockable {

	void clock();

	default void clockBack() {
	}

}
