package com.library;
import com.thinking.machines.nafServer.annotation.*;
import com.thinking.machines.nafCommon.*;
@Path("/serviceA")
public class ServiceA
{
@Path("/whatever")
public String getWhatever()
{
return "Whatever";
}
@Path("/add")
public void add(int a,int b)
{
System.out.println(a+b);
}
@Path("/getProduct")
public int getProduct(int a,int b)
{
return a*b;
}
@Path("/getNum")
public static int getNum(int x,int y)
{
return x+y;
}
@Path("/doSomething")
public aaa doSomething(aaa a)
{
a.setX(80);
return a; 
}
@Path("/doSomethingGood")
public int [] doSomethingGood(int [] g)
{
for(int x=0;x<g.length;x++)g[x]=g[x]+1;
return g;
}
public int getDiff(int e,int f)
{
return e-f;
}
 }