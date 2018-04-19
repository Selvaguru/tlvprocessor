package com.mobileiron.tlv;

public enum Type {
	UPPRCS, REPLCE, NONE;

	public static Type getValueOf(String typeString) {
		Type value = null;
		try {
			value = Type.valueOf(typeString);
		} catch (IllegalArgumentException exception) {
			value = Type.NONE;
		}
		return value;

	}
}
