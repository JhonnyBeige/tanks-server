package gtanks.battles.timer;

import gtanks.StringUtils;
import gtanks.battles.BattlefieldPlayerController;
import gtanks.battles.managers.SpawnManager;
import gtanks.battles.tanks.math.Vector3;
import gtanks.commands.Type;
import gtanks.json.JsonUtils;

/**
 * @deprecated
 */
@Deprecated
public class TankRespawnTimer extends Thread {
    private static final long TIME_TO_PREPARE_SPAWN = 3000L;
    private static final long TIME_TO_SPAWN = 5000L;
    private BattlefieldPlayerController player;
    private Vector3 preparedPos;
    private boolean onlySpawn = false;
    private boolean interputed = false;

    public TankRespawnTimer(BattlefieldPlayerController player, boolean onlySpawn, Vector3 preparedPos) {
        this.player = player;
        this.onlySpawn = onlySpawn;
        this.preparedPos = preparedPos;
        this.start();
    }

    @Override
    public void run() {
        try {
            if (!this.onlySpawn) {
                sleep(3000L);
                this.prepareSpawn();
            } else {
                this.player.send(Type.BATTLE, "prepare_to_spawn", StringUtils.concatStrings(this.player.tank.id, ";", String.valueOf(this.preparedPos.x), "@", String.valueOf(this.preparedPos.y), "@", String.valueOf(this.preparedPos.z), "@", String.valueOf(this.preparedPos.rot)));
            }

            sleep(5000L);
            this.spawn();
        } catch (InterruptedException var2) {
            var2.printStackTrace();
        }

    }

    public void interput() {
        this.interputed = true;
    }

    public void on() {
        this.interputed = false;
    }

    private void prepareSpawn() {
        if (!this.interputed) {
            if (this.player != null) {
                if (this.player.battle != null) {
                    Vector3 pos = SpawnManager.getSpawnState(this.player.battle.battleInfo.map, this.player.playerTeamType);
                    this.preparedPos = pos;
                    this.player.tank.position = pos;
                    this.player.send(Type.BATTLE, "prepare_to_spawn", StringUtils.concatStrings(this.player.tank.id, ";", String.valueOf(pos.x), "@", String.valueOf(pos.y), "@", String.valueOf(pos.z), "@", String.valueOf(pos.rot)));
                }
            }
        }
    }

    private void spawn() {
        if (this.player != null && this.player.tank != null && !this.interputed) {
            this.player.battle.tanksKillModel.changeHealth(this.player.tank, 10000);
            this.player.battle.sendToAllPlayers(Type.BATTLE, "spawn", JsonUtils.parseSpawnCommand(this.player, this.preparedPos));
            this.player.tank.state = "newcome";
        }

    }
}
