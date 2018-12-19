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
        double[] vectorPAfterCalculation = new double[16];
        double[][] matrixHAfterCalculation = new double[16][16];
        double[][] matrixHBC2DGlobal = new double[16][16];

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
        double[] arrayOfTemperature = new double[fileData.getGridHeightNumberOfElements() * fileData.getGridWidthNumberOfElements()];

        //initalization arrayOfTemperature
        for (int q = 0; q < 16; q++) {
            grid.nodes[q].setTemperature(fileData.getInitialTemperature());
            arrayOfTemperature[q] = grid.nodes[q].getTemperature();
        }
        showNodesTemperature(arrayOfTemperature);


        for (double i = 0; i < fileData.getSimulationTime(); i += fileData.getSimulationStepTime()) {
            for (int j = 0; j < (fileData.getGridWidthNumberOfElements() - 1) * (fileData.getGridHeightNumberOfElements() - 1); j++) {
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
                builderJacobian2D.integralPointsCalculation(universalElement.getDnDksiValueArray(), universalElement.getDnDetaValueArray(), elements[j]);
                double[] detJ = builderJacobian2D.detJCalculation(builderJacobian2D.integralPointsCalculation(universalElement.getDnDksiValueArray(), universalElement.getDnDetaValueArray(), elements[j]));
                builderJacobian2D.buildJ_1_1_1Calculation(builderJacobian2D.integralPointsCalculation(universalElement.getDnDksiValueArray(), universalElement.getDnDetaValueArray(), elements[j]), builderJacobian2D.detJCalculation(builderJacobian2D.integralPointsCalculation(universalElement.getDnDksiValueArray(), universalElement.getDnDetaValueArray(), elements[j])));

                BuilderMatrixH builderMatrixH = new BuilderMatrixH();
                builderMatrixH.dnDxCalculation(universalElement, builderJacobian2D);
                double[][] dnDx = builderMatrixH.dnDxCalculation(universalElement, builderJacobian2D);

                builderMatrixH.dnDyCalculation(universalElement, builderJacobian2D);
                double[][] dnDy = builderMatrixH.dnDyCalculation(universalElement, builderJacobian2D);
                builderMatrixH.dN_dx_dN_dx_dN_dy_dN_dyTDetCalculation(dnDx, dnDy, detJ, fileData.getConductivity());
                double[][] matrixHLocal = builderMatrixH.matrixHCalculation();
                globalMatrixHArray = arrayToGlobal(localId, globalId, matrixHLocal, globalMatrixHArray);

                BuilderMatrixC builderMatrixC = new BuilderMatrixC();
                double[][] N1N2N3N4 = builderMatrixC.N1N2N3N4Calculations();
                builderMatrixC.pcNumberCalculations(detJ, fileData.getSpecificHeat(), fileData.getDensity());
                double[][] matrixCLocal = builderMatrixC.MatrixCCalculation();
                globalMatrixCArray = arrayToGlobal(localId, globalId, matrixCLocal, globalMatrixCArray);
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
                    double p1Array[] = vectorP.vectorPCalculation(0.03333, fileData.getAmbientTemperature(), points[0], fileData.getCoefficientAlpha());
                    vectorPToGlobal(localId, globalId, p1Array, globalVectorPArray);
                    double p2Array[] = vectorP.vectorPCalculation(0.03333, fileData.getAmbientTemperature(), points[1], fileData.getCoefficientAlpha());
                    vectorPToGlobal(localId, globalId, p2Array, globalVectorPArray);
                }

                builderMatrixHBC2D.areaCalculations(areaPoint1, areaPoint2, 0, lengthSideArrayDetJ, fileData.getCoefficientAlpha());
                double[][] areaSum1 = copyArray(builderMatrixHBC2D.areaCalculations(areaPoint1, areaPoint2, 0, lengthSideArrayDetJ, fileData.getCoefficientAlpha()));


                double[] areaPoint3 = builderMatrixHBC2D.N1N2N3N4Calculations(points[2]);
                double[] areaPoint4 = builderMatrixHBC2D.N1N2N3N4Calculations(points[3]);

                if (areaArray[j].area[1] == 1) {
                    double p3Array[] = vectorP.vectorPCalculation(0.03333, fileData.getAmbientTemperature(), points[2], fileData.getCoefficientAlpha());
                    vectorPToGlobal(localId, globalId, p3Array, globalVectorPArray);
                    double p4Array[] = vectorP.vectorPCalculation(0.03333, fileData.getAmbientTemperature(), points[3], fileData.getCoefficientAlpha());
                    vectorPToGlobal(localId, globalId, p4Array, globalVectorPArray);
                }

                builderMatrixHBC2D.areaCalculations(areaPoint3, areaPoint4, 1, lengthSideArrayDetJ, fileData.getCoefficientAlpha());
                double[][] areaSum2 = copyArray(builderMatrixHBC2D.areaCalculations(areaPoint3, areaPoint4, 1, lengthSideArrayDetJ, fileData.getCoefficientAlpha()));

                if (areaArray[j].area[2] == 1) {
                    double p5Array[] = vectorP.vectorPCalculation(0.03333, fileData.getAmbientTemperature(), points[4], fileData.getCoefficientAlpha());
                    vectorPToGlobal(localId, globalId, p5Array, globalVectorPArray);
                    double p6Array[] = vectorP.vectorPCalculation(0.03333, fileData.getAmbientTemperature(), points[5], fileData.getCoefficientAlpha());
                    vectorPToGlobal(localId, globalId, p6Array, globalVectorPArray);
                }

                double[] areaPoint5 = builderMatrixHBC2D.N1N2N3N4Calculations(points[4]);
                double[] areaPoint6 = builderMatrixHBC2D.N1N2N3N4Calculations(points[5]);


                builderMatrixHBC2D.areaCalculations(areaPoint5, areaPoint6, 2, lengthSideArrayDetJ, fileData.getCoefficientAlpha());
                double[][] areaSum3 = copyArray(builderMatrixHBC2D.areaCalculations(areaPoint5, areaPoint6, 2, lengthSideArrayDetJ, fileData.getCoefficientAlpha()));

                if (areaArray[j].area[3] == 1) {
                    double p7Array[] = vectorP.vectorPCalculation(0.03333, fileData.getAmbientTemperature(), points[6], fileData.getCoefficientAlpha());
                    vectorPToGlobal(localId, globalId, p7Array, globalVectorPArray);
                    double p8Array[] = vectorP.vectorPCalculation(0.03333, fileData.getAmbientTemperature(), points[7], fileData.getCoefficientAlpha());
                    vectorPToGlobal(localId, globalId, p8Array, globalVectorPArray);
                }

                double[] areaPoint7 = builderMatrixHBC2D.N1N2N3N4Calculations(points[6]);
                double[] areaPoint8 = builderMatrixHBC2D.N1N2N3N4Calculations(points[7]);
                builderMatrixHBC2D.areaCalculations(areaPoint7, areaPoint8, 3, lengthSideArrayDetJ, fileData.getCoefficientAlpha());
                double[][] areaSum4 = copyArray(builderMatrixHBC2D.areaCalculations(areaPoint7, areaPoint8, 3, lengthSideArrayDetJ, fileData.getCoefficientAlpha()));

                double[][] localHBC2D = builderMatrixHBC2D.matrixHCalculation(areaArray[j], areaSum1, areaSum2, areaSum3, areaSum4);
                matrixHBC2DGlobal = arrayToGlobal(localId, globalId, localHBC2D, matrixHBC2DGlobal);


            }
            matrixHAfterCalculation = globalMatrixHCalculation(globalMatrixHArray, globalMatrixCArray, fileData.getSimulationStepTime(), matrixHBC2DGlobal);
            vectorPAfterCalculation = globalVectorPOperation(globalVectorPArray, globalMatrixCArray, fileData.getSimulationStepTime(), arrayOfTemperature);

            arrayOfTemperature = gaussMethod.GaussCalculation(fileData.getGridHeightNumberOfElements() * fileData.getGridWidthNumberOfElements(), matrixHAfterCalculation, vectorPAfterCalculation);

            for (int q = 0; q < arrayOfTemperature.length; q++) {
                grid.nodes[q].setTemperature(arrayOfTemperature[q]);
            }

            showNodesTemperature(arrayOfTemperature);

            showGlobalArrayVectorP(vectorPAfterCalculation);
            showGlobalArray(matrixHAfterCalculation);

            for (int w = 0; w < 16; w++) {
                for (int r = 0; r < 16; r++) {
                    globalMatrixHArray[w][r] = 0;
                    globalMatrixCArray[w][r] = 0;
                    matrixHAfterCalculation[w][r] = 0;
                    matrixHAfterCalculation[w][r] = 0;
                    matrixHBC2DGlobal[w][r] = 0;
                }
                globalVectorPArray[w] = 0;
                vectorPAfterCalculation[w] = 0;
            }


//  showGlobalArrayVectorP(vectorPAfterCalculation);
//        showGlobalArray(globalMatrixHArray);
//        showGlobalArray(globalMatrixCArray);

        }

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

    private static void showGlobalArray(double[][] globalArray) {
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
            System.out.println(array[i] + " ");
        }
    }

    private static double[] globalVectorPOperation(double[] globalVectorP, double[][] globalMatrixC, double dt, double[] temperature) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                globalMatrixC[i][j] /= dt;
                globalMatrixC[j][i] *= temperature[i];
            }
        }
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                globalVectorP[i] += globalMatrixC[i][j];
            }
        }

        return globalVectorP;
    }

    private static double[][] globalMatrixHCalculation(double[][] matrixH, double[][] matrixC, double dt, double[][] matrixHBC2D) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                matrixH[i][j] += matrixC[i][j] / dt + matrixHBC2D[i][j];
            }
        }
        return matrixH;
    }

    private static void showNodesTemperature(double[] temperature) {
        for (int j = 0; j < 16; j++) {
            System.out.print(temperature[j] + " ");
            if (j == 3 || j == 7 || j == 11) {
                System.out.println("");
            }
        }
        System.out.println("");
    }

}
