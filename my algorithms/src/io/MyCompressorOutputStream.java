package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream {
	
	OutputStream out;

	public MyCompressorOutputStream(OutputStream out) {
		super();
		this.out = out;
	}

	@Override
	public void write(int b) throws IOException {
		// TODO Auto-generated method stub

	}
	
	public void write(byte[] b) throws IOException {
		
		String psik = ",";
		int i=0;
		
		//Write array length
		out.write(String.valueOf(b.length).getBytes());
		out.write(psik.getBytes());
		
		for(;i<9;i++) {
			out.write(String.valueOf(b[i]).getBytes());
			out.write(psik.getBytes());
		}
				
		int length = b.length;
		int trigger = 1;
		int count = 0;
		
		while(i<length) {
			if(b[i++] == trigger)
				count++;
			else {
				if(trigger==1) {
					while(true) {
						if(count<=255) {
							out.write(String.valueOf(count).getBytes());
							out.write(psik.getBytes());
							out.write(String.valueOf(1).getBytes());
							out.write(psik.getBytes());
							break;
						}
						else {
							out.write(String.valueOf(255).getBytes());
							out.write(psik.getBytes());
							out.write(String.valueOf(1).getBytes());
							out.write(psik.getBytes());
							count=count-255;
						}
					}
					count = 1;
					trigger = 0;
				}
				else if(trigger==0) {
					while(true) {
						if(count<=255) {
						out.write(String.valueOf(count).getBytes());
						out.write(psik.getBytes());
						out.write(String.valueOf(0).getBytes());
						out.write(psik.getBytes());
						break;
						}
						else {
							out.write(String.valueOf(255).getBytes());
							out.write(psik.getBytes());
							out.write(String.valueOf(0).getBytes());
							out.write(psik.getBytes());
							count=count-255;
						}
					}
					count = 1;
					trigger = 1;
				}
			}
		}
		
		if(trigger==1) {
			out.write(String.valueOf(count).getBytes());
			out.write(psik.getBytes());
			out.write(String.valueOf(1).getBytes());
			out.write(psik.getBytes());
		}
		
	}

}
