package HttpClient;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Set;

public interface HttpClient {

    void sendRequest(Request request) throws MalformedURLException, FileNotFoundException;

    void setBlockIpSet(Set<String> blockIpSet);

}
