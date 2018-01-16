/**
 * 
 */
package com.aiblockchain.rest.jpa.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import com.aiblockchain.rest.data.config.SpringDataContext;
import com.aiblockchain.rest.jpa.entity.Account;
import com.aiblockchain.rest.jpa.entity.Asset;
import com.aiblockchain.rest.jpa.entity.Customer;
import com.aiblockchain.rest.jpa.service.AccountService;
import com.aiblockchain.rest.jpa.service.AssetService;
import com.aiblockchain.rest.jpa.service.CustomerService;
import com.aiblockchain.rest.jpa.service.SampleDataService;

/**
 * @author Athi
 *
 */
//@Service
@Path("/dat")
@Component
public class DigitalAssetTransferResource {
	@GET
    @Produces("text/plain")
    public Response hello() {
		/*System.out.println(EntityManager.class.getProtectionDomain()
                .getCodeSource()
                .getLocation());*/
        return Response.ok().entity("Hello from DigitalAssetTranfer Resource").build();
    }
	
    @GET
	@Path("/customer/list")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    public Response getCustomerList() {
    	CustomerService custService = (CustomerService) SpringDataContext.getBean("customerService");
    	List<Customer> customerList = custService.getCustomerList();
    	System.out.println("Customers size : " + customerList.size());
    	
    	return Response.ok().entity(customerList).header("customer", "AllCustomers").build();
    }
    
    @GET
	@Path("/customer/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    public Response getCustomer(@PathParam(value = "id") int id) {
    	CustomerService custService = (CustomerService) SpringDataContext.getBean("customerService");
    	System.out.println("Customer Id = " +id);
    	Customer customer = custService.getCustomer(id);
    	System.out.println("Customer in Resource : " + customer.getLegalName());
    	//System.out.println("customer : " + customer);
    	
    	return Response.ok().entity(customer).header("customer", "Customer"+id).build();
    }
    
    @GET
	@Path("/asset/list")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAssetList() {
    	System.out.println("Starting get Asset list");
    	AssetService assetService = (AssetService) SpringDataContext.getBean("assetService");
    	//System.out.println("Start get list with assetService = " + assetService);
    	List<Asset> assetList= assetService.getAssetList();

    	System.out.println("Assets size : " + assetList.size());
    	
    	return Response.ok().entity(assetList).header("asset", "AllAssets").build();  	
    }

    @GET
	@Path("/asset/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAsset(@PathParam(value = "id") int id) {
    	AssetService assetService = (AssetService) SpringDataContext.getBean("assetService");
    	System.out.println("Asset Id = " +id);
    	Asset asset = assetService.getAsset(id);
    	System.out.println("Asset in Resource : " + asset.getDescription());
    	//System.out.println("customer : " + customer);
    	
    	return Response.ok().entity(asset).header("asset", "Asset"+id).build();
    }
    
    @GET
	@Path("/account/list")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Response getAccountList() {
    	System.out.println("Starting get Account list");
    	AccountService acctService = (AccountService) SpringDataContext.getBean("accountService");
    	List<Account> acctList= acctService.getAccountList();

    	System.out.println("Accounts size : " + acctList.size());
    	
    	return Response.ok().entity(acctList).header("account", "AllAccounts").build();
    }
    
    @GET
	@Path("/sample/save")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    public Response saveAsset () {
    //public Response saveAsset (Asset asset, @Context UriInfo uriInfo) {
    	//Asset asset = new Asset(null, "1.5", "none", "vvs1", "colorless", "round", "Test asset", "1x1x1", "Superior", "test1", "test2");
    	SampleDataService sampleDataService = (SampleDataService) SpringDataContext.getBean("sampleDataService");
    	sampleDataService.saveSampleData();
    	
        //UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        //builder.path(Integer.toString(dbAsset.getId()));
        //builder.path(Integer.toString(dbAsset.getId()));
        
    	return Response.ok().build();
    }	
}
