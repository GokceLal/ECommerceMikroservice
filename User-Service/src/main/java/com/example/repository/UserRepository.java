package com.example.repository;

import com.example.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {

    /**
     *
     * @param userName
     * @return
     *     @Query(value = "{userName: ?0}")
     *     User getUser(String userName);
     */

}
