package com.hxb.smartspace.common;

import org.slf4j.helpers.MessageFormatter;

/**
 * @author Zhao Jinyan
 * @date 2019/2/12 15:41
 */
public class SmartSpaceException extends Exception {

    public SmartSpaceException() {
    }

    public SmartSpaceException(String message) {
        super(message);
    }

    public SmartSpaceException(String message, Object ... args){
        super(MessageFormatter.arrayFormat(message, args).getMessage());
    }

}
