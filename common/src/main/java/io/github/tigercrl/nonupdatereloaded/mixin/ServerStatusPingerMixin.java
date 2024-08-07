package io.github.tigercrl.nonupdatereloaded.mixin;

import io.github.tigercrl.nonupdatereloaded.NonUpdateReloaded;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.ServerStatusPinger;
import net.minecraft.client.multiplayer.resolver.ServerAddress;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.net.InetSocketAddress;

@Mixin(ServerStatusPinger.class)
public class ServerStatusPingerMixin {
    @Inject(method = "pingServer", at = @At("HEAD"))
    private void pingServer(ServerData serverData, Runnable runnable, CallbackInfo ci) {
        if (NonUpdateReloaded.config.allowServerConnects)
            NonUpdateReloaded.addTempWhitelist(serverData.ip);
    }

    @Inject(method = "pingLegacyServer", at = @At("RETURN"))
    private void pingLegacyServer(InetSocketAddress inetSocketAddress, ServerAddress serverAddress, ServerData serverData, CallbackInfo ci) {
        if (NonUpdateReloaded.config.allowServerConnects)
            NonUpdateReloaded.addTempWhitelist(serverAddress.getHost() + ":" + serverAddress.getPort());
    }
}
