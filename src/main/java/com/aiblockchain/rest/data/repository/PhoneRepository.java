/**
 * 
 */
package com.aiblockchain.rest.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aiblockchain.rest.data.entity.Phone;

/**
 * @author Athi
 *
 */
@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {

	//Phone findPhone(long id);

}
