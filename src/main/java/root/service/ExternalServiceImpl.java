package root.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import root.service.ExternalService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.cache.annotation.CacheResult;
import java.util.HashMap;
import java.util.Map;

@Component("externalServiceImplClass")
public class ExternalServiceImpl implements ExternalService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalServiceImpl.class);

    private Map<Integer, ExternalInfo> testMap = new HashMap<>();

    public ExternalServiceImpl(){

    }

    @CacheResult
    public ExternalInfo getExternalInfo(Integer id) throws Exception {
        if(testMap.get(id) == null){
            throw new Exception("ExternalServiceImpl Exception: Object ExternalInfo not found in Map!");
        }else{
            return testMap.get(id);
        }
    }

    public Map<Integer, ExternalInfo> getTestMap(){
        return this.testMap;
    }

    @PostConstruct
    public void init(){
        this.testMap.put(1, new ExternalInfo(1, null));
        this.testMap.put(2, new ExternalInfo(2, "hashinfo"));
        this.testMap.put(3, new ExternalInfo(3, "info"));
        this.testMap.put(4, new ExternalInfo(4, "information"));
    }

    @PreDestroy
    public void clearTestMap(){
        this.testMap.clear();

        LOGGER.info("Test map cleared");
    }
}
