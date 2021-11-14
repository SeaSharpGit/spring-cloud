package com.sea.springcloud.common.sms.entity;

import lombok.Data;

@Data
public class SmsResult {

    private String Message;

    private String RequestId;

    private String Code;

    public boolean isSuccess() {
        return Code.equals("OK");
    }

}
