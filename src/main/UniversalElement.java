package main;

public class UniversalElement {
    private Point[] arrayOfIntegralPoints = {new Point((-1 / Math.sqrt(3)), (-1 / Math.sqrt(3))), new Point((1 / Math.sqrt(3)), (-1 / Math.sqrt(3))), new Point((1 / Math.sqrt(3)), (1 / Math.sqrt(3))), new Point((-1 / Math.sqrt(3)), (1 / Math.sqrt(3)))};
    private double[] ksiValueArray = {-0.577350269189626, -0.577350269189626, 0.577350269189626, 0.577350269189626};
    private double[] etaValueArray = {-0.577350269189626, 0.577350269189626, 0.577350269189626, -0.577350269189626};
    private Point[] surfaceArrayOfIntegralPoints = {new Point(-1 / Math.sqrt(3), - 1), new Point(1 / Math.sqrt(3), - 1), new Point(1, -1 / Math.sqrt(3)), new Point(1, 1 / Math.sqrt(3)), new Point(1 / Math.sqrt(3), 1),new Point(-1 / Math.sqrt(3), 1),  new Point(-1, 1 / Math.sqrt(3)), new Point(-1, -1 / Math.sqrt(3)) };

    public Point[] getSurfaceArrayOfIntegralPoints() {
        return surfaceArrayOfIntegralPoints;
    }

    private double[][] dnDksiValueArray = new double[4][4];
    private double[][] dnDetaValueArray = new double[4][4];

    //calculation dnDksi which will be used in Jacobian 2D
    public double[][] calculateDnDksiValue() {

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                switch (j) {
                    case 0:
                        dnDksiValueArray[i][j] = -0.25 * (1 - ksiValueArray[i]);
                        break;
                    case 1:
                        dnDksiValueArray[i][j] = 0.25 * (1 - ksiValueArray[i]);
                        break;
                    case 2:
                        dnDksiValueArray[i][j] = 0.25 * (1 + ksiValueArray[i]);
                        break;
                    case 3:
                        dnDksiValueArray[i][j] = -0.25 * (1 + ksiValueArray[i]);
                        break;
                }
            }
        }
        return dnDksiValueArray;
    }

    //calculation DnDEta which will be used in Jacobian2D
    public double [][] calculateDnEtaValue() {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                switch (j) {
                    case 0:
                        dnDetaValueArray[i][j] = -0.25 * (1 - etaValueArray[i]);
                        break;
                    case 1:
                        dnDetaValueArray[i][j] = -0.25 * (1 + etaValueArray[i]);
                        break;
                    case 2:
                        dnDetaValueArray[i][j] = 0.25 * (1 + etaValueArray[i]);
                        break;
                    case 3:
                        dnDetaValueArray[i][j] = 0.25 * (1 - etaValueArray[i]);
                        break;
                }
            }
        }
        return dnDetaValueArray;
    }


    public UniversalElement() {

    }

    public Point[] getArrayOfIntegralPoints() {
        return arrayOfIntegralPoints;
    }

    public double[] getKsiValueArray() {
        return ksiValueArray;
    }

    public double[] getEtaValueArray() {
        return etaValueArray;
    }

    public double[][] getDnDksiValueArray() {
        return dnDksiValueArray;
    }

    public double[][] getDnDetaValueArray() {
        return dnDetaValueArray;
    }

    public double getDnDksiValueArrayById(int i, int j) {
        return dnDksiValueArray[i][j];
    }

    public double getdnEtaValueArray(int i, int j) {
        return dnDetaValueArray[i][j];
    }

}
