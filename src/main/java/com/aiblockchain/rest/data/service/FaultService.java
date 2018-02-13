/**
 * 
 */
package com.aiblockchain.rest.data.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aiblockchain.rest.data.config.SpringDataContext;
import com.aiblockchain.rest.data.repository.cre.FaultRepository;
import com.aiblockchain.rest.jpa.entity.cre.Fault;

/**
 * @author Athi
 *
 */
@Service("DataFaultService")
public class FaultService {
	@Autowired
	FaultRepository faultRepo;
	
	public List<Fault> getFaultList() {
		List<Fault> faults = new ArrayList<>();
		try {
			//FaultRepository faultRepo = (FaultRepository) SpringDataContext.getBean("FaultRepository");
			System.out.println("Start get list with faultRepo = " + faultRepo);
			System.out.println("fault count = " + faultRepo.count());
			faults = faultRepo.findAll();
			System.out.println("Faults = " + faults.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return faults;		
	}
}
