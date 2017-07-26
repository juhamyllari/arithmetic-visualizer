package av.arithmeticvisualizer;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        
        double[][] A = {{1, 2, 3,}, {4, 5, 6}};
        double[][] B = {{18}};
        
        Node root = new AdditionNode(new ArrayNode(7), new ArrayNode(B));
        
        try {
            System.out.println(Arrays.deepToString(root.eval()));
        } catch (DimensionMismatchException ex) {
            System.out.println("Evaluation failed with message:");
            System.out.println(ex.getMessage());
        }
    }
    
}
