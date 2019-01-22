package com.library;
import com.thinking.machines.nafServer.annotation.*;
@Path("/ServiceB")
public class ServiceB
{
@Path("/Hello")
public String getHello()
{
return "Hello";
}
@Path("/Namastey")
public void getNamastey(String g)
{
System.out.println("Namastay "+g);
}
@Path("/GoodMorning")
public String getGoodMorning()
{
return "Good-Morning";
}
}