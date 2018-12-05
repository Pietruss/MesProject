package main;

public class AreaGenerator {
    private double [] area;

    public void areaStatusGenerator(Grid grid, FileData fileData) {
        for (int i = 0; i < fileData.getGridWidthNumberOfElements() - 1; ++i) {
            for (int j = 0; j < fileData.getGridHeightNumberOfElements() - 1; ++j) {
                double[] areaStatus = new double[4];
                if (grid.nodes[j + i * fileData.getGridHeightNumberOfElements()].borderCondition == true && grid.nodes[j + (i + 1) * fileData.getGridHeightNumberOfElements()].borderCondition == true) {
                    areaStatus[0] = 1;
                }
                if (grid.nodes[j + (i + 1) * fileData.getGridHeightNumberOfElements()].borderCondition == true && grid.nodes[j + (i + 1) * fileData.getGridHeightNumberOfElements() + 1].borderCondition == true) {
                    areaStatus[1] = 1;
                }
                if (grid.nodes[j + (i + 1) * fileData.getGridHeightNumberOfElements() + 1].borderCondition == true && grid.nodes[j + 1 + i * fileData.getGridHeightNumberOfElements()].borderCondition == true) {
                    areaStatus[2] = 1;
                }
                if (grid.nodes[j + 1 + i * fileData.getGridHeightNumberOfElements()].borderCondition == true && grid.nodes[j + i * fileData.getGridHeightNumberOfElements()].borderCondition == true) {
                    areaStatus[3] = 1;
                }
            }
        }
    }
}
