package com.amagana.settings_service.dto;

import com.amagana.settings_service.enums.StatusResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@ToString
public class ApiResponse<T>{
    private StatusResponse status;
    private String message;
    private List<ErrorsDTO> errors;
    private T results;
    
    public static <T> ApiResponse<T> single(StatusResponse status, T results) {
        return ApiResponse.<T>builder()
                .status(status)
                .results(results)
                .build();
    }

    public static <T> ApiResponse<List<T>> list(StatusResponse status,  List<T> results) {
        return ApiResponse.<List<T>>builder()
        		 .status(status)
                 .results(results)
                 .build();
    }
    
    public static <T> ApiResponse<T> singleMessage(StatusResponse status,  String message) {
        return ApiResponse.<T>builder()
        		 .status(status)
                 .message(message)
                 .build();
    }
    
    public static <T> ApiResponse<T> errors(StatusResponse status,  String message, List<ErrorsDTO> errors) {
        return ApiResponse.<T>builder()
        		 .status(status)
                 .message(message)
                 .errors(errors)
                 .build();
    }
}
