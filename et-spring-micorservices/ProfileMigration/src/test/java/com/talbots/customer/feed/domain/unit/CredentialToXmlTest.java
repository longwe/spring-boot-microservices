package com.talbots.customer.feed.domain.unit;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.talbots.customer.feed.batch.util.CustomerMapperHelper;
import com.talbots.customer.feed.domain.Credential;

public class CredentialToXmlTest {
	private Credential credential;

	@Before
	public void setUp() {

		credential = new Credential("srivera@lyonscg.com", CustomerMapperHelper.getPassword(), "true");
	}

	@After
	public void tearDown() {
		credential = null;
	}

	@Test
	public void testObjectToXml() throws JAXBException, FileNotFoundException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Credential.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(credential, new File("Credentials.xml"));
		marshaller.marshal(credential, System.out);
	}
}
