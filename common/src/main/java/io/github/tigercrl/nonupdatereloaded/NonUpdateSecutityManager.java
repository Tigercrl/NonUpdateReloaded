package io.github.tigercrl.nonupdatereloaded;

import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.security.Permission;
import java.util.Arrays;

import static io.github.tigercrl.nonupdatereloaded.NonUpdateReloaded.config;


public class NonUpdateSecutityManager extends SecurityManager {
    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    public void checkPermission(Permission perm) {
    }

    @Override
    public void checkPermission(Permission perm, Object context) {
        checkPermission(perm);
    }

    @Override
    public void checkConnect(String host, int port) {
        if (Arrays.stream(config.whitelist).noneMatch(host::contains)) {
            LOGGER.info("Blocked connection to " + host + (port > 0 ? ":" + port : ""));
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
            value.set(src, to.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
