package org.dom4j.tree;

import com.example.mylibrary.BuildConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.dom4j.DocumentFactory;
import org.dom4j.Namespace;
import org.dom4j.QName;

public class QNameCache {
    private DocumentFactory documentFactory;
    protected Map<Namespace, Map<String, QName>> namespaceCache = Collections.synchronizedMap(new WeakHashMap());
    protected Map<String, QName> noNamespaceCache = Collections.synchronizedMap(new WeakHashMap());

    public QNameCache() {
    }

    public QNameCache(DocumentFactory documentFactory2) {
        this.documentFactory = documentFactory2;
    }

    public List<QName> getQNames() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.noNamespaceCache) {
            arrayList.addAll(this.noNamespaceCache.values());
        }
        synchronized (this.namespaceCache) {
            for (Map<String, QName> values : this.namespaceCache.values()) {
                arrayList.addAll(values.values());
            }
        }
        return arrayList;
    }

    public QName get(String str) {
        QName qName;
        if (str != null) {
            qName = this.noNamespaceCache.get(str);
        } else {
            qName = null;
            str = BuildConfig.FLAVOR;
        }
        if (qName != null) {
            return qName;
        }
        QName createQName = createQName(str);
        createQName.setDocumentFactory(this.documentFactory);
        this.noNamespaceCache.put(str, createQName);
        return createQName;
    }

    public QName get(String str, Namespace namespace) {
        QName qName;
        Map<String, QName> namespaceCache2 = getNamespaceCache(namespace);
        if (str != null) {
            qName = namespaceCache2.get(str);
        } else {
            qName = null;
            str = BuildConfig.FLAVOR;
        }
        if (qName != null) {
            return qName;
        }
        QName createQName = createQName(str, namespace);
        createQName.setDocumentFactory(this.documentFactory);
        namespaceCache2.put(str, createQName);
        return createQName;
    }

    public QName get(String str, Namespace namespace, String str2) {
        QName qName;
        Map<String, QName> namespaceCache2 = getNamespaceCache(namespace);
        if (str != null) {
            qName = namespaceCache2.get(str);
        } else {
            qName = null;
            str = BuildConfig.FLAVOR;
        }
        if (qName != null) {
            return qName;
        }
        QName createQName = createQName(str, namespace, str2);
        createQName.setDocumentFactory(this.documentFactory);
        namespaceCache2.put(str, createQName);
        return createQName;
    }

    public QName get(String str, String str2) {
        int indexOf = str.indexOf(58);
        if (indexOf < 0) {
            return get(str, Namespace.get(str2));
        }
        if (indexOf != 0) {
            return get(str.substring(indexOf + 1), Namespace.get(str.substring(0, indexOf), str2));
        }
        throw new IllegalArgumentException("Qualified name cannot start with ':'.");
    }

    public QName intern(QName qName) {
        return get(qName.getName(), qName.getNamespace(), qName.getQualifiedName());
    }

    /* access modifiers changed from: protected */
    public Map<String, QName> getNamespaceCache(Namespace namespace) {
        if (namespace == Namespace.NO_NAMESPACE) {
            return this.noNamespaceCache;
        }
        Map<String, QName> map = null;
        if (namespace != null) {
            map = this.namespaceCache.get(namespace);
        }
        if (map != null) {
            return map;
        }
        Map<String, QName> createMap = createMap();
        this.namespaceCache.put(namespace, createMap);
        return createMap;
    }

    /* access modifiers changed from: protected */
    public Map<String, QName> createMap() {
        return Collections.synchronizedMap(new HashMap());
    }

    /* access modifiers changed from: protected */
    public QName createQName(String str) {
        return new QName(str);
    }

    /* access modifiers changed from: protected */
    public QName createQName(String str, Namespace namespace) {
        return new QName(str, namespace);
    }

    /* access modifiers changed from: protected */
    public QName createQName(String str, Namespace namespace, String str2) {
        return new QName(str, namespace, str2);
    }
}
