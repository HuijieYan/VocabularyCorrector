public class Dictionary {
    private StringArray dictionary;
    private int[] alphabetIndex;
    private CorrectSpell corrector = new CorrectSpell();

    public Dictionary(){
        this.dictionary = new StringArray();
        FileInput in = new FileInput(".\\words");
        while (in.hasNextLine()){
            String s = in.nextLine();
            this.dictionary.add(s);
        }
        in.close();

        this.alphabetIndex = new int[27];
        char[] alphabetList = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        int index = 0;
        for(char element : alphabetList){
            this.alphabetIndex[index] = this.dictionary.indexOf(String.valueOf(element));
            index++;
        }
        this.alphabetIndex[26] = this.dictionary.size() - 1;
    }

    public boolean contains(String str){
        if(str == null){
            return false;
        }
        char alphabet = str.toLowerCase().charAt(0);
        String alphabetList = "abcdefghijklmnopqrstuvwxyz";
        int index = alphabetList.indexOf(alphabet);
        if(index == -1){
            return false;
        }

        int lowerBound = alphabetIndex[index];
        int upperBound = alphabetIndex[index+1];
        while(lowerBound < upperBound){
            if(this.dictionary.get(lowerBound).equalsIgnoreCase(str)){ return true; }
            lowerBound++;
        }

        return false;
    }

    public StringArray correctWord(String str){
        if(str == null){
            return new StringArray();
        }

        StringArray replacement = new StringArray();
        for(int dicIndex = 0; dicIndex < this.dictionary.size(); dicIndex++){
            String dicStr = this.dictionary.get(dicIndex);
            if(dicStr == null){
                continue;
            }
            if(this.corrector.minDistance(str.toLowerCase(), dicStr.toLowerCase()) <= 2){
                replacement.add(dicStr);
            }
        }
        return replacement;
    }
}