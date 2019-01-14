package main;

public class VectorP {

    public double[] vectorPCalculation(double lengthOfSide, double t0, Point point, double alphaCoefficient) {
        double[] nCaulculation = new double[4];
            nCaulculation[0] = 0.25 * (1 - point.x) * (1 - point.y) * lengthOfSide / 2 * t0 * alphaCoefficient;
            nCaulculation[1] = 0.25 * (1 + point.x) * (1 - point.y) * lengthOfSide / 2 * t0 * alphaCoefficient;
            nCaulculation[2] = 0.25 * (1 + point.x) * (1 + point.y) * lengthOfSide / 2 * t0 * alphaCoefficient;
            nCaulculation[3] = 0.25 * (1 - point.x) * (1 + point.y) * lengthOfSide / 2 * t0 * alphaCoefficient;

        return nCaulculation;
    }

}
