SELECT distinct u.id AS id, 
          u.login, 
		  sfu.password,   
		  u.first_name, 
		  u.last_name, 
		  u.email, 
		  u.gender,  
		  cc.billing_addr as addressId,
          c.first_name as "ad_first_name",  
		  c.last_name as "ad_last_name", 
		  c.address1, 
		  c.address2, 
		  c.address3, 
		  c.city, c.state, 
          c.postal_code, 
		  c.country, 
		  c.phone_number,
		  dbo.csys_contact_info.address4,
		  dbo.csys_contact_info.nickname, 
		  dbo.csys_contact_info.location, 
		  dbo.csys_contact_info.day_phone, 
          dbo.csys_contact_info.evening_phone,
		  dbo.tlb_user.birth_month,
          dbo.tlb_user.us_citizen_status, 
	      dbo.tlb_user.classic_awards_member,
		  dbo.tlb_user.classic_awards_member_number, 
          dbo.tlb_user.receieve_promo_updates
		  ,csu.promo_signup

FROM     dbo.dps_user AS u LEFT OUTER JOIN
                      dbo.tlb_user ON u.id = dbo.tlb_user.id LEFT OUTER JOIN
                      dbo.csys_contact_info RIGHT OUTER JOIN
                      dbo.dps_usr_creditcard AS uc INNER JOIN
                      dbo.dps_credit_card AS cc ON uc.credit_card_id = cc.id INNER JOIN
                      dbo.dps_contact_info AS c ON cc.billing_addr = c.id ON dbo.csys_contact_info.id = c.id ON u.id = uc.user_id
					  left join sfcc_commerce.dbo.csys_user csu on u.id = csu.id
					  left join sfcc_commerce.dbo.sfcc_dps_user sfu on u.id = sfu.id
					  where processed=1
and login in (
'sherlocker71@gmail.com',
'c@j.com',
'Caitlin.jones@talbots.com',
'Test1@hotmail.com',
'test2@hotmail.com',
'Nicole.molla@talbots.com',
'nlmolla@hotmail.com',
'Lillian.harley@comcast.net')

order by u.id