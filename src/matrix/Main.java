package matrix;

class Main{
    public static void main(String[] args) {

        /*
         * The class Matrix takes the input of the binary matrix and print it by spirally.
         * The Matrix could be any size.
         */

        Matrix myMatrix = new Matrix(5,7);
        myMatrix.printMatrix();
        System.out.println();
        myMatrix.printSpirally();

    }
}
