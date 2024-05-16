package bahar.window_kill.model.entities;

import bahar.window_kill.control.Constants;
import bahar.window_kill.model.MainBoard;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.effect.Light;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Epsilon extends Entity {
    double radius = 10;
    public Epsilon() {
        view = makeView();
        deltaX = (Constants.RESPOND_DURATION) / 5;
        deltaY = (Constants.RESPOND_DURATION) / 5;
        HP = 100;
    }
    public Epsilon(double x, double y) {
        this();
        setLayoutLocation(x, y);
    }
    @Override
    protected Node makeView() {
        Circle circle = new Circle();
        circle.setFill(Constants.BACKGROUND_COLOR);
        circle.setStroke(Color.WHITE); // Set stroke color
        circle.setRadius(radius);
        circle.setStrokeWidth(3);
        return circle;
    }
    public void setColor(Color color) {
        ((Circle) view).setStroke(color);
    }
    public double inBoardArea(MainBoard mainBoard) {
        Rectangle rectangle = new Rectangle(0, 0, mainBoard.getWidth(), mainBoard.getHeight());
        Shape intersection = Shape.intersect((Shape) this.getView(), (Shape) rectangle);
        double area = intersection.getBoundsInLocal().getWidth() * intersection.getBoundsInLocal().getHeight();
        return area;
    }
    public Point2D collisionInBoardPoint(MainBoard mainBoard) {
        double x = getLayoutX();
        double y = getLayoutY();
        if (getLayoutY() - 2 * radius < 0)
            y = -1E6;
        if (getLayoutY() + 2 * radius > mainBoard.getHeight())
            y = 1E6;
        if (getLayoutX() < 2 * radius)
            x = -1E6;
        if (getLayoutX() + 2 * radius > mainBoard.getWidth())
            x = 1E6;
        return new Point2D(x, y);
    }
}
