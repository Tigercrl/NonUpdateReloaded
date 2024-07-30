package io.github.tigercrl.nonupdatereloaded;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = NonUpdateReloaded.MOD_ID)
public class ModConfig implements ConfigData {
    public boolean isWhitelist = true;
    public String[] list = new String[]{
            "$r.+\\.mojang\\.com"
    };
    public boolean allowServerConnects = true;
}
