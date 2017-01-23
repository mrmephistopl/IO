package Model.GUI;

import java.util.List;
import java.util.Map;

/**
 * Created by micp on 01.12.16.
 */
public interface IForm {
    void ShowForm();
    void InitializeForm(IPanelForm generalForm,List<Map<String, Object>> data);
}
