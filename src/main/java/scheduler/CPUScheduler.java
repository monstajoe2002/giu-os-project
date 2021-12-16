package scheduler;
import java.util.LinkedList;
import java.util.Queue;

public class CPUScheduler {
    static String[] process,startTime,endTime;
    static int completion, arrival, turnAroundTime=completion-arrival;
    public static String [][] splitInput(String input)
    {
        String[] value = input.split(";");
        process=value[0].split(",");
        startTime = value[1].split(",");
        endTime = value[2].split(",");
        String [][] result = new String[][]{process,startTime,endTime};
        return result;
    }
    public static String Scheduler_FCFS(String input)
    {
        String result = "";
        String [][] e=splitInput(input);
        for(int i=0;i<process.length;i++)
        {
            if(i!=process.length-1)
                result+=process[i]+"("+endTime[i]+")"+",";

            else
                result+=process[i]+"("+endTime[i]+")";
        }
        return result;

    }
    public static void main(String[] args) {
        String test1=Scheduler_FCFS("A,B,C,D,E;0,2,4,5,8;3,6,4,5,2");
        String test2=Scheduler_FCFS("Hi,Bye;0,0;4,2");
        System.out.println(test1);
        System.out.println(test2);

    }
	public static String Scheduler_SJF(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	public static Object Scheduler_RR(String string) {
		// TODO Auto-generated method stub
		return null;
	}
}
