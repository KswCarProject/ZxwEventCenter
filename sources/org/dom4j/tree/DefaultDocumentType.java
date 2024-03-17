package org.dom4j.tree;

import java.util.List;
import org.dom4j.dtd.Decl;

public class DefaultDocumentType extends AbstractDocumentType {
    protected String elementName;
    private List<Decl> externalDeclarations;
    private List<Decl> internalDeclarations;
    private String publicID;
    private String systemID;

    public DefaultDocumentType() {
    }

    public DefaultDocumentType(String str, String str2) {
        this.elementName = str;
        this.systemID = str2;
    }

    public DefaultDocumentType(String str, String str2, String str3) {
        this.elementName = str;
        this.publicID = str2;
        this.systemID = str3;
    }

    public String getElementName() {
        return this.elementName;
    }

    public void setElementName(String str) {
        this.elementName = str;
    }

    public String getPublicID() {
        return this.publicID;
    }

    public void setPublicID(String str) {
        this.publicID = str;
    }

    public String getSystemID() {
        return this.systemID;
    }

    public void setSystemID(String str) {
        this.systemID = str;
    }

    public List<Decl> getInternalDeclarations() {
        return this.internalDeclarations;
    }

    public void setInternalDeclarations(List<Decl> list) {
        this.internalDeclarations = list;
    }

    public List<Decl> getExternalDeclarations() {
        return this.externalDeclarations;
    }

    public void setExternalDeclarations(List<Decl> list) {
        this.externalDeclarations = list;
    }
}
