package com.mobiliron.tlv;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

	private void processTLV() throws IOException {
		Scanner scanner = new Scanner(this.in);
		while (scanner.hasNextLine()) {
			String tlvEncoded = scanner.nextLine();
			TLV tlvObject = new TLV(tlvEncoded, TYPE_LENGTH, VALUE_LENGTH);
			this.out.write(tlvObject.decode().getBytes());
			this.out.write(System.getProperty("line.separator").getBytes());
		}
		scanner.close();
	}

	public static void main(String[] args) throws IOException {
		TLVProcessor processor = new TLVProcessor(System.in, System.out);
		processor.processTLV();
	}
}
