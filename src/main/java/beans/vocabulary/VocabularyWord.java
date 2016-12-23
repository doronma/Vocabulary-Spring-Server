package beans.vocabulary;

import org.springframework.data.annotation.Id;

public class VocabularyWord {

	@Id
	private String id;

	private String engWord;
	private String hebWord;
	private String wordGroup;

	public VocabularyWord() {
	}

	

	@Override
	public String toString() {
		return String.format("Customer[id=%s, engWord='%s', hebWord='%s', wordGroup='%s']",
				id, engWord, hebWord,wordGroup);
	}



	public VocabularyWord(String engWord, String hebWord, String wordGroup) {
		super();
		this.engWord = engWord;
		this.hebWord = hebWord;
		this.wordGroup = wordGroup;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
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



	public String getWordGroup() {
		return wordGroup;
	}



	public void setWordGroup(String wordGroup) {
		this.wordGroup = wordGroup;
	}

	

}