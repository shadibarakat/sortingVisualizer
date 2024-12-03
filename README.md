# Sorting Visualizer

This JavaFX application visualizes popular sorting algorithms, including Bubble Sort, Selection Sort, and Insertion Sort. Users can generate random arrays, control the sorting speed, and view statistics such as the number of comparisons and swaps.

---

## Features

- **Sorting Algorithms**: Visualizes Bubble Sort, Selection Sort, and Insertion Sort.
- **Dynamic Array Size**: Adjust the array size using a slider (10 to 100 elements).
- **Sorting Speed Control**: Modify the sorting speed with a slider (50ms to 500ms per step).
- **Visual Feedback**: Bars change color during sorting (red for comparison, green for sorted elements).
- **Statistics**: Displays the number of comparisons and swaps performed during sorting.
- **Interactive Interface**: Includes buttons and dropdowns for user control.

---

## How to Use

1. **Generate Array**:
   - Click the **"Generate Array"** button to create a random array with the size specified by the **"Array Size"** slider.

2. **Select Algorithm**:
   - Choose a sorting algorithm from the dropdown menu:
     - Bubble Sort
     - Selection Sort
     - Insertion Sort

3. **Adjust Speed**:
   - Use the **"Speed"** slider to control the sorting speed.

4. **Start Sorting**:
   - Click the **"Sort"** button to start visualizing the selected algorithm.

5. **View Statistics**:
   - Monitor the **"Comparisons"** and **"Swaps"** statistics at the top of the application.

---

## Development Environment

- **Java**: JDK 8 or later
- **JavaFX**: JavaFX SDK
- **IDE**: Any Java-compatible IDE (e.g., IntelliJ IDEA, Eclipse)

---

## Prerequisites

- Install Java JDK (version 8 or later).
- Ensure JavaFX is set up in your development environment.

---

## Installation

1. Clone the repository or download the `SortingVisualizer.java` file.
2. Set up your JavaFX environment:
   - Add JavaFX libraries to your project.
3. Run the `SortingVisualizer` class.

---

## Project Structure

- **Main Class**: `SortingVisualizer`
- **Key Functions**:
  - `bubbleSort`: Implements Bubble Sort visualization.
  - `selectionSort`: Implements Selection Sort visualization.
  - `insertionSort`: Implements Insertion Sort visualization.
  - `generateArray`: Generates a random array.
  - `generateBars`: Updates the bar visualization based on the array.
  - `updateStats`: Updates the comparison and swap counters.

---

## Controls and Interface

- **Generate Array Button**: Creates a new random array.
- **Sort Button**: Starts sorting the array with the selected algorithm.
- **Dropdown Menu**: Selects the sorting algorithm.
- **Speed Slider**: Adjusts the sorting speed.
- **Array Size Slider**: Modifies the number of elements in the array.
- **Statistics Label**: Displays the current number of comparisons and swaps.

---

## Visual Feedback

- **Blue Bars**: Default bar color.
- **Red Bars**: Bars currently being compared.
- **Green Bars**: Bars sorted into their correct position.

---

## Customization

You can add more sorting algorithms to the application by following these steps:

1. Implement the sorting algorithm in a new method.
2. Ensure the method uses the `Timeline` class for animation.
3. Update the `ComboBox` with the new algorithm.
4. Link the new method in the `sortButton` event listener.

---

## Future Improvements

- Add more algorithms like Quick Sort, Merge Sort, or Heap Sort.
- Include pause and reset functionalities.
- Allow customization of bar colors and other visual elements.
- Display the time taken for each sorting algorithm.

---

## License

This project is open-source and can be modified or distributed as needed.
