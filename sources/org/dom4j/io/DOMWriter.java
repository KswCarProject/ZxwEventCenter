package org.dom4j.io;

import java.io.PrintStream;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.CDATA;
import org.dom4j.Comment;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.Namespace;
import org.dom4j.ProcessingInstruction;
import org.dom4j.Text;
import org.dom4j.tree.NamespaceStack;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Node;

public class DOMWriter {
    private static final String[] DEFAULT_DOM_DOCUMENT_CLASSES = {"org.apache.xerces.dom.DocumentImpl", "gnu.xml.dom.DomDocument", "org.apache.crimson.tree.XmlDocument", "com.sun.xml.tree.XmlDocument", "oracle.xml.parser.v2.XMLDocument", "oracle.xml.parser.XMLDocument", "org.dom4j.dom.DOMDocument"};
    private static boolean loggedWarning = false;
    private Class<?> domDocumentClass;
    private NamespaceStack namespaceStack = new NamespaceStack();

    public DOMWriter() {
    }

    public DOMWriter(Class<?> cls) {
        this.domDocumentClass = cls;
    }

    public Class<?> getDomDocumentClass() throws DocumentException {
        Class<?> cls = this.domDocumentClass;
        if (cls == null) {
            String[] strArr = DEFAULT_DOM_DOCUMENT_CLASSES;
            int length = strArr.length;
            int length2 = strArr.length;
            int i = 0;
            while (i < length2) {
                try {
                    cls = Class.forName(strArr[i], true, DOMWriter.class.getClassLoader());
                    if (cls != null) {
                        break;
                    }
                    i++;
                } catch (Exception unused) {
                }
            }
        }
        return cls;
    }

    public void setDomDocumentClass(Class<?> cls) {
        this.domDocumentClass = cls;
    }

    public void setDomDocumentClassName(String str) throws DocumentException {
        try {
            this.domDocumentClass = Class.forName(str, true, DOMWriter.class.getClassLoader());
        } catch (Exception e) {
            throw new DocumentException("Could not load the DOM Document class: " + str, e);
        }
    }

    public Document write(org.dom4j.Document document) throws DocumentException {
        if (document instanceof Document) {
            return (Document) document;
        }
        resetNamespaceStack();
        Document createDomDocument = createDomDocument(document);
        appendDOMTree(createDomDocument, (Node) createDomDocument, document.content());
        this.namespaceStack.clear();
        return createDomDocument;
    }

    public Document write(org.dom4j.Document document, DOMImplementation dOMImplementation) throws DocumentException {
        if (document instanceof Document) {
            return (Document) document;
        }
        resetNamespaceStack();
        Document createDomDocument = createDomDocument(document, dOMImplementation);
        appendDOMTree(createDomDocument, (Node) createDomDocument, document.content());
        this.namespaceStack.clear();
        return createDomDocument;
    }

    /* access modifiers changed from: protected */
    public void appendDOMTree(Document document, Node node, List<org.dom4j.Node> list) {
        for (org.dom4j.Node next : list) {
            if (next instanceof Element) {
                appendDOMTree(document, node, (Element) next);
            } else if (next instanceof Text) {
                appendDOMTree(document, node, ((Text) next).getText());
            } else if (next instanceof CDATA) {
                appendDOMTree(document, node, (CDATA) next);
            } else if (next instanceof Comment) {
                appendDOMTree(document, node, (Comment) next);
            } else if (next instanceof Entity) {
                appendDOMTree(document, node, (Entity) next);
            } else if (next instanceof ProcessingInstruction) {
                appendDOMTree(document, node, (ProcessingInstruction) next);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void appendDOMTree(Document document, Node node, Element element) {
        org.w3c.dom.Element createElementNS = document.createElementNS(element.getNamespaceURI(), element.getQualifiedName());
        int size = this.namespaceStack.size();
        Namespace namespace = element.getNamespace();
        if (isNamespaceDeclaration(namespace)) {
            this.namespaceStack.push(namespace);
            writeNamespace(createElementNS, namespace);
        }
        List<Namespace> declaredNamespaces = element.declaredNamespaces();
        int size2 = declaredNamespaces.size();
        for (int i = 0; i < size2; i++) {
            Namespace namespace2 = declaredNamespaces.get(i);
            if (isNamespaceDeclaration(namespace2)) {
                this.namespaceStack.push(namespace2);
                writeNamespace(createElementNS, namespace2);
            }
        }
        for (Attribute next : element.attributes()) {
            createElementNS.setAttributeNS(next.getNamespaceURI(), next.getQualifiedName(), next.getValue());
        }
        appendDOMTree(document, (Node) createElementNS, element.content());
        node.appendChild(createElementNS);
        while (this.namespaceStack.size() > size) {
            this.namespaceStack.pop();
        }
    }

    /* access modifiers changed from: protected */
    public void appendDOMTree(Document document, Node node, CDATA cdata) {
        node.appendChild(document.createCDATASection(cdata.getText()));
    }

    /* access modifiers changed from: protected */
    public void appendDOMTree(Document document, Node node, Comment comment) {
        node.appendChild(document.createComment(comment.getText()));
    }

    /* access modifiers changed from: protected */
    public void appendDOMTree(Document document, Node node, String str) {
        node.appendChild(document.createTextNode(str));
    }

    /* access modifiers changed from: protected */
    public void appendDOMTree(Document document, Node node, Entity entity) {
        node.appendChild(document.createEntityReference(entity.getName()));
    }

    /* access modifiers changed from: protected */
    public void appendDOMTree(Document document, Node node, ProcessingInstruction processingInstruction) {
        node.appendChild(document.createProcessingInstruction(processingInstruction.getTarget(), processingInstruction.getText()));
    }

    /* access modifiers changed from: protected */
    public void writeNamespace(org.w3c.dom.Element element, Namespace namespace) {
        element.setAttribute(attributeNameForNamespace(namespace), namespace.getURI());
    }

    /* access modifiers changed from: protected */
    public String attributeNameForNamespace(Namespace namespace) {
        String prefix = namespace.getPrefix();
        if (prefix.length() <= 0) {
            return "xmlns";
        }
        return "xmlns" + ":" + prefix;
    }

    /* access modifiers changed from: protected */
    public Document createDomDocument(org.dom4j.Document document) throws DocumentException {
        Class<?> cls = this.domDocumentClass;
        if (cls != null) {
            try {
                return (Document) cls.newInstance();
            } catch (Exception e) {
                throw new DocumentException("Could not instantiate an instance of DOM Document with class: " + this.domDocumentClass.getName(), e);
            }
        } else {
            Document createDomDocumentViaJAXP = createDomDocumentViaJAXP();
            if (createDomDocumentViaJAXP != null) {
                return createDomDocumentViaJAXP;
            }
            Class<?> domDocumentClass2 = getDomDocumentClass();
            try {
                return (Document) domDocumentClass2.newInstance();
            } catch (Exception e2) {
                throw new DocumentException("Could not instantiate an instance of DOM Document with class: " + domDocumentClass2.getName(), e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Document createDomDocumentViaJAXP() throws DocumentException {
        try {
            return JAXPHelper.createDocument(false, true);
        } catch (Throwable th) {
            if (loggedWarning) {
                return null;
            }
            loggedWarning = true;
            if (SAXHelper.isVerboseErrorReporting()) {
                System.out.println("Warning: Caught exception attempting to use JAXP to create a W3C DOM document");
                PrintStream printStream = System.out;
                printStream.println("Warning: Exception was: " + th);
                th.printStackTrace();
                return null;
            }
            System.out.println("Warning: Error occurred using JAXP to create a DOM document.");
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public Document createDomDocument(org.dom4j.Document document, DOMImplementation dOMImplementation) throws DocumentException {
        return dOMImplementation.createDocument((String) null, (String) null, (DocumentType) null);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x000a, code lost:
        r0 = r2.getURI();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isNamespaceDeclaration(org.dom4j.Namespace r2) {
        /*
            r1 = this;
            if (r2 == 0) goto L_0x0020
            org.dom4j.Namespace r0 = org.dom4j.Namespace.NO_NAMESPACE
            if (r2 == r0) goto L_0x0020
            org.dom4j.Namespace r0 = org.dom4j.Namespace.XML_NAMESPACE
            if (r2 == r0) goto L_0x0020
            java.lang.String r0 = r2.getURI()
            if (r0 == 0) goto L_0x0020
            int r0 = r0.length()
            if (r0 <= 0) goto L_0x0020
            org.dom4j.tree.NamespaceStack r0 = r1.namespaceStack
            boolean r2 = r0.contains(r2)
            if (r2 != 0) goto L_0x0020
            r2 = 1
            return r2
        L_0x0020:
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.dom4j.io.DOMWriter.isNamespaceDeclaration(org.dom4j.Namespace):boolean");
    }

    /* access modifiers changed from: protected */
    public void resetNamespaceStack() {
        this.namespaceStack.clear();
        this.namespaceStack.push(Namespace.XML_NAMESPACE);
    }
}