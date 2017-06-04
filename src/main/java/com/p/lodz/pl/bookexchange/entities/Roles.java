package com.p.lodz.pl.bookexchange.entities;

public enum Roles {
	SUPERADMINISTRATOR("ROLE_SUPERADMINISTRATOR"), ADMINISTRATOR("ROLE_ADMINISTRATOR"), MODERATOR("ROLE_MODERATOR"), USER("ROLE_USER");

	private final String text;

	/**
	 * @param text
	 */
	private Roles(final String text) {
		this.text = text;
	}

	
	@Override
	public String toString() {
		return text;
	}
}
