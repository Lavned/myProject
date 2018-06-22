package io.ionic.ylnewapp.constants;

/**
 * Created by hah on 2018/5/2 0002.
 */

public class Constants {

//    public final static String URL_BASE = "http://www.yifan886.com/";

    public final static String URL_BASE = "http://192.168.123.210:5000/";
    public final static String URL_BASE2 = "http://192.168.123.210:8080/#/"; //网页




    public final static String MARKRT_URL_BASE = "http://192.168.123.219:8088/"; //行情
    public final static String MARKRT_CHOOSE = "http://192.168.123.219:9092/choose";//自选socket
    public final static String MARKRT_SZ = "http://192.168.123.219:9092/marketValue";//市值socket
    public final static String MARKRT_TICKER= "http://47.52.145.241:9092/ticker";//市值二级socket



    /**
     * x协议
     */
    public  static  class WbUrl{
        public final static String webAiInstruction = URL_BASE2 + "aiInstruction";
        public final static String webBtcRecruit = URL_BASE2 + "btcRecruit";
        public final static String webAssetDelegation = URL_BASE2 + "assetDelegation";
        public final static String webInvestmentRisk = URL_BASE2 + "investmentRisk";
        public final static String webIcoProtocol = URL_BASE2 + "icoProtocol";
        public final static String webeEthAgreement = URL_BASE2 + "ethAgreement";
    }

}
