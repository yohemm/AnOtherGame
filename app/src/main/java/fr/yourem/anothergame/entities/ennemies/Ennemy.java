package fr.yourem.anothergame.entities.ennemies;

import fr.yourem.anothergame.entities.Entity;
import fr.yourem.anothergame.entities.players.Player;
import fr.yourem.anothergame.floors.Floor;
import fr.yourem.anothergame.floors.rooms.Room;
import javafx.scene.image.Image;

import java.util.Arrays;

public class Ennemy extends Entity {
    private boolean canAttack;
    private int damage;
    private double attackSpeed;

    private EnnemyType type;

    public Ennemy(double speed, double pv, int pvMax, double[] position, int[] size, boolean[][] canMoveInDirection, boolean canAttack, int damage, double attackSpeed, EnnemyType type) {
        super(speed, pv, pvMax, position, size, canMoveInDirection);
        this.canAttack = canAttack;
        this.damage = damage;
        this.attackSpeed= attackSpeed;
        this.type = type;
    }
    public Ennemy(EntityBuilder entityBuilder, boolean canAttack, int damage, double attackSpeed, EnnemyType type){
        super(entityBuilder);
        this.canAttack = canAttack;
        this.damage = damage;
        this.attackSpeed= attackSpeed;
        this.type = type;
    }
    public Ennemy(EnnemyBuilder ennemyBuilder){
        this(ennemyBuilder.createEnnemy());
    }
    public Ennemy(Ennemy ennemy){
        this(ennemy.speed, ennemy.pv, ennemy.pvMax, ennemy.position, ennemy.size, ennemy.canMoveInDirection, ennemy.canAttack, ennemy.damage, ennemy.attackSpeed, ennemy.type);
    }

    public void update(Player player){
        return;
    }
    public boolean attack(){
        return false;
    }

    @Override
    public String toString() {
        return "Ennemy{" +
                "damage=" + damage +
                ", attackSpeed=" + attackSpeed +
                ", speed=" + speed +
                ", pv=" + pv +
                ", pvMax=" + pvMax +
                ", position=" + Arrays.toString(position) +
                ", size=" + Arrays.toString(size) +
                '}';
    }

    public void move(Player player, Floor floor, Room room) {
        EnnemyType.move(this, player, floor, room);
    }

    public EnnemyType getType() {
        return type;
    }
}
