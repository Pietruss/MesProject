package main;

import interfaces.ShowElement;

public class Element implements ShowElement {
    public int[] nodeID;

    public double getCoefficient() {
        return coefficient;
    }

    double coefficient;

    public Element(int[] nodeID, double coefficient) {
        this.nodeID = nodeID;
        this.coefficient = coefficient;
    }

    @Override
    public void showElement() {
        System.out.println(nodeID[0] + " " + nodeID[1] + " " + nodeID[2] + " " + nodeID[3]);
    }

}
