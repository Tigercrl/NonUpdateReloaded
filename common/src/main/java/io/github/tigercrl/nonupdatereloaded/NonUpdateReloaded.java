package io.github.tigercrl.nonupdatereloaded;

import com.mojang.logging.LogUtils;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import org.slf4j.Logger;

import java.util.List;

public class NonUpdateReloaded {
    public static final String MOD_ID = "nonupdate_reloaded";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static ModConfig config;

    public static void init() {
        AutoConfig.register(ModConfig.class, GsonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();
        System.setSecurityManager(new NonUpdateSecutityManager());
        LOGGER.info("NonUpdateReloaded initialized!");
    }
}
