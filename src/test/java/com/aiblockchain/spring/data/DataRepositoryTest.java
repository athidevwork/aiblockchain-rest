package com.aiblockchain.spring.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aiblockchain.rest.data.entity.dat.Account;
import com.aiblockchain.rest.data.entity.dat.Address;
import com.aiblockchain.rest.data.entity.dat.Asset;
import com.aiblockchain.rest.data.entity.dat.ContactAddress;
import com.aiblockchain.rest.data.entity.dat.ContactEmail;
import com.aiblockchain.rest.data.entity.dat.ContactPhone;
import com.aiblockchain.rest.data.entity.dat.Customer;
import com.aiblockchain.rest.data.entity.dat.Email;
import com.aiblockchain.rest.data.entity.dat.Lot;
import com.aiblockchain.rest.data.entity.dat.Phone;
import com.aiblockchain.rest.data.entity.dat.Transaction;
import com.aiblockchain.rest.data.repository.dat.AccountRepository;
import com.aiblockchain.rest.data.repository.dat.AddressRepository;
import com.aiblockchain.rest.data.repository.dat.AssetRepository;
import com.aiblockchain.rest.data.repository.dat.ContactAddressRepository;
import com.aiblockchain.rest.data.repository.dat.ContactEmailRepository;
import com.aiblockchain.rest.data.repository.dat.ContactPhoneRepository;
import com.aiblockchain.rest.data.repository.dat.CustomerRepository;
import com.aiblockchain.rest.data.repository.dat.EmailRepository;
import com.aiblockchain.rest.data.repository.dat.LotRepository;
import com.aiblockchain.rest.data.repository.dat.PhoneRepository;
import com.aiblockchain.rest.data.repository.dat.TransactionRepository;

//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/test-context.xml")
//@ContextConfiguration(locations="classpath:spring/application-context.xml")
@PropertySource("classpath:application.properties")
public class DataRepositoryTest {

	@Autowired
	AddressRepository addrRepo;
	@Autowired
	EmailRepository emailRepo;	
	@Autowired
	PhoneRepository phoneRepo;	
	@Autowired
	CustomerRepository custRepo;
	@Autowired
	ContactAddressRepository contactAddrRepo;
	@Autowired
	ContactEmailRepository contactEmailRepo;
	@Autowired
	ContactPhoneRepository contactPhoneRepo;	
	@Autowired
	AccountRepository acctRepo;	
	@Autowired
	AssetRepository assetRepo;	
	@Autowired
	LotRepository lotRepo;
	@Autowired
	TransactionRepository transRepo;
	
	Address addr = null;
	Email email = null;
	Phone phone = null;
	Customer customer = null;
	String legalName = "Athi Muthu";
	
	ContactAddress contactAddress = null;
	ContactEmail contactEmail = null;
	ContactPhone contactPhone = null;
	
	Asset asset = null;
	Account acct = null;
	Lot lot = null;
	Transaction trans = null;
	
	//@Test
	public void testSaveAddress() {
		addr = new Address("1010 Alexander Rd", "Suite 100", "Office", "Princeton", "NJ", "08060");
		//addrRepo.save(addr);
		
		/*Address dbAddr = addrRepo.findAddr(addr.getId());
		assertNotNull(dbAddr);
		assertEquals("Zip", "08060", dbAddr.getZip());*/	
	}

	//@Test
	public void testSaveEmail() {
		email = new Email("Test//@Test.com", "Work");
		//emailRepo.save(email);
		
		/*Email dbEmail = emailRepo.findEmail(email.getId());
		assertNotNull(dbEmail);
		assertEquals("Email", "Test//@Test.com", dbEmail.getEmail());*/		
	}
	
	//@Test
	public void testSavePhone() {
		phone = new Phone("123-456-7896", "Work");
		//phoneRepo.save(phone);
		
		/*Phone dbPhone = phoneRepo.findPhone(phone.getId());
		assertNotNull(dbPhone);
		assertEquals("Phone", "123-456-7896", dbPhone.getPnumber());*/				
	}
	
	//@Test
	public void testSaveCustomer() {
		customer = new Customer("Saru Athi", "Saru athi", "M938456345647363", legalName, "");
		/*customer.setAccounts(acctRepo.findAcctsForName(legalName));
		customer.setContactAddresses(contactAddrRepo.findAddrsForName(legalName));
		customer.setContactEmails(contactEmailRepo.findEmailsForName(legalName));
		customer.setContactPhones(contactPhoneRepo.findPhonesForName(legalName));*/
		//custRepo.save(customer);
		
		/*Customer cust = custRepo.findCustomer(customer.getId());
		assertNotNull(cust);
		assertEquals("Legal Name", legalName, cust.getLegalName());*/		
	}
	
	//@Test
	public void testSaveDigitalData() {
		testSaveCustomer();
		testSaveAddress();
		testSaveEmail();
		testSavePhone();
		
		//save contact_address
		contactAddress = new ContactAddress(customer, addr);
		//contactAddrRepo.save(contactAddress);
		//save contact_email
		contactEmail = new ContactEmail(customer, email);
		//contactEmailRepo.save(contactEmail);
		//save contact_phone
		contactPhone = new ContactPhone(customer, phone);
		//contactPhoneRepo.save(contactPhone);	
		
		//save asset
		/*asset = new Asset(null, "1.0", "None", "Clear", "Yellow", "Round", "First Asset for Athi", "1x1x1", "VVVS1", "Round", "0.5g");
		assetRepo.save(asset);*/
		
		//save account
		acct = new Account(customer);
		//acctRepo.save(acct);
		
		//save lot
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		Date fromDate = null;
		try {
			fromDate = (Date) df.parse("2017/01/01");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		lot = new Lot(fromDate, new BigDecimal(15000.00), null, null, acct);
		//lotRepo.save(lot);
		
		//save transaction
		trans = new Transaction(null, null, "First Retailer transaction", acct, asset, acct, lot, null);
		//transRepo.save(trans);
	}

	@Test
	public void testGetDiamond() {
		//fail("Not yet implemented");
	}
}
