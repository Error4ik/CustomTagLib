package org.example.project.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class JsonTag extends TagSupport {
    private String json;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            out.print("<div>");
        } catch (IOException e) {
            throw new JspException(e);
        }
        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            out.print("</div>");
        } catch (IOException e) {
            throw new JspException(e);
        }
        return EVAL_PAGE;
    }

    @Override
    public int doAfterBody() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            out.print(String.format("<pre>%s</pre>", json));
        } catch (IOException e) {
            throw new JspException(e);
        }
        return SKIP_BODY;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
