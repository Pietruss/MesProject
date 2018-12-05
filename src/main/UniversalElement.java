package main;

public class UniversalElement {
    private Point[] arrayOfIntegralPoints = {new Point((-1 / Math.sqrt(3)), (-1 / Math.sqrt(3))), new Point((1 / Math.sqrt(3)), (-1 / Math.sqrt(3))), new Point((1 / Math.sqrt(3)), (1 / Math.sqrt(3))), new Point((-1 / Math.sqrt(3)), (1 / Math.sqrt(3)))};
    private double[] ksiValueArray = {-0.577350269189626, -0.577350269189626, 0.577350269189626, 0.577350269189626};
    private double[] etaValueArray = {-0.577350269189626, 0.577350269189626, 0.577350269189626, -0.577350269189626};


    private double[][] dnDksiValueArray = new double[4][4];
    private double[][] dnDetaValueArray = new double[4][4];


    public void calculateDn_Dksi_Dn_Eta_Value() {

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                switch (j) {
                    case 0:
                        dnDksiValueArray[i][j] = -0.25 * (1 - ksiValueArray[i]);
                        dnDetaValueArray[i][j] = -0.25 * (1 - etaValueArray[i]);
                        break;
                    case 1:
                        dnDksiValueArray[i][j] = 0.25 * (1 - ksiValueArray[i]);
                        dnDetaValueArray[i][j] = -0.25 * (1 + etaValueArray[i]);
                        break;
                    case 2:
                        dnDksiValueArray[i][j] = 0.25 * (1 + ksiValueArray[i]);
                        dnDetaValueArray[i][j] = 0.25 * (1 + etaValueArray[i]);
                        break;
                    case 3:
                        dnDksiValueArray[i][j] = -0.25 * (1 + ksiValueArray[i]);
                        dnDetaValueArray[i][j] = 0.25 * (1 - etaValueArray[i]);
                        break;
                }
//                System.out.println(dnDksiValueArray[i][j]);
//                System.out.println(dnDetaValueArray[i][j]);
            }
//            System.out.println();
        }
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
