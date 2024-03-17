package org.dom4j.tree;

import java.util.Iterator;
import java.util.List;
import org.dom4j.Branch;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.IllegalAddException;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.xml.sax.EntityResolver;

public class DefaultDocument extends AbstractDocument {
    private List<Node> content;
    private DocumentType docType;
    private DocumentFactory documentFactory = DocumentFactory.getInstance();
    private transient EntityResolver entityResolver;
    private String name;
    private Element rootElement;

    public DefaultDocument() {
    }

    public DefaultDocument(String str) {
        this.name = str;
    }

    public DefaultDocument(Element element) {
        this.rootElement = element;
    }

    public DefaultDocument(DocumentType documentType) {
        this.docType = documentType;
    }

    public DefaultDocument(Element element, DocumentType documentType) {
        this.rootElement = element;
        this.docType = documentType;
    }

    public DefaultDocument(String str, Element element, DocumentType documentType) {
        this.name = str;
        this.rootElement = element;
        this.docType = documentType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public Element getRootElement() {
        return this.rootElement;
    }

    public DocumentType getDocType() {
        return this.docType;
    }

    public void setDocType(DocumentType documentType) {
        this.docType = documentType;
    }

    public Document addDocType(String str, String str2, String str3) {
        setDocType(getDocumentFactory().createDocType(str, str2, str3));
        return this;
    }

    public String getXMLEncoding() {
        return this.encoding;
    }

    public EntityResolver getEntityResolver() {
        return this.entityResolver;
    }

    public void setEntityResolver(EntityResolver entityResolver2) {
        this.entityResolver = entityResolver2;
    }

    public Object clone() {
        DefaultDocument defaultDocument = (DefaultDocument) super.clone();
        defaultDocument.rootElement = null;
        defaultDocument.content = null;
        defaultDocument.appendContent(this);
        return defaultDocument;
    }

    public List<ProcessingInstruction> processingInstructions() {
        BackedList createResultList = createResultList();
        for (Node next : contentList()) {
            if (next instanceof ProcessingInstruction) {
                createResultList.add((ProcessingInstruction) next);
            }
        }
        return createResultList;
    }

    public List<ProcessingInstruction> processingInstructions(String str) {
        BackedList createResultList = createResultList();
        for (Node next : contentList()) {
            if (next instanceof ProcessingInstruction) {
                ProcessingInstruction processingInstruction = (ProcessingInstruction) next;
                if (str.equals(processingInstruction.getName())) {
                    createResultList.add(processingInstruction);
                }
            }
        }
        return createResultList;
    }

    public ProcessingInstruction processingInstruction(String str) {
        for (Node next : contentList()) {
            if (next instanceof ProcessingInstruction) {
                ProcessingInstruction processingInstruction = (ProcessingInstruction) next;
                if (str.equals(processingInstruction.getName())) {
                    return processingInstruction;
                }
            }
        }
        return null;
    }

    public boolean removeProcessingInstruction(String str) {
        Iterator<Node> it = contentList().iterator();
        while (it.hasNext()) {
            Node next = it.next();
            if ((next instanceof ProcessingInstruction) && str.equals(((ProcessingInstruction) next).getName())) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public void setContent(List<Node> list) {
        this.rootElement = null;
        contentRemoved();
        if (list instanceof ContentListFacade) {
            list = ((ContentListFacade) list).getBackingList();
        }
        if (list == null) {
            this.content = null;
            return;
        }
        List<Node> createContentList = createContentList(list.size());
        Iterator<Node> it = list.iterator();
        while (it.hasNext()) {
            Node next = it.next();
            Document document = next.getDocument();
            if (!(document == null || document == this)) {
                next = (Node) next.clone();
            }
            if (next instanceof Element) {
                if (this.rootElement == null) {
                    this.rootElement = (Element) next;
                } else {
                    throw new IllegalAddException("A document may only contain one root element: " + list);
                }
            }
            createContentList.add(next);
            childAdded(next);
        }
        this.content = createContentList;
    }

    public void clearContent() {
        contentRemoved();
        this.content = null;
        this.rootElement = null;
    }

    public void setDocumentFactory(DocumentFactory documentFactory2) {
        this.documentFactory = documentFactory2;
    }

    /* access modifiers changed from: protected */
    public List<Node> contentList() {
        if (this.content == null) {
            List<Node> createContentList = createContentList();
            this.content = createContentList;
            Element element = this.rootElement;
            if (element != null) {
                createContentList.add(element);
            }
        }
        return this.content;
    }

    /* access modifiers changed from: protected */
    public void addNode(Node node) {
        if (node != null) {
            Document document = node.getDocument();
            if (document == null || document == this) {
                contentList().add(node);
                childAdded(node);
                return;
            }
            throw new IllegalAddException((Branch) this, node, "The Node already has an existing document: " + document);
        }
    }

    /* access modifiers changed from: protected */
    public void addNode(int i, Node node) {
        if (node != null) {
            Document document = node.getDocument();
            if (document == null || document == this) {
                contentList().add(i, node);
                childAdded(node);
                return;
            }
            throw new IllegalAddException((Branch) this, node, "The Node already has an existing document: " + document);
        }
    }

    /* access modifiers changed from: protected */
    public boolean removeNode(Node node) {
        if (node == this.rootElement) {
            this.rootElement = null;
        }
        if (!contentList().remove(node)) {
            return false;
        }
        childRemoved(node);
        return true;
    }

    /* access modifiers changed from: protected */
    public void rootElementAdded(Element element) {
        this.rootElement = element;
        element.setDocument(this);
    }

    /* access modifiers changed from: protected */
    public DocumentFactory getDocumentFactory() {
        return this.documentFactory;
    }
}
