package HttpClient;

import java.util.List;

public class RoundRobinIp {

    private Integer currentIpIndex = 0;
    private List<String> ipList;

    public RoundRobinIp(List<String> ipList) {
        this.ipList = ipList;
    }

    public String getIp() {
        String ip = ipList.get(currentIpIndex);
        currentIpIndex = (currentIpIndex + 1) % ipList.size();
        return ip;
    }
}
