/*1������excel��ʱ����*/
delete jat_sys_right_mapping2;
select * from jat_sys_right_mapping2 a for update;

/*2����ȡ����ӵ�Ȩ��*/
select *
  from jat_sys_right_mapping2 a
 where /*a.toolbar_code not like '0809%' --��ʽ����
   and */not exists
 (select 1
          from jat_sys_right_mapping b
         where a.toolbar_code = b.toolbar_code
           and nvl(a.item_code, 0) = nvl(b.item_code, 0))
 order by a.toolbar_code, a.item_code;

/*3����ȡɾ����Ȩ��*/
select *
  from jat_sys_right_mapping a
 where not exists (select 1
          from jat_sys_right_mapping2 b
         where a.toolbar_code = b.toolbar_code
           and nvl(a.item_code, 0) = nvl(b.item_code, 0))
 order by a.toolbar_code, a.item_code;

/*4������Ȩ��ӳ���ϵ*/
select * from jat_sys_right_mapping a where 1 = 0 for update;

/*5������Ȩ��ӳ���ϵ*/
--Ȩ��ӳ��
select *
  from jat_sys_right_mapping a
 order by a.toolbar_code, nvl(a.item_code, 0), a.sort;
--�����ֵ�
select * from jat_sys_dict_entry a order by a.entry_code;
select * from jat_sys_dict_item a order by a.entry_code, a.item_key;
--���ݱ������
select * from jat_basic_billno_rule a order by a.bill_code;
--ϵͳ����
select * from jat_sys_parameter a order by a.param_name, a.param_value;
--��ѯ����
select * from jat_sys_query_conditon_head a order by a.headid;
select * from jat_sys_query_conditon_line a order by a.headid, a.lineid;
