package com.thinking.machines.nafCommon;
import java.io.*;
public class Response implements Serializable
{
private Object[] arguments;
private Object result;
private String exception;
private String error;
private boolean isVoid;
private boolean isException;
private boolean isError;
private boolean isSuccessful;
public Response()
{
this.arguments=null;
this.result=null;
this.exception=null;
this.error=null;
this.isVoid=false;
this.isException=false;
this.isError=false;
this.isSuccessful=false;
}
public void setException(String exception)
{
this.exception=exception;
}
public void setArguments(Object[] arguments)
{
this.arguments=arguments;
}
public void setResult(Object result)
{
this.result=result;
}

public void setError(String error)
{
this.error=error;
}

public void setIsVoid(boolean isVoid)
{
this.isVoid=isVoid;
}

public void setIsException(boolean isException)
{
this.isException=isException;
}

public void setIsError(boolean isError)
{
this.isError=isError;
}

public void setIsSuccessful(boolean isSuccessful)
{
this.isSuccessful=isSuccessful;
}

public String getException()
{
return exception;
}
public Object getResult()
{
return this.result;
}
public Object[] getArguments()
{
return this.arguments;
}

public String getError()
{
return error;
}

public boolean getIsVoid()
{
return isVoid;
}

public boolean getIsException()
{
return isException;
}

public boolean getIsError()
{
return isError;
}

public boolean getIsSuccessful()
{
return isSuccessful;
}
}