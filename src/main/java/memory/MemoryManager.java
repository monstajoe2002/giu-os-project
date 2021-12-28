package memory;

import java.io.*;
import java.util.Scanner;

public class MemoryManager {
    //create parameters for all 3 files
    private  File INFO_FILE;
    private File MEMORY_FILE;
    private File VIRTUAL_MEMORY_FILE;

    /*constructor initializes all 3 variables
         with their respective files*/
    public MemoryManager(File INFO_FILE, File MEMORY_FILE, File VIRTUAL_MEMORY_FILE) {
        this.INFO_FILE = INFO_FILE;
        this.MEMORY_FILE = MEMORY_FILE;
        this.VIRTUAL_MEMORY_FILE = VIRTUAL_MEMORY_FILE;
    }

    public File createOutputFile() {
        return null;
    }

    public String readInfo() throws IOException {

        String data = "";

        if (INFO_FILE.exists()) {
            try {
                Scanner myReader = new Scanner(INFO_FILE);
                while (myReader.hasNextLine()) {
                    data = myReader.nextLine();
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return data;
    }

    public String readPhysicalMemory() {

        String data = "";

        if (MEMORY_FILE.exists()) {
            try {
                Scanner myReader = new Scanner(MEMORY_FILE);
                while (myReader.hasNextLine()) {
                    data = myReader.nextLine();
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return data;
    }

    public String readVirtMemory() {
        String data = "";

        if (VIRTUAL_MEMORY_FILE.exists()) {
            try {
                Scanner myReader = new Scanner(VIRTUAL_MEMORY_FILE);
                while (myReader.hasNextLine()) {
                    data = myReader.nextLine();
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return data;
    }

    //driver main() method,
    // create two instances of the class to test both examples
    public static void main(String[] args) {

    }
}
