package Core;

import DataSource.AccessToFile;
import Model.Core.IDataController;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by micp on 01.12.16.
 */
public class JSONController implements IDataController{

    AccessToFile myFile ;
    private String fileName;

    public JSONController(String fileName){
        this.fileName = fileName;
       // myFile = new AccessToFile(fileName);

    }

    @Override
    public void SaveData(DataFile data) {
        //lpmwerska do binarki calej daty i zapis

        File openFile = new File(fileName);
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writeValue(openFile, data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public DataFile LoadAllData() {
        File openFile = new File(fileName);
        ObjectMapper mapper = new ObjectMapper();

        DataFile q = null;
        try {
            q = mapper.readValue(openFile, DataFile.class);
        } catch (IOException e) {
           // e.printStackTrace();
            q = new DataFile();
        }
        return q;
    }
}
