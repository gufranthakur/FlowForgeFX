module com.flowforgefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;
    requires org.fxmisc.richtext;
    


    opens com.flowforgefx to javafx.fxml;
    exports com.flowforgefx;
}