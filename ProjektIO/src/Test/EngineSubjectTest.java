package Test;

import Core.Engine;
import Model.Enumes.SubjectTypes;
import Model.Enumes.Types;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Betunia on 2017-01-23.
 */
public class EngineSubjectTest {

    @Test
    public void testGetActualId() throws Exception {

        Engine engine= new Engine();
        Map<String, Object> sendData;
        List<Map<String, Object>> list;

        list= engine.LoadData(Types.subject);

        int id= engine.GetActualId(Types.subject);

        sendData= new HashMap<>();
        sendData.put("name","biologia ogólna");
        sendData.put("id",-1);
        sendData.put("type", SubjectTypes.lecture);

        engine.SaveData(Types.subject,sendData);

        int idb= engine.GetActualId(Types.subject);

        Assert.assertEquals(id+1,idb);
    }

    @Test
    public void testSaveAndLoadData() throws Exception {
        Engine engine = new Engine();


        Map<String, Object> sendData;
        List<Map<String, Object>> list;

        list =  engine.LoadData(Types.subject);

        int a = list.size();


        sendData = new HashMap<>();
        sendData.put("name","biologia ogólna");
        sendData.put("id",-1);
        sendData.put("type", SubjectTypes.lecture);
        engine.SaveData(Types.subject,sendData);

        list =  engine.LoadData(Types.subject);

        int b = list.size();

        assertEquals(a+1,b);


    }



    @Test
    public void testRemoveObject() throws Exception {

        Engine engine = new Engine();
        List<Map<String, Object>> list;


        list =  engine.LoadData(Types.subject);

        int a = list.size();


        int id = engine.GetActualId(Types.subject);

        engine.RemoveObject(Types.subject,id-1);



        list =  engine.LoadData(Types.subject);

        int b = list.size();


        assertEquals(a,b+1);
    }
}