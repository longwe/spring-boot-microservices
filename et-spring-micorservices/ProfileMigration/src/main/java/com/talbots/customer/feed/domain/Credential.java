/**
 * 
 */
package com.talbots.customer.feed.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sr4mxl
 *
 */
@XmlRootElement(name = "credentials")
public class Credential {

	@XmlElement(name = "login")
	private String login;

	@XmlElement(name = "password")
	protected Password password;

	@XmlElement(name = "enabled-flag ", defaultValue = "true")
	private String enabledFlag;

	/**
	 * @param login
	 * @param password
	 * @param enabledFlag
	 */
	public Credential(String login, Password password, String enabledFlag) {
		super();
		this.login = login;
		this.password = password;
		this.enabledFlag = enabledFlag;
	}

	/**
	 * 
	 */
	public Credential() {
		super();

	}

}
