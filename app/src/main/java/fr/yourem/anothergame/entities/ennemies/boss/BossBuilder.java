package fr.yourem.anothergame.entities.ennemies.boss;

import fr.yourem.anothergame.entities.ennemies.EnnemyBuilder;

public class BossBuilder extends EnnemyBuilder{
    private String name;
    private String dialogue;
    private int dropPlayerUnlock;
    private BossType type;

    public BossBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public BossBuilder setDialogue(String dialogue) {
        this.dialogue = dialogue;
        return this;
    }

    public BossBuilder setDropPlayerUnlock(int dropPlayerUnlock) {
        this.dropPlayerUnlock = dropPlayerUnlock;
        return this;
    }

    @Override
    public void reformat(){
        super.reformat();
        if (name==null || name.isEmpty()){
            name = "Unamed";
        }
        if (dropPlayerUnlock <0){
            dropPlayerUnlock = 100;
        }
    }

    public Boss createBoss() {
        reformat();
        return new Boss(speed, pv, pvMax, position, size, canMoveInDirection, canAttack, damage, attackSpeed, name, dialogue, dropPlayerUnlock, type);
    }
}