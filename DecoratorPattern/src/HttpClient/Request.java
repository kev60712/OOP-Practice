package HttpClient;

import java.util.ArrayList;
import java.util.List;

public class Request {

    private String url;
    private List<String> ipList;

    public Request(String url) {
        this.url = url;
        this.ipList = new ArrayList<>();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<String> getIpList() {
        return ipList;
    }

    public void setIpList(List<String> ipList) {
        this.ipList = ipList;
    }
}
