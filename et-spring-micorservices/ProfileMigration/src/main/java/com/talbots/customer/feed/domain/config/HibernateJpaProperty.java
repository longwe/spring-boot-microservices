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
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "hibernateproperties")
@Configuration
public class HibernateJpaProperty {
	private String showSql;
	private String useSqlComments;
	private String formatSql;
	private String hibernateDdlAuto;
	@NotNull
	private String databasePlatform;

}
