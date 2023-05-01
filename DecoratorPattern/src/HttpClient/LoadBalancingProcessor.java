package HttpClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LoadBalancingProcessor extends HttpRequestProcessor{

    private Map<String, RoundRobinIp> roundRobinHostIpMap = new HashMap<>();
    public LoadBalancingProcessor(HttpClient next) {
        super(next);
    }

    @Override
    Request processRequest(Request request) throws MalformedURLException {
        if (request.getIpList().size() == 0){
            return request;
        }
        RoundRobinIp roundRobinIp;
        URL url = new URL(request.getUrl());
        if (roundRobinHostIpMap.containsKey(url.getHost())) {
            roundRobinIp = roundRobinHostIpMap.get(url.getHost());
        }else{
            roundRobinHostIpMap.put(url.getHost(), new RoundRobinIp(request.getIpList()));
            roundRobinIp = roundRobinHostIpMap.get(url.getHost());
        }
        String ip = roundRobinIp.getIp();
        request.setIpList(Collections.singletonList(ip));
        return request;
    }
}
