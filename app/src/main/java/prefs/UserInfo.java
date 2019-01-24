package prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 10/1/2016.
 */

public class UserInfo {
    private static final String TAG = UserSession.class.getSimpleName();
    private static final String KEY_NAME = "name";
    private static final String KEY_SURNAME = "surname";
    private static final String KEY_EMAILADDRESS = "emailaddress";
    private static final String KEY_CONTACTNUM = "contactnum";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_ROLE_TYPE = "role_type";
    private static final String KEY_PICTURE = "picture";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;

    public UserInfo(Context ctx) {
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences(KEY_NAME, ctx.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setName(String name){
        editor.putString(KEY_SURNAME, name);
        editor.apply();
    }

    public void setSurname(String surname){
        editor.putString(KEY_SURNAME, surname);
        editor.apply();
    }

    public void setEmailAddress(String emailaddress){
        editor.putString(KEY_EMAILADDRESS, emailaddress);
        editor.apply();
    }

    public void setContactnum(String contactnum){
        editor.putString(KEY_CONTACTNUM, contactnum);
        editor.apply();
    }

    public void setPassword(String password){
        editor.putString(KEY_PASSWORD, password);
        editor.apply();
    }

    public void setRole_Type(String role_type){
        editor.putString(KEY_ROLE_TYPE, role_type);
        editor.apply();
    }

    public void setPicture(String picture){
        editor.putString(KEY_PICTURE, picture);
        editor.apply();
    }


    public void clearUserInfo(){
        editor.clear();
        editor.commit();
    }

    public String getKeyname(){return prefs.getString(KEY_NAME, "");}

    public String getKeySurname(){return prefs.getString(KEY_SURNAME, "");}

    public String getKeyEmailaddress(){return prefs.getString(KEY_EMAILADDRESS, "");}

    public String getKeyContactnum(){return prefs.getString(KEY_CONTACTNUM, "");}

    public String getKeyPassword(){return prefs.getString(KEY_PASSWORD, "");}

    public String getKeyRoleType(){return prefs.getString(KEY_ROLE_TYPE, "");}

    public String getKeyPicture(){return prefs.getString(KEY_PICTURE, "");}
}
