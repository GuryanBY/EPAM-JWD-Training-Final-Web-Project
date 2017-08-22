package com.epam.kgd.victory.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class FooterTag extends TagSupport {

	private static final long serialVersionUID = 3358496223142270053L;

	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			out.write("<div id=\"copyright\">");
			out.write("<div class=\"container\">");
			out.write("<p class=\"copyright\">");
			out.write("&copy; Copyright 2017. \"Alpha auction\" developed by ");
			out.write("<a href=\"http://www.facebook.com/user/\" rel=\"nofollow\">Guryan</a>.");
			out.write("</p></div></div>");
		} catch (IOException e) {
			throw new JspException(e);
		}
		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

}
