package root;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import root.service.ExternalInfo;
import root.service.ExternalService;
import root.service.Process;

@ComponentScan
@EnableAspectJAutoProxy
public class Main {
    public static void main(String [] args) throws Exception {
        AnnotationConfigApplicationContext a = new AnnotationConfigApplicationContext(Main.class);

        ExternalInfo e = new ExternalInfo(1, "dsfsf");
        Process p = a.getBean(Process.class);

        p.run(e);
        p.run(e);
    }
}
