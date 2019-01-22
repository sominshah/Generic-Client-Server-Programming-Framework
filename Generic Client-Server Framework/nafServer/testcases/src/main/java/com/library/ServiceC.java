package com.library;
import com.thinking.machines.nafServer.annotation.*;
@Path("/serviceC")
public class ServiceC
{
@Path("/calculation")
public float getCalculation(int x,float f)
{
return x+f;
}
}