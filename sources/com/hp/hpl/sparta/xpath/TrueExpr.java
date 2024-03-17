package com.hp.hpl.sparta.xpath;

import com.example.mylibrary.BuildConfig;

public class TrueExpr extends BooleanExpr {
    static final TrueExpr INSTANCE = new TrueExpr();

    public String toString() {
        return BuildConfig.FLAVOR;
    }

    private TrueExpr() {
    }

    public void accept(BooleanExprVisitor booleanExprVisitor) {
        booleanExprVisitor.visit(this);
    }
}
