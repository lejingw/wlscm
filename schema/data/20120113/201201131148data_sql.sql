
/*
update jat_basic_billno_rule a
   set a.create_date = '2012-01-01 00:00:00',
       a.update_date = '2012-01-01 00:00:00',
       a.create_id   = 2029,
       a.update_id   = 2029;
update jat_sys_dict_entry a
   set a.create_date = '2012-01-01 00:00:00',
       a.update_date = '2012-01-01 00:00:00',
       a.create_id   = 2029,
       a.update_id   = 2029;
update jat_sys_dict_item a
   set a.create_date = '2012-01-01 00:00:00',
       a.update_date = '2012-01-01 00:00:00',
       a.create_id   = 2029,
       a.update_id   = 2029;
*/
select * from jat_basic_billno_rule a where a.status = 1 order by a.bill_code;
select * from jat_sys_dict_entry a where a.status = 1 order by a.entry_code;
select * from jat_sys_dict_item a where a.status = 1 order by a.entry_code, a.item_order, a.item_key;