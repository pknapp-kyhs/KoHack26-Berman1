import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import Perek.*;

public class Chumash {
    public static String[] books = {"Genesis", "Exodus", "Leviticus", "Numbers", "Deuteronomy"};
    public static String URL = "https://www.sefaria.org/api/v3/texts";
    public static String[][] englishParams = {{"return_format","text_only"},{"version","english"}};
    public static String[][] hebrewParams = {{"return_format","text_only"},{"version","hebrew"}};

    public static String[][][] getPassuk(int book, int perek, int passuk) throws IOException {
        return getPassuk(books[book], perek, passuk);
    }

    public static String[][][] getPassuk(String book, int perek, int passuk) throws IOException {
        String spot = makeSpot(book, perek, passuk);


        if (Arrays.asList(books).contains(book)) {
            String[][] hebrew = Parser.parse(API.requestBody(URL, spot, hebrewParams),Perek.class).versions[0].text;
            String[][] english = Parser.parse(API.requestBody(URL, spot, englishParams),Perek.class).versions[0].text;
            return processSefer(hebrew, english);
        } else {
            throw new InputMismatchException("Sefer Does Not Exist");
            throw new 
        }
    }

    public static String[] processPassuk(String hebrew, String english) {
        String[] out = {hebrew, english};
        return out;
    }
    public static String[][] processPerek(String[] hebrew, String[] english) throws IOException {
        if (hebrew.length != english.length) {
            throw new IOException("Sefaria returned 2 different perek lengths");
        } else {
            String[][] out = new String[hebrew.length][2];
            for (int i = 0; i < hebrew.length; i++) {
                out[i] = processPassuk(hebrew[i], english[i]);
            }
            return out;
        }
    }
    public static String[][][] processSefer(String[][] hebrew, String[][] english) throws IOException {
        if (hebrew.length != english.length) {
            throw new IOException("sefaria provided 2 different sefer lengths");
        } else {
            String[][][] out = new String[hebrew.length][][];
            for (int i = 0; i < hebrew.length; i++) {
                out[i] = processPerek(hebrew[i], english[i]);
            }
            return out;
        }
    }

    public static String makeSpot(String book,
        int perek, int passuk) {
        String out = book;
        if (perek > 0) {
            out += "%20" + perek;
            if (passuk > 0) {
                out += ":" + passuk;
            }
        }
        return out;
    }
}
