package io.github.tigercrl.nonupdatereloaded.mixin;

import io.github.tigercrl.nonupdatereloaded.NonUpdateReloaded;
import net.minecraft.client.gui.screens.ConnectScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConnectScreen.class)
public class ConnectScreenMixin {

    @Inject(method = "connect", at = @At("HEAD"))
    private void connect(String string, int i, CallbackInfo ci) {
        if (NonUpdateReloaded.config.allowServerConnects)
            NonUpdateReloaded.addTempWhitelist(string + ":" + i);
    }
}
