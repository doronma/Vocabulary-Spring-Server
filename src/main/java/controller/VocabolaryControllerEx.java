package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import beans.vocabulary.Message;
import beans.vocabulary.Word;
import beans.vocabulary.WordGroup;
import repository.VocabularyRepositoryEx;

@CrossOrigin
@RestController
public class VocabolaryControllerEx {

	@Autowired
	private VocabularyRepositoryEx repository;

	@RequestMapping("/addWordsEx")
	public String addWords() {
		repository.deleteAll();
		WordGroup weekDaysWordGroup = getWeekDaysWordGroup();
		repository.save(weekDaysWordGroup);
		return "ok";
	}

	private WordGroup getWeekDaysWordGroup() {
		WordGroup wordGroup1 = new WordGroup("WeekDays");
		wordGroup1.addWord(new Word("Sunday", "יום ראשון"));
		wordGroup1.addWord(new Word("Monday", "יום שני"));
		wordGroup1.addWord(new Word("Tuesday", "יום שלישי"));
		wordGroup1.addWord(new Word("Wednesday", "יום רביעי"));
		wordGroup1.addWord(new Word("Thursday", "יום חמישי"));
		wordGroup1.addWord(new Word("Friday", "יום שישי"));
		wordGroup1.addWord(new Word("Saturday", "יום שבת"));
		return wordGroup1;
	}

	@CrossOrigin
	@RequestMapping("/getWordGroupNameList")
	public List<String> getLastWordGroup() {
		List<String> wordGroupNameList = new ArrayList<String>();
		for (WordGroup wordGroup : repository.findAll()) {
			wordGroupNameList.add(wordGroup.getGroupName());
		}
		System.out.println("Getting word list - " + wordGroupNameList);
		return wordGroupNameList;
	}

	@CrossOrigin
	@RequestMapping("/getWordsExByName")
	public WordGroup getWordGroup(@RequestParam(value = "groupName", defaultValue = "WeekDays") String groupName) {
		WordGroup wordGroup = repository.findByGroupName(groupName);
		// System.out.println(wordGroup.getGroupName());

		return wordGroup;

	}
	
	
	@RequestMapping(value="/addWordGroup",method = RequestMethod.POST)
	public Message addWordGroup (@RequestBody WordGroup wordGroup){
		
		System.out.println(wordGroup);
		repository.save(wordGroup);
		Message message = new Message();
		message.setMsg("All is Good");
		return message;
	}
	
	@RequestMapping(value="/editWordGroup",method = RequestMethod.POST)
	public Message editWordGroup (@RequestBody WordGroup editedWordGroup){
		Message message = new Message();
		WordGroup wordGroup = repository.findByGroupName(editedWordGroup.getGroupName());
		if (wordGroup==null){
			message.setMsg("Word Group was not found");
		}else{
				wordGroup.setWordList(editedWordGroup.getWordList());
				repository.save(wordGroup);
				message.setMsg("All is Good");
			}
		
		return message;
	}
	
	@RequestMapping(value="/deleteWordGroup",method = RequestMethod.POST)
	public Message deleteWordGroup (@RequestBody String groupName){
		
		System.out.println("deleting - " + groupName);
		WordGroup wordGroup = repository.findByGroupName(groupName);
		repository.delete(wordGroup);
		Message message = new Message();
		message.setMsg("All is Good");
		return message;
	}

}