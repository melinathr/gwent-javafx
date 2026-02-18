module project.is.killing.me {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.json;
    exports view;
    opens view to javafx.fxml;
}