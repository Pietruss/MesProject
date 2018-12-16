package main;

public class BuilderJacobian2D {
    public double[][] getIntegralPoints() {
        return integralPoints;
    }

    public double[] getDetJ() {
        return detJ;
    }

    public double[][] getBuildJ_1_1_1Array() {
        return buildJ_1_1_1Array;
    }

    private double[][] integralPoints = new double[4][4];
    private double[] detJ = new double[4];
    private double[][] buildJ_1_1_1Array = new double[4][4];

    public BuilderJacobian2D() {

    }

    public double[][] integralPointsCalculation(double[][] dnDksiArray, double[][] dnDetaArray, Element element) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                switch (i) {
                    case 0:
                        integralPoints[i][j] = dnDksiArray[0][0] * element.nodes[0].getxCoordinate() + dnDksiArray[0][1] * element.nodes[1].getxCoordinate() + dnDksiArray[0][2] * element.nodes[2].getxCoordinate() + dnDksiArray[0][3] * element.nodes[3].getxCoordinate();
                        break;
                    case 1:
                        integralPoints[i][j] = dnDksiArray[0][0] * element.nodes[0].getyCoordinate() + dnDksiArray[0][1] * element.nodes[1].getyCoordinate() + dnDksiArray[0][2] * element.nodes[2].getyCoordinate() + dnDksiArray[0][3] * element.nodes[3].getyCoordinate();
                        if (integralPoints[i][j] < 0) {
                        integralPoints[i][j] = 0;
                    }
                        break;
                    case 2:
                        integralPoints[i][j] = dnDetaArray[0][0] * element.nodes[0].getxCoordinate() + dnDetaArray[0][1] * element.nodes[1].getxCoordinate() + dnDetaArray[0][2] * element.nodes[2].getxCoordinate() + dnDetaArray[0][3] * element.nodes[3].getxCoordinate();
                        if (integralPoints[i][j] < 0) {
                            integralPoints[i][j] = 0;
                        }
                        break;
                    case 3:
                        integralPoints[i][j] = dnDetaArray[0][0] * element.nodes[0].getyCoordinate() + dnDetaArray[0][1] * element.nodes[1].getyCoordinate() + dnDetaArray[0][2] * element.nodes[2].getyCoordinate() + dnDetaArray[0][3] * element.nodes[3].getyCoordinate();
                        break;
                }
            }
        }
        return integralPoints;
    }

    public double[] detJCalculation(double[][] integralPoints) {
        for (int i = 0; i < 4; i++) {
            detJ[i] = integralPoints[0][i] * integralPoints[3][i] - integralPoints[i][1] * integralPoints[2][i];
        }
        return detJ;
    }

    public double[][] buildJ_1_1_1Calculation(double[][] integralPoints, double[] detJ) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                switch (i) {
                    case 0:
                        buildJ_1_1_1Array[i][j] = integralPoints[3][i] / detJ[i];
                        break;
                    case 1:
                        buildJ_1_1_1Array[i][j] = integralPoints[1][i] / detJ[i];
                        break;
                    case 2:
                        buildJ_1_1_1Array[i][j] = integralPoints[2][i] / detJ[i];
                        break;
                    case 3:
                        buildJ_1_1_1Array[i][j] = integralPoints[0][i] / detJ[i];
                        break;
                }
            }
        }
        return buildJ_1_1_1Array;
    }


    public void showArray2D(double[][] array) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public void showArray1D(double[] array) {
        for (int i = 0; i < 4; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("");
    }
}
