package gtanks.battles.tanks.weapons.isida;

import gtanks.battles.tanks.weapons.WeaponType;
import gtanks.battles.tanks.weapons.WeaponEntity;
import gtanks.battles.tanks.weapons.ShotData;

public class IsidaEntity implements WeaponEntity {
    public int capacity;
    public int chargeRate;
    public int dischargeRate;
    public int tickPeriod;
    public float lockAngle;
    public float lockAngleCos;
    public float maxAngle;
    public float maxAngleCos;
    public float maxRadius;
    public float damage_min;
    public float damage_max;
    private ShotData shotData;

    public IsidaEntity(int capacity, int chargeRate, int dischargeRate, int tickPeriod, float lockAngle, float lockAngleCos, float maxAngle, float maxAngleCos, float maxRadius, ShotData shotData, float damage_min, float damage_max) {
        this.capacity = capacity;
        this.chargeRate = chargeRate;
        this.dischargeRate = dischargeRate;
        this.tickPeriod = tickPeriod;
        this.lockAngle = lockAngle;
        this.lockAngleCos = lockAngleCos;
        this.maxAngle = maxAngle;
        this.maxAngleCos = maxAngleCos;
        this.maxRadius = maxRadius;
        this.shotData = shotData;
        this.damage_min = damage_min;
        this.damage_max = damage_max;
    }

    @Override
    public ShotData getShotData() {
        return this.shotData;
    }

    @Override
    public WeaponType getType() {
        return WeaponType.ISIDA;
    }
}
