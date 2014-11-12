package edu.sjsu.cmpe.cache.client;

import java.util.ArrayList;
import java.util.List;

import com.google.common.hash.Hashing;

public class Client {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting Cache Client...");
        
       //adding all servers
        List<DistributedCacheService> Servers = new ArrayList<DistributedCacheService>();
      Servers.add(new DistributedCacheService("http://localhost:3000"));
      Servers.add(new DistributedCacheService("http://localhost:3001"));
      Servers.add(new DistributedCacheService("http://localhost:3002"));

   char[] arr ={'0', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'};
   
   
 
   System.out.println("\n Adding data:");

   for(int j=1;j<=([arr.lenth]-1);j++)
   {
   
   int x = Hashing.consistentHash(Hashing.md5().hashInt(j), Servers.size());
   Servers.get(x).put(j,Character.toString(arr[j]));
   //server.get(bucket).put(putkey, value[putkey]);
   
   System.out.println("PUT --> Key=" + j + " and Value="+ arr[j] + " routed to Cache server at localhost://300"+ x);
   }

  
   //GET data from Cache servers consistent hashing
  System.out.println("\n GET data from Cache... ");

  for(int j=1;j<11;j++)
  {
   int x=Hashing.consistentHash(Hashing.md5().hashInt(j), Servers.size());
       System.out.println("The key value pair " + j +"-" + Servers.get(x).get(j)+ " is received to server " + x);
    }
}
    
}
