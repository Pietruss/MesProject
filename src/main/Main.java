package main;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        FileData fileData = new FileData();
        fileData.openFileAndReadData();

        Grid grid = new Grid();

        grid.setNumberOfElements((fileData.getGridHeightNumberOfElements() - 1) * (fileData.getGridWidthNumberOfElements() - 1));
        grid.setNumberOfNodes(fileData.getGridHeight() * fileData.getGridWidth());

        grid.nodeIDGeneration(fileData);
        grid.elementsGeneration(fileData);

        Element element = grid.elements[0];

        UniversalElement universalElement = new UniversalElement();
        universalElement.calculateDn_Dksi_Dn_Eta_Value();
        BuilderMatrixH builderMatrixH = new BuilderMatrixH();
        builderMatrixH.BuilderJacobian(universalElement, grid);

        BuilderMatrixC builderMatrixC = new BuilderMatrixC();
        builderMatrixC.N1N2N3N4Calculations();
        builderMatrixC.pcNumberCalculations(builderMatrixH);
        builderMatrixC.MatrixCCalculation();

        AreaArray []areaArray;

        AreaGenerator areaGenerator = new AreaGenerator();
        areaArray = areaGenerator.areaStatusGenerator(grid, fileData);
        for (int i = 0; i < 15; i++) {
                System.out.println(areaArray[i].area[0] + " " + areaArray[i].area[1] + " " + areaArray[i].area[2] + " " + areaArray[i].area[3]);
        }
        double []detJ;
        BuilderMatrixHBC2D builderMatrixHBC2D = new BuilderMatrixHBC2D(areaArray);
        builderMatrixHBC2D.detJCalculations();
        detJ = builderMatrixHBC2D.getDetJ();
        for (int i = 0; i < 15; i++) {
            System.out.println(i);
            builderMatrixHBC2D.areaArrayInit(areaArray[i]);
        }

        Point [] surfaceIntegrationPoints = new Point[8];
        surfaceIntegrationPoints[0] = new Point(-0.57, -1);
        surfaceIntegrationPoints[1] = new Point(0.57, -1);
        surfaceIntegrationPoints[2] = new Point(1, -0.57);
        surfaceIntegrationPoints[3] = new Point(1, 0.57);
        surfaceIntegrationPoints[4] = new Point(0.57, 1);
        surfaceIntegrationPoints[5] = new Point(-0.57, 1);
        surfaceIntegrationPoints[6] = new Point(-1, 0.57);
        surfaceIntegrationPoints[7] = new Point(-1, -0.57);

        VectorP vectorP = new VectorP();
//        for (int i = 0; i < 8; i++) {
//
//        }
        vectorP.vectorPCalculation(detJ, fileData.getTemperature(), surfaceIntegrationPoints[0]);






    }
}
