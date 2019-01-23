package com.hyzs.cidyth.common.utils;

import com.hyzs.cidyth.common.enums.AnalysisNodeEnum;
import com.hyzs.cidyth.common.enums.DemandStatus;
import com.hyzs.cidyth.common.enums.TableTypeEnum;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.commons.lang3.StringUtils;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import java.util.List;

/**
 * Created by Administrator on 2018/3/10 0010.
 */
public class MessageUtil {

    private static String endpoint = "http://10.42.62.168:9999/SmsPlatformWebService.asmx";
    private static String actionURI = "http://tempuri.org/";
    private static String CID_Spid = "168";//开户信息：spid
    private static String CID_Username = "xjzdsms";//开户信息：账号
    private static String CID_Password = "idz74x2c";//开户信息：密码
    private static String CID_SessionId = "";//连接ID
    private static String GETRANDOM = "GetRandom";//获取随机数方法名
    private static String REGIST = "RegistCallbackAddr";//注册地址回调函数,获取连接ID
    private static String SENDSMS = "SendSMS";//注册地址回调函数方法名

    /**
     * 请求CID短信平台接口GetRandom方法
     * @return 成功返回 > 0 的随机数，失败返回 -1
     */
    private static String getRandom(){
        try {
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setSOAPActionURI(actionURI + GETRANDOM);
            call.setTargetEndpointAddress(endpoint);
            call.setOperationName(GETRANDOM);
            call.setReturnType(XMLType.XSD_STRING);

            //得到接口返回的随机数
            String result = (String) call.invoke(new Object[] {});
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
    }

    /**
     * 注册第三方WebService的URL到CID短信平台
     * @return 成功返回>0的值为连接通道ID，<0为失败码
     */
    private static String registCallBack(String rand){
        try {
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setSOAPActionURI(actionURI + REGIST);
            call.setOperationName(new QName(actionURI, REGIST));
            call.addParameter(new QName(actionURI, "uc"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(actionURI, "pw"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(actionURI, "spid"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(actionURI, "rand"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(actionURI, "url"), XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_STRING);
            //添加参数
            Object[] params = new Object[5];
            params[0] = CID_Username;
            params[1] = MD5Util.md5(rand + CID_Spid + CID_Password);
            params[2] = CID_Spid;
            params[3] = rand;
            params[4] = "";

            //得到接口返回的随机数
            CID_SessionId = (String) call.invoke(params);
            System.out.println("CID_SessionId = " + CID_SessionId);
            return CID_SessionId;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 发送短信
     * @param mobiles 接收手机号
     * @param deptName 单位名称
     * @param userName 发送人
     * @param sendContent 发送内容
     * @return
     */
    public static boolean sendSMS(String mobiles, String deptName, String userName, String sendContent) {
        return sendSMS(mobiles, deptName, userName, sendContent, null);
    }

    /**
     * 发送短信
     * @param mobiles 接收的号码
     * @param sendContent 发送内容
     * @return 返回boolean，true代表成功，false代表失败。
     */
    public static boolean sendSMS(String mobiles, String deptName, String userName, String sendContent, Enum objEnum){
        if(objEnum != null){
            sendContent = getMessageFormat(deptName, userName, sendContent, objEnum);
        }
        //如果手机号码或内容都是空，则返回失败
        if(StringUtils.isEmpty(mobiles) || StringUtils.isEmpty(sendContent)){
            return false;
        }
        try {
            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setSOAPActionURI(actionURI + SENDSMS);
            call.setTargetEndpointAddress(endpoint);
            call.setOperationName(new QName(actionURI, SENDSMS));
            call.addParameter(new QName(actionURI, "uc"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(actionURI, "pw"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(actionURI, "spid"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(actionURI, "rand"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(actionURI, "source"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(actionURI, "callee"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(actionURI, "cont"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(actionURI, "msgid"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(actionURI, "sessionid"), XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_STRING);

            String rand = getRandom();

            //添加参数
            Object[] params = new Object[9];
            params[0] = CID_Username;
            params[1] = MD5Util.md5(rand + CID_Spid + CID_Password);
            params[2] = CID_Spid;
            params[3] = rand;
            params[4] = CID_Spid;
            params[5] = mobiles;
            params[6] = MD5Util.getBase64(sendContent);
            params[7] = createRandom(true, 6);
            params[8] = registCallBack(getRandom());

            //得到接口返回的随机数
            String result = (String) call.invoke(params);
            System.out.println("发送短信返回代码：result = " + result);
            if(Integer.parseInt(result) == 0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 创建指定数量的随机字符串
     *
     * @param numberFlag 是否是数字
     * @param length
     * @return
     */
    public static String createRandom(boolean numberFlag, int length) {
        String retStr = "";
        String strTable = numberFlag ? "1234567890" : "1234567890abcdefghijkmnpqrstuvwxyz";
        int len = strTable.length();
        boolean bDone = true;
        do {
            retStr = "";
            int count = 0;
            for (int i = 0; i < length; i++) {
                double dblR = Math.random() * len;
                int intR = (int) Math.floor(dblR);
                char c = strTable.charAt(intR);
                if (('0' <= c) && (c <= '9')) {
                    count++;
                }
                retStr += strTable.charAt(intR);
            }
            if (count >= 2) {
                bDone = false;
            }
        } while (bDone);
        return retStr;
    }

    /**
     * 格式化发送信息
     * @author 陈铭
     * @date 2018-05-17 20:05:31
     * @param sendMessage
     * @param objEnum
     * @return java.lang.String
     */
    public static String getMessageFormat(String deptName, String userName, String sendMessage, Enum objEnum){
        String sendPrefix = "";
        if(StringUtils.isNotEmpty(deptName)){
            sendPrefix = deptName + "的" + userName;
        }else{
            sendPrefix = userName;
        }

        // 发布需求
        if(objEnum.name().equals(DemandStatus.INIT.name())){
            return "创建需求：" + sendPrefix  + "向您发送了一条需求：" + sendMessage + "。请在12小时内进行指派，谢谢！";
        }
        // 指派需求
        if(objEnum.name().equals(DemandStatus.ALLOCATED.name())){
            return "指派：您收到一条情报超市任务：" + sendMessage + "。请在24小时内进行签收，并在5个工作日内进行反馈，谢谢！";
        }
        // 反馈线索
        if(objEnum.name().equals(DemandStatus.FEEDBACKED.name())){
            return "反馈线索：" + sendPrefix + "向您反馈了一条线索：" + sendMessage + "，请对该反馈线索进行点评吧，谢谢！";
        }
        // 下发研判线索
        if(objEnum.name().equals(AnalysisNodeEnum.WAIT_ALLOCATE.name())){
            return "下发：" + sendPrefix + "向您下发串并案研判线索：" + sendMessage + "，请您尽快指派，谢谢！";
        }
        // 签收研判线索
        if(objEnum.name().equals(AnalysisNodeEnum.WAIT_SIGN.name())){
            return "签收：" + sendPrefix + "向您指派串并案研判线索：" + sendMessage + "，请您尽快签收，谢谢！";
        }
        return "";
    }

}
