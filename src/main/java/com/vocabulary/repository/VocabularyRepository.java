package com.vocabulary.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.vocabulary.beans.WordGroup;

public interface VocabularyRepository extends MongoRepository<WordGroup, String> {

	public WordGroup findByGroupName(String groupName);

}