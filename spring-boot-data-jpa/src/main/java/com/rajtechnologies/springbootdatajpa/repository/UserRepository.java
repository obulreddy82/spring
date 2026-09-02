package com.rajtechnologies.springbootdatajpa.repository;

import com.rajtechnologies.springbootdatajpa.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameAndEmail(String name, String email);

    List<User> findByEmail(String email, Sort sort);

    //When method-name queries become complicated, use @Query.
    //JPQL
    //@Query("SELECT u FROM User u WHERE u.age > :age")
    @Query(
            value = "SELECT * FROM users WHERE age > :age",
            nativeQuery = true
    )
    List<User> getAdultUsers(@Param("age") int age);
}
