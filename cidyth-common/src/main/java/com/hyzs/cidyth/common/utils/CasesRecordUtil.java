package com.hyzs.cidyth.common.utils;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;

/**
 * Created by Administrator on 2018/3/10 0010.
 */
public class CasesRecordUtil {

    private static String endpoint = "http://10.42.0.34:7081/NoteOpenService.asmx";
    private static String namespace = "http://tempuri.org/";

    /**
     * 调用考拉系统获取电子笔录（不带正文）
     * @author 陈铭
     * @date 2018-05-31 16:43:06
     * @param strAJBH 案件编号
     * @param strRYBH 人员编号
     * @param strSFZH 身份证号
     * @param strPoliceNumber 警员编号
     * @param strUserLevel 用户级别
     * @return java.lang.String
     */
    public static String getRecordByCXTJ(String strAJBH, String strRYBH, String strSFZH, String strPoliceNumber, String strUserLevel){
        try{

            String methodName = "getRecordByCXTJ";
            String soapActionURI = namespace + methodName;

            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setSOAPActionURI(soapActionURI);
            call.setOperationName(new QName(namespace, methodName));

            call.addParameter(new QName(namespace, "strAJBH"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(namespace, "strRYBH"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(namespace, "strSFZH"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(namespace, "strPoliceNumber"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(namespace, "strUserLevel"), XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_STRING);

            String result = "";
            Object[] params = new Object[5];

            params[0] = strAJBH == null ? "" : strAJBH;
            params[1] = strRYBH == null ? "" : strRYBH;
            params[2] = strSFZH == null ? "" : strSFZH;
            params[3] = strPoliceNumber;
            params[4] = Integer.parseInt(strUserLevel);
            result = (String) call.invoke(params);

            /*// 1、只根据案件编号查询
            params[0] = strAJBH;
            params[1] = "";
            params[2] = "";
            params[3] = strPoliceNumber;
            params[4] = Integer.parseInt(strUserLevel);
            result = (String) call.invoke(params);

            //如果不为空，则返回
            if(!result.equals("[]")){
                return result;
            }

            //2、只根据身份证号查询
            params[0] = "";
            params[1] = "";
            params[2] = strSFZH;
            params[3] = strPoliceNumber;
            params[4] = Integer.parseInt(strUserLevel);
            result = (String) call.invoke(params);

            //如果不为空，则返回
            if(!result.equals("[]")){
                return result;
            }

            //2、只根据人员编号查询
            params[0] = "";
            params[1] = strRYBH;
            params[2] = "";
            params[3] = strPoliceNumber;
            params[4] = Integer.parseInt(strUserLevel);
            result = (String) call.invoke(params);*/

            return result;
        }catch(Exception e){
            System.out.println("调用WebService接口异常");
        }
        return null;
    }

    /**
     * 根据笔录编号获取笔录信息（附带正文）
     * @author 陈铭
     * @date 2018-05-31 17:13:06
     * @param strBLBH 笔录编号
     * @param strPoliceNumber 警员编号
     * @param strUserLevel 用户级别
     * @return java.lang.String
     */
    public static String getRecordByBLBH(String strBLBH, String strPoliceNumber, String strUserLevel){
        try{
            String methodName = "getRecordByBLBH";
            String soapActionURI = namespace + methodName;

            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setSOAPActionURI(soapActionURI);
            call.setOperationName(new QName(namespace, methodName));

            call.addParameter(new QName(namespace, "strBLBH"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(namespace, "strPoliceNumber"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(namespace, "strUserLevel"), XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_STRING);

            Object[] params = new Object[3];
            params[0] = strBLBH == null ? "" : strBLBH;
            params[1] = strPoliceNumber;
            params[2] = Integer.parseInt(strUserLevel);

            String result = (String) call.invoke(params);
            return result;
        }catch(Exception e){
            System.out.println("调用WebService接口异常");
        }
        return null;
    }

    public static void main(String[] args) {
        try{

            String methodName = "getRecordByCXTJ";
            String soapActionURI = namespace + methodName;

            Service service = new Service();
            Call call = (Call) service.createCall();
            call.setTargetEndpointAddress(endpoint);
            call.setSOAPActionURI(soapActionURI);
            call.setOperationName(new QName(namespace, methodName));

            call.addParameter(new QName(namespace, "strAJBH"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(namespace, "strRYBH"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(namespace, "strSFZH"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(namespace, "strPoliceNumber"), XMLType.XSD_STRING, ParameterMode.IN);
            call.addParameter(new QName(namespace, "strUserLevel"), XMLType.XSD_STRING, ParameterMode.IN);
            call.setReturnType(XMLType.XSD_STRING);

            Object[] params = new Object[5];
            params[0] = "A4403035900002018020003A";
            params[1] = "";
            params[2] = "";
            params[3] = "";
            params[4] = "";

            String result = (String) call.invoke(params);

            System.out.println("result = " + result);
        }catch(Exception e){
            System.out.println("调用WebService接口异常：" + e.getMessage());
        }
    }

}
