package com.hp.hpl.sparta;

import java.io.PrintStream;

/* compiled from: ParseSource */
class DefaultLog implements ParseLog {
    DefaultLog() {
    }

    public void error(String str, String str2, int i) {
        PrintStream printStream = System.err;
        printStream.println(str2 + "(" + i + "): " + str + " (ERROR)");
    }

    public void warning(String str, String str2, int i) {
        PrintStream printStream = System.out;
        printStream.println(str2 + "(" + i + "): " + str + " (WARNING)");
    }

    public void note(String str, String str2, int i) {
        PrintStream printStream = System.out;
        printStream.println(str2 + "(" + i + "): " + str + " (NOTE)");
    }
}
