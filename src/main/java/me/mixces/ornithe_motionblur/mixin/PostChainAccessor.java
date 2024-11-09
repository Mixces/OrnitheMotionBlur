package me.mixces.ornithe_motionblur.mixin;

import net.minecraft.client.render.PostChain;
import net.minecraft.client.render.PostPass;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(PostChain.class)
public interface PostChainAccessor {

	@Accessor
	List<PostPass> getPasses();
}
