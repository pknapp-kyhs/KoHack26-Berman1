package Perek;
public class Perek {
    Version[] versions;
    public String toString() {
        String out = "";
        for (Version version : versions) {
            out += version + "\n";
        }
        return out;
    }
}
