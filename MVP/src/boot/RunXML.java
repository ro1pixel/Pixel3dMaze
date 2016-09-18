package boot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import presenter.Properties;

public class RunXML {

	public static void main(String[] args) {
		
		Properties properties = new Properties("Growing","BFS",6);
		FileOutputStream xml;
		
		try {
			xml = new FileOutputStream("Properties.xml");
			JAXB.marshal(properties, xml);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
//		try {
//			File xml = new File("test.xml");
//			JAXBContext jaxbContext = JAXBContext.newInstance(Properties.class);
//			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//			
//			//output pretty printed
//			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//			jaxbMarshaller.marshal(properties, xml);
//			jaxbMarshaller.marshal(properties, System.out);
////			JAXB.marshal(properties, xml);
//		} catch (JAXBException e) {
//			e.printStackTrace();
//	      }

	}

}
