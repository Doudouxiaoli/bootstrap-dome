package com.kingyee.starter;

import com.kingyee.common.mail.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhl on 2019/12/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {

    @Autowired
    private MailService mailService;

    @Test
    public void sendMail() {
        System.out.println("send");
        mailService.sendSimpleMail("zhl@kingyee.com.cn", "Spring boot 项目-" + SystemConfig.getProjectName(), "发送邮件功能");
    }

    @Test
    public void testHtmlMail() {
        String content = "<html>\n" +
                "<body>\n" +
                "    <h3 style='color:red'>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("zhl@kingyee.com.cn", "Spring boot 项目-" + SystemConfig.getProjectName(), content);
    }

    /*@Test
    public void sendAttachmentsMail() {
        String filePath = "D:\\不良反应.pdf";
        mailService.sendAttachmentsMail("zhl@kingyee.com.cn", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }*/

    @Test
    public void sendTemplateMail() {
        //模板名称
        String tpl = "email.html";
        //模板参数
        Map<String, Object> data = new HashMap<>();
        data.put("name", "测试模板");
        mailService.sendTemplateMail("zhl@kingyee.com.cn", "测试模板邮件-" + SystemConfig.getProjectName(), tpl, data);
    }
}
