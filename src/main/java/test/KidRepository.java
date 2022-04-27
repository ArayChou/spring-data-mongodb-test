package test;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface KidRepository extends MongoRepository<Kid, String>, QuerydslPredicateExecutor<Kid> {
}
