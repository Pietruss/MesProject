package main;

public class Grid {
    public Node[] nodes;
    public Element[] elements;

    public void setNumberOfElements(int numberOfElements) {
        this.elements = new Element[numberOfElements];
    }

    public void setNumberOfNodes(int numberOfNodes) {
        this.nodes = new Node[numberOfNodes];
    }

    public Node getNodesByID(int id) {
        return nodes[id];
    }

    public void nodeIDGeneration(FileData fileData) {
        int counterOfElements = 0;
        for (int i = 0; i < fileData.getGridWidthNumberOfElements() - 1; ++i) {
            for (int j = 1; j < fileData.getGridHeightNumberOfElements(); ++j) {
                int[] nodeID = new int[4];
                nodeID[0] = j + i * fileData.getGridHeightNumberOfElements();
                nodeID[1] = j + (i + 1) * fileData.getGridHeightNumberOfElements();
                nodeID[2] = j + (i + 1) * fileData.getGridHeightNumberOfElements() + 1;
                nodeID[3] = j + 1 + i * fileData.getGridHeightNumberOfElements();

                this.elements[counterOfElements] = new Element(nodeID, fileData.getCoefficient());
                this.elements[counterOfElements++].showElement();


            }
        }
    }

    public void elementsGeneration(FileData fileData) {
        int counterOfElements = 0;
        for (int i = 0; i < fileData.getGridWidthNumberOfElements(); ++i) {
            for (int j = 0; j < fileData.getGridHeightNumberOfElements(); ++j) {
                this.nodes[counterOfElements] = new Node(i * (fileData.getGridWidth()) / (fileData.getGridWidthNumberOfElements() - 1), j * (fileData.getGridHeight()) / (fileData.getGridHeightNumberOfElements() - 1));
                if (i == 0 || i == fileData.getGridWidthNumberOfElements() - 1 || j == 0 || j == fileData.getGridHeightNumberOfElements() - 1) {
                    this.nodes[counterOfElements].borderCondition = true;
                }
                this.nodes[counterOfElements++].showNode();
            }
        }
    }
}
