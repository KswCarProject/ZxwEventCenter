package org.dom4j.tree;

import java.util.AbstractList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.dom4j.IllegalAddException;
import org.dom4j.Node;

public class ContentListFacade<T extends Node> extends AbstractList<T> {
    private AbstractBranch branch;
    private List<T> branchContent;

    public ContentListFacade(AbstractBranch abstractBranch, List<T> list) {
        this.branch = abstractBranch;
        this.branchContent = list;
    }

    public boolean add(T t) {
        this.branch.childAdded(t);
        return this.branchContent.add(t);
    }

    public void add(int i, T t) {
        this.branch.childAdded(t);
        this.branchContent.add(i, t);
    }

    public T set(int i, T t) {
        this.branch.childAdded(t);
        return (Node) this.branchContent.set(i, t);
    }

    public boolean remove(Object obj) {
        this.branch.childRemoved(asNode(obj));
        return this.branchContent.remove(obj);
    }

    public T remove(int i) {
        T t = (Node) this.branchContent.remove(i);
        if (t != null) {
            this.branch.childRemoved(t);
        }
        return t;
    }

    public boolean addAll(Collection<? extends T> collection) {
        int size = this.branchContent.size();
        Iterator<? extends T> it = collection.iterator();
        while (it.hasNext()) {
            add((Node) it.next());
            size++;
        }
        return size == this.branchContent.size();
    }

    public boolean addAll(int i, Collection<? extends T> collection) {
        int size = this.branchContent.size();
        Iterator<? extends T> it = collection.iterator();
        while (it.hasNext()) {
            add(i, (Node) it.next());
            size--;
            i++;
        }
        return size == this.branchContent.size();
    }

    public void clear() {
        Iterator it = iterator();
        while (it.hasNext()) {
            this.branch.childRemoved((Node) it.next());
        }
        this.branchContent.clear();
    }

    public boolean removeAll(Collection<?> collection) {
        for (Object asNode : collection) {
            this.branch.childRemoved(asNode(asNode));
        }
        return this.branchContent.removeAll(collection);
    }

    public int size() {
        return this.branchContent.size();
    }

    public boolean isEmpty() {
        return this.branchContent.isEmpty();
    }

    public boolean contains(Object obj) {
        return this.branchContent.contains(obj);
    }

    public Object[] toArray() {
        return this.branchContent.toArray();
    }

    public Object[] toArray(Object[] objArr) {
        return this.branchContent.toArray(objArr);
    }

    public boolean containsAll(Collection<?> collection) {
        return this.branchContent.containsAll(collection);
    }

    public T get(int i) {
        return (Node) this.branchContent.get(i);
    }

    public int indexOf(Object obj) {
        return this.branchContent.indexOf(obj);
    }

    public int lastIndexOf(Object obj) {
        return this.branchContent.lastIndexOf(obj);
    }

    /* access modifiers changed from: protected */
    public Node asNode(Object obj) {
        if (obj instanceof Node) {
            return (Node) obj;
        }
        throw new IllegalAddException("This list must contain instances of Node. Invalid type: " + obj);
    }

    /* access modifiers changed from: protected */
    public List<T> getBackingList() {
        return this.branchContent;
    }
}
