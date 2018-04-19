package com.mobileiron.tlv;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class TLVProcessor {
	private static int TYPE_LENGTH = 6;
	private static int VALUE_LENGTH = 4;

	private InputStream in;
	private OutputStream out;

	public TLVProcessor(InputStream in, OutputStream out) {
		this.in = in;
		this.out = out;
	}

	private void processTLV() {
		try (PrintWriter writer = new PrintWriter(out, true)) {
			try (Scanner scanner = new Scanner(this.in)) {
				while (scanner.hasNext()) {
					String tlvEncoded = scanner.next();
					TLV tlvObject = new TLV(tlvEncoded, TYPE_LENGTH, VALUE_LENGTH);
					List<String> decodedTLVs = tlvObject.decode();
					Iterator<String> iterator = decodedTLVs.iterator();
					while (iterator.hasNext()) {
						String tlv = (String) iterator.next();
						writer.println(tlv);
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		TLVProcessor processor = new TLVProcessor(System.in, System.out);
		processor.processTLV();
	}
}
