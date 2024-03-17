package org.dom4j.datatype;

import com.example.mylibrary.BuildConfig;
import com.sun.msv.datatype.xsd.DatatypeFactory;
import com.sun.msv.datatype.xsd.TypeIncubator;
import com.sun.msv.datatype.xsd.XSDatatype;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.QName;
import org.dom4j.io.SAXReader;
import org.dom4j.util.AttributeHelper;
import org.relaxng.datatype.DatatypeException;
import org.relaxng.datatype.ValidationContext;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;

public class SchemaParser {
    private static final QName XSD_ALL;
    private static final QName XSD_ATTRIBUTE;
    private static final QName XSD_CHOICE;
    private static final QName XSD_COMPLEXTYPE;
    private static final QName XSD_ELEMENT;
    private static final QName XSD_INCLUDE;
    private static final Namespace XSD_NAMESPACE;
    private static final QName XSD_RESTRICTION;
    private static final QName XSD_SEQUENCE;
    private static final QName XSD_SIMPLETYPE;
    private Map<String, XSDatatype> dataTypeCache;
    private DatatypeDocumentFactory documentFactory;
    private NamedTypeResolver namedTypeResolver;
    private Namespace targetNamespace;

    static {
        Namespace namespace = Namespace.get("xsd", "http://www.w3.org/2001/XMLSchema");
        XSD_NAMESPACE = namespace;
        XSD_ELEMENT = QName.get("element", namespace);
        XSD_ATTRIBUTE = QName.get("attribute", namespace);
        XSD_SIMPLETYPE = QName.get("simpleType", namespace);
        XSD_COMPLEXTYPE = QName.get("complexType", namespace);
        XSD_RESTRICTION = QName.get("restriction", namespace);
        XSD_SEQUENCE = QName.get("sequence", namespace);
        XSD_CHOICE = QName.get("choice", namespace);
        XSD_ALL = QName.get("all", namespace);
        XSD_INCLUDE = QName.get("include", namespace);
    }

    public SchemaParser() {
        this(DatatypeDocumentFactory.singleton);
    }

    public SchemaParser(DatatypeDocumentFactory datatypeDocumentFactory) {
        this.dataTypeCache = new HashMap();
        this.documentFactory = datatypeDocumentFactory;
        this.namedTypeResolver = new NamedTypeResolver(datatypeDocumentFactory);
    }

    public void build(Document document) {
        this.targetNamespace = null;
        internalBuild(document);
    }

    public void build(Document document, Namespace namespace) {
        this.targetNamespace = namespace;
        internalBuild(document);
    }

    private synchronized void internalBuild(Document document) {
        Element rootElement = document.getRootElement();
        if (rootElement != null) {
            for (Element attributeValue : rootElement.elements(XSD_INCLUDE)) {
                String attributeValue2 = attributeValue.attributeValue("schemaLocation");
                EntityResolver entityResolver = document.getEntityResolver();
                if (entityResolver != null) {
                    try {
                        InputSource resolveEntity = entityResolver.resolveEntity((String) null, attributeValue2);
                        if (resolveEntity != null) {
                            build(new SAXReader().read(resolveEntity));
                        } else {
                            throw new InvalidSchemaException("Could not resolve the schema URI: " + attributeValue2);
                        }
                    } catch (Exception e) {
                        PrintStream printStream = System.out;
                        printStream.println("Failed to load schema: " + attributeValue2);
                        PrintStream printStream2 = System.out;
                        printStream2.println("Caught: " + e);
                        e.printStackTrace();
                        throw new InvalidSchemaException("Failed to load schema: " + attributeValue2);
                    }
                } else {
                    throw new InvalidSchemaException("No EntityResolver available");
                }
            }
            for (Element onDatatypeElement : rootElement.elements(XSD_ELEMENT)) {
                onDatatypeElement(onDatatypeElement, this.documentFactory);
            }
            for (Element onNamedSchemaSimpleType : rootElement.elements(XSD_SIMPLETYPE)) {
                onNamedSchemaSimpleType(onNamedSchemaSimpleType);
            }
            for (Element onNamedSchemaComplexType : rootElement.elements(XSD_COMPLEXTYPE)) {
                onNamedSchemaComplexType(onNamedSchemaComplexType);
            }
            this.namedTypeResolver.resolveNamedTypes();
        }
    }

    private void onDatatypeElement(Element element, DocumentFactory documentFactory2) {
        QName qName;
        XSDatatype loadXSDatatypeFromSimpleType;
        String attributeValue = element.attributeValue("name");
        String attributeValue2 = element.attributeValue("type");
        DatatypeElementFactory datatypeElementFactory = null;
        if (attributeValue != null) {
            QName qName2 = getQName(attributeValue);
            QName qName3 = qName2;
            datatypeElementFactory = getDatatypeElementFactory(qName2);
            qName = qName3;
        } else {
            qName = null;
        }
        if (attributeValue2 != null) {
            XSDatatype typeByName = getTypeByName(attributeValue2);
            if (typeByName == null || datatypeElementFactory == null) {
                this.namedTypeResolver.registerTypedElement(element, getQName(attributeValue2), documentFactory2);
                return;
            }
            datatypeElementFactory.setChildElementXSDatatype(qName, typeByName);
            return;
        }
        Element element2 = element.element(XSD_SIMPLETYPE);
        if (!(element2 == null || (loadXSDatatypeFromSimpleType = loadXSDatatypeFromSimpleType(element2)) == null || datatypeElementFactory == null)) {
            datatypeElementFactory.setChildElementXSDatatype(qName, loadXSDatatypeFromSimpleType);
        }
        Element element3 = element.element(XSD_COMPLEXTYPE);
        if (!(element3 == null || datatypeElementFactory == null)) {
            onSchemaComplexType(element3, datatypeElementFactory);
        }
        if (datatypeElementFactory != null) {
            Iterator<Element> elementIterator = element.elementIterator(XSD_ATTRIBUTE);
            if (elementIterator.hasNext()) {
                do {
                    onDatatypeAttribute(element, datatypeElementFactory, elementIterator.next());
                } while (elementIterator.hasNext());
            }
        }
    }

    private void onNamedSchemaComplexType(Element element) {
        Attribute attribute = element.attribute("name");
        if (attribute != null) {
            QName qName = getQName(attribute.getText());
            DatatypeElementFactory datatypeElementFactory = getDatatypeElementFactory(qName);
            onSchemaComplexType(element, datatypeElementFactory);
            this.namedTypeResolver.registerComplexType(qName, datatypeElementFactory);
        }
    }

    private void onSchemaComplexType(Element element, DatatypeElementFactory datatypeElementFactory) {
        Iterator<Element> elementIterator = element.elementIterator(XSD_ATTRIBUTE);
        while (elementIterator.hasNext()) {
            Element next = elementIterator.next();
            QName qName = getQName(next.attributeValue("name"));
            XSDatatype dataTypeForXsdAttribute = dataTypeForXsdAttribute(next);
            if (dataTypeForXsdAttribute != null) {
                datatypeElementFactory.setAttributeXSDatatype(qName, dataTypeForXsdAttribute);
            }
        }
        Element element2 = element.element(XSD_SEQUENCE);
        if (element2 != null) {
            onChildElements(element2, datatypeElementFactory);
        }
        Element element3 = element.element(XSD_CHOICE);
        if (element3 != null) {
            onChildElements(element3, datatypeElementFactory);
        }
        Element element4 = element.element(XSD_ALL);
        if (element4 != null) {
            onChildElements(element4, datatypeElementFactory);
        }
    }

    private void onChildElements(Element element, DatatypeElementFactory datatypeElementFactory) {
        Iterator<Element> elementIterator = element.elementIterator(XSD_ELEMENT);
        while (elementIterator.hasNext()) {
            onDatatypeElement(elementIterator.next(), datatypeElementFactory);
        }
    }

    private void onDatatypeAttribute(Element element, DatatypeElementFactory datatypeElementFactory, Element element2) {
        String attributeValue = element2.attributeValue("name");
        QName qName = getQName(attributeValue);
        XSDatatype dataTypeForXsdAttribute = dataTypeForXsdAttribute(element2);
        if (dataTypeForXsdAttribute != null) {
            datatypeElementFactory.setAttributeXSDatatype(qName, dataTypeForXsdAttribute);
            return;
        }
        String attributeValue2 = element2.attributeValue("type");
        PrintStream printStream = System.out;
        printStream.println("Warning: Couldn't find XSDatatype for type: " + attributeValue2 + " attribute: " + attributeValue);
    }

    private XSDatatype dataTypeForXsdAttribute(Element element) {
        String attributeValue = element.attributeValue("type");
        if (attributeValue != null) {
            return getTypeByName(attributeValue);
        }
        Element element2 = element.element(XSD_SIMPLETYPE);
        if (element2 != null) {
            return loadXSDatatypeFromSimpleType(element2);
        }
        String attributeValue2 = element.attributeValue("name");
        throw new InvalidSchemaException("The attribute: " + attributeValue2 + " has no type attribute and does not contain a <simpleType/> element");
    }

    private void onNamedSchemaSimpleType(Element element) {
        Attribute attribute = element.attribute("name");
        if (attribute != null) {
            this.namedTypeResolver.registerSimpleType(getQName(attribute.getText()), loadXSDatatypeFromSimpleType(element));
        }
    }

    private XSDatatype loadXSDatatypeFromSimpleType(Element element) {
        Element element2 = element.element(XSD_RESTRICTION);
        if (element2 != null) {
            String attributeValue = element2.attributeValue("base");
            if (attributeValue != null) {
                XSDatatype typeByName = getTypeByName(attributeValue);
                if (typeByName != null) {
                    return deriveSimpleType(typeByName, element2);
                }
                onSchemaError("Invalid base type: " + attributeValue + " when trying to build restriction: " + element2);
                return null;
            }
            Element element3 = element.element(XSD_SIMPLETYPE);
            if (element3 != null) {
                return loadXSDatatypeFromSimpleType(element3);
            }
            onSchemaError("The simpleType element: " + element + " must contain a base attribute or simpleType element");
            return null;
        }
        onSchemaError("No <restriction>. Could not create XSDatatype for simpleType: " + element);
        return null;
    }

    private XSDatatype deriveSimpleType(XSDatatype xSDatatype, Element element) {
        TypeIncubator typeIncubator = new TypeIncubator(xSDatatype);
        try {
            Iterator<Element> elementIterator = element.elementIterator();
            while (elementIterator.hasNext()) {
                Element next = elementIterator.next();
                typeIncubator.addFacet(next.getName(), next.attributeValue("value"), AttributeHelper.booleanValue(next, "fixed"), (ValidationContext) null);
            }
            return typeIncubator.derive(BuildConfig.FLAVOR, (String) null);
        } catch (DatatypeException e) {
            onSchemaError("Invalid restriction: " + e.getMessage() + " when trying to build restriction: " + element);
            return null;
        }
    }

    private DatatypeElementFactory getDatatypeElementFactory(QName qName) {
        DatatypeElementFactory elementFactory = this.documentFactory.getElementFactory(qName);
        if (elementFactory != null) {
            return elementFactory;
        }
        DatatypeElementFactory datatypeElementFactory = new DatatypeElementFactory(qName);
        qName.setDocumentFactory(datatypeElementFactory);
        return datatypeElementFactory;
    }

    private XSDatatype getTypeByName(String str) {
        XSDatatype xSDatatype = this.dataTypeCache.get(str);
        if (xSDatatype == null) {
            int indexOf = str.indexOf(58);
            if (indexOf >= 0) {
                try {
                    xSDatatype = DatatypeFactory.getTypeByName(str.substring(indexOf + 1));
                } catch (DatatypeException unused) {
                }
            }
            if (xSDatatype == null) {
                try {
                    xSDatatype = DatatypeFactory.getTypeByName(str);
                } catch (DatatypeException unused2) {
                }
            }
            if (xSDatatype == null) {
                xSDatatype = this.namedTypeResolver.simpleTypeMap.get(getQName(str));
            }
            if (xSDatatype != null) {
                this.dataTypeCache.put(str, xSDatatype);
            }
        }
        return xSDatatype;
    }

    private QName getQName(String str) {
        Namespace namespace = this.targetNamespace;
        if (namespace == null) {
            return this.documentFactory.createQName(str);
        }
        return this.documentFactory.createQName(str, namespace);
    }

    private void onSchemaError(String str) {
        throw new InvalidSchemaException(str);
    }
}
