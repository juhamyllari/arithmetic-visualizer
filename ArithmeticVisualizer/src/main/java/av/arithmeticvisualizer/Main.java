package av.arithmeticvisualizer;

import java.util.Arrays;
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
        
        try {
            root = new MultiplicationNode(new ArrayNode(A), new ArrayNode(D));
            System.out.println(Arrays.deepToString(root.eval().getValue()));
        } catch (WrongShapeException ex) {
            System.out.println("Node creation or evaluation failed with message:");
            System.out.println(ex.getMessage());
        }

    }

}
