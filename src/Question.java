public class Question {
    public String textOfQuestion;
    public String answer;
    int indexOfQuestion;
    static Boolean useTTS;
    public Question(String textOfQuestion, String answer, int indexOfQuestion){
        this.textOfQuestion = textOfQuestion;
        this.answer = answer;
        this.indexOfQuestion = indexOfQuestion;
        useTTS = true;
        
    }
    public void askQuestion(){
        GUI.createWindow(String.valueOf(indexOfQuestion));
        GUI.addTextArea(textOfQuestion, 100);
        if(useTTS){
            TextToSpeech.speak(textOfQuestion);
        }
    }
}
