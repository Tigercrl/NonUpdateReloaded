package io.github.tigercrl.nonupdatereloaded.fabric;

import io.github.tigercrl.nonupdatereloaded.NonUpdateReloaded;
import net.fabricmc.api.ModInitializer;

public class NonUpdateReloadedFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        NonUpdateReloaded.init();
    }
}