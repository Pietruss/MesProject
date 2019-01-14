package main;

public class BuilderMatrixH {

    public double[] getDetJ() {
        return detJ;
    }

    public double[][] getBuildJ_1_1_1Array() {
        return buildJ_1_1_1Array;
    }

    private double[] detJ = new double[4];
    private double[][] buildJ_1_1_1Array = new double[4][4];
    private double[][] buildJ_1_1Array = new double[4][4];

    private double[][] dnDxValueArray = new double[4][4];
    private double[][] dnDyValueArray = new double[4][4];
    private double[][] dNdxdNdxValueArray1Point = new double[4][4];
    private double[][] dNdxdNdxValueArray2Point = new double[4][4];
    private double[][] dNdxdNdxValueArray3Point = new double[4][4];
    private double[][] dNdxdNdxValueArray4Point = new double[4][4];

    private double[][] dNdydNdyValueArray1Point = new double[4][4];
    private double[][] dNdydNdyValueArray2Point = new double[4][4];
    private double[][] dNdydNdyValueArray3Point = new double[4][4];
    private double[][] dNdydNdyValueArray4Point = new double[4][4];

    private double[][] dNdxdNdxValueArray1PointDet = new double[4][4];
    private double[][] dNdxdNdxValueArray2PointDet = new double[4][4];
    private double[][] dNdxdNdxValueArray3PointDet = new double[4][4];
    private double[][] dNdxdNdxValueArray4PointDet = new double[4][4];

    private double[][] dNdydNdyValueArray1PointDet = new double[4][4];
    private double[][] dNdydNdyValueArray2PointDet = new double[4][4];
    private double[][] dNdydNdyValueArray3PointDet = new double[4][4];
    private double[][] dNdydNdyValueArray4PointDet = new double[4][4];

    private double[][] dNdxdNdxTdNdxydNdyTDetK1Point = new double[4][4];
    private double[][] dNdxdNdxTdNdxydNdyTDetK2Point = new double[4][4];
    private double[][] dNdxdNdxTdNdxydNdyTDetK3Point = new double[4][4];
    private double[][] dNdxdNdxTdNdxydNdyTDetK4Point = new double[4][4];

    private double[][] matrixH = new double[4][4];


    public double[][] dnDxCalculation(UniversalElement universalElement, BuilderJacobian2D builderJacobian2D) {
        double[][] buildJ_1_1_1Array = builderJacobian2D.getBuildJ_1_1_1Array();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                switch (i) {
                    case 0:
                        dnDxValueArray[i][j] = buildJ_1_1_1Array[0][0] * universalElement.getDnDksiValueArrayById(i, j) + buildJ_1_1_1Array[1][0] * universalElement.getdnEtaValueArray(i, j);
                        break;
                    case 1:
                        dnDxValueArray[i][j] = buildJ_1_1_1Array[0][1] * universalElement.getDnDksiValueArrayById(i, j) + buildJ_1_1_1Array[1][1] * universalElement.getdnEtaValueArray(i, j);
                        break;
                    case 2:
                        dnDxValueArray[i][j] = buildJ_1_1_1Array[0][2] * universalElement.getDnDksiValueArrayById(i, j) + buildJ_1_1_1Array[1][2] * universalElement.getdnEtaValueArray(i, j);
                        break;
                    case 3:
                        dnDxValueArray[i][j] = buildJ_1_1_1Array[0][3] * universalElement.getDnDksiValueArrayById(i, j) + buildJ_1_1_1Array[1][3] * universalElement.getdnEtaValueArray(i, j);
                        break;
                }
            }
        }
        return dnDxValueArray;
    }

    public double[][] dnDyCalculation(UniversalElement universalElement, BuilderJacobian2D builderJacobian2D) {
        double[][] buildJ_1_1_1Array = builderJacobian2D.getBuildJ_1_1_1Array();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                switch (i) {
                    case 0:
                        dnDyValueArray[i][j] = buildJ_1_1_1Array[2][0] * universalElement.getDnDksiValueArrayById(i, j) + buildJ_1_1_1Array[3][0] * universalElement.getdnEtaValueArray(i, j);
                        break;
                    case 1:
                        dnDyValueArray[i][j] = buildJ_1_1_1Array[2][1] * universalElement.getDnDksiValueArrayById(i, j) + buildJ_1_1_1Array[3][1] * universalElement.getdnEtaValueArray(i, j);
                        break;
                    case 2:
                        dnDyValueArray[i][j] = buildJ_1_1_1Array[2][2] * universalElement.getDnDksiValueArrayById(i, j) + buildJ_1_1_1Array[3][2] * universalElement.getdnEtaValueArray(i, j);
                        break;
                    case 3:
                        dnDyValueArray[i][j] = buildJ_1_1_1Array[2][3] * universalElement.getDnDksiValueArrayById(i, j) + buildJ_1_1_1Array[3][3] * universalElement.getdnEtaValueArray(i, j);
                        break;
                }
            }
        }
        return dnDyValueArray;
    }

    public void dN_dx_dN_dx_dN_dy_dN_dyTDetCalculation(double [][] dnDxValueArray, double [][] dnDyValueArray, double [] detJ, double conductivity) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                switch (i) {
                    case 0:

                        dNdxdNdxValueArray1Point[i][j] = dnDxValueArray[0][0] * dnDxValueArray[0][j];
                        dNdxdNdxValueArray2Point[i][j] = dnDxValueArray[1][0] * dnDxValueArray[1][j];
                        dNdxdNdxValueArray3Point[i][j] = dnDxValueArray[2][0] * dnDxValueArray[2][j];
                        dNdxdNdxValueArray4Point[i][j] = dnDxValueArray[3][0] * dnDxValueArray[3][j];

                        dNdydNdyValueArray1Point[i][j] = dnDyValueArray[0][0] * dnDyValueArray[0][j];
                        dNdydNdyValueArray2Point[i][j] = dnDyValueArray[1][0] * dnDyValueArray[1][j];
                        dNdydNdyValueArray3Point[i][j] = dnDyValueArray[2][0] * dnDyValueArray[2][j];
                        dNdydNdyValueArray4Point[i][j] = dnDyValueArray[3][0] * dnDyValueArray[3][j];

                        dNdxdNdxValueArray1PointDet[i][j] = dNdxdNdxValueArray1Point[0][j] * detJ[0];
                        dNdxdNdxValueArray2PointDet[i][j] = dNdxdNdxValueArray2Point[0][j] * detJ[1];
                        dNdxdNdxValueArray3PointDet[i][j] = dNdxdNdxValueArray3Point[0][j] * detJ[2];
                        dNdxdNdxValueArray4PointDet[i][j] = dNdxdNdxValueArray4Point[0][j] * detJ[3];

                        dNdydNdyValueArray1PointDet[i][j] = dNdydNdyValueArray1Point[0][j] * detJ[0];
                        dNdydNdyValueArray2PointDet[i][j] = dNdydNdyValueArray2Point[0][j] * detJ[1];
                        dNdydNdyValueArray3PointDet[i][j] = dNdydNdyValueArray3Point[0][j] * detJ[2];
                        dNdydNdyValueArray4PointDet[i][j] = dNdydNdyValueArray4Point[0][j] * detJ[3];

                        dNdxdNdxTdNdxydNdyTDetK1Point[i][j] = conductivity * (dNdxdNdxValueArray1PointDet[i][j] + dNdydNdyValueArray1PointDet[i][j]);
                        dNdxdNdxTdNdxydNdyTDetK2Point[i][j] = conductivity * (dNdxdNdxValueArray2PointDet[i][j] + dNdydNdyValueArray2PointDet[i][j]);
                        dNdxdNdxTdNdxydNdyTDetK3Point[i][j] = conductivity * (dNdxdNdxValueArray3PointDet[i][j] + dNdydNdyValueArray3PointDet[i][j]);
                        dNdxdNdxTdNdxydNdyTDetK4Point[i][j] = conductivity * (dNdxdNdxValueArray4PointDet[i][j] + dNdydNdyValueArray4PointDet[i][j]);
                        break;

                    case 1:

                        dNdxdNdxValueArray1Point[i][j] = dnDxValueArray[0][1] * dnDxValueArray[0][j];
                        dNdxdNdxValueArray2Point[i][j] = dnDxValueArray[1][1] * dnDxValueArray[1][j];
                        dNdxdNdxValueArray3Point[i][j] = dnDxValueArray[2][1] * dnDxValueArray[2][j];
                        dNdxdNdxValueArray4Point[i][j] = dnDxValueArray[3][1] * dnDxValueArray[3][j];

                        dNdydNdyValueArray1Point[i][j] = dnDyValueArray[0][1] * dnDyValueArray[0][j];
                        dNdydNdyValueArray2Point[i][j] = dnDyValueArray[1][1] * dnDyValueArray[1][j];
                        dNdydNdyValueArray3Point[i][j] = dnDyValueArray[2][1] * dnDyValueArray[2][j];
                        dNdydNdyValueArray4Point[i][j] = dnDyValueArray[3][1] * dnDyValueArray[3][j];

                        dNdxdNdxValueArray1PointDet[i][j] = dNdxdNdxValueArray1Point[1][j] * detJ[0];
                        dNdxdNdxValueArray2PointDet[i][j] = dNdxdNdxValueArray2Point[1][j] * detJ[1];
                        dNdxdNdxValueArray3PointDet[i][j] = dNdxdNdxValueArray3Point[1][j] * detJ[2];
                        dNdxdNdxValueArray4PointDet[i][j] = dNdxdNdxValueArray4Point[1][j] * detJ[3];

                        dNdydNdyValueArray1PointDet[i][j] = dNdydNdyValueArray1Point[1][j] * detJ[0];
                        dNdydNdyValueArray2PointDet[i][j] = dNdydNdyValueArray2Point[1][j] * detJ[1];
                        dNdydNdyValueArray3PointDet[i][j] = dNdydNdyValueArray3Point[1][j] * detJ[2];
                        dNdydNdyValueArray4PointDet[i][j] = dNdydNdyValueArray4Point[1][j] * detJ[3];

                        dNdxdNdxTdNdxydNdyTDetK1Point[i][j] = conductivity * (dNdxdNdxValueArray1PointDet[i][j] + dNdydNdyValueArray1PointDet[i][j]);
                        dNdxdNdxTdNdxydNdyTDetK2Point[i][j] = conductivity * (dNdxdNdxValueArray2PointDet[i][j] + dNdydNdyValueArray2PointDet[i][j]);
                        dNdxdNdxTdNdxydNdyTDetK3Point[i][j] = conductivity * (dNdxdNdxValueArray3PointDet[i][j] + dNdydNdyValueArray3PointDet[i][j]);
                        dNdxdNdxTdNdxydNdyTDetK4Point[i][j] = conductivity * (dNdxdNdxValueArray4PointDet[i][j] + dNdydNdyValueArray4PointDet[i][j]);
                        break;

                    case 2:

                        dNdxdNdxValueArray1Point[i][j] = dnDxValueArray[0][2] * dnDxValueArray[0][j];
                        dNdxdNdxValueArray2Point[i][j] = dnDxValueArray[1][2] * dnDxValueArray[1][j];
                        dNdxdNdxValueArray3Point[i][j] = dnDxValueArray[2][2] * dnDxValueArray[2][j];
                        dNdxdNdxValueArray4Point[i][j] = dnDxValueArray[3][2] * dnDxValueArray[3][j];

                        dNdydNdyValueArray1Point[i][j] = dnDyValueArray[0][2] * dnDyValueArray[0][j];
                        dNdydNdyValueArray2Point[i][j] = dnDyValueArray[1][2] * dnDyValueArray[1][j];
                        dNdydNdyValueArray3Point[i][j] = dnDyValueArray[2][2] * dnDyValueArray[2][j];
                        dNdydNdyValueArray4Point[i][j] = dnDyValueArray[3][2] * dnDyValueArray[3][j];

                        dNdxdNdxValueArray1PointDet[i][j] = dNdxdNdxValueArray1Point[2][j] * detJ[i];
                        dNdxdNdxValueArray2PointDet[i][j] = dNdxdNdxValueArray2Point[2][j] * detJ[1];
                        dNdxdNdxValueArray3PointDet[i][j] = dNdxdNdxValueArray3Point[2][j] * detJ[2];
                        dNdxdNdxValueArray4PointDet[i][j] = dNdxdNdxValueArray4Point[2][j] * detJ[3];

                        dNdydNdyValueArray1PointDet[i][j] = dNdydNdyValueArray1Point[2][j] * detJ[0];
                        dNdydNdyValueArray2PointDet[i][j] = dNdydNdyValueArray2Point[2][j] * detJ[1];
                        dNdydNdyValueArray3PointDet[i][j] = dNdydNdyValueArray3Point[2][j] * detJ[2];
                        dNdydNdyValueArray4PointDet[i][j] = dNdydNdyValueArray4Point[2][j] * detJ[3];

                        dNdxdNdxTdNdxydNdyTDetK1Point[i][j] = conductivity * (dNdxdNdxValueArray1PointDet[i][j] + dNdydNdyValueArray1PointDet[i][j]);
                        dNdxdNdxTdNdxydNdyTDetK2Point[i][j] = conductivity * (dNdxdNdxValueArray2PointDet[i][j] + dNdydNdyValueArray2PointDet[i][j]);
                        dNdxdNdxTdNdxydNdyTDetK3Point[i][j] = conductivity * (dNdxdNdxValueArray3PointDet[i][j] + dNdydNdyValueArray3PointDet[i][j]);
                        dNdxdNdxTdNdxydNdyTDetK4Point[i][j] = conductivity * (dNdxdNdxValueArray4PointDet[i][j] + dNdydNdyValueArray4PointDet[i][j]);

                        break;

                    case 3:

                        dNdxdNdxValueArray1Point[i][j] = dnDxValueArray[0][3] * dnDxValueArray[0][j];
                        dNdxdNdxValueArray2Point[i][j] = dnDxValueArray[1][3] * dnDxValueArray[1][j];
                        dNdxdNdxValueArray3Point[i][j] = dnDxValueArray[2][3] * dnDxValueArray[2][j];
                        dNdxdNdxValueArray4Point[i][j] = dnDxValueArray[3][3] * dnDxValueArray[3][j];

                        dNdydNdyValueArray1Point[i][j] = dnDyValueArray[0][3] * dnDyValueArray[0][j];
                        dNdydNdyValueArray2Point[i][j] = dnDyValueArray[1][3] * dnDyValueArray[1][j];
                        dNdydNdyValueArray3Point[i][j] = dnDyValueArray[2][3] * dnDyValueArray[2][j];
                        dNdydNdyValueArray4Point[i][j] = dnDyValueArray[3][3] * dnDyValueArray[3][j];

                        dNdxdNdxValueArray1PointDet[i][j] = dNdxdNdxValueArray1Point[3][j] * detJ[i];
                        dNdxdNdxValueArray2PointDet[i][j] = dNdxdNdxValueArray2Point[3][j] * detJ[1];
                        dNdxdNdxValueArray3PointDet[i][j] = dNdxdNdxValueArray3Point[3][j] * detJ[2];
                        dNdxdNdxValueArray4PointDet[i][j] = dNdxdNdxValueArray4Point[3][j] * detJ[3];

                        dNdydNdyValueArray1PointDet[i][j] = dNdydNdyValueArray1Point[3][j] * detJ[0];
                        dNdydNdyValueArray2PointDet[i][j] = dNdydNdyValueArray2Point[3][j] * detJ[1];
                        dNdydNdyValueArray3PointDet[i][j] = dNdydNdyValueArray3Point[3][j] * detJ[2];
                        dNdydNdyValueArray4PointDet[i][j] = dNdydNdyValueArray4Point[3][j] * detJ[3];

                        dNdxdNdxTdNdxydNdyTDetK1Point[i][j] = conductivity * (dNdxdNdxValueArray1PointDet[i][j] + dNdydNdyValueArray1PointDet[i][j]);
                        dNdxdNdxTdNdxydNdyTDetK2Point[i][j] = conductivity * (dNdxdNdxValueArray2PointDet[i][j] + dNdydNdyValueArray2PointDet[i][j]);
                        dNdxdNdxTdNdxydNdyTDetK3Point[i][j] = conductivity * (dNdxdNdxValueArray3PointDet[i][j] + dNdydNdyValueArray3PointDet[i][j]);
                        dNdxdNdxTdNdxydNdyTDetK4Point[i][j] = conductivity * (dNdxdNdxValueArray4PointDet[i][j] + dNdydNdyValueArray4PointDet[i][j]);

                        break;
                }
            }
        }
    }

    public double[][] matrixHCalculation() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrixH[i][j] = dNdxdNdxTdNdxydNdyTDetK1Point[i][j] + dNdxdNdxTdNdxydNdyTDetK2Point[i][j] + dNdxdNdxTdNdxydNdyTDetK3Point[i][j] + dNdxdNdxTdNdxydNdyTDetK4Point[i][j];
            }
        }
        return matrixH;
    }
}
