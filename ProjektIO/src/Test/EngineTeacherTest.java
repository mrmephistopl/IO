package Test;

import Core.Engine;
import Model.Enumes.Types;
import org.junit.Assert;
import org.junit.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Betunia on 2017-01-23.
 */
public class EngineTeacherTest {



    @Test
    public void testGetActualId() throws Exception {

        Engine engine= new Engine();
        Map<String, Object> sendData;
        List<Map<String, Object>> list;

        list= engine.LoadData(Types.teacher);

        int id= engine.GetActualId(Types.teacher);

        sendData= new HashMap<>();
        sendData.put("sName","Mirek");
        sendData.put("fName","Jurek");
        sendData.put("id",-1);

        engine.SaveData(Types.teacher,sendData);

        int idb= engine.GetActualId(Types.teacher);

        Assert.assertEquals(id+1,idb);
    }
    @Test
    public void testSaveAndLoadData() throws Exception{
        Engine engine= new Engine();

        Map<String, Object> sendData;
        List<Map<String, Object>> list;
        list= engine.LoadData(Types.teacher);

        int a=list.size();

        sendData= new HashMap<>();
        sendData.put("sName","Mirek");
        sendData.put("fName","Jurek");
        sendData.put("id",-1);
        engine.SaveData(Types.teacher,sendData);

        list=engine.LoadData(Types.teacher);

        int b=list.size();
        Assert.assertEquals(a+1,b);
    }




    @Test
    public void testRemoveObject() throws Exception {

        Engine engine= new Engine();
        List<Map<String,Object>> list;

        list= engine.LoadData(Types.teacher);

        int a= list.size();

        int id= engine.GetActualId(Types.teacher);

        engine.RemoveObject(Types.teacher,id-1);

        list=engine.LoadData(Types.teacher);
        int b= list.size();

        Assert.assertEquals(a,b+1);
    }


}