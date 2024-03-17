package org.dom4j;

public class InvalidXPathException extends IllegalArgumentException {
    public InvalidXPathException(String str) {
        super("Invalid XPath expression: " + str);
    }

    public InvalidXPathException(String str, String str2) {
        super("Invalid XPath expression: " + str + " " + str2);
    }
}
