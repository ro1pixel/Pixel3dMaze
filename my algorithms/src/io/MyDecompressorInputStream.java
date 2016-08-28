package io;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream {
	
	InputStream in;

	public MyDecompressorInputStream() {
		super();
		in = new InputStream() {
			
			@Override
			public int read() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}
		};
	}
	
	@Override
	public int read() throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

}
