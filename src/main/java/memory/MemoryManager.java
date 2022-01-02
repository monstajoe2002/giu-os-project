package memory;

import java.io.*;
import java.util.*;

public class MemoryManager {


    //create parameters for all 3 files
    private File InfoFile;
    private File PhyicalMemoryFile;
    private File VirtMemoryFile;
    private static Stack<Object> memStack = new Stack<>();
    static HashMap<Integer, String> memoryPhysical;
    static String[] memoryVirtual = new String[10];
    static String[][] info;


    /*constructor initializes all 3 variables
         with their respective files*/
    public MemoryManager(String info, String physMemory, String virtMemory) {
        InfoFile = new File(info);
        PhyicalMemoryFile = new File(physMemory);
        VirtMemoryFile = new File(virtMemory);
    }

    public File getInfoFile() {
        return InfoFile;
    }

    public File getMemoryFile() {
        return PhyicalMemoryFile;
    }

    public File getVirtualMemoryFile() {
        return VirtMemoryFile;
    }

    public void setInfoFile(String infoFile) {
        InfoFile = new File(infoFile);
    }

    public void setMemoryFile(String memoryFile) {
        PhyicalMemoryFile = new File(memoryFile);
    }

    public void setVirtualMemoryFile(String virtMemoryFile) {
        VirtMemoryFile = new File(virtMemoryFile);
    }

    public void createOutputFile() {
        File output = new File("src\\main\\java\\memory\\OUTPUT.txt");
        try {
            if (output.createNewFile()) {
                System.out.println("File created successfully");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String s) {
        try {
            FileWriter fileWriter = new FileWriter("src\\main\\java\\memory\\OUTPUT.txt");
            fileWriter.write(s);
            fileWriter.close();
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public String readInfo() throws IOException {

        String data = "";


        if (InfoFile.exists()) {
            try {
                Scanner myReader = new Scanner(InfoFile);
                while (myReader.hasNextLine()) {
                    data += myReader.nextLine() + "\n";
                }
                myReader.close();
                String[] temp = data.split("(\n)");
                info = new String[temp.length][];
                for (int i = 0; i < temp.length; i++) {
                    info[i] = temp[i].split(" ");
                }

            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        return data;
    }

    public String readPhysicalMemory() {

        String data = "";
        String[] temp;
        if (PhyicalMemoryFile.exists()) {
            try {
                Scanner myReader = new Scanner(PhyicalMemoryFile);
                while (myReader.hasNextLine()) {
                    data += myReader.nextLine() + "\n";
                }

                myReader.close();

                temp = data.split("\n"); //split the data string by line

                for (int i = 0; i < temp.length; i++) {
                    createPhysicalMemory(temp[i]);
                    /*method call to give every
                     element in the temp array a
                    key-value pair by splitting both
                    into 2 substrings*/
                    //e.g. ["54286 inc %71263"] becomes {54286= inc %71263}

                }


            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return data;
    }

    private static HashMap<Integer, String> createPhysicalMemory(String s) {
        int splitIndex = s.indexOf(" ");
        String key = s.substring(0, splitIndex);
        String value = s.substring(splitIndex);
        memoryPhysical = new HashMap<>();
        memoryPhysical.put(Integer.parseInt(key), value);
//        System.out.println(memoryPhysical);
        return memoryPhysical;
    }


    public String readVirtMemory() {
        String data = "";

        if (VirtMemoryFile.exists()) {
            try {
                Scanner myReader = new Scanner(VirtMemoryFile);
                while (myReader.hasNextLine()) {
                    data += myReader.nextLine() + "\n";
                }

                myReader.close();
            } catch (FileNotFoundException e) {

            }

        }
        memoryVirtual = data.split("\n");


        return data;
    }

    public void intializeMemory() throws IOException {

        boolean flag = true;
        readInfo();
        readVirtMemory();
        readPhysicalMemory();
        getPhysicalAddress(2,Integer.parseInt(memoryVirtual[0].substring(3)));
    }

    public void getPhysicalAddress(int index, int virtualAddress) {
         int physicalAddress = -1;
         physicalAddress =virtualAddress+Integer.parseInt(info[index][info[index].length-1]); //base is the last element of the nth subarray
         System.out.println(physicalAddress);
    }

    //driver main() method,
    // create two instances of the class to test both examples
    public static void main(String[] args) throws IOException {
        MemoryManager memoryManager1 = new MemoryManager("src\\main\\java\\memory\\test1\\INFOFILE.txt",
                "src\\main\\java\\memory\\test1\\MEMORYFILE.txt",
                "src\\main\\java\\memory\\test1\\VIRTUALMEMORY.txt");
        MemoryManager memoryManager2 = new MemoryManager("src\\main\\java\\memory\\test2\\INFOFILE.txt",
                "src\\main\\java\\memory\\test2\\MEMORYFILE.txt",
                "src\\main\\java\\memory\\test2\\VIRTUALMEMORY.txt");
        memoryManager1.intializeMemory();


    }
}
