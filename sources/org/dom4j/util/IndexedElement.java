package org.dom4j.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.QName;
import org.dom4j.tree.BackedList;
import org.dom4j.tree.DefaultElement;

public class IndexedElement extends DefaultElement {
    private Map<Object, Attribute> attributeIndex;
    private Map<Object, Object> elementIndex;

    public IndexedElement(String str) {
        super(str);
    }

    public IndexedElement(QName qName) {
        super(qName);
    }

    public IndexedElement(QName qName, int i) {
        super(qName, i);
    }

    public Attribute attribute(String str) {
        return attributeIndex().get(str);
    }

    public Attribute attribute(QName qName) {
        return attributeIndex().get(qName);
    }

    public Element element(String str) {
        return asElement(elementIndex().get(str));
    }

    public Element element(QName qName) {
        return asElement(elementIndex().get(qName));
    }

    public List<Element> elements(String str) {
        return asElementList(elementIndex().get(str));
    }

    public List<Element> elements(QName qName) {
        return asElementList(elementIndex().get(qName));
    }

    /* access modifiers changed from: protected */
    public Element asElement(Object obj) {
        if (obj instanceof Element) {
            return (Element) obj;
        }
        if (obj == null) {
            return null;
        }
        List list = (List) obj;
        if (list.size() >= 1) {
            return (Element) list.get(0);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public List<Element> asElementList(Object obj) {
        if (obj instanceof Element) {
            return createSingleResultList((Element) obj);
        }
        if (obj == null) {
            return createEmptyList();
        }
        BackedList createResultList = createResultList();
        for (Element addLocal : (List) obj) {
            createResultList.addLocal(addLocal);
        }
        return createResultList;
    }

    /* access modifiers changed from: protected */
    public Iterator<Element> asElementIterator(Object obj) {
        return asElementList(obj).iterator();
    }

    /* access modifiers changed from: protected */
    public void addNode(Node node) {
        super.addNode(node);
        if (this.elementIndex != null && (node instanceof Element)) {
            addToElementIndex((Element) node);
        } else if (this.attributeIndex != null && (node instanceof Attribute)) {
            addToAttributeIndex((Attribute) node);
        }
    }

    /* access modifiers changed from: protected */
    public boolean removeNode(Node node) {
        if (!super.removeNode(node)) {
            return false;
        }
        if (this.elementIndex != null && (node instanceof Element)) {
            removeFromElementIndex((Element) node);
            return true;
        } else if (this.attributeIndex == null || !(node instanceof Attribute)) {
            return true;
        } else {
            removeFromAttributeIndex((Attribute) node);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public Map<Object, Attribute> attributeIndex() {
        if (this.attributeIndex == null) {
            this.attributeIndex = createAttributeIndex();
            Iterator<Attribute> attributeIterator = attributeIterator();
            while (attributeIterator.hasNext()) {
                addToAttributeIndex(attributeIterator.next());
            }
        }
        return this.attributeIndex;
    }

    /* access modifiers changed from: protected */
    public Map<Object, Object> elementIndex() {
        if (this.elementIndex == null) {
            this.elementIndex = createElementIndex();
            Iterator<Element> elementIterator = elementIterator();
            while (elementIterator.hasNext()) {
                addToElementIndex(elementIterator.next());
            }
        }
        return this.elementIndex;
    }

    /* access modifiers changed from: protected */
    public Map<Object, Attribute> createAttributeIndex() {
        return createIndex();
    }

    /* access modifiers changed from: protected */
    public Map<Object, Object> createElementIndex() {
        return createIndex();
    }

    /* access modifiers changed from: protected */
    public void addToElementIndex(Element element) {
        QName qName = element.getQName();
        String name = qName.getName();
        addToElementIndex(qName, element);
        addToElementIndex(name, element);
    }

    /* access modifiers changed from: protected */
    public void addToElementIndex(Object obj, Element element) {
        Object obj2 = this.elementIndex.get(obj);
        if (obj2 == null) {
            this.elementIndex.put(obj, element);
        } else if (obj2 instanceof List) {
            ((List) obj2).add(element);
        } else {
            List createList = createList();
            createList.add((Element) obj2);
            createList.add(element);
            this.elementIndex.put(obj, createList);
        }
    }

    /* access modifiers changed from: protected */
    public void removeFromElementIndex(Element element) {
        QName qName = element.getQName();
        String name = qName.getName();
        removeFromElementIndex(qName, element);
        removeFromElementIndex(name, element);
    }

    /* access modifiers changed from: protected */
    public void removeFromElementIndex(Object obj, Element element) {
        Object obj2 = this.elementIndex.get(obj);
        if (obj2 instanceof List) {
            ((List) obj2).remove(element);
        } else {
            this.elementIndex.remove(obj);
        }
    }

    /* access modifiers changed from: protected */
    public void addToAttributeIndex(Attribute attribute) {
        QName qName = attribute.getQName();
        String name = qName.getName();
        addToAttributeIndex(qName, attribute);
        addToAttributeIndex(name, attribute);
    }

    /* access modifiers changed from: protected */
    public void addToAttributeIndex(Object obj, Attribute attribute) {
        if (this.attributeIndex.get(obj) != null) {
            this.attributeIndex.put(obj, attribute);
        }
    }

    /* access modifiers changed from: protected */
    public void removeFromAttributeIndex(Attribute attribute) {
        QName qName = attribute.getQName();
        String name = qName.getName();
        removeFromAttributeIndex(qName, attribute);
        removeFromAttributeIndex(name, attribute);
    }

    /* access modifiers changed from: protected */
    public void removeFromAttributeIndex(Object obj, Attribute attribute) {
        Attribute attribute2 = this.attributeIndex.get(obj);
        if (attribute2 != null && attribute2.equals(attribute)) {
            this.attributeIndex.remove(obj);
        }
    }

    /* access modifiers changed from: protected */
    public <T> Map<Object, T> createIndex() {
        return new HashMap();
    }

    /* access modifiers changed from: protected */
    public <T extends Node> List<T> createList() {
        return new ArrayList();
    }
}
