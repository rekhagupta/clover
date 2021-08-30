package utilities;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class utils {
    public static HashMap<String, String> paramsToMap(String inputStr, String separator, String appId) throws UnsupportedEncodingException {
        HashMap<String, String> queryParams = new HashMap<String, String>();
        if (!inputStr.isEmpty()) {

            String[] pairs = inputStr.split(separator);
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                queryParams.put(pair.substring(0, idx), pair.substring(idx + 1));
            }
            if (!appId.isEmpty()) queryParams.put("APPID", appId);
        }
        return queryParams;
    }

    public static String getValueForKey(){
        return null;
    }
}
