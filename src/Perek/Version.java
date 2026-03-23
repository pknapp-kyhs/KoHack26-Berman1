package Perek;

//a data hold class to allow gson to unpack sefaria data
public class Version {
    //an array of perakim each of which is an array of psukim
    public String[][] text;
    public String language;
    public String toString() {
        String out = "Languge: " + language + "\n";
        for (String[] perek : text) {
            for (String passuk : perek) {
                out += passuk + "\n";
            }
            out += "\n";
        }
        return out;
    }
}
