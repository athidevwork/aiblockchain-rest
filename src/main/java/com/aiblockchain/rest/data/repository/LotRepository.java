/**
 * 
 */
package com.aiblockchain.rest.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aiblockchain.rest.data.entity.Lot;

/**
 * @author Athi
 *
 */
//@Repository
public interface LotRepository extends JpaRepository<Lot, Integer> {

}
