/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author fabia
 */
public class DraggableImage {
    private Node shape;
    private double x, y;
    private Canvas cnvWorkSpace;

    public DraggableImage(Node shape, double x, double y, Canvas cnvWorkSpace) {
        this.shape = shape;
        this.x = x;
        this.y = y;
        this.cnvWorkSpace = cnvWorkSpace;
        this.shape.setLayoutX(x);
        this.shape.setLayoutY(y);
    }

    public Node getShape() {
        return shape;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        if (x >= 0 && x + shape.getBoundsInLocal().getWidth() <= cnvWorkSpace.getWidth()) {
            this.x = x;
            shape.setLayoutX(x);
        }
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        if (y >= 0 && y + shape.getBoundsInLocal().getHeight() <= cnvWorkSpace.getHeight()) {
            this.y = y;
            shape.setLayoutY(y);
        }
    }

    public boolean contains(double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= (x + shape.getBoundsInLocal().getWidth()) &&
                mouseY >= y && mouseY <= (y + shape.getBoundsInLocal().getHeight());
    }
}

