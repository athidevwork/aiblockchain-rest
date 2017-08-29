/**
 * Provides a save request API object. The fault documents are digested by the client and the hashes of
 * each form the signatures. This request says to store the given list of hex encoded signatures as OP_RETURN data on the blockchain.
 *
 * Presently the "add" command is supported.
 */
package com.aiblockchain.api;

import java.io.Serializable;
import java.util.List;

/**
 * @author Athi
 *
 */
public class SaveRequest implements Serializable {

  private static final long serialVersionUID = 4919239675643182205L;

  // the command, "save"
  private final String command;

  // the list of fault signatures, in which each signature is a hex encoded byte array of at most 80 bytes
  // typically the doc signature is a SHA-256 hash with 32 byte length
  private final List<String> docSignatures;

  /**
   * Constructs a new SaveRequest instance.
   *
   * @param command the command, "add"
   * @param docSignatures the list of doc signatures
   */
  public SaveRequest(
          final String command,
          final List<String> docSignatures) {
    //Preconditions
    assert StringUtils.isNonEmptyString(command) : "command must be a non empty string";
    assert docSignatures != null : "docSignatures must not be null";

    this.command = command;
    this.docSignatures = docSignatures;
  }

  /**
   * Gets the command.
   *
   * @return the command
   */
  public String getCommand() {
    return command;
  }

  /**
   * Gets the list of document signatures.
   *
   * @return the list of document signatures
   */
  public List<String> getDocSignatures() {
    return docSignatures;
  }
  
  /** Returns a string representation of this object.
   * 
   * @return a string representation of this object 
   */
  @Override
  public String toString() {
    return new StringBuilder()
            .append("[SaveRequest ")
            .append(command)
            .append(", ")
            .append(docSignatures)
            .append("]")
            .toString();
  }
}
