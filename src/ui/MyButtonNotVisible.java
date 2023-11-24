package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class MyButtonNotVisible {

    private int x, y, width, height;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;


    public MyButtonNotVisible(String text, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        initBounds();
    }

    private void initBounds() {
        this.bounds = new Rectangle(x, y, width, height);
    }

    public void draw(Graphics g) {

        //Body
        drawBody(g);

    }

    private void drawBody(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
    }

    public void resetBooleans() {
        this.mouseOver = false;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public void setMousePressed(boolean setMousePressed) {
        this.mousePressed = mousePressed;
    }

    public Rectangle getBounds() {
        return bounds;
    }
    
}
