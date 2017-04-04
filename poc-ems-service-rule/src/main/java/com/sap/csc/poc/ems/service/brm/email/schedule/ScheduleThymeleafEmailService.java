package com.sap.csc.poc.ems.service.brm.email.schedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.internet.InternetAddress;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.jooq.lambda.Unchecked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.sap.csc.poc.ems.service.brm.email.joint.Conspirator;
import com.sap.csc.poc.ems.service.brm.email.joint.MimeEmailAttachment;
import com.sap.csc.poc.ems.service.brm.email.joint.MimeEmailBox;
import com.sap.csc.poc.ems.service.brm.email.joint.MimeEmailInlinePicture;

import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.EmailAttachment;
import it.ozimov.springboot.mail.model.ImageType;
import it.ozimov.springboot.mail.model.InlinePicture;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmailAttachment;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultInlinePicture;
import it.ozimov.springboot.mail.service.SchedulerService;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;

@Service
public class ScheduleThymeleafEmailService {

	@Autowired
	private SchedulerService schedulerService;

	private final ExecutorService cachedThreadPool = Executors.newFixedThreadPool(5);

	public void scheduleMimeEmails(MimeEmailBox mimeEmailBox) {
		Preconditions.checkArgument(CollectionUtils.isEmpty(mimeEmailBox.getConspirators()),
				"Email receiver is not in presence");
		mimeEmailBox.getConspirators()
				// Parallel streaming destination email
				.parallelStream()
				// Process each in order
				.forEachOrdered(Unchecked.consumer(conspirator -> {
					final Email email = DefaultEmail.builder()
							// From address
							.from(new InternetAddress(
									// Original email
									mimeEmailBox.getOriginator().getEmail(),
									// Original Name
									mimeEmailBox.getOriginator().getName()))
							// To address
							.to(Lists.newArrayList(new InternetAddress(
									// Each destination email
									conspirator.getEmail(),
									// Each destination name
									conspirator.getName())))
							// Email subject
							.subject(mimeEmailBox.getSubject())
							// Email body. Otherwise, overridden by template.
							.body(mimeEmailBox.getBody())
							// Attachment
							.attachment(getCsvForecastAttachment("premium"))
							// Email encoding
							.encoding(mimeEmailBox.getCharset())
							// Build
							.build();
				}));
	}

	public void scheduleMimeEmails()
			throws UnsupportedEncodingException, CannotSendEmailException, InterruptedException {
		OffsetDateTime now = OffsetDateTime.now();
		OffsetDateTime later = now.plusMinutes(60);
		OffsetDateTime other = now.plusSeconds(5);
		while (other.isBefore(later)) {
			scheduleMimeEmailWithThymeleafBroadcast(other, 10);
			other = other.plusSeconds(8);
			Thread.sleep(3000);
		}

	}

	private void scheduleMimeEmailWithThymeleafBroadcast(OffsetDateTime when, int defaultPriority)
			throws UnsupportedEncodingException, CannotSendEmailException {
		InlinePicture inlinePicture = createGalaxyInlinePicture();

		final String template = "emailTemplate.html";
		Arrays.asList(
				// new Conspirator("827016252@qq.com", "余佳雨先生",
				// defaultPriority), //
				new Conspirator("1084204372@qq.com", "彭川先生", 1) //
		// new Conspirator("305014129@qq.com", "李孟川先生", defaultPriority), //
		// new Conspirator("441095845@qq.com", "李鲌苓先生", defaultPriority), //
		// new Conspirator("739823891@qq.com", "陈海鹏先生", defaultPriority), //
		// new Conspirator("1546622705@qq.com", "陈林先生", 1), //
		// new Conspirator("15982036509@163.com", "陈林先生", 1) //
		).stream().forEach(Unchecked.consumer(candidate -> {

			final Email email = DefaultEmail.builder()
					// From address
					.from(new InternetAddress("ostenant@163.com", "赚多多"))
					// To address
					.to(Lists.newArrayList(new InternetAddress(candidate.getEmail(), candidate.getName())))
					// Email subject
					.subject("恭喜您中奖了！！！")
					// Email body. Otherwise, will be overridden by
					// template.
					.body("")
					// Attachment
					.attachment(getCsvForecastAttachment("premium"))
					// Email encoding
					.encoding("UTF-8")
					// Build
					.build();

			Map<String, Object> modelObject = ImmutableMap.of(//
					"title", "赚多多", //
					"name", candidate.getName(), //
					"gender", "先生", //
					"account", candidate.getEmail()//
			);

			// Multi-thread
			cachedThreadPool.execute(() -> {
				try {

					// Schedule sending
					schedulerService.schedule(email, when, candidate.getPriority(), template, modelObject,
							inlinePicture);
				} catch (CannotSendEmailException e) {
					e.printStackTrace();
				}
			});

		}));

	}

	protected void scheduleMimeEmailWithThymeleaf(OffsetDateTime when, int priority)
			throws UnsupportedEncodingException, CannotSendEmailException {
		InlinePicture inlinePicture = createGalaxyInlinePicture();

		List<InternetAddress> toAddressList = new ArrayList<InternetAddress>();
		toAddressList.add(new InternetAddress("ostenant@163.com", "陈林先生"));

		final Email email = DefaultEmail.builder()
				// From address
				.from(new InternetAddress("ostenant@163.com", "赚多多"))
				// To address
				.to(toAddressList)
				// Email subject
				.subject("恭喜您中奖了！！！")
				// Email body. Otherwise, will be overridden by template.
				.body("")
				// Attachment
				.attachment(getCsvForecastAttachment("premium"))
				// Email encoding
				.encoding("UTF-8")
				// Build
				.build();

		final String template = "emailTemplate.html";

		Map<String, Object> modelObject = ImmutableMap.of(//
				"title", "赚多多", //
				"name", "陈林", //
				"gender", "先生", //
				"account", "ostenant@163.com"//
		);

		schedulerService.schedule(email, when, priority, template, modelObject, inlinePicture);
	}

	private InlinePicture createGalaxyInlinePicture() {
		ClassLoader classLoader = getClass().getClassLoader();

		File pictureFile = new File(classLoader.getResource("images/qlogo.jpg").getFile());

		Preconditions.checkState(pictureFile.exists(), "There is not picture %s", pictureFile.getName());

		return DefaultInlinePicture.builder()
				// Specify file
				.file(pictureFile)
				// Specify image type
				.imageType(ImageType.JPG)
				// Specify the file name to use in template
				.templateName("Expression.jpg")
				// Build
				.build();

	}

	private EmailAttachment getCsvForecastAttachment(String filename) {
		final String testData = "years from now,death probability\n1,0.9\n2,0.95\n3,1.0";
		return DefaultEmailAttachment.builder()
				// Specify attachment name in email
				.attachmentName(filename + ".csv")
				// Specify attachment data
				.attachmentData(testData.getBytes(Charset.forName("UTF-8")))
				// Specify attachment's media type
				.mediaType(MediaType.TEXT_PLAIN)
				// Build
				.build();
	}

	/**
	 * Build an email picture from MimeEmailInlinePicture
	 * 
	 * @param mimeEmailInlinePicture
	 * @return
	 */
	protected InlinePicture createInlinePicture(MimeEmailInlinePicture mimeEmailInlinePicture) {

		File pictureFile = mimeEmailInlinePicture.getImageFile();

		Preconditions.checkState(pictureFile.exists(), "There is not picture %s", pictureFile.getName());

		return DefaultInlinePicture.builder()
				// Specify file
				.file(pictureFile)
				// Specify image type
				.imageType(mimeEmailInlinePicture.getImageType())
				// Specify the file name to use in template
				.templateName(mimeEmailInlinePicture.getTemplateName())
				// Build
				.build();

	}

	/**
	 * Build an email attachment from MimeEmailAttachment
	 * 
	 * @param attachment
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	protected EmailAttachment getAttachment(MimeEmailAttachment attachment) throws FileNotFoundException, IOException {
		File attachmentFile = attachment.getAttachmentFile();

		Preconditions.checkState(attachmentFile.exists(), "There is not attachment %s", attachmentFile.getName());
		byte[] byteArray = IOUtils.toByteArray(new FileInputStream(attachmentFile));
		return DefaultEmailAttachment.builder()
				// Specify attachment name in email
				.attachmentName(attachment.getAttachmentName())
				// Specify attachment data
				.attachmentData(byteArray)
				// Specify attachment's media type
				.mediaType(attachment.getAttachmentMediaType())
				// Build
				.build();
	}

}
