package fina.student.securechat;

import android.util.Log;

import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;
import java.util.Map;

public class testAlg {


    testAlg() throws GeneralSecurityException {
        Map keyPair;
        keyPair=RSA.generateKeyPair();
        String publicKey = (String)keyPair.get("publicKey");
        String privateKey = (String)keyPair.get("privateKey");
        String key="hello";
        String msg="hai";
        String enckey=  RSA.encrypt(key,publicKey);
        String encmsg=AESCrypt.encrypt(key, msg);
        String deckey=RSA.decrypt(enckey,privateKey);


        String decmsg=AESCrypt.decrypt(deckey, encmsg);
        Log.d("testKey",decmsg);


    }
}
