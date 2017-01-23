package DataSource;

import javax.security.auth.DestroyFailedException;
import javax.security.auth.Destroyable;
import java.io.*;

/**
 * Created by micp on 01.12.16.
 */
public class AccessToFile implements Destroyable {
    private FileWriter fw;
    private FileReader fr;

    public AccessToFile(String fileName) {
        File openFile = new File(fileName);

        try {
            if(!openFile.exists())
                    openFile.createNewFile();

            fw = new FileWriter(openFile);
            fr = new FileReader(openFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() throws DestroyFailedException {
        try {
            fw.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Save (String obj){
        try {
            BufferedWriter bw = new BufferedWriter( fw );
            bw.write(obj);
            bw.close( );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String Load(){
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(fr);
            char [] buf = new char[1024];
            int leng = 0;
            while((leng = br.read(buf) ) != -1){
                stringBuilder.append(buf,0,leng);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}
