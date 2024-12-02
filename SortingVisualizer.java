import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Random;

public class SortingVisualizer extends Application {
    private int[] array;
    private Timeline timeline;
    private int comparisons = 0;
    private int swaps = 0;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();

        Pane canvas = new Pane();
        root.setCenter(canvas);

        HBox controls = new HBox(10); // Add spacing between controls

        // Generate Array Button
        Button generateButton = new Button("Generate Array");

        // Sort Button
        Button sortButton = new Button("Sort");

        // Speed Slider
        Slider speedSlider = new Slider(50, 500, 100); // Min 50ms, Max 500ms, Default 100ms
        speedSlider.setShowTickLabels(true);
        speedSlider.setShowTickMarks(true);
        speedSlider.setMajorTickUnit(100);
        speedSlider.setBlockIncrement(50);

        // Array Size Slider
        Slider sizeSlider = new Slider(10, 100, 50); // Min 10 elements, Max 100 elements, Default 50
        sizeSlider.setShowTickLabels(true);
        sizeSlider.setShowTickMarks(true);
        sizeSlider.setMajorTickUnit(10);
        sizeSlider.setBlockIncrement(5);

        // Algorithm Selector
        ComboBox<String> algorithmSelector = new ComboBox<>();
        algorithmSelector.getItems().addAll("Bubble Sort", "Selection Sort", "Insertion Sort");
        algorithmSelector.setValue("Bubble Sort");

        // Stats Label
        Label statsLabel = new Label("Comparisons: 0 | Swaps: 0");

        // Add controls to the HBox
        controls.getChildren().addAll(generateButton, sortButton, algorithmSelector, speedSlider, sizeSlider);
        root.setBottom(controls);
        root.setTop(statsLabel);

        // Event listener for "Generate Array"
        generateButton.setOnAction(e -> {
            int arraySize = (int) sizeSlider.getValue(); // Get array size from slider
            array = generateArray(arraySize, (int) canvas.getHeight());
            comparisons = 0;
            swaps = 0;
            updateStats(statsLabel); // Reset stats
            generateBars(canvas, array);
            if (timeline != null) timeline.stop(); // Stop any ongoing sort
        });

        // Event listener for "Sort"
        sortButton.setOnAction(e -> {
            String selectedAlgorithm = algorithmSelector.getValue();
            if (selectedAlgorithm.equals("Bubble Sort")) {
                bubbleSort(array, canvas, speedSlider.getValue(), statsLabel);
            } else if (selectedAlgorithm.equals("Selection Sort")) {
                selectionSort(array, canvas, speedSlider.getValue(), statsLabel);
            } else if (selectedAlgorithm.equals("Insertion Sort")) {
                insertionSort(array, canvas, speedSlider.getValue(), statsLabel);
            }
        });

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Sorting Visualizer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void generateBars(Pane canvas, int[] array) {
        canvas.getChildren().clear();
        int width = (int) canvas.getWidth() / array.length;

        for (int i = 0; i < array.length; i++) {
            Rectangle bar = new Rectangle(i * width, canvas.getHeight() - array[i], width - 2, array[i]);
            bar.setFill(Color.BLUE);
            canvas.getChildren().add(bar);
        }
    }

    private int[] generateArray(int size, int maxValue) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(maxValue) + 1;
        }
        return array;
    }

    private void updateStats(Label statsLabel) {
        statsLabel.setText("Comparisons: " + comparisons + " | Swaps: " + swaps);
    }

    private void bubbleSort(int[] array, Pane canvas, double speed, Label statsLabel) {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        int n = array.length;
        int[] i = {0}, j = {0};

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(speed), e -> {
            if (i[0] < n - 1) {
                if (j[0] < n - i[0] - 1) {
                    Rectangle bar1 = (Rectangle) canvas.getChildren().get(j[0]);
                    Rectangle bar2 = (Rectangle) canvas.getChildren().get(j[0] + 1);

                    bar1.setFill(Color.RED); // Highlight bar1
                    bar2.setFill(Color.RED); // Highlight bar2

                    comparisons++;
                    updateStats(statsLabel);

                    if (array[j[0]] > array[j[0] + 1]) {
                        int temp = array[j[0]];
                        array[j[0]] = array[j[0] + 1];
                        array[j[0] + 1] = temp;

                        swaps++;
                        updateStats(statsLabel);

                        generateBars(canvas, array);
                    }

                    bar1.setFill(Color.BLUE); // Reset color
                    bar2.setFill(Color.BLUE); // Reset color
                    j[0]++;
                } else {
                    j[0] = 0;
                    i[0]++;
                }
            } else {
                timeline.stop(); // End sorting
                for (int k = 0; k < array.length; k++) {
                    Rectangle bar = (Rectangle) canvas.getChildren().get(k);
                    bar.setFill(Color.GREEN); // Mark sorted bars as green
                }
            }
        }));

        timeline.play();
    }

    // Selection Sort
    private void selectionSort(int[] array, Pane canvas, double speed, Label statsLabel) {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        int n = array.length;
        int[] i = {0};

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(speed), e -> {
            if (i[0] < n - 1) {
                int minIndex = i[0];
                for (int j = i[0] + 1; j < n; j++) {
                    comparisons++;
                    updateStats(statsLabel);

                    if (array[j] < array[minIndex]) {
                        minIndex = j;
                    }
                }
                if (minIndex != i[0]) {
                    int temp = array[i[0]];
                    array[i[0]] = array[minIndex];
                    array[minIndex] = temp;

                    swaps++;
                    updateStats(statsLabel);

                    generateBars(canvas, array);
                }
                i[0]++;
            } else {
                timeline.stop();
                for (int k = 0; k < array.length; k++) {
                    Rectangle bar = (Rectangle) canvas.getChildren().get(k);
                    bar.setFill(Color.GREEN);
                }
            }
        }));

        timeline.play();
    }

    // Insertion Sort
    private void insertionSort(int[] array, Pane canvas, double speed, Label statsLabel) {
        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        int[] i = {1};

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(speed), e -> {
            if (i[0] < array.length) {
                int key = array[i[0]];
                int j = i[0] - 1;

                while (j >= 0 && array[j] > key) {
                    array[j + 1] = array[j];
                    j--;

                    comparisons++;
                    updateStats(statsLabel);
                }

                array[j + 1] = key;

                swaps++;
                updateStats(statsLabel);

                generateBars(canvas, array);
                i[0]++;
            } else {
                timeline.stop();
                for (int k = 0; k < array.length; k++) {
                    Rectangle bar = (Rectangle) canvas.getChildren().get(k);
                    bar.setFill(Color.GREEN);
                }
            }
        }));

        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
