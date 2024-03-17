package org.dom4j.io;

import java.util.ArrayList;
import java.util.HashMap;
import org.dom4j.Element;
import org.dom4j.ElementHandler;
import org.dom4j.ElementPath;

class DispatchHandler implements ElementHandler {
    private boolean atRoot = true;
    private ElementHandler defaultHandler;
    private ArrayList<ElementHandler> handlerStack = new ArrayList<>();
    private HashMap<String, ElementHandler> handlers = new HashMap<>();
    private String path = "/";
    private ArrayList<String> pathStack = new ArrayList<>();

    public void addHandler(String str, ElementHandler elementHandler) {
        this.handlers.put(str, elementHandler);
    }

    public ElementHandler removeHandler(String str) {
        return this.handlers.remove(str);
    }

    public boolean containsHandler(String str) {
        return this.handlers.containsKey(str);
    }

    public ElementHandler getHandler(String str) {
        return this.handlers.get(str);
    }

    public int getActiveHandlerCount() {
        return this.handlerStack.size();
    }

    public void setDefaultHandler(ElementHandler elementHandler) {
        this.defaultHandler = elementHandler;
    }

    public void resetHandlers() {
        this.atRoot = true;
        this.path = "/";
        this.pathStack.clear();
        this.handlerStack.clear();
        this.handlers.clear();
        this.defaultHandler = null;
    }

    public String getPath() {
        return this.path;
    }

    public void onStart(ElementPath elementPath) {
        ElementHandler elementHandler;
        Element current = elementPath.getCurrent();
        this.pathStack.add(this.path);
        if (this.atRoot) {
            this.path += current.getName();
            this.atRoot = false;
        } else {
            this.path += "/" + current.getName();
        }
        HashMap<String, ElementHandler> hashMap = this.handlers;
        if (hashMap != null && hashMap.containsKey(this.path)) {
            ElementHandler elementHandler2 = this.handlers.get(this.path);
            this.handlerStack.add(elementHandler2);
            elementHandler2.onStart(elementPath);
        } else if (this.handlerStack.isEmpty() && (elementHandler = this.defaultHandler) != null) {
            elementHandler.onStart(elementPath);
        }
    }

    public void onEnd(ElementPath elementPath) {
        ElementHandler elementHandler;
        HashMap<String, ElementHandler> hashMap = this.handlers;
        if (hashMap != null && hashMap.containsKey(this.path)) {
            ArrayList<ElementHandler> arrayList = this.handlerStack;
            arrayList.remove(arrayList.size() - 1);
            this.handlers.get(this.path).onEnd(elementPath);
        } else if (this.handlerStack.isEmpty() && (elementHandler = this.defaultHandler) != null) {
            elementHandler.onEnd(elementPath);
        }
        ArrayList<String> arrayList2 = this.pathStack;
        this.path = arrayList2.remove(arrayList2.size() - 1);
        if (this.pathStack.size() == 0) {
            this.atRoot = true;
        }
    }
}
