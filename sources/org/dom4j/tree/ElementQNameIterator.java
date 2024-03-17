package org.dom4j.tree;

import java.util.Iterator;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.QName;

public class ElementQNameIterator extends FilterIterator<Node> {
    private QName qName;

    public ElementQNameIterator(Iterator<Node> it, QName qName2) {
        super(it);
        this.qName = qName2;
    }

    /* access modifiers changed from: protected */
    public boolean matches(Node node) {
        if (node instanceof Element) {
            return this.qName.equals(((Element) node).getQName());
        }
        return false;
    }
}
