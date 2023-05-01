package HttpClient;
import java.net.MalformedURLException;
import java.net.URL;

public class GetHttpClient extends BlackListProcessor{

    public GetHttpClient(){
        super(null);
    }

    @Override
    public void sendRequest(Request request) throws MalformedURLException {
        checkBlockIp(request.getIpList().get(0));
        if (request.getIpList().size() == 0){
            System.out.println("No IP found for " + request.getUrl());
            return;
        }
        URL url = new URL(request.getUrl());
        System.out.println("Sending GET request: "
                + request.getUrl().replace(url.getHost(), request.getIpList().get(0)));
    }
}
