package org.dom4j.dom;

import org.dom4j.CDATA;
import org.dom4j.Element;
import org.dom4j.tree.DefaultCDATA;
import org.w3c.dom.CDATASection;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.UserDataHandler;

public class DOMCDATA extends DefaultCDATA implements CDATASection {
    public NamedNodeMap getAttributes() {
        return null;
    }

    public String getNodeName() {
        return "#cdata-section";
    }

    public DOMCDATA(String str) {
        super(str);
    }

    public DOMCDATA(Element element, String str) {
        super(element, str);
    }

    public boolean supports(String str, String str2) {
        return DOMNodeHelper.supports(this, str, str2);
    }

    public String getNamespaceURI() {
        return DOMNodeHelper.getNamespaceURI(this);
    }

    public String getPrefix() {
        return DOMNodeHelper.getPrefix(this);
    }

    public void setPrefix(String str) throws DOMException {
        DOMNodeHelper.setPrefix(this, str);
    }

    public String getLocalName() {
        return DOMNodeHelper.getLocalName(this);
    }

    public String getNodeValue() throws DOMException {
        return DOMNodeHelper.getNodeValue(this);
    }

    public void setNodeValue(String str) throws DOMException {
        DOMNodeHelper.setNodeValue(this, str);
    }

    public Node getParentNode() {
        return DOMNodeHelper.getParentNode(this);
    }

    public NodeList getChildNodes() {
        return DOMNodeHelper.getChildNodes(this);
    }

    public Node getFirstChild() {
        return DOMNodeHelper.getFirstChild(this);
    }

    public Node getLastChild() {
        return DOMNodeHelper.getLastChild(this);
    }

    public Node getPreviousSibling() {
        return DOMNodeHelper.getPreviousSibling(this);
    }

    public Node getNextSibling() {
        return DOMNodeHelper.getNextSibling(this);
    }

    public Document getOwnerDocument() {
        return DOMNodeHelper.getOwnerDocument(this);
    }

    public Node insertBefore(Node node, Node node2) throws DOMException {
        checkNewChildNode(node);
        return DOMNodeHelper.insertBefore(this, node, node2);
    }

    public Node replaceChild(Node node, Node node2) throws DOMException {
        checkNewChildNode(node);
        return DOMNodeHelper.replaceChild(this, node, node2);
    }

    public Node removeChild(Node node) throws DOMException {
        return DOMNodeHelper.removeChild(this, node);
    }

    public Node appendChild(Node node) throws DOMException {
        checkNewChildNode(node);
        return DOMNodeHelper.appendChild(this, node);
    }

    private void checkNewChildNode(Node node) throws DOMException {
        throw new DOMException(3, "CDATASection nodes cannot have children");
    }

    public boolean hasChildNodes() {
        return DOMNodeHelper.hasChildNodes(this);
    }

    public Node cloneNode(boolean z) {
        return DOMNodeHelper.cloneNode(this, z);
    }

    public void normalize() {
        DOMNodeHelper.normalize(this);
    }

    public boolean isSupported(String str, String str2) {
        return DOMNodeHelper.isSupported(this, str, str2);
    }

    public boolean hasAttributes() {
        return DOMNodeHelper.hasAttributes(this);
    }

    public String getData() throws DOMException {
        return DOMNodeHelper.getData(this);
    }

    public void setData(String str) throws DOMException {
        DOMNodeHelper.setData(this, str);
    }

    public int getLength() {
        return DOMNodeHelper.getLength(this);
    }

    public String substringData(int i, int i2) throws DOMException {
        return DOMNodeHelper.substringData(this, i, i2);
    }

    public void appendData(String str) throws DOMException {
        DOMNodeHelper.appendData(this, str);
    }

    public void insertData(int i, String str) throws DOMException {
        DOMNodeHelper.insertData(this, i, str);
    }

    public void deleteData(int i, int i2) throws DOMException {
        DOMNodeHelper.deleteData(this, i, i2);
    }

    public void replaceData(int i, int i2, String str) throws DOMException {
        DOMNodeHelper.replaceData(this, i, i2, str);
    }

    public Text splitText(int i) throws DOMException {
        if (!isReadOnly()) {
            String text = getText();
            int length = text != null ? text.length() : 0;
            if (i < 0 || i >= length) {
                throw new DOMException(1, "No text at offset: " + i);
            }
            String substring = text.substring(0, i);
            String substring2 = text.substring(i);
            setText(substring);
            Element parent = getParent();
            CDATA createCDATA = createCDATA(substring2);
            if (parent != null) {
                parent.add(createCDATA);
            }
            return DOMNodeHelper.asDOMText(createCDATA);
        }
        throw new DOMException(7, "CharacterData node is read only: " + this);
    }

    /* access modifiers changed from: protected */
    public CDATA createCDATA(String str) {
        return new DOMCDATA(str);
    }

    public boolean isElementContentWhitespace() {
        DOMNodeHelper.notSupported();
        return false;
    }

    public String getWholeText() {
        DOMNodeHelper.notSupported();
        return null;
    }

    public Text replaceWholeText(String str) throws DOMException {
        DOMNodeHelper.notSupported();
        return null;
    }

    public String getBaseURI() {
        DOMNodeHelper.notSupported();
        return null;
    }

    public short compareDocumentPosition(Node node) throws DOMException {
        DOMNodeHelper.notSupported();
        return 0;
    }

    public String getTextContent() throws DOMException {
        DOMNodeHelper.notSupported();
        return null;
    }

    public void setTextContent(String str) throws DOMException {
        DOMNodeHelper.notSupported();
    }

    public boolean isSameNode(Node node) {
        return DOMNodeHelper.isNodeSame(this, node);
    }

    public String lookupPrefix(String str) {
        DOMNodeHelper.notSupported();
        return null;
    }

    public boolean isDefaultNamespace(String str) {
        DOMNodeHelper.notSupported();
        return false;
    }

    public String lookupNamespaceURI(String str) {
        DOMNodeHelper.notSupported();
        return null;
    }

    public boolean isEqualNode(Node node) {
        return DOMNodeHelper.isNodeEquals(this, node);
    }

    public Object getFeature(String str, String str2) {
        DOMNodeHelper.notSupported();
        return null;
    }

    public Object setUserData(String str, Object obj, UserDataHandler userDataHandler) {
        DOMNodeHelper.notSupported();
        return null;
    }

    public Object getUserData(String str) {
        DOMNodeHelper.notSupported();
        return null;
    }
}