package com.maizhong.platform.dao;

import javax.jms.Destination;

/**
 * Created by Xushd on 2017/9/21.
 */
public interface ActiveMqProducer {

    void sendMessage(Destination destination, final String msg);
}
