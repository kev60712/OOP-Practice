package HttpClient;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Set;

public abstract class HttpRequestProcessor implements HttpClient{

    protected HttpClient next;
    protected Set<String> blockIpSet;

    public HttpRequestProcessor(HttpClient next) {
        this.next = next;
    }

    // Abstract method 也要把被實作可能產生的exception列出來??
    abstract Request processRequest(Request request) throws FileNotFoundException, MalformedURLException;

    @Override
    public void sendRequest(Request request) throws MalformedURLException, FileNotFoundException {
        Request processedRequest = processRequest(request);
        if (blockIpSet != null){
            next.setBlockIpSet(blockIpSet);
        }
        if (next != null){
            next.sendRequest(processedRequest);
        }
    }

    @Override
    public void setBlockIpSet(Set<String> blockIpSet) {
        this.blockIpSet = blockIpSet;
    }
}
