package main;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        int[] localId = {1, 2, 3, 4};
        double[][] globalMatrixHArray = new double[16][16];
        double[][] globalMatrixCArray = new double[16][16];
        double[] globalVectorPArray = new double[16];
        double[] vectorPAfterCalculation;
        double[][] matrixHAfterCalculation;
        double[][] globalMatrixHBC2D = new double[16][16];

        FileData fileData = new FileData();
        fileData.openFileAndReadData();
        fileData.showInputData();

        Grid grid = new Grid();
        grid.setNumberOfElements((fileData.getGridHeightNumberOfElements() - 1) * (fileData.getGridWidthNumberOfElements() - 1));
        grid.setNumberOfNodes(fileData.getGridHeightNumberOfElements() * fileData.getGridWidthNumberOfElements());
        Element[] elements = grid.nodeIDGeneration(fileData);
        grid.elementsGeneration(fileData);
        grid.nodesToElementsGeneration(fileData);
        GaussMethod gaussMethod = new GaussMethod();
        GlobalOperations globalOperations = new GlobalOperations();
        double[] arrayOfTemperature;


        for (int q = 0; q < 16; q++) {
            grid.nodes[q].setTemperature(fileData.getInitialTemperature());
            System.out.print(grid.nodes[q].getTemperature() + " ");
        }
        System.out.println("\n");



        for (double i = 0; i < fileData.getSimulationTime(); i += fileData.getSimulationStepTime()) {
            for (int j = 0; j < (fileData.getGridWidthNumberOfElements() - 1) * (fileData.getGridHeightNumberOfElements() - 1); j++) {
                int[] globalId = {elements[j].nodeID[0], elements[j].nodeID[1], elements[j].nodeID[2], elements[j].nodeID[3]};

                UniversalElement universalElement = new UniversalElement();
                Point[] points = universalElement.getSurfaceArrayOfIntegralPoints();

                BuilderJacobian2D builderJacobian2D = new BuilderJacobian2D();
                universalElement.calculateDnDksiValue();
                universalElement.calculateDnEtaValue();
                builderJacobian2D.buildJ_1_1Calculation(universalElement.getDnDksiValueArray(), universalElement.getDnDetaValueArray(), elements[j]);
                double[] detJ = builderJacobian2D.detJCalculation(builderJacobian2D.buildJ_1_1Calculation(universalElement.getDnDksiValueArray(), universalElement.getDnDetaValueArray(), elements[j]));
                builderJacobian2D.buildJ_1_1_1Calculation(builderJacobian2D.buildJ_1_1Calculation(universalElement.getDnDksiValueArray(), universalElement.getDnDetaValueArray(), elements[j]), builderJacobian2D.detJCalculation(builderJacobian2D.buildJ_1_1Calculation(universalElement.getDnDksiValueArray(), universalElement.getDnDetaValueArray(), elements[j])));

                BuilderMatrixH builderMatrixH = new BuilderMatrixH();
                builderMatrixH.dnDxCalculation(universalElement, builderJacobian2D);
                double[][] dnDx = builderMatrixH.dnDxCalculation(universalElement, builderJacobian2D);

                builderMatrixH.dnDyCalculation(universalElement, builderJacobian2D);
                double[][] dnDy = builderMatrixH.dnDyCalculation(universalElement, builderJacobian2D);
                builderMatrixH.dN_dx_dN_dx_dN_dy_dN_dyTDetCalculation(dnDx, dnDy, detJ, fileData.getConductivity());
                double[][] matrixHLocal = builderMatrixH.matrixHCalculation();
                globalMatrixHArray = globalOperations.arrayToGlobal(localId, globalId, matrixHLocal, globalMatrixHArray);

                BuilderMatrixC builderMatrixC = new BuilderMatrixC();
                builderMatrixC.N1N2N3N4Calculations();
                builderMatrixC.pcNumberCalculations(detJ, fileData.getSpecificHeat(), fileData.getDensity());
                double[][] matrixCLocal = builderMatrixC.MatrixCCalculation();
                globalMatrixCArray = globalOperations.arrayToGlobal(localId, globalId, matrixCLocal, globalMatrixCArray);
                AreaArray[] areaArray;
                AreaGenerator areaGenerator = new AreaGenerator();
                areaArray = areaGenerator.areaStatusGenerator(grid, fileData);

                BuilderMatrixHBC2D builderMatrixHBC2D = new BuilderMatrixHBC2D(areaArray);
                double[] lengthSideArray = builderMatrixHBC2D.lengthSideCalculation(elements[j]);
                double[] lengthSideArrayDetJ = builderMatrixHBC2D.detJCalculations(lengthSideArray);

                double[] areaPoint1 = builderMatrixHBC2D.N1N2N3N4Calculations(points[0]);
                double[] areaPoint2 = builderMatrixHBC2D.N1N2N3N4Calculations(points[1]);

                VectorP vectorP = new VectorP();

                if (areaArray[j].area[0] == 1) {
                    double p1Array[] = vectorP.vectorPCalculation((fileData.getGridWidth()) / (fileData.getGridHeightNumberOfElements()- 1), fileData.getAmbientTemperature(), points[0], fileData.getCoefficientAlpha());
                    globalOperations.vectorPToGlobal(localId, globalId, p1Array, globalVectorPArray);
                    double p2Array[] = vectorP.vectorPCalculation((fileData.getGridWidth()) / (fileData.getGridHeightNumberOfElements()- 1), fileData.getAmbientTemperature(), points[1], fileData.getCoefficientAlpha());
                    globalOperations.vectorPToGlobal(localId, globalId, p2Array, globalVectorPArray);
                }

                builderMatrixHBC2D.areaCalculations(areaPoint1, areaPoint2, 0, lengthSideArrayDetJ, fileData.getCoefficientAlpha());
                double[][] areaSum1 = globalOperations.copyArray(builderMatrixHBC2D.areaCalculations(areaPoint1, areaPoint2, 0, lengthSideArrayDetJ, fileData.getCoefficientAlpha()));


                double[] areaPoint3 = builderMatrixHBC2D.N1N2N3N4Calculations(points[2]);
                double[] areaPoint4 = builderMatrixHBC2D.N1N2N3N4Calculations(points[3]);

                if (areaArray[j].area[1] == 1) {
                    double p3Array[] = vectorP.vectorPCalculation((fileData.getGridHeight()) / (fileData.getGridHeightNumberOfElements()- 1), fileData.getAmbientTemperature(), points[2], fileData.getCoefficientAlpha());
                    globalOperations.vectorPToGlobal(localId, globalId, p3Array, globalVectorPArray);
                    double p4Array[] = vectorP.vectorPCalculation((fileData.getGridHeight()) / (fileData.getGridHeightNumberOfElements()- 1), fileData.getAmbientTemperature(), points[3], fileData.getCoefficientAlpha());
                    globalOperations.vectorPToGlobal(localId, globalId, p4Array, globalVectorPArray);
                }

                builderMatrixHBC2D.areaCalculations(areaPoint3, areaPoint4, 1, lengthSideArrayDetJ, fileData.getCoefficientAlpha());
                double[][] areaSum2 = globalOperations.copyArray(builderMatrixHBC2D.areaCalculations(areaPoint3, areaPoint4, 1, lengthSideArrayDetJ, fileData.getCoefficientAlpha()));

                if (areaArray[j].area[2] == 1) {
                    double p5Array[] = vectorP.vectorPCalculation((fileData.getGridWidth()) / (fileData.getGridHeightNumberOfElements()- 1), fileData.getAmbientTemperature(), points[4], fileData.getCoefficientAlpha());
                    globalOperations.vectorPToGlobal(localId, globalId, p5Array, globalVectorPArray);
                    double p6Array[] = vectorP.vectorPCalculation((fileData.getGridWidth()) / (fileData.getGridHeightNumberOfElements()- 1), fileData.getAmbientTemperature(), points[5], fileData.getCoefficientAlpha());
                    globalOperations.vectorPToGlobal(localId, globalId, p6Array, globalVectorPArray);
                }

                double[] areaPoint5 = builderMatrixHBC2D.N1N2N3N4Calculations(points[4]);
                double[] areaPoint6 = builderMatrixHBC2D.N1N2N3N4Calculations(points[5]);


                builderMatrixHBC2D.areaCalculations(areaPoint5, areaPoint6, 2, lengthSideArrayDetJ, fileData.getCoefficientAlpha());
                double[][] areaSum3 = globalOperations.copyArray(builderMatrixHBC2D.areaCalculations(areaPoint5, areaPoint6, 2, lengthSideArrayDetJ, fileData.getCoefficientAlpha()));

                if (areaArray[j].area[3] == 1) {
                    double p7Array[] = vectorP.vectorPCalculation((fileData.getGridHeight()) / (fileData.getGridHeightNumberOfElements()- 1), fileData.getAmbientTemperature(), points[6], fileData.getCoefficientAlpha());
                    globalOperations.vectorPToGlobal(localId, globalId, p7Array, globalVectorPArray);
                    double p8Array[] = vectorP.vectorPCalculation((fileData.getGridHeight()) / (fileData.getGridHeightNumberOfElements()- 1), fileData.getAmbientTemperature(), points[7], fileData.getCoefficientAlpha());
                    globalOperations.vectorPToGlobal(localId, globalId, p8Array, globalVectorPArray);
                }

                double[] areaPoint7 = builderMatrixHBC2D.N1N2N3N4Calculations(points[6]);
                double[] areaPoint8 = builderMatrixHBC2D.N1N2N3N4Calculations(points[7]);
                builderMatrixHBC2D.areaCalculations(areaPoint7, areaPoint8, 3, lengthSideArrayDetJ, fileData.getCoefficientAlpha());
                double[][] areaSum4 = globalOperations.copyArray(builderMatrixHBC2D.areaCalculations(areaPoint7, areaPoint8, 3, lengthSideArrayDetJ, fileData.getCoefficientAlpha()));

                double[][] localHBC2D = builderMatrixHBC2D.matrixHCalculation(areaArray[j], areaSum1, areaSum2, areaSum3, areaSum4);
                globalMatrixHBC2D = globalOperations.arrayToGlobal(localId, globalId, localHBC2D, globalMatrixHBC2D);

            }
            //global Matrix and VectorP operations
//                System.out.println("-------------");
//                globalOperations.showGlobalArray(globalMatrixCArray);
//                System.out.println("-------------");
            matrixHAfterCalculation = globalOperations.globalMatrixHCalculation(globalMatrixHArray, globalMatrixCArray, fileData.getSimulationStepTime(), globalMatrixHBC2D);
            vectorPAfterCalculation = globalOperations.globalVectorPOperation(globalVectorPArray, globalMatrixCArray, fileData.getSimulationStepTime(), grid);

            // result of gaussMethod
            arrayOfTemperature = gaussMethod.GaussCalculation(fileData.getGridHeightNumberOfElements() * fileData.getGridWidthNumberOfElements(), matrixHAfterCalculation, vectorPAfterCalculation);

            // show important value
            System.out.println("Number of iteration: " + i / fileData.getSimulationStepTime());

            globalOperations.showNodesTemperature(arrayOfTemperature);
//            globalOperations.showGlobalArrayVectorP(globalVectorPArray);
//            globalOperations.showGlobalArray(globalMatrixHArray);


            // matrix reset
            for (int w = 0; w < 16; w++) {
                grid.nodes[w].setTemperature(arrayOfTemperature[w]);
                for (int r = 0; r < 16; r++) {
                    globalMatrixHArray[w][r] = 0;
                    globalMatrixCArray[w][r] = 0;
                    matrixHAfterCalculation[w][r] = 0;
                    matrixHAfterCalculation[w][r] = 0;
                    globalMatrixHBC2D[w][r] = 0;
                }
                globalVectorPArray[w] = 0;
                vectorPAfterCalculation[w] = 0;
            }
        }
    }
}
