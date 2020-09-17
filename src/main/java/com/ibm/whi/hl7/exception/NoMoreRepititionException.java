/*
 * (C) Copyright IBM Corp. 2020
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package com.ibm.whi.hl7.exception;

public class NoMoreRepititionException extends RuntimeException {

  public NoMoreRepititionException(String message, Throwable e) {
    super(message, e);
  }



  /**
   * 
   */
  private static final long serialVersionUID = 1L;

}
