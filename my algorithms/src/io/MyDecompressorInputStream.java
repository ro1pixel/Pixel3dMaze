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
		
		Scanner scanner = new Scanner(in);
		Scanner input = scanner.useDelimiter(",");
		
		//array = new byte[Integer.valueOf(input.next())];
		input.next();
		int counter=0;
		int j = 9;
		byte wallOrPath = 0;
		
		for(int i=1;i<10;i++) {
			array[i]=Byte.valueOf(input.next());
		}
		
		while(input.hasNext()) {
			counter = Integer.valueOf(input.next());
			wallOrPath = Byte.valueOf(input.next());
			
			while(counter!=0) {
				array[j++]=wallOrPath;
				counter--;
			}
		}
		
		return array.length;
	}

}
