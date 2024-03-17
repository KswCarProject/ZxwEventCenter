package org.dom4j.bean;

import java.io.PrintStream;
import org.dom4j.Attribute;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.QName;
import org.dom4j.tree.DefaultAttribute;
import org.xml.sax.Attributes;

public class BeanDocumentFactory extends DocumentFactory {
    private static BeanDocumentFactory singleton = new BeanDocumentFactory();

    /* access modifiers changed from: protected */
    public Object createBean(QName qName) {
        return null;
    }

    public static DocumentFactory getInstance() {
        return singleton;
    }

    public Element createElement(QName qName) {
        Object createBean = createBean(qName);
        if (createBean == null) {
            return new BeanElement(qName);
        }
        return new BeanElement(qName, createBean);
    }

    public Element createElement(QName qName, Attributes attributes) {
        Object createBean = createBean(qName, attributes);
        if (createBean == null) {
            return new BeanElement(qName);
        }
        return new BeanElement(qName, createBean);
    }

    public Attribute createAttribute(Element element, QName qName, String str) {
        return new DefaultAttribute(qName, str);
    }

    /* access modifiers changed from: protected */
    public Object createBean(QName qName, Attributes attributes) {
        String value = attributes.getValue("class");
        if (value == null) {
            return null;
        }
        try {
            return Class.forName(value, true, BeanDocumentFactory.class.getClassLoader()).newInstance();
        } catch (Exception e) {
            handleException(e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void handleException(Exception exc) {
        PrintStream printStream = System.out;
        printStream.println("#### Warning: couldn't create bean: " + exc);
    }
}