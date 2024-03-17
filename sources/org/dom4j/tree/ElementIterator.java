package org.dom4j.tree;

import java.util.Iterator;
import org.dom4j.Element;
import org.dom4j.Node;

public class ElementIterator extends FilterIterator<Node> {
    public ElementIterator(Iterator<Node> it) {
        super(it);
    }

    /* access modifiers changed from: protected */
    public boolean matches(Node node) {
        return node instanceof Element;
    }
}
