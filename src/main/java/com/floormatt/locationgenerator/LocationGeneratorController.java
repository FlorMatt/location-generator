package com.floormatt.locationgenerator;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.Arrays;
import java.util.List;

public class LocationGeneratorController {

    @FXML private TextField txtfld_aisleName;
    @FXML private TextField txtfld_numRows;
    @FXML private TextField txfld_levels;
    @FXML private TextArea txta_results;
    @FXML private TextArea texta_howTo;


    @FXML
    public void initialize() {
        // Optional: show instructions in your “How To” tab
        texta_howTo.setText(
                """
                        1. Enter your aisle name (e.g. PLOT02)
                        2. Enter number of rows (e.g. 25)
                        3. Enter levels separated by commas (e.g. A,B,C,D)
                        4. Click Generate to see all codes.
                        
                        Expected Results:
                        PLOT02-01-A
                        PLOT02-01-B
                        PLOT02-01-C
                        PLOT02-01-D"""
        );
    }

    @FXML
    private void generateLocations() {

        String aisleName = txtfld_aisleName.getText().trim();
        String numRowsText = txtfld_numRows.getText().trim();
        String levelsText = txfld_levels.getText().trim();


        //parse through the number of rows
        int numRows;

        try {
            numRows = Integer.parseInt(numRowsText);
            if (numRows < 1) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            showAlert("Invalid Input: Number of Rows", "‘Number of Rows’ must be a positive integer.");
            return;
        }

        //split levels by comma, strip white space
        List<String> levels = Arrays.stream(levelsText.split("\\s*,\\s*"))
                .filter(s -> !s.isEmpty())
                .toList();

        if (levels.isEmpty()) {
            showAlert("Invalid Input: Levels", "Please enter at least one level (e.g. A,B,C).");
            return;
        }

        //build the locations
        StringBuilder sb = new StringBuilder();
        for (int row = 1; row <= numRows; row++) {
            String formattedRow = String.format("%02d", row);
            for (String level : levels) {
                sb.append(aisleName)
                        .append('-')
                        .append(formattedRow)
                        .append('-')
                        .append(level)
                        .append('\n');
            }
        }

        //display the results
        txta_results.setText(sb.toString());
    }

    //utility to show an alert
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}