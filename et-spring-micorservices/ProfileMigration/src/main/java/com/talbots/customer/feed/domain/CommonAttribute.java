/**
 * 
 */
package com.talbots.customer.feed.domain;

import java.lang.reflect.Field;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * @author sr4mxl
 *
 */
@XmlRootElement(name = "custom-attribute")
public class CommonAttribute {
	
	
	private String attributeId;
	
	
    private String description;


	public CommonAttribute() {

	}

	@XmlAttribute(name = "attribute-id")
	public String getAttributeId() {
		return attributeId;
	}


	public void setAttributeId(String attributeId) {
		this.attributeId = attributeId;
	}

	 @XmlValue
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
    public String toString() {
        Field[] fields = this.getClass().getDeclaredFields();
        String res = "\n";
        try {
            for (Field field : fields) {
                res += field.getName() + " : " + field.get(this) + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }

        return res;
    }
	

}
