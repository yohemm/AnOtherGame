package fr.yourem.anothergame.entities.players;

import fr.yourem.anothergame.entities.Entity;

import java.util.Arrays;
import java.util.Map;

public class Player extends Entity {
    static int maxStat = 100;
    protected int dodge, luck, control, rangeAttractItem;
    protected PlayerType type;
    protected double[] velocity = new double[]{0,0};
    protected int[] direction = new int[]{0,0};

    public int[] getDirection() {
        return direction;
    }

    public void setDirection(int[] direction) {
        this.direction = direction;
    }

    public Player(double speed, double pv, int pvMax, double[] position, int[] size, boolean[][] canMoveInDirection, int doge, int luck, int control, int rangeAttractItem) {
        super(speed, pv, pvMax, position, size, canMoveInDirection);
        this.dodge = doge;
        this.luck = luck;
        this.control = control;
        this.rangeAttractItem = rangeAttractItem;
    }
    public Player(EntityBuilder entityBuilder,int doge, int luck, int control, int rangeAttractItem, double[] velocity){
        super(entityBuilder);
        this.dodge = doge;
        this.luck = luck;
        this.control = control;
        this.rangeAttractItem = rangeAttractItem;
    }
    public Player(PlayerBuilder playerBuilder){
        this(playerBuilder.createPlayer());
    }
    public Player(Player player){
        this(player.speed, player.pv, player.pvMax, player.position, player.size, player.canMoveInDirection, player.dodge, player.luck, player.control, player.rangeAttractItem, player.velocity, player.type);
    }

    public Player(double speed, double pv, int pvMax, double[] position, int[] size, boolean[][] canMoveInDirection,int doge, int luck, int control, int rangeAttractItem, double[] velocity, PlayerType type) {
        super(speed, pv, pvMax, position, size, canMoveInDirection);
        this.type = type;
        this.dodge = doge;
        this.luck = luck;
        this.control = control;
        this.rangeAttractItem = rangeAttractItem;
        this.velocity = velocity;
    }

    public void setEffect(Map<String,Integer> effects){
        for(Map.Entry<String,Integer> entry: effects.entrySet()){
            switch (entry.getKey()){
                case "dodge":
                    dodge+= entry.getValue();
                    break;
                case "control":
                    control+= entry.getValue();
                    break;
                case "range_attract_item":
                    rangeAttractItem+= entry.getValue();
                    break;
                case "speed":
                    speed+= entry.getValue();
                    break;
                case "life":
                    pvMax+= entry.getValue();
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    public void move() {
        velocity[0] += direction[0]*((double)control/(double)maxStat)*speed;
        velocity[1] += direction[1]*((double)control/(double)maxStat)*speed;
        if (velocity[0]>0){
            if (velocity[0]>speed){
                velocity[0]=speed;
            }

            if (direction[0]==0){
                velocity[0] -= (double)control/(double)maxStat*speed;
                if (direction[0]<0)
                    velocity[1]=0;
            }
            if (!canMoveInDirection[0][1]){
                velocity[0]=0;
            }
        } else if(velocity[0]<0){
            if (velocity[0] < -speed){
                velocity[0]=-speed;
            }
            if (direction[0]==0){
                velocity[0] += (double)control/(double)maxStat*speed;
                if (velocity[0]>0)
                    velocity[0] = 0;
            }
            if (!canMoveInDirection[0][0]){
                velocity[0]=0;
            }
        }
        if (velocity[1]>0){
            if (velocity[1]>speed){
                velocity[1]=speed;
            }
            if (direction[1]==0){
                velocity[1] -= (double)control/(double)maxStat*speed;
                if (velocity[1]<0)
                    velocity[1] = 0;
            }
            if (!canMoveInDirection[1][1]){
                velocity[1]=0;
            }

        } else if(velocity[1]<0){
            if (velocity[1]< -speed){
                velocity[1]=-speed;
            }
            if (direction[1]==0){
                velocity[1] += (double)control/(double)maxStat*speed;
                if (velocity[1]>0)
                    velocity[1] = 0;
            }
            if (!canMoveInDirection[1][0]){
                velocity[1]=0;
            }
        }
        position[0]+=velocity[0];
        position[1]+=velocity[1];
    }

    @Override
    public String toString() {
        return "Player{" +
                "dodge=" + dodge +
                ", luck=" + luck +
                ", control=" + control +
                ", rangeAttractItem=" + rangeAttractItem +
                ", velocity=" + Arrays.toString(velocity) +
                ", direction=" + Arrays.toString(direction) +
                ", speed=" + speed +
                ", pv=" + pv +
                ", pvMax=" + pvMax +
                ", position=" + Arrays.toString(position) +
                ", size=" + Arrays.toString(size) +
                '}';
    }

    public void setVelocity(double[] velocity) {
        this.velocity = velocity;
    }

    public PlayerType getType() {
        return type;
    }
}
