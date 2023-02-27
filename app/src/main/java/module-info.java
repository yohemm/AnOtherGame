module fr.yourem.anothergame {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens fr.yourem.anothergame to javafx.fxml;
    exports fr.yourem.anothergame;
}