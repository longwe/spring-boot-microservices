/**
 * 
 */

package com.talbots.customer.feed.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sr4mxl
 *
 */
@XmlRootElement(name="custom-attributes")

public class CustomAttributes {
	
	
	
	List < CommonAttribute > customAttributeList;

	
	public List<CommonAttribute> getCustomAttributeList() {
		return customAttributeList;
	}

	@XmlElement(name = "custom-attribute")
	public void setCustomAttributeList(List<CommonAttribute> customAttributeList) {
		this.customAttributeList = customAttributeList;
	}
	
	
	public void add(CommonAttribute commonAttribute) {
        if (this.customAttributeList == null) {
            this.customAttributeList = new ArrayList<CommonAttribute>();
        }
        this.customAttributeList.add(commonAttribute);

    }


	


	}
