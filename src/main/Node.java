package main;

import interfaces.ShowNode;

public class Node implements ShowNode {
    public double getxCoordinate() {
        return xCoordinate;
    }

    public double getyCoordinate() {
        return yCoordinate;
    }

    double xCoordinate, yCoordinate, temperature;
    boolean borderCondition = false;

    public Node(double xCoordinate, double yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public boolean isBorderCondition() {
        return borderCondition;
    }

    public Node(double xCoordinate, double yCoordinate, boolean borderCondition) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.borderCondition = borderCondition;
    }

    @Override
    public void showNode() {
        System.out.println("x: " + this.xCoordinate +" y: " + " " + this.yCoordinate + " " + this.borderCondition);
    }


}
