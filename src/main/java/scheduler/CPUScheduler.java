package scheduler;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class CPUScheduler {
    static String[] process, arrivalTimes, burstTimes;
    static String result;
    static int burst, arrival, turnAroundTime=burst-arrival;
    static Queue<String> processes = new LinkedList<String>();
    public static String [][] splitInput(String input)
    {
        String[] value = input.split(";");
        process=value[0].split(",");
        arrivalTimes = value[1].split(",");
        burstTimes = value[2].split(",");
        String [][] result = new String[][]{process, arrivalTimes, burstTimes};
        return result;
    }
    public static void enqueueProcesses()
    {
        for (String process : process) {
            processes.add(process);
        }
        System.out.println(processes);
    }
    public static String Scheduler_FCFS(String input)
    {
        result="";
        splitInput(input);
        enqueueProcesses();
        for(int i=0;i<process.length;i++)
        {
            if(i!=process.length-1)
                result+=process[i]+"("+ burstTimes[i]+")"+",";

            else
                result+=process[i]+"("+ burstTimes[i]+")";
        }

        processes.clear();
        return result;

    }
    public static void bubbleSJF(String[] arr)
    {
        String temp ="";
        for(int i=2;i<arr.length; i++)
        {
            for(int j=2;j<arr.length-1-i;j++){
                if (arr[j+1].compareTo(arr[j])<0) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }

            }
        }

    }
    public static String Scheduler_SJF(String input) {
        result="";
        splitInput(input);
        String newArr[] = new String[]{};
        String start=arrivalTimes[0];

        return result;
    }
    public static String Scheduler_RR(String input) {
        result="";
        splitInput(input);


        return result;

    }
    public static void main(String[] args) {
        String test1=Scheduler_FCFS("A,B,C,D,E;0,2,4,5,8;3,6,4,5,2");
        String test2=Scheduler_FCFS("Hi,Bye;0,0;4,2");
        String test3=Scheduler_SJF("Hi,Bye;0,0;4,2");
        String test4=Scheduler_SJF("A,B,C,D,E;0,2,4,5,8;3,6,4,5,2");
        String test5=Scheduler_RR("A,B,C,D,E;0,2,4,5,8;3,6,4,5,2");
        String test6=Scheduler_RR("Hi,Bye;0,0;4,2");
        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3);
        System.out.println(test4);
        System.out.println(test5);
        System.out.println(test6);


    }

}
