package main;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        int[] localId = new int[4];
        localId[0] = 1;
        localId[1] = 2;
        localId[2] = 3;
        localId[3] = 4;


        FileData fileData = new FileData();
        fileData.openFileAndReadData();
        fileData.showInputData();

        Grid grid = new Grid();
        grid.setNumberOfElements((fileData.getGridHeightNumberOfElements() - 1) * (fileData.getGridWidthNumberOfElements() - 1));
        grid.setNumberOfNodes(fileData.getGridHeightNumberOfElements() * fileData.getGridWidthNumberOfElements());
        grid.nodeIDGeneration(fileData);
        grid.elementsGeneration(fileData);

        UniversalElement universalElement = new UniversalElement();


        universalElement.calculateDn_Dksi_Dn_Eta_Value();
        BuilderMatrixH builderMatrixH = new BuilderMatrixH();
        builderMatrixH.BuilderJacobian(universalElement, grid);


        BuilderMatrixC builderMatrixC = new BuilderMatrixC();
        builderMatrixC.N1N2N3N4Calculations();
        builderMatrixC.pcNumberCalculations(builderMatrixH);
        builderMatrixC.MatrixCCalculation();

        AreaArray[] areaArray;

        AreaGenerator areaGenerator = new AreaGenerator();
        areaArray = areaGenerator.areaStatusGenerator(grid, fileData);
        for (int i = 0; i < (fileData.getGridWidthNumberOfElements() - 1) * (fileData.getGridHeightNumberOfElements() - 1); i++) {
            System.out.println(areaArray[i].area[0] + " " + areaArray[i].area[1] + " " + areaArray[i].area[2] + " " + areaArray[i].area[3]);
        }
        double[] detJ;
        BuilderMatrixHBC2D builderMatrixHBC2D = new BuilderMatrixHBC2D(areaArray);
        builderMatrixHBC2D.detJCalculations();
        detJ = builderMatrixHBC2D.getDetJ();
        for (int i = 0; i < (fileData.getGridWidthNumberOfElements() - 1) * (fileData.getGridHeightNumberOfElements() - 1); i++) {
            System.out.println("\n" + i);
            builderMatrixHBC2D.areaArrayInit(areaArray[i]);
        }
//        arrayToGlobal
//        double [][] array = new double[16][16];
//        builderMatrixC.printArray(array);
//
//        Point [] surfaceIntegrationPoints = new Point[8];
//        surfaceIntegrationPoints[0] = new Point(-0.57, -1);
//        surfaceIntegrationPoints[1] = new Point(0.57, -1);
//        surfaceIntegrationPoints[2] = new Point(1, -0.57);
//        surfaceIntegrationPoints[3] = new Point(1, 0.57);
//        surfaceIntegrationPoints[4] = new Point(0.57, 1);
//        surfaceIntegrationPoints[5] = new Point(-0.57, 1);
//        surfaceIntegrationPoints[6] = new Point(-1, 0.57);
//        surfaceIntegrationPoints[7] = new Point(-1, -0.57);
//
//        VectorP vectorP = new VectorP();
////        for (int i = 0; i < 8; i++) {
////
////        }
//        vectorP.vectorPCalculation(detJ, fileData.getInitialTemperature(), surfaceIntegrationPoints[0]);


    }

    public void arrayToGlobal(int[] localId, int[] globalId, double[][] localArray, double[][] globalArray) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int localx = localId[j];
                int localy = localId[i];

                int globalx = globalId[localx - 1];
                int globaly = globalId[localy - 1];

                globalArray[globalx - 1][globaly - 1] += localArray[i][j];
            }
        }
    }
}
