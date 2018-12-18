package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileData {
    public int gridHeightNumberOfElements;
    public int gridWidthNumberOfElements;

    public int getGridHeightNumberOfElements() {
        return gridHeightNumberOfElements;
    }

    public int getGridWidthNumberOfElements() {
        return gridWidthNumberOfElements;
    }

    public double getGridHeight() {
        return gridHeight;
    }

    public double getGridWidth() {
        return gridWidth;
    }

    public int getCoefficientAlpha() {
        return coefficientAlpha;
    }

    public int getInitialTemperature() {
        return InitialTemperature;
    }

    public int counterToReadFromFile;
    public double gridHeight;
    public double gridWidth;
    public int coefficientAlpha;
    public int InitialTemperature;
    public int simulationTime;
    public double simulationStepTime;
    public int ambientTemperature;
    public double specificHeat;
    public double conductivity;
    public double density;

    public int getSimulationTime() {
        return simulationTime;
    }

    public double getSimulationStepTime() {
        return simulationStepTime;
    }

    public int getAmbientTemperature() {
        return ambientTemperature;
    }

    public double getSpecificHeat() {
        return specificHeat;
    }

    public double getConductivity() {
        return conductivity;
    }

    public double getDensity() {
        return density;
    }

    public void openFileAndReadData() throws FileNotFoundException {
        File file = new File("data.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            if (counterToReadFromFile == 0) {
                InitialTemperature = Integer.parseInt(String.valueOf(scanner.nextInt()));
            } else if (counterToReadFromFile == 1) {
                simulationTime = Integer.parseInt(String.valueOf(scanner.nextInt()));
            } else if (counterToReadFromFile == 2) {
                simulationStepTime = Double.parseDouble(String.valueOf(scanner.next()));
            } else if (counterToReadFromFile == 3) {
                ambientTemperature = Integer.parseInt(String.valueOf(scanner.nextInt()));
            } else if (counterToReadFromFile == 4) {
                coefficientAlpha = Integer.parseInt(String.valueOf(scanner.nextInt()));
            } else if (counterToReadFromFile == 5) {
                gridHeight = Double.parseDouble(String.valueOf(scanner.next()));
            } else if (counterToReadFromFile == 6){
                gridWidth = Double.parseDouble(String.valueOf(scanner.next()));
            } else if (counterToReadFromFile == 7){
                gridHeightNumberOfElements = Integer.parseInt(String.valueOf(scanner.nextInt()));
            } else if (counterToReadFromFile == 8){
                gridWidthNumberOfElements = Integer.parseInt(String.valueOf(scanner.nextInt()));
            } else if (counterToReadFromFile == 9){
                specificHeat = Double.parseDouble(String.valueOf(scanner.nextDouble()));
            } else if (counterToReadFromFile == 10){
                conductivity = Double.parseDouble(String.valueOf(scanner.nextDouble()));
            } else if (counterToReadFromFile == 11) {
                density = Double.parseDouble(String.valueOf(scanner.nextDouble()));
            }
            scanner.nextLine();
            counterToReadFromFile++;
        }
    }
    public void showInputData(){
        System.out.println("Initial temperature: " + getInitialTemperature());
        System.out.println("Initial simulation time: " + getSimulationTime());
        System.out.println("Initial simulation step time: " + getSimulationStepTime());
        System.out.println("Initial ambient temperature: " + getAmbientTemperature());
        System.out.println("Initial alpha: " + getCoefficientAlpha());
        System.out.println("Initial height: " + getGridHeight());
        System.out.println("Initial width: " + getGridWidth());
        System.out.println("Initial height elements: " + getGridHeightNumberOfElements());
        System.out.println("Initial width elements: " + getGridWidthNumberOfElements());
        System.out.println("Initial specific heat: " + getSpecificHeat());
        System.out.println("Initial conductivity: " + getConductivity());
        System.out.println("Initial density: " + getDensity());

    }
}
