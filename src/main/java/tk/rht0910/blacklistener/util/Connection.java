package tk.rht0910.blacklistener.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connection {
    public static String get(String strUrl) throws InternalError {
        HttpURLConnection con = null;
        StringBuffer result = new StringBuffer();

        try {
            URL url = new URL(strUrl);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            final int status = con.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                final InputStream in = con.getInputStream();
                String encoding = con.getContentEncoding();
                if(null == encoding){
                    encoding = "UTF-8";
                }
                final InputStreamReader inReader = new InputStreamReader(in, encoding);
                final BufferedReader bufReader = new BufferedReader(inReader);
                String line = null;
                while((line = bufReader.readLine()) != null) {
                    result.append(line);
                }
                bufReader.close();
                inReader.close();
                in.close();
            } else {
                throw new InternalError("HTTP GET Request Failed: Returned " + status);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return result.toString();
    }
}
