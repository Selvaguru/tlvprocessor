package com.mobileiron.tlv;

import java.util.ArrayList;
import java.util.List;

public class TLV {

	private String tlvEncoded;
	private int typeLength;
	private int valueLength;

	public TLV(String tlvEncoded, int typeLength, int valueLength) {
		this.tlvEncoded = tlvEncoded;
		this.typeLength = typeLength;
		this.valueLength = valueLength;
	}

	public List<String> decode() {
		List<String> decodedTLVs = new ArrayList<>();
		String tempTLV = tlvEncoded;
		while (!tempTLV.isEmpty()) {
			Type type = Type.getValueOf(tempTLV.substring(0, typeLength));
			int length = Integer.parseInt(tempTLV.substring(typeLength + 1, typeLength + valueLength + 1));
			String value = tempTLV.substring(typeLength + valueLength + 2, typeLength + valueLength + length + 2);

			switch (type) {
			case UPPRCS:
				decodedTLVs.add(type + "-" + value.toUpperCase());
				break;
			case REPLCE:
				decodedTLVs.add(type + "-THIS STRING");
				break;
			case NONE:
				decodedTLVs.add("Type not valid");
			}
			tempTLV = tempTLV.substring(typeLength + valueLength + length + 2);

		}
		return decodedTLVs;
	}

}