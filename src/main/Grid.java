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

    public Element[] nodeIDGeneration(FileData fileData) {
        int counterOfElements = 0;
        System.out.println("\nAll information of elements:\n");
        for (int i = 0; i < fileData.getGridWidthNumberOfElements() - 1; ++i) {
            for (int j = 1; j < fileData.getGridHeightNumberOfElements(); ++j) {
                int[] nodeID = new int[4];
                nodeID[0] = j + i * fileData.getGridHeightNumberOfElements();
                nodeID[1] = j + (i + 1) * fileData.getGridHeightNumberOfElements();
                nodeID[2] = j + (i + 1) * fileData.getGridHeightNumberOfElements() + 1;
                nodeID[3] = j + 1 + i * fileData.getGridHeightNumberOfElements();

                this.elements[counterOfElements] = new Element(nodeID, fileData.getConductivity());
                this.elements[counterOfElements++].showElement();
            }
        }
        return this.elements;
    }

    public void elementsGeneration(FileData fileData) {
        int counterOfElements = 0;
        System.out.println("\nAll information about node:\n");
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
    public void nodesToElementsGeneration(FileData fileData){
        for (int i = 0; i < fileData.getGridWidthNumberOfElements() - 1; i++) {
            for (int j = 0; j < fileData.getGridHeightNumberOfElements() - 1; j++) {
                this.elements[j + i * (fileData.getGridHeightNumberOfElements() - 1)].nodes[0] = this.nodes[j + i * fileData.getGridHeightNumberOfElements()];
                this.elements[j + i * (fileData.getGridHeightNumberOfElements() - 1)].nodes[1] = this.nodes[j + (i + 1) * fileData.getGridHeightNumberOfElements()];
                this.elements[j + i * (fileData.getGridHeightNumberOfElements() - 1)].nodes[2] = this.nodes[j + (i + 1) * fileData.getGridHeightNumberOfElements() + 1];
                this.elements[j + i * (fileData.getGridHeightNumberOfElements() - 1)].nodes[3] = this.nodes[j + 1 + i * fileData.getGridHeightNumberOfElements()];
            }
        }
    }

}
