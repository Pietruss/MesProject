package main;

import interfaces.ShowElement;

public class Element implements ShowElement {
    public int[] nodeID;
    public Node[] nodes = new Node[4];

    public int[] getNodeID() {
        return nodeID;
    }

    public Element(Element element) {

    }

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
    public void showElementWithCoordinates(){
        System.out.println(nodes[0].getxCoordinate() + " " + nodes[0].getyCoordinate());
        System.out.println(nodes[1].getxCoordinate() + " " + nodes[1].getyCoordinate());
        System.out.println(nodes[2].getxCoordinate() + " " + nodes[2].getyCoordinate());
        System.out.println(nodes[3].getxCoordinate() + " " + nodes[3].getyCoordinate());
    }

}
