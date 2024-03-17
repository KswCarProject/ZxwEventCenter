package org.dom4j.tree;

import com.example.mylibrary.BuildConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import org.dom4j.Branch;
import org.dom4j.Comment;
import org.dom4j.Element;
import org.dom4j.IllegalAddException;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.ProcessingInstruction;
import org.dom4j.QName;

public abstract class AbstractBranch extends AbstractNode implements Branch {
    protected static final int DEFAULT_CONTENT_LIST_SIZE = 5;

    /* access modifiers changed from: protected */
    public abstract void addNode(int i, Node node);

    /* access modifiers changed from: protected */
    public abstract void addNode(Node node);

    /* access modifiers changed from: protected */
    public abstract void childAdded(Node node);

    /* access modifiers changed from: protected */
    public abstract void childRemoved(Node node);

    /* access modifiers changed from: protected */
    public abstract List<Node> contentList();

    public boolean isReadOnly() {
        return false;
    }

    /* access modifiers changed from: protected */
    public abstract boolean removeNode(Node node);

    public boolean hasContent() {
        return nodeCount() > 0;
    }

    public List<Node> content() {
        return new ContentListFacade(this, contentList());
    }

    public String getText() {
        List<Node> contentList = contentList();
        if (contentList == null) {
            return BuildConfig.FLAVOR;
        }
        int size = contentList.size();
        if (size < 1) {
            return BuildConfig.FLAVOR;
        }
        String contentAsText = getContentAsText(contentList.get(0));
        if (size == 1) {
            return contentAsText;
        }
        StringBuilder sb = new StringBuilder(contentAsText);
        for (int i = 1; i < size; i++) {
            sb.append(getContentAsText(contentList.get(i)));
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public String getContentAsText(Object obj) {
        if (!(obj instanceof Node)) {
            return obj instanceof String ? (String) obj : BuildConfig.FLAVOR;
        }
        Node node = (Node) obj;
        short nodeType = node.getNodeType();
        if (nodeType == 3 || nodeType == 4 || nodeType == 5) {
            return node.getText();
        }
        return BuildConfig.FLAVOR;
    }

    /* access modifiers changed from: protected */
    public String getContentAsStringValue(Object obj) {
        if (!(obj instanceof Node)) {
            return obj instanceof String ? (String) obj : BuildConfig.FLAVOR;
        }
        Node node = (Node) obj;
        short nodeType = node.getNodeType();
        if (nodeType == 1 || nodeType == 3 || nodeType == 4 || nodeType == 5) {
            return node.getStringValue();
        }
        return BuildConfig.FLAVOR;
    }

    public String getTextTrim() {
        String text = getText();
        StringBuilder sb = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(text);
        while (stringTokenizer.hasMoreTokens()) {
            sb.append(stringTokenizer.nextToken());
            if (stringTokenizer.hasMoreTokens()) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    public void setProcessingInstructions(List<ProcessingInstruction> list) {
        for (ProcessingInstruction addNode : list) {
            addNode(addNode);
        }
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

    public Element addElement(String str, String str2, String str3) {
        return addElement(getDocumentFactory().createQName(str, Namespace.get(str2, str3)));
    }

    public void add(Node node) {
        short nodeType = node.getNodeType();
        if (nodeType == 1) {
            add((Element) node);
        } else if (nodeType == 7) {
            add((ProcessingInstruction) node);
        } else if (nodeType != 8) {
            invalidNodeTypeAddException(node);
        } else {
            add((Comment) node);
        }
    }

    public boolean remove(Node node) {
        short nodeType = node.getNodeType();
        if (nodeType == 1) {
            return remove((Element) node);
        }
        if (nodeType == 7) {
            return remove((ProcessingInstruction) node);
        }
        if (nodeType == 8) {
            return remove((Comment) node);
        }
        invalidNodeTypeAddException(node);
        return false;
    }

    public void add(Comment comment) {
        addNode(comment);
    }

    public void add(Element element) {
        addNode(element);
    }

    public void add(ProcessingInstruction processingInstruction) {
        addNode(processingInstruction);
    }

    public boolean remove(Comment comment) {
        return removeNode(comment);
    }

    public boolean remove(Element element) {
        return removeNode(element);
    }

    public boolean remove(ProcessingInstruction processingInstruction) {
        return removeNode(processingInstruction);
    }

    public Element elementByID(String str) {
        int nodeCount = nodeCount();
        for (int i = 0; i < nodeCount; i++) {
            Node node = node(i);
            if (node instanceof Element) {
                Element element = (Element) node;
                String elementID = elementID(element);
                if (elementID != null && elementID.equals(str)) {
                    return element;
                }
                Element elementByID = element.elementByID(str);
                if (elementByID != null) {
                    return elementByID;
                }
            }
        }
        return null;
    }

    public void appendContent(Branch branch) {
        int nodeCount = branch.nodeCount();
        for (int i = 0; i < nodeCount; i++) {
            add((Node) branch.node(i).clone());
        }
    }

    public Node node(int i) {
        return contentList().get(i);
    }

    public int nodeCount() {
        return contentList().size();
    }

    public int indexOf(Node node) {
        return contentList().indexOf(node);
    }

    public Iterator<Node> nodeIterator() {
        return contentList().iterator();
    }

    /* access modifiers changed from: protected */
    public String elementID(Element element) {
        return element.attributeValue("ID");
    }

    /* access modifiers changed from: protected */
    public List<Node> createContentList() {
        return new ArrayList(5);
    }

    /* access modifiers changed from: protected */
    public List<Node> createContentList(int i) {
        return new ArrayList(i);
    }

    /* access modifiers changed from: protected */
    public <T extends Node> BackedList<T> createResultList() {
        return new BackedList<>(this, contentList());
    }

    /* access modifiers changed from: protected */
    public <T extends Node> List<T> createSingleResultList(T t) {
        BackedList backedList = new BackedList(this, contentList(), 1);
        backedList.addLocal(t);
        return backedList;
    }

    /* access modifiers changed from: protected */
    public <T extends Node> List<T> createEmptyList() {
        return new BackedList(this, contentList(), 0);
    }

    /* access modifiers changed from: protected */
    public void contentRemoved() {
        for (Node childRemoved : contentList()) {
            childRemoved(childRemoved);
        }
    }

    /* access modifiers changed from: protected */
    public void invalidNodeTypeAddException(Node node) {
        throw new IllegalAddException("Invalid node type. Cannot add node: " + node + " to this branch: " + this);
    }
}
