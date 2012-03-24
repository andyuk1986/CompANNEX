package com.compannex.biz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.compannex.exceptions.CompANNEXException;
import com.compannex.properties.CompANNEXProperties;
import com.compannex.util.StringUtil;

public class LogoMethods {

	private PartnerMethods partnerMethods;
	
	private CompANNEXProperties compANNEXProperties;
	
	public void addCompanyLogo(MultipartFile logo, int companyID) throws CompANNEXException {

		try {
			String logoPath = StringUtil.getFolderPath(getCompANNEXProperties().getLogosPath()) + companyID + "_logo" + StringUtil.getExtension(logo.getOriginalFilename());
			InputStream in = logo.getInputStream();
			File file = new File(logoPath);
			file.createNewFile();file.getAbsolutePath();
			FileOutputStream f = new FileOutputStream(file);
			int ch = 0;
			while ((ch = in.read()) != -1) {
				f.write(ch);
			}
			f.flush();
			f.close();
			getPartnerMethods().editPartnerLogo(companyID, getCompANNEXProperties().getLogoURI() + "/" + companyID + "_logo" + StringUtil.getExtension(logo.getOriginalFilename()));
		} catch (Exception e) {
			throw new CompANNEXException(e);
		}
	}

	public PartnerMethods getPartnerMethods() {
		return partnerMethods;
	}

	public void setPartnerMethods(PartnerMethods partnerMethods) {
		this.partnerMethods = partnerMethods;
	}

	public CompANNEXProperties getCompANNEXProperties() {
		return compANNEXProperties;
	}

	public void setCompANNEXProperties(CompANNEXProperties compANNEXProperties) {
		this.compANNEXProperties = compANNEXProperties;
	}
		
}
