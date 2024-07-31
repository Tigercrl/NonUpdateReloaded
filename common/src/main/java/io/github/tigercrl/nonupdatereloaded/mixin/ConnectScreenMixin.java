package io.github.tigercrl.nonupdatereloaded.mixin;

import io.github.tigercrl.nonupdatereloaded.NonUpdateReloaded;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ConnectScreen.class)
public class ConnectScreenMixin {

    @Inject(method = "connect", at = @At("HEAD"))
    private void connect(Minecraft minecraft, ServerAddress serverAddress, ServerData serverData, CallbackInfo ci) {
        if (NonUpdateReloaded.config.allowServerConnects)
            NonUpdateReloaded.addTempWhitelist(serverAddress.getHost() + ":" + serverAddress.getPort());
    }
}
