package me.mekb.portalgui.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {
    @Redirect(method = "updateNausea", at = @At(value = "FIELD", target = "Lnet/minecraft/client/MinecraftClient;currentScreen:Lnet/minecraft/client/gui/screen/Screen;"))
    private Screen updateNauseaScreen(MinecraftClient instance) {
        // code from updateNausea:
        //        if (this.inNetherPortal) {
        //            if (this.client.currentScreen != null && !this.client.currentScreen.shouldPause() && !(this.client.currentScreen instanceof DeathScreen)) {
        //                if (this.client.currentScreen instanceof HandledScreen) {
        //                    this.closeHandledScreen();
        //                }
        //                this.client.setScreen(null);
        //            }
        // this will close the current screen if we're in a nether portal (except for download terrain screen, death screen, or when paused)
        // we don't want this, so we make currentScreen null which will prevent this condition
        return null;
    }
}
