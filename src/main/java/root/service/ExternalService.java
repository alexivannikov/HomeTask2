package root.service;

import root.service.ExternalInfo;

public interface ExternalService {
    ExternalInfo getExternalInfo(Integer id) throws Exception;
}
