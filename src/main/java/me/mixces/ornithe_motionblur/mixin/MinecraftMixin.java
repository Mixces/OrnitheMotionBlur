package me.mixces.ornithe_motionblur.mixin;

import me.mixces.ornithe_motionblur.shared.IGameRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.GameRenderer;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {

	@Shadow
	public Screen screen;

	@Shadow
	public GameRenderer gameRenderer;

	@Inject(
		method = "tick",
		at = @At(
			value = "FIELD",
			opcode = Opcodes.GETFIELD,
			target = "Lnet/minecraft/client/Minecraft;screen:Lnet/minecraft/client/gui/screen/Screen;",
			ordinal = 4
		)
	)
	private void ornithe_motionblur$updateScreen(CallbackInfo ci) {
		if (screen == null && !gameRenderer.hasShader()) {
			/* only show if NOT in a menu and another shader isn't already applied */
			((IGameRenderer) gameRenderer).ornithe_motionblur$forceShader();
		}
	}
}
