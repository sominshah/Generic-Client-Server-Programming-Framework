package com.thinking.machines.nafServer.tool;
import java.util.*;
public class ServiceMistake
{
private String method;
private List<String> mistakes;
public ServiceMistake(String method)
{
this.method=method;
this.mistakes=new LinkedList<>();
}
public String getMethodName()
{
return this.method;
}
public List<String> getMistakes()
{
return this.mistakes;
}
public void addMistake(String mistake)
{
this.mistakes.add(mistake);
}
}