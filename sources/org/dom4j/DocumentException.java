package org.dom4j;

public class DocumentException extends Exception {
    @Deprecated
    public Throwable getNestedException() {
        return null;
    }

    public DocumentException() {
    }

    public DocumentException(String str) {
        super(str);
    }

    public DocumentException(String str, Throwable th) {
        super(str, th);
    }

    public DocumentException(Throwable th) {
        super(th);
    }
}
