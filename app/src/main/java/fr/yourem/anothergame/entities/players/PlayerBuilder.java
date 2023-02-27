package fr.yourem.anothergame.entities.players;

import fr.yourem.anothergame.entities.Entity;

public class PlayerBuilder extends Entity.EntityBuilder {
    private int doge = 0;
    private int luck = 0;
    private int control = 10;
    private PlayerType type = PlayerType.WOODBODY;
    private int rangeAttractItem = 0;
    private double[] velocity = new double[]{0,0};


    public PlayerBuilder setDoge(int doge) {
        this.doge = doge;
        return this;
    }

    public PlayerBuilder setLuck(int luck) {
        this.luck = luck;
        return this;
    }

    public PlayerBuilder setControl(int control) {
        this.control = control;
        return this;
    }

    public PlayerBuilder setRangeAttractItem(int rangeAttractItem) {
        this.rangeAttractItem = rangeAttractItem;
        return this;
    }

    public PlayerBuilder setVelocity(double[] velocity) {
        this.velocity = velocity;
        return this;
    }

    public PlayerBuilder setType(PlayerType type) {
        this.type = type;
        return this;
    }

    @Override
    public void reformat(){
        super.reformat();
        if (doge < 0){
            doge = 10;
        }
        if (luck < 0){
            luck = 10;
        }
        if (control < 0){
            control = 10;
        }
        if (rangeAttractItem < 0){
            rangeAttractItem = 10;
        }
        if (velocity == null || velocity.length != 0){
            velocity = new double[]{0,0};
        }
    }

    public Player createPlayer() {
        reformat();
        return new Player(speed,pv, pvMax,position,size,canMoveInDirection, doge, luck, control, rangeAttractItem, velocity, type);
    }
}