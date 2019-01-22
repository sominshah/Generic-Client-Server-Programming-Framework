package com.thinking.machines.nafServer;
import com.thinking.machines.nafServer.model.*;
import com.thinking.machines.nafServer.tool.*;
import com.thinking.machines.nafCommon.*;
import static com.thinking.machines.nafCommon.Protocol.*;
import java.io.*;
import java.lang.reflect.*;
import java.net.*;
public class RequestProcessor extends Thread
{
/**
*This class is created to process the requests of clients. This class extends Thread class to achieve multithreading.
*
*
**/

private Socket client;
private Application application;
	RequestProcessor(Socket ck,Application application)
	{
	this.client=ck;
	this.application=application;
	start();
	}
public void run()
{
	System.out.println("Request Arrived.");
	try
	{
	Object result=null;
	Service service=null;
	Module module=null;
	String path;
	Object[] arguments;
	Class serviceClass=null;
	Method method=null;
	InputStream is;
	Response response=new Response();
	Request request;
	OutputStream os;
	byte requestLengthInBytes[]=new byte[4];
	int requestLength;
	int bytesToRead;
	int bytesToWrite;
	byte ack[]=new byte[1];
	byte requestBytes[];
	byte responseBytes[];
	byte responseLengthInBytes[]=new byte[4];
	byte chunks[]=new byte[1024];
	ByteArrayInputStream bais;
	ByteArrayOutputStream baos;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	int responseLength;
	int chunkSize;
	is=client.getInputStream();
	baos=new ByteArrayOutputStream();
	byte b[]=new byte[1024];
	int byteCount;
	System.out.println("Data Fatching start");
		is=client.getInputStream();
		byteCount=is.read(requestLengthInBytes);
		requestLength=(requestLengthInBytes[0] & 0xFF) <<24 | (requestLengthInBytes[1] & 0xFF) <<16 | (requestLengthInBytes[2] & 0xFF) <<8 | (requestLengthInBytes[3] & 0xFF);
		ack[0]=ACKNOWLEDGEMENT;
		os=client.getOutputStream();
		os.write(ack,0,1);
		os.flush();
		baos=new ByteArrayOutputStream();
		bytesToRead=requestLength;
			while(bytesToRead>0)
			{
			byteCount=is.read(chunks);
				if(byteCount>0)
				{
				baos.write(chunks,0,byteCount);
				}
			bytesToRead-=byteCount;
			}
		ack[0]=ACKNOWLEDGEMENT;
		os.write(ack,0,1);
		os.flush();
		requestBytes=baos.toByteArray();
		bais=new ByteArrayInputStream(requestBytes);
		ois=new ObjectInputStream(bais);
		request=(Request)ois.readObject();
		path=request.getPath();
		arguments=request.getArguments();
				if(application.containsService(path))
				{
				try
				{
			                 service=application.getService(path);
			                 module=service.getModule();
				serviceClass=module.getServiceClass();
				}catch(ApplicationException ae)
					{
				                 System.out.println(ae);
					}
			if(service.getNumberOfParameters()==0)
			{	if(arguments!=null)
				{
				try
				{
				method=service.getMethod();
					if(service.getIsVoid())
                                                                    	{
					 method.invoke(serviceClass.newInstance());
					}else
					 {
					 result=method.invoke(serviceClass.newInstance());
					System.out.println("Result is :"+result);
					}
				response.setIsSuccessful(true);
				System.out.println(result);
				response.setResult(result);
				}
				catch(InstantiationException ie)
				{	
				System.out.println(ie);
				}
				catch(IllegalAccessException iae)
				{
				System.out.println(iae);
				}
				catch(InvocationTargetException ita)
				{
				System.out.println(ita);
				}
				}
				
			}//if ends
			else
			{	try
				{
				method=service.getMethod();	
			                		 if(service.getIsVoid())
                                                                  		  {
					method.invoke(serviceClass.newInstance(),arguments);
					}else
					{	 result=method.invoke(serviceClass.newInstance(),arguments);
					System.out.println("Result is :"+result);
					}
				response.setIsSuccessful(true);
				response.setResult(result);
				}
				catch(IllegalAccessException iaex)
				{
				System.out.println(iaex);
				}
				catch(InvocationTargetException ita)
				{
				System.out.println(ita);
				}
				catch(InstantiationException ie)
				{
				System.out.println(ie);
				}
			}//else ends
			}//if ends
		baos=new ByteArrayOutputStream();
		oos=new ObjectOutputStream(baos);
		oos.writeObject(response);
		oos.flush();
		responseBytes=baos.toByteArray();
		responseLength=responseBytes.length;
		responseLengthInBytes=new byte[4];
		responseLengthInBytes[0]=(byte)(responseLength>>24);
		responseLengthInBytes[1]=(byte)(responseLength>>16);
		responseLengthInBytes[2]=(byte)(responseLength>>8);
		responseLengthInBytes[3]=(byte)(responseLength);
		os.write(responseLengthInBytes,0,4);
		os.flush();
		byteCount=is.read(ack);
		if(ack[0]!=ACKNOWLEDGEMENT)throw new RuntimeException("Unable to receive acknoledgement");
		bytesToWrite=responseLength;
		chunkSize=1024;
		int i=0;
			while(bytesToWrite>0)
			{
				if(bytesToWrite<chunkSize)chunkSize=bytesToWrite;
				os.write(responseBytes,i,chunkSize);
			os.flush();
			i+=chunkSize;
			bytesToWrite-=chunkSize;
			}
		byteCount=is.read(ack);
		if(ack[0]!=ACKNOWLEDGEMENT)throw new RuntimeException("Unable to receive acknoledgement ");
		client.close();
		System.out.println("Response Sent!");
                                  System.out.println("-------------------------------------------------------");
		}catch(ClassNotFoundException cnfe)
		{
		System.out.println(cnfe);
		}
		catch(IOException e)
		{
		System.out.println(e);
		}
}//run
}//class