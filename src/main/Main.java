package main;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        FileData fileData = new FileData();
        fileData.openFileAndReadData();

        Grid grid = new Grid();

        grid.setNumberOfElements((fileData.getGridHeightNumberOfElements() - 1) * (fileData.getGridWidthNumberOfElements() - 1));
        grid.setNumberOfNodes(fileData.getGridHeight() * fileData.getGridWidth());

        grid.nodeIDGeneration(fileData);
        grid.elementsGeneration(fileData);

        Element element = grid.elements[0];

        UniversalElement universalElement = new UniversalElement();
        universalElement.calculateDn_Dksi_Dn_Eta_Value();
        BuilderMatrixH builderMatrixH = new BuilderMatrixH();
        builderMatrixH.BuilderJacobian(universalElement, grid);

        BuilderMatrixC builderMatrixC = new BuilderMatrixC();
        builderMatrixC.N1N2N3N4Calculations();
        builderMatrixC.pcNumberCalculations(builderMatrixH);
        builderMatrixC.MatrixCCalculation();

        BuilderMatrixHBC2D builderMatrixHBC2D = new BuilderMatrixHBC2D();
        builderMatrixHBC2D.detJCalculations();
        builderMatrixHBC2D.areaArrayInit();

        AreaGenerator areaGenerator = new AreaGenerator();
        areaGenerator.areaStatusGenerator(grid, fileData);



    }
}
