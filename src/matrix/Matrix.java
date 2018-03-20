package matrix;

public class Matrix {
    private int[][] arr;
    Matrix(int x, int y){
        arr = new int[x][y];
        fillArray();
    }
    private void fillArray(){
        int numbers = 0;
        for (int i=0; i<arr.length; i++){
            for (int j=0; j<arr[i].length; j++){
                arr[i][j] = numbers++;
            }
        }
    }
    void printMatrix(){
        for (int[] anArr : arr) {
            for (int anAnArr : anArr) {
                System.out.print(anAnArr + "\t");
            }
            System.out.println();
        }
    }
    void printSpirally(){
        int length = arr[0].length;
        int height = arr.length;
        int borderOfLength = 0;
        int borderOfHeight = 0;
        int row = 0; // current row, current element in column
        int column = 0; // current column, current element in row
        while (true){
            if (column>=length-borderOfLength) {
                break;
            }
            while (column<length-borderOfLength){
                System.out.print(arr[row][column++] + " ");
            }
            column--;
            row++;
            if (row>=height-borderOfHeight) {
                break;
            }
            while (row<height-borderOfHeight){
                System.out.print(arr[row++][column] + " ");
            }
            column--;
            row--;
            if (column<borderOfLength){
                break;
            }
            while (column>=borderOfLength){
                System.out.print(arr[row][column--] + " ");
            }
            column++;
            row--;
            if (row<=borderOfHeight){
                break;
            }
            while (row>borderOfHeight){
                System.out.print(arr[row--][column] + " ");
            }
            row++;
            column++;
            borderOfLength++;
            borderOfHeight++;
        }
    }
}
