/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author jitor
 */
public class ConstructionObject {
    private double posX, posY;
    private String objectType;
    private double rotation, flip, height, width;
//    private lista de quantity

    public ConstructionObject(double posX, double posY, String objectType, double rotation, double flip, double height, double width) {
        this.posX = posX;
        this.posY = posY;
        this.objectType = objectType;
        this.rotation = rotation;
        this.flip = flip;
        this.height = height;
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getFlip() {
        return flip;
    }

    public void setFlip(double flip) {
        this.flip = flip;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    @Override
    public String toString() {
        return   posX + "|" + posY + "|" + objectType + "|" + rotation + "|" + flip + "|" + height + "|" + width;
    }

}
