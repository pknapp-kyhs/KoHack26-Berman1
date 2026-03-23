package Perek;
//a data holding class to allow gson to parse data from sefaria
public class Perek {
    public Version[] versions;
    public String toString() {
        String out = "";
        for (Version version : versions) {
            out += version + "\n";
        }
        return out;
    }
}
