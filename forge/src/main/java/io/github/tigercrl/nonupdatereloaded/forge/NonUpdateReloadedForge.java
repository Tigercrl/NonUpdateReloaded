package io.github.tigercrl.nonupdatereloaded.forge;

import io.github.tigercrl.nonupdatereloaded.NonUpdateReloaded;
import net.minecraftforge.fml.common.Mod;

@Mod(NonUpdateReloaded.MOD_ID)
public class NonUpdateReloadedForge {
    public NonUpdateReloadedForge() {
        // Submit our event bus to let architectury register our content on the right time
//        EventBuses.registerModEventBus(NonUpdateReloaded.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        NonUpdateReloaded.init();
    }
}