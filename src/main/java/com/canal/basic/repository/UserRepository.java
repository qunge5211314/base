package com.canal.basic.repository;

import com.canal.basic.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByNameOrEmail(String name, String email);
    Optional<User> findById(Long id);

    List<User> findUsersByIdIn(List<Long> idList);
    User findByIdNotAndName(Long id, String name);

}
