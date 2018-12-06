package main;

public class BuilderMatrixHBC2D {
    private float convection = 25.0f;
    private double[] area = {1, 1, 0, 0};
    private double[] lengthSide = {10, 8, 10, 8};
    private double[] detJ = new double[4];
    private double[][] area1 = new double[2][6];
    private double[][] area1pc2 = new double[4][4];
    private double[][] area1sum = new double[4][4];

    private double[][] area2 = new double[2][6];
    private double[][] area3 = new double[2][6];
    private double[][] area4 = new double[2][6];
    private double[][] matrixH = new double[4][4];

    private double[][] returnArea1 = new double[4][4];
    private double[][] returnArea2 = new double[4][4];
    private double[][] returnArea3 = new double[4][4];
    private double[][] returnArea4 = new double[4][4];

    private void printArray(double [][] array){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println("");
        }
    }


    public void setArea(double[] area) {
        this.area = area;
    }

    void matrixHCalculation(double[] area, double[][] areaPc1, double[][] areaPc2, double[][] areaPc3, double[][] areaPc4) {
        System.out.println("");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrixH[i][j] = areaPc1[i][j] * area[0] + areaPc2[i][j] * area[1] + areaPc3[i][j] * area[2] + areaPc4[i][j] * area[3];
                System.out.print(matrixH[i][j] + " ");
            }
            System.out.println("");
        }
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


    void detJCalculations() {
        for (int i = 0; i < 4; i++) {
            detJ[i] = lengthSide[i] / 2;
        }
    }

    void areaArrayInit() {
        area1[0][0] = -0.5773502692;
        area1[0][1] = -1;
        area1[0][2] = 0.7886751346;
        area1[0][3] = 0.2113248654;
        area1[0][4] = 0;
        area1[0][5] = 0;

        area1[1][0] = 0.5773502692;
        area1[1][1] = -1;
        area1[1][2] = 0.2113248654;
        area1[1][3] = 0.7886751346;
        area1[1][4] = 0;
        area1[1][5] = 0;

        area2[0][0] = 1;
        area2[0][1] = -0.5773502692;
        area2[0][2] = 0;
        area2[0][3] = 0.7886751346;
        area2[0][4] = 0.2113248654;
        area2[0][5] = 0;

        area2[1][0] = 1;
        area2[1][1] = 0.5773502692;
        area2[1][2] = 0;
        area2[1][3] = 0.2113248654;
        area2[1][4] = 0.7886751346;
        area2[1][5] = 0;


        area3[0][0] = 0.5773502692;
        area3[0][1] = 1;
        area3[0][2] = 0;
        area3[0][3] = 0;
        area3[0][4] = 0.7886751346;
        area3[0][5] = 0.2113248654;

        area3[1][0] = 0.5773502692;
        area3[1][1] = 1;
        area3[1][2] = 0;
        area3[1][3] = 0;
        area3[1][4] = 0.2113248654;
        area3[1][5] = 0.7886751346;


        area4[0][0] = -1;
        area4[0][1] = 0.5773502692;
        area4[0][2] = 0.2113248654;
        area4[0][3] = 0;
        area4[0][4] = 0;
        area4[0][5] = 0.7886751346;

        area4[1][0] = -1;
        area4[1][1] = -0.5773502692;
        area4[1][2] = 0.7886751346;
        area4[1][3] = 0;
        area4[1][4] = 0;
        area4[1][5] = 0.2113248654;


        returnArea1 = copyArray(areaCalculations(area1, 0));
        printArray(returnArea1);
        returnArea2 = copyArray(areaCalculations(area2, 1));
        printArray(returnArea2);
        returnArea3 = copyArray(areaCalculations(area3, 2));
        printArray(returnArea3);
        returnArea4 = copyArray(areaCalculations(area4, 3));
        printArray(returnArea4);

        matrixHCalculation(area, returnArea1, returnArea2, returnArea3, returnArea4);


    }

    private double[][] areaCalculations(double[][] area1, int detJIndex) {
        double[][] area1pc1 = new double[4][4];

        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    area1pc1[i][0] = area1[0][2] * area1[0][2] * convection;
                    area1pc1[i][1] = area1[0][2] * area1[0][3] * convection;
                    area1pc1[i][2] = area1[0][2] * area1[0][4] * convection;
                    area1pc1[i][3] = area1[0][2] * area1[0][5] * convection;

                    area1pc2[i][0] = area1[1][2] * area1[1][2] * convection;
                    area1pc2[i][1] = area1[1][2] * area1[1][3] * convection;
                    area1pc2[i][2] = area1[1][2] * area1[1][4] * convection;
                    area1pc2[i][3] = area1[1][2] * area1[1][5] * convection;

                    break;

                case 1:
                    area1pc1[i][0] = area1[0][3] * area1[0][2] * convection;
                    area1pc1[i][1] = area1[0][3] * area1[0][3] * convection;
                    area1pc1[i][2] = area1[0][3] * area1[0][4] * convection;
                    area1pc1[i][3] = area1[0][3] * area1[0][5] * convection;

                    area1pc2[i][0] = area1[1][3] * area1[1][2] * convection;
                    area1pc2[i][1] = area1[1][3] * area1[1][3] * convection;
                    area1pc2[i][2] = area1[1][3] * area1[1][4] * convection;
                    area1pc2[i][3] = area1[1][3] * area1[1][5] * convection;

                    break;

                case 2:
                    area1pc1[i][0] = area1[0][4] * area1[0][2] * convection;
                    area1pc1[i][1] = area1[0][4] * area1[0][3] * convection;
                    area1pc1[i][2] = area1[0][4] * area1[0][4] * convection;
                    area1pc1[i][3] = area1[0][4] * area1[0][5] * convection;

                    area1pc2[i][0] = area1[1][4] * area1[1][2] * convection;
                    area1pc2[i][1] = area1[1][4] * area1[1][3] * convection;
                    area1pc2[i][2] = area1[1][4] * area1[1][4] * convection;
                    area1pc2[i][3] = area1[1][4] * area1[1][5] * convection;

                    break;

                case 3:
                    area1pc1[i][0] = area1[0][5] * area1[0][2] * convection;
                    area1pc1[i][1] = area1[0][5] * area1[0][3] * convection;
                    area1pc1[i][2] = area1[0][5] * area1[0][4] * convection;
                    area1pc1[i][3] = area1[0][5] * area1[0][5] * convection;

                    area1pc2[i][0] = area1[1][5] * area1[1][2] * convection;
                    area1pc2[i][1] = area1[1][5] * area1[1][3] * convection;
                    area1pc2[i][2] = area1[1][5] * area1[1][4] * convection;
                    area1pc2[i][3] = area1[1][5] * area1[1][5] * convection;

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
