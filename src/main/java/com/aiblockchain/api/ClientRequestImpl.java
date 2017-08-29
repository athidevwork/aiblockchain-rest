/**
 * 
 */
package com.aiblockchain.api;

/**
 * @author Athi
 *
 */
public class ClientRequestImpl implements ClientRequest {
	private String command;

	public void setCommand(String command) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}

}
