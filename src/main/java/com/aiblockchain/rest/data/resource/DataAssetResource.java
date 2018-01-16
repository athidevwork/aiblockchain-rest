/**
 * 
 */
package com.aiblockchain.rest.data.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aiblockchain.rest.data.adapter.HibernateProxyTypeAdapter;
import com.aiblockchain.rest.data.config.SpringDataContext;
import com.aiblockchain.rest.data.entity.Asset;
import com.aiblockchain.rest.data.service.DataAssetService;
import com.aiblockchain.rest.data.wrapper.Assets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * @author Athi
 *
 */
//@Service
//@Controller
//@Component
//@ContextConfiguration(locations="classpath:spring/application-context.xml")
//@Transactional(propagation=Propagation.REQUIRED)
@Path("/data_asset_transfer")
//@Component
public class DataAssetResource {
	GsonBuilder gsonBuilder = new GsonBuilder();
	
	//Gson gson = new Gson();
	
	/*@Autowired
	AssetService assetService;	
	@Autowired
	AssetRepository assetRepo;
	@Autowired
	AccountRepository acctRepo;
	@Autowired
	TransactionRepository transRepo;*/	

	@GET
    @Produces("text/plain")
    public Response hello() {
		/*System.out.println(EntityManager.class.getProtectionDomain()
                .getCodeSource()
                .getLocation());*/
        return Response.ok().entity("Hello from AssetTranfer Resource").build();
    }

    @GET
	@Path("/list")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    @Transactional()
    public Response getAssetList() {
    	//curl -i -H "Accept: application/json" -H "Content-Type: application/json" -X GET http://localhost:20001/asset_transfer/list
    	//curl -i -H "Accept: application/json" -X GET http://localhost:20001/asset_transfer/list
    	//curl -i -H "Accept: application/xml" -X GET http://localhost:20001/asset_transfer/list
    	/*DataAssetService assetService = (DataAssetService) SpringDataContext.getBean("assetService");
    	//System.out.println("Start get list with assetService = " + assetService);
    	List<Asset> assetList= assetService.getAssetList();
    	//System.out.println("Assets = " + assets);

    	List<Asset> pojoAsset = assetList.stream()
                .map(asset -> convertToPojoAsset(asset))
                .collect(Collectors.toList());
    	//System.out.println("Converted data " + pojoAsset);
    	
    	Assets assets = new Assets();
    	assets.setAssetList(assetList);
    	//System.out.println(assets);
    	try {
    		gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
    		Gson gson = gsonBuilder.create();
			System.out.println(gson.toJson(assets, Assets.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        return Response.ok(assets).build(); */
        return null;
    }

    @POST
	@Path("/save")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
    @Transactional()
    public Response saveAsset (Asset asset, @Context UriInfo uriInfo) {
    	//Asset asset = new Asset(null, "1.5", "none", "vvs1", "colorless", "round", "Test asset", "1x1x1", "Superior", "test1", "test2");
    	/*DataAssetService assetService = (DataAssetService) SpringDataContext.getBean("assetService");
    	Asset dbAsset = assetService.saveAsset(asset);
    	
        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(dbAsset.getAssetId());
        //builder.path(Integer.toString(dbAsset.getId()));
        
    	return Response.created(builder.build()).build();*/
    	return null;
    }
	/*private Asset convertToPojoAsset(Asset sdAsset) {
		ModelMapper modelMapper = (ModelMapper) SpringDataContext.getBean("modelMapper");		
	    Asset asst = modelMapper.map(sdAsset, Asset.class);
		return asst;
	}
	
	private Asset convertToSpringEntity(Asset asset) throws ParseException {
		ModelMapper modelMapper = (ModelMapper) SpringDataContext.getBean("modelMapper");		
		Asset sdAsset = modelMapper.map(asset, Asset.class);
		if (sdAsset.getId() != null) {
			
		}
		return sdAsset;
	}*/
}
