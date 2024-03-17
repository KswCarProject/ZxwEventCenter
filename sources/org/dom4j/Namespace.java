package org.dom4j;

import com.example.mylibrary.BuildConfig;
import org.dom4j.tree.AbstractNode;
import org.dom4j.tree.DefaultNamespace;
import org.dom4j.tree.NamespaceCache;

public class Namespace extends AbstractNode {
    protected static final NamespaceCache CACHE;
    public static final Namespace NO_NAMESPACE;
    public static final Namespace XML_NAMESPACE;
    private int hashCode;
    private String prefix;
    private String uri;

    public short getNodeType() {
        return 13;
    }

    static {
        NamespaceCache namespaceCache = new NamespaceCache();
        CACHE = namespaceCache;
        XML_NAMESPACE = namespaceCache.get("xml", "http://www.w3.org/XML/1998/namespace");
        NO_NAMESPACE = namespaceCache.get(BuildConfig.FLAVOR, BuildConfig.FLAVOR);
    }

    public Namespace(String str, String str2) {
        str = str == null ? BuildConfig.FLAVOR : str;
        this.prefix = str;
        this.uri = str2 == null ? BuildConfig.FLAVOR : str2;
        if (!str.isEmpty()) {
            QName.validateNCName(this.prefix);
        }
    }

    public static Namespace get(String str, String str2) {
        return CACHE.get(str, str2);
    }

    public static Namespace get(String str) {
        return CACHE.get(str);
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            this.hashCode = createHashCode();
        }
        return this.hashCode;
    }

    /* access modifiers changed from: protected */
    public int createHashCode() {
        int hashCode2 = this.uri.hashCode() ^ this.prefix.hashCode();
        if (hashCode2 == 0) {
            return 47806;
        }
        return hashCode2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Namespace) {
            Namespace namespace = (Namespace) obj;
            if (hashCode() != namespace.hashCode() || !this.uri.equals(namespace.getURI()) || !this.prefix.equals(namespace.getPrefix())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public String getText() {
        return this.uri;
    }

    public String getStringValue() {
        return this.uri;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public String getURI() {
        return this.uri;
    }

    public String getXPathNameStep() {
        String str = this.prefix;
        if (str == null || BuildConfig.FLAVOR.equals(str)) {
            return "namespace::*[name()='']";
        }
        return "namespace::" + this.prefix;
    }

    public String getPath(Element element) {
        StringBuffer stringBuffer = new StringBuffer(10);
        Element parent = getParent();
        if (!(parent == null || parent == element)) {
            stringBuffer.append(parent.getPath(element));
            stringBuffer.append('/');
        }
        stringBuffer.append(getXPathNameStep());
        return stringBuffer.toString();
    }

    public String getUniquePath(Element element) {
        StringBuffer stringBuffer = new StringBuffer(10);
        Element parent = getParent();
        if (!(parent == null || parent == element)) {
            stringBuffer.append(parent.getUniquePath(element));
            stringBuffer.append('/');
        }
        stringBuffer.append(getXPathNameStep());
        return stringBuffer.toString();
    }

    public String toString() {
        return super.toString() + " [Namespace: prefix " + getPrefix() + " mapped to URI \"" + getURI() + "\"]";
    }

    public String asXML() {
        StringBuffer stringBuffer = new StringBuffer(10);
        String prefix2 = getPrefix();
        if (prefix2 == null || prefix2.length() <= 0) {
            stringBuffer.append("xmlns=\"");
        } else {
            stringBuffer.append("xmlns:");
            stringBuffer.append(prefix2);
            stringBuffer.append("=\"");
        }
        stringBuffer.append(getURI());
        stringBuffer.append("\"");
        return stringBuffer.toString();
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /* access modifiers changed from: protected */
    public Node createXPathResult(Element element) {
        return new DefaultNamespace(element, getPrefix(), getURI());
    }
}
