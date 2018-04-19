package com.mobileiron.tlv;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.mobileiron.tlv.TLV;

public class TLVTest {

	TLV subject;

	@Test
	public void testTLVDecodeSuccess() {
		// Test Uppercase
		subject = new TLV("UPPRCS-0005-abcde", 6, 4);
		List<String> mockData = new ArrayList<>();
		mockData.add("UPPRCS-ABCDE");
		assertEquals(subject.decode(), mockData);

		// Test Replace
		subject = new TLV("REPLCE-0003-123", 6, 4);
		mockData = new ArrayList<>();
		mockData.add("REPLCE-THIS STRING");
		assertEquals(subject.decode(), mockData);
	}

	@Test
	public void testTLVDecodeFailure() {
		subject = new TLV("NONCMD-0005-abcde", 6, 4);
		List<String> mockData = new ArrayList<>();
		mockData.add("Type not valid");
		assertEquals(subject.decode(), mockData);
	}

	@Test
	public void testMultipleTLVTokens() {
		subject = new TLV("UPPRCS-0008-AbcdefghREPLCE-0003-123REPLCE-0001-Z", 6, 4);
		List<String> mockData = new ArrayList<>();
		mockData.add("UPPRCS-ABCDEFGH");
		mockData.add("REPLCE-THIS STRING");
		mockData.add("REPLCE-THIS STRING");
		assertEquals(subject.decode(), mockData);
	}
}
