package sun.misc;

import java.lang.reflect.Field;

// Fake unsafe
public class Unsafe {
    public static Unsafe getUnsafe() {
        return null;
    }

    public void putObject(Object o, long l, Object o1) {
    }

    public long objectFieldOffset(Field field) {
        return 0;
    }
}
