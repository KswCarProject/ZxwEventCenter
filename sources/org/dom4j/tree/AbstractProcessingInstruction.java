package org.dom4j.tree;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import org.dom4j.Element;
import org.dom4j.ProcessingInstruction;
import org.dom4j.Visitor;

public abstract class AbstractProcessingInstruction extends AbstractNode implements ProcessingInstruction {
    public short getNodeType() {
        return 7;
    }

    public boolean removeValue(String str) {
        return false;
    }

    public String getPath(Element element) {
        Element parent = getParent();
        if (parent == null || parent == element) {
            return "processing-instruction()";
        }
        return parent.getPath(element) + "/processing-instruction()";
    }

    public String getUniquePath(Element element) {
        Element parent = getParent();
        if (parent == null || parent == element) {
            return "processing-instruction()";
        }
        return parent.getUniquePath(element) + "/processing-instruction()";
    }

    public String toString() {
        return super.toString() + " [ProcessingInstruction: &" + getName() + ";]";
    }

    public String asXML() {
        return "<?" + getName() + " " + getText() + "?>";
    }

    public void write(Writer writer) throws IOException {
        writer.write("<?");
        writer.write(getName());
        writer.write(" ");
        writer.write(getText());
        writer.write("?>");
    }

    public void accept(Visitor visitor) {
        visitor.visit((ProcessingInstruction) this);
    }

    public void setValue(String str, String str2) {
        throw new UnsupportedOperationException("This PI is read-only and cannot be modified");
    }

    public void setValues(Map<String, String> map) {
        throw new UnsupportedOperationException("This PI is read-only and cannot be modified");
    }

    public String getName() {
        return getTarget();
    }

    public void setName(String str) {
        setTarget(str);
    }

    /* access modifiers changed from: protected */
    public String toString(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry next : map.entrySet()) {
            sb.append((String) next.getKey());
            sb.append("=\"");
            sb.append((String) next.getValue());
            sb.append("\" ");
        }
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public Map<String, String> parseValues(String str) {
        HashMap hashMap = new HashMap();
        StringTokenizer stringTokenizer = new StringTokenizer(str, " ='\"", true);
        while (stringTokenizer.hasMoreTokens()) {
            String name = getName(stringTokenizer);
            if (stringTokenizer.hasMoreTokens()) {
                hashMap.put(name, getValue(stringTokenizer));
            }
        }
        return hashMap;
    }

    private String getName(StringTokenizer stringTokenizer) {
        StringBuilder sb = new StringBuilder(stringTokenizer.nextToken());
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken = stringTokenizer.nextToken();
            if (nextToken.equals("=")) {
                break;
            }
            sb.append(nextToken);
        }
        return sb.toString().trim();
    }

    private String getValue(StringTokenizer stringTokenizer) {
        String nextToken = stringTokenizer.nextToken();
        StringBuilder sb = new StringBuilder();
        while (stringTokenizer.hasMoreTokens() && !nextToken.equals("'") && !nextToken.equals("\"")) {
            nextToken = stringTokenizer.nextToken();
        }
        while (stringTokenizer.hasMoreTokens()) {
            String nextToken2 = stringTokenizer.nextToken();
            if (nextToken.equals(nextToken2)) {
                break;
            }
            sb.append(nextToken2);
        }
        return sb.toString();
    }
}
