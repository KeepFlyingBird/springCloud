package cn.freeFly.springcloud.dto.feign;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeignEntity {
    //零售订单号(projectId)
    @JSONField(name = "fcOrderNo")
    private String projectId;
    //一汽订单号
    @JSONField(name = "yqOrderNo")
    private String yqOrderNo;
    //期次
    @JSONField(name = "creditPeriod")
    private int period;
    //应收日期
    @JSONField(name = "pmtDueDate",format="yyyy/MM/dd")
    private String ysDate;
    //查询开始时间
    @JSONField(name = "startDate")
    private String queryStartDate;
    //应收本金
    @JSONField(name = "corpusReceivable")
    private double ysBenjin;
    //应收利息
    @JSONField(name = "interestReceivable")
    private double ysLixi;
    //应收罚息
    @JSONField(name = "penaltyInterestReceivable")
    private double ysFaxi;
    //实收本金
    @JSONField(name = "corpusReceived")
    private double ssBenjin;
    //实收利息
    @JSONField(name = "interestReceivede")
    private double ssLixi;
    //实收罚息
    @JSONField(name = "penaltyInterestReceived")
    private double ssFaxi;
    //逾期日期
    @JSONField(serialize = false, name = "interestPenaltyDate")
    private String yqDate;
    //逾期天数
    @JSONField(serialize = false, name = "dueDayNum")
    private int overdueDays;
    // 年利率
    @JSONField(serialize = false, name = "annualInterestRate")
    private double yearInterest;
    // 实收日期
    @JSONField(serialize = false, name = "recevieableDate")
    private String realReceivedDate;
}
