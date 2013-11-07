/*1、保存excel临时数据*/
delete jat_sys_right_mapping2;
select * from jat_sys_right_mapping2 a for update;

/*2、获取新添加的权限*/
select *
  from jat_sys_right_mapping2 a
 where /*a.toolbar_code not like '0809%' --推式管理
   and */not exists
 (select 1
          from jat_sys_right_mapping b
         where a.toolbar_code = b.toolbar_code
           and nvl(a.item_code, 0) = nvl(b.item_code, 0))
 order by a.toolbar_code, a.item_code;

/*3、获取删除的权限*/
select *
  from jat_sys_right_mapping a
 where not exists (select 1
          from jat_sys_right_mapping2 b
         where a.toolbar_code = b.toolbar_code
           and nvl(a.item_code, 0) = nvl(b.item_code, 0))
 order by a.toolbar_code, a.item_code;

/*4、保存权限映射关系*/
select * from jat_sys_right_mapping a where 1 = 0 for update;

/*5、备份权限映射关系*/
--权限映射
select *
  from jat_sys_right_mapping a
 order by a.toolbar_code, nvl(a.item_code, 0), a.sort;
--数据字典
select * from jat_sys_dict_entry a order by a.entry_code;
select * from jat_sys_dict_item a order by a.entry_code, a.item_key;
--单据编码规则
select * from jat_basic_billno_rule a order by a.bill_code;
--系统参数
select * from jat_sys_parameter a order by a.param_name, a.param_value;
--查询条件
select * from jat_sys_query_conditon_head a order by a.headid;
select * from jat_sys_query_conditon_line a order by a.headid, a.lineid;
