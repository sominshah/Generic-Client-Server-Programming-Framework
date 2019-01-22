package com.Main;
import com.thinking.machines.nafClient.*;
import com.thinking.machines.nafCommon.*;
public class Main
{
public static void main(String hh[])
{
TMNAFClient tmNafClient=new TMNAFClient("localhost",3000);
try
{
/*
aaa a=(aaa) tmNafClient.process("/serviceA/doSomething",new aaa());
System.out.println(a.getX());
int i=(int) tmNafClient.process("/serviceA/getProduct",10,30);
System.out.println(i);
*/
int x[] =new int[5];
x[0]=21;
x[1]=22;
x[2]=23;
x[3]=24;
x[4]=25;
tmNafClient.process("/serviceA/doSomethingGood",x);
for(int f=0;f<x.length;f++)
{
System.out.println(x[f]);
}

}catch(ApplicationException app)
{
System.out.println(app);
}
}
}