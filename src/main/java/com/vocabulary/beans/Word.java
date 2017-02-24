package com.vocabulary.beans;

public class Word {

	private String engWord;
	private String hebWord;

	public Word() {
	}

	@Override
	public String toString() {
		return String.format("[engWord='%s', hebWord='%s']", engWord, hebWord);
	}

	public Word(String engWord, String hebWord) {
		super();
		this.engWord = engWord;
		this.hebWord = hebWord;
	}

	public String getEngWord() {
		return engWord;
	}

	public void setEngWord(String engWord) {
		this.engWord = engWord;
	}

	public String getHebWord() {
		return hebWord;
	}

	public void setHebWord(String hebWord) {
		this.hebWord = hebWord;
	}

}