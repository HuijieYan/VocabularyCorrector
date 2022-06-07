public class CorrectSpell {
    private char[] word1CharList;
    private char[] word2CharList;
    private int[][] moveList;
    private int row;
    private int column;

    public int minDistance(String word1, String word2){
        this.row = word1.length();
        this.column = word2.length();
        this.moveList = new int[this.row + 1][this.column + 1];
        for(int index = 0; index <= this.row; index++){
            this.moveList[index][0] = index;
        }
        for(int index = 0; index <= this.column; index++){
            this.moveList[0][index] = index;
        }

        this.word1CharList = word1.toCharArray();
        this.word2CharList = word2.toCharArray();

        return this.distanceCalculator();
    }

    private int distanceCalculator(){
        for(int rowIndex = 1; rowIndex <= this.row; rowIndex++) {
            for (int columnIndex = 1; columnIndex <= this.column; columnIndex++) {
                if(this.word1CharList[rowIndex - 1] == this.word2CharList[columnIndex - 1]){
                    this.moveList[rowIndex][columnIndex] = this.moveList[rowIndex - 1][columnIndex - 1];
                }else{
                    this.moveList[rowIndex][columnIndex] = 1 + Math.min(this.moveList[rowIndex - 1][columnIndex - 1], Math.min(this.moveList[rowIndex - 1][columnIndex], this.moveList[rowIndex][columnIndex - 1]));
                }
            }
        }
        return this.moveList[this.row][this.column];
    }

    public void showList(){
        for(int rowIndex = 0; rowIndex <= this.row; rowIndex++){
            for(int columnIndex = 0; columnIndex <= this.column; columnIndex++){
                System.out.print(this.moveList[rowIndex][columnIndex]);
            }
            System.out.println();
        }
    }
}