/**
 * 
 */
package com.talbots.customer.feed.domain;

import java.lang.reflect.Field;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sr4mxl
 *
 */
//@XmlRootElement(name = "address")
public class Addresses {
	
	
	    
		List<Address> addresses;
		
		
		public List<Address> getAddresses() {
			return addresses;
		}



		@XmlElement(name = "address")
	    public void setAddresses(List<Address> addresses) {
			this.addresses = addresses;
		}




		@Override
	    public String toString() {
	        Field[] fields = this.getClass().getDeclaredFields();
	        String res = "";
	        try {
	            for (Field field : fields) {
	                res += field.getName() + " :\n" + field.get(this);
	            }
	        } catch (Exception e) {
	            e.printStackTrace(); 
	        }

	        return res;
	    }
}
