package io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MyDecompressorInputStream extends InputStream {
	
	InputStream in;

	public MyDecompressorInputStream(InputStream in) {
		super();
		this.in = in;
	}
	
	@Override
	public int read() throws IOException {
		return in.read();
	}
	
	public int read(byte[] array) throws IOException {
		int i=0;
		@SuppressWarnings("resource")
		String content = new Scanner(new File("1.maz")).useDelimiter("\\Z").next();
		String[] split = content.split(",");
		
		for(;i<9;i++) {
			//array[i] = split[i].getBytes();
		}
		
		while(i<array.length) {
			int count = in.read();
			byte b = (byte) in.read();
			
			for(int j=0;j<count;j++) {
				array[i++] = b;
			}
		}
		
		return array.length;
	}

}
