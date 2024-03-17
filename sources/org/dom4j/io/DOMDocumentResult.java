package org.dom4j.io;

import javax.xml.transform.sax.SAXResult;
import org.w3c.dom.Document;
import org.xml.sax.ContentHandler;
import org.xml.sax.ext.LexicalHandler;

public class DOMDocumentResult extends SAXResult {
    private DOMSAXContentHandler contentHandler;

    public DOMDocumentResult() {
        this(new DOMSAXContentHandler());
    }

    public DOMDocumentResult(DOMSAXContentHandler dOMSAXContentHandler) {
        this.contentHandler = dOMSAXContentHandler;
        super.setHandler(dOMSAXContentHandler);
        super.setLexicalHandler(this.contentHandler);
    }

    public Document getDocument() {
        return this.contentHandler.getDocument();
    }

    public void setHandler(ContentHandler contentHandler2) {
        if (contentHandler2 instanceof DOMSAXContentHandler) {
            DOMSAXContentHandler dOMSAXContentHandler = (DOMSAXContentHandler) contentHandler2;
            this.contentHandler = dOMSAXContentHandler;
            super.setHandler(dOMSAXContentHandler);
        }
    }

    public void setLexicalHandler(LexicalHandler lexicalHandler) {
        if (lexicalHandler instanceof DOMSAXContentHandler) {
            DOMSAXContentHandler dOMSAXContentHandler = (DOMSAXContentHandler) lexicalHandler;
            this.contentHandler = dOMSAXContentHandler;
            super.setLexicalHandler(dOMSAXContentHandler);
        }
    }
}
