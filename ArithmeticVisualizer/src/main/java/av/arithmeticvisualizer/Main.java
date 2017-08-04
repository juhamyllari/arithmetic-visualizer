package av.arithmeticvisualizer;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {

        double[][] A = {{1, 2, 3,}, {4, 5, 6}};
        double[][] B = {{18}};
        double[][] C = {{1, 2, 3,}};
        double[][] D = {{4}, {5}, {6}};
        double[][] E = {{4}, {5}};

        Node root;
        Expression expr;

        root = new BinaryNode(new ValueNode(A), new ValueNode(D), Utils.multiply);
        expr = new Expression(root);
        System.out.println(Arrays.deepToString(expr.evaluate().getValue()));

    }

}
