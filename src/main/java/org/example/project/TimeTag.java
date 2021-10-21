package org.example.project;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.time.LocalDateTime;

public class TimeTag extends BodyTagSupport {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            out.print(LocalDateTime.now());
            out.print("<br/>");
        } catch (IOException e) {
            throw new JspException(e);
        }

        return SKIP_BODY;
    }
}
