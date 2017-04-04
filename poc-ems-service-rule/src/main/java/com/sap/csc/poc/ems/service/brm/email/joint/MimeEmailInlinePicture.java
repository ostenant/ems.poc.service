package com.sap.csc.poc.ems.service.brm.email.joint;

import java.io.File;
import java.io.Serializable;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;
import com.google.common.io.Files;

import it.ozimov.springboot.mail.model.ImageType;

public class MimeEmailInlinePicture implements Serializable {

	private static final long serialVersionUID = -7895039410501662242L;

	private static final String EMPTY_EXCEPTION = "File doesn't exist!";
	private static final String IMAGE_TYPE_EXCEPTION = "Image type not support!";

	private ImageType imageType;

	private String templateName;

	private String templateBaseName;

	private String templateExtensionName;

	private File imageFile;

	public MimeEmailInlinePicture() {
	}

	public MimeEmailInlinePicture(File imageFile) {
		this(null, imageFile);
	}

	public MimeEmailInlinePicture(String templateBaseName, File imageFile) {
		if (Files.isFile().apply(imageFile)
				&& StringUtils.lastIndexOf(imageFile.getName(), FilenameUtils.EXTENSION_SEPARATOR_STR) > -1) {
			this.imageFile = imageFile;
		} else {
			throw new RuntimeException(EMPTY_EXCEPTION);
		}

		this.templateBaseName = StringUtils.isNotBlank(templateBaseName) ? templateBaseName
				: FilenameUtils.getBaseName(imageFile.getName());
		this.templateExtensionName = FilenameUtils.getExtension(imageFile.getName());

		this.templateName = StringUtils.join(templateBaseName, FilenameUtils.EXTENSION_SEPARATOR_STR,
				templateExtensionName);

		switch (ImageType.valueOf(templateExtensionName)) {
		case GIF:
			setImageType(ImageType.GIF);
			break;
		case JPG:
			setImageType(ImageType.JPG);
			break;
		case JPEG:
			setImageType(ImageType.JPEG);
			break;
		case PNG:
			setImageType(ImageType.PNG);
			break;

		default:
			throw new RuntimeException(IMAGE_TYPE_EXCEPTION);
		}

	}

	public File getClassPathPicture(String imageFileUrl) {
		ClassLoader classLoader = getClass().getClassLoader();
		File pictureFile = new File(classLoader.getResource(imageFileUrl).getFile());

		Preconditions.checkState(pictureFile.exists(), "There is not picture %s", pictureFile.getName());

		return pictureFile;
	}

	public ImageType getImageType() {
		return imageType;
	}

	public void setImageType(ImageType imageType) {
		this.imageType = imageType;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getTemplateBaseName() {
		return templateBaseName;
	}

	public void setTemplateBaseName(String templateBaseName) {
		this.templateBaseName = templateBaseName;
	}

	public String getTemplateExtensionName() {
		return templateExtensionName;
	}

	public void setTemplateExtensionName(String templateExtensionName) {
		this.templateExtensionName = templateExtensionName;
	}

	public File getImageFile() {
		return imageFile;
	}

	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	@Override
	public String toString() {
		return "MimeEmailInlinePicture [imageType=" + imageType + ", templateName=" + templateName
				+ ", templateBaseName=" + templateBaseName + ", templateExtensionName=" + templateExtensionName
				+ ", imageFile=" + imageFile + "]";
	}

}
