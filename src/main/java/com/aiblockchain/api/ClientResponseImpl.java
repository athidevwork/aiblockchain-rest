/**
 * 
 */
package com.aiblockchain.api;

/**
 * @author Athi
 *
 */
public class ClientResponseImpl implements ClientResponse {
	private String status;
	private String resultType;
	private Object result;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResultType() {
		return resultType;
	}

	public void setResultType(String resultType) {
		this.resultType = resultType;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object response) {
		this.result = response;
	}
	
}
