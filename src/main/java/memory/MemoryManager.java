package memory;

import java.io.*;
import java.util.Scanner;

public class MemoryManager {


    //create parameters for all 3 files
    private File INFO_FILE;
    private File MEMORY_FILE;
    private File VIRTUAL_MEMORY_FILE;

    /*constructor initializes all 3 variables
         with their respective files*/
    public MemoryManager(String info, String physMemory, String virtMemory) {
        INFO_FILE = new File(info);
        MEMORY_FILE = new File(physMemory);
        VIRTUAL_MEMORY_FILE = new File(virtMemory);
    }

    public File getInfoFile() {
        return INFO_FILE;
    }

    public File getMemoryFile() {
        return MEMORY_FILE;
    }

    public File getVirtualMemoryFile() {
        return VIRTUAL_MEMORY_FILE;
    }

    public void setInfoFile(String infoFile) {
        INFO_FILE = new File(infoFile);
    }

    public void setMemoryFile(String memoryFile)  {
        MEMORY_FILE = new File(memoryFile);
    }

    public void setVirtualMemoryFile(String virtMemoryFile)  {
        VIRTUAL_MEMORY_FILE = new File(virtMemoryFile);
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
        MemoryManager memoryManager1 = new MemoryManager("src\\main\\java\\memory\\test1\\INFOFILE.txt",
                "src\\main\\java\\memory\\test1\\MEMORYFILE.txt",
                "src\\main\\java\\memory\\test1\\VIRTUALMEMORY.txt");
        MemoryManager memoryManager2 = new MemoryManager("src\\main\\java\\memory\\test2\\INFOFILE.txt",
                "src\\main\\java\\memory\\test2\\MEMORYFILE.txt",
                "src\\main\\java\\memory\\test2\\VIRTUALMEMORY.txt");


    }
}
