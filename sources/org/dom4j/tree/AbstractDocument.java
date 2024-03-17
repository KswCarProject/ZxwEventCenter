package org.dom4j.tree;

import com.example.mylibrary.BuildConfig;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.IllegalAddException;
import org.dom4j.Node;
import org.dom4j.QName;
import org.dom4j.Visitor;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public abstract class AbstractDocument extends AbstractBranch implements Document {
    protected String encoding;

    public Node asXPathResult(Element element) {
        return this;
    }

    public Document getDocument() {
        return this;
    }

    public short getNodeType() {
        return 9;
    }

    public String getPath(Element element) {
        return "/";
    }

    public String getUniquePath(Element element) {
        return "/";
    }

    public String getXMLEncoding() {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract void rootElementAdded(Element element);

    public String getStringValue() {
        Element rootElement = getRootElement();
        return rootElement != null ? rootElement.getStringValue() : BuildConfig.FLAVOR;
    }

    public String asXML() {
        OutputFormat outputFormat = new OutputFormat();
        outputFormat.setEncoding(this.encoding);
        try {
            StringWriter stringWriter = new StringWriter();
            XMLWriter xMLWriter = new XMLWriter((Writer) stringWriter, outputFormat);
            xMLWriter.write((Document) this);
            xMLWriter.flush();
            return stringWriter.toString();
        } catch (IOException e) {
            throw new RuntimeException("IOException while generating textual representation: " + e.getMessage());
        }
    }

    public void write(Writer writer) throws IOException {
        OutputFormat outputFormat = new OutputFormat();
        outputFormat.setEncoding(this.encoding);
        new XMLWriter(writer, outputFormat).write((Document) this);
    }

    public void accept(Visitor visitor) {
        visitor.visit((Document) this);
        DocumentType docType = getDocType();
        if (docType != null) {
            visitor.visit(docType);
        }
        List<Node> content = content();
        if (content != null) {
            for (Node accept : content) {
                accept.accept(visitor);
            }
        }
    }

    public String toString() {
        return super.toString() + " [Document: name " + getName() + "]";
    }

    public void normalize() {
        Element rootElement = getRootElement();
        if (rootElement != null) {
            rootElement.normalize();
        }
    }

    public Document addComment(String str) {
        add(getDocumentFactory().createComment(str));
        return this;
    }

    public Document addProcessingInstruction(String str, String str2) {
        add(getDocumentFactory().createProcessingInstruction(str, str2));
        return this;
    }

    public Document addProcessingInstruction(String str, Map<String, String> map) {
        add(getDocumentFactory().createProcessingInstruction(str, map));
        return this;
    }

    public Element addElement(String str) {
        Element createElement = getDocumentFactory().createElement(str);
        add(createElement);
        return createElement;
    }

    public Element addElement(String str, String str2) {
        Element createElement = getDocumentFactory().createElement(str, str2);
        add(createElement);
        return createElement;
    }

    public Element addElement(QName qName) {
        Element createElement = getDocumentFactory().createElement(qName);
        add(createElement);
        return createElement;
    }

    public void setRootElement(Element element) {
        clearContent();
        if (element != null) {
            super.add(element);
            rootElementAdded(element);
        }
    }

    public void add(Element element) {
        checkAddElementAllowed(element);
        super.add(element);
        rootElementAdded(element);
    }

    public boolean remove(Element element) {
        boolean remove = super.remove(element);
        if (getRootElement() != null && remove) {
            setRootElement((Element) null);
        }
        element.setDocument((Document) null);
        return remove;
    }

    /* access modifiers changed from: protected */
    public void childAdded(Node node) {
        if (node != null) {
            node.setDocument(this);
        }
    }

    /* access modifiers changed from: protected */
    public void childRemoved(Node node) {
        if (node != null) {
            node.setDocument((Document) null);
        }
    }

    /* access modifiers changed from: protected */
    public void checkAddElementAllowed(Element element) {
        Element rootElement = getRootElement();
        if (rootElement != null) {
            throw new IllegalAddException((Branch) this, (Node) element, "Cannot add another element to this Document as it already has a root element of: " + rootElement.getQualifiedName());
        }
    }

    public void setXMLEncoding(String str) {
        this.encoding = str;
    }
}
