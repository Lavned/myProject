package io.ionic.ylnewapp.constants;

/**
 * Created by hah on 2018/5/2 0002.
 */

public class Constants {

    public final static String URL_BASE = "http://www.bit000.com:5000/"; //发布环境
//    public final static String URL_BASE = "http://192.168.123.214:5000/"; //本地测试环境
//        public final static String URL_BASE = "http://39.104.191.49:5000/"; //线上测试环境39.104.191.49:500047.52.246.112:80/

//    public final static String URL_BASE2 = "http://192.168.123.214:8082/#/"; //网页环境
    public final static String URL_BASE2 = "http://app.bit000.com/#/"; //网页发布环境




//    public final static String MARKRT_URL_BASENative = "http://47.52.145.241:8080/";
//

    /**
     * 行情相关接口
     */
    public final static String MARKRT_URL_BASEHTTP = "http://47.52.241.237:8088/";  //http接口发布环境
    public final static String SOCKETHTTP = "http://47.52.241.237:9092/";//socket发布环境的IP端口
    public final static String MARKRT_CHOOSE = SOCKETHTTP + "choose";//自选socket
    public final static String MARKRT_SZ = SOCKETHTTP+ "marketValue";//市值socket
    public final static String MARKRT_TICKER= SOCKETHTTP+ "ticker";//市值二级socket

    /**
     * x协议
     */
    public  static  class WbUrl{
        public final static String webAiInstruction = URL_BASE2 + "aiInstruction";
        public final static String webBtcRecruit = URL_BASE2 + "btcRecruit";
        public final static String webAssetDelegation = URL_BASE2 + "assetDelegation";
        public final static String webInvestmentRisk = URL_BASE2 + "investmentRisk";
        public final static String webIcoProtocol = URL_BASE2 + "icoProtocol";
        public final static String webeEthAgreement = URL_BASE2 + "etfAgreement";
        public final static String webeHelp = URL_BASE2 + "main/help";
    }

}
