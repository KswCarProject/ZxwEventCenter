package org.dom4j.tree;

import androidx.appcompat.widget.ActivityChooserView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Node;

public class BackedList<T extends Node> extends ArrayList<T> {
    private AbstractBranch branch;
    private List<Node> branchContent;

    public BackedList(AbstractBranch abstractBranch, List<Node> list) {
        this(abstractBranch, list, list.size());
    }

    public BackedList(AbstractBranch abstractBranch, List<Node> list, int i) {
        super(i);
        this.branch = abstractBranch;
        this.branchContent = list;
    }

    public BackedList(AbstractBranch abstractBranch, List<Node> list, List<T> list2) {
        super(list2);
        this.branch = abstractBranch;
        this.branchContent = list;
    }

    public boolean add(T t) {
        this.branch.addNode(t);
        return super.add(t);
    }

    public void add(int i, T t) {
        int i2;
        int size = size();
        if (i < 0) {
            throw new IndexOutOfBoundsException("Index value: " + i + " is less than zero");
        } else if (i <= size) {
            if (size == 0) {
                i2 = this.branchContent.size();
            } else if (i < size) {
                i2 = this.branchContent.indexOf(get(i));
            } else {
                i2 = this.branchContent.indexOf(get(size - 1)) + 1;
            }
            this.branch.addNode(i2, t);
            super.add(i, t);
        } else {
            throw new IndexOutOfBoundsException("Index value: " + i + " cannot be greater than the size: " + size);
        }
    }

    public T set(int i, T t) {
        int indexOf = this.branchContent.indexOf(get(i));
        if (indexOf < 0) {
            indexOf = i == 0 ? 0 : ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        }
        if (indexOf < this.branchContent.size()) {
            this.branch.removeNode((Node) get(i));
            this.branch.addNode(indexOf, t);
        } else {
            this.branch.removeNode((Node) get(i));
            this.branch.addNode(t);
        }
        this.branch.childAdded(t);
        return (Node) super.set(i, t);
    }

    public boolean remove(Object obj) {
        if (obj instanceof Node) {
            this.branch.removeNode((Node) obj);
        }
        return super.remove(obj);
    }

    public T remove(int i) {
        T t = (Node) super.remove(i);
        if (t != null) {
            this.branch.removeNode(t);
        }
        return t;
    }

    public boolean addAll(Collection<? extends T> collection) {
        ensureCapacity(size() + collection.size());
        int size = size();
        Iterator<? extends T> it = collection.iterator();
        while (it.hasNext()) {
            add((Node) it.next());
            size--;
        }
        return size != 0;
    }

    public boolean addAll(int i, Collection<? extends T> collection) {
        ensureCapacity(size() + collection.size());
        int size = size();
        Iterator<? extends T> it = collection.iterator();
        while (it.hasNext()) {
            add(i, (Node) it.next());
            size--;
            i++;
        }
        return size != 0;
    }

    public void clear() {
        Iterator it = iterator();
        while (it.hasNext()) {
            Node node = (Node) it.next();
            this.branchContent.remove(node);
            this.branch.childRemoved(node);
        }
        super.clear();
    }

    public void addLocal(T t) {
        super.add(t);
    }
}
