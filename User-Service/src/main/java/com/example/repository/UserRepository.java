package com.example.repository;

import com.example.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    Page<User> findAllByUserNameContaining(String userName, Pageable pageable);

    /**
     *
     * @param userName
     * @return
     *     @Query(value = "{userName: ?0}")
     *     User getUser(String userName);
     */

}
