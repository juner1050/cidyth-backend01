package com.hyzs.cidyth.common.module;

import org.apache.commons.lang3.tuple.Pair;

public final class UnSafePair<L, R> extends Pair<L, R> {
	private static final long serialVersionUID = -8707175007138089944L;
	public L left;
	public R right;

	public UnSafePair() {
	}

	public UnSafePair(L left, R right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public R setValue(R value) {
		throw new UnsupportedOperationException();
	}

	public void setLeft(L left) {
		this.left = left;
	}

	public void setRight(R right) {
		this.right = right;
	}

	@Override
	public L getLeft() {
		return left;
	}

	@Override
	public R getRight() {
		return right;
	}

}
