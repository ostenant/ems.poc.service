package com.sap.csc.poc.ems.service.brm.email.immediate;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.EmailAttachment;
import it.ozimov.springboot.mail.model.ImageType;
import it.ozimov.springboot.mail.model.InlinePicture;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmail;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultEmailAttachment;
import it.ozimov.springboot.mail.model.defaultimpl.DefaultInlinePicture;
import it.ozimov.springboot.mail.service.EmailService;
import it.ozimov.springboot.mail.service.exception.CannotSendEmailException;

@Service
public class SimpleThymeleafEmailService {

	@Autowired
	private EmailService emailService;

	public void sendMimeEmailWithThymeleaf() throws UnsupportedEncodingException, CannotSendEmailException {
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

		emailService.send(email, template, modelObject, inlinePicture);
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

}
