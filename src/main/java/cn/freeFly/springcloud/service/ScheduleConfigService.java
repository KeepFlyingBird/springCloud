package cn.freeFly.springcloud.service;

import cn.freeFly.springcloud.entity.ScheduleConfig;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2020-06-19
 */
public interface ScheduleConfigService extends IService<ScheduleConfig> {
    List<ScheduleConfig> queryActiveTaskScheduleList();
}
