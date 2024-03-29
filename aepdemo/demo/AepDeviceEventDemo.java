import com.ctg.ag.sdk.biz.AepDeviceEventClient;
import com.ctg.ag.sdk.biz.aep_device_event.*;
import com.ctg.ag.sdk.core.constant.Scheme;
import com.ctg.ag.sdk.core.model.ApiCallBack;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;


public class AepDeviceEventDemo {

    // 没有签名同步调用接口示例
    @Test
    public void testApi() throws Exception {

        AepDeviceEventClient client = AepDeviceEventClient.newClient()
                .appKey("stqO6tloZYd")
                .appSecret("EOaK9hdwIw")
                .build();

//        {
//            QueryEventListRequest request = new QueryEventListRequest();
//            // request.setParam..  	// set your request params here
//            System.out.println(client.QueryEventList(request));
//        }

        {
            QueryDeviceEventListRequest request = new QueryDeviceEventListRequest();
            // request.setParam..  	// set your request params here

            request.setParamMasterKey("1af2fd1e8df143eab4f9fb449696f6f4");
            long endTime = System.currentTimeMillis();
            long startTime = endTime - 1000 * 60 * 60 * 24;
            String body = "{" +
//                    "\"eventType\":1," +
                    "\"pageTimestamp\":" + endTime + "," +
                    "\"productId\":\"15058399\"," +
                    "\"deviceId\":\"e2862fe7296f44898792bebbd8121ce8\"," +
                    "\"startTime\":" + startTime + "," +
                    "\"endTime\":" + endTime + "," +
                    "\"pageSize\":10" +
                    "}";
            System.out.println(body);
            request.setBody(body.getBytes());
            System.out.println(client.QueryDeviceEventList(request));
        }
//
//        {
//            QueryDeviceEventTotalRequest request = new QueryDeviceEventTotalRequest();
//            // request.setParam..  	// set your request params here
//            System.out.println(client.QueryDeviceEventTotal(request));
//        }

        client.shutdown();

    }

    // 没有签名异步调用接口示例
    @Test
    public void testApiAsync() throws Exception {

        AepDeviceEventClient client = AepDeviceEventClient.newClient().build();

        {

            List<Future<QueryEventListResponse>> res = new ArrayList<Future<QueryEventListResponse>>();

            // multi request
            for (int i = 0; i < 5; i++) {

                QueryEventListRequest request = new QueryEventListRequest();
                // request.setParam..  	// set your request params here

                res.add(client.QueryEventList(request, new ApiCallBack<QueryEventListRequest, QueryEventListResponse>() {
                    @Override
                    public void onFailure(QueryEventListRequest request, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(QueryEventListRequest request, QueryEventListResponse response) {
                        System.out.println("Receive data and handle it");
                    }
                }));

            }

            // wait and collect all data
            for (Future<QueryEventListResponse> future : res)
                System.out.println(future.get());

        }

        {

            List<Future<QueryDeviceEventListResponse>> res = new ArrayList<Future<QueryDeviceEventListResponse>>();

            // multi request
            for (int i = 0; i < 5; i++) {

                QueryDeviceEventListRequest request = new QueryDeviceEventListRequest();
                // request.setParam..  	// set your request params here

                res.add(client.QueryDeviceEventList(request, new ApiCallBack<QueryDeviceEventListRequest, QueryDeviceEventListResponse>() {
                    @Override
                    public void onFailure(QueryDeviceEventListRequest request, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(QueryDeviceEventListRequest request, QueryDeviceEventListResponse response) {
                        System.out.println("Receive data and handle it");
                    }
                }));

            }

            // wait and collect all data
            for (Future<QueryDeviceEventListResponse> future : res)
                System.out.println(future.get());

        }

        {

            List<Future<QueryDeviceEventTotalResponse>> res = new ArrayList<Future<QueryDeviceEventTotalResponse>>();

            // multi request
            for (int i = 0; i < 5; i++) {

                QueryDeviceEventTotalRequest request = new QueryDeviceEventTotalRequest();
                // request.setParam..  	// set your request params here

                res.add(client.QueryDeviceEventTotal(request, new ApiCallBack<QueryDeviceEventTotalRequest, QueryDeviceEventTotalResponse>() {
                    @Override
                    public void onFailure(QueryDeviceEventTotalRequest request, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(QueryDeviceEventTotalRequest request, QueryDeviceEventTotalResponse response) {
                        System.out.println("Receive data and handle it");
                    }
                }));

            }

            // wait and collect all data
            for (Future<QueryDeviceEventTotalResponse> future : res)
                System.out.println(future.get());

        }

        client.shutdown();
    }

    // 没有签名https同步调用接口示例
    @Test
    public void testApiWithSsl() throws Exception {

        AepDeviceEventClient client = AepDeviceEventClient.newClient().scheme(Scheme.HTTPS).build();

        {
            QueryEventListRequest request = new QueryEventListRequest();
            // request.setParam..  	// set your request params here
            System.out.println(client.QueryEventList(request));
        }

        {
            QueryDeviceEventListRequest request = new QueryDeviceEventListRequest();
            // request.setParam..  	// set your request params here
            System.out.println(client.QueryDeviceEventList(request));
        }

        {
            QueryDeviceEventTotalRequest request = new QueryDeviceEventTotalRequest();
            // request.setParam..  	// set your request params here
            System.out.println(client.QueryDeviceEventTotal(request));
        }

        client.shutdown();
    }

    // 签名同步调用接口示例
    @Test
    public void testApiWithSignature() throws Exception {

        AepDeviceEventClient client = AepDeviceEventClient.newClient().appKey("Your app key here").appSecret("Your app secret here").build();

        {
            QueryEventListRequest request = new QueryEventListRequest();
            // request.setParam..  	// set your request params here
            System.out.println(client.QueryEventList(request));
        }

        {
            QueryDeviceEventListRequest request = new QueryDeviceEventListRequest();
            // request.setParam..  	// set your request params here
            System.out.println(client.QueryDeviceEventList(request));
        }

        {
            QueryDeviceEventTotalRequest request = new QueryDeviceEventTotalRequest();
            // request.setParam..  	// set your request params here
            System.out.println(client.QueryDeviceEventTotal(request));
        }

        client.shutdown();
    }

}
