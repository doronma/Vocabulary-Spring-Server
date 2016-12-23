package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import beans.vocabulary.VocabularyWord;
import beans.vocabulary.VocabularyWordContainer;
import repository.VocabularyRepository;

@RestController
public class VocabolaryController {

	@Autowired
	private VocabularyRepository repository;

	@RequestMapping("/addWords")
	public String addWords() {
		// repository.deleteAll();
		repository.save(new VocabularyWord("Sunday", "יום ראשון", "WeekDays"));
		repository.save(new VocabularyWord("Monday", "יום שני", "WeekDays"));
		repository.save(new VocabularyWord("Tuesday", "יום שלישי", "WeekDays"));
		repository.save(new VocabularyWord("Wednesday", "יום רביעי", "WeekDays"));
		repository.save(new VocabularyWord("Thursday", "יום חמישי", "WeekDays"));
		repository.save(new VocabularyWord("Friday", "יום שישי", "WeekDays"));
		repository.save(new VocabularyWord("Saturday", "יום שבת", "WeekDays"));
		for (VocabularyWord vocabularyWord : repository.findAll()) {
			System.out.println(vocabularyWord);
		}

		return "ok";

	}
	@CrossOrigin
	@RequestMapping("/getWords")
	public VocabularyWordContainer getCustomers(
			@RequestParam(value = "wordGroup", defaultValue = "WeekDays") String wordGroup) {

		List<VocabularyWord> vocabularyWordList = new ArrayList<VocabularyWord>();
		for (VocabularyWord vocabularyWord : repository.findAll()) {
			System.out.println(vocabularyWord);
			vocabularyWordList.add(vocabularyWord);
		}
		VocabularyWordContainer vocabularyWordContainer = new VocabularyWordContainer();
		vocabularyWordContainer.setVocabularyWordList(vocabularyWordList);
		return vocabularyWordContainer;

	}

}