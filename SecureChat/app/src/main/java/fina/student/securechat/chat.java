package fina.student.securechat;

import com.scottyab.aescrypt.AESCrypt;

import java.io.Serializable;
import java.security.GeneralSecurityException;

public class chat  implements Serializable {

    public String getKey() {
        return key;
    }

    public boolean isIsread() {
        return isread;
    }

    public void setIsread(boolean isread) {
        this.isread = isread;
    }

    private boolean isread;
    public void setKey(String key) {
        this.key = key;
    }

    public String key;
    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getMsg() {
return this.msg;

    }

    private String sender,receiver,msg;

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setMsg(String msg)
    {
       this.msg=msg;

    }

    void encrypt(String key){
        //https://github.com/scottyab/AESCrypt-Android
        try {
            this.msg =  AESCrypt.encrypt(key, this.msg);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
    void decrypt(String key){
        try {
            this.msg =  AESCrypt.decrypt(key, this.msg);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }


}
