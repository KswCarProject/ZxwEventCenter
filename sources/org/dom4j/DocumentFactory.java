package org.dom4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import org.dom4j.rule.Pattern;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultCDATA;
import org.dom4j.tree.DefaultComment;
import org.dom4j.tree.DefaultDocument;
import org.dom4j.tree.DefaultDocumentType;
import org.dom4j.tree.DefaultElement;
import org.dom4j.tree.DefaultEntity;
import org.dom4j.tree.DefaultProcessingInstruction;
import org.dom4j.tree.DefaultText;
import org.dom4j.tree.QNameCache;
import org.dom4j.util.SimpleSingleton;
import org.dom4j.util.SingletonStrategy;
import org.dom4j.xpath.DefaultXPath;
import org.dom4j.xpath.XPathPattern;
import org.jaxen.VariableContext;

public class DocumentFactory implements Serializable {
    private static SingletonStrategy<DocumentFactory> singleton;
    protected transient QNameCache cache;
    private Map<String, String> xpathNamespaceURIs;

    private static SingletonStrategy<DocumentFactory> createSingleton() {
        SingletonStrategy<DocumentFactory> singletonStrategy;
        String str = "org.dom4j.DocumentFactory";
        try {
            str = System.getProperty("org.dom4j.factory", str);
        } catch (Exception unused) {
        }
        try {
            singletonStrategy = (SingletonStrategy) Class.forName(System.getProperty("org.dom4j.DocumentFactory.singleton.strategy", "org.dom4j.util.SimpleSingleton")).newInstance();
        } catch (Exception unused2) {
            singletonStrategy = new SimpleSingleton<>();
        }
        singletonStrategy.setSingletonClassName(str);
        return singletonStrategy;
    }

    public DocumentFactory() {
        init();
    }

    public static synchronized DocumentFactory getInstance() {
        DocumentFactory instance;
        synchronized (DocumentFactory.class) {
            if (singleton == null) {
                singleton = createSingleton();
            }
            instance = singleton.instance();
        }
        return instance;
    }

    public Document createDocument() {
        DefaultDocument defaultDocument = new DefaultDocument();
        defaultDocument.setDocumentFactory(this);
        return defaultDocument;
    }

    public Document createDocument(String str) {
        Document createDocument = createDocument();
        createDocument.setXMLEncoding(str);
        return createDocument;
    }

    public Document createDocument(Element element) {
        Document createDocument = createDocument();
        createDocument.setRootElement(element);
        return createDocument;
    }

    public DocumentType createDocType(String str, String str2, String str3) {
        return new DefaultDocumentType(str, str2, str3);
    }

    public Element createElement(QName qName) {
        return new DefaultElement(qName);
    }

    public Element createElement(String str) {
        return createElement(createQName(str));
    }

    public Element createElement(String str, String str2) {
        return createElement(createQName(str, str2));
    }

    public Attribute createAttribute(Element element, QName qName, String str) {
        return new DefaultAttribute(qName, str);
    }

    public Attribute createAttribute(Element element, String str, String str2) {
        return createAttribute(element, createQName(str), str2);
    }

    public CDATA createCDATA(String str) {
        return new DefaultCDATA(str);
    }

    public Comment createComment(String str) {
        return new DefaultComment(str);
    }

    public Text createText(String str) {
        if (str != null) {
            return new DefaultText(str);
        }
        throw new IllegalArgumentException("Adding text to an XML document must not be null");
    }

    public Entity createEntity(String str, String str2) {
        return new DefaultEntity(str, str2);
    }

    public Namespace createNamespace(String str, String str2) {
        return Namespace.get(str, str2);
    }

    public ProcessingInstruction createProcessingInstruction(String str, String str2) {
        return new DefaultProcessingInstruction(str, str2);
    }

    public ProcessingInstruction createProcessingInstruction(String str, Map<String, String> map) {
        return new DefaultProcessingInstruction(str, map);
    }

    public QName createQName(String str, Namespace namespace) {
        return this.cache.get(str, namespace);
    }

    public QName createQName(String str) {
        return this.cache.get(str);
    }

    public QName createQName(String str, String str2, String str3) {
        return this.cache.get(str, Namespace.get(str2, str3));
    }

    public QName createQName(String str, String str2) {
        return this.cache.get(str, str2);
    }

    public XPath createXPath(String str) throws InvalidXPathException {
        DefaultXPath defaultXPath = new DefaultXPath(str);
        Map<String, String> map = this.xpathNamespaceURIs;
        if (map != null) {
            defaultXPath.setNamespaceURIs(map);
        }
        return defaultXPath;
    }

    public XPath createXPath(String str, VariableContext variableContext) {
        XPath createXPath = createXPath(str);
        createXPath.setVariableContext(variableContext);
        return createXPath;
    }

    public NodeFilter createXPathFilter(String str, VariableContext variableContext) {
        XPath createXPath = createXPath(str);
        createXPath.setVariableContext(variableContext);
        return createXPath;
    }

    public NodeFilter createXPathFilter(String str) {
        return createXPath(str);
    }

    public Pattern createPattern(String str) {
        return new XPathPattern(str);
    }

    public List<QName> getQNames() {
        return this.cache.getQNames();
    }

    public Map<String, String> getXPathNamespaceURIs() {
        return this.xpathNamespaceURIs;
    }

    public void setXPathNamespaceURIs(Map<String, String> map) {
        this.xpathNamespaceURIs = map;
    }

    protected static DocumentFactory createSingleton(String str) {
        try {
            return (DocumentFactory) Class.forName(str, true, DocumentFactory.class.getClassLoader()).newInstance();
        } catch (Throwable unused) {
            PrintStream printStream = System.out;
            printStream.println("WARNING: Cannot load DocumentFactory: " + str);
            return new DocumentFactory();
        }
    }

    /* access modifiers changed from: protected */
    public QName intern(QName qName) {
        return this.cache.intern(qName);
    }

    /* access modifiers changed from: protected */
    public QNameCache createQNameCache() {
        return new QNameCache(this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.cache = createQNameCache();
    }
}
