package com.mobiliron.tlv;

public class TLV {

	private String tlvEncoded;
	private int typeLength;
	private int valueLength;

	public TLV(String tlvEncoded, int typeLength, int valueLength) {
		this.tlvEncoded = tlvEncoded;
		this.typeLength = typeLength;
		this.valueLength = valueLength;
	}

	public String decode() {
		TYPE type = null;
		int length = 0;
		String value = "";
		String decodedTLV = "";
		try {
			type = TYPE.valueOf(tlvEncoded.substring(0, typeLength));
		} catch (IllegalArgumentException exception) {
			decodedTLV = "Type not valid";
		}

		if (type != null) {
			length = Integer.parseInt(tlvEncoded.substring(typeLength + 1, typeLength + valueLength + 1));
			value = tlvEncoded.substring(typeLength + valueLength + 2, typeLength + valueLength + length + 2);

			switch (type) {
			case UPPRCS:
				decodedTLV = type + "-" + value.toUpperCase();
				break;
			case REPLCE:
				decodedTLV = type + "-THIS STRING";
				break;
			}
		}

		return decodedTLV;
	}

}