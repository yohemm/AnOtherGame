package fr.yourem.anothergame.floors.rooms;

public enum RoomType {
    SPAWN,CHEST,NEXT_FLOOR,ENNEMIES,BOSS;
    public String getLettre(){
        switch (this){
            case SPAWN : return "S";
            case CHEST : return "C";
            case NEXT_FLOOR : return "N";
            case ENNEMIES: return "E";
            case BOSS: return "B";
            default:return "D";
        }
    }
}
