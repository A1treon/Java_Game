package org.project.gameObjects.entity;

public class GravityEntity extends Entity {

    private boolean isGrounded;

    public GravityEntity() {
        super();
        this.getPhysics().setGravityOn(true);
    }

    public void setIsGrounded(boolean isGrounded) {
        this.isGrounded = isGrounded;
    }

    @Override
    public void moveVertical(double velocity) {
        if (isGrounded) {
            this.getPhysics().setYVelocity(velocity);
        } else {
            super.setYPos(super.getPhysics().getNewYPosition(super.getYPos()));
        }
    }

}
