package av.arithmeticvisualizer;

public class Expression {

    private Node root;

    public Expression(Node root) {
        this.root = root;
    }

    public Value evaluate() {
        return root.evaluate();
    }

}
