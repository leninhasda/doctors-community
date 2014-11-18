package com.jonak.lib;

// import defaults
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lenin on 11/19/14.
 */
public class Tools {

    // get the request parameter
    public static String get(String name) throws Exception
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        if( request.getParameterMap().containsKey( name ) ) {
            return request.getParameter(name);
        } else {
            return null;
        }
    }

    // redirects to a location
    public static void redirect( String url ) throws Exception
    {
        HttpServletResponse response = ServletActionContext.getResponse();

        try {
            response.sendRedirect(url);
        } catch (Exception ex) {
            throw new RuntimeException("error redirecting: "+ex.getMessage());
        }
    }
}
