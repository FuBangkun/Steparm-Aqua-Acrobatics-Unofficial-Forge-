package sheg1_steparm.aquaacrobaticsunofficial.entity.player;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import sheg1_steparm.aquaacrobaticsunofficial.entity.EntitySize;
import sheg1_steparm.aquaacrobaticsunofficial.entity.Pose;

public interface IPlayerResizeable {
    boolean aquaAcrobatics$canSwim();

    void aquaAcrobatics$updateSwimming();

    boolean aquaAcrobatics$getEyesInWaterPlayer();

    float aquaAcrobatics$getWaterVision();

    float aquaAcrobatics$getWidth();

    float aquaAcrobatics$getHeight();

    EntitySize aquaAcrobatics$getSize(Pose poseIn);

    void aquaAcrobatics$recalculateSize();

    boolean aquaAcrobatics$isResizingAllowed();

    boolean aquaAcrobatics$isActuallySneaking();

    float aquaAcrobatics$getStandingEyeHeight(Pose poseIn, EntitySize sizeIn);

    void aquaAcrobatics$setPose(Pose poseIn);

    Pose aquaAcrobatics$getPose();

    boolean aquaAcrobatics$isPoseClear(Pose poseIn);

    boolean aquaAcrobatics$getShouldBeDead();

    boolean aquaAcrobatics$isSwimming();

    boolean aquaAcrobatics$isActuallySwimming();

    @SideOnly(Side.CLIENT)
    boolean aquaAcrobatics$isVisuallySwimming();

    void aquaAcrobatics$setSwimming(boolean flag);

    float aquaAcrobatics$getSwimAnimation(float partialTicks);

    boolean aquaAcrobatics$canForceCrawling();

    boolean aquaAcrobatics$isForcingCrawling();

    void aquaAcrobatics$setForcingCrawling(boolean flag);

    float aquaAcrobatics$getPlayerEyeHeight();
}