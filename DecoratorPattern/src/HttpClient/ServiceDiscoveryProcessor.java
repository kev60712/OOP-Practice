package HttpClient;

import util.FileUtil;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class ServiceDiscoveryProcessor extends HttpRequestProcessor{

    private Map<String, List<String>> hostIpMap;

    public ServiceDiscoveryProcessor(HttpClient next) {
        super(next);
    }

    public void init() throws FileNotFoundException {
        hostIpMap = new HashMap<>();
        String  filePath = System.getProperty("user.dir") + "/DecoratorPattern/src/source/host_ip_map.txt";
        List<String> hostIpList = Arrays.asList(FileUtil.readFile(filePath).split("\n"));
        for (String hostIp : hostIpList){
            String[] hostIpPair = hostIp.split(":");
            String host = hostIpPair[0];
            String ips = hostIpPair[1].replace(" ", "");
            List<String> ipList = Arrays.asList(ips.split(","));
            hostIpMap.put(host, ipList);
        }
    }

    @Override
    public Request processRequest(Request request) throws FileNotFoundException, MalformedURLException {
        if (hostIpMap == null){
            init();
        }
        String urlStr = request.getUrl();
        URL url = new URL(urlStr);
        List<String> mappedIp = hostIpMap.get(url.getHost());
        request.setIpList(mappedIp);
        return request;
    }
}
