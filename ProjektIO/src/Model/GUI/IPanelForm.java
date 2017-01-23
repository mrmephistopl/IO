package Model.GUI;

import Model.Enumes.Types;

import java.util.List;
import java.util.Map;

/**
 * Created by micp on 01.12.16.
 */
public interface IPanelForm {
    void SendFormData(Map<String,Object> data);
    void RemoveFormData(int id,Types type);
    List<Map<String, Object>> GetData(Types type);
}
