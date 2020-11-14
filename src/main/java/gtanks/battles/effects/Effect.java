package gtanks.battles.effects;

import gtanks.battles.BattlefieldPlayerController;
import gtanks.battles.tanks.math.Vector3;

public interface Effect {
    String DEACTIVATE_EFFECT_COMMAND = "disnable_effect";

    void activate(BattlefieldPlayerController var1, boolean var2, Vector3 var3);

    void deactivate();

    EffectType getEffectType();

    int getID();

    int getDurationTime();
}
