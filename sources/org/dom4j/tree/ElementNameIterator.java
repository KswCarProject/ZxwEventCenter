package org.dom4j.tree;

import java.util.Iterator;
import org.dom4j.Element;
import org.dom4j.Node;

public class ElementNameIterator extends FilterIterator<Node> {
    private String name;

    public ElementNameIterator(Iterator<Node> it, String str) {
        super(it);
        this.name = str;
    }

    /* access modifiers changed from: protected */
    public boolean matches(Node node) {
        if (node instanceof Element) {
            return this.name.equals(((Element) node).getName());
        }
        return false;
    }
}
