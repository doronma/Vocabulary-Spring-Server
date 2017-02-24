package com.vocabulary.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vocabulary.beans.Message;
import com.vocabulary.beans.WordGroup;
import com.vocabulary.repository.VocabularyRepository;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
public class VocabolaryController {

	private static final Logger log = LoggerFactory.getLogger(VocabolaryController.class);

	@Autowired
	private VocabularyRepository repository;

	/**
	 * @return
	 */
	@CrossOrigin
	@ApiOperation(value = "get wordGroups list")
	@RequestMapping(value = "/wordGroups", method = RequestMethod.GET)
	public List<String> getLastWordGroup() {
		List<String> wordGroupNameList = new ArrayList<String>();
		for (WordGroup wordGroup : repository.findAll()) {
			wordGroupNameList.add(wordGroup.getGroupName());
		}
		log.info("Getting word list - " + wordGroupNameList);
		
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return wordGroupNameList;
	}

	/**
	 * @param groupName
	 * @return
	 */
	@CrossOrigin
	@ApiOperation(value = "get group words")
	@RequestMapping(value = "/groupWords", method = RequestMethod.GET)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "groupName", value = "The name of the requested word group", required = true, dataType = "string", paramType = "query") })
	public WordGroup getWordGroup(@RequestParam(value = "groupName", defaultValue = "WeekDays") String groupName) {
		WordGroup wordGroup = repository.findByGroupName(groupName);
		log.info("Getting words for wordGroup - " + groupName);
		return wordGroup;
	}

	/**
	 * @param wordGroup
	 * @return
	 */
	@CrossOrigin(origins = "http://localhost:4200")

	@ApiOperation(value = "add new wordGroup")
	@RequestMapping(value = "/addWordGroup", method = RequestMethod.POST)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "wordGroup", value = "The new wordGroup to be added (without id)", required = true, dataType = "wordGroup", paramType = "body") })
	public Message addWordGroup(@RequestBody WordGroup wordGroup) {
		log.info("Adding WordGroup - " + wordGroup);
		repository.save(wordGroup);
		Message message = new Message();
		message.setMsg("WordGroup " + wordGroup.getGroupName() + " was added");
		return message;
	}

	/**
	 * @param editedWordGroup
	 * @return
	 */
	@ApiOperation(value = "edit existing wordGroup")
	@RequestMapping(value = "/editWordGroup", method = RequestMethod.PUT)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "editedWordGroup", value = "The  wordGroup to be edited", required = true, dataType = "wordGroup", paramType = "body") })
	public Message editWordGroup(@RequestBody WordGroup editedWordGroup) {
		Message message = new Message();
		WordGroup wordGroup = repository.findByGroupName(editedWordGroup.getGroupName());
		if (wordGroup == null) {
			message.setMsg("Word Group" + editedWordGroup.getGroupName() + " was not found");
			log.info("Word Group" + editedWordGroup.getGroupName() + " was not found");
		} else {
			wordGroup.setWordList(editedWordGroup.getWordList());
			repository.save(wordGroup);
			message.setMsg("WordGroup " + wordGroup.getGroupName() + " was edited");
			log.info("WordGroup " + wordGroup.getGroupName() + " was edited");

		}
		return message;
	}
	@CrossOrigin
	@ApiOperation(value = "delete wordGroup")
	@RequestMapping(value = "/deleteWordGroup", method = RequestMethod.DELETE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "groupName", value = "The  wordGroup name to be deleted", required = true, dataType = "string", paramType = "query") })
	public Message deleteWordGroup(@RequestParam(value = "groupName") String groupName) {
		log.info("deleting - " + groupName);
		if (groupName==null|| groupName.length()==0){
			log.error("Empty Group");
			Message message = new Message();
			message.setMsg("empty Group - nothing was deleted");
			return message;
		}
		WordGroup wordGroup = repository.findByGroupName(groupName);
		repository.delete(wordGroup);
		Message message = new Message();
		message.setMsg("wordGroup " + groupName + " was deleted");
		return message;
	}

}