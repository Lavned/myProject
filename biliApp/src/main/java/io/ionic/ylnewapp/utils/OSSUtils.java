package io.ionic.ylnewapp.utils;

/**
 * Created by mogojing on 2018/5/22/0022.
 */

public class OSSUtils {

//    //
//    private static String OSS_ENDPOINT ="http://oss-cn-beijing.aliyuncs.com";
//    private static String ACCESS_ID = "LTAIjkWsKwye8bkW";
//    private static String ACCESS_KEY ="daIB1XYRSDIwaEB2baF99BhOjt1NPE";
//    static OSSClient oss ;
//    static OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(ACCESS_ID, ACCESS_KEY);
//
//    public static void upload(Context context,String uploadFilePath){
//        ClientConfiguration conf = new ClientConfiguration();
//        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
//        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
//        conf.setMaxConcurrentRequest(8); // 最大并发请求数，默认5个
//        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
//
//        // oss为全局变量，OSS_ENDPOINT是一个OSS区域地址
//        oss = new OSSClient(context.getApplicationContext(), OSS_ENDPOINT, credentialProvider, conf);
//        // 构造上传请求
//        PutObjectRequest put = new PutObjectRequest("zjja", ACCESS_ID, uploadFilePath);
//        // 异步上传时可以设置进度回调
//        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
//            @Override
//            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
//                Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
//            }
//        });
//        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
//            @Override
//            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
//                Log.d("PutObject", "UploadSuccess"+request.getUploadFilePath());
//                Log.d("ETag", result.getETag());
//                Log.d("RequestId", result.getRequestId());
//            }
//            @Override
//            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
//                if (clientExcepion != null) {
//                    // 本地异常如网络异常等
//                    clientExcepion.printStackTrace();
//                }
//                if (serviceException != null) {
//                    // 服务异常
//                    Log.e("ErrorCode", serviceException.getErrorCode());
//                    Log.e("RequestId", serviceException.getRequestId());
//                    Log.e("HostId", serviceException.getHostId());
//                    Log.e("RawMessage", serviceException.getRawMessage());
//                }
//            }
//        });
// task.cancel(); // 可以取消任务
// task.waitUntilFinished(); // 可以等待任务完成
//    }
}
