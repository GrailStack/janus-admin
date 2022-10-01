package org.xujin.janus.client.cmo;

/**
 * @author: gan
 * @date: 2020/6/12
 */
public class CallBackCmd {
    public static final String method = "callBack";
    private String requestMethod;
    private String requestID;
    private int processResult;
    private String errorMessage;

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public int getProcessResult() {
        return processResult;
    }

    public void setProcessResult(int processResult) {
        this.processResult = processResult;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
