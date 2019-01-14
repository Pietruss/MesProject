package main;

import java.text.DecimalFormat;

public class GlobalOperations {

    protected double[][] arrayToGlobal(int[] localId, int[] globalId, double[][] localArray, double[][] globalArray) {
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

    protected double[] vectorPToGlobal(int[] localId, int[] globalId, double[] localArray, double[] globalArray) {
        for (int i = 0; i < 4; i++) {
            int localx = localId[i];
            int globalx = globalId[localx - 1];

            globalArray[globalx - 1] += localArray[i];

        }

        return globalArray;
    }

    protected double[] globalVectorPOperation(double[] globalVectorP, double[][] globalMatrixC, double dt, Grid grid) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                globalMatrixC[i][j] /= dt;
                globalMatrixC[j][i] *= grid.nodes[i].getTemperature();
            }
        }
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                globalVectorP[i] += globalMatrixC[i][j];
            }
        }

        return globalVectorP;
    }

    protected double[][] globalMatrixHCalculation(double[][] matrixH, double[][] matrixC, double dt, double[][] matrixHBC2D) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                matrixH[i][j] += matrixC[i][j] / dt + matrixHBC2D[i][j];
            }
        }
        return matrixH;
    }

    protected void showGlobalArray(double[][] globalArray) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(4);
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(df.format(globalArray[i][j]) + " ");
            }
            System.out.println("");
        }
    }

    protected double[][] copyArray(double[][] array) {
        double[][] newArray = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newArray[i][j] = array[i][j];
            }
        }
        return newArray;
    }

    protected void showGlobalArrayVectorP(double[] array) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(3);
        System.out.println("ShowGlobalArrayVectorPMethod: ");
        for(double i: array){
            System.out.print(df.format(i));
            System.out.print(" ");
        }
        System.out.println("\n");
    }

    protected void showNodesTemperature(double[] temperature) {
        System.out.println("ShowNodesTemperatureMethod: ");
        for (int j = 0; j < 16; j++) {
            System.out.print(temperature[j] + " ");
            if (j == 3 || j == 7 || j == 11) {
                System.out.println("");
            }
        }
        System.out.println("\n");
    }


}
