package com.michael.xmlwriteandread;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class XmlParserUtil {
    /* EXAMPLE
    <?xml version='1.0' encoding='UTF-8' standalone='yes' ?>
    <Persons>
      <Person>
          <name>xiaowang</name>
          <age>20</age>
          <sex>male</sex>
          <tel>10086</tel>
          <address>guangdong shenzhen</address>
      </Person>
      <Person>
          <name>xiaohong</name>
          <age>23</age>
          <sex>female</sex>
          <tel>10000</tel>
          <address>shanghai</address>
      </Person>
    </Persons>
    */
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
            xmlSerializer.startTag(null,"Persons");

            for (PersonBean person : persons){
                xmlSerializer.startTag(null,"Person");
                String[] person_values = {person.getName(),
                           String.valueOf(person.getAge()),
                                          person.getSex(),
                                          person.getTel(),
                                          person.getAddress()};
                for(int i=0;i<PersonBean.MSG.length;i++){
                    xmlSerializer.startTag(null,PersonBean.MSG[i]);
                    xmlSerializer.text(person_values[i]);
                    xmlSerializer.endTag(null,PersonBean.MSG[i]);
                }
                xmlSerializer.endTag(null,"Person");
            }
            xmlSerializer.endTag(null,"Persons");
            xmlSerializer.flush();
            mFileOutputStream.close();
        } catch (Exception e) {
            Log.e("XmlParserUtil","createXmlFile error");
        }
    }

    public static List<PersonBean> pullParserXml(String path){
        List<PersonBean>  list = null;
        File file = new File(path);
        PersonBean person = null;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        }catch (IOException e){
            Log.e("FileInputStream","Cannot open FileInputStream");
        }
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser        parser  = factory.newPullParser();
            parser.setInput(inputStream,"UTF-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                         list = new ArrayList<PersonBean>();
                        break;
                    case XmlPullParser.START_TAG :
                        String nodeName = parser.getName();
                         if(nodeName.equals("Person")){
                             person = new PersonBean();
                         }else if(nodeName.equals(PersonBean.MSG[0])){
                            eventType = parser.next();
                            person.setName(parser.getText());
                         }else if(nodeName.equals(PersonBean.MSG[1])){
                             eventType = parser.next();
                            person.setAge(Integer.valueOf(parser.getText()));
                         }else if(nodeName.equals(PersonBean.MSG[2])){
                             eventType = parser.next();
                            person.setSex(parser.getText());
                         }else if(nodeName.equals(PersonBean.MSG[3])){
                             eventType = parser.next();
                            person.setTel(parser.getText());
                         }else if(nodeName.equals(PersonBean.MSG[4])){
                            eventType = parser.next();
                            person.setAddress(parser.getText());
                         }
                         break;
                    case XmlPullParser.END_TAG:
                         if(parser.getName().equals("Person")) {
                             list.add(person);
                         }
                         break;
                }
                eventType = parser.next();
            }
        }catch (Exception e){
            Log.e("XmlPullParser","XmlPullParser error");
        }
       return list;
    }
}