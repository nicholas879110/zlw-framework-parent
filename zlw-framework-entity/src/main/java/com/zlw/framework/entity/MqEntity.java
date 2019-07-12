package com.zlw.framework.entity;


import java.util.UUID;

public class MqEntity<T> extends BaseEntity<T> {

    private static final long serialVersionUID = -3274450090127362151L;

    /**
     * 消息ID
     */
    protected final String messageId = UUID.randomUUID().toString();

    public String getMessageId() {
        return messageId;
    }

}
