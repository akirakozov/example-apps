package ru.akirakozov.apps;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.lang.model.element.Element;

/**
 * @author akirakozov
 */
public class ElementCounter extends DefaultHandler {
    private final String name;
    private long counter = 0;

    public ElementCounter(String name) {
        this.name = name;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (name.equals(qName)) {
            counter++;
        }
    }

    public long getCount() {
        return counter;
    }
}
