package repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import beans.vocabulary.VocabularyWord;

public interface VocabularyRepository extends MongoRepository<VocabularyWord, String> {

   public List<VocabularyWord> findByWordGroup(String wordGroup);

}