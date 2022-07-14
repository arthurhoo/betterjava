package com.huhao.code.advancejava.templates;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Arthur Hu
 * @date: 2020/5/10 下午10:34
 * Description:
 */
public class FreeMarkerTemplateTest {

    @Test
    public void test_parse() throws IOException, TemplateException {
        String templateStr = "标题：${title}\n" +
                "\n" +
                "采购订单${orderId}的在制品正在从${from}(发出地)发往${toWarehouse}(目的地)，发货单号${sendOrderId},请注意签收！";
        Map<String,String> valueMap = new HashMap<String, String>();
        valueMap.put("title","厂内在制品发货通");
        valueMap.put("orderId","1233322");
        valueMap.put("from","水洗工段");
        valueMap.put("toWarehouse","车缝工段");
        valueMap.put("sendOrderId","876565");
        Configuration configuration = new Configuration();
        StringWriter out = new StringWriter();
        Template messageTemplate = new Template("firstTemplate",templateStr,configuration);
        messageTemplate.process(valueMap,out);
        String outStr = out.toString();
        System.out.println(outStr);
    }
}
