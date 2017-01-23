package Model.Core;

import Model.Enumes.Types;

import java.util.List;
import java.util.Map;

/**
 * Created by micp on 01.12.16.
 */
public interface IEngine {
    void SaveData(Types type, Map<String,Object> form);
    List< Map<String,Object>> LoadData(Types type);
    void RemoveObject(Types type,int id);
}
