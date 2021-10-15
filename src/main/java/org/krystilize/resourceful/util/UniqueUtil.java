package org.krystilize.resourceful.util;

public class UniqueUtil {
    private static volatile int i = 0;

    public static int next() {
        return i++;
    }
}
