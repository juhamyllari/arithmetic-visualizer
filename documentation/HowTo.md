## How to Use Arithmetic Visualizer

### Running Arithmetic Visualizer

To run Arithmetic Visualizer, issue the following command in the directory where your jar file is located:
```
java -jar ArithmeticVisualizer-1.0.jar
```

If you are using OpenJDK, you may need to install OpenJFX before you can run Arithmetic Visualizer.

### The Entry Scene

Arithmetic Visualizer opens in the entry scene. You are prompted to enter two arrays (scalars, vectors or matrices) and to choose an operation to apply to them.

#### Entering Arrays

To enter an array, type it into the corresponding text box. Separate elements in the same row with whitespace and rows with a semicolon. For example, to enter a matrix whose first row is [1, 2, 3] and whose second row is [10, 20, 30], type
```
1 2 3; 10 20 30
```

Array elements are double precision floating point numbers. Both decimal notation and scientific notation are supported. Integers are automatically converted to floating point numbers. The following are therefore equivalent:
```
10.0
1.0e1
10
```

To create a scalar, simply enter an array consisting of a single element. To create a row vector, enter the elements separated by whitespace. To create a column vector, separate the elements by semicolons or create a corresponding row vector and transpose it (see below).

#### Transposing Arrays

You can transpose an operand by clicking the Transpose button for that array.

#### Selecting the Operation

Use the selection box to select which operation you want to apply to the operands. Operations supported at the moment are addition, subtraction and multiplication. The multiplication symbol in the selection box actually stands for three distinct operations: matrix-matrix multiplication, scalar multiplication from the left and scalar multiplication from the right. Arithmetic Visualizer will automatically select the correct multiplication mode based on the arrays you enter.

Arithmetic Visualizer treats matrix-vector multiplication and vector-vector multiplication simply as instances of matrix-matrix multiplication. Therefore, to take the dot product of two vectors, make the left operand a row vector and the right operand a column vector.

#### Creating a Node and Evaluating the Operation

After entering the two operands and selecting an operation, click "Create or update node" to see your operands and the operator symbol. An error text will inform you if the array dimensions are incompatible. Then click Evaluate to proceed to the evaluation scene of the application.

### The Evaluation Scene

In the evaluation scene you will see your two operands. Press Play to start the visualization. You will see an animation of the operation you entered. You can use the Play/Pause button to pause and resume the animation. If you click Play after the animation has finished, the animation will replay.

You can use the result of the operation as one or both operands in a new operation. Use the selection box to select how you would like to use the result array and click "Enter next expression". You can repeat the process as many times as you like. Happy visualizing!

### Exiting the Application

To exit Arithmetic Visualizer, close the window.
