package org.example.project;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class TextBodyTag extends TagSupport {
    private int iterations;
    private int value;
    private int count;
    private String json;

    @Override
    public int doStartTag() throws JspException {
        count = 0;

        JspWriter out = pageContext.getOut();

        try {
            out.print("<table border='1'><tbody>");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return EVAL_BODY_INCLUDE;
    }

    @Override
    public int doEndTag() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            out.print("</tbody></table>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return EVAL_PAGE;
    }

    @Override
    public int doAfterBody() throws JspException {
        JspWriter out = pageContext.getOut();

        try {
            count++;
            out.print(String.format("<tr><td>Row number #%s</td></tr>", json));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (count > iterations) {
            return SKIP_BODY;
        } else {
            return EVAL_BODY_AGAIN;
        }
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
