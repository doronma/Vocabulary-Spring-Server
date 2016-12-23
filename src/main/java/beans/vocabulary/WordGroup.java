package beans.vocabulary;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

public class WordGroup {
	@Id
	private String id;
	private String groupName;
	private List<Word> wordList = new ArrayList<Word>();

	public WordGroup() {
		super();
	}

	public WordGroup(String groupName) {
		super();
		this.groupName = groupName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<Word> getWordList() {
		return wordList;
	}

	public void setWordList(List<Word> wordList) {
		this.wordList = wordList;
	}
	
	public void addWord(Word word){
		wordList.add(word);
	}
	
	@Override
	public String toString() {
		return wordList.toString();
		
	}
	
}
