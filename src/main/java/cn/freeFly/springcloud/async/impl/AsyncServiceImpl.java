package cn.freeFly.springcloud.async.impl;

import cn.freeFly.springcloud.async.AsyncService;
import cn.freeFly.springcloud.exception.AsyncException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.Future;

@Service
@Slf4j
public class AsyncServiceImpl implements AsyncService {

    @Override
    @Async
    public void asyncTaskNoResValue() {
        long startTime = System.currentTimeMillis();
        try {
            //模拟耗时
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("异步请求线程名称："+Thread.currentThread().getName() + "：asyncTaskNoResValue，耗时：" + (endTime - startTime));
    }

    @Override
    @Async(value = "taskExecutor")
    public Future<String> asyncTaskResValue(String str) {
        long startTime = System.currentTimeMillis();
        try {
            //模拟耗时
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("异步请求线程名称："+Thread.currentThread().getName() + "：asyncTaskResValue，耗时：" + (endTime - startTime));
        return AsyncResult.forValue(str);
    }

    @Override
    @Async(value = "taskExecutor")
    @Transactional
    public void asyncTaskForTransaction(boolean exFlag) {
        //新增一个用户

        if(exFlag){
            //模拟异常
            throw new AsyncException("模拟异常");
        }
    }
}
