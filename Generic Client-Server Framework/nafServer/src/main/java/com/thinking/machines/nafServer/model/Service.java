package com.thinking.machines.nafServer.model;
import java.lang.reflect.*;
public class Service
{
private Module module;
private Method method;
private int [] sessionParametersIndexes;
private int [] applicationParametersIndexes;
private boolean injectSession;
private boolean isVoid;
private boolean isStatic;
private boolean injectApplication;
private int numberOfParameters;
public Module getModule()
{
return  module;
}
public Method getMethod()
{
return this.method;
}
public int [] getSessionParametersIndexes()
{
return this.sessionParametersIndexes;
}
public int [] getApplicationParametersIndexes()
{
return this.applicationParametersIndexes;
}
public void setModule(Module module)
{
this.module=module;
}
public void setMethod(Method method)
{
this.method=method;
}
public void setSessionParametersIndexes(int [] sessionParametersIndexes)
{
this.sessionParametersIndexes=sessionParametersIndexes;
}
public void setApplicationParametersIndexes(int [] applicationParametersIndexes)
{
this.applicationParametersIndexes=applicationParametersIndexes;
}
public void setInjectSession(boolean injectSession)
{
this.injectSession=injectSession;
}

public void setIsVoid(boolean isVoid)
{
this.isVoid=isVoid;
}

public void setInjectApplication(boolean injectApplication)
{
this.injectApplication=injectApplication;
}

public void setNumberOfParameters(int numberOfParameters)
{
this.numberOfParameters=numberOfParameters;
}

public boolean getInjectSession()
{
return injectSession;
}

public boolean getIsVoid()
{
return isVoid;
}

public boolean getInjectApplication()
{
return injectApplication;
}

public int getNumberOfParameters()
{
return numberOfParameters;
}
public void setIsStatic(boolean isStatic)
{
this.isStatic=isStatic;
}
public boolean getIsStatic()
{
return this.isStatic;
}
}