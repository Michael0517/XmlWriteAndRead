package com.michael.xmlwriteandread;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;


public class XmlParserUtil {

    public static void createXmlFile(final String xmlPath,List<PersonBean> persons){
        File file = new File(xmlPath);
        FileOutputStream mFileOutputStream = null;
        try {
            mFileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            Log.e("FileNotFoundException" , "cannot create mFileOutputStream beause of File not finding");
        }
        XmlSerializer xmlSerializer = Xml.newSerializer();
        try {
            xmlSerializer.setOutput(mFileOutputStream,"UTF-8");
            xmlSerializer.startDocument(null,true);
            xmlSerializer.startTag(null,"Person");
            for (PersonBean person : persons){

                //write name
                xmlSerializer.startTag(null,"name");
                xmlSerializer.text(person.getName());
                xmlSerializer.endTag(null,"name");
                addItem();
                //write age
                //write sex
                //write tel
                //write address
            }
            xmlSerializer.startTag(null,"name");
        } catch (Exception e) {
            Log.d("XmlParserUtil","");
        }
     //   mFileOutputStream.write();
    }


    public static void addItem throws Exception(XmlSerializer xmlSerializer,String item,String value){
        xmlSerializer.startTag(null,"name");
        xmlSerializer.text(person.getName());
        xmlSerializer.endTag(null,"name");
    }



}