package io.github.tigercrl.nonupdatereloaded;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class NonUpdateReloaded {
    public static final String MOD_ID = "nonupdate_reloaded";
    private static final Logger LOGGER = LogManager.getLogger();
    public static ModConfig config;
    private static final List<String> tempWhitelist = new ArrayList<>();

    public static void init() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        ReflectionHelper.setSecurityManager(new NonUpdateSecurityManager());
        LOGGER.info("NonUpdateReloaded initialized!");
    }

    public static void addTempWhitelist(String address) {
        if (!tempWhitelist.contains(address)) {
            tempWhitelist.add(address);
            LOGGER.info("Temporarily added server address \"" + address + "\" to whitelist");
        }
    }

    public static boolean isTempWhitelisted(String address) {
        for (String s : tempWhitelist) {
            if (s.equals(address) || s.split(":")[0].equals(address))
                return true;
        }
        return false;
    }
}
