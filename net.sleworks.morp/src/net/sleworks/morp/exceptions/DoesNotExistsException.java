/*******************************************************************************************
 * Copyright (c) Igor R. Dejanović <igor DOT dejanovic AT gmail DOT com>
 * See LICENSE file which accompanies this distribution.
 *******************************************************************************************/
package net.sleworks.morp.exceptions;

public class DoesNotExistsException extends Exception {

	public DoesNotExistsException(String message) {
		super(message);
	}

	private static final long serialVersionUID = 6957575820007781219L;

}
