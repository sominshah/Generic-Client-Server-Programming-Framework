package com.thinking.machines.nafCommon;
import java.io.*;
public class Request implements Serializable
{
private String path;
private Object[] arguments;
private String clientId;
public Request()
{
this.path=null;
this.arguments=null;
this.clientId=null;
}
public void setPath(String path)
{
this.path=path;
}
public void setArguments(Object[] arguments)
{
this.arguments=arguments;
}

public void setClientId(String clientId)
{
this.clientId=clientId;
}

public String getPath()
{
return this.path;
}
public Object[] getArguments()
{
return this.arguments;
}

public String getClientId()
{
return this.clientId;
}
}