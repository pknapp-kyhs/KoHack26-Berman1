package Perek;

public class Version {
    public String[][] text;
    public String language;
    public String toString() {
        String out = "Languge: " + language;
        for (String[] perek : text) {
            for (String passuk : perek) {
                out += passuk + "\n";
            }
            out += "\n";
        }
        return out;
    }
}
