package io.github.tigercrl.nonupdatereloaded;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = NonUpdateReloaded.MOD_ID)
public class ModConfig implements ConfigData {
    public String[] whitelist = new String[0];
    public boolean allowServerConnects = true;
}
