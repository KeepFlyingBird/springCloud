package cn.freeFly.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2020-06-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("cacmp_schedule_config")
@ApiModel(value="ScheduleConfig对象", description="")
public class ScheduleConfig extends Model<ScheduleConfig> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String jobName;

    private String className;

    private String method;

    private String cron;

    @ApiModelProperty(value = "0 代表开启，1代表关闭")
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
