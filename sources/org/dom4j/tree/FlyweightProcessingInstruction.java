package org.dom4j.tree;

import com.example.mylibrary.BuildConfig;
import java.util.Collections;
import java.util.Map;
import org.dom4j.Element;
import org.dom4j.Node;

public class FlyweightProcessingInstruction extends AbstractProcessingInstruction {
    protected String target;
    protected String text;
    protected Map<String, String> values;

    public FlyweightProcessingInstruction() {
    }

    public FlyweightProcessingInstruction(String str, Map<String, String> map) {
        this.target = str;
        this.values = map;
        this.text = toString(map);
    }

    public FlyweightProcessingInstruction(String str, String str2) {
        this.target = str;
        this.text = str2;
        this.values = parseValues(str2);
    }

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String str) {
        throw new UnsupportedOperationException("This PI is read-only and cannot be modified");
    }

    public String getText() {
        return this.text;
    }

    public String getValue(String str) {
        String str2 = this.values.get(str);
        return str2 == null ? BuildConfig.FLAVOR : str2;
    }

    public Map<String, String> getValues() {
        return Collections.unmodifiableMap(this.values);
    }

    /* access modifiers changed from: protected */
    public Node createXPathResult(Element element) {
        return new DefaultProcessingInstruction(element, getTarget(), getText());
    }
}
