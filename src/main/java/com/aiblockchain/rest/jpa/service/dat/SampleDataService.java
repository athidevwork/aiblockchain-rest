/**
 * 
 */
package com.aiblockchain.rest.jpa.service.dat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.aiblockchain.rest.jpa.entity.dat.Account;
import com.aiblockchain.rest.jpa.entity.dat.Address;
import com.aiblockchain.rest.jpa.entity.dat.Asset;
import com.aiblockchain.rest.jpa.entity.dat.Customer;
import com.aiblockchain.rest.jpa.entity.dat.Email;
import com.aiblockchain.rest.jpa.entity.dat.Lot;
import com.aiblockchain.rest.jpa.entity.dat.Phone;
import com.aiblockchain.rest.jpa.entity.dat.Transaction;
import com.aiblockchain.rest.security.Utils;

/**
 * @author Athi
 *
 */
//@Component
@Service("sampleDataService")
public class SampleDataService {

	@Autowired
	@PersistenceContext(unitName="aiblockchain_mysql_dat")
    private EntityManager entityManager;
    
    @Transactional(transactionManager = "transactionManager", propagation = Propagation.REQUIRED)
	public void saveSampleData() {
    	System.out.println("Setting up sample data");
		
    	try {
	    	//Address, Phone, Email and Customer
			Address workAddress1 = new Address();
			workAddress1.setLine1("50 Harrison St");
			workAddress1.setLine2("Suite 212B");
			workAddress1.setCity("Hoboken");
			workAddress1.setState("NJ");
			workAddress1.setZip("07030");
			workAddress1.setAddressType("Work");
			
			Address homeAddress1 = new Address();
			homeAddress1.setLine1("100 Springdale Rd");
			homeAddress1.setLine2("A3-186");
			homeAddress1.setCity("Cherry Hill");
			homeAddress1.setState("NJ");
			homeAddress1.setZip("08003");
			homeAddress1.setAddressType("Home");		
			
			Phone workPhone1 = new Phone("work", "732-789-6354");
			Phone homePhone1 = new Phone("work", "732-789-6355");
			Email workEmail1 = new Email("work", "jonny@work.com");
			Email homeEmail1 = new Email("home", "jonny@home.com");
			
			Customer customer1 = new Customer();		
			customer1.setLegalName("AI Blockchain");
			customer1.setEmergencyContact("Luna");
			customer1.setBeneficiary("Nina");
			customer1.setGovtId("US123");
			customer1.setTaxId("ABC");
			
			HashSet<Address> addresses1 = new HashSet<Address>();
			addresses1.add(homeAddress1);
			addresses1.add(workAddress1);
			customer1.setAddresses(addresses1);
			
			Set<Phone> phones1 = new HashSet<Phone>();
			phones1.add(workPhone1);
			phones1.add(homePhone1);
			customer1.setPhones(phones1);
			
			Set<Email> emails1 = new HashSet<Email>();
			emails1.add(workEmail1);
			emails1.add(homeEmail1);
			customer1.setEmails(emails1);
	
			Address workAddress2 = new Address();
			workAddress2.setLine1("50 Harrison St");
			workAddress2.setLine2("Suite 212B");
			workAddress2.setCity("Hoboken");
			workAddress2.setState("NJ");
			workAddress2.setZip("07030");
			workAddress2.setAddressType("Work");
			
			Address homeAddress2 = new Address();
			homeAddress2.setLine1("203 Yarrow Cir");
			homeAddress2.setLine2("A3");
			homeAddress2.setCity("Dayton");
			homeAddress2.setState("NJ");
			homeAddress2.setZip("08810");
			homeAddress2.setAddressType("Home");		
			
			Phone workPhone2 = new Phone("work", "732-789-1234");
			Phone homePhone2 = new Phone("home", "732-789-5678");
			Email workEmail2 = new Email("work", "athi@work.com");
			Email homeEmail2 = new Email("home", "athi@home.com");
			
			Customer customer2 = new Customer();		
			customer2.setLegalName("AI Blockchain Inc");
			customer2.setEmergencyContact("Esther");
			customer2.setBeneficiary("Laila");
			customer2.setGovtId("US567");
			customer2.setTaxId("DEF");
			
			HashSet<Address> addresses2 = new HashSet<Address>();
			addresses2.add(homeAddress2);
			addresses2.add(workAddress2);
			customer2.setAddresses(addresses2);
			
			Set<Phone> phones2 = new HashSet<Phone>();
			phones2.add(workPhone2);
			phones2.add(homePhone2);
			customer2.setPhones(phones2);
			
			Set<Email> emails2 = new HashSet<Email>();
			emails2.add(workEmail2);
			emails2.add(homeEmail2);
			customer2.setEmails(emails2);
			
			//Account
			Account account1 = new Account();
			account1.setAccountDesc("Jonny Custodian Account");
			account1.setAccountId("10_12345");
			
			Set<Account> accounts1 = new HashSet<Account>();
			accounts1.add(account1);		
	
			Account account2 = new Account();
			account2.setAccountDesc("Athi personal Account");
			account2.setAccountId("20_67890");
			
			Set<Account> accounts2 = new HashSet<Account>();
			accounts2.add(account2);	
			
			//Asset
			Asset diamond1 = new Asset();
			diamond1.setAssetId("12345ABCDE");
			diamond1.setAccount(account2);
			diamond1.setCarat("1.0");
			diamond1.setClarity("VVSI");
			diamond1.setCut("Princess");
			diamond1.setDescription("One carat VVSI princess diamond");
			diamond1.setMeasurements("0.2 inch diameter");
			diamond1.setShape("Round");
			diamond1.setWeight("1g");
			
			System.out.println(diamond1.toString());
			System.out.println("Encoded Asset Hash string : " + Utils.sha256(diamond1.toString()));		
			diamond1.setAssetHash(Utils.sha256(diamond1.toString()));
			
			Set<Asset> assets = new HashSet<Asset>();
			assets.add(diamond1);
			account1.setAssets(assets);
			
			//Lot and Transaction
			Lot diamond1jonnyPurchaseLot = new Lot();
			diamond1jonnyPurchaseLot.setAccount(account1);
			diamond1jonnyPurchaseLot.setAsset(diamond1);
			diamond1jonnyPurchaseLot.setPrice(new BigDecimal(1100.00));
			diamond1jonnyPurchaseLot.setPurchaseDate(new Date());
			diamond1jonnyPurchaseLot.setQty(1);
			diamond1jonnyPurchaseLot.setStatus("OPEN");
			
			HashSet<Lot> lots1 = new HashSet<Lot>();
			lots1.add(diamond1jonnyPurchaseLot);
			account1.setLots(lots1);
			customer1.setAccounts(accounts1);
					
			Transaction jonnyTrans1 = new Transaction();
			jonnyTrans1.setDescription("Jonny's purchase from Macy's");
			jonnyTrans1.setLot(diamond1jonnyPurchaseLot);
			jonnyTrans1.setFromAccount(account1);
			jonnyTrans1.setOwnerAcct(account1);
			jonnyTrans1.setAsset(diamond1);
			
			Set<Transaction> txn1 = new HashSet<Transaction>();
			diamond1jonnyPurchaseLot.setTransactions(txn1);
			account1.setOwner(txn1);
			account1.setReceiver(txn1);
			
			/*System.out.println("Trans1 Encoded Transaction Hash string : " + Utils.sha256(jonnyTrans1.toString()));	
			jonnyTrans1.setBeforeHash(Utils.sha256(jonnyTrans1.toString()));
			jonnyTrans1.setAfterHash(Utils.sha256(jonnyTrans1.toString()));*/
			
			System.out.println("Before customer1 and trans1");
			entityManager.persist(customer1);
			entityManager.persist(jonnyTrans1);
			
			account2.setAssets(assets);
			
			Lot diamond1jonnySaleLot = new Lot();
			diamond1jonnySaleLot.setAccount(account1);
			diamond1jonnySaleLot.setAsset(diamond1);
			diamond1jonnySaleLot.setSalePrice(new BigDecimal(1500.00));
			diamond1jonnySaleLot.setSaleDate(new Date());
			diamond1jonnySaleLot.setQty(1);
			diamond1jonnySaleLot.setStatus("CLOSED");
					
			HashSet<Lot> lots2 = new HashSet<Lot>();
			lots2.add(diamond1jonnySaleLot);
			account2.setLots(lots2);
			customer2.setAccounts(accounts2);
			
			Transaction jonnySaleTrans2 = new Transaction();
			jonnySaleTrans2.setDescription("Jonny sale of diamond");
			jonnySaleTrans2.setLot(diamond1jonnySaleLot);
			jonnySaleTrans2.setFromAccount(account1);
			jonnySaleTrans2.setToAccount(account2);
			jonnySaleTrans2.setOwnerAcct(account2);
			jonnySaleTrans2.setAsset(diamond1);
			
			/*jonnySaleTrans2.setBeforeHash(Utils.sha256(jonnyTrans1.toString()));
			jonnySaleTrans2.setAfterHash(Utils.sha256(jonnySaleTrans2.toString()));*/
			
			Set<Transaction> txn2 = new HashSet<Transaction>();
			diamond1jonnySaleLot.setTransactions(txn2);
			account2.setOwner(txn2);
			account2.setReceiver(txn2);
			
			System.out.println("Before customer2 and jonny sale");
			entityManager.persist(customer2);
			entityManager.persist(jonnySaleTrans2);
			
			Lot diamond1AthiPurchaseLot = new Lot();
			diamond1AthiPurchaseLot.setAccount(account1);
			diamond1AthiPurchaseLot.setAsset(diamond1);
			diamond1AthiPurchaseLot.setPrice(new BigDecimal(1500.00));
			diamond1AthiPurchaseLot.setPurchaseDate(new Date());
			diamond1AthiPurchaseLot.setQty(1);
			diamond1AthiPurchaseLot.setStatus("OPEN");
		
			HashSet<Lot> lots3 = new HashSet<Lot>();
			lots3.add(diamond1AthiPurchaseLot);
			account2.setLots(lots3);
			customer2.setAccounts(accounts2);
			
			Transaction athiPurchaseTrans1 = new Transaction();
			athiPurchaseTrans1.setDescription("Athi's resale purchase of diamond");
			athiPurchaseTrans1.setLot(diamond1AthiPurchaseLot);
			athiPurchaseTrans1.setFromAccount(account1);
			athiPurchaseTrans1.setToAccount(account2);
			athiPurchaseTrans1.setOwnerAcct(account2);
			athiPurchaseTrans1.setAsset(diamond1);
			
			Set<Transaction> txn3 = new HashSet<Transaction>();
			diamond1AthiPurchaseLot.setTransactions(txn3);
			account2.setOwner(txn3);
			account2.setReceiver(txn3);
			
			System.out.println("Before entities persist");
			//write the data
			System.out.println("EntityManager - " + entityManager);	
			//System.out.println(customer1.toString());
			//System.out.println(customer2.toString());
			
			//entityManager.getTransaction().begin();
			entityManager.persist(athiPurchaseTrans1);
			//entityManager.getTransaction().commit();
			
			System.out.println("After entities persist");
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
	    	    
	    //close connections
	    //em.close();
	    //PersistenceManager.INSTANCE.close();		
	}
}
