package com.dsu_review_api.infrastructure.persistence;

import com.dsu_review_api.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {



    @Query("select m  from User m where m.user_number = :user_number and m.password = :password")
    User checkLogin(@Param("user_number") String user_number ,@Param("password") String password);


}
