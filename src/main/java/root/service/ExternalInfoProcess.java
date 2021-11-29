package root.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import root.CheckRequest;
import javax.cache.annotation.CacheResult;

@Component("externalInfoProcessClass")
@Lazy
@Scope("prototype")
@CacheResult
public class ExternalInfoProcess implements Process {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalInfoProcess.class);
    //@Value("${id}")
    private int idNotProcess = 4;

    @CheckRequest
    public boolean run(ExternalInfo e) throws Exception {
        if(e.getId() == this.idNotProcess){
            throw new Exception("ExternalInfoProcess Exception: external info id is equal to idNotProcess");
        }
        else{
            LOGGER.info("External info id is not equal to idNotProcess. Returned true");

            return true;
        }

    }
}
