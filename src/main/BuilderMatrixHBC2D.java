package main;

public class BuilderMatrixHBC2D {
    private float convection = 300;
    private AreaArray[] areaArray;

    public void setDetJ(double[] detJ) {
        this.detJ = detJ;
    }

    public double[] getDetJ() {
        return detJ;
    }

    private double[] detJ = new double[4];;
    private double[][] area1sum = new double[4][4];
    private double[][] matrixH = new double[4][4];


    public BuilderMatrixHBC2D(AreaArray[] areaArray) {
        this.areaArray = areaArray;
    }

    public double[] lengthSideCalculation(Element element) {
        double[] lengthSideArray = new double[4];
        for (int i = 0; i < 4; i++) {
            lengthSideArray[i] = Math.sqrt(Math.pow(element.nodes[1].getxCoordinate() - element.nodes[0].getxCoordinate(), 2) + Math.pow(element.nodes[1].getyCoordinate() - element.nodes[0].getyCoordinate(), 2));
        }
        return lengthSideArray;
    }


    public double [][] matrixHCalculation(AreaArray areaArray, double[][] areaPc1, double[][] areaPc2, double[][] areaPc3, double[][] areaPc4) {
        System.out.println("");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrixH[i][j] = areaPc1[i][j] * areaArray.area[0] + areaPc2[i][j] * areaArray.area[1] + areaPc3[i][j] * areaArray.area[2] + areaPc4[i][j] * areaArray.area[3];
            }
        }
        return matrixH;
    }

    double[][] copyArray(double[][] oldArray) {
        double[][] newArray = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newArray[i][j] = oldArray[i][j];
            }
        }
        return newArray;
    }


    public double[] detJCalculations(double[] lengthSideArray) {
        for (int i = 0; i < 4; i++) {
            detJ[i] = lengthSideArray[i] / 2;
        }
        return detJ;
    }

    public double[] N1N2N3N4Calculations(Point point1) {
        double[] N1N2N3N4ArrayValueP1 = new double[4];
        N1N2N3N4ArrayValueP1[0] = 0.25 * (1 - point1.getX()) * (1 - point1.getY());
        N1N2N3N4ArrayValueP1[1] = 0.25 * (1 + point1.getX()) * (1 - point1.getY());
        N1N2N3N4ArrayValueP1[2] = 0.25 * (1 + point1.getX()) * (1 + point1.getY());
        N1N2N3N4ArrayValueP1[3] = 0.25 * (1 - point1.getX()) * (1 + point1.getY());

        VectorP vectorP1 = new VectorP();
        VectorP vectorP2 = new VectorP();
        vectorP1.vectorPCalculation(0.03333, 100, point1, 300);

        return N1N2N3N4ArrayValueP1;
    }

    public double[][] areaCalculations(double[] areaPoint1, double[] areaPoint2, int detJIndex, double [] detJ) {
        double[][] area1pc1 = new double[4][4];
        double[][] area1pc2 = new double[4][4];



        for (int i = 0; i < 4; i++) {

            switch (i) {
                case 0:
                    area1pc1[i][0] = areaPoint1[0] * areaPoint1[0] * convection;
                    area1pc1[i][1] = areaPoint1[0] * areaPoint1[1] * convection;
                    area1pc1[i][2] = areaPoint1[0] * areaPoint1[2] * convection;
                    area1pc1[i][3] = areaPoint1[0] * areaPoint1[3] * convection;

                    area1pc2[i][0] = areaPoint2[0] * areaPoint2[0] * convection;
                    area1pc2[i][1] = areaPoint2[0] * areaPoint2[1] * convection;
                    area1pc2[i][2] = areaPoint2[0] * areaPoint2[2] * convection;
                    area1pc2[i][3] = areaPoint2[0] * areaPoint2[3] * convection;

                    break;

                case 1:
                    area1pc1[i][0] = areaPoint1[1] * areaPoint1[0] * convection;
                    area1pc1[i][1] = areaPoint1[1] * areaPoint1[1] * convection;
                    area1pc1[i][2] = areaPoint1[1] * areaPoint1[2] * convection;
                    area1pc1[i][3] = areaPoint1[1] * areaPoint1[3] * convection;

                    area1pc2[i][0] = areaPoint2[1] * areaPoint2[0] * convection;
                    area1pc2[i][1] = areaPoint2[1] * areaPoint2[1] * convection;
                    area1pc2[i][2] = areaPoint2[1] * areaPoint2[2] * convection;
                    area1pc2[i][3] = areaPoint2[1] * areaPoint2[3] * convection;

                    break;

                case 2:
                    area1pc1[i][0] = areaPoint1[2] * areaPoint1[0] * convection;
                    area1pc1[i][1] = areaPoint1[2] * areaPoint1[1] * convection;
                    area1pc1[i][2] = areaPoint1[2] * areaPoint1[2] * convection;
                    area1pc1[i][3] = areaPoint1[2] * areaPoint1[3] * convection;

                    area1pc2[i][0] = areaPoint2[2] * areaPoint2[0] * convection;
                    area1pc2[i][1] = areaPoint2[2] * areaPoint2[1] * convection;
                    area1pc2[i][2] = areaPoint2[2] * areaPoint2[2] * convection;
                    area1pc2[i][3] = areaPoint2[2] * areaPoint2[3] * convection;

                    break;

                case 3:
                    area1pc1[i][0] = areaPoint1[3] * areaPoint1[0] * convection;
                    area1pc1[i][1] = areaPoint1[3] * areaPoint1[1] * convection;
                    area1pc1[i][2] = areaPoint1[3] * areaPoint1[2] * convection;
                    area1pc1[i][3] = areaPoint1[3] * areaPoint1[3] * convection;

                    area1pc2[i][0] = areaPoint2[3] * areaPoint2[0] * convection;
                    area1pc2[i][1] = areaPoint2[3] * areaPoint2[1] * convection;
                    area1pc2[i][2] = areaPoint2[3] * areaPoint2[2] * convection;
                    area1pc2[i][3] = areaPoint2[3] * areaPoint2[3] * convection;
                    break;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                area1sum[i][j] = (area1pc1[i][j] + area1pc2[i][j]) * detJ[detJIndex];
            }
        }
        return area1sum;
    }


}
