package org.dom4j;

import com.example.mylibrary.BuildConfig;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.regex.Pattern;
import org.dom4j.tree.QNameCache;
import org.dom4j.util.SingletonStrategy;

public class QName implements Serializable {
    private static final String NAME_CHAR = "_A-Za-zÀ-ÖØ-öø-˿Ͱ-ͽͿ-῿‌-‍⁰-↏Ⰰ-⿯、-퟿豈-﷏ﷰ-�-.0-9·̀-ͯ‿-⁀";
    private static final String NAME_START_CHAR = "_A-Za-zÀ-ÖØ-öø-˿Ͱ-ͽͿ-῿‌-‍⁰-↏Ⰰ-⿯、-퟿豈-﷏ﷰ-�";
    private static final String NCNAME = "[_A-Za-zÀ-ÖØ-öø-˿Ͱ-ͽͿ-῿‌-‍⁰-↏Ⰰ-⿯、-퟿豈-﷏ﷰ-�][_A-Za-zÀ-ÖØ-öø-˿Ͱ-ͽͿ-῿‌-‍⁰-↏Ⰰ-⿯、-퟿豈-﷏ﷰ-�-.0-9·̀-ͯ‿-⁀]*";
    private static final Pattern RE_NAME = Pattern.compile("[:_A-Za-zÀ-ÖØ-öø-˿Ͱ-ͽͿ-῿‌-‍⁰-↏Ⰰ-⿯、-퟿豈-﷏ﷰ-�][:_A-Za-zÀ-ÖØ-öø-˿Ͱ-ͽͿ-῿‌-‍⁰-↏Ⰰ-⿯、-퟿豈-﷏ﷰ-�-.0-9·̀-ͯ‿-⁀]*");
    private static final Pattern RE_NCNAME = Pattern.compile(NCNAME);
    private static final Pattern RE_QNAME = Pattern.compile("(?:[_A-Za-zÀ-ÖØ-öø-˿Ͱ-ͽͿ-῿‌-‍⁰-↏Ⰰ-⿯、-퟿豈-﷏ﷰ-�][_A-Za-zÀ-ÖØ-öø-˿Ͱ-ͽͿ-῿‌-‍⁰-↏Ⰰ-⿯、-퟿豈-﷏ﷰ-�-.0-9·̀-ͯ‿-⁀]*:)?[_A-Za-zÀ-ÖØ-öø-˿Ͱ-ͽͿ-῿‌-‍⁰-↏Ⰰ-⿯、-퟿豈-﷏ﷰ-�][_A-Za-zÀ-ÖØ-öø-˿Ͱ-ͽͿ-῿‌-‍⁰-↏Ⰰ-⿯、-퟿豈-﷏ﷰ-�-.0-9·̀-ͯ‿-⁀]*");
    private static SingletonStrategy<QNameCache> singleton;
    private DocumentFactory documentFactory;
    private int hashCode;
    private String name;
    private transient Namespace namespace;
    private String qualifiedName;

    /* JADX WARNING: Can't wrap try/catch for region: R(2:3|4) */
    /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|5|6|8) */
    /* JADX WARNING: Code restructure failed: missing block: B:4:?, code lost:
        r1 = java.lang.Class.forName("org.dom4j.util.SimpleSingleton");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0026 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x002a */
    static {
        /*
            java.lang.String r0 = "[:_A-Za-zÀ-ÖØ-öø-˿Ͱ-ͽͿ-῿‌-‍⁰-↏Ⰰ-⿯、-퟿豈-﷏ﷰ-�][:_A-Za-zÀ-ÖØ-öø-˿Ͱ-ͽͿ-῿‌-‍⁰-↏Ⰰ-⿯、-퟿豈-﷏ﷰ-�-.0-9·̀-ͯ‿-⁀]*"
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            RE_NAME = r0
            java.lang.String r0 = "[_A-Za-zÀ-ÖØ-öø-˿Ͱ-ͽͿ-῿‌-‍⁰-↏Ⰰ-⿯、-퟿豈-﷏ﷰ-�][_A-Za-zÀ-ÖØ-öø-˿Ͱ-ͽͿ-῿‌-‍⁰-↏Ⰰ-⿯、-퟿豈-﷏ﷰ-�-.0-9·̀-ͯ‿-⁀]*"
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            RE_NCNAME = r0
            java.lang.String r0 = "(?:[_A-Za-zÀ-ÖØ-öø-˿Ͱ-ͽͿ-῿‌-‍⁰-↏Ⰰ-⿯、-퟿豈-﷏ﷰ-�][_A-Za-zÀ-ÖØ-öø-˿Ͱ-ͽͿ-῿‌-‍⁰-↏Ⰰ-⿯、-퟿豈-﷏ﷰ-�-.0-9·̀-ͯ‿-⁀]*:)?[_A-Za-zÀ-ÖØ-öø-˿Ͱ-ͽͿ-῿‌-‍⁰-↏Ⰰ-⿯、-퟿豈-﷏ﷰ-�][_A-Za-zÀ-ÖØ-öø-˿Ͱ-ͽͿ-῿‌-‍⁰-↏Ⰰ-⿯、-퟿豈-﷏ﷰ-�-.0-9·̀-ͯ‿-⁀]*"
            java.util.regex.Pattern r0 = java.util.regex.Pattern.compile(r0)
            RE_QNAME = r0
            java.lang.String r0 = "org.dom4j.util.SimpleSingleton"
            r1 = 0
            java.lang.String r2 = "org.dom4j.QName.singleton.strategy"
            java.lang.String r2 = java.lang.System.getProperty(r2, r0)     // Catch:{ Exception -> 0x0026 }
            java.lang.Class r1 = java.lang.Class.forName(r2)     // Catch:{ Exception -> 0x0026 }
            goto L_0x002a
        L_0x0026:
            java.lang.Class r1 = java.lang.Class.forName(r0)     // Catch:{ Exception -> 0x002a }
        L_0x002a:
            java.lang.Object r0 = r1.newInstance()     // Catch:{ Exception -> 0x003b }
            org.dom4j.util.SingletonStrategy r0 = (org.dom4j.util.SingletonStrategy) r0     // Catch:{ Exception -> 0x003b }
            singleton = r0     // Catch:{ Exception -> 0x003b }
            java.lang.Class<org.dom4j.tree.QNameCache> r1 = org.dom4j.tree.QNameCache.class
            java.lang.String r1 = r1.getName()     // Catch:{ Exception -> 0x003b }
            r0.setSingletonClassName(r1)     // Catch:{ Exception -> 0x003b }
        L_0x003b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.dom4j.QName.<clinit>():void");
    }

    public QName(String str) {
        this(str, Namespace.NO_NAMESPACE);
    }

    public QName(String str, Namespace namespace2) {
        this.name = str == null ? BuildConfig.FLAVOR : str;
        namespace2 = namespace2 == null ? Namespace.NO_NAMESPACE : namespace2;
        this.namespace = namespace2;
        if (namespace2.equals(Namespace.NO_NAMESPACE)) {
            validateName(this.name);
        } else {
            validateNCName(this.name);
        }
    }

    public QName(String str, Namespace namespace2, String str2) {
        this.name = str == null ? BuildConfig.FLAVOR : str;
        this.qualifiedName = str2;
        this.namespace = namespace2 == null ? Namespace.NO_NAMESPACE : namespace2;
        validateNCName(this.name);
        validateQName(this.qualifiedName);
    }

    public static QName get(String str) {
        return getCache().get(str);
    }

    public static QName get(String str, Namespace namespace2) {
        return getCache().get(str, namespace2);
    }

    public static QName get(String str, String str2, String str3) {
        if ((str2 == null || str2.length() == 0) && str3 == null) {
            return get(str);
        }
        if (str2 == null || str2.length() == 0) {
            return getCache().get(str, Namespace.get(str3));
        }
        if (str3 == null) {
            return get(str);
        }
        return getCache().get(str, Namespace.get(str2, str3));
    }

    public static QName get(String str, String str2) {
        if (str2 == null) {
            return getCache().get(str);
        }
        return getCache().get(str, str2);
    }

    public static QName get(String str, Namespace namespace2, String str2) {
        return getCache().get(str, namespace2, str2);
    }

    public String getName() {
        return this.name;
    }

    public String getQualifiedName() {
        if (this.qualifiedName == null) {
            String namespacePrefix = getNamespacePrefix();
            if (namespacePrefix == null || namespacePrefix.length() <= 0) {
                this.qualifiedName = this.name;
            } else {
                this.qualifiedName = namespacePrefix + ":" + this.name;
            }
        }
        return this.qualifiedName;
    }

    public Namespace getNamespace() {
        return this.namespace;
    }

    public String getNamespacePrefix() {
        Namespace namespace2 = this.namespace;
        if (namespace2 == null) {
            return BuildConfig.FLAVOR;
        }
        return namespace2.getPrefix();
    }

    public String getNamespaceURI() {
        Namespace namespace2 = this.namespace;
        if (namespace2 == null) {
            return BuildConfig.FLAVOR;
        }
        return namespace2.getURI();
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            int hashCode2 = getName().hashCode() ^ getNamespaceURI().hashCode();
            this.hashCode = hashCode2;
            if (hashCode2 == 0) {
                this.hashCode = 47806;
            }
        }
        return this.hashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof QName) {
            QName qName = (QName) obj;
            if (hashCode() != qName.hashCode() || !getName().equals(qName.getName()) || !getNamespaceURI().equals(qName.getNamespaceURI())) {
                return false;
            }
            return true;
        }
        return false;
    }

    public String toString() {
        return super.toString() + " [name: " + getName() + " namespace: \"" + getNamespace() + "\"]";
    }

    public DocumentFactory getDocumentFactory() {
        return this.documentFactory;
    }

    public void setDocumentFactory(DocumentFactory documentFactory2) {
        this.documentFactory = documentFactory2;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.namespace.getPrefix());
        objectOutputStream.writeObject(this.namespace.getURI());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.namespace = Namespace.get((String) objectInputStream.readObject(), (String) objectInputStream.readObject());
    }

    private static QNameCache getCache() {
        return singleton.instance();
    }

    private static void validateName(String str) {
        if (!RE_NAME.matcher(str).matches()) {
            throw new IllegalArgumentException(String.format("Illegal character in name: '%s'.", new Object[]{str}));
        }
    }

    protected static void validateNCName(String str) {
        if (!RE_NCNAME.matcher(str).matches()) {
            throw new IllegalArgumentException(String.format("Illegal character in local name: '%s'.", new Object[]{str}));
        }
    }

    private static void validateQName(String str) {
        if (!RE_QNAME.matcher(str).matches()) {
            throw new IllegalArgumentException(String.format("Illegal character in qualified name: '%s'.", new Object[]{str}));
        }
    }
}
