package com.sap.csc.poc.ems.service.brm.email.schedule;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.internet.InternetAddress;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.jooq.lambda.Unchecked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.sap.csc.poc.ems.service.brm.email.joint.MimeEmailAttachment;
import com.sap.csc.poc.ems.service.brm.email.joint.MimeEmailBox;
import com.sap.csc.poc.ems.service.brm.email.joint.MimeEmailInlinePicture;

import it.ozimov.springboot.mail.model.Email;
import it.ozimov.springboot.mail.model.EmailAttachment;
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

	private final ExecutorService executors = Executors.newFixedThreadPool(5);

	/**
	 * Schedule email with given MimeEmailBox
	 * 
	 * @param mimeEmailBox
	 * @throws UnsupportedEncodingException
	 * @throws CannotSendEmailException
	 * @throws InterruptedException
	 */
	public void scheduleMimeEmail(MimeEmailBox mimeEmailBox)
			throws UnsupportedEncodingException, CannotSendEmailException, InterruptedException {
		Long intervalSeconds = mimeEmailBox.getWhen();
		OffsetDateTime now = OffsetDateTime.now();
		OffsetDateTime scheduleTime = now.plusSeconds(intervalSeconds);
		scheduleMimeEmail(mimeEmailBox, scheduleTime);

	}

	/**
	 * Send email with given MimeEmailBox
	 * 
	 * @param mimeEmailBox
	 * @param scheduleTime
	 */
	public void scheduleMimeEmail(MimeEmailBox mimeEmailBox, OffsetDateTime scheduleTime) {
		// Check email destination
		Preconditions.checkArgument(CollectionUtils.isEmpty(mimeEmailBox.getConspirators()),
				"Email receiver is not in presence");

		// Sending to all conspirators
		mimeEmailBox.getConspirators()
				// Parallel streaming destination email
				.parallelStream()
				// Process each in order
				.forEachOrdered(Unchecked.consumer(conspirator -> {
					// Attachment Information
					List<MimeEmailAttachment> mimeEmailAttachments = mimeEmailBox.getMimeEmailAttachments();
					// Attachments
					final List<DefaultEmailAttachment> defaultEmailAttachments = Lists.newArrayList();

					if (CollectionUtils.isNotEmpty(mimeEmailAttachments)) {
						// Parse one by one
						mimeEmailAttachments.stream().forEachOrdered(Unchecked.consumer(mimeEmailAttachment -> {
							DefaultEmailAttachment attachment = (DefaultEmailAttachment) getMimeAttachment(
									mimeEmailAttachment);
							// attached
							defaultEmailAttachments.add(attachment);

						}));
					}

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
							// Attachments
							.attachments(defaultEmailAttachments)
							// Email encoding
							.encoding(mimeEmailBox.getCharset())
							// Build
							.build();

					// Set priority
					Integer priority = conspirator.getPriority() > 0 ? conspirator.getPriority()
							: (mimeEmailBox.getPriority() > 0 ? mimeEmailBox.getPriority() : 5);

					// Execute in parellel
					executors.execute(() -> {
						try {
							// Picture Description
							List<MimeEmailInlinePicture> mimeEmailPictures = mimeEmailBox.getMimeEmailPictures();
							// Picture
							if (CollectionUtils.isNotEmpty(mimeEmailPictures)) {
								List<DefaultInlinePicture> inlinePictures = Lists.newArrayList();
								mimeEmailPictures.stream().forEachOrdered((mimeEmailPicture) -> {
									inlinePictures.add((DefaultInlinePicture) createInlinePicture(mimeEmailPicture));

								});

								// Schedule sending with pictures
								schedulerService.schedule(email, scheduleTime, priority,
										// Template file name
										mimeEmailBox.getTemplateName(),
										// Template data object
										ImmutableMap.copyOf(mimeEmailBox.getModelObject()),
										// pictures
										inlinePictures.toArray(new DefaultInlinePicture[inlinePictures.size()]));

							} else {

								// Schedule sending
								schedulerService.schedule(email, scheduleTime, priority, mimeEmailBox.getTemplateName(),
										// Template data object
										ImmutableMap.copyOf(mimeEmailBox.getModelObject()));
							}

						} catch (CannotSendEmailException e) {
							e.printStackTrace();

						}
					});

				}));

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
	protected EmailAttachment getMimeAttachment(MimeEmailAttachment attachment)
			throws FileNotFoundException, IOException {
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
