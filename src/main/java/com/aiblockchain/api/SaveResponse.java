/**
 * 
 */
package com.aiblockchain.api;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Athi
 *
 */
public class SaveResponse implements Serializable {
	private static final long serialVersionUID = 9064520101982385231L;
	private String result;

	private Map<String, Serializable> savedData;

	public String getResult() {
		return result;
	}

	public Map<String, Serializable> getSavedData() {
		return savedData;
	}

	public void setResult(String command) {
		this.result = command;
	}

	public void setSavedData(Map<String, Serializable> faultData) {
		this.savedData = faultData;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) { return true; }
		if (o == null || getClass() != o.getClass()) { return false; }

		SaveResponse that = (SaveResponse) o;

		if (result != null ? !result.equals(that.result) : that.result != null) { return false; }
		if (savedData != null ? !savedData.equals(that.savedData) : that.savedData != null) { return false; }

		return true;
	}

	@Override
	public int hashCode() {
		int result = this.result != null ? this.result.hashCode() : 0;
		result = 31 * result + (savedData != null ? savedData.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "SaveResponse{"  +
				"result='"      + result + '\'' +
				", savedData='" + savedData + '\'' +
				'}';
	}
}
