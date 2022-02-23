import java.io.PrintStream;
import java.net.*;

public class JarFileLoader extends URLClassLoader
{

    public JarFileLoader(URL urls[])
    {
        super(urls);
    }

    public void addFile(String path)
        throws MalformedURLException
    {
        String urlPath = (new StringBuilder()).append("jar:file://").append(path).append("!/").toString();
        addURL(new URL(urlPath));
    }

    public static void main(String args[])
    {
        try
        {
            System.out.println("First attempt...");
            Class.forName("org.gjt.mm.mysql.Driver");
        }
        catch(Exception ex)
        {
            System.out.println("Failed.");
        }
        try
        {
            URL urls[] = new URL[0];
            JarFileLoader cl = new JarFileLoader(urls);
            cl.addFile("/opt/mysql-connector-java-5.0.4/mysql-connector-java-5.0.4-bin.jar");
            System.out.println("Second attempt...");
            cl.loadClass("org.gjt.mm.mysql.Driver");
            System.out.println("Success!");
        }
        catch(Exception ex)
        {
            System.out.println("Failed.");
            ex.printStackTrace();
        }
    }
}
