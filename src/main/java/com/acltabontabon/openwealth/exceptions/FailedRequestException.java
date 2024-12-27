package com.acltabontabon.openwealth.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClientException;

public class FailedRequestException extends RestClientException {

    @Getter
    private HttpStatusCode statusCode;

    public FailedRequestException(String msg, HttpStatusCode statusCode) {
        super(msg);
        this.statusCode = statusCode;
    }

    public FailedRequestException(String msg, Throwable ex) {
        super(msg, ex);
    }

    public String getStatusMessage() {
        return switch (statusCode.value()) {
            case 400 -> "Bad Request - The request could not be understood by the server due to malformed syntax.";
            case 401 -> "Unauthorized - The request has not been applied because it lacks valid authentication credentials for the target resource.";
            case 403 -> "Forbidden - The server understood the request but refuses to authorize it.";
            case 404 -> "Not Found - The origin server did not find a current representation for the target resource or is not willing to disclose that one exists.";
            case 405 -> "Method Not Allowed - The method received in the request-line is known by the origin server but not supported by the target resource.";
            default -> "Unknown";
        };
    }

    @Override
    public String toString() {
        return getStatusMessage();
    }
}
