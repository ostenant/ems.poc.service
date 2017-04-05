package com.sap.csc.poc.ems.service.brm.email.immediate;

import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.annotation.Order;

import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;

@Configuration
@Order(1)
public class SimpleMimeEmailWithThymeleafApplication implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Autowired
	private SimpleThymeleafEmailService simpleThymeleafEmailService;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	// @PostConstruct
	public void sendEmail() throws UnsupportedEncodingException, InterruptedException, CannotSendEmailException {
		// Send email
		simpleThymeleafEmailService.sendMimeEmailWithThymeleaf();

		// Close context 10 seconds later than sending email
		close();
	}

	public void close() {
		TimerTask shutdownTask = new TimerTask() {

			@Override
			public void run() {
				((AbstractApplicationContext) applicationContext).close();
			}
		};
		Timer timer = new Timer();
		timer.schedule(shutdownTask, TimeUnit.SECONDS.toMillis(10));
	}

}
