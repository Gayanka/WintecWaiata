package com.example.wintecwaiata;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ReadAllRawData {

    ArrayList<String> listEkorekoe, listHemaimaiaroha, listItewhare;
    ArrayList<String> listPuatekowhai, listpupuketehihiri, listTutiramainga, listWaikatoteawa;

    public void listRaw() throws IllegalAccessException {
        listEkorekoe = new ArrayList<>();
        listHemaimaiaroha = new ArrayList<>();
        listWaikatoteawa = new ArrayList<>();
        listTutiramainga = new ArrayList<>();
        listpupuketehihiri = new ArrayList<>();
        listItewhare = new ArrayList<>();
        listPuatekowhai = new ArrayList<>();

        //get all file from raw directory
        Field[] fields = R.raw.class.getFields();
        for (int count = 0; count < fields.length; count++) {
//            Log.i("Raw Asset: ", fields[count].getName());

            if (fields[count].getName().startsWith("eko")) {
                listEkorekoe.add("android.resource://" + getClass().getPackage().getName()
                        + "/" + fields[count].getInt(fields[count]));
            }

            if (fields[count].getName().startsWith("hem")) {
                listHemaimaiaroha.add("android.resource://" + getClass().getPackage().getName()
                        + "/" + fields[count].getInt(fields[count]));
            }

            if (fields[count].getName().startsWith("wai")) {
                listWaikatoteawa.add("android.resource://" + getClass().getPackage().getName()
                        + "/" + fields[count].getInt(fields[count]));
            }

            if (fields[count].getName().startsWith("tut")) {
                listTutiramainga.add("android.resource://" + getClass().getPackage().getName()
                        + "/" + fields[count].getInt(fields[count]));
            }

            if (fields[count].getName().startsWith("pup")) {
                listpupuketehihiri.add("android.resource://" + getClass().getPackage().getName()
                        + "/" + fields[count].getInt(fields[count]));
            }

            if (fields[count].getName().startsWith("ite")) {
                listItewhare.add("android.resource://" + getClass().getPackage().getName()
                        + "/" + fields[count].getInt(fields[count]));
            }

            if (fields[count].getName().startsWith("pua")) {
                listPuatekowhai.add("android.resource://" + getClass().getPackage().getName()
                        + "/" + fields[count].getInt(fields[count]));
            }

        }

        //check the list size
//        Log.d(TAG, "listRaw(listEkorekoe): " + listEkorekoe.size());
//        Log.d(TAG, "listRaw(listHemaimaiaroha): " + listHemaimaiaroha.size());
//        Log.d(TAG, "listRaw(listItewhare): " + listItewhare.size());
//        Log.d(TAG, "listRaw(listPuatekowhai): " + listPuatekowhai.size());
//        Log.d(TAG, "listRaw(listpupuketehihiri): " + listpupuketehihiri.size());
//        Log.d(TAG, "listRaw(listTutiramainga): " + listTutiramainga.size());
//        Log.d(TAG, "listRaw(listWaikatoteawa): " + listWaikatoteawa.size());
//
//        Log.d(TAG, "listRaw(listEkorekoe): " + listEkorekoe.get(0).toString());
//        Log.d(TAG, "listRaw(listHemaimaiaroha): " + listHemaimaiaroha.get(0).toString());
//        Log.d(TAG, "listRaw(listItewhare): " + listItewhare.get(0).toString());
//        Log.d(TAG, "listRaw(listPuatekowhai): " + listPuatekowhai.get(0).toString());
//        Log.d(TAG, "listRaw(listpupuketehihiri): " + listpupuketehihiri.get(0).toString());
//        Log.d(TAG, "listRaw(listTutiramainga): " + listTutiramainga.get(0).toString());
//        Log.d(TAG, "listRaw(listWaikatoteawa): " + listWaikatoteawa.get(0).toString());

    }
}
