/**
 * 
 */
package com.aiblockchain.rest.data.wrapper;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.aiblockchain.rest.data.entity.Asset;

/**
 * @author Athi
 *
 */
@XmlRootElement(name = "Assets")
//@XmlAccessorType(XmlAccessType.FIELD)
public class Assets implements Serializable {
	List<Asset> assetList;
	private static final long serialVersionUID = 4560470592705892957L;

	public Assets () {}
	
	public List<Asset> getAssetList() {
		return assetList;
	}

	public void setAssetList(List<Asset> assetList) {
		this.assetList = assetList;
	}

	/*@Override
	public String toString() {
		return "Assets [assetList=" + assetList + "]";
	}*/
	
}
