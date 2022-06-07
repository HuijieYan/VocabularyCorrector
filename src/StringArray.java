public class StringArray {
    private String[] array;
    private int size = 0;
    private int length = 100;

    public StringArray(){
        this.array = new String[100];
    }

    public StringArray(StringArray a){
        a.sizeChange();
        this.array = a.array.clone();
        this.size = a.size;
        this.length = a.length;
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        if(this.size == 0){
            return true;
        }else{
            return false;
        }
    }

    public String get(int index){
        if(0 <= index && index < this.size){
            return this.array[index];
        }else{
            return null;
        }
    }

    public void set(int index, String s){
        if(0 <= index && index < this.size){
            this.array[index] = s;
        }
    }

    public void add(String s){
        this.sizeChange();
        this.array[this.size] = s;
        this.size++;
    }

    public void insert(int index, String s){
        this.sizeChange();
        if(0 <= index && index < this.size){
            System.arraycopy(this.array,index,this.array,(index+1),(this.size - index));
            this.array[index] = s;
            this.size++;
        }
    }

    public void remove(int index){
        if(index == this.size - 1){
            this.array[index] = null;
        }else if(0 <= index && index < (this.size - 1)){
            System.arraycopy(this.array,(index+1),this.array,index,(this.size - index - 1));
        }else{
            return;
        }
        this.size--;
        this.sizeChange();
    }

    private void sizeChange(){
        if(this.size < (this.length / 2)){
            if (this.length == 100) {
                return;
            }

            String[] temp = this.array.clone();
            this.array = new String[this.length/2];
            System.arraycopy(temp, 0, this.array, 0, this.length/2);
            this.length = this.length / 2;
        }else if(this.size == this.length) {
            if (this.length > 1000000) {
                return;
            }

            String[] temp = this.array.clone();
            this.array = new String[this.length * 2];
            System.arraycopy(temp,0,this.array,0,this.length);
            this.length = this.length * 2;
        }
    }

    public boolean contains(String s){
        if(s == null){
            for(int index = 0; index < this.size; index++){
                if (this.array[index] == null){ return true; }
            }
            return false;
        }

        for(int index = 0; index < this.size; index++){
            if (this.array[index].equalsIgnoreCase(s)){ return true; }
        }
        return false;
    }

    public boolean containsMatchingCase(String s){
        if(s == null){
            for(int index = 0; index < this.size; index++){
                if (this.array[index] == null){ return true; }
            }
            return false;
        }

        for(int index = 0; index < this.size; index++){
            if (this.array[index].equals(s)){ return true; }
        }
        return false;
    }

    public int indexOf(String s){
        if(s == null){
            for(int index = 0; index < this.size; index++){
                if (this.array[index] == null){ return index; }
            }
            return -1;
        }

        for(int index = 0; index < this.size; index++){
            if (this.array[index].equalsIgnoreCase(s)){ return index; }
        }
        return -1;
    }

    public int indexOfMatchingCase(String s){
        if(s == null){
            for(int index = 0; index < this.size; index++){
                if (this.array[index] == null){ return index; }
            }
            return -1;
        }

        for(int index = 0; index < this.size; index++){
            if (this.array[index].equals(s)){ return index; }
        }
        return -1;
    }

    public void showStringArray(){
        int wordLimite = 1;
        for(int currentIndex = 0; currentIndex < this.size; currentIndex++){
            System.out.print(this.array[currentIndex] + "  ");
            wordLimite++;
            if(wordLimite == 10){
                System.out.println();
                wordLimite = 1;
            }
        }
        System.out.println();
    }
}