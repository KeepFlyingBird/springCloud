package cn.freeFly.springcloud.service.impl;

import cn.freeFly.springcloud.mapper.ScheduleConfigMapper;
import cn.freeFly.springcloud.entity.ScheduleConfig;
import cn.freeFly.springcloud.service.ScheduleConfigService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2020-06-19
 */
@Service
public class ScheduleConfigServiceImpl extends ServiceImpl<ScheduleConfigMapper, ScheduleConfig> implements ScheduleConfigService {
    @Autowired
    public ScheduleConfigMapper scheduleConfigMapper;

    @Override
    public List<ScheduleConfig> queryActiveTaskScheduleList() {
        QueryWrapper<ScheduleConfig> queryWrapper = new QueryWrapper();
        queryWrapper.eq("status","0");
        return scheduleConfigMapper.selectList(queryWrapper);
    }
}
