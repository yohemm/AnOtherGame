package fr.yourem.anothergame.entities.ennemies;

import fr.yourem.anothergame.entities.Entity;

public class EnnemyBuilder extends Entity.EntityBuilder {
    protected boolean canAttack = true;
    protected int damage = 5;

    protected EnnemyType type;
    protected double attackSpeed = 1;

    public EnnemyBuilder setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
        return this;
    }

    public EnnemyBuilder setDamage(int damage) {
        this.damage = damage;
        return this;
    }

    public EnnemyBuilder setType(EnnemyType type) {
        this.type = type;
        return this;
    }

    public EnnemyBuilder setAttackSpeed(double attackSpeed) {
        this.attackSpeed = attackSpeed;
        return this;
    }
    @Override
    public void reformat(){
        super.reformat();
        if (damage <0)
            damage = 5;
        if (attackSpeed <0)
            attackSpeed = 1;
    }

    public Ennemy createEnnemy() {
        reformat();
        return new Ennemy(speed, pv, pvMax, position, size, canMoveInDirection, canAttack, damage, attackSpeed, type);
    }
}