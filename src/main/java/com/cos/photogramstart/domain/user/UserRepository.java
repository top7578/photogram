package com.cos.photogramstart.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//어노테이션이 없어도 IOC 등록이 자동으로 된다.
public interface UserRepository extends JpaRepository<User, Integer> {

    //JPA query method
    User findByUsername(String username);

    @Query(value = "SELECT distinct u FROM User u JOIN FETCH u.toSubscribe ts WHERE u.id=:id")
    User findUser(int id);
}
