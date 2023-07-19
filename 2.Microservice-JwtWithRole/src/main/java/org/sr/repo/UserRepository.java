package org.sr.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.sr.entity.CUSTOM_USER;

@Repository
public interface UserRepository extends MongoRepository<CUSTOM_USER, String> {
	Optional<CUSTOM_USER> findByUsername(String username);
}
