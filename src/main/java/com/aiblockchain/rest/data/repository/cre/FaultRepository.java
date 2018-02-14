package com.aiblockchain.rest.data.repository.cre;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aiblockchain.rest.jpa.entity.cre.Fault;

/**
 * @author Athi
 *
 */
@Repository("FaultRepository")
//@Transactional(propagation = Propagation.NESTED, value = "transactionManageCre")
@Transactional(value = "cre")
public interface FaultRepository  extends JpaRepository<Fault, Integer> {
	public List<Fault> findByAibcStatus(String status);
	//public List<Fault> findByDateContaining(String yearMonthOrDate); //2018
}
