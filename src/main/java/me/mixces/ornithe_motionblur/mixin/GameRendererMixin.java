package me.mixces.ornithe_motionblur.mixin;

import com.mojang.blaze3d.platform.GLX;
import com.mojang.blaze3d.shaders.Uniform;
import me.mixces.ornithe_motionblur.shared.IGameRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.PostChain;
import net.minecraft.client.render.PostPass;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.resource.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.List;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin implements IGameRenderer {

	@Shadow
	private PostChain shaderEffect;

	@Shadow
	private Minecraft minecraft;

	@Shadow
	protected abstract void loadShader(Identifier location);

	@Unique
	private static final net.minecraft.resource.Identifier shader = new Identifier("shaders/post/ornithe_phosphor.json");

	@Override
	public void ornithe_motionblur$forceShader() {
		if (GLX.usePostProcess && minecraft.getCamera() instanceof PlayerEntity) {
			if (shaderEffect != null) {
				shaderEffect.close();
			}

			/* renders our phosphor blur */
			loadShader(shader);

			/* deals with the intensity of the blur*/
			List<PostPass> listShaders = ((PostChainAccessor) shaderEffect).getPasses();
			if (listShaders == null) return;
			for (PostPass pass : listShaders) {
				/* "Phosphor" is the name of the uniform parameter we would want to modify */
				Uniform uniform = pass.getEffect().getUniform("Phosphor");
				if (uniform == null) continue;

				/* original values are 0.95, 0.95, 0.95 */
				uniform.set(0.3f, 0.3f, 0.3f);
				/* lower = higher intensity */
			}
		}
	}
}
