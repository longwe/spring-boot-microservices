SELECT  top(10)         u.id,
                  -- u.lastactivity_date,
                 
                           u.first_name as 'Profile first_name',
                           u.last_name as 'Profile last_name',
                           addr.shipping_addr_id,
                           oa.address_id as addressId, 
                              c.first_name as 'ad_first_name',
                              c.last_name as 'ad_last_name',
                              c.address1 as 'Address1',
                              c.address2 as 'Address2',
                              c.address3 as 'Address3',
                              csu.address4 as 'Address4',
                              c.city as 'city',
                              c.state as 'state',
                              c.postal_code as 'postal_code',
                              c.country as 'country',
                              csu.day_phone,
                              
                              csu.nickname as 'nickname',
                              csu.location as 'location',
                              u.email as 'Profile email'
                             -- dbo.csys_contact_info.day_phone,
                             -- dbo.csys_contact_info.evening_phone
 
                     FROM     dbo.dps_user AS u  
                     LEFT OUTER JOIN csys_contact_info csu ON u.id = csu.id 
                     INNER JOIN atgcore.dbo.dps_other_addr oa on u.id = oa.user_id 
                                  INNER JOIN atgcore.dbo.dps_contact_info c on c.id = oa.address_id
                                  LEFT OUTER JOIN atgcore.dbo.dps_user_address addr ON u.id = addr.id 
                                   where c.first_name <> 'NULL'
                                  order by u.id
