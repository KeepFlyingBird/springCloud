package cn.freeFly.springcloud.async;

import java.util.concurrent.Future;

public interface AsyncService {
    /**
     * 异步调用，无返回值
     */
    void asyncTaskNoResValue();

    /**
     * 异步调用，有返回值
     * @return
     */
    Future<String> asyncTaskResValue(String str);

    /**
     * 异步调用，无返回值，事务测试
     */
    void asyncTaskForTransaction(boolean exFlag);
}
