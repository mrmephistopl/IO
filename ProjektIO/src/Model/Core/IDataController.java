package Model.Core;

import Core.DataFile;

/**
 * Created by micp on 01.12.16.
 */
public interface IDataController {
    void SaveData(DataFile data);
    DataFile LoadAllData();
}
