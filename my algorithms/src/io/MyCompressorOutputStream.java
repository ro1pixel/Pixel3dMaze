package io;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
	
	OutputStream out;

	public MyCompressorOutputStream() {
		super();
		out = new OutputStream() {
			
			@Override
			public void write(int b) throws IOException {
				// TODO Auto-generated method stub
				
			}
		};
	}

	@Override
	public void write(int arg0) throws IOException {
		// TODO Auto-generated method stub

	}

}
