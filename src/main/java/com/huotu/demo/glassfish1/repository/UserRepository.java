package com.huotu.demo.glassfish1.repository;

import com.huotu.demo.glassfish1.entity1.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author CJ
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
