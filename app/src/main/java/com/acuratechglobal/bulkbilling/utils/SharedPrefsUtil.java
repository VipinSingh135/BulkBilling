package com.acuratechglobal.bulkbilling.utils;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

public class SharedPrefsUtil {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor editor;
    private Gson GSON ;
    public static String PREFERENCE_USER_DATA= "user_data";

    @Inject
    SharedPrefsUtil(SharedPreferences mSharedPreferences, Gson GSON) {
        this.mSharedPreferences = mSharedPreferences;
        this.GSON=GSON;
        editor= mSharedPreferences.edit();
    }

    public void save(String key, boolean value) {
        editor.putBoolean(key, value).apply();
    }

    public void save(String key, String value) {
        editor.putString(key, value).apply();
    }

    public void save(String key, int value) {
        editor.putInt(key, value).apply();
    }

    public void save(String key, float value) {
        editor.putFloat(key, value).apply();
    }

    public void save(String key, long value) {
        editor.putLong(key, value).apply();
    }

    public void save(String key, Set<String> value) {
        editor.putStringSet(key, value).apply();
    }

    // to save object in preference
    public void save(String key, Object object) {
        if (object == null) {
            throw new IllegalArgumentException("object is null");
        }

        if (key.equals("") || key == null) {
            throw new IllegalArgumentException("key is empty or null");
        }

        editor.putString(key, GSON.toJson(object)).apply();
    }

    //to save arraylist in preference
    public <T> void save(String key, ArrayList<T> list){
        if (list == null) {
            throw new IllegalArgumentException("object is null");
        }

        if (key.equals("") || key == null) {
            throw new IllegalArgumentException("key is empty or null");
        }

        editor.putString(key, GSON.toJson(list)).apply();
    }


    // To get object from prefrences
    public <T> T getObject(String key, Class<T> a) {
        String gson = mSharedPreferences.getString(key, null);
        if (gson == null) {
            return null;
        } else {
            try {
                return GSON.fromJson(gson, a);
            } catch (Exception e) {
                throw new IllegalArgumentException("Object storaged with key "
                        + key + " is instanceof other class");
            }
        }
    }

//    public static <T> List<T> readAnyTypeOfList(String key, TypeToken<List<T>> tt) {
//        Gson gson = new Gson();
//        return (gson.fromJson(SharedPref.read(key, "[]"), tt.getType()));
//    }

    public <T> ArrayList<T> getLists(String key, TypeToken<List<T>> tt){
        String gson = mSharedPreferences.getString(key, null);
        if (gson == null) {
            return null;
        } else {
            try {
                return GSON.fromJson(gson, tt.getType());
            } catch (Exception e) {
                throw new IllegalArgumentException("Object storaged with key "
                        + key + " is instanceof other class");
            }
        }
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mSharedPreferences.getBoolean(key, defValue);
    }

    public String getString(String key, String defValue) {
        return mSharedPreferences.getString(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return mSharedPreferences.getInt(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return mSharedPreferences.getFloat(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return mSharedPreferences.getLong(key, defValue);
    }

    public Set<String> getStringSet(String key, Set<String> defValue) {
        return mSharedPreferences.getStringSet(key, defValue);
    }

    public Map<String, ?> getAll() {
        return mSharedPreferences.getAll();
    }

    public void remove(String key) {
        editor.remove(key).apply();
    }

    public void removeAll() {
        editor.clear();
        editor.apply();
    }

}
