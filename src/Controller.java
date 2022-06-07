public class Controller {
    private Dictionary dictionary;
    private Input input = new Input();
    private StringArray unMatchedString;
    private StringArray replacement;
    private String inputFileName;

    public Controller(){
        this.dictionary = new Dictionary();
    }

    private void checkSpell(){
        this.inputFileName = this.getFileName();
        FileInput in = new FileInput(this.inputFileName);
        String buffer = "";
        while(in.hasNextLine()){
            buffer = buffer + in.nextLine() + " ";
        }
        in.close();

        StringArray textEntry = this.toStringArray(buffer);
        this.unMatchedString = new StringArray();
        this.replacement = new StringArray();
        for(int index = 0; index < textEntry.size(); index++){
            String element = textEntry.get(index);
            if(!this.dictionary.contains(element)){
                this.unMatchedString.add(element);
                this.replacement.add(null);
            }
        }

        if(this.unMatchedString.size() > 0){
            System.out.println("\nThe wrong words are: ");
            this.unMatchedString.showStringArray();
            this.correctSpell();
        }else{
            System.out.println("\nAll words in the text file is correct!\n");
        }
    }

    private void correctSpell(){
        System.out.println("Do you want to correct the wrong words by this application?");
        while(true){
            System.out.println("Enter Yes to see a list of possible correct spellings, or No to back to Menu.");
            String mode = this.input.next();
            if("Yes".equalsIgnoreCase(mode)){
                break;
            }else if("No".equalsIgnoreCase(mode)){
                return;
            }else{
                System.out.println("Invalid mode, Please enter again!");
            }
        }


        for(int index = 0; index < this.unMatchedString.size(); index++){
            String str = this.unMatchedString.get(index);
            StringArray correctorList = this.dictionary.correctWord(str);
            if(correctorList.size() == 0){
                System.out.printf("There is not a good replacement for %s!\n", str);
                continue;
            }


            while(true){
                System.out.printf("\nThe possible correct words for %s are:\n", str);
                correctorList.showStringArray();

                System.out.print("\nPlease enter the word that you want (input 0 to skip this word!): ");
                String result = this.input.next();

                if(result.equals("0")){
                    break;
                }else if(correctorList.contains(result)){
                    this.replacement.set(index, result);
                    break;
                }else{
                    System.out.println("Invalid input, please try again!");
                }
            }

        }

        this.outputToFile();

    }

    private void outputToFile(){
        FileInput in = new FileInput(this.inputFileName);
        String buffer = "";
        while(in.hasNextLine()){
            buffer = buffer + in.nextLine() + "\n";
        }
        in.close();

        for(int index = 0; index < this.unMatchedString.size(); index++){
            if(this.replacement.get(index) != null){
                buffer = buffer.replaceAll(this.unMatchedString.get(index), this.replacement.get(index));
            }
        }

        System.out.println("\nYour text will be in a output file!");
        FileOutput out = new FileOutput(this.getFileName());
        out.writeString(buffer);
        out.close();
    }

    private String getFileName(){
        System.out.print("Please enter the file name: ");
        return this.input.next();
    }

    private StringArray toStringArray(String str){
        String[] temp = str.replaceAll("[\\pP‘’“”]", "").split(" ");
        StringArray textEntry = new StringArray();
        for(String element : temp){
            textEntry.add(element.replaceAll(" ", ""));
        }
        return textEntry;
    }


    private void SelectMode(){
        while(true){
            System.out.println("\nWelcome to the spell-check application!");
            System.out.println("Enter 1 to select a file that contains text.");
            System.out.println("Enter 2 quit the application.");

            int mode = this.input.nextInt();
            if(mode == 1){
                this.checkSpell();
            }else if(mode == 2){
                this.input.close();
                break;
            }else{
                System.out.println("Invalid mode, Please enter again!(1: Select file, 2: Exit.)");
            }
        }
    }

    public void start() {
        this.SelectMode();
    }
}