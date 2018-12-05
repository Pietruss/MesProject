package main;

public class BuilderMatrixC {
    private double conductivity = 700;
    private double density = 7800;
    private double[] detJ;
    private double[][] N1N2N3N4 = new double[4][4];
    private double[] ksiValueArray = {-0.577350269189626, 0.577350269189626, 0.577350269189626, -0.577350269189626};
    private double[] etaValueArray = {-0.577350269189626, -0.577350269189626, 0.577350269189626, 0.577350269189626};
    private double[][] pc1 = new double[4][4];
    private double[][] pc2 = new double[4][4];
    private double[][] pc3 = new double[4][4];
    private double[][] pc4 = new double[4][4];
    private double[][] matrixC = new double[4][4];

    void N1N2N3N4Calculations() {
        System.out.println("------------");
        for (int i = 0; i < 4; i++) {
            System.out.println("");
            switch (i) {
                case 0:
                    N1N2N3N4[i][0] = 0.25 * (1 - ksiValueArray[0]) * (1 - etaValueArray[0]);
                    System.out.print(N1N2N3N4[i][0] + " ");
                    N1N2N3N4[i][1] = 0.25 * (1 + ksiValueArray[0]) * (1 - etaValueArray[0]);
                    System.out.print(N1N2N3N4[i][1] + " ");
                    N1N2N3N4[i][2] = 0.25 * (1 + ksiValueArray[0]) * (1 + etaValueArray[0]);
                    System.out.print(N1N2N3N4[i][2] + " ");
                    N1N2N3N4[i][3] = 0.25 * (1 - ksiValueArray[0]) * (1 + etaValueArray[0]);
                    System.out.print(N1N2N3N4[i][3] + " ");
                    break;
                case 1:
                    N1N2N3N4[i][0] = 0.25 * (1 - ksiValueArray[1]) * (1 - etaValueArray[1]);
                    System.out.print(N1N2N3N4[i][0] + " ");
                    N1N2N3N4[i][1] = 0.25 * (1 + ksiValueArray[1]) * (1 - etaValueArray[1]);
                    System.out.print(N1N2N3N4[i][1] + " ");
                    N1N2N3N4[i][2] = 0.25 * (1 + ksiValueArray[1]) * (1 + etaValueArray[1]);
                    System.out.print(N1N2N3N4[i][2] + " ");
                    N1N2N3N4[i][3] = 0.25 * (1 - ksiValueArray[1]) * (1 + etaValueArray[1]);
                    System.out.print(N1N2N3N4[i][3] + " ");
                    break;
                case 2:
                    N1N2N3N4[i][0] = 0.25 * (1 - ksiValueArray[2]) * (1 - etaValueArray[2]);
                    System.out.print(N1N2N3N4[i][0] + " ");
                    N1N2N3N4[i][1] = 0.25 * (1 + ksiValueArray[2]) * (1 - etaValueArray[2]);
                    System.out.print(N1N2N3N4[i][1] + " ");
                    N1N2N3N4[i][2] = 0.25 * (1 + ksiValueArray[2]) * (1 + etaValueArray[2]);
                    System.out.print(N1N2N3N4[i][2] + " ");
                    N1N2N3N4[i][3] = 0.25 * (1 - ksiValueArray[2]) * (1 + etaValueArray[2]);
                    System.out.print(N1N2N3N4[i][3] + " ");
                    break;
                case 3:
                    N1N2N3N4[i][0] = 0.25 * (1 - ksiValueArray[3]) * (1 - etaValueArray[3]);
                    System.out.print(N1N2N3N4[i][0] + " ");
                    N1N2N3N4[i][1] = 0.25 * (1 + ksiValueArray[3]) * (1 - etaValueArray[3]);
                    System.out.print(N1N2N3N4[i][1] + " ");
                    N1N2N3N4[i][2] = 0.25 * (1 + ksiValueArray[3]) * (1 + etaValueArray[3]);
                    System.out.print(N1N2N3N4[i][2] + " ");
                    N1N2N3N4[i][3] = 0.25 * (1 - ksiValueArray[3]) * (1 + etaValueArray[3]);
                    System.out.print(N1N2N3N4[i][3] + " ");
                    break;
            }
        }
    }

    void pcNumberCalculations(BuilderMatrixH builderMatrixH) {
        detJ = builderMatrixH.getDetJ();
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    pc1[i][0] = N1N2N3N4[i][0] * N1N2N3N4[i][0] * detJ[i] * conductivity * density;
                    pc1[i][1] = N1N2N3N4[i][0] * N1N2N3N4[i][1] * detJ[i] * conductivity * density;
                    pc1[i][2] = N1N2N3N4[i][0] * N1N2N3N4[i][2] * detJ[i] * conductivity * density;
                    pc1[i][3] = N1N2N3N4[i][0] * N1N2N3N4[i][3] * detJ[i] * conductivity * density;

                    pc2[i][0] = N1N2N3N4[1][0] * N1N2N3N4[1][0] * detJ[i] * conductivity * density;
                    pc2[i][1] = N1N2N3N4[1][0] * N1N2N3N4[1][1] * detJ[i] * conductivity * density;
                    pc2[i][2] = N1N2N3N4[1][0] * N1N2N3N4[1][2] * detJ[i] * conductivity * density;
                    pc2[i][3] = N1N2N3N4[1][0] * N1N2N3N4[1][3] * detJ[i] * conductivity * density;

                    pc3[i][0] = N1N2N3N4[2][0] * N1N2N3N4[2][0] * detJ[i] * conductivity * density;
                    pc3[i][1] = N1N2N3N4[2][0] * N1N2N3N4[2][1] * detJ[i] * conductivity * density;
                    pc3[i][2] = N1N2N3N4[2][0] * N1N2N3N4[2][2] * detJ[i] * conductivity * density;
                    pc3[i][3] = N1N2N3N4[2][0] * N1N2N3N4[2][3] * detJ[i] * conductivity * density;

                    pc4[i][0] = N1N2N3N4[3][0] * N1N2N3N4[3][0] * detJ[i] * conductivity * density;
                    pc4[i][1] = N1N2N3N4[3][0] * N1N2N3N4[3][1] * detJ[i] * conductivity * density;
                    pc4[i][2] = N1N2N3N4[3][0] * N1N2N3N4[3][2] * detJ[i] * conductivity * density;
                    pc4[i][3] = N1N2N3N4[3][0] * N1N2N3N4[3][3] * detJ[i] * conductivity * density;
                    break;

                case 1:
                    pc1[i][0] = N1N2N3N4[i][0] * N1N2N3N4[0][0] * detJ[i] * conductivity * density;
                    pc1[i][1] = N1N2N3N4[i][0] * N1N2N3N4[0][1] * detJ[i] * conductivity * density;
                    pc1[i][2] = N1N2N3N4[i][0] * N1N2N3N4[0][2] * detJ[i] * conductivity * density;
                    pc1[i][3] = N1N2N3N4[i][0] * N1N2N3N4[0][3] * detJ[i] * conductivity * density;

                    pc2[i][0] = N1N2N3N4[i][1] * N1N2N3N4[1][0] * detJ[i] * conductivity * density;
                    pc2[i][1] = N1N2N3N4[i][1] * N1N2N3N4[1][1] * detJ[i] * conductivity * density;
                    pc2[i][2] = N1N2N3N4[i][1] * N1N2N3N4[1][2] * detJ[i] * conductivity * density;
                    pc2[i][3] = N1N2N3N4[i][1] * N1N2N3N4[1][3] * detJ[i] * conductivity * density;

                    pc3[i][0] = N1N2N3N4[2][1] * N1N2N3N4[2][0] * detJ[i] * conductivity * density;
                    pc3[i][1] = N1N2N3N4[2][1] * N1N2N3N4[2][1] * detJ[i] * conductivity * density;
                    pc3[i][2] = N1N2N3N4[2][1] * N1N2N3N4[2][2] * detJ[i] * conductivity * density;
                    pc3[i][3] = N1N2N3N4[2][1] * N1N2N3N4[2][3] * detJ[i] * conductivity * density;

                    pc4[i][0] = N1N2N3N4[3][1] * N1N2N3N4[3][0] * detJ[i] * conductivity * density;
                    pc4[i][1] = N1N2N3N4[3][1] * N1N2N3N4[3][1] * detJ[i] * conductivity * density;
                    pc4[i][2] = N1N2N3N4[3][1] * N1N2N3N4[3][2] * detJ[i] * conductivity * density;
                    pc4[i][3] = N1N2N3N4[3][1] * N1N2N3N4[3][3] * detJ[i] * conductivity * density;


                    break;
                case 2:
                    pc1[i][0] = N1N2N3N4[i][0] * N1N2N3N4[0][0] * detJ[i] * conductivity * density;
                    pc1[i][1] = N1N2N3N4[i][0] * N1N2N3N4[0][1] * detJ[i] * conductivity * density;
                    pc1[i][2] = N1N2N3N4[i][0] * N1N2N3N4[0][2] * detJ[i] * conductivity * density;
                    pc1[i][3] = N1N2N3N4[i][0] * N1N2N3N4[0][3] * detJ[i] * conductivity * density;

                    pc2[i][0] = N1N2N3N4[i][1] * N1N2N3N4[1][0] * detJ[i] * conductivity * density;
                    pc2[i][1] = N1N2N3N4[i][1] * N1N2N3N4[1][1] * detJ[i] * conductivity * density;
                    pc2[i][2] = N1N2N3N4[i][1] * N1N2N3N4[1][2] * detJ[i] * conductivity * density;
                    pc2[i][3] = N1N2N3N4[i][1] * N1N2N3N4[1][3] * detJ[i] * conductivity * density;

                    pc3[i][0] = N1N2N3N4[2][2] * N1N2N3N4[2][0] * detJ[i] * conductivity * density;
                    pc3[i][1] = N1N2N3N4[2][2] * N1N2N3N4[2][1] * detJ[i] * conductivity * density;
                    pc3[i][2] = N1N2N3N4[2][2] * N1N2N3N4[2][2] * detJ[i] * conductivity * density;
                    pc3[i][3] = N1N2N3N4[2][2] * N1N2N3N4[2][3] * detJ[i] * conductivity * density;

                    pc4[i][0] = N1N2N3N4[3][2] * N1N2N3N4[3][0] * detJ[i] * conductivity * density;
                    pc4[i][1] = N1N2N3N4[3][2] * N1N2N3N4[3][1] * detJ[i] * conductivity * density;
                    pc4[i][2] = N1N2N3N4[3][2] * N1N2N3N4[3][2] * detJ[i] * conductivity * density;
                    pc4[i][3] = N1N2N3N4[3][2] * N1N2N3N4[3][3] * detJ[i] * conductivity * density;

                    break;
                case 3:
                    pc1[i][0] = N1N2N3N4[i][0] * N1N2N3N4[0][0] * detJ[i] * conductivity * density;
                    pc1[i][1] = N1N2N3N4[i][0] * N1N2N3N4[0][1] * detJ[i] * conductivity * density;
                    pc1[i][2] = N1N2N3N4[i][0] * N1N2N3N4[0][2] * detJ[i] * conductivity * density;
                    pc1[i][3] = N1N2N3N4[i][0] * N1N2N3N4[0][3] * detJ[i] * conductivity * density;

                    pc2[i][0] = N1N2N3N4[i][1] * N1N2N3N4[1][0] * detJ[i] * conductivity * density;
                    pc2[i][1] = N1N2N3N4[i][1] * N1N2N3N4[1][1] * detJ[i] * conductivity * density;
                    pc2[i][2] = N1N2N3N4[i][1] * N1N2N3N4[1][2] * detJ[i] * conductivity * density;
                    pc2[i][3] = N1N2N3N4[i][1] * N1N2N3N4[1][3] * detJ[i] * conductivity * density;

                    pc3[i][0] = N1N2N3N4[2][3] * N1N2N3N4[2][0] * detJ[i] * conductivity * density;
                    pc3[i][1] = N1N2N3N4[2][3] * N1N2N3N4[2][1] * detJ[i] * conductivity * density;
                    pc3[i][2] = N1N2N3N4[2][3] * N1N2N3N4[2][2] * detJ[i] * conductivity * density;
                    pc3[i][3] = N1N2N3N4[2][3] * N1N2N3N4[2][3] * detJ[i] * conductivity * density;

                    pc4[i][0] = N1N2N3N4[3][3] * N1N2N3N4[3][0] * detJ[i] * conductivity * density;
                    pc4[i][1] = N1N2N3N4[3][3] * N1N2N3N4[3][1] * detJ[i] * conductivity * density;
                    pc4[i][2] = N1N2N3N4[3][3] * N1N2N3N4[3][2] * detJ[i] * conductivity * density;
                    pc4[i][3] = N1N2N3N4[3][3] * N1N2N3N4[3][3] * detJ[i] * conductivity * density;

                    break;
            }
        }
    }

    void MatrixCCalculation() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrixC[i][j] = pc1[i][j] + pc2[i][j] + pc3[i][j] + pc4[i][j];
                System.out.print(matrixC[i][j] + " ");
            }
        }
    }
}
