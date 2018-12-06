package main;

public class VectorP {
    Point [] points = new Point[4];

    public void vectorPCalculation(double [] detJ, double t0, Point point){
        double []nCaulculation = new double[4];
        double alpa = 25;
        double []length = {8, 10};
        for (int i = 0; i < 4; i++) {
            nCaulculation[0] = 0.25 * (1 - point.x) * (1 - point.y) * detJ[0] * length[1] / 2 * t0 * alpa;
            nCaulculation[1] = 0.25 * (1 + point.x) * (1 - point.y) * detJ[0] * length[1] / 2 * t0 * alpa;
            nCaulculation[2] = 0.25 * (1 + point.x) * (1 + point.y) * detJ[0] * length[1] / 2 * t0 * alpa;
            nCaulculation[3] = 0.25 * (1 - point.x) * (1 + point.y) * detJ[0] * length[1] / 2 * t0 * alpa;
        }


    }



}
