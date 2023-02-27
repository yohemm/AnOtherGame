package fr.yourem.anothergame;

import fr.yourem.anothergame.entities.Entity;
import fr.yourem.anothergame.entities.ennemies.Ennemy;
import fr.yourem.anothergame.entities.ennemies.EnnemyBuilder;
import fr.yourem.anothergame.entities.ennemies.boss.BossBuilder;
import fr.yourem.anothergame.entities.players.PlayerBuilder;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) {
        EnnemyBuilder entityBuilder = (EnnemyBuilder) new EnnemyBuilder().setAttackSpeed(72).setPvMax(700);
        Entity ennemy = (Entity) entityBuilder.createEnnemy();

        BossBuilder bossBuilder = (BossBuilder) new BossBuilder().setAttackSpeed(72).setPvMax(700);
        Entity boss = (Entity) bossBuilder.createBoss();
        PlayerBuilder playerBuilder = (PlayerBuilder) new PlayerBuilder().setDoge(72).setPvMax(700);
        Entity player = (Entity) playerBuilder.createPlayer();
        System.out.println(ennemy);
        System.out.println(boss);
        System.out.println(player);
/*
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Yourem\\Desktop\\git_remote_workspace.png");
        Image image = new Image(fileInputStream);
        ImageView imageView = new ImageView(image);
        imageView.setX(100);
        imageView.setY(400);
        root.getChildren().add(imageView);*/
    }
}
