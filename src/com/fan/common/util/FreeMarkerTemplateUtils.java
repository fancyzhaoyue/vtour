package com.fan.common.util;

import java.io.IOException;
import java.io.StringWriter;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerTemplateUtils {
	public static String processTemplateIntoString(Template template, Object model) throws IOException, TemplateException
    {
        StringWriter result = new StringWriter();
        template.process(model, result);
        return result.toString();
    }

}
