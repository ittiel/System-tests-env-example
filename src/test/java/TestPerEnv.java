
import org.junit.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestPerEnv {
    static Properties envProps = new Properties();

    // Run once - get environment properties
    //todo: extract to a utility method
    @BeforeClass
    public static void runOnceBeforeClass() {
        //usage example from cmd: mvn test -Denvironment=production
        String environment = System.getProperty("environment");
        System.out.println("Using environment: " + environment);

        InputStream input = null;
        try{
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            String propertiesPath = "env" + File.separator + environment + File.separator + "env.properties";
            input = classloader.getResourceAsStream(propertiesPath);
            envProps.load(input);
        //todo: fail tests if environment is not set properly.
        }catch (FileNotFoundException fnf){
            System.out.println("Couldn't find the relevant properties file for the environment " + environment);
        }catch (IOException io){
            System.out.println("Failed to load env properties " + io.getMessage());
        }catch (NullPointerException npe){
            //todo: use failover to ci environment if really needed
            System.out.println("Need to pass -Denvironment=<environment name>");
        }
    }

    @Test
    public void TestSomething() {
        System.out.println(envProps.getProperty("site.url"));
        Assert.assertTrue(true);
    }


}
