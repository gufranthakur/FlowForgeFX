module com.flowforgefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;
    requires org.fxmisc.richtext;
    requires javafx.graphics;
    requires com.flowforgefx;


    opens com.flowforgefx to javafx.fxml;
    exports com.flowforgefx;
}