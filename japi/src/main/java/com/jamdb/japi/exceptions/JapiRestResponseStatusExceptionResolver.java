package com.jamdb.japi.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerMethodExceptionResolver;

import java.io.IOException;

public class JapiRestResponseStatusExceptionResolver extends AbstractHandlerMethodExceptionResolver {
    @Override
    protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, Exception ex) {
        try{
            if(ex instanceof IllegalArgumentException){
                return handleIllegalArgument(ex,response,handler);
            }
        }
    }
    private ModelAndView handleIllegalArgument (IllegalArgumentException ex, HttpServletResponse response) throws IOException{
        response.sendError(HttpServletResponse.SC_CONFLICT);
        String accept = response.getHeader(HttpHeaders.ACCEPT);

    }
}
