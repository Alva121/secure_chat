package fina.student.securechat;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import static android.content.Context.MODE_PRIVATE;

public class utils {
    private static final utils ourInstance = new utils();

    public static utils getInstance() {
        return ourInstance;
    }

    private static Map keyPair;
    public static final String IP = "http://192.168.43.222/secure_chat/api.php";
  //  public static final String IP = "http://192.168.43.141/secure_chat/api.php";
    public static final String TYPE1 = "?type=1";
    public static final String TYPE2 = "?type=2";
    public  String CUSER="";
    public  String FUSER="";
    public static final String TYPE4 = "?type=4";
    public static final String TYPE3 = "?type=3";
    public static final String TYPE5 = "?type=5";
    public ArrayList<Friend> friends = new ArrayList<>();
    public String publicKey = "";
    public String privateKey = "";
    public ArrayList<chat>chatme=new ArrayList<>();
    public ArrayList<chat>chatALL=new ArrayList<>();

    private utils() {

    }

    String generateKey() {//https://gist.github.com/balzss/a287b7ef1e7b6abcf069d522dcc53ffc
        return UUID.randomUUID().toString();
    }

    String encKey(String key) {
        try {

            return RSA.encrypt(key, utils.getInstance().publicKey);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    String decKey(String enckey) {
        try {

            return RSA.decrypt(enckey, utils.getInstance().privateKey);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void init() {

        try {
            keyPair=RSA.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        utils.getInstance().privateKey= (String)keyPair.get("privateKey");
   utils.getInstance().publicKey= (String)keyPair.get("publicKey");
        Log.d("private", privateKey);
        Log.d("public", publicKey);


    }

    void savePrivateKey(String privateKey, Context context) {
        SharedPreferences sharedPreferences
                = context.getSharedPreferences("mydata",
                MODE_PRIVATE);

// Creating an Editor object
// to edit(write to the file)
        SharedPreferences.Editor myEdit
                = sharedPreferences.edit();

// Storing the key and its value
// as the data fetched from edittext
        myEdit.putString(
                "key",
               utils.getInstance().privateKey);


// Once the changes have been made,
// we need to commit to apply those changes made,
// otherwise, it will throw an error
        myEdit.commit();
    }


    String getPrivateKey(Context context) {

// Retrieving the value using its keys
// the file name must be same in both saving
// and retrieving the data
        SharedPreferences sh
                = context.getSharedPreferences("mydata",MODE_PRIVATE);

// The value will be default as empty string
// because for the very first time
// when the app is opened,
// there is nothing to show
        String s1 = sh.getString("key", "");
        return s1;
    }

    void writechat(Context context)  {

        SharedPreferences sharedPreferences
                = context.getSharedPreferences("mydata",
                MODE_PRIVATE);

// Creating an Editor object
// to edit(write to the file)
        SharedPreferences.Editor myEdit
                = sharedPreferences.edit();

// Storing the key and its value
// as the data fetched from edittext

        Gson j=new Gson();
       String data= j.toJson(utils.getInstance().chatme);
        myEdit.putString(
                "chat",
               data);
// Once the changes have been made,
// we need to commit to apply those changes made,
// otherwise, it will throw an error
        myEdit.commit();
    }


   void readchat(Context context) {

// Retrieving the value using its keys
// the file name must be same in both saving
// and retrieving the data
        SharedPreferences sh
                = context.getSharedPreferences("mydata",MODE_PRIVATE);

// The value will be default as empty string
// because for the very first time
// when the app is opened,
// there is nothing to show


        String s1 = sh.getString("chat", "");

        Gson j=new Gson();
        utils.getInstance().chatme.clear();
        if(!s1.isEmpty())
        utils.getInstance().chatme.addAll(Arrays.asList(j.fromJson(s1,chat[].class)));

    }
}
