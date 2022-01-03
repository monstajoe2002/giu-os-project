package memory;

import java.io.*;
import java.util.*;

public class MemoryManager {


    //create parameters for all 3 files
    private File InfoFile;
    private File PhyicalMemoryFile;
    private File VirtMemoryFile;
    private File output;
    private static Stack<Object> memStack = new Stack<>();
    static HashMap<Integer, String>  memoryPhysical;
    ;
    static String[] memoryVirtual = new String[]{};
    static String[][] info;


    /*constructor initializes all 3 variables
         with their respective files*/
    public MemoryManager(String info, String physMemory, String virtMemory) {
        InfoFile = new File(info);
        PhyicalMemoryFile = new File(physMemory);
        VirtMemoryFile = new File(virtMemory);
        memoryPhysical = new HashMap<>();
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
        output = new File("src\\main\\java\\memory\\OUTPUT.txt");
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
            FileWriter fileWriter = new FileWriter(output, true);
            fileWriter.write(s + "\n");
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

        memoryPhysical.put(Integer.parseInt(key), value);
//        System.out.println(memoryPhysical);
        return memoryPhysical;
    }
    private static HashMap<Integer, String> createPhysicalMemory() {

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

    public void run() throws IOException {
        String s,out = "";
        int oldVal,newVal=-1;
        readInfo();
        readVirtMemory();
        readPhysicalMemory();
        createOutputFile();
        createPhysicalMemory();
        if(output.exists())
        {
            output.delete();
        }
        else
            createOutputFile();

        for (int i = 1; i < info.length; i++) {
            for (int j = 0; j<memoryVirtual.length; j++) {
                s = memoryPhysical.get(getPhysicalAddress(i, Integer.parseInt(memoryVirtual[i].substring(3))));
                out="FETCH VIRTUAL MEMORY " + memoryVirtual[j] + "-> PHYSICAL MEMORY " + getPhysicalAddress(i, Integer.parseInt(memoryVirtual[j].substring(3))) +
                        "\n" + "EXECUTE" + s+"\n";

                String [] execution=s.split("( )|(,)");
                if(info[i-1][i].startsWith("CODE_SEGMENT"))
                {
                    if (s.contains("mov"))
                    {
                        if(execution[execution.length-1].contains("%"))
                        {
                            out+="RESULT"+memoryPhysical.get(Integer.parseInt(execution[execution.length-1].substring(1)))+ " STORED AT LOCATION "+execution[2]+"\n";
                        }
                        else
                            out+="RESULT"+execution[execution.length-1]+ " STORED AT LOCATION "+execution[2]+"\n";
                    }
                    else if (s.contains("add"))
                    {

                        oldVal=Integer.parseInt(memoryPhysical.get(execution[execution.length-1].substring(1)));
                        newVal=oldVal+Integer.parseInt(execution[execution.length-1]);
                        out+="RESULT "+Integer.toString(newVal)+ " STORED AT LOCATION "+execution[2]+"\n";
                    }
                    else if (s.contains("sub"))
                    {
                        oldVal=Integer.parseInt(memoryPhysical.get(execution[execution.length-1].substring(1)));
                        newVal=oldVal-Integer.parseInt(execution[execution.length-1]);
                        out+="RESULT "+Integer.toString(newVal)+ " STORED AT LOCATION "+execution[2]+"\n";
                    }
                    else if (s.contains("inc"))
                    {
                        oldVal=Integer.parseInt(memoryPhysical.get(execution[execution.length-1].substring(1)));
                        newVal=oldVal++;
                        out+="RESULT "+Integer.toString(newVal)+ " STORED AT LOCATION "+execution[2]+"\n";
                    }
                    else if (s.contains("dec"))
                    {
                        oldVal=Integer.parseInt(memoryPhysical.get(execution[execution.length-1].substring(1)));
                        newVal=oldVal--;
                        out+="RESULT "+Integer.toString(newVal)+ " STORED AT LOCATION "+execution[2]+"\n";
                    }
                }
                writeToFile(out);

            }

        }



    }

    public int getPhysicalAddress(int index, int virtualAddress) {
        int physicalAddress = -1;
        physicalAddress = virtualAddress + Integer.parseInt(info[index][info[index].length - 1]); //base is the last element of the nth subarray
        return physicalAddress;
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

    }
}