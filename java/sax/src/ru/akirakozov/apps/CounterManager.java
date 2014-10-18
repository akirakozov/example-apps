package ru.akirakozov.apps;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author akirakozov
 */
public class CounterManager {

    public long calcCount(String fileName, String elementName) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            ElementCounter counter = new ElementCounter(elementName);
            saxParser.parse(fileName, counter);
            return counter.getCount();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
