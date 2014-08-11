package com.compannex.biz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.springframework.web.multipart.MultipartFile;

import com.compannex.exceptions.CompANNEXException;
import com.compannex.properties.CompANNEXProperties;
import com.compannex.util.StringUtil;

public class LogoMethods {

	private CompanyMethods companyMethods;
	
	private CompANNEXProperties compANNEXProperties;
	
	public void addCompanyLogo(ServletContext context, MultipartFile logo, int companyID) throws CompANNEXException {

		try {
			String logoPath = context.getRealPath(StringUtil.getFolderPath(getCompANNEXProperties().getLogosPath()) + companyID + "_logo" + StringUtil.getExtension(logo.getOriginalFilename()));
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
			getCompanyMethods().editCompanyLogo(companyID, getCompANNEXProperties().getLogoURI() + "/" + companyID + "_logo" + StringUtil.getExtension(logo.getOriginalFilename()));
		} catch (Exception e) {
			throw new CompANNEXException(e);
		}
	}

	public CompanyMethods getCompanyMethods() {
		return companyMethods;
	}


	public void setCompanyMethods(CompanyMethods companyMethods) {
		this.companyMethods = companyMethods;
	}

	public CompANNEXProperties getCompANNEXProperties() {
		return compANNEXProperties;
	}

	public void setCompANNEXProperties(CompANNEXProperties compANNEXProperties) {
		this.compANNEXProperties = compANNEXProperties;
	}
		
}
