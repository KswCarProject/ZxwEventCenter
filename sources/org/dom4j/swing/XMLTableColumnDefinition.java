package org.dom4j.swing;

import java.io.PrintStream;
import java.io.Serializable;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.dom4j.XPath;

public class XMLTableColumnDefinition implements Serializable {
    public static final int NODE_TYPE = 3;
    public static final int NUMBER_TYPE = 2;
    public static final int OBJECT_TYPE = 0;
    public static final int STRING_TYPE = 1;
    private XPath columnNameXPath;
    private String name;
    private int type;
    private XPath xpath;

    public XMLTableColumnDefinition() {
    }

    public XMLTableColumnDefinition(String str, String str2, int i) {
        this.name = str;
        this.type = i;
        this.xpath = createXPath(str2);
    }

    public XMLTableColumnDefinition(String str, XPath xPath, int i) {
        this.name = str;
        this.xpath = xPath;
        this.type = i;
    }

    public XMLTableColumnDefinition(XPath xPath, XPath xPath2, int i) {
        this.xpath = xPath2;
        this.columnNameXPath = xPath;
        this.type = i;
    }

    public static int parseType(String str) {
        if (str == null || str.length() <= 0) {
            return 0;
        }
        if (str.equals("string")) {
            return 1;
        }
        if (str.equals("number")) {
            return 2;
        }
        return str.equals("node") ? 3 : 0;
    }

    public Class<?> getColumnClass() {
        int i = this.type;
        if (i == 1) {
            return String.class;
        }
        if (i == 2) {
            return Number.class;
        }
        if (i != 3) {
            return Object.class;
        }
        return Node.class;
    }

    public Object getValue(Object obj) {
        int i = this.type;
        if (i == 1) {
            return this.xpath.valueOf(obj);
        }
        if (i == 2) {
            return this.xpath.numberValueOf(obj);
        }
        if (i != 3) {
            return this.xpath.evaluate(obj);
        }
        return this.xpath.selectSingleNode(obj);
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public XPath getXPath() {
        return this.xpath;
    }

    public void setXPath(XPath xPath) {
        this.xpath = xPath;
    }

    public XPath getColumnNameXPath() {
        return this.columnNameXPath;
    }

    public void setColumnNameXPath(XPath xPath) {
        this.columnNameXPath = xPath;
    }

    /* access modifiers changed from: protected */
    public XPath createXPath(String str) {
        return DocumentHelper.createXPath(str);
    }

    /* access modifiers changed from: protected */
    public void handleException(Exception exc) {
        PrintStream printStream = System.out;
        printStream.println("Caught: " + exc);
    }
}
