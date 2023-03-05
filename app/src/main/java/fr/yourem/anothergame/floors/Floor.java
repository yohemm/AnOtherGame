package fr.yourem.anothergame.floors;

import fr.yourem.anothergame.entities.ennemies.Ennemy;
import fr.yourem.anothergame.floors.rooms.Room;
import fr.yourem.anothergame.floors.rooms.RoomBuilder;
import fr.yourem.anothergame.floors.rooms.RoomType;
import fr.yourem.anothergame.floors.themes.Theme;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Floor {

    static private double removeRoom = 0.3;
    static private int minMapSize = 10;
    static private int maxMapSize = 50;
    static private double minChestFreq = 0.1;
    static private double maxChestFreq = 0.4;
    static private double bestDistanceStair = 0.8;
    static private double bestDistanceBoss = 0.9;
    static private double bestDistanceChest = 0.48;
    private int difficulty, deep, timeToSurvive;
    private long seed;

    private Theme theme;

    private Room[][] map;
    public Floor(int deep, Theme theme,int gameDifficulty, int timeToSurvive) {
        Random random = new Random();
        seed = random.nextLong();
        this.difficulty = (int) (gameDifficulty * deep * theme.getNaturalDiffculty())%50;
        int size = minMapSize + (maxMapSize-minMapSize)*(difficulty/150);
        this.deep = deep;
        this.timeToSurvive = timeToSurvive;
        this.theme = theme;
        this.map = new Room[size][size];
        initialiseRooms(size);
    }
    public void initialiseRooms(int size){
        Random r = new Random(seed);
        double freqChest = freqChest();
        RoomBuilder[][] initMap = new RoomBuilder[size][size];
        map = new Room[size][size];

        initMap[size/2][size/2] = new RoomBuilder(seed).setType(RoomType.SPAWN);
        for (int j = 0; j < size;j++){
            for (int i = 0; i < size;i++){
                if (j == size/2 && i == size/2) break;
                int distanceToSpawn = (Math.abs(i-(size)/2)+Math.abs(j-(size)/2))/2;
                double proportionaldistance = distanceToSpawn/(double)(size/2);
                double nearestChest = Math.abs(proportionaldistance-bestDistanceChest);

                /*suppreession pseudo aléatoir de la room*/
                if (r.nextInt((int)((1.1-proportionaldistance)*100))< 100*removeRoom) {
                    initMap[j][i] = null;
                }else{
                    initMap[j][i] = new RoomBuilder();
                    /*definie le type basic des room*/
                    if (r.nextInt((int)(1000*freqChest)) > nearestChest*1000){
                        initMap[j][i].setType(RoomType.CHEST);
                    }else{
                        initMap[j][i].setType(RoomType.ENNEMIES);
                    }
                }
            }
        }

        /*filtre des room connecter*/
        printMap(initMap);
        System.out.println("--------------");
        int[][] generatable = roomCanBeGenerate(initMap);

        for (int j = 0; j < size;j++){
            for (int i = 0; i < size;i++) {
                boolean find = false;
                for (int[] coor : generatable)
                    if (j == coor[0] && i == coor[1]) {
                        find = true;
                        break;
                    }
                if (!find) initMap[j][i] = null;/*
                else if (i > size*0.5+r.nextDouble(0.5)*size){
                    initMap[j][i].setType(RoomType.NEXT_FLOOR)
                }*/
                if (initMap[j][i] != null) {
                    this.map[j][i] = initMap[j][i].createRoom(this);
                }
            }
        }
        printMap();
    }
    public Room getSpawn(){
        if (map[map.length/2][map[0].length/2] != null && map[map.length/2][map[0].length/2].getType() == RoomType.SPAWN){
            return map[map.length/2][map[0].length/2];
        }else{
            for (Room[] rList : map)
                for (Room r: rList)
                    if (r != null && r.getType() == RoomType.SPAWN)
                        return r;
        }
        return null;
    }
    public Room[] getDoors(Room currentRoom){
        Room[] doors = new Room[4];
        int[] posRoom = findRoomPos(currentRoom);
        if (posRoom[0]-1 >=0)
            doors[0] = map[posRoom[0]-1][posRoom[1]] != null ?map[posRoom[0]-1][posRoom[1]]:null;
        if (posRoom[0]+1 < map.length)
            doors[1] = map[posRoom[0]+1][posRoom[1]] != null ?map[posRoom[0]+1][posRoom[1]]:null;
        if (posRoom[1]-1 >=0)
            doors[2] = map[posRoom[0]][posRoom[1]-1] != null ?map[posRoom[0]][posRoom[1]-1]:null;
        if (posRoom[1]+1 < map[0].length)
            doors[3] = map[posRoom[0]][posRoom[1]+1] != null ?map[posRoom[0]][posRoom[1]+1]:null;
        return doors;
    }
    public int[] findRoomPos(Room room){
        for (int j = 0; j < map.length;j++)
            for (int i = 0; i < map[0].length;i++)
                if (map[j][i] == room)
                    return new int[] {j,i};
        return null;
    }
    public void printMap(RoomBuilder[][] map){
        String line = "";
        for (int j = 0; j < map.length;j++){
            line = "";
            for (int i = 0; i < map.length;i++) {
                line += map[j][i]==null?" ":map[j][i].getType().getLettre();
            }
            System.out.println(line);
        }
    }
    public void printMap(){
        String line = "";
        for (int j = 0; j < map.length;j++){
            line = "";
            for (int i = 0; i < map.length;i++) {
                line += map[j][i]==null?" ":map[j][i].getType().getLettre();
            }
            System.out.println(line);
        }
    }
    static private boolean listCoorRoomContain(List<int[]> listCoorRoom, int i , int j){
        for (int a = 0; a < listCoorRoom.size(); a++){
            if (listCoorRoom.get(a)[0] == i && listCoorRoom.get(a)[1] == j){
                return true;
            }
        }
        return false;
    }
    static private int[] verifyRoom(RoomBuilder[][]map, List<int[]> listCoorRoom, int i, int j){
        if ( i < map.length && i>0  && j< map[i].length && j>0 &&( map[i][j] != null && !listCoorRoomContain(listCoorRoom, i,j))){
            listCoorRoom.add(new int[]{i, j});
            return new int[] {i,j};
        }
        return null;
    }
    static void roomCanBeGenerate(RoomBuilder[][]map, List<int[]> listCoorRoom, List<int[]> oldAdd) {
        for (int[] add : oldAdd) {
            int[] coor;
            List<int[]> newAdd = new ArrayList<>();
            int i = add[0];
            int j = add[1];
            int i2;
            int j2;
            i2 = i;
            j2 = j+1;
            coor = verifyRoom(map,listCoorRoom,i2,j2);
            if (coor != null) newAdd.add(coor);
            j2 = j-1;
            coor = verifyRoom(map,listCoorRoom,i2,j2);
            if (coor != null) newAdd.add(coor);
            i2 = i+1;
            j2 = j;
            coor = verifyRoom(map,listCoorRoom,i2,j2);
            if (coor != null) newAdd.add(coor);
            i2 = i-1;
            coor = verifyRoom(map,listCoorRoom,i2,j2);
            if (coor != null) newAdd.add(coor);
            if(newAdd.size()>0)
                roomCanBeGenerate(map, listCoorRoom, newAdd);
        }
    }
    static int[][] roomCanBeGenerate(RoomBuilder[][] map){
        List<int[]> listCoorRoom = new ArrayList<>();
        int i = map.length/2;
        int j = map[i].length/2;
        if (map[i][j] != null && map[i][j].getType() == RoomType.SPAWN ) {
            listCoorRoom.add(new int[]{i, j});
            roomCanBeGenerate(map, listCoorRoom, List.copyOf(listCoorRoom));
        }
        return  listCoorRoom.toArray(new int[0][0]);
    }
    private double freqChest(){
        double deepDifficulty = deep<20? deep/20:1;
        return maxChestFreq - (maxChestFreq - minChestFreq)*deepDifficulty;
    }

    public Floor nextFloor(int gameDifficulty){
        return new Floor(deep+1, theme, difficulty, timeToSurvive);
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getDeep() {
        return deep;
    }

    public long getSeed() {
        return seed;
    }

    public Theme getTheme() {
        return theme;
    }
}
