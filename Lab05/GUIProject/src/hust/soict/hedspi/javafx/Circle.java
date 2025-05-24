package hust.soict.hedspi.javafx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * Represents a circle shape for the drawing application.
 * Extends JavaFX Ellipse class to create a circular shape.
 */
public class Circle extends Ellipse {

    /**
     * Creates a new Circle at the specified coordinates with the given radius and color.
     *
     * @param x The x-coordinate of the center of the circle
     * @param y The y-coordinate of the center of the circle
     * @param radius The radius of the circle
     * @param color The color of the circle
     */
    public Circle(double x, double y, double radius, Color color) {
        // Set center coordinates
        setCenterX(x);
        setCenterY(y);

        // Make it a circle by setting equal radiuses
        setRadiusX(radius);
        setRadiusY(radius);

        // Set the fill color
        setFill(color);

        // Add a slight border for better visibility
        setStroke(Color.BLACK);
        setStrokeWidth(0.5);
    }
}