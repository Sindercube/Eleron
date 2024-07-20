package com.sindercube.aileron.mixin;

import com.sindercube.aileron.handlers.CampfireHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CampfireBlock.class)
public class CampfireBlockMixin {

	@Inject(method = "onEntityCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"), cancellable = true)
	private void protectWithSmokeStack(BlockState s, World w, BlockPos p, Entity entity, CallbackInfo ci) {
		CampfireHandler.protectWithSmokeStack(entity, ci);
	}

	@Inject(method = "onEntityCollision", at = @At("HEAD"))
	private void handleSmokestackCharging(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
		CampfireHandler.handleSmokestackCharging(world, entity, ci);
	}


//	TODO reimplement this sometime
//	/**
//	 * @author Sindercube
//	 * @reason Stacking particle distance
//	 */
//	@Overwrite
//	public static void spawnSmokeParticle(World world, BlockPos pos, boolean isSignal, boolean lotsOfSmoke) {
//		CampfireHandler.spawnSmokeParticle(world, pos, isSignal, lotsOfSmoke);
//	}

}
