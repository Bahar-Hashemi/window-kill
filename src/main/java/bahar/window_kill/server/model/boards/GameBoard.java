package bahar.window_kill.server.model.boards;

import bahar.window_kill.client.model.entities.Entity;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Scanner;

public class GameBoard {
    protected boolean hovering;
    protected final ArrayList<Entity> children;
    protected double x, y, width, height;
    public GameBoard(boolean hovering) {
        this.hovering = hovering;
        children = new ArrayList<>();
    }
    public double getLayoutX() {
        return x;
    }
    public double getLayoutY() {
        return y;
    }
    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
    public void setLayoutX(double x) {
        this.x = x;
    }
    public void setLayoutY(double y) {
        this.y = y;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }
    public void setDimensions(double x, double y, double width, double height) {
        IndependentMoveX(x); IndependentMoveY(y);
        this.width = width;
        this.height = height;
    }
    public void setIndependentDimensions(double x, double y, double width, double height) {
        IndependentMoveX(x); IndependentMoveY(y);
        setSize(width, height);
    }
    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }
    public void IndependentMoveX(double x) {
        double deltaX = this.x - x;
        for (Entity entity: getChildren())
            entity.setLayoutX(entity.getLayoutX() + deltaX);
        this.x = x;
    }
    public void IndependentMoveY(double y) {
        double deltaY = this.y - y;
        for (Entity entity: getChildren())
            entity.setLayoutY(entity.getLayoutY() + deltaY);
        this.y = y;
    }
    public void add(Entity entity) {

    }
    public boolean getHovering() {
        return hovering;
    }
    public ArrayList<Entity> getChildren() {
        return children;
    }
    public void readFromString(Scanner sc) {

    }
    public void writeToString(StringBuilder sb) {
        sb.append(this.hovering).append("\n");
        sb.append(this.x).append(" ").append(this.y).append(" ").append(this.width).append(" ").append(this.height).append("\n");
    }
}
