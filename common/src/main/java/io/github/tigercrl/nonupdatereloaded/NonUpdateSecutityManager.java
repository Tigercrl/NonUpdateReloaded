package io.github.tigercrl.nonupdatereloaded;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.security.Permission;


public class NonUpdateSecutityManager extends SecurityManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Unsafe unsafe = ReflectionHelper.getUnsafe();

    @Override
    public void checkPermission(Permission perm) {
    }

    @Override
    public void checkPermission(Permission perm, Object context) {
        checkPermission(perm);
    }

    public boolean isBlocked(String host, int port) {
        String address = host + (port > 0 ? ":" + port : "");
        if (NonUpdateReloaded.isTempWhitelisted(address))
            return false;
        boolean blocked = true;
        for (String s : NonUpdateReloaded.config.list) {
            if (s.startsWith("$r")) {
                s = s.substring(2);
                if (host.matches(s) || address.matches(s)) {
                    blocked = false;
                    break;
                }
            } else if (s.startsWith("$e")) {
                s = s.substring(2);
                if (host.endsWith(s) || address.endsWith(s)) {
                    blocked = false;
                    break;
                }
            } else if (s.startsWith("$s")) {
                s = s.substring(2);
                if (host.startsWith(s) || address.startsWith(s)) {
                    blocked = false;
                    break;
                }
            } else if (s.startsWith("$c")) {
                s = s.substring(2);
                if (host.contains(s) || address.contains(s)) {
                    blocked = false;
                    break;
                }
            } else {
                if (s.equals(address) || s.equals(host)) {
                    blocked = false;
                    break;
                }
            }
        }
        if (!NonUpdateReloaded.config.isWhitelist)
            blocked = !blocked;
        return blocked;
    }

    @Override
    public void checkConnect(String host, int port) {
        if (isBlocked(host, port)) {
            LOGGER.info("Blocked connection to {}{}", host, port > 0 ? ":" + port : "");
            coverString(host, "0.0.0.0");
        }
    }

    @Override
    public void checkConnect(String host, int port, Object context) {
        checkConnect(host, port);
    }

    public static void coverString(String src, String to) {
        try {
            Field value = String.class.getDeclaredField("value");
            value.setAccessible(true);
            unsafe.putObject(src, unsafe.objectFieldOffset(value), value.get(to));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
