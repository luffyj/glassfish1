package com.huotu.demo.glassfish1.repository;

import com.huotu.demo.glassfish1.entity2.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author CJ
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
