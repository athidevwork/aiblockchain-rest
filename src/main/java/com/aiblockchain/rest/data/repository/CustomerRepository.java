/**
 * 
 */
package com.aiblockchain.rest.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aiblockchain.rest.data.entity.Customer;

/**
 * @author Athi
 *
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	//Customer findCustomer(long id);

}
