import HttpClient.BlackListProcessor;
import HttpClient.GetHttpClient;
import HttpClient.HttpClient;
import HttpClient.ServiceDiscoveryProcessor;
import HttpClient.LoadBalancingProcessor;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import HttpClient.Request;

public class Main {
    public static void main(String[] args) throws MalformedURLException, FileNotFoundException {
        String warterBallUrl = "http://waterballsa.tw/world";
        HttpClient httpClient1 = new ServiceDiscoveryProcessor(new LoadBalancingProcessor(new BlackListProcessor(new GetHttpClient())));
        httpClient1.sendRequest(new Request(warterBallUrl));
        httpClient1.sendRequest(new Request(warterBallUrl));
        httpClient1.sendRequest(new Request(warterBallUrl));
        httpClient1.sendRequest(new Request(warterBallUrl));
        httpClient1.sendRequest(new Request(warterBallUrl));

        System.out.println("=====================================");
        String googleUrl = "http://google.com/mail";
        HttpClient httpClient2 = new BlackListProcessor(new LoadBalancingProcessor(new ServiceDiscoveryProcessor(new GetHttpClient())));
        httpClient2.sendRequest(new Request(googleUrl));
        httpClient2.sendRequest(new Request(googleUrl));
    }
}

