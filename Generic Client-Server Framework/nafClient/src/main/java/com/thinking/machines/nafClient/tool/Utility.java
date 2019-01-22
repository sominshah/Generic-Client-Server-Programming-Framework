package com.thinking.machines.nafClient.tool;
import java.util.*;
import java.lang.reflect.*;
public class Utility
{
public static void copyObject(Object target,Object source)
{
Class sourceClass,targetClass;
targetClass=target.getClass();
sourceClass=source.getClass();
Method targetMethods[];
targetMethods=targetClass.getMethods();
Method sourceMethods[];
sourceMethods=sourceClass.getMethods();
LinkedList<Pair<Method,Method>> setterGetters=new LinkedList<Pair<Method,Method>>();
LinkedList<Method> sourceGetterMethods=new LinkedList<>();
for(Method method:sourceMethods)
{
if(isGetter(method))
{
System.out.println("Source getter method : "+method.getName());
sourceGetterMethods.add(method);
}
}
String setterName,getterName;
Method getterMethod;
for(Method method:targetMethods)
{
if(!isSetter(method)) continue;
getterMethod=getGetterOf(method,sourceGetterMethods);
if(getterMethod!=null) setterGetters.add(new Pair(method,getterMethod));
}
// Information extraction about setter / getter complete
Class propertyType;
Object object;
for(Pair<Method,Method> pair:setterGetters)
{
System.out.println(pair.first.getName()+"---->"+pair.second.getName());
propertyType=pair.second.getReturnType();
if(isPrimitive(propertyType))
{
try
{
pair.first.invoke(target,pair.second.invoke(source));
}catch(IllegalAccessException iae){}
catch(InvocationTargetException ite){}
catch(Throwable t){}
continue;
}
if(isOneDimensionalArray(propertyType))
{
continue;
}
// do whatever remains
}
} // ObjectCopier ends
public static boolean isPrimitive(Class type)
{
return true;
}
public static boolean isOneDimensionalArray(Class type)
{
return false;
}
public static Method getGetterOf(Method setterMethod,LinkedList<Method> getterMethods)
{
String setterPropertyName="";
String setterName=setterMethod.getName();
Class setterPropertyType;
Class getterPropertyType;
if(setterName.length()>3) setterPropertyName=setterName.substring(3);
String getterName;
setterPropertyType=setterMethod.getParameterTypes()[0];
String getterPropertyName;
for(Method method:getterMethods)
{
getterPropertyName="";
getterName=method.getName();
if(getterName.length()>3) getterPropertyName=getterName.substring(3);
getterPropertyType=method.getReturnType();
if(setterPropertyName.equals(getterPropertyName) && setterPropertyType.equals(getterPropertyType))
{
return method;
}
}
return null;
}
public static boolean isSetter(Method method)
{
return method.getName().startsWith("set") && method.getParameterCount()==1;
}
public static boolean isGetter(Method method)
{
if(method.getName().startsWith("get")==false) return false;
if(method.getReturnType().getSimpleName().toUpperCase().equals("VOID")) return false;
if(method.getParameterCount()>0) return false;
return true;
}
}
