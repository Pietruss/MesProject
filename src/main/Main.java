package main;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        int[] localId = new int[4];
        localId[0] = 1;
        localId[1] = 2;
        localId[2] = 3;
        localId[3] = 4;
        double[][] globalMatrixHArray = new double[16][16];
        double[][] globalMatrixCArray = new double[16][16];
        double[] globalVectorPArray = new double[16];

        FileData fileData = new FileData();
        fileData.openFileAndReadData();
        fileData.showInputData();

        Grid grid = new Grid();
        grid.setNumberOfElements((fileData.getGridHeightNumberOfElements() - 1) * (fileData.getGridWidthNumberOfElements() - 1));
        grid.setNumberOfNodes(fileData.getGridHeightNumberOfElements() * fileData.getGridWidthNumberOfElements());
        Element[] elements = grid.nodeIDGeneration(fileData);
        grid.elementsGeneration(fileData);
        grid.nodesToElementsGeneration(fileData);
//        for (int i = 0; i < fileData.getSimulationTime(); i += fileData.getSimulationStepTime()) {
        for (int j = 0; j < 9; j++) {
            int[] globalId = new int[4];
            globalId[0] = elements[j].nodeID[0];
            globalId[1] = elements[j].nodeID[1];
            globalId[2] = elements[j].nodeID[2];
            globalId[3] = elements[j].nodeID[3];

            UniversalElement universalElement = new UniversalElement();
            Point[] points = universalElement.getSurfaceArrayOfIntegralPoints();

            BuilderJacobian2D builderJacobian2D = new BuilderJacobian2D();
            universalElement.calculateDnDksiValue();
            universalElement.calculateDnEtaValue();
//            System.out.println("Coordinates of nodes element");
//            elements[j].showElementWithCoordinates();
            double[][] integralPointsCalculationArray = builderJacobian2D.integralPointsCalculation(universalElement.getDnDksiValueArray(), universalElement.getDnDetaValueArray(), elements[j]);
//            System.out.println("integralPointsCalculationArray");
//            builderJacobian2D.showArray2D(integralPointsCalculationArray);
            double[] detJ = builderJacobian2D.detJCalculation(builderJacobian2D.integralPointsCalculation(universalElement.getDnDksiValueArray(), universalElement.getDnDetaValueArray(), elements[j]));
//            System.out.println("DetJ Array");
//            builderJacobian2D.showArray1D(detJ);
            double[][] buildJ_1_1_1CalculationArray = builderJacobian2D.buildJ_1_1_1Calculation(builderJacobian2D.integralPointsCalculation(universalElement.getDnDksiValueArray(), universalElement.getDnDetaValueArray(), elements[j]), builderJacobian2D.detJCalculation(builderJacobian2D.integralPointsCalculation(universalElement.getDnDksiValueArray(), universalElement.getDnDetaValueArray(), elements[j])));
//            System.out.println("buildJ_1_1_1CalculationArray");
//            builderJacobian2D.showArray2D(buildJ_1_1_1CalculationArray);

            BuilderMatrixH builderMatrixH = new BuilderMatrixH();
            builderMatrixH.dnDxCalculation(universalElement, builderJacobian2D);
            double[][] dnDx = builderMatrixH.dnDxCalculation(universalElement, builderJacobian2D);
//            builderJacobian2D.showArray2D(dnDx);

            builderMatrixH.dnDyCalculation(universalElement, builderJacobian2D);
            double[][] dnDy = builderMatrixH.dnDyCalculation(universalElement, builderJacobian2D);
//            builderJacobian2D.showArray2D(dnDy);
            builderMatrixH.dN_dx_dN_dx_dN_dy_dN_dyTDetCalculation(dnDx, dnDy, detJ, fileData.getConductivity());
            double[][] matrixHLocal = builderMatrixH.matrixHCalculation();
//            builderJacobian2D.showArray2D(matrixHLocal);
            globalMatrixHArray = arrayToGlobal(localId, globalId, matrixHLocal, globalMatrixHArray);


            BuilderMatrixC builderMatrixC = new BuilderMatrixC();
            double[][] N1N2N3N4 = builderMatrixC.N1N2N3N4Calculations();
            builderMatrixC.pcNumberCalculations(detJ, fileData.getSpecificHeat(), fileData.getDensity());
            double[][] matricCLocal = builderMatrixC.MatrixCCalculation();
            arrayToGlobal(localId, globalId, matricCLocal, globalMatrixCArray);
            AreaArray[] areaArray;
            AreaGenerator areaGenerator = new AreaGenerator();
            areaArray = areaGenerator.areaStatusGenerator(grid, fileData);

            BuilderMatrixHBC2D builderMatrixHBC2D = new BuilderMatrixHBC2D(areaArray);
            double[] lengthSideArray = builderMatrixHBC2D.lengthSideCalculation(elements[j]);
//            builderJacobian2D.showArray1D(lengthSideArray);
            double[] lengthSideArrayDetJ = builderMatrixHBC2D.detJCalculations(lengthSideArray);

            double[] areaPoint1 = builderMatrixHBC2D.N1N2N3N4Calculations(points[0]);
            double[] areaPoint2 = builderMatrixHBC2D.N1N2N3N4Calculations(points[1]);
            VectorP vectorP = new VectorP();

            if (areaArray[j].area[0] == 1) {
                double p1Array[] = vectorP.vectorPCalculation(0.03333, 1200, points[0], 300);
                vectorPToGlobal(localId, globalId, p1Array, globalVectorPArray);
                double p2Array[] = vectorP.vectorPCalculation(0.03333, 1200, points[1], 300);
                vectorPToGlobal(localId, globalId, p2Array, globalVectorPArray);
            }

            builderMatrixHBC2D.areaCalculations(areaPoint1, areaPoint2, 0, lengthSideArrayDetJ);
            double[][] areaSum1 = copyArray(builderMatrixHBC2D.areaCalculations(areaPoint1, areaPoint2, 0, lengthSideArrayDetJ));


            double[] areaPoint3 = builderMatrixHBC2D.N1N2N3N4Calculations(points[2]);
            double[] areaPoint4 = builderMatrixHBC2D.N1N2N3N4Calculations(points[3]);

            if (areaArray[j].area[1] == 1) {
                double p3Array[] = vectorP.vectorPCalculation(0.03333, 1200, points[2], 300);
                vectorPToGlobal(localId, globalId, p3Array, globalVectorPArray);
                double p4Array[] = vectorP.vectorPCalculation(0.03333, 1200, points[3], 300);
                vectorPToGlobal(localId, globalId, p4Array, globalVectorPArray);
            }


            builderMatrixHBC2D.areaCalculations(areaPoint3, areaPoint4, 1, lengthSideArrayDetJ);
            double[][] areaSum2 = copyArray(builderMatrixHBC2D.areaCalculations(areaPoint3, areaPoint4, 1, lengthSideArrayDetJ));

            if (areaArray[j].area[2] == 1) {
                double p5Array[] = vectorP.vectorPCalculation(0.03333, 1200, points[4], 300);
                vectorPToGlobal(localId, globalId, p5Array, globalVectorPArray);
                double p6Array[] = vectorP.vectorPCalculation(0.03333, 1200, points[5], 300);
                vectorPToGlobal(localId, globalId, p6Array, globalVectorPArray);
            }

            double[] areaPoint5 = builderMatrixHBC2D.N1N2N3N4Calculations(points[4]);
            double[] areaPoint6 = builderMatrixHBC2D.N1N2N3N4Calculations(points[5]);


            builderMatrixHBC2D.areaCalculations(areaPoint5, areaPoint6, 2, lengthSideArrayDetJ);
            double[][] areaSum3 = copyArray(builderMatrixHBC2D.areaCalculations(areaPoint5, areaPoint6, 2, lengthSideArrayDetJ));

            if (areaArray[j].area[3] == 1) {
                double p7Array[] = vectorP.vectorPCalculation(0.03333, 1200, points[6], 300);
                vectorPToGlobal(localId, globalId, p7Array, globalVectorPArray);
                double p8Array[] = vectorP.vectorPCalculation(0.03333, 1200, points[7], 300);
                vectorPToGlobal(localId, globalId, p8Array, globalVectorPArray);
            }

            double[] areaPoint7 = builderMatrixHBC2D.N1N2N3N4Calculations(points[6]);
            double[] areaPoint8 = builderMatrixHBC2D.N1N2N3N4Calculations(points[7]);
            builderMatrixHBC2D.areaCalculations(areaPoint7, areaPoint8, 3, lengthSideArrayDetJ);
            double[][] areaSum4 = copyArray(builderMatrixHBC2D.areaCalculations(areaPoint7, areaPoint8, 3, lengthSideArrayDetJ));

            builderMatrixHBC2D.matrixHCalculation(areaArray[j], areaSum1, areaSum2, areaSum3, areaSum4);

        }
        showGlobalArrayVectorP(globalVectorPArray);
//        printGlobalArray(globalMatrixHArray);
//        printGlobalArray(globalMatrixCArray);

//        }

//
//
//
//        double[] detJ;
//        builderMatrixHBC2D.detJCalculations();
//        detJ = builderMatrixHBC2D.getDetJ();
//        for (int i = 0; i < (fileData.getGridWidthNumberOfElements() - 1) * (fileData.getGridHeightNumberOfElements() - 1); i++) {
//            System.out.println("\n" + i);
//            builderMatrixHBC2D.areaArrayInit(areaArray[i]);
//        }
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

    private static double[][] arrayToGlobal(int[] localId, int[] globalId, double[][] localArray, double[][] globalArray) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int localx = localId[j];
                int localy = localId[i];

                int globalx = globalId[localx - 1];
                int globaly = globalId[localy - 1];

                globalArray[globalx - 1][globaly - 1] += localArray[i][j];
            }
        }
        return globalArray;
    }

    private static void printGlobalArray(double[][] globalArray) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(globalArray[i][j] + " ");
            }
            System.out.println("");
        }
    }

    private static double[][] copyArray(double[][] array) {
        double[][] newArray = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newArray[i][j] = array[i][j];
            }
        }
        return newArray;
    }

    private static double[] vectorPToGlobal(int[] localId, int[] globalId, double[] localArray, double[] globalArray) {
        for (int i = 0; i < 4; i++) {
            int localx = localId[i];
            int globalx = globalId[localx - 1];

            globalArray[globalx - 1] += localArray[i];

        }

        return globalArray;
    }

    private static void showGlobalArrayVectorP(double[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("");
    }
}
