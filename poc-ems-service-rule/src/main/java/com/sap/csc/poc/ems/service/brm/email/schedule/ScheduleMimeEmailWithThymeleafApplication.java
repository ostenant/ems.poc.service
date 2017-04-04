package com.sap.csc.poc.ems.service.brm.email.schedule;

import java.io.UnsupportedEncodingException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;

@Configuration
// @EnableAsync
@Order(2)
public class ScheduleMimeEmailWithThymeleafApplication implements ApplicationContextAware {

	@Autowired
	private ScheduleThymeleafEmailService scheduleThymeleafEmailService;

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	// @PostConstruct
	public void schedulEmail() throws UnsupportedEncodingException, CannotSendEmailException, InterruptedException {
		scheduleThymeleafEmailService.scheduleMimeEmails();

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
