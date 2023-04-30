package util.fileUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public class PropertyUtil {
    private PropertyUtil(){}
    private static Properties prop = new Properties();
    private static Map<String, String> propertyMap = null;

    public synchronized static void readPropertyAndPrepareMap(){
        System.out.println("Reading config.properties file");
        propertyMap = new HashMap<>();
        try(FileInputStream fis =
                    new FileInputStream(System.getProperty("user.dir")
                            + "/src/test/resources/config/config.properties");) {
            prop.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("file not found ",e);
        } catch (IOException e) {
            throw new RuntimeException("IO error", e);
        }

        prop.entrySet().forEach(entry -> {
            propertyMap.put(entry.getKey().toString(), (String) entry.getValue());
        });

        System.out.println("Finished reading property file");
    }

    public static String getProperty(Enum key){
        if(Objects.isNull(propertyMap))
            readPropertyAndPrepareMap();

        if(!propertyMap.containsKey(key.toString()))
            throw new RuntimeException(key+" does not exists in property file");

        return propertyMap.get(key.toString());
    }
}
