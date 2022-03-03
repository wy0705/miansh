package com.lmxdawn.him.api.db.message;//package com.mf.game.engine.db.message;
//
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.impl.client.DefaultHttpClient;
//
//import java.util.Date;
//
//public class SendMessage {
//    //发送验证码的请求路径URL
//    private static final String
//            SERVER_URL="https://api.netease.im/sms/sendcode.action";
//    //网易云信分配的账号，请替换你在管理后台应用下申请的Appkey
//    private static final String
//            APP_KEY="545ab725fbfe94fa3585bf24ba18a889";
//    //网易云信分配的密钥，请替换你在管理后台应用下申请的appSecret
//    private static final String APP_SECRET="5862f3168049";
//    //随机数
//    private static final String NONCE="123456";
//    //短信模板ID
//    private static final String TEMPLATEID="3094133";
//    //手机号
//    //private static final String MOBILE="13812186059";
//    //验证码长度，范围4～10，默认为4
//    private static final String CODELEN="4";
//
//
//    public static String sendMess(String phone) throws Exception {
//
//
//        DefaultHttpClient httpClient = new DefaultHttpClient();
//        HttpPost httpPost = new HttpPost(SERVER_URL);
//        String curTime = String.valueOf((new Date()).getTime() / 1000L);
//        /*
//         * 参考计算CheckSum的java代码，在上述文档的参数列表中，有CheckSum的计算文档示例
//         */
//        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);
//
//
//        // 设置请求的header
//        httpPost.addHeader("AppKey", APP_KEY);
//        httpPost.addHeader("Nonce", NONCE);
//        httpPost.addHeader("CurTime", curTime);
//        httpPost.addHeader("CheckSum", checkSum);
//        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//
//
//        // 设置请求的的参数，requestBody参数
//        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//        /*
//         * 1.如果是模板短信，请注意参数mobile是有s的，详细参数配置请参考“发送模板短信文档”
//         * 2.参数格式是jsonArray的格式，例如 "['13888888888','13666666666']"
//         * 3.params是根据你模板里面有几个参数，那里面的参数也是jsonArray格式
//         */
//        nvps.add(new BasicNameValuePair("templateid", TEMPLATEID));
//        nvps.add(new BasicNameValuePair("mobile", phone));
//        nvps.add(new BasicNameValuePair("codeLen", CODELEN));
//
//
//        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
//
//
//        // 执行请求
//        HttpResponse response = httpClient.execute(httpPost);
//        /*
//         * 1.打印执行结果，打印结果一般会200、315、403、404、413、414、500
//         * 2.具体的code有问题的可以参考官网的Code状态表
//         *
//         *
//         * {"code":200,"msg":"1","obj":"8588"}
//         */
//
//        String result = EntityUtils.toString(response.getEntity(), "utf-8");
//        //将输出结果进行解析，获取发送出去的验证码，例如上述中的“8588”。
//        String[] results = result.split(":");
//        String _result =results[3].substring(1, 5);
//
//        return _result;
//    }
//}