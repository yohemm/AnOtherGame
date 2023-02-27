package fr.yourem.anothergame.entities.ennemies.boss;

import fr.yourem.anothergame.entities.Entity;
import fr.yourem.anothergame.entities.ennemies.Ennemy;
import fr.yourem.anothergame.entities.ennemies.EnnemyBuilder;
import fr.yourem.anothergame.entities.ennemies.EnnemyType;
import javafx.scene.image.Image;

import java.util.Arrays;

public class Boss extends Ennemy {
    protected String name;
    protected String dialogue;
    protected int dropPlayerUnlock;
    protected BossType type;

    public Boss(double speed, double pv, int pvMax, double[] position, int[] size, boolean[][] canMoveInDirection, boolean canAttack, int damage, double attackSpeed, String name, String dialogue, int dropPlayerUnlock, BossType type) {
        super(speed, pv, pvMax, position, size, canMoveInDirection, canAttack, damage, attackSpeed, EnnemyType.BOSS);
        this.name = name;
        this.dialogue = dialogue;
        this.dropPlayerUnlock = dropPlayerUnlock;
        this.type = type;
    }

    public Boss(EnnemyBuilder ennemyBuilder, String name, String dialogue, int dropPlayerUnlock, BossType type) {
        super(ennemyBuilder);
        this.name = name;
        this.dialogue = dialogue;
        this.dropPlayerUnlock = dropPlayerUnlock;
        this.type = type;

    }

    @Override
    public String toString() {
        return "Boss{" +
                "name='" + name + '\'' +
                ", dialogue='" + dialogue + '\'' +
                ", dropPlayerUnlock=" + dropPlayerUnlock +
                ", speed=" + speed +
                ", pv=" + pv +
                ", pvMax=" + pvMax +
                ", position=" + Arrays.toString(position) +
                ", size=" + Arrays.toString(size) +
                '}';
    }

    public void attackSpecial(){

    };
}
