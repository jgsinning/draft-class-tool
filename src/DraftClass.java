import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DraftClass {

    private byte[] bytes;
    private List<Player> players;

    public DraftClass() {
        bytes = null;
        players = null;
    }

    public void loadClassFromCSV(String path) throws IOException {
        try {
            players = new ArrayList<Player>();
            FileReader fr = new FileReader(path);
            CSVReader csvReader = new CSVReader(fr);
            List<String[]> records = csvReader.readAll();

            // check if header incorrect
            if (!Arrays.equals(records.get(0), getCSVHeader())) {
                throw new Exception("CSV header incorrectly formatted");
            }
            // check length requirement
            if (records.size() > 451) {
                throw new Exception("CSV has over 450 players");
            }
            // add players to draft class
            for (int i = 1; i < records.size(); i++) {
                players.add(new Player(records.get(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // loads draft class from draft class file at path
    public void loadClassFromFile(String path) throws IOException {
        // read the draft class file byte by byte
        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch(Exception e) {
            e.printStackTrace();
        }
        players = new ArrayList<Player>();

        // get all 450 players as objects
        int i = 70; // first 70 bytes (0-69) are (probably) useless
        while (i < bytes.length - 1680) {   // 1680 bytes at the end of the file
            players.add(getPlayerObject(i));
            i += 336;   // each player is 336 bytes
        }
    }

    // return a Player from the 336 bytes starting at index
    public Player getPlayerObject(int index) {
        String[] names = handleNames(index);
        int[] attrs = getAttributes(index);

        Player tempPlayer = new Player(names[0], names[1]);
        tempPlayer.updateAttributes(attrs);
        tempPlayer.setPosition(bytes[index + 74]);  // position is 74 bytes after first letter of first name
        tempPlayer.setProjection(getProjection(index));
        tempPlayer.setAgeHtWt(getAgeHtWt(index));
        tempPlayer.setDev(bytes[index + 165]);  // dev trait is 165 bytes after
        tempPlayer.updateOvr();

        return tempPlayer;
    }

    // return [firstName, lastName] of player at index
    public String[] handleNames(int index) {
        // copy over first name bytes to string
        byte[] firstNameBytes = new byte[13];
        System.arraycopy(bytes, index, firstNameBytes, 0, 13);
        String firstName = new String(firstNameBytes);

        // copy over last name bytes to string
        byte[] lastNameBytes = new byte[15];
        System.arraycopy(bytes, index + 17, lastNameBytes, 0, 15);
        String lastName = new String(lastNameBytes);

        return new String[]{firstName, lastName};
    }

    // all 54 attributes in order (with 2 blank spaces)
    public int[] getAttributes(int index) {
        byte[] attrsByte = new byte[56];
        // 82 after index
        System.arraycopy(bytes, index + 82, attrsByte, 0, 56);
        return byteArrToIntArr(attrsByte);
    }

    // returns [round, pick] of player
    public int[] getProjection(int index) {
        byte[] proj = new byte[2];
        proj[0] = bytes[index + 78];
        proj[1] = bytes[index + 80];
        return byteArrToIntArr(proj);
    }

    // returns player's age, height, and weight
    public int[] getAgeHtWt(int index) {
        byte[] arr = new byte[3];
        System.arraycopy(bytes, index + 70, arr, 0, 3);
        return byteArrToIntArr(arr);
    }

    // returns the header of the CSV file
    public String[] getCSVHeader() {
        String[] header = {
                "FIRST", "LAST", "POS", "PROJ", "HT", "WT", "AGE", "M16OVR", "DEV", "AWR", "SPD", "ACC", "AGI", "STR",
                "CAR", "BCV", "COD", "JKM", "SPM", "SFA", "TRK", "BTK", "CTH", "CIT", "SPC", "SRR", "MRR", "DRR", "RLS",
                "JMP", "THP", "SAC", "MAC", "DAC", "RUN", "PAC", "TUP", "BSK", "PBK", "PBF", "PBP", "RBK", "RBF", "RBP",
                "IBL", "LBK", "PRC", "TAK", "POW", "BSH", "FMV", "PMV", "PUR", "MCV", "ZCV", "PRS", "RET", "KPW", "KAC",
                "STA", "TGH", "INJ", "PER", "UNKNOWN1", "UNKNOWN2"
        };
        return header;
    }

    // turns a loaded draft class into a CSV file
    public void classToCSV(String path) throws IOException {
        File file = new File(path);
        try {
            FileWriter outputFile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputFile, ',',
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.NO_ESCAPE_CHARACTER,
                    System.getProperty("line.separator"));
            writer.writeNext(getCSVHeader());
            for (Player p : players) {
                writer.writeNext(p.csvLine());
            }
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void classToFile(String templatePath, String classPath) throws IOException {
        try {
            bytes = Files.readAllBytes(Paths.get(templatePath));
            int i = 70;
            int j = 0;
            while (j < 450) {
                Player tempPlayer = players.get(j);
                handleNameBytes(i, tempPlayer.firstName, tempPlayer.lastName);  // name
                bytes[i + 74] = (byte)Integer.parseInt(tempPlayer.position);    // position
                // age height weight
                bytes[i + 70] = (byte)tempPlayer.age;
                bytes[i + 71] = (byte)tempPlayer.height;
                // TODO: check indices of height and weight
                // height is accurate, weight is not
                if (tempPlayer.weight > 160 + 127) {
                    bytes[i + 72] = (byte)(tempPlayer.weight - 160 - 128 - 32);
                } else {
                    bytes[i + 72] = (byte)(tempPlayer.weight - 160 - 32);
                }
                // dev
                bytes[i + 165] = (byte)(Integer.parseInt(tempPlayer.dev));
                // attributes
                handleAttributeBytes(i, tempPlayer.getAttributes());
                i += 336;
                j++;
            }
            File output = new File(classPath);
            FileOutputStream outputStream = new FileOutputStream(output);
            outputStream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void handleNameBytes(int index, String first, String last) {
        char[] firstChar = first.toCharArray();
        char[] lastChar = last.toCharArray();
        char[] firstCharFull = new char[13];
        char[] lastCharFull = new char[15];
        System.arraycopy(firstChar, 0, firstCharFull, 0, firstChar.length);
        System.arraycopy(lastChar, 0, lastCharFull, 0, lastChar.length);
        for (int i = 0; i < 13; i++) {
            bytes[index + i] = (byte)firstCharFull[i];
        }
        for (int i = 0; i < 15; i++) {
            bytes[index + 17 + i] = (byte)lastCharFull[i];
        }
    }

    public void handleAttributeBytes(int index, int[] attrs) {
        for (int i = 0; i < attrs.length; i++) {
            bytes[index + 82 + i] = (byte)attrs[i];
        }
    }

    // Converts elements of byte array to int array
    public int[] byteArrToIntArr(byte[] byteArr) {
        int[] intArr = new int[byteArr.length];
        for (int i = 0; i < byteArr.length; i++) {
            intArr[i] = byteArr[i];
        }
        return intArr;
    }

}
