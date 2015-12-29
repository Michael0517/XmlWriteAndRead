package com.michael.xmlwriteandread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testCreateXml();
        testParserXml();
    }

    public void testCreateXml(){
        //data/data/com.michael.xmlwriteandread/files/Andy.xml
        String file = this.getFilesDir().getAbsolutePath()+"/"+"Andy.xml";
        Log.d("michael","path = "+file);
        File myFile = new File(file);
        try {
            if(!myFile.exists()) {
                Boolean isCreate = myFile.createNewFile();
            }
        }catch (IOException e){
            Log.d("IOException","cannot create file");
        }
        PersonBean person1 = new PersonBean("xiaowang",20,"male","10086","guangdong shenzhen");
        PersonBean person2 = new PersonBean("xiaohong",23,"female","10000","shanghai");
        List<PersonBean> list = new ArrayList<PersonBean>();
        list.add(person1);
        list.add(person2);
        XmlParserUtil.createXmlFile(myFile.getPath(),list);
    }

    public void testParserXml(){
        String path = "data/data/com.michael.xmlwriteandread/files/Andy.xml";
        List<PersonBean> list=XmlParserUtil.pullParserXml(path);
        for(PersonBean person:list){
            Log.d("michael","list.size = "+list.size()+ "    name = "+person.getName()+"    age = "+String.valueOf(person.getAge())+"   sex = "+person.getSex()+"    Tel = "+person.getTel()+"   address = "+person.getAddress());
        }
    }
}
