package com.jlxu.demo.mq;

import com.alibaba.fastjson.JSONObject;
import com.jlxu.demo.config.RocketMqProperties;
import com.jlxu.demo.model.TRegion;
import com.jlxu.demo.utils.RunTimeUtil;
import lombok.extern.slf4j.Slf4j;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.List;

@Slf4j
//@Component
public class RegionMQConsumer implements MessageListenerConcurrently {

    @Resource
    RocketMqProperties rocketMqProperties;

    private DefaultMQPushConsumer consumer;

    @Value("${group.tRegion}")
    private String groupName;

    @Value("${topic.tRegion}")
    private String topics;

    @Value("${tag.tRegion}")
    private String tags;

    private String host;

    @PostConstruct
    public void init() {
        try {
            log.info("MQ:开始服务商用户消费者");
            this.host = rocketMqProperties.getHost() + ":" + rocketMqProperties.getPort();
            consumer = new DefaultMQPushConsumer(groupName);
            consumer.setNamesrvAddr(host);
            // 从消息队列尾开始消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
            // 批量消费,每次拉取10条
            consumer.setConsumeMessageBatchMaxSize(10);
            // 集群消费模式
//            consumer.setMessageModel(MessageModel.CLUSTERING);
            //广播消费模式
            consumer.setMessageModel(MessageModel.BROADCASTING);
            // 订阅主题
            consumer.subscribe(topics, tags);
            //设置实例
            consumer.setInstanceName(RunTimeUtil.getRocketMqUniqeInstanceName());
            // 注册消息监听器
            consumer.registerMessageListener(this);

            // 启动消费端
            consumer.start();
            log.info("MQ:启动服务商用户消费者完成");
        } catch (MQClientException e) {
            log.error("MQ：启动服务商用户消费者失败：{}-{}", e.getResponseCode(), e.getErrorMessage());
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        int index = 0;
        try {
            for (; index < msgs.size(); index++) {
                MessageExt msg = msgs.get(index);
                String messageBody = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
                TRegion tRegion = JSONObject.parseObject(messageBody, TRegion.class);
                log.debug("接受组织消息成功: {}", JSONObject.toJSONString(tRegion));
                log.info("MQ：服务商用户消费者接收新信息: {} {} {} {} {}", msg.getMsgId(), msg.getTopic(), msg.getTags(), msg.getKeys(), messageBody);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (index < msgs.size()) {
                context.setAckIndex(index + 1);
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    @PreDestroy
    public void stop() {
        if (consumer != null) {
            consumer.shutdown();
            log.error("MQ：关闭服务商用户消费者");
        }
    }
}
