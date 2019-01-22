package com.library;
import com.thinking.machines.nafServer.*;
import com.thinking.machines.nafCommon.*;
public class Main
{
public static void main(String gg[])
{
int portNumber=3000;
TMNAFServer server=new TMNAFServer();
server.startServer(portNumber);
}
}