import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DraftClassTool {

    public static void main(String[] args) {
        handleCommand(args);
    }

    // handles the following usages:
    // save "classPath" "csvPath"
    // load "csvPath" "classPath"
    // all other inputs: prints usage info (help command)
    public static void handleCommand(String[] args) {
        // save command
        if (args.length == 3 && args[0].equalsIgnoreCase("export")) {
            handleExportCommand(args[1], args[2]);
        }
        // load command
        else if (args.length == 4 && args[0].equalsIgnoreCase("import")) {
            handleImportCommand(args[1], args[2], args[3]);
        }
        // invalid input
        else {
            handleHelpCommand();
        }
    }

    // handles exporting draft class to csv
    public static void handleExportCommand(String classPath, String csvPath) {
        try {
            DraftClass draftClass = new DraftClass();
            draftClass.loadClassFromFile(classPath);
            draftClass.classToCSV(csvPath);
            System.out.println("Successfully saved CSV file at " + csvPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // handles importing csv into a draft class
    public static void handleImportCommand(String csvPath, String templatePath, String classPath) {
        try {
            DraftClass draftClass = new DraftClass();
            draftClass.loadClassFromCSV(csvPath);
            draftClass.classToFile(templatePath, classPath);
            System.out.println("Successfully saved draft class file at " + classPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // handles unknown inputs
    public static void handleHelpCommand() {
        String help = """
                M22 Draft Class Reader/Writer
                Use the following commands as arguments
                \tie: java -jar DraftClassTool.jar export "C:\\class" "C:\\out.csv"
                
                To export a draft class to a CSV file:
                export "classPath" "csvPath"
                \tclassPath: location of draft class file to be converted
                \tcsvPath: location of CSV file to be created

                To import a CSV file into a draft class file:
                import "csvPath" "templatePath" "classPath"
                \tcsvPath: location of CSV file to be converted
                \ttemplatePath: location of any draft class file
                \t\tused as a template for player traits, appearance, equipment, etc
                \tclassPath: location of draft class file to be created

                All other inputs will print this message
                """;
        System.out.println(help);
    }

}
