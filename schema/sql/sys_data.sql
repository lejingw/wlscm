select * from jat_sys_right_mapping a order by a.toolbar_code, nvl(a.item_code,0), a.sort;
select * from jat_sys_dict_entry a order by a.entry_code;
select * from jat_sys_dict_item a order by a.entry_code, a.item_key;
select * from jat_sys_parameter a order by a.param_name, a.param_value;
select * from jat_sys_query_conditon_head a order by a.headid;
select * from jat_sys_query_conditon_line a order by a.headid, a.lineid;