package av.arithmeticvisualizer;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        double[][] matrixA = {{1, 2, 3}, {4, 5, 6}};
        double[][] matrixB = {{18}};
        double[][] matrixC = {{1, 2, 3}};
        double[][] matrixD = {{4}, {5}, {6}};
        double[][] matrixE = {{4}, {5}};

        Node root;
        Expression expr;

        root = new BinaryNode(new ValueNode(matrixA), new ValueNode(matrixD), NodeFunctions.multiply);
        expr = new Expression(root);
        try {
            System.out.println(Arrays.deepToString(expr.evaluate().getValue()));
        } catch (WrongShapeException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
