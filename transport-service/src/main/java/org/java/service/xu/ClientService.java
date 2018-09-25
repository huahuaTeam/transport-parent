package org.java.service.xu;

import org.java.entity.TranClient;

import java.util.List;
import java.util.Map;

public interface ClientService {

    public void addClient(TranClient client);

    public List<Map<String,Object>> selAllClient();
}
