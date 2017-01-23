package Test

import Core.DataFile
import Core.JSONController
import Core.Objects.Group
import Model.Enumes.SubjectTypes
import junit.framework.Assert
import junit.framework.TestCase
import org.testng.annotations.Test
/**
 * Created by Betunia on 2017-01-23.
 */
class JSONControllerTest extends TestCase {

    @Test
    public void testSaveData() throws Exception {
        JSONController t = new JSONController("sss.jd");
        DataFile df = new  DataFile();

        df.groups.add(new Group(1,"äa",SubjectTypes.auditorium));
        t.SaveData(df);

        File ex = new File("sss.jd");
        Assert.assertEquals(ex.exists(),true);
    }

    @org.junit.Test
    public void testLoadAllData() throws Exception {
        JSONController t = new JSONController("sss.jd");

        DataFile df =  t.LoadAllData();
        Assert.assertNotNull(df);

        Assert.assertEquals(df.groups.size(),1);
    }
}
