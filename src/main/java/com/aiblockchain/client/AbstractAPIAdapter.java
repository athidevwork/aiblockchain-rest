package com.aiblockchain.client;

import org.apache.log4j.Logger;

import com.aiblockchain.api.SaveRequest;
import com.aiblockchain.api.SaveResponse;

/**
 * APIAdapter.java
 *
 * Description: Provides an abstract API adapter for AI Coin and AI Blockchain to communicate with the cloud.
 *
 * Copyright (C) Apr 19, 2017, AI Coin, Inc.
 */
public abstract class AbstractAPIAdapter {

  // the singleton instance
  private static AbstractAPIAdapter apiAdapter;

  /**
   * Constructs an AbstractAPIAdapter instance.
   *
   */
  public AbstractAPIAdapter() {
    apiAdapter = this;
  }

  /** Gets the singleton instance of this class.
   * 
   * @return the singleton instance of this class, or null if not yet initialized by the AICOperation agent
   */
  public static AbstractAPIAdapter getInstance() {
    return apiAdapter;
  }
  
  /**
   * Adds the given document signatures to the block chain.
   *
   * @param saveRequest the save request, which contains the doc signatures
   * @return
   */
  public abstract SaveResponse saveRequest(final SaveRequest saveRequest);
  
  /** Gets the logger.
   * 
   * @return the logger
   */
  abstract protected Logger getLogger();
}
