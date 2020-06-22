package cn.freeFly.springcloud.config.rabbitMq;

import cn.freeFly.springcloud.config.async.AsyncConfig;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;

import javax.annotation.Resource;

@Configuration
public class RabbitMqConfig {
    @Resource(name = "taskExecutor")
    public TaskExecutor taskExecutor;
    /**
     * @Author xiao.yunfei
     * @Date 2019/12/17 19:01
     * @MethodParameters
     * @Description 创建Mq连接工厂类
     */
    @Bean("baseRabbitConnFactory")
    @Primary
    public ConnectionFactory baseRabbitConnFactory(
            @Value("${spring.rabbitmq.host}") String host,
            @Value("${spring.rabbitmq.port}") int port,
            @Value("${spring.rabbitmq.username}") String username,
            @Value("${spring.rabbitmq.password}") String password,
            @Value("${spring.rabbitmq.virtual-host}") String vHost){

        // Spring 发送消息的核心是JmsTemplate，JmsTemlate每次发送消息时都会重新创建连接，创建connection，session，创建productor
        // 使用连接池CachingConnectionFactory提高资源使用率
        CachingConnectionFactory connFactory = new CachingConnectionFactory();
        connFactory.setAddresses(host);
        connFactory.setPort(port);
        connFactory.setUsername(username);
        connFactory.setPassword(password);
        connFactory.setVirtualHost(vHost);
        return connFactory;
    }
    /**
     * @Author xiao.yunfei
     * @Date 2019/12/17 19:27
     * @MethodParameters
     * @Description 对队列进行管理，用于创建、绑定、删除队列与交换机，发送消息
     */
    @Bean("baseRabbitAdmin")
    @Primary
    public RabbitAdmin baseRabbitAdmin(
            @Qualifier("baseRabbitConnFactory") ConnectionFactory connFactory
    ){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connFactory);
        return rabbitAdmin;
    }
    /**
     * @Author xiao.yunfei
     * @Date 2019/12/17 19:35
     * @MethodParameters
     * @Description 生产者消息发送组件
     */
    @Bean("baseRabbitTemplate")
    @Primary
    public RabbitTemplate baseRabbitTemplate(
            @Qualifier("baseRabbitConnFactory") ConnectionFactory connFactory
    ){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connFactory);
        // 设置消息转换Json格式传输
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
    /**
     * @Author xiao.yunfei
     * @Date 2019/12/17 19:38
     * @MethodParameters
     * @Description RabbitMq监听器容器工厂
     */
    @Bean("baseRabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory baseRabbitListenerContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("baseRabbitConnFactory") ConnectionFactory connFactory
    ){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        // 设置消费者个数
        factory.setConcurrentConsumers(2);
        // 设置每次消费个数
        factory.setPrefetchCount(1);
        factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
        factory.setTaskExecutor(taskExecutor);
        configurer.configure(factory, connFactory);
        return factory;
    }
}
