package com.didispace.filter;

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;

/**
 * @author 翟永超
 * @create 2016/12/15.
 * @blog http://blog.didispace.com
 */
public class DidiErrorAttributes extends DefaultErrorAttributes {

//    @Override
//    public Map<String, Object> getErrorAttributes(RequestAttributes requestAttributes, boolean includeStackTrace) {
//        Map<String, Object> result = super.getErrorAttributes(requestAttributes, includeStackTrace);
//        result.remove("exception");
//        return result;
//    }

}
