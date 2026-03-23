import java.io.IOException;
import java.util.Arrays;
import java.util.InputMismatchException;
import Perek.*;

//a class to do useful things specifc to chumash and the sefaria api more generally
public class Chumash {
    //a list of books in chumash that can be easily plugged into the api
    public static String[] books = {"Genesis", "Exodus", "Leviticus", "Numbers", "Deuteronomy"};
    //the hardcoded link to the api
    public static String URL = "https://www.sefaria.org/api/v3/texts";
    //the params to run the api with to get a single result for a passuk in english or hebrew, with just text
    public static String[][] englishParams = {{"return_format","text_only"},{"version","english"}};
    public static String[][] hebrewParams = {{"return_format","text_only"},{"version","hebrew"}};

    //a function to get a passuk (from chumash only), if passuk is 0 or less it will get a perek, if perek is 0 or less it will get the entire book
    public static String[][][] getPassuk(int book, int perek, int passuk) throws IOException {
        return getPassuk(books[book], perek, passuk);
    }

    //a more general function to get any passuk or source from sefaria. If passuk is <1 returns perek, if perek<1 returns whole sefer
    public static String[][][] getPassuk(String book, int perek, int passuk) throws IOException {
        String spot = makeSpot(book, perek, passuk);
        //pulls a full sefer (or perek in extra layer of array(or passuk in 2 layers of array)
        String[][] hebrew = Parser.parse(API.requestBody(URL, spot, hebrewParams),Perek.class).versions[0].text;
        String[][] english = Parser.parse(API.requestBody(URL, spot, englishParams),Perek.class).versions[0].text;
        //puts them together into one 3d array
        return processSefer(hebrew, english);
    }

    //a function to take in a hebrew and english passuk and make a 1d array representing the passuk in 2 languges
    public static String[] processPassuk(String hebrew, String english) {
        String[] out = {hebrew, english};
        return out;
    }
    //a function to take in a hebrew and english perek and make a 2d array representing the perek in 2 languges
    public static String[][] processPerek(String[] hebrew, String[] english) throws IOException {
        //checks that both perakim are the smae length
        if (hebrew.length != english.length) {
            throw new IOException("Sefaria returned 2 different perek lengths");
        } else {
            //makes a 2d array with an array of length 2 for each passuk in the perek
            String[][] out = new String[hebrew.length][2];
            //fills in the output array
            for (int i = 0; i < hebrew.length; i++) {
                out[i] = processPassuk(hebrew[i], english[i]);
            }
            return out;
        }
    }
    //a function to take in a hebrew and english sefer and make a 3d array representing the sefer in 2 languges
    public static String[][][] processSefer(String[][] hebrew, String[][] english) throws IOException {
       //makes sure that both have the same number of perakim
        if (hebrew.length != english.length) {
            throw new IOException("sefaria provided 2 different sefer lengths");
        } else {
            //makes a new 3d array with a 2d perek array for each perek
            String[][][] out = new String[hebrew.length][][];
            //uses the processPerek function to fill in the sefer array
            for (int i = 0; i < hebrew.length; i++) {
                out[i] = processPerek(hebrew[i], english[i]);
            }
            return out;
        }
    }

    //makes a string representing the parameter for location in the sefaria api
    public static String makeSpot(String book, int perek, int passuk) {
        String out = book;
        //checks if perek exists (is>0), if not, just returns the name of sefer
        if (perek > 0) {
            //if it does exist, it adds the perek after a space (represented by %20)
            out += "%20" + perek;
            //checks if passuk exists (is>0), if not, just returns the perek location
            if (passuk > 0) {
                //adds the passuk number, after a colon
                out += ":" + passuk;
            }
        }
        return out;
    }
}
