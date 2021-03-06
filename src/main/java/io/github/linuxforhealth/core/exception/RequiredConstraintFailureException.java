/*
 * (C) Copyright IBM Corp. 2020
 *
 * SPDX-License-Identifier: Apache-2.0
 */
package io.github.linuxforhealth.core.exception;

public class RequiredConstraintFailureException extends RuntimeException {

  public RequiredConstraintFailureException(String message, Exception e) {
    super(message, e);
  }

  public RequiredConstraintFailureException(String message) {
    super(message);
  }

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

}
