

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Future;

import org.junit.Test;

import com.ctg.ag.sdk.core.constant.Scheme;
import com.ctg.ag.sdk.core.model.ApiCallBack;

import com.ctg.ag.sdk.biz.AepNbDeviceManagementClient;
import com.ctg.ag.sdk.biz.aep_nb_device_management.BatchCreateNBDeviceRequest;
import com.ctg.ag.sdk.biz.aep_nb_device_management.BatchCreateNBDeviceResponse;


public class AepNbDeviceManagementDemo {

    // 没有签名同步调用接口示例
    @Test
    public void testApi() throws Exception {

        AepNbDeviceManagementClient client = AepNbDeviceManagementClient.newClient()
                .appKey("")
                .appSecret("")
                .build();

        {
            BatchCreateNBDeviceRequest request = new BatchCreateNBDeviceRequest();
            // request.setParam..  	// set your request params here
            System.out.println(client.BatchCreateNBDevice(request));
        }

        client.shutdown();

    }

    // 没有签名异步调用接口示例
    @Test
    public void testApiAsync() throws Exception {

        AepNbDeviceManagementClient client = AepNbDeviceManagementClient.newClient().build();

        {

            List<Future<BatchCreateNBDeviceResponse>> res = new ArrayList<Future<BatchCreateNBDeviceResponse>>();

            // multi request
            for (int i = 0; i < 5; i++) {

                BatchCreateNBDeviceRequest request = new BatchCreateNBDeviceRequest();
                // request.setParam..  	// set your request params here

                res.add(client.BatchCreateNBDevice(request, new ApiCallBack<BatchCreateNBDeviceRequest, BatchCreateNBDeviceResponse>() {
                    @Override
                    public void onFailure(BatchCreateNBDeviceRequest request, Exception e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(BatchCreateNBDeviceRequest request, BatchCreateNBDeviceResponse response) {
                        System.out.println("Receive data and handle it");
                    }
                }));

            }

            // wait and collect all data
            for (Future<BatchCreateNBDeviceResponse> future : res)
                System.out.println(future.get());

        }

        client.shutdown();
    }

    // 没有签名https同步调用接口示例
    @Test
    public void testApiWithSsl() throws Exception {

        AepNbDeviceManagementClient client = AepNbDeviceManagementClient.newClient().scheme(Scheme.HTTPS).build();

        {
            BatchCreateNBDeviceRequest request = new BatchCreateNBDeviceRequest();
            // request.setParam..  	// set your request params here
            System.out.println(client.BatchCreateNBDevice(request));
        }

        client.shutdown();
    }

    // 签名同步调用接口示例
    @Test
    public void testApiWithSignature() throws Exception {

        AepNbDeviceManagementClient client = AepNbDeviceManagementClient.newClient().appKey("Your app key here").appSecret("Your app secret here").build();

        {
            BatchCreateNBDeviceRequest request = new BatchCreateNBDeviceRequest();
            // request.setParam..  	// set your request params here
            System.out.println(client.BatchCreateNBDevice(request));
        }

        client.shutdown();
    }

}
