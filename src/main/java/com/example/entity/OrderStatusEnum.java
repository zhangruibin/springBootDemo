package com.example.entity;


import com.example.service.GenericEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * 订单状态的枚举
 * 赵文吉
 */

public enum OrderStatusEnum implements GenericEnum {

    /*ORDER_SYSTEM_AUDIT("00", "订单系统审核"),
    LOGISTICS_REFUSAL("01", "物流拒送"),
    LOGISTICS_AUDIT_COMPLETED("02", "物流审核完成"),
    START_STOCKING("03", "开始备货"),
    HAVE_REPORTED("04", "已报竣"),
    HAVE_SHIPPED("05", "已发货"),
    HUNGUP("06", "挂起"),
    REFUSE_ACCEPT("07","拒收"),
    COMPLETE_FAIL("08","报竣失败");*/
    PRE_CHECKOUT_FAILURE("30184","预校验失败"),
    NO_START("00000", "未受理"),
    PAY_WAIT("10100", "等待支付"),
    PAY_SUCCESS("10101", "支付成功"),
    ORDER_SYSTEM_AUDIT("10102", "系统审核订单"),
    LOGISTICS_REFUSAL("60601", "物流拒送"),
    LOGISTICS_AUDIT_COMPLETED("60602", "物流审核完成"),
    HAVE_REPORTED("60603", "已报竣"),
    HUNGUP("60604", "挂起"),
    COMPLETE_FAIL("60606", "报竣失败"),
    ORDER_A_ZJ_AUDITFAIL("10103", "证件审核不通过"),
    REFUSE_ACCEPT("10104", "订单关闭"),
    START_STOCKING("10105", "开始备货"),
    ORDER_ZBPS("10106", "准备配送"),
    ORDER_JEDQR("10108", "订单金额待确认"),
    ORDER_IDCARD_RESUBMIT("10107", "证件已重新提交"),
    HAVE_SHIPPED("10111", "已发货"),
    ORDER_SUCCESS("10112", "交易完成"),
    ORDER_HWDDZTD("10120", "货物抵达自提点"),
    ORDER_AUDITFAIL("10124", "审核不通过"),
    ORDER_CANCEL("10202", "撤单中"),
    ORDER_INVALID("10204", "失效订单"),
    ORDER_CANCELFAIL("10206", "撤单失败"),
    ORDER_CANCELSUCCESS("10212", "撤单完成"),
    THSQDSH("10301", "申请待审核"),
    THZ("10302", "退货中"),
    TH_SHTG("10303", "退货审核通过"),
    TH_SHWTG("10304", "退货审核未通过"),
    TH_DDYHJSSW("10305", "等待用户寄送实物"),
    TH_SWSHZ("10306", "实物审核中"),
    TH_SB("10307", "退货失败"),
    TH_SWSHWTG("10308", "实物审核未通过"),
    TH_SQSHZ("10309", "申请审核中"),
    TH_TKSB("10311", "退款失败"),
    TH_THWC("10313", "退货完成"),
    HH_SQDSH("10401", "申请待审核"),
    HHZ("10402", "换货中"),
    HH_SHTG("10403", "换货审核通过"),
    HH_SHWTG("10404", "换货审核未通过"),
    HH_DDYHJSSW("10405", "换货已发货"),
    HH_SWSHZ("10406", "实物审核中"),
    HH_SB("10407", "换货失败"),
    HH_SQSHZ("10408", "申请审核中"),
    HH_SWSHWTG("10409", "实物审核未通过"),
    HH_YQS("10411", "换货已签收"),
    HH_WC("10412", "换货完成"),
    HH_JS("10415", "换货拒收"),
    HH_YJS("10541", "已拒收"),
    HH_TKSHZ("10545", "退款审核中"),
    HH_TKSHTG("10546", "退款审核通过"),
    HH_TKSHWTG("10547", "退款审核未通过"),
    YQX("10701", "已取消"),
    ZFZ("10702", "支付中"),
    ZFSB("10703", "支付失败"),
    CZZ("11104", "充值中"),
    CZ_CG("11105", "充值成功"),
    CZ_SB("11106", "充值失败"),
    CZ_JGQRZ("11107", "充值结果确认中"),
    CZ_GMCG("11108", "购买成功"),
    CZ_GMSB("11109", "购买失败"),
    CZ_TKZ("11201", "退款中"),
    CZ_TKCG("11202", "退款成功"),
    CZ_TKSB("11203", "退款失败"),
    ORDER_LOCK("11404", "订单锁定 "),
    ORDER_A_AUDITING("12001", "A类货到付款审核"),
    ORDER_A_AUDITSUCCESS("12002", "A类货到付款审核通过"),
    ORDER_A_AUDITFAIL("12003", "A类货到付款审核不通过"),
    ORDER_AUDIT_SUCCESS_NO1("12004", "货到付款-初审通过"),
    TRANSACT_ING("20100", "办理中"),
    TRANSACT_SUCCESS("20101", "办理成功"),
    TRANSACT_FAIL("20102", "办理失败"),
    ORDER_TKSB("30104", "退款失败"),
    AZZ("20200", "安装中"),
    AZ_CG("20201", "安装成功"),
    AZ_SB("20202", "安装失败"),
    UIM_ACTIVATING("30100", "激活中"),
    UIM_ACTIVATION_SUCCESS("30101", "激活成功"),
    UIM_ACTIVATION_FAILED("30102", "激活失败"),
    UIM_ACTIVATION_TRANSFINITE("13001", "超期未激活"),
    ORDER_EXPRIE("10704", "订单失效"),
    DEDUCT_MONEY_FAILED("30104", "扣款失败"),
    IDCARD_FIRST_TRIAL_FAILED("30105", "身份初审失败"),
    IDCARD_RECHECK_FAILED("30106", "身份复审失败"),
    IDCARD_RECHECK_SUCCESS("30107", "身份复审成功"),
    CARD_AUDITING("30110", "身份信息审核中"),
    CARD_AUDIT_SUCCESS("30111", "身份信息审核成功"),
    CARD_AUDIT_FAILED("30121", "身份信息审核失败"),
    ACTIVE_BUSINESS("30108", "去营业厅激活审核中"),
    ACTIVE_BUSINESS_HANDLING("30109", "去营业厅激活办理中"),
    ACTIVE_BUSINESS_FAILED("30120", "去营业厅激活审核失败"),
    NEED_BUSINESS_HANDLING("30130", "需营业厅办理"),
    INFORMATION_AUDIT_FAILURE("30205", "信息审核失败"),
    WAIT_PROV_ACTIVATION("21000", "待省激活"),
    PRE_CHECKLIST_SUCCESS("30185","预校验单成功"),
    COLSE_ORDER("60605", "异常关闭订单"),
    ORDER_GENERATION_WAITING_TO_UPLOAD("40100","订单生成等待上传");





    private String code;
    private String desc;

     OrderStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static OrderStatusEnum fromCode(String code) {
        return code != null && code.length() != 0 ? valueOfCodeEnum(code) : ORDER_SYSTEM_AUDIT;
    }
    public static OrderStatusEnum valueOfNameEnum(String name) {
        OrderStatusEnum[] iss = values();
        for (OrderStatusEnum cs : iss) {
            if (cs.equals(name)) {
                return cs;
            }
        }
        return null;
    }
    public static OrderStatusEnum valueOfDescEnum(String desc) {
        OrderStatusEnum[] iss = values();
        for (OrderStatusEnum cs : iss) {
            if (cs.getDesc().equals(desc)) {
                return cs;
            }
        }
        return null;
    }

    public static OrderStatusEnum valueOfCodeEnum(String code) {
        OrderStatusEnum[] iss = values();
        for (OrderStatusEnum cs : iss) {
            if (cs.getCode().equals(code)) {
                return cs;
            }
        }
        return null;
    }
    public static String toString(String code){
        String s = "";
        OrderStatusEnum[] iss = values();
        for (OrderStatusEnum cs : iss) {
            if (cs.getCode().equals(code)) {
                s = "{oederStatue:{code:"+cs.getCode()+",name:"+cs.name()+",desc:"+cs.desc+"}}";
            }
        }
        return s;
    }
}
