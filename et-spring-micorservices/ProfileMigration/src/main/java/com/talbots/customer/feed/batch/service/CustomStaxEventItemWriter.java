package com.talbots.customer.feed.batch.service;

import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Namespace;
import javax.xml.stream.events.StartElement;

import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class CustomStaxEventItemWriter<T> extends StaxEventItemWriter<T> {
	private String rootTagNamespace = "";

	/**
	 * 
	 * @return
	 */
	public String getRootTagNamespace() {
		return rootTagNamespace;
	}

	/**
	 * 
	 * @param rootTagNamespace
	 */
	public void setRootTagNamespace(String rootTagNamespace) {
		this.rootTagNamespace = rootTagNamespace;
	}

	/**
	 * 
	 * Replace the standard startDocument with one that lets us put a XML namespace
	 * on the root tag.
	 */
	@Override
	protected void startDocument(XMLEventWriter writer) throws XMLStreamException {
		XMLEventFactory factory = XMLEventFactory.newInstance();

// write start document
		writer.add(factory.createStartDocument(getEncoding(), getVersion()));

// write root tag
		Iterator<Namespace> nsIt = null;
		if (StringUtils.hasText(getRootTagNamespace()))

		{ // Create the namespace for the root tag Namespace ns =
			// factory.createNamespace("", getRootTagNamespace()); nsIt =
			// Collections.singletonList(ns).iterator(); }
			QName rootTag = new QName(getRootTagNamespace(), getRootTagName(), "");
			StartElement startElement = factory.createStartElement(rootTag, null, nsIt);
			writer.add(startElement);

// write root tag attributes
			if (!CollectionUtils.isEmpty(getRootElementAttributes())) {
				for (Map.Entry<String, String> entry : getRootElementAttributes().entrySet())

				{
					writer.add(factory.createAttribute(entry.getKey(), entry.getValue()));
				}
			}

			writer.flush();
		}
	}
}