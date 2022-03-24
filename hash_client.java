import java.io.*;
import java.util.*;
import java.math.*;
import java.security.*;
import java.net.*;

//import java.nio.charset.StandardCharsets;
public class hash_client {
    public static String getmd5(String input) {

        try {
            MessageDigest msgDst = MessageDigest.getInstance("MD5");
            byte[] msgArr = msgDst.digest(input.getBytes()); // from an input digest() and it returns an array of byte
            BigInteger bi = new BigInteger(1, msgArr);
            String hshtxt = bi.toString(16);
            while (hshtxt.length() < 32) {
                hshtxt = "0" + hshtxt;
            }
            return hshtxt;

        } catch (NoSuchAlgorithmException abc) {
            throw new RuntimeException(abc);
        }
    }

    public static void main(String args[]) {
        try {
            int i1;
            BigInteger p, q, p_n, q_n, n, fi, e, e_, d, i;
            BigInteger mesg, cipher_text, plain_text, mesg2;
            BigInteger b = BigInteger.valueOf(0);
            BigInteger bi = BigInteger.valueOf(1);
            BigInteger bii = BigInteger.valueOf(2);
            // for euclidean algorithm.
            BigInteger r, t1, t2, t, qo;
            String serverName = "localhost";
            int port = 8088;

            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);
            System.out.println("Just connected to " + client.getRemoteSocketAddress());

            Scanner sc = new Scanner(System.in);
            DataInputStream in = new DataInputStream(client.getInputStream());
            e = new BigInteger(in.readUTF());
            n = new BigInteger(in.readUTF());
            System.out.print("Enter the message to encrypt: ");
            mesg = sc.nextBigInteger();
            mesg2 = mesg;
            String strpln = mesg2.toString(2);
            String hash4 = getmd5(strpln);
            System.out.println("  ");
            System.out.println("Our plain_text Entered is:" + mesg + " so it's calculated hash is " + hash4);
            cipher_text = mesg.modPow(e, n);
            System.out.println("----------------------------------");
            System.out.print("Cipher Text of the entered message: ");
            System.out.println(cipher_text);
            System.out.print("Cipher Text of the entered message with plain_text hash is :" + cipher_text);
            System.out.println(hash4);
            
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            out.writeUTF(cipher_text.toString());
            out.writeUTF(hash4);
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}