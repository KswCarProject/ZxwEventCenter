package org.dom4j.tree;

import com.example.mylibrary.BuildConfig;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.CDATA;
import org.dom4j.CharacterData;
import org.dom4j.Comment;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Entity;
import org.dom4j.IllegalAddException;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;
import org.dom4j.Text;
import org.dom4j.Visitor;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.xml.sax.Attributes;

public abstract class AbstractElement extends AbstractBranch implements Element {
    private static final DocumentFactory DOCUMENT_FACTORY = DocumentFactory.getInstance();
    protected static final boolean USE_STRINGVALUE_SEPARATOR = false;
    protected static final boolean VERBOSE_TOSTRING = false;

    /* access modifiers changed from: protected */
    public abstract List<Attribute> attributeList();

    /* access modifiers changed from: protected */
    public abstract List<Attribute> attributeList(int i);

    public short getNodeType() {
        return 1;
    }

    public void setData(Object obj) {
    }

    public boolean isRootElement() {
        Document document = getDocument();
        return document != null && document.getRootElement() == this;
    }

    public void setName(String str) {
        setQName(getDocumentFactory().createQName(str));
    }

    public void setNamespace(Namespace namespace) {
        setQName(getDocumentFactory().createQName(getName(), namespace));
    }

    public String getXPathNameStep() {
        String namespaceURI = getNamespaceURI();
        if (namespaceURI == null || namespaceURI.length() == 0) {
            return getName();
        }
        String namespacePrefix = getNamespacePrefix();
        if (namespacePrefix != null && namespacePrefix.length() != 0) {
            return getQualifiedName();
        }
        return "*[name()='" + getName() + "']";
    }

    public String getPath(Element element) {
        if (this == element) {
            return ".";
        }
        Element parent = getParent();
        if (parent == null) {
            return "/" + getXPathNameStep();
        } else if (parent == element) {
            return getXPathNameStep();
        } else {
            return parent.getPath(element) + "/" + getXPathNameStep();
        }
    }

    public String getUniquePath(Element element) {
        int indexOf;
        Element parent = getParent();
        if (parent == null) {
            return "/" + getXPathNameStep();
        }
        StringBuilder sb = new StringBuilder();
        if (parent != element) {
            sb.append(parent.getUniquePath(element));
            sb.append("/");
        }
        sb.append(getXPathNameStep());
        List<Element> elements = parent.elements(getQName());
        if (elements.size() > 1 && (indexOf = elements.indexOf(this)) >= 0) {
            sb.append("[");
            sb.append(Integer.toString(indexOf + 1));
            sb.append("]");
        }
        return sb.toString();
    }

    public String asXML() {
        try {
            StringWriter stringWriter = new StringWriter();
            XMLWriter xMLWriter = new XMLWriter((Writer) stringWriter, new OutputFormat());
            xMLWriter.write((Element) this);
            xMLWriter.flush();
            return stringWriter.toString();
        } catch (IOException e) {
            throw new RuntimeException("IOException while generating textual representation: " + e.getMessage());
        }
    }

    public void write(Writer writer) throws IOException {
        new XMLWriter(writer, new OutputFormat()).write((Element) this);
    }

    public void accept(Visitor visitor) {
        visitor.visit((Element) this);
        int attributeCount = attributeCount();
        for (int i = 0; i < attributeCount; i++) {
            visitor.visit(attribute(i));
        }
        int nodeCount = nodeCount();
        for (int i2 = 0; i2 < nodeCount; i2++) {
            node(i2).accept(visitor);
        }
    }

    public String toString() {
        String namespaceURI = getNamespaceURI();
        if (namespaceURI == null || namespaceURI.length() <= 0) {
            return super.toString() + " [Element: <" + getQualifiedName() + " attributes: " + attributeList() + "/>]";
        }
        return super.toString() + " [Element: <" + getQualifiedName() + " uri: " + namespaceURI + " attributes: " + attributeList() + "/>]";
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

    public Object getData() {
        return getText();
    }

    public Node node(int i) {
        Node node;
        if (i >= 0) {
            List<Node> contentList = contentList();
            if (i < contentList.size() && (node = contentList.get(i)) != null) {
                return node;
            }
        }
        return null;
    }

    public int indexOf(Node node) {
        return contentList().indexOf(node);
    }

    public int nodeCount() {
        return contentList().size();
    }

    public Iterator<Node> nodeIterator() {
        return contentList().iterator();
    }

    public Element element(String str) {
        for (Node next : contentList()) {
            if (next instanceof Element) {
                Element element = (Element) next;
                if (str.equals(element.getName())) {
                    return element;
                }
            }
        }
        return null;
    }

    public Element element(QName qName) {
        for (Node next : contentList()) {
            if (next instanceof Element) {
                Element element = (Element) next;
                if (qName.equals(element.getQName())) {
                    return element;
                }
            }
        }
        return null;
    }

    public Element element(String str, Namespace namespace) {
        return element(getDocumentFactory().createQName(str, namespace));
    }

    public List<Element> elements() {
        BackedList createResultList = createResultList();
        for (Node next : contentList()) {
            if (next instanceof Element) {
                createResultList.addLocal((Element) next);
            }
        }
        return createResultList;
    }

    public List<Element> elements(String str) {
        BackedList createResultList = createResultList();
        for (Node next : contentList()) {
            if (next instanceof Element) {
                Element element = (Element) next;
                if (str.equals(element.getName())) {
                    createResultList.addLocal(element);
                }
            }
        }
        return createResultList;
    }

    public List<Element> elements(QName qName) {
        BackedList createResultList = createResultList();
        for (Node next : contentList()) {
            if (next instanceof Element) {
                Element element = (Element) next;
                if (qName.equals(element.getQName())) {
                    createResultList.addLocal(element);
                }
            }
        }
        return createResultList;
    }

    public List<Element> elements(String str, Namespace namespace) {
        return elements(getDocumentFactory().createQName(str, namespace));
    }

    public Iterator<Element> elementIterator() {
        return elements().iterator();
    }

    public Iterator<Element> elementIterator(String str) {
        return elements(str).iterator();
    }

    public Iterator<Element> elementIterator(QName qName) {
        return elements(qName).iterator();
    }

    public Iterator<Element> elementIterator(String str, Namespace namespace) {
        return elementIterator(getDocumentFactory().createQName(str, namespace));
    }

    public List<Attribute> attributes() {
        return new ContentListFacade(this, attributeList());
    }

    public Iterator<Attribute> attributeIterator() {
        return attributeList().iterator();
    }

    public Attribute attribute(int i) {
        return attributeList().get(i);
    }

    public int attributeCount() {
        return attributeList().size();
    }

    public Attribute attribute(String str) {
        for (Attribute next : attributeList()) {
            if (str.equals(next.getName())) {
                return next;
            }
        }
        return null;
    }

    public Attribute attribute(QName qName) {
        for (Attribute next : attributeList()) {
            if (qName.equals(next.getQName())) {
                return next;
            }
        }
        return null;
    }

    public Attribute attribute(String str, Namespace namespace) {
        return attribute(getDocumentFactory().createQName(str, namespace));
    }

    public void setAttributes(Attributes attributes, NamespaceStack namespaceStack, boolean z) {
        int length = attributes.getLength();
        if (length > 0) {
            DocumentFactory documentFactory = getDocumentFactory();
            if (length == 1) {
                String qName = attributes.getQName(0);
                if (z || !qName.startsWith("xmlns")) {
                    String uri = attributes.getURI(0);
                    String localName = attributes.getLocalName(0);
                    add(documentFactory.createAttribute((Element) this, namespaceStack.getAttributeQName(uri, localName, qName), attributes.getValue(0)));
                    return;
                }
                return;
            }
            List<Attribute> attributeList = attributeList(length);
            attributeList.clear();
            for (int i = 0; i < length; i++) {
                String qName2 = attributes.getQName(i);
                if (z || !qName2.startsWith("xmlns")) {
                    String uri2 = attributes.getURI(i);
                    String localName2 = attributes.getLocalName(i);
                    Attribute createAttribute = documentFactory.createAttribute((Element) this, namespaceStack.getAttributeQName(uri2, localName2, qName2), attributes.getValue(i));
                    attributeList.add(createAttribute);
                    childAdded(createAttribute);
                }
            }
        }
    }

    public String attributeValue(String str) {
        Attribute attribute = attribute(str);
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    public String attributeValue(QName qName) {
        Attribute attribute = attribute(qName);
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    public String attributeValue(String str, String str2) {
        String attributeValue = attributeValue(str);
        return attributeValue != null ? attributeValue : str2;
    }

    public String attributeValue(QName qName, String str) {
        String attributeValue = attributeValue(qName);
        return attributeValue != null ? attributeValue : str;
    }

    public void setAttributeValue(String str, String str2) {
        addAttribute(str, str2);
    }

    public void setAttributeValue(QName qName, String str) {
        addAttribute(qName, str);
    }

    public void add(Attribute attribute) {
        if (attribute.getParent() != null) {
            throw new IllegalAddException((Element) this, (Node) attribute, "The Attribute already has an existing parent \"" + attribute.getParent().getQualifiedName() + "\"");
        } else if (attribute.getValue() == null) {
            Attribute attribute2 = attribute(attribute.getQName());
            if (attribute2 != null) {
                remove(attribute2);
            }
        } else {
            attributeList().add(attribute);
            childAdded(attribute);
        }
    }

    public boolean remove(Attribute attribute) {
        List<Attribute> attributeList = attributeList();
        boolean remove = attributeList.remove(attribute);
        if (remove) {
            childRemoved(attribute);
            return remove;
        }
        Attribute attribute2 = attribute(attribute.getQName());
        if (attribute2 == null) {
            return remove;
        }
        attributeList.remove(attribute2);
        return true;
    }

    public List<ProcessingInstruction> processingInstructions() {
        BackedList createResultList = createResultList();
        for (Node next : contentList()) {
            if (next instanceof ProcessingInstruction) {
                createResultList.addLocal((ProcessingInstruction) next);
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
                    createResultList.addLocal(processingInstruction);
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

    public Node getXPathResult(int i) {
        Node node = node(i);
        return (node == null || node.supportsParent()) ? node : node.asXPathResult(this);
    }

    public Element addAttribute(String str, String str2) {
        Attribute attribute = attribute(str);
        if (str2 != null) {
            if (attribute == null) {
                add(getDocumentFactory().createAttribute((Element) this, str, str2));
            } else if (attribute.isReadOnly()) {
                remove(attribute);
                add(getDocumentFactory().createAttribute((Element) this, str, str2));
            } else {
                attribute.setValue(str2);
            }
        } else if (attribute != null) {
            remove(attribute);
        }
        return this;
    }

    public Element addAttribute(QName qName, String str) {
        Attribute attribute = attribute(qName);
        if (str != null) {
            if (attribute == null) {
                add(getDocumentFactory().createAttribute((Element) this, qName, str));
            } else if (attribute.isReadOnly()) {
                remove(attribute);
                add(getDocumentFactory().createAttribute((Element) this, qName, str));
            } else {
                attribute.setValue(str);
            }
        } else if (attribute != null) {
            remove(attribute);
        }
        return this;
    }

    public Element addCDATA(String str) {
        addNewNode(getDocumentFactory().createCDATA(str));
        return this;
    }

    public Element addComment(String str) {
        addNewNode(getDocumentFactory().createComment(str));
        return this;
    }

    public Element addElement(String str) {
        Namespace namespace;
        String str2;
        Element element;
        DocumentFactory documentFactory = getDocumentFactory();
        int indexOf = str.indexOf(":");
        if (indexOf > 0) {
            String substring = str.substring(0, indexOf);
            str2 = str.substring(indexOf + 1);
            namespace = getNamespaceForPrefix(substring);
            if (namespace == null) {
                throw new IllegalAddException("No such namespace prefix: " + substring + " is in scope on: " + this + " so cannot add element: " + str);
            }
        } else {
            namespace = getNamespaceForPrefix(BuildConfig.FLAVOR);
            str2 = str;
        }
        if (namespace != null) {
            element = documentFactory.createElement(documentFactory.createQName(str2, namespace));
        } else {
            element = documentFactory.createElement(str);
        }
        addNewNode(element);
        return element;
    }

    public Element addEntity(String str, String str2) {
        addNewNode(getDocumentFactory().createEntity(str, str2));
        return this;
    }

    public Element addNamespace(String str, String str2) {
        addNewNode(getDocumentFactory().createNamespace(str, str2));
        return this;
    }

    public Element addProcessingInstruction(String str, String str2) {
        addNewNode(getDocumentFactory().createProcessingInstruction(str, str2));
        return this;
    }

    public Element addProcessingInstruction(String str, Map<String, String> map) {
        addNewNode(getDocumentFactory().createProcessingInstruction(str, map));
        return this;
    }

    public Element addText(String str) {
        addNewNode(getDocumentFactory().createText(str));
        return this;
    }

    public void add(Node node) {
        short nodeType = node.getNodeType();
        if (nodeType == 1) {
            add((Element) node);
        } else if (nodeType == 2) {
            add((Attribute) node);
        } else if (nodeType == 3) {
            add((Text) node);
        } else if (nodeType == 4) {
            add((CDATA) node);
        } else if (nodeType == 5) {
            add((Entity) node);
        } else if (nodeType == 7) {
            add((ProcessingInstruction) node);
        } else if (nodeType == 8) {
            add((Comment) node);
        } else if (nodeType != 13) {
            invalidNodeTypeAddException(node);
        } else {
            add((Namespace) node);
        }
    }

    public boolean remove(Node node) {
        short nodeType = node.getNodeType();
        if (nodeType == 1) {
            return remove((Element) node);
        }
        if (nodeType == 2) {
            return remove((Attribute) node);
        }
        if (nodeType == 3) {
            return remove((Text) node);
        }
        if (nodeType == 4) {
            return remove((CDATA) node);
        }
        if (nodeType == 5) {
            return remove((Entity) node);
        }
        if (nodeType == 7) {
            return remove((ProcessingInstruction) node);
        }
        if (nodeType == 8) {
            return remove((Comment) node);
        }
        if (nodeType != 13) {
            return false;
        }
        return remove((Namespace) node);
    }

    public void add(CDATA cdata) {
        addNode(cdata);
    }

    public void add(Comment comment) {
        addNode(comment);
    }

    public void add(Element element) {
        addNode(element);
    }

    public void add(Entity entity) {
        addNode(entity);
    }

    public void add(Namespace namespace) {
        addNode(namespace);
    }

    public void add(ProcessingInstruction processingInstruction) {
        addNode(processingInstruction);
    }

    public void add(Text text) {
        addNode(text);
    }

    public boolean remove(CDATA cdata) {
        return removeNode(cdata);
    }

    public boolean remove(Comment comment) {
        return removeNode(comment);
    }

    public boolean remove(Element element) {
        return removeNode(element);
    }

    public boolean remove(Entity entity) {
        return removeNode(entity);
    }

    public boolean remove(Namespace namespace) {
        return removeNode(namespace);
    }

    public boolean remove(ProcessingInstruction processingInstruction) {
        return removeNode(processingInstruction);
    }

    public boolean remove(Text text) {
        return removeNode(text);
    }

    public boolean hasMixedContent() {
        List<Node> contentList = contentList();
        if (contentList != null && !contentList.isEmpty() && contentList.size() >= 2) {
            Class<?> cls = null;
            for (Node node : contentList) {
                Class<?> cls2 = node.getClass();
                if (cls2 != cls) {
                    if (cls != null) {
                        return true;
                    }
                    cls = cls2;
                }
            }
        }
        return false;
    }

    public boolean isTextOnly() {
        List<Node> contentList = contentList();
        if (contentList != null && !contentList.isEmpty()) {
            for (Node node : contentList) {
                if (!(node instanceof CharacterData)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void setText(String str) {
        List<Node> contentList = contentList();
        if (contentList != null) {
            Iterator<Node> it = contentList.iterator();
            while (it.hasNext()) {
                short nodeType = it.next().getNodeType();
                if (nodeType == 3 || nodeType == 4 || nodeType == 5) {
                    it.remove();
                }
            }
        }
        addText(str);
    }

    public String getStringValue() {
        List<Node> contentList = contentList();
        int size = contentList.size();
        if (size <= 0) {
            return BuildConfig.FLAVOR;
        }
        if (size == 1) {
            return getContentAsStringValue(contentList.get(0));
        }
        StringBuilder sb = new StringBuilder();
        for (Node contentAsStringValue : contentList) {
            String contentAsStringValue2 = getContentAsStringValue(contentAsStringValue);
            if (contentAsStringValue2.length() > 0) {
                sb.append(contentAsStringValue2);
            }
        }
        return sb.toString();
    }

    public void normalize() {
        List<Node> contentList = contentList();
        int i = 0;
        while (true) {
            Text text = null;
            while (i < contentList.size()) {
                Node node = contentList.get(i);
                if (node instanceof Text) {
                    Text text2 = (Text) node;
                    if (text != null) {
                        text.appendText(text2.getText());
                        remove(text2);
                    } else {
                        String text3 = text2.getText();
                        if (text3 == null || text3.length() <= 0) {
                            remove(text2);
                        } else {
                            i++;
                            text = text2;
                        }
                    }
                } else {
                    if (node instanceof Element) {
                        ((Element) node).normalize();
                    }
                    i++;
                }
            }
            return;
        }
    }

    public String elementText(String str) {
        Element element = element(str);
        if (element != null) {
            return element.getText();
        }
        return null;
    }

    public String elementText(QName qName) {
        Element element = element(qName);
        if (element != null) {
            return element.getText();
        }
        return null;
    }

    public String elementTextTrim(String str) {
        Element element = element(str);
        if (element != null) {
            return element.getTextTrim();
        }
        return null;
    }

    public String elementTextTrim(QName qName) {
        Element element = element(qName);
        if (element != null) {
            return element.getTextTrim();
        }
        return null;
    }

    public void appendAttributes(Element element) {
        int attributeCount = element.attributeCount();
        for (int i = 0; i < attributeCount; i++) {
            Attribute attribute = element.attribute(i);
            if (attribute.supportsParent()) {
                addAttribute(attribute.getQName(), attribute.getValue());
            } else {
                add(attribute);
            }
        }
    }

    public Element createCopy() {
        Element createElement = createElement(getQName());
        createElement.appendAttributes(this);
        createElement.appendContent(this);
        return createElement;
    }

    public Element createCopy(String str) {
        Element createElement = createElement(str);
        createElement.appendAttributes(this);
        createElement.appendContent(this);
        return createElement;
    }

    public Element createCopy(QName qName) {
        Element createElement = createElement(qName);
        createElement.appendAttributes(this);
        createElement.appendContent(this);
        return createElement;
    }

    public QName getQName(String str) {
        String str2;
        int indexOf = str.indexOf(":");
        if (indexOf > 0) {
            str2 = str.substring(0, indexOf);
            str = str.substring(indexOf + 1);
        } else {
            str2 = BuildConfig.FLAVOR;
        }
        Namespace namespaceForPrefix = getNamespaceForPrefix(str2);
        if (namespaceForPrefix != null) {
            return getDocumentFactory().createQName(str, namespaceForPrefix);
        }
        return getDocumentFactory().createQName(str);
    }

    public Namespace getNamespaceForPrefix(String str) {
        Namespace namespaceForPrefix;
        if (str == null) {
            str = BuildConfig.FLAVOR;
        }
        if (str.equals(getNamespacePrefix())) {
            return getNamespace();
        }
        if (str.equals("xml")) {
            return Namespace.XML_NAMESPACE;
        }
        for (Node next : contentList()) {
            if (next instanceof Namespace) {
                Namespace namespace = (Namespace) next;
                if (str.equals(namespace.getPrefix())) {
                    return namespace;
                }
            }
        }
        Element parent = getParent();
        if (parent != null && (namespaceForPrefix = parent.getNamespaceForPrefix(str)) != null) {
            return namespaceForPrefix;
        }
        if (str.length() == 0) {
            return Namespace.NO_NAMESPACE;
        }
        return null;
    }

    public Namespace getNamespaceForURI(String str) {
        if (str == null || str.length() <= 0) {
            return Namespace.NO_NAMESPACE;
        }
        if (str.equals(getNamespaceURI())) {
            return getNamespace();
        }
        for (Node next : contentList()) {
            if (next instanceof Namespace) {
                Namespace namespace = (Namespace) next;
                if (str.equals(namespace.getURI())) {
                    return namespace;
                }
            }
        }
        return null;
    }

    public List<Namespace> getNamespacesForURI(String str) {
        BackedList createResultList = createResultList();
        for (Node next : contentList()) {
            if (next instanceof Namespace) {
                Namespace namespace = (Namespace) next;
                if (namespace.getURI().equals(str)) {
                    createResultList.addLocal(namespace);
                }
            }
        }
        return createResultList;
    }

    public List<Namespace> declaredNamespaces() {
        BackedList createResultList = createResultList();
        for (Node next : contentList()) {
            if (next instanceof Namespace) {
                createResultList.addLocal((Namespace) next);
            }
        }
        return createResultList;
    }

    public List<Namespace> additionalNamespaces() {
        BackedList createResultList = createResultList();
        for (Node next : contentList()) {
            if (next instanceof Namespace) {
                Namespace namespace = (Namespace) next;
                if (!namespace.equals(getNamespace())) {
                    createResultList.addLocal(namespace);
                }
            }
        }
        return createResultList;
    }

    public List<Namespace> additionalNamespaces(String str) {
        BackedList createResultList = createResultList();
        for (Node next : contentList()) {
            if (next instanceof Namespace) {
                Namespace namespace = (Namespace) next;
                if (!str.equals(namespace.getURI())) {
                    createResultList.addLocal(namespace);
                }
            }
        }
        return createResultList;
    }

    public void ensureAttributesCapacity(int i) {
        if (i > 1) {
            List<Attribute> attributeList = attributeList();
            if (attributeList instanceof ArrayList) {
                ((ArrayList) attributeList).ensureCapacity(i);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Element createElement(String str) {
        return getDocumentFactory().createElement(str);
    }

    /* access modifiers changed from: protected */
    public Element createElement(QName qName) {
        return getDocumentFactory().createElement(qName);
    }

    /* access modifiers changed from: protected */
    public void addNode(Node node) {
        if (node.getParent() == null) {
            addNewNode(node);
            return;
        }
        throw new IllegalAddException((Element) this, node, "The Node already has an existing parent of \"" + node.getParent().getQualifiedName() + "\"");
    }

    /* access modifiers changed from: protected */
    public void addNode(int i, Node node) {
        if (node.getParent() == null) {
            addNewNode(i, node);
            return;
        }
        throw new IllegalAddException((Element) this, node, "The Node already has an existing parent of \"" + node.getParent().getQualifiedName() + "\"");
    }

    /* access modifiers changed from: protected */
    public void addNewNode(Node node) {
        contentList().add(node);
        childAdded(node);
    }

    /* access modifiers changed from: protected */
    public void addNewNode(int i, Node node) {
        contentList().add(i, node);
        childAdded(node);
    }

    /* access modifiers changed from: protected */
    public boolean removeNode(Node node) {
        boolean remove = contentList().remove(node);
        if (remove) {
            childRemoved(node);
        }
        return remove;
    }

    /* access modifiers changed from: protected */
    public void childAdded(Node node) {
        if (node != null) {
            node.setParent(this);
        }
    }

    /* access modifiers changed from: protected */
    public void childRemoved(Node node) {
        if (node != null) {
            node.setParent((Element) null);
            node.setDocument((Document) null);
        }
    }

    /* access modifiers changed from: protected */
    public DocumentFactory getDocumentFactory() {
        DocumentFactory documentFactory;
        QName qName = getQName();
        if (qName == null || (documentFactory = qName.getDocumentFactory()) == null) {
            return DOCUMENT_FACTORY;
        }
        return documentFactory;
    }

    /* access modifiers changed from: protected */
    public List<Attribute> createAttributeList() {
        return createAttributeList(5);
    }

    /* access modifiers changed from: protected */
    public List<Attribute> createAttributeList(int i) {
        return new ArrayList(i);
    }

    /* access modifiers changed from: protected */
    public <T> Iterator<T> createSingleIterator(T t) {
        return new SingleIterator(t);
    }
}
