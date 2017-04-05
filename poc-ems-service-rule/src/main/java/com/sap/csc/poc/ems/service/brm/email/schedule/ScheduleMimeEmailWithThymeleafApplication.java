package com.sap.csc.poc.ems.service.brm.email.schedule;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.sap.csc.poc.ems.service.brm.email.joint.Conspirator;
import com.sap.csc.poc.ems.service.brm.email.joint.MimeEmailBox;
import com.sap.csc.poc.ems.service.brm.email.joint.Originator;

import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;

@Configuration
@Order(2)
// @EnableAsync
public class ScheduleMimeEmailWithThymeleafApplication implements ApplicationContextAware {

	@Autowired
	private ScheduleThymeleafEmailService scheduleThymeleafEmailService;

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@PostConstruct
	public void schedulEmail() throws UnsupportedEncodingException, CannotSendEmailException, InterruptedException {
		MimeEmailBox mimeEmailBox = new MimeEmailBox();

		// Email subject
		mimeEmailBox.setSubject("赚多多");
		// Email sender
		Originator originator = new Originator("ostenant@163.com", "ostenant");
		mimeEmailBox.setOriginator(originator);
		// Email receivers
		List<Conspirator> conspirators = Lists.newArrayList( //
				new Conspirator("ostenant@163.com", "Ostenant"), //
				new Conspirator("Vincent.chen01@sap.com", "Vincent", 1));
		mimeEmailBox.setConspirators(conspirators);

		// Schedule priority
		// mimeEmailBox.setPriority(1);
		// Schedule time
		mimeEmailBox.setWhen(30L);

		// Email template path
		mimeEmailBox.setTemplateName("templates/emailTemplate.html");
		// Email Model object to render template
		Map<String, Object> modelObject = ImmutableMap.of(//
				"name", "Vincent", //
				"gender", "先生", //
				"account", "Vincent.chen01@sap.com", //
				"title", "赚多多");
		mimeEmailBox.setModelObject(modelObject);

		// Email attachments
		mimeEmailBox.setMimeEmailAttachments(null);
		// Email inline pictures
		mimeEmailBox.setMimeEmailPictures(null);

		// Email body. If it's a template email, then set it as empty
		scheduleThymeleafEmailService.scheduleMimeEmail(mimeEmailBox);

		close();
	}

	// @Async
	private void close() {
		TimerTask shutdownTask = new TimerTask() {
			@Override
			public void run() {
				((ConfigurableApplicationContext) applicationContext).close();
			}
		};
		Timer shutdownTimer = new Timer();
		shutdownTimer.schedule(shutdownTask, TimeUnit.SECONDS.toMillis(10));
	}

}
