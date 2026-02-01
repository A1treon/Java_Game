package org.project.gameObjects.surfaces;

import org.project.gameObjects.Collider;
import org.project.gameObjects.GameObject;

public class Surface extends GameObject {
    private Collider collider;

    public Surface() {
        this.collider = new Collider();
    }

    @Override
    public void setXPos(double xPos) {
        super.setXPos(xPos);
        syncCollider();
    }

    @Override
    public void setYPos(double yPos) {
        super.setYPos(yPos);
        syncCollider();
    }

    @Override
    public void setWidth(double width) {
        super.setWidth(width);
        syncCollider();
    }

    @Override
    public void setHeight(double height) {
        super.setHeight(height);
        syncCollider();
    }

    private void syncCollider() {
        collider.setXPos(this.getXPos());
        collider.setYPos(this.getYPos());
        collider.setWidth(this.getWidth());
        collider.setHeight(this.getHeight());
    }
}
