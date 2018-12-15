package main;

public class AreaGenerator {
    AreaArray[] areaArray = new AreaArray[24];

    public AreaArray[] areaStatusGenerator(Grid grid, FileData fileData) {
        for (int i = 0; i < fileData.getGridWidthNumberOfElements() - 1; ++i) {
            for (int j = 0; j < fileData.getGridHeightNumberOfElements() - 1; ++j) {
                double[] areaStatus = new double[4];
                areaArray[j + i * (fileData.getGridWidthNumberOfElements() - 1)] = new AreaArray(areaStatus);
                if (grid.nodes[j + i * fileData.getGridHeightNumberOfElements()].borderCondition && grid.nodes[j + (i + 1) * fileData.getGridHeightNumberOfElements()].borderCondition) {
                    areaArray[j + i * (fileData.getGridWidthNumberOfElements() - 1)].area[0] = 1;
                }
                if (grid.nodes[j + (i + 1) * fileData.getGridHeightNumberOfElements()].borderCondition && grid.nodes[j + (i + 1) * fileData.getGridHeightNumberOfElements() + 1].borderCondition) {
                    areaArray[j + i * (fileData.getGridWidthNumberOfElements() - 1)].area[1] = 1;
                }
                if (grid.nodes[j + (i + 1) * fileData.getGridHeightNumberOfElements() + 1].borderCondition && grid.nodes[j + 1 + i * fileData.getGridHeightNumberOfElements()].borderCondition) {
                    areaArray[j + i * (fileData.getGridWidthNumberOfElements() - 1)].area[2] = 1;
                }
                if (grid.nodes[j + 1 + i * fileData.getGridHeightNumberOfElements()].borderCondition && grid.nodes[j + i * fileData.getGridHeightNumberOfElements()].borderCondition) {
                    areaArray[j + i * (fileData.getGridWidthNumberOfElements() - 1)].area[3] = 1;
                }

            }
        }
        return areaArray;
    }
}
