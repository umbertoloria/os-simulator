package com.umbertoloria.bittings;

public class BitStream {

	private Bit[] bits;

	public BitStream(int size) {
		bits = new Bit[size];
		for (int i = 0; i < bits.length; i++) {
			bits[i] = new Bit(false);
		}
	}

	public BitStream(String str) {
		bits = new Bit[str.length()];
		for (int i = 0; i < bits.length; i++) {
			bits[i] = new Bit(str.charAt(i) == '1');
		}
	}

	public BitStream(boolean empty, int size) {
		bits = new Bit[size];
	}

	public void set(int index, boolean val) {
		bits[index].set(val);
	}

	public void set(boolean[] likeThis) {
		if (size() != likeThis.length) {
			throw new RuntimeException("Dimensioni problematiche");
		}
		for (int i = 0; i < likeThis.length; i++) {
			bits[i].set(likeThis[i]);
		}
	}

	public void set(BitStream bs) {
		if (size() != bs.size()) {
			throw new RuntimeException("Dimensioni problematiche");
		}
		for (int i = 0; i < bs.size(); i++) {
			bits[i].set(bs.get(i).get());
		}
	}

	public Bit get(int index) {
		return bits[index];
	}

	public int size() {
		return bits.length;
	}

	public boolean equals(BitStream bs) {
		if (size() != bs.size()) {
			return false;
		}
		for (int i = 0; i < bits.length; i++) {
			if (!get(i).equals(bs.get(i))) {
				return false;
			}
		}
		return true;
	}

	public boolean endsWith(BitStream bs) {
		int offset = size() - bs.size();
		int i;
		for (i = 0; i < bs.size(); i++) {
			if (!get(offset + i).equals(bs.get(i))) {
				break;
			}
		}
		return i >= bs.size();
	}

	// Operations and Utils
	public BitStream sub(int from, int to) {
		BitStream res = new BitStream(false, to - from);
		for (int i = 0; i < to - from; i++) {
			res.bits[i] = get(from + i);
		}
		return res;
	}

	public BitStream startFrom(int from) {
		return sub(from, size());
	}

	public BitStream truncate(int to) {
		return sub(0, to);
	}

	// FIXME: rimuovere al più presto
	public boolean[] toArray() {
		boolean[] res = new boolean[size()];
		for (int i = 0; i < res.length; i++) {
			res[i] = get(i).get();
		}
		return res;
	}

	public BitStream clone() {
		/*BitStream res = new BitStream(size());
		for (int i = 0; i < res.size(); i++) {
			res.set(i, this.get(i).get());
		}
		return res;
		TODO: quale scelgo?
		*/
		try {
			return (BitStream) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}

	public String toString () {
		StringBuilder res = new StringBuilder();
		for (Bit bit : bits) {
			res.append(bit);
		}
		return res.toString();
	}

}
