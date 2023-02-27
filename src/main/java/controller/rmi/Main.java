package controller.rmi;

import javax.naming.*;
import javax.script.Bindings;
import java.io.File;
import java.util.Properties;

/**
 * @author Sergey Klunniy
 */
public class Main {
    public static void main(String[] args) throws NamingException {
        String name = "E:\\java-programms\\enternet-shop\\TrackensureConfiguration.properties";

        Properties props = new Properties();
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
        props.put(Context.PROVIDER_URL, "file:///");

        Context initialContext = new InitialContext(props);

        File obj = (File)initialContext.lookup(name);
        System.out.println(obj.getName());
        if (name.equals("")) {
            System.out.println("Looked up the initial context");
        } else {
            System.out.println(name + " is bound to: " + obj);
        }

//        list objects:
//        NamingEnumeration list = initialContext.list("/");
//        while (list.hasMore()) {
//            NameClassPair nc = (NameClassPair) list.next();
//            System.out.println(nc);

//        NamingEnumeration bindings = initialContext.listBindings("/");
//        while (bindings.hasMore()) {
//            Binding bd = (Binding) bindings.next();
//            System.out.println(bd.getName() + ": " + bd.getObject());
//        }

    }
}
