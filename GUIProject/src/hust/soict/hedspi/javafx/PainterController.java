package hust.soict.hedspi.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class PainterController {

    @FXML
    private Pane drawingAreaPane;

    @FXML
    private RadioButton radioPen;

    @FXML
    private RadioButton radioEraser;

    // Biến để lưu trữ màu hiện tại
    private Color currentColor = Color.BLACK; // Mặc định là màu đen (bút vẽ)

    // Thuộc tính mặc định của Circle
    private final double CIRCLE_RADIUS = 4.0;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        // Xóa tất cả các hình tròn từ khu vực vẽ
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        draw(event);
    }

    @FXML
    void drawingAreaMousePressed(MouseEvent event) {
        draw(event);
    }

    private void draw(MouseEvent event) {
        // Lấy tọa độ nơi chuột được kéo hoặc nhấn
        double x = event.getX();
        double y = event.getY();

        // Tạo một hình tròn mới tại tọa độ đó với màu thích hợp
        Circle newCircle = new Circle(x, y, CIRCLE_RADIUS, currentColor);

        // Thêm hình tròn vào khu vực vẽ
        drawingAreaPane.getChildren().add(newCircle);
    }

    @FXML
    void handleRadioButtonAction(ActionEvent event) {
        if (radioPen != null && radioPen.isSelected()) {
            currentColor = Color.BLACK;
        } else if (radioEraser != null && radioEraser.isSelected()) {
            currentColor = Color.WHITE;
        }
    }
}