import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exceptions.HostNotReachableException;


public class Host
{
    static void checkHostIsReachable(String hostName) throws IOException, HostNotReachableException
    {
        String command = "ping " + hostName;
        Process p = Runtime.getRuntime().exec(command);
        
        BufferedReader stdInput = new BufferedReader(new
             InputStreamReader(p.getInputStream()));

        BufferedReader stdError = new BufferedReader(new
             InputStreamReader(p.getErrorStream()));

        String s;
        // read the output from the command
        
        while ((s = stdInput.readLine()) != null) {
            
            if(s.contains("Reply from"))
            {
                break;
            }
            else
            {
                if(s.contains("timed out"))
                {
                    System.out.println(hostName);
                    break;
                }
            }
        }

         

    }
    
    public static void main(String[] args) throws HostNotReachableException, IOException
    {
        String hostName = "10.129.50.";
        for(int i=1;i<=255;i++)
        {
            String name = hostName + String.valueOf(i);
            checkHostIsReachable(name);
        }
    }
    
}
