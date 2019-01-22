package com.thinking.machines.nafClient;
import com.thinking.machines.nafCommon.*;
import static com.thinking.machines.nafCommon.Protocol.*;
import java.util.*;
import java.net.*;
import java.io.*;
public class TMNAFClient
{
private String host;
private int portNumber;
private String path;
private Object [] arguments;
Response response;
Request request;
public TMNAFClient(String host,int portNumber)
{
this.host=host;
this.portNumber=portNumber;
}
public <E> Object process(String path,E ...k) throws ApplicationException
{
System.out.println("Prepareing Request from Client Side... ");
this.path=path;
this.arguments=k;
request=new Request();
request.setPath(path);
request.setArguments(arguments);
try
{
int bytesToSend;
int chunkSize=1024;
ByteArrayOutputStream baos=new ByteArrayOutputStream();
ObjectOutputStream oos=new ObjectOutputStream(baos);
oos.writeObject(request);
oos.flush();
byte requestBytes[]=baos.toByteArray();
int requestSize=requestBytes.length;
byte requestSizeInBytes []=new byte[4];
requestSizeInBytes[0]=(byte)(requestSize>>24);
requestSizeInBytes[1]=(byte)(requestSize>>16);
requestSizeInBytes[2]=(byte)(requestSize>>8);
requestSizeInBytes[3]=(byte)(requestSize);
Socket socket =new Socket(host,portNumber);
OutputStream os=socket.getOutputStream();
os.write(requestSizeInBytes,0,4);
os.flush();
InputStream is=socket.getInputStream();
byte ack[]=new byte[1];
int byteCount=is.read(ack);
if(ack[0]!=ACKNOWLEDGEMENT)throw new RuntimeException("Unable to receive acknoledgement");
bytesToSend=requestSize;
int i=0;
while(bytesToSend>0)
{
if(bytesToSend<chunkSize)chunkSize=bytesToSend;
os.write(requestBytes,i,chunkSize);
os.flush();
i=i+chunkSize;
bytesToSend-=chunkSize;
}
byteCount=is.read(ack);
if(ack[0]!=ACKNOWLEDGEMENT)throw new RuntimeException("Unable to receive acknowledgement");
byte [] responseLengthInBytes=new byte[4];
byteCount=is.read(responseLengthInBytes);
int responseLength;
responseLength=(responseLengthInBytes[0]&255)<<24|(responseLengthInBytes[1] &255)<<16|(responseLengthInBytes[2] &255)<<8|(responseLengthInBytes[3] &255);
ack[0]=ACKNOWLEDGEMENT;
os.write(ack,0,1);
os.flush();
System.out.println("Request Sent! ");
baos=new ByteArrayOutputStream();
byte chunk[]=new byte[1024];
int bytesToRead=responseLength;
while(bytesToRead>0)
{
byteCount=is.read(chunk);
if(byteCount>0)
{
baos.write(chunk,0,byteCount);
baos.flush();
}
bytesToRead-=byteCount;
}
os.write(ack,0,1);
os.flush();
byte responseBytes[]=baos.toByteArray();
ByteArrayInputStream bais=new ByteArrayInputStream(responseBytes);
ObjectInputStream ois=new ObjectInputStream(bais);
response=(Response)ois.readObject();
socket.close();
if(response.getIsException())throw new ApplicationException(response.getException());
return response.getResult();
}
catch(ClassNotFoundException cnfe)
{
System.out.println(cnfe.getMessage());
}
catch(IOException io)
{
System.out.println(io);
}
return null;
}
}