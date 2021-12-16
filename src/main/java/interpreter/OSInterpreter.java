package interpreter;
import java.io.*;
import java.util.*;

public class OSInterpreter {
    static String[] command;
    static File file;
    static StackObj stack;
    static String  value2 = "";
    static Object result;
    static String value = "";
    ArrayList <Object> args ;
    public OSInterpreter() {

        file = null;
        stack = new StackObj(5);
        args=new ArrayList<Object>();
    }

    public static void print(Object x) {
    		String str = "";
    		if(value==""){
	    		for(int i=1;i<command.length;i++)
	    		{
	    			str+=command[i] + " ";
	    		}
	    		value = str;
    		}
    		System.out.print(value);
    		value = "";
    }
    
    public static void assign(Object x, Object y) throws IncompatibleTypeException, IOException {

        if (y instanceof String){
        
           	 if(command[2].equals("readFile")){
           		value = readFile(command[3]);
           	 
           } 
           	 
           	 
           	 else if(command[2].equals("input")) {
           		
             	   Scanner sc = new Scanner(System.in);
             	  System.out.print(x+" = ");
             	   value2 = sc.nextLine();
             	   
             	   
                 }
           	 
           	 else {
            System.out.print(x+" = ");
            String str = "";
            for(int i=2; i<command.length; i++){
            	 str += command[i]+" ";
            }
            stack.push(str);
	        while(!stack.isEmpty()){
	           System.out.print(stack.top() + " ");
	           value = stack.pop()+" ";
	        }
            System.out.println();
           }
        }
    
        else
            throw new IncompatibleTypeException("\nIncompatible data type: " + x); 
    }
    public static void writeFile(String filename, Object data) throws IOException {
        file=new File("src/interpreter/"+value2);
        if (file.exists()) {
           if(value!=null)
           {
        	   try {
                   FileWriter writer = new FileWriter(file);
                   writer.write((String)value);
                   writer.close();
               } catch (IOException e) {
                   System.out.println("An error has occurred while writing");
               }
           }
        } else {
            System.out.println("File not found. Creating file...");
            if (file.createNewFile()){
                System.out.println("File created: " + file.getName());
            FileWriter writer = new FileWriter(file);
            writer.write((String)data);
            writer.close();
            }
        }
    }

    public static String readFile(String filename) throws IOException {
        file = new File("src/interpreter/" + value2);
        String data="";
        
        if (file.exists()) {
            try {
                Scanner myReader = new Scanner(file);
                while (myReader.hasNextLine()) {
                    data = myReader.nextLine();
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

        } else{
            System.out.println("File not found.");
            if (file.createNewFile())
                System.out.println("File created: " + file.getName());
        
        }
        return data;
    }
    
    public void input() throws IOException,IncompatibleTypeException{
        Scanner scanner = new Scanner(System.in);
        String input = "";
        Object value = null;
        QueueObj queue = new QueueObj(4);
            while (!input.equals("exit")) {
            command = input.split(" ");
            if (command[0].equals("print")) {
                for (int i = 1; i < command.length; i++) {
                    queue.enqueue(command[i]);
                    print(queue.dequeue() + " ");
                }
                if(command[1].contains(".txt"))
                    {
                        System.out.println(readFile(command[1]));
                    }
                System.out.println("");
            } else if (command[0].equals("assign")) {
                args.add(command[1]);
                args.add(command[2]);
                if (args.get(1).equals("input")) {
                    value=scanner.nextLine();
                    args.remove(1);
                    args.add(value);
                    assign(command[1],args.get(1));
                }
                else if (command[2].equals("readFile")) {
                    value=readFile((String)command[3]);
                    assign(command[1],value);
                }
                else 
                    assign(command[1],command[2]);
            } else if (command[0].equals("writeFile")) {
                writeFile(command[1], command[2]);
            } else if (command[0].equals("readFile")) {
                readFile(command[1]);
            }
            input = scanner.nextLine();
            
        }
        
        scanner.close();
    }
    
    public static void main(String[] args) throws IOException, IncompatibleTypeException{
        OSInterpreter interpreter = new OSInterpreter();
        interpreter.input();

    }
}
