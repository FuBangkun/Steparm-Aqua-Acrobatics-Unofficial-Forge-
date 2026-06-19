package sheg1_steparm.aquaacrobaticsunofficial.util;

import net.minecraft.util.MovementInput;

public class MovementInputStorage extends MovementInput {
    public int sprintToggleTimer;
    public boolean isFlying;
    public boolean isSprinting;
    public boolean isStartingToFly;

    public void copyFrom(MovementInput movement) {
        this.moveStrafe = movement.moveStrafe;
        this.moveForward = movement.moveForward;
        this.forwardKeyDown = movement.forwardKeyDown;
        this.backKeyDown = movement.backKeyDown;
        this.leftKeyDown = movement.leftKeyDown;
        this.rightKeyDown = movement.rightKeyDown;
        this.jump = movement.jump;
        this.sneak = movement.sneak;
    }
}