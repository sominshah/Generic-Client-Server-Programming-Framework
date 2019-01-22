package com.thinking.machines.nafServer;
import com.thinking.machines.nafServer.model.*;
import com.thinking.machines.nafServer.tool.*;
import java.net.*;
public class TMNAFServer 
{
private Application application;
public TMNAFServer()
{
initialize();
}
private void initialize()
{
application=ApplicationUtility.getApplication();
}
public void startServer()
{
startServer(3000);
}
public void startServer(int portNumber)
{
try
{
ServerSocket sk=new ServerSocket(portNumber);
System.out.println("Server is Listening on Port :"+portNumber);
Socket ck=null;
while(true)
{
ck=sk.accept();
new RequestProcessor(ck,application);
}
}catch(Exception e)
{
System.out.println(e);
} 

}
}