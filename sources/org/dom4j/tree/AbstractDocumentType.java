package org.dom4j.tree;

import com.example.mylibrary.BuildConfig;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import org.dom4j.DocumentType;
import org.dom4j.Element;
import org.dom4j.Visitor;
import org.dom4j.dtd.Decl;

public abstract class AbstractDocumentType extends AbstractNode implements DocumentType {
    public short getNodeType() {
        return 10;
    }

    public String getPath(Element element) {
        return BuildConfig.FLAVOR;
    }

    public String getUniquePath(Element element) {
        return BuildConfig.FLAVOR;
    }

    public String getName() {
        return getElementName();
    }

    public void setName(String str) {
        setElementName(str);
    }

    public String getText() {
        List<Decl> internalDeclarations = getInternalDeclarations();
        if (internalDeclarations == null || internalDeclarations.size() <= 0) {
            return BuildConfig.FLAVOR;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<Decl> it = internalDeclarations.iterator();
        if (it.hasNext()) {
            sb.append(it.next().toString());
            while (it.hasNext()) {
                sb.append("\n");
                sb.append(it.next().toString());
            }
        }
        return sb.toString();
    }

    public String toString() {
        return super.toString() + " [DocumentType: " + asXML() + "]";
    }

    public String asXML() {
        boolean z;
        StringBuilder sb = new StringBuilder("<!DOCTYPE ");
        sb.append(getElementName());
        String publicID = getPublicID();
        if (publicID == null || publicID.length() <= 0) {
            z = false;
        } else {
            sb.append(" PUBLIC \"");
            sb.append(publicID);
            sb.append("\"");
            z = true;
        }
        String systemID = getSystemID();
        if (systemID != null && systemID.length() > 0) {
            if (!z) {
                sb.append(" SYSTEM");
            }
            sb.append(" \"");
            sb.append(systemID);
            sb.append("\"");
        }
        sb.append(">");
        return sb.toString();
    }

    public void write(Writer writer) throws IOException {
        boolean z;
        writer.write("<!DOCTYPE ");
        writer.write(getElementName());
        String publicID = getPublicID();
        if (publicID == null || publicID.length() <= 0) {
            z = false;
        } else {
            writer.write(" PUBLIC \"");
            writer.write(publicID);
            writer.write("\"");
            z = true;
        }
        String systemID = getSystemID();
        if (systemID != null && systemID.length() > 0) {
            if (!z) {
                writer.write(" SYSTEM");
            }
            writer.write(" \"");
            writer.write(systemID);
            writer.write("\"");
        }
        List<Decl> internalDeclarations = getInternalDeclarations();
        if (internalDeclarations != null && internalDeclarations.size() > 0) {
            writer.write(" [");
            for (Decl obj : internalDeclarations) {
                writer.write("\n  ");
                writer.write(obj.toString());
            }
            writer.write("\n]");
        }
        writer.write(">");
    }

    public void accept(Visitor visitor) {
        visitor.visit((DocumentType) this);
    }
}
