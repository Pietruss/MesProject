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

    public int getGridHeight() {
        return gridHeight;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public int getTemperature() {
        return temperature;
    }

    public int counterToReadFromFile;
    public int gridHeight;
    public int gridWidth;
    public int coefficient;
    public int temperature;

    void openFileAndReadData() throws FileNotFoundException {
        File file = new File("D:\\Informatyka\\Semestr5\\mes\\src\\main\\data.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            if (counterToReadFromFile == 0) {
                gridHeight = Integer.parseInt(String.valueOf(scanner.nextInt()));
            } else if (counterToReadFromFile == 1) {
                gridWidth = Integer.parseInt(String.valueOf(scanner.nextInt()));
            } else if (counterToReadFromFile == 2) {
                gridHeightNumberOfElements = Integer.parseInt(String.valueOf(scanner.nextInt()));
            } else if (counterToReadFromFile == 3) {
                gridWidthNumberOfElements = Integer.parseInt(String.valueOf(scanner.nextInt()));
            } else if (counterToReadFromFile == 4) {
                coefficient = Integer.parseInt(String.valueOf(scanner.nextInt()));
            } else if (counterToReadFromFile == 5) {
                temperature = Integer.parseInt(String.valueOf(scanner.nextInt()));
            }
            scanner.nextLine();
            counterToReadFromFile++;
        }
    }
}
