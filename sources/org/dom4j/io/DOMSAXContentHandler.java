package org.dom4j.io;

import org.dom4j.Branch;
import org.dom4j.CDATA;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.Text;
import org.dom4j.dom.DOMAttribute;
import org.dom4j.dom.DOMCDATA;
import org.dom4j.dom.DOMComment;
import org.dom4j.dom.DOMDocumentFactory;
import org.dom4j.dom.DOMElement;
import org.dom4j.dom.DOMText;
import org.dom4j.tree.NamespaceStack;
import org.w3c.dom.ProcessingInstruction;
import org.xml.sax.Attributes;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ext.LexicalHandler;
import org.xml.sax.ext.Locator2;
import org.xml.sax.helpers.DefaultHandler;

public class DOMSAXContentHandler extends DefaultHandler implements LexicalHandler {
    private StringBuffer cdataText;
    private Element currentElement;
    private int declaredNamespaceIndex;
    private Document document;
    private DOMDocumentFactory documentFactory;
    private ElementStack elementStack;
    private EntityResolver entityResolver;
    private boolean ignoreComments;
    private InputSource inputSource;
    private boolean insideCDATASection;
    private Locator locator;
    private boolean mergeAdjacentText;
    private NamespaceStack namespaceStack;
    private boolean stripWhitespaceText;
    private StringBuffer textBuffer;
    private boolean textInTextBuffer;

    public void endDTD() throws SAXException {
    }

    public void endEntity(String str) throws SAXException {
    }

    public void startDTD(String str, String str2, String str3) throws SAXException {
    }

    public void startEntity(String str) throws SAXException {
    }

    public void warning(SAXParseException sAXParseException) throws SAXException {
    }

    public DOMSAXContentHandler() {
        this((DOMDocumentFactory) DOMDocumentFactory.getInstance());
    }

    public DOMSAXContentHandler(DOMDocumentFactory dOMDocumentFactory) {
        this.mergeAdjacentText = false;
        this.textInTextBuffer = false;
        this.ignoreComments = false;
        this.stripWhitespaceText = false;
        this.documentFactory = dOMDocumentFactory;
        this.elementStack = createElementStack();
        this.namespaceStack = new NamespaceStack(dOMDocumentFactory);
    }

    public org.w3c.dom.Document getDocument() {
        if (this.document == null) {
            this.document = createDocument();
        }
        return (org.w3c.dom.Document) this.document;
    }

    public void setDocumentLocator(Locator locator2) {
        this.locator = locator2;
    }

    public void processingInstruction(String str, String str2) throws SAXException {
        if (this.mergeAdjacentText && this.textInTextBuffer) {
            completeCurrentTextNode();
        }
        ProcessingInstruction processingInstruction = (ProcessingInstruction) this.documentFactory.createProcessingInstruction(str, str2);
        Element element = this.currentElement;
        if (element != null) {
            ((org.w3c.dom.Element) element).appendChild(processingInstruction);
        } else {
            getDocument().appendChild(processingInstruction);
        }
    }

    public void startPrefixMapping(String str, String str2) throws SAXException {
        this.namespaceStack.push(str, str2);
    }

    public void endPrefixMapping(String str) throws SAXException {
        this.namespaceStack.pop(str);
        this.declaredNamespaceIndex = this.namespaceStack.size();
    }

    public void startDocument() throws SAXException {
        this.document = null;
        this.currentElement = null;
        this.elementStack.clear();
        this.namespaceStack.clear();
        this.declaredNamespaceIndex = 0;
        if (this.mergeAdjacentText && this.textBuffer == null) {
            this.textBuffer = new StringBuffer();
        }
        this.textInTextBuffer = false;
    }

    public void endDocument() throws SAXException {
        this.namespaceStack.clear();
        this.elementStack.clear();
        this.currentElement = null;
        this.textBuffer = null;
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        if (this.mergeAdjacentText && this.textInTextBuffer) {
            completeCurrentTextNode();
        }
        QName qName = this.namespaceStack.getQName(str, str2, str3);
        Branch branch = this.currentElement;
        if (branch == null) {
            branch = (Document) getDocument();
        }
        DOMElement dOMElement = new DOMElement(qName);
        branch.add((Element) dOMElement);
        addDeclaredNamespaces(dOMElement);
        addAttributes(dOMElement, attributes);
        this.elementStack.pushElement(dOMElement);
        this.currentElement = dOMElement;
    }

    public void endElement(String str, String str2, String str3) throws SAXException {
        if (this.mergeAdjacentText && this.textInTextBuffer) {
            completeCurrentTextNode();
        }
        this.elementStack.popElement();
        this.currentElement = this.elementStack.peekElement();
    }

    public void characters(char[] cArr, int i, int i2) throws SAXException {
        if (i2 != 0 && this.currentElement != null) {
            if (this.insideCDATASection) {
                if (this.mergeAdjacentText && this.textInTextBuffer) {
                    completeCurrentTextNode();
                }
                this.cdataText.append(new String(cArr, i, i2));
            } else if (this.mergeAdjacentText) {
                this.textBuffer.append(cArr, i, i2);
                this.textInTextBuffer = true;
            } else {
                ((DOMElement) this.currentElement).add((Text) new DOMText(new String(cArr, i, i2)));
            }
        }
    }

    public void error(SAXParseException sAXParseException) throws SAXException {
        throw sAXParseException;
    }

    public void fatalError(SAXParseException sAXParseException) throws SAXException {
        throw sAXParseException;
    }

    public void startCDATA() throws SAXException {
        this.insideCDATASection = true;
        this.cdataText = new StringBuffer();
    }

    public void endCDATA() throws SAXException {
        this.insideCDATASection = false;
        ((DOMElement) this.currentElement).add((CDATA) new DOMCDATA(this.cdataText.toString()));
    }

    public void comment(char[] cArr, int i, int i2) throws SAXException {
        if (!this.ignoreComments) {
            if (this.mergeAdjacentText && this.textInTextBuffer) {
                completeCurrentTextNode();
            }
            String str = new String(cArr, i, i2);
            if (str.length() > 0) {
                DOMComment dOMComment = new DOMComment(str);
                Element element = this.currentElement;
                if (element != null) {
                    ((DOMElement) element).add((Comment) dOMComment);
                } else {
                    getDocument().appendChild(dOMComment);
                }
            }
        }
    }

    public ElementStack getElementStack() {
        return this.elementStack;
    }

    public void setElementStack(ElementStack elementStack2) {
        this.elementStack = elementStack2;
    }

    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }

    public void setEntityResolver(EntityResolver entityResolver2) {
        this.entityResolver = entityResolver2;
    }

    public InputSource getInputSource() {
        return this.inputSource;
    }

    public void setInputSource(InputSource inputSource2) {
        this.inputSource = inputSource2;
    }

    public boolean isMergeAdjacentText() {
        return this.mergeAdjacentText;
    }

    public void setMergeAdjacentText(boolean z) {
        this.mergeAdjacentText = z;
    }

    public boolean isStripWhitespaceText() {
        return this.stripWhitespaceText;
    }

    public void setStripWhitespaceText(boolean z) {
        this.stripWhitespaceText = z;
    }

    public boolean isIgnoreComments() {
        return this.ignoreComments;
    }

    public void setIgnoreComments(boolean z) {
        this.ignoreComments = z;
    }

    /* access modifiers changed from: protected */
    public void completeCurrentTextNode() {
        boolean z;
        if (this.stripWhitespaceText) {
            int length = this.textBuffer.length();
            int i = 0;
            while (true) {
                if (i >= length) {
                    z = true;
                    break;
                } else if (!Character.isWhitespace(this.textBuffer.charAt(i))) {
                    z = false;
                    break;
                } else {
                    i++;
                }
            }
            if (!z) {
                ((DOMElement) this.currentElement).add((Text) new DOMText(this.textBuffer.toString()));
            }
        } else {
            ((DOMElement) this.currentElement).add((Text) new DOMText(this.textBuffer.toString()));
        }
        this.textBuffer.setLength(0);
        this.textInTextBuffer = false;
    }

    /* access modifiers changed from: protected */
    public Document createDocument() {
        Document createDocument = this.documentFactory.createDocument(getEncoding());
        createDocument.setEntityResolver(this.entityResolver);
        InputSource inputSource2 = this.inputSource;
        if (inputSource2 != null) {
            createDocument.setName(inputSource2.getSystemId());
        }
        return createDocument;
    }

    private String getEncoding() {
        Locator locator2 = this.locator;
        if (locator2 != null && (locator2 instanceof Locator2)) {
            return ((Locator2) locator2).getEncoding();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void addDeclaredNamespaces(Element element) {
        int size = this.namespaceStack.size();
        while (true) {
            int i = this.declaredNamespaceIndex;
            if (i < size) {
                Namespace namespace = this.namespaceStack.getNamespace(i);
                ((DOMElement) element).setAttribute(attributeNameForNamespace(namespace), namespace.getURI());
                this.declaredNamespaceIndex++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void addAttributes(Element element, Attributes attributes) {
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            String qName = attributes.getQName(i);
            if (!qName.startsWith("xmlns")) {
                String uri = attributes.getURI(i);
                String localName = attributes.getLocalName(i);
                ((DOMElement) element).setAttributeNode(new DOMAttribute(this.namespaceStack.getAttributeQName(uri, localName, qName), attributes.getValue(i)));
            }
        }
    }

    /* access modifiers changed from: protected */
    public ElementStack createElementStack() {
        return new ElementStack();
    }

    /* access modifiers changed from: protected */
    public String attributeNameForNamespace(Namespace namespace) {
        String prefix = namespace.getPrefix();
        if (prefix.length() <= 0) {
            return "xmlns";
        }
        return "xmlns" + ":" + prefix;
    }
}
