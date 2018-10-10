package com.example.demo;

import com.example.demo.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private MailService mailService;

	@Autowired
	private TemplateEngine templateEngine;

	@Test
	public void testSimpleMail() throws Exception {
		mailService.sendSimpleMail("alex_hu@wistronits.com","test simple mail"," hello this is simple mail");
	}

	@Test
	public void testHtmlMail() throws Exception {
		String content="<html>\n" +
				"<body>\n" +
				"    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
				"</body>\n" +
				"</html>";
		mailService.sendHtmlMail("alex_hu@wistronits.com","test simple mail",content);
	}

	@Test
	public void sendTemplateMail() {
		//创建邮件正文
		Context context = new Context();
		context.setVariable("id", "006");
		String emailContent = templateEngine.process("emailTemplate", context);

		mailService.sendHtmlMail("alex_hu@wistronits.com","主题：这是模板邮件",emailContent);
	}

}
