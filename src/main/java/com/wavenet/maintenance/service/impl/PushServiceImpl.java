/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: PushServiceImpl.java
 * @Package com.wavenet.maintenance.service.impl
 * @Description: (用一句话描述该文件做什么)
 * @author: admin
 * @date: 2020-05-14 15:03
 * @version V1.0
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.service.impl;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import com.alibaba.fastjson.JSON;
import com.wavenet.maintenance.dao.entity.PushInfo;
import com.wavenet.maintenance.service.PushService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @ClassName: PushServiceImpl
 * @Description: (这里用一句话描述这个类的作用)
 * @author: admin
 * @date: 2020-05-14 15:03     
 * @Copyright: 2020 www.wavenet.com.cn. All rights reserved.
 */
@Service
@Slf4j
public class PushServiceImpl implements PushService {
    private static JPushClient client = new JPushClient("f2701e28264cddd73dede39b", "15c755b4845ffd3f6adfffec");


    public PushResult pushCustomMessageS( String town) {
        PushPayload.Builder pushPayload = PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.all());
        //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
//        if (userId != null) {
//            pushPayload.setAudience(Audience.alias(userId));
//        } else {
            //pushPayload.setAudience(Audience.all());
            pushPayload.setAudience(Audience.tag(town));


        pushPayload.setNotification(Notification.alert("您有一条新消息,请注意查收"))
                .setNotification(Notification.android("请尽快处理", "你有一条新消息", null))
                .setMessage(Message.content("您有一条新消息,请注意查收"))
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build());
        //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
        // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
        // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
        try {
            return client.sendPush(pushPayload.build());
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public PushResult push(PushInfo info) throws Exception {

        String title = "您有一条新消息";
        String msg = "请尽快处理。";
        if (info.getType().equals("1")) {
            PushPayload pushPayload = PushPayload.newBuilder().setPlatform(Platform.all())
                    .setAudience(Audience.alias(info.getPersonCode()))
                    .setNotification(Notification.alert("青浦新消息通知"))
                    .setNotification(Notification.android(msg, title, null))
//                    .setNotification(Notification.ios(msg,null))
//                    .setNotification(Notification.ios_incr_badge(1))
                    .setMessage(Message.content("你有新单待处理"))
                    .setOptions(Options.newBuilder()
                            .setApnsProduction(true)
                            .build()).build();

            try {
                return client.sendPush(pushPayload);
            } catch (APIConnectionException e) {
                e.printStackTrace();
            } catch (APIRequestException e) {
                e.printStackTrace();
            }
        } else {
            PushPayload pushPayload = PushPayload.newBuilder().setPlatform(Platform.all())
                    .setAudience(Audience.all())
                    .setNotification(Notification.alert("青浦新消息通知"))
                    .setNotification(Notification.android(msg, title, null))
//                    .setNotification(Notification.ios(msg,null))
//                    .setNotification(Notification.ios_incr_badge(1))
                    .setMessage(Message.content("你有新单待处理"))
                    .setOptions(Options.newBuilder()
                            .setApnsProduction(true)
                            .build()).build();

            try {
                return client.sendPush(pushPayload);
            } catch (APIConnectionException e) {
                e.printStackTrace();
            } catch (APIRequestException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
