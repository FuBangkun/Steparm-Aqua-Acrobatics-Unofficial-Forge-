package sheg1_steparm.aquaacrobaticsunofficial.core.mixin.client;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import sheg1_steparm.aquaacrobaticsunofficial.entity.player.IPlayerResizeable;
import sheg1_steparm.aquaacrobaticsunofficial.util.math.MathHelperNew;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin {
    @Shadow
    @Final
    public Minecraft mc;

    @Unique
    private float aquaAcrobatics$eyeHeight;
    @Unique
    private float aquaAcrobatics$previousEyeHeight;
    @Unique
    private float aquaAcrobatics$entityEyeHeight;
    @Unique
    private float aquaAcrobatics$partialTicks;

    @Inject(method = "orientCamera", at = @At("HEAD"))
    private void orientCamera(float partialTicks, CallbackInfo callbackInfo) {
        this.aquaAcrobatics$partialTicks = partialTicks;
    }

    @ModifyVariable(method = "orientCamera", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/Entity;prevPosX:D", ordinal = 0, opcode = Opcodes.GETFIELD), ordinal = 1)
    public float getEyeHeight(float eyeHeight) {
        Entity entity = this.mc.getRenderViewEntity();

        if (!(entity instanceof IPlayerResizeable)) {
            return eyeHeight;
        }

        this.aquaAcrobatics$entityEyeHeight = ((IPlayerResizeable) entity).aquaAcrobatics$getPlayerEyeHeight();
        return MathHelperNew.lerp(this.aquaAcrobatics$partialTicks, this.aquaAcrobatics$previousEyeHeight, this.aquaAcrobatics$eyeHeight);
    }

    @Inject(method = "updateRenderer", at = @At("TAIL"))
    public void updateRenderer(CallbackInfo callbackInfo) {
        this.aquaAcrobatics$interpolateHeight();
    }

    @Unique
    private void aquaAcrobatics$interpolateHeight() {
        this.aquaAcrobatics$previousEyeHeight = this.aquaAcrobatics$eyeHeight;
        this.aquaAcrobatics$eyeHeight += (this.aquaAcrobatics$entityEyeHeight - this.aquaAcrobatics$eyeHeight) * 0.5F;
    }

    @Redirect(
            method = "renderWorldPass",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/Entity;isInsideOfMaterial(Lnet/minecraft/block/material/Material;)Z",
                    ordinal = 0
            ),
            require = 0,
            expect = 0
    )
    private boolean ignoreWater(Entity entity, Material material) {
        if (material == Material.WATER) {
            return false;
        }
        return entity.isInsideOfMaterial(material);
    }
}