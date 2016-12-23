package repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import beans.vocabulary.WordGroup;

public interface VocabularyRepositoryEx extends MongoRepository<WordGroup, String> {

	public WordGroup findByGroupName(String groupName);

}