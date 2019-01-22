package com.thinking.machines.nafServer.model;
import com.thinking.machines.nafCommon.*;
import java.util.*;
public class Module
{
private boolean isSessionAware;
private boolean isApplicationAware;
private Class serviceClass;
private LinkedList<Property> autoWiredProperties;
public Module()
{
}
public void setIsSessionaware(boolean isSessionAware)
{
this.isSessionAware=isSessionAware;
}

public void setIsApplicationAware(boolean isApplicationAware)
{
this.isApplicationAware=isApplicationAware;
}
public void setServiceClass(Class serviceClass)
{
this.serviceClass=serviceClass;
}
public void setAutoWiredProperties(LinkedList<Property> autoWiredProperties)
{
this.autoWiredProperties=autoWiredProperties;
}
public LinkedList<Property> getAutoWiredProperties()
{
return this.autoWiredProperties;
}
public Class getServiceClass()
{
return this.serviceClass;
}

public boolean getIsSessionAware()
{
return isSessionAware;
}

public boolean getIsApplicationAware()
{
return isApplicationAware;
}
}