package org.dom4j.tree;

import java.io.IOException;
import java.io.Writer;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.Visitor;

public abstract class AbstractAttribute extends AbstractNode implements Attribute {
    public short getNodeType() {
        return 2;
    }

    public void setNamespace(Namespace namespace) {
        throw new UnsupportedOperationException("This Attribute is read only and cannot be changed");
    }

    public String getText() {
        return getValue();
    }

    public void setText(String str) {
        setValue(str);
    }

    public void setValue(String str) {
        throw new UnsupportedOperationException("This Attribute is read only and cannot be changed");
    }

    public Object getData() {
        return getValue();
    }

    public void setData(Object obj) {
        setValue(obj == null ? null : obj.toString());
    }

    public String toString() {
        return super.toString() + " [Attribute: name " + getQualifiedName() + " value \"" + getValue() + "\"]";
    }

    public String asXML() {
        return getQualifiedName() + "=\"" + getValue() + "\"";
    }

    public void write(Writer writer) throws IOException {
        writer.write(getQualifiedName());
        writer.write("=\"");
        writer.write(getValue());
        writer.write("\"");
    }

    public void accept(Visitor visitor) {
        visitor.visit((Attribute) this);
    }

    public Namespace getNamespace() {
        return getQName().getNamespace();
    }

    public String getName() {
        return getQName().getName();
    }

    public String getNamespacePrefix() {
        return getQName().getNamespacePrefix();
    }

    public String getNamespaceURI() {
        return getQName().getNamespaceURI();
    }

    public String getQualifiedName() {
        return getQName().getQualifiedName();
    }

    public String getPath(Element element) {
        StringBuilder sb = new StringBuilder();
        Element parent = getParent();
        if (!(parent == null || parent == element)) {
            sb.append(parent.getPath(element));
            sb.append("/");
        }
        sb.append("@");
        String namespaceURI = getNamespaceURI();
        String namespacePrefix = getNamespacePrefix();
        if (namespaceURI == null || namespaceURI.length() == 0 || namespacePrefix == null || namespacePrefix.length() == 0) {
            sb.append(getName());
        } else {
            sb.append(getQualifiedName());
        }
        return sb.toString();
    }

    public String getUniquePath(Element element) {
        StringBuilder sb = new StringBuilder();
        Element parent = getParent();
        if (!(parent == null || parent == element)) {
            sb.append(parent.getUniquePath(element));
            sb.append("/");
        }
        sb.append("@");
        String namespaceURI = getNamespaceURI();
        String namespacePrefix = getNamespacePrefix();
        if (namespaceURI == null || namespaceURI.length() == 0 || namespacePrefix == null || namespacePrefix.length() == 0) {
            sb.append(getName());
        } else {
            sb.append(getQualifiedName());
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public Node createXPathResult(Element element) {
        return new DefaultAttribute(element, getQName(), getValue());
    }
}
