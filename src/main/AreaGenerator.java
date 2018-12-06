package main;

public class AreaGenerator {
    AreaArray[] areaArray = new AreaArray[24];

    public AreaArray[] areaStatusGenerator(Grid grid, FileData fileData) {
        for (int i = 0; i < fileData.getGridWidthNumberOfElements() - 1; ++i) {
            System.out.println("--------");
            for (int j = 0; j < fileData.getGridHeightNumberOfElements() - 1; ++j) {
                System.out.println(j + i * fileData.getGridWidthNumberOfElements() + i);
                double[] areaStatus = new double[4];
                areaArray[j + i * fileData.getGridWidthNumberOfElements() + i] = new AreaArray(areaStatus);
                if (grid.nodes[j + i * fileData.getGridHeightNumberOfElements()].borderCondition && grid.nodes[j + (i + 1) * fileData.getGridHeightNumberOfElements()].borderCondition) {
                    areaArray[j + i * fileData.getGridWidthNumberOfElements() + i].area[0] = 1;
                }
                if (grid.nodes[j + (i + 1) * fileData.getGridHeightNumberOfElements()].borderCondition && grid.nodes[j + (i + 1) * fileData.getGridHeightNumberOfElements() + 1].borderCondition) {
                    areaArray[j + i * fileData.getGridWidthNumberOfElements() + i].area[1] = 1;
                }
                if (grid.nodes[j + (i + 1) * fileData.getGridHeightNumberOfElements() + 1].borderCondition && grid.nodes[j + 1 + i * fileData.getGridHeightNumberOfElements()].borderCondition) {
                    areaArray[j + i * fileData.getGridWidthNumberOfElements() + i].area[2] = 1;
                }
                if (grid.nodes[j + 1 + i * fileData.getGridHeightNumberOfElements()].borderCondition && grid.nodes[j + i * fileData.getGridHeightNumberOfElements()].borderCondition) {
                    areaArray[j + i * fileData.getGridWidthNumberOfElements() + i].area[3] = 1;
                }

            }
        }

        return areaArray;
    }
}
