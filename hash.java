import java.io.*;
import java.util.*;
import java.math.*;
import java.security.*;
//import java.nio.charset.StandardCharsets;
public class hash
{
	public static String getmd5(String input)
	{

		try{
			MessageDigest msgDst=MessageDigest.getInstance("MD5");
			byte[] msgArr=msgDst.digest(input.getBytes());       //from an input digest() and it returns an array of byte
			BigInteger bi = new BigInteger(1, msgArr);
			String hshtxt = bi.toString(16);  
			while (hshtxt.length() < 32)   
			{  
				hshtxt = "0" + hshtxt;  
			}  
			return hshtxt;  
			  
		}
		catch(NoSuchAlgorithmException abc)
		{
			throw new RuntimeException(abc);
		}
	}
	public static void main(String args[])
	{
		String str="Dhruv Gajjar 19DCE031!";
		String hash=getmd5(str);
		str="Dhruv Gajjar 19DCE031!";
		System.out.println("The HashCode Generated for "+str+" is "+hash);		
		System.out.println("   ");		
		System.out.println("Now lets observe the avalanche effect in the message by changing it littel bit!");
		String str1="Dhruv Gajjar 19DCE031.";
		String hash1=getmd5(str1);
		System.out.println("The HashCode Generated for "+str1+" is "+hash1);
		/////////////////////////////////////////////////////////////////////
		System.out.println("   ");
		String str2="Dhruv Gajjar 19DCE031";
		String hash2=getmd5(str2);
		System.out.println("The HashCode Generated for "+str2+" is "+hash2);
		/////////////////////////////////////////////////////////////////////
		System.out.println("   ");
		String str3="dhruv Gajjar 19DCE031.";
		String hash3=getmd5(str3);
		System.out.println("The HashCode Generated for "+str3+" is "+hash3);
		System.out.println("   ");
		
        System.out.println("Just look at the beautiful avalanche effect cause by MD5!!!");
	}
}