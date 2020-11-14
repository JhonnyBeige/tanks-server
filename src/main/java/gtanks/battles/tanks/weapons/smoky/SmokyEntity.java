package gtanks.battles.tanks.weapons.smoky;

import gtanks.battles.tanks.weapons.WeaponType;
import gtanks.battles.tanks.weapons.WeaponEntity;
import gtanks.battles.tanks.weapons.ShotData;

public class SmokyEntity implements WeaponEntity {
    public final WeaponType type;
    public float damage_min;
    public float damage_max;
    private ShotData shotData;

    public SmokyEntity(ShotData shotData, float damage_min, float damage_max) {
        this.type = WeaponType.SMOKY;
        this.damage_min = damage_min;
        this.damage_max = damage_max;
        this.shotData = shotData;
    }

    @Override
    public ShotData getShotData() {
        return this.shotData;
    }

    @Override
    public WeaponType getType() {
        return this.type;
    }
}
