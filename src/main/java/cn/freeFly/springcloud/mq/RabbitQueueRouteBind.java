package cn.freeFly.springcloud.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class RabbitQueueRouteBind {
    @Value("${exchange.test}")
    private String exchangeTest;

    @Value("${queue.test}")
    private String queueTest;
    @Value("${routingKey.test}")
    private String routingKeyTest;

    /**
     * 创建Exchange，建立routingkey和queue关系
     queue 队列声明 - 消息队列
     durable:是否持久化
     exclusive: 仅创建者可以使用的私有队列，断开后自动删除，默认为false
     auto_delete: 当所有消费客户端连接断开后，是否自动删除队列，默认为false
     */
    @Bean
    public void TestQueueRouteBind(@Qualifier("baseConnectionFactory") ConnectionFactory connectionFactory) {
        //创建exchange
        TopicExchange topicExchange = new TopicExchange(exchangeTest);
        Queue queue = new Queue(queueTest, true);
        Binding queueBinding = BindingBuilder.bind(queue).to(topicExchange).with(routingKeyTest);

        //声明交换器、队列、绑定规则
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        rabbitAdmin.declareExchange(topicExchange);
        rabbitAdmin.declareQueue(queue);
        rabbitAdmin.declareBinding(queueBinding);
    }
}
