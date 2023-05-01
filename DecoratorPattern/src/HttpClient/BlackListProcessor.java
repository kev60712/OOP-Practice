package HttpClient;

import util.FileUtil;

import java.io.FileNotFoundException;
import java.util.HashSet;


public class BlackListProcessor extends HttpRequestProcessor{

    public BlackListProcessor(HttpClient next) {
        super(next);
    }

    private void init() throws FileNotFoundException {
        blockIpSet = new HashSet<>();
        String filePath = System.getProperty("user.dir") + "/DecoratorPattern/src/source/ip_black_list.txt";
        String blockIps = FileUtil.readFile(filePath);
        blockIps.replace(" ", "");
        String[] blockIpArr = blockIps.split(",");
        for (String blockIp : blockIpArr){
            blockIpSet.add(blockIp);
        }
    }

    @Override
    Request processRequest(Request request) throws FileNotFoundException {
        if (blockIpSet == null){
            init();
        }

        if (request.getIpList().size() == 0){
            return request;
        }
        String ip = request.getIpList().get(0);
        checkBlockIp(ip);
        return request;
    }

    public void checkBlockIp(String ip){
        if (blockIpSet != null && blockIpSet.contains(ip)){
            throw new RuntimeException("This ip is blocked");
        }
    }
}
