/**
 * 
 */
package com.talbots.customer.feed.domain.config;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.ToString;

/**
 * @author sr4mxl
 *
 */
@Component
@Data
@ToString
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "datasource")
public class PropDataSource {

	@NotNull
	private String url;
	private String userName;
	private String password;
	@NotNull
	private String driverClassName;
	// @NotNull
	// private DataSourceJpaProperty jpaProperties;

}
