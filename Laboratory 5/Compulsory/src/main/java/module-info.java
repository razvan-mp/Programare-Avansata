module compulsory.compulsory {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens compulsory.compulsory to javafx.fxml;
    exports compulsory.compulsory;
}