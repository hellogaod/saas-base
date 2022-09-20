package com.base.saas.common.exception;

import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author HLH
 * @description 自定义参数异常
 * @email 17703595860@163.com
 * @date Created in 2021/8/10 下午10:56
 */
@Getter
public class ParamException extends CustomizeException {

    private List<String> fieldList;
    private List<String> msgList;

    public ParamException(String message) {
        super(message);
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamException(List<String> fieldList, List<String> msgList) throws CustomizeException {
        super(generatorMessage(fieldList, msgList));
        this.fieldList = fieldList;
        this.msgList = msgList;
    }

    public ParamException(List<String> fieldList, List<String> msgList, Exception ex) throws CustomizeException {
        super(generatorMessage(fieldList, msgList), ex);
        this.fieldList = fieldList;
        this.msgList = msgList;
    }

    private static String generatorMessage(List<String> fieldList, List<String> msgList) throws CustomizeException {
        if (CollectionUtils.isEmpty(fieldList) || CollectionUtils.isEmpty(msgList) || fieldList.size() != msgList.size()) {
            return "参数错误";
        }

        StringBuilder message = new StringBuilder();
        for (int i = 0; i < fieldList.size(); i++) {
            String field = fieldList.get(i);
            String msg = msgList.get(i);
            if (i == fieldList.size() - 1) {
                message.append(field).append(":").append(msg);
            } else {
                message.append(field).append(":").append(msg).append(",");
            }
        }
        return message.toString();
    }


}
