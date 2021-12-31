package memory;

import java.io.*;
import java.util.*;

public class MemoryManager {


    //create parameters for all 3 files
    private File InfoFile;
    private File PhyicalMemoryFile;
    private File VirtMemoryFile;
    private static Stack<Object> memStack = new Stack<>();
    private static Hashtable<Integer, String> memoryPhysical=new Hashtable<>();
    private static String[] info;

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
                info = data.split("(\n)|( )");

            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return data;
    }

    public String readPhysicalMemory() {

        String data = "";
        String [] temp;
        if (PhyicalMemoryFile.exists()) {
            try {
                Scanner myReader = new Scanner(PhyicalMemoryFile);
                while (myReader.hasNextLine()) {
                    data += myReader.nextLine() + "\n";
                }
                myReader.close();
                temp=data.split("\n");
                String[][] temp2=new String[temp.length][];
                for(int i=0; i<temp.length; i++)
                {
                    temp2[i]=temp[i].split("( )");
                }
                for(int i=0; i<temp2.length; i++)
                {
                   for(int j=1;j<temp2[i].length;j++)
                       memoryPhysical.put(Integer.parseInt(temp2[i][0]),temp2[i][j]);
                }
                System.out.println(memoryPhysical);

            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        return data;
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
        return data;
    }

    public void intializeMemory() throws IOException {
        readInfo();
        readPhysicalMemory();

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
