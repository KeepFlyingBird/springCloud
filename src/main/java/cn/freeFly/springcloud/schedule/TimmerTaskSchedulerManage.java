package cn.freeFly.springcloud.schedule;

import cn.freeFly.springcloud.entity.ScheduleConfig;
import cn.freeFly.springcloud.service.ScheduleConfigService;
import cn.freeFly.springcloud.utils.ApplicationContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Component
public class TimmerTaskSchedulerManage {
    @Autowired
    private ScheduleConfigService scheduleConfigService;
    @Autowired
    private ThreadPoolTaskScheduler timmerTaskThreadPoolTaskScheduler;

    private Map<String, ScheduledFuture<?>> futuresMap = new ConcurrentHashMap<String, ScheduledFuture<?>>();

    @Bean
    public ThreadPoolTaskScheduler timmerTaskThreadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        //核心线程数
        threadPoolTaskScheduler.setPoolSize(5);
        return threadPoolTaskScheduler;
    }
    @Bean
    public void initTimmer(){
        List<ScheduleConfig> list = scheduleConfigService.queryActiveTaskScheduleList();
        for (ScheduleConfig s : list){
            ScheduledFuture<?> future = timmerTaskThreadPoolTaskScheduler.schedule(getRunnable(s), getTrigger(s));
            futuresMap.put(s.getJobName(), future);
        }
    }
    /**
     * 添加任务
     * @param s
     */
    public void addTask(ScheduleConfig s){
        ScheduledFuture<?> future = timmerTaskThreadPoolTaskScheduler.schedule(getRunnable(s), getTrigger(s));
        futuresMap.put(s.getJobName(), future);
    }

    /**
     * 暂停任务
     * @param key
     * @return
     */
    public boolean pauseeTask(String key) {
        ScheduledFuture toBeRemovedFuture = futuresMap.remove(key);
        if (toBeRemovedFuture != null) {
            toBeRemovedFuture.cancel(true);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 更新任务
     * @param s
     */
    public void updateTask(ScheduleConfig s) {
        ScheduledFuture toBeRemovedFuture = futuresMap.remove(s.getJobName());
        if (toBeRemovedFuture != null) {
            toBeRemovedFuture.cancel(true);
        }
        addTask(s);
    }

    private Runnable getRunnable(ScheduleConfig scheduleConfig){
        return new Runnable() {
            @Override
            public void run() {
                Class<?> clazz;
                try {
                    clazz = Class.forName(scheduleConfig.getClassName());
                    String className = lowerFirstCapse(clazz.getSimpleName());
                    Object bean = (Object) ApplicationContextHelper.getBean(className);
                    Method method = ReflectionUtils.findMethod(bean.getClass(), scheduleConfig.getMethod());
                    ReflectionUtils.invokeMethod(method, bean);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        };
    }
    private Trigger getTrigger(ScheduleConfig scheduleConfig){
        return new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                CronTrigger trigger = new CronTrigger(scheduleConfig.getCron());
                Date nextExec = trigger.nextExecutionTime(triggerContext);
                return nextExec;
            }
        };

    }
    /**
     * 转换首字母小写
     *
     * @param str
     * @return
     */
    public static String lowerFirstCapse(String str) {
        char[] chars = str.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
