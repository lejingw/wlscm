insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (56, 5, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (57, 5, 'a.express_mode', '���˷�ʽ', 1, 5, 'movepackexpressmode', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (58, 5, 'a.express_no', '��ݵ���', 1, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (59, 5, 'a.in_org_id', '������֯', 4, 1, '', 4, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (60, 5, 'a.sum_count', '�ܼ���', 2, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (61, 5, 'a.plan_end_date', 'Ԥ�Ƶ�������', 5, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (62, 5, 'a.gross_weight', '����ë��', 3, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (63, 5, 'a.receive_emp_id', '������', 4, 1, '', 8, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (64, 5, 'a.receive_date', '����ʱ��', 5, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (65, 5, 'a.create_date', '����ʱ��', 5, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (66, 5, 'a.create_id', '������', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (67, 5, 'a.update_date', '�޸�ʱ��', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (68, 5, 'a.update_id', '�޸���', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (69, 5, 'a.status', '״̬', 1, 2, 'select distinct b.item_order as "value", b.item_value as "text" from jat_move_pack_head a, jat_sys_dict_item b where a.status = b.item_key and b.entry_code = ''status'' order by b.item_order', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (329, 5, '(select wm_concat(c.bill_no) from jat_move_pack_line c where c.headid = a.headid)', '���ӵ���', 1, 1, '', 15, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6282, 5, 'a.out_org_id', '������֯', 4, 1, '', 3, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1, 6, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (2, 6, 'a.dodate', 'ҵ������', 5, 1, 'movetype', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5, 6, 'a.out_org_id', '������֯', 4, 1, '', 2, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6, 6, 'a.in_org_id', '������֯', 4, 1, '', 3, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (8, 6, 'a.sum_count', '�����ϼ�', 2, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (9, 6, 'a.sum_weight', '�����ϼ�', 3, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (10, 6, 'hh.rececount', '���պϼ�', 2, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (11, 6, '(a.sum_count - hh.rececount - hh.discardcount)', 'δ���պϼ�', 2, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (12, 6, 'ff.cert_count', '����֤��ϼ�', 2, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (189, 6, 'a.back_in48_flag', '48Сʱ�˻�', 1, 5, 'yesorno', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (191, 6, 'a.pack_no', 'װ�䵥��', 1, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (192, 6, 'a.create_id', '������', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (193, 6, 'a.create_date', '��������', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (194, 6, 'a.update_id', '�޸���', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (195, 6, 'a.update_date', '�޸�����', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (341, 6, 'a.status', '״̬', 1, 2, 'select distinct b.item_order as "value", b.item_value as "text" from jat_move_head a, jat_sys_dict_item b where a.bill_type = 2 and a.status = b.item_key and b.entry_code = ''status'' order by b.item_order', 15, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (115, 7, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (116, 7, 'a.move_type', '��������', 1, 5, 'movetype', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (117, 7, 'a.src_bill_code', '��Դ����', 1, 3, '[{value:''CZ'',text:''�ɹ��ܵ�''}]', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (118, 7, 'a.src_bill_no', '��Դ���ݺ�', 1, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (119, 7, 'a.out_org_id', '������֯', 4, 1, '', 5, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (120, 7, 'a.in_org_id', '������֯', 4, 1, '', 6, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (121, 7, 'a.status', '״̬', 1, 2, 'select distinct b.item_order as "value", b.item_value as "text" from jat_move_head a, jat_sys_dict_item b where a.bill_type = 1 and a.status = b.item_key and b.entry_code = ''status'' order by b.item_order', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (122, 7, 'a.sum_count', '�����ϼ�', 2, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (123, 7, 'a.sum_weight', '�����ϼ�', 3, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (124, 7, 'hh.rececount', '���պϼ�', 2, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (125, 7, '(a.sum_count - hh.rececount - hh.discardcount)', 'δ���պϼ�', 2, 1, '', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (126, 7, 'ff.cert_count', '����֤��ϼ�', 2, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (139, 7, 'a.create_date', '��������', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (153, 7, 'a.update_date', '�޸�����', 5, 1, '', 16, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (155, 7, 'a.create_id', '������', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (156, 7, 'a.update_id', '�޸���', 4, 1, '', 15, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (157, 7, 'a.dodate', 'ҵ������', 5, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (190, 7, 'a.pack_no', 'װ�䵥��', 1, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (13, 8, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (14, 8, 'a.order_kind', 'Ҫ������', 1, 5, 'orderkind', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15, 8, 'a.order_type', '��������', 1, 5, 'ordertype', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (16, 8, 'a.status', '״̬', 1, 3, '[{value:''2'',text:''����''},{value:''8'',text:''�ر�''}]', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (17, 8, 'g.cycle_type_id', '��������', 1, 2, 'select a.cycle_type_id as "value", a.cycle_type_code as "text" from jas_pl_cycle_type a where a.isorno_sealed = ''0'' order by a.cycle_type_id', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (18, 8, 'f.sum_num', '�ɹ�����', 2, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (19, 8, 'a.create_id', '������', 4, 1, '', 7, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (20, 8, 'a.create_date', '����ʱ��', 5, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (187, 8, 'decode((select count(1) from jat_pur_purchase_line b where a.headid = b.gather_head_id and b.used_flag = 1), 0, 0, 1)', '�Ƿ���', 1, 5, 'yesorno', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (159, 9, 'a.billno', '���ݱ��', 1, 1, '', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (160, 9, 'a.order_kind', 'Ҫ������', 1, 5, 'orderkind', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (161, 9, 'a.order_type', '��������', 1, 5, 'ordertype', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (162, 9, 'a.status', '״̬', 1, 3, '[{value:''2'',text:''����''},{value:''8'',text:''�ر�''}]', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (163, 9, 'g.cycle_type_id', '��������', 1, 2, 'select a.cycle_type_id as "value", a.cycle_type_code as "text" from jas_pl_cycle_type a where a.isorno_sealed = ''0'' order by a.cycle_type_id', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (164, 9, 'f.sum_num', '�ɹ�����', 2, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (165, 9, 'a.create_id', '������', 4, 1, '', 8, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (166, 9, 'a.create_date', '����ʱ��', 5, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (21, 10, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (22, 10, 'a.src_bill_code', '��Դ��������', 1, 3, '[{value:''DZ'', text:''������''}, {value:''CZ'', text:''�ɹ��ܵ�''}]', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (23, 10, '(case when a.src_bill_code = ''CZ'' then (select c.billno from jat_pur_gather_head c where a.src_bill_id = c.headid(+)) when a.src_bill_code = ''DZ'' then (select d.no from jas_pos_bill_customize d where a.src_bill_id = d.id) end)', '��Դ���ݺ�', 1, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (24, 10, 'a.bill_type', '��������', 1, 5, 'purbilltype', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (25, 10, 'a.biz_type', 'ҵ������', 1, 5, 'purbiztype', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (26, 10, 'a.vendor_id', '��Ӧ��', 4, 1, '', 5, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (27, 10, 'a.create_id', '������', 4, 1, '', 8, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (28, 10, 'a.create_date', '����ʱ��', 5, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (29, 10, 'a.status', '״̬', 1, 2, 'select distinct a.status as "value", b.item_value as "text" from jat_pur_purchase_head a left join jat_sys_dict_item b on a.status = b.item_key and b.entry_code = ''status''', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (30, 10, 'f.sum_num', '�ɹ�����', 2, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6284, 10, 'a.update_id', '�޸���', 4, 1, '', 10, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6285, 10, 'a.update_date', '�޸�ʱ��', 5, 1, '', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (173, 11, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (174, 11, 'a.src_bill_code', '��Դ��������', 1, 3, '[{value:''DZ'', text:''������''}, {value:''CZ'', text:''�ɹ��ܵ�''}]', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (175, 11, '(case when a.src_bill_code = ''CZ'' then (select c.billno from jat_pur_gather_head c where a.src_bill_id = c.headid(+)) when a.src_bill_code = ''DZ'' then (select d.no from jas_pos_bill_customize d where a.src_bill_id = d.id) end)', '��Դ���ݺ�', 1, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (176, 11, 'a.bill_type', '��������', 1, 5, 'purbilltype', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (177, 11, 'a.biz_type', 'ҵ������', 1, 5, 'purbiztype', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (178, 11, 'a.vendor_id', '��Ӧ��', 4, 1, '', 5, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (179, 11, 'a.create_id', '������', 4, 1, '', 8, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (180, 11, 'a.create_date', '����ʱ��', 5, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (181, 11, 'a.status', '״̬', 1, 2, 'select distinct a.status as "value", b.item_value as "text" from jat_pur_purchase_head a left join jat_sys_dict_item b on a.status = b.item_key and b.entry_code = ''status''', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (184, 11, 'f.sum_num', '�ɹ�����', 2, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (185, 11, 'decode((select count(1) from jat_pur_purchase_line b where a.headid = b.headid and b.used_flag = 1), 0, 0, 1)', '�Ƿ���', 1, 5, 'yesorno', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6286, 11, 'a.update_id', '�޸���', 1, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6287, 11, 'a.update_date', '�޸�ʱ��', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (31, 12, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (32, 12, 'a.dodate', 'ҵ������', 5, 1, '', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (33, 12, 'a.out_group', '��������', 1, 5, 'group', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (34, 12, 'a.in_group', '�������', 1, 5, 'group', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (35, 12, 'a.receive_date', '����ʱ��', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (36, 12, 'a.receive_emp_id', '������', 1, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (37, 12, 'a.create_id', '������', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (38, 12, 'a.create_date', '��������', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (39, 12, 'a.update_id', '�޸���', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (40, 12, 'a.update_date', '�޸�����', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (41, 12, 'a.status', '״̬', 1, 2, 'select distinct b.item_order as "value", b.item_value as "text" from jat_move_head a, jat_sys_dict_item b where a.bill_type = 5 and a.status = b.item_key and b.entry_code = ''status'' order by b.item_order', 15, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (322, 12, 'a.sum_count', '�����ϼ�', 2, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (323, 12, 'a.sum_weight', '�����ϼ�', 3, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (324, 12, 'hh.rececount', '���պϼ�', 2, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (325, 12, '(a.sum_count - hh.rececount - hh.discardcount)', 'δ���պϼ�', 2, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (326, 12, 'ff.cert_count', '����֤��ϼ�', 2, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (196, 13, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (197, 13, 'a.dodate', 'ҵ������', 5, 1, '', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (198, 13, 'a.out_stock_id', '�����ֿ�', 1, 5, 'invcode', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (199, 13, 'a.in_stock_id', '����ֿ�', 1, 5, 'invcode', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (200, 13, 'a.receive_date', '����ʱ��', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (201, 13, 'a.receive_emp_id', '������', 1, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (202, 13, 'a.create_id', '������', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (203, 13, 'a.create_date', '��������', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (204, 13, 'a.update_id', '�޸���', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (205, 13, 'a.update_date', '�޸�����', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (206, 13, 'a.status', '״̬', 1, 2, 'select distinct b.item_order as "value", b.item_value as "text" from jat_move_head a, jat_sys_dict_item b where a.bill_type = 4 and a.status = b.item_key and b.entry_code = ''status'' order by b.item_order', 15, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (317, 13, 'a.sum_count', '�����ϼ�', 2, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (318, 13, 'a.sum_weight', '�����ϼ�', 3, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (319, 13, 'hh.rececount', '���պϼ�', 2, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (320, 13, '(a.sum_count - hh.rececount - hh.discardcount)', 'δ���պϼ�', 2, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (321, 13, 'ff.cert_count', '����֤��ϼ�', 2, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (42, 14, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (43, 14, 'a.express_mode', '���˷�ʽ', 1, 5, 'movepackexpressmode', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (44, 14, 'a.express_no', '��ݵ���', 1, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (45, 14, 'a.in_org_id', '������֯', 4, 1, '', 4, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (46, 14, 'a.sum_count', '�ܼ���', 2, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (47, 14, 'a.plan_end_date', 'Ԥ�Ƶ�������', 5, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (48, 14, 'a.gross_weight', '����ë��', 3, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (49, 14, 'a.receive_emp_id', '������', 4, 1, '', 8, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (50, 14, 'a.receive_date', '����ʱ��', 5, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (51, 14, 'a.create_date', '����ʱ��', 5, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (52, 14, 'a.create_id', '������', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (53, 14, 'a.update_date', '�޸�ʱ��', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (54, 14, 'a.update_id', '�޸���', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (55, 14, 'a.status', '״̬', 1, 2, 'select distinct b.item_order as "value", b.item_value as "text" from jat_move_pack_head a, jat_sys_dict_item b where a.status = b.item_key and b.entry_code = ''status'' order by b.item_order', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (328, 14, '(select wm_concat(c.bill_no) from jat_move_pack_line c where c.headid = a.headid)', 'ά�޵���', 1, 1, '', 15, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6281, 14, 'a.out_org_id', '������֯', 4, 1, '', 3, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (207, 15, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (208, 15, 'a.express_mode', '���˷�ʽ', 1, 5, 'movepackexpressmode', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (209, 15, 'a.express_no', '��ݵ���', 1, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (210, 15, 'a.in_org_id', '������֯', 4, 1, '', 4, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (211, 15, 'a.sum_count', '�ܼ���', 2, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (212, 15, 'a.plan_end_date', 'Ԥ�Ƶ�������', 5, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (213, 15, 'a.gross_weight', '����ë��', 3, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (214, 15, 'a.receive_emp_id', '������', 4, 1, '', 8, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (215, 15, 'a.receive_date', '����ʱ��', 5, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (216, 15, 'a.create_date', '����ʱ��', 5, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (217, 15, 'a.create_id', '������', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (218, 15, 'a.update_date', '�޸�ʱ��', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (219, 15, 'a.update_id', '�޸���', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (220, 15, 'a.status', '״̬', 1, 2, 'select distinct b.item_order as "value", b.item_value as "text" from jat_move_pack_head a, jat_sys_dict_item b where a.status = b.item_key and b.entry_code = ''status'' order by b.item_order', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (327, 15, '(select wm_concat(c.bill_no) from jat_move_pack_line c where c.headid = a.headid)', '��������', 1, 1, '', 15, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6280, 15, 'a.out_org_id', '������֯', 4, 1, '', 3, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (221, 17, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (222, 17, 'a.move_type', '��������', 1, 5, 'movetype', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (223, 17, 'a.dodate', 'ҵ������', 5, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (224, 17, 'a.src_bill_code', '��Դ����', 1, 3, '[{value:''CZ'',text:''�ɹ��ܵ�''}]', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (225, 17, 'a.src_bill_no', '��Դ���ݺ�', 1, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (226, 17, 'a.out_org_id', '������֯', 4, 1, '', 5, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (227, 17, 'a.in_org_id', '������֯', 4, 1, '', 6, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (228, 17, 'a.pack_no', 'װ�䵥��', 1, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (229, 17, 'a.status', '״̬', 1, 2, 'select distinct b.item_order as "value", b.item_value as "text" from jat_move_head a, jat_sys_dict_item b where a.bill_type = 1 and a.status = b.item_key and b.entry_code = ''status'' order by b.item_order', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (230, 17, 'a.sum_count', '�����ϼ�', 2, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (231, 17, 'a.sum_weight', '�����ϼ�', 3, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (232, 17, 'hh.rececount', '���պϼ�', 2, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (233, 17, '(a.sum_count - hh.rececount - hh.discardcount)', 'δ���պϼ�', 2, 1, '', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (234, 17, 'ff.cert_count', '����֤��ϼ�', 2, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (235, 17, 'a.create_id', '������', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (236, 17, 'a.create_date', '��������', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (237, 17, 'a.update_id', '�޸���', 4, 1, '', 15, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (238, 17, 'a.update_date', '�޸�����', 5, 1, '', 16, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (239, 18, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (240, 18, 'a.dodate', 'ҵ������', 5, 1, 'movetype', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (241, 18, 'a.out_org_id', '������֯', 4, 1, '', 2, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (242, 18, 'a.in_org_id', '������֯', 4, 1, '', 3, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (243, 18, 'a.back_in48_flag', '48Сʱ�˻�', 1, 5, 'yesorno', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (244, 18, 'a.pack_no', 'װ�䵥��', 1, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (245, 18, 'a.sum_count', '�����ϼ�', 2, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (246, 18, 'a.sum_weight', '�����ϼ�', 3, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (247, 18, 'hh.rececount', '���պϼ�', 2, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (248, 18, '(a.sum_count - hh.rececount - hh.discardcount)', 'δ���պϼ�', 2, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (249, 18, 'a.create_id', '������', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (250, 18, 'ff.cert_count', '����֤��ϼ�', 2, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (251, 18, 'a.create_date', '��������', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (252, 18, 'a.update_id', '�޸���', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (253, 18, 'a.update_date', '�޸�����', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (342, 18, 'a.status', '״̬', 1, 2, 'select distinct b.item_order as "value", b.item_value as "text" from jat_move_head a, jat_sys_dict_item b where a.bill_type = 2 and a.status = b.item_key and b.entry_code = ''status'' order by b.item_order', 15, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (254, 19, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (255, 19, 'a.dodate', 'ҵ������', 5, 1, 'movetype', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (256, 19, 'a.out_org_id', '������֯', 4, 1, '', 2, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (257, 19, 'a.in_org_id', '������֯', 4, 1, '', 3, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (258, 19, 'a.pack_no', 'װ�䵥��', 1, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (259, 19, 'a.sum_count', '�����ϼ�', 2, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (260, 19, 'a.sum_weight', '�����ϼ�', 3, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (261, 19, 'hh.rececount', '���պϼ�', 2, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (262, 19, '(a.sum_count - hh.rececount - hh.discardcount)', 'δ���պϼ�', 2, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (263, 19, 'a.create_id', '������', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (264, 19, 'ff.cert_count', '����֤��ϼ�', 2, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (265, 19, 'a.create_date', '��������', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (266, 19, 'a.update_id', '�޸���', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (267, 19, 'a.update_date', '�޸�����', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (343, 19, 'a.status', '״̬', 1, 2, 'select distinct b.item_order as "value", b.item_value as "text" from jat_move_head a, jat_sys_dict_item b where a.bill_type = 3 and a.status = b.item_key and b.entry_code = ''status'' order by b.item_order', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (268, 20, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (269, 20, 'a.dodate', 'ҵ������', 5, 1, 'movetype', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (270, 20, 'a.out_org_id', '������֯', 4, 1, '', 2, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (271, 20, 'a.in_org_id', '������֯', 4, 1, '', 3, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (272, 20, 'a.pack_no', 'װ�䵥��', 1, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (273, 20, 'a.sum_count', '�����ϼ�', 2, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (274, 20, 'a.sum_weight', '�����ϼ�', 3, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (275, 20, 'hh.rececount', '���պϼ�', 2, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (276, 20, '(a.sum_count - hh.rececount - hh.discardcount)', 'δ���պϼ�', 2, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (277, 20, 'a.create_id', '������', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (278, 20, 'ff.cert_count', '����֤��ϼ�', 2, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (279, 20, 'a.create_date', '��������', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (280, 20, 'a.update_id', '�޸���', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (281, 20, 'a.update_date', '�޸�����', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (344, 20, 'a.status', '״̬', 1, 2, 'select distinct b.item_order as "value", b.item_value as "text" from jat_move_head a, jat_sys_dict_item b where a.bill_type = 3 and a.status = b.item_key and b.entry_code = ''status'' order by b.item_order', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (282, 21, 'a.org_id', '��֯', 4, 1, '', 0, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (283, 21, 'a.rece_user', '������Ա', 4, 1, '', 1, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (284, 22, 'a.in_org_id', '������֯', 4, 1, '', 0, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (285, 22, 'a.item_class_id', '����', 4, 1, '', 1, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (286, 22, 'a.orna_class_id', 'С��', 4, 1, '', 2, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (287, 22, '(select wm_concat(b.item_class_name) from jas_bd_style_item_class b where instr('','' || a.style_item_ids || '','', '','' || b.item_class_id || '','') > 0)', '��ʽ����', 1, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (288, 23, 'a.item_class_id', '����', 4, 1, '', 0, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (289, 23, 'a.orna_class_id', 'С��', 4, 1, '', 1, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (290, 23, 'a.coef_way', '��ʽ', 1, 5, 'movecoefway', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (291, 23, 'a.coef', 'ϵ��', 3, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (292, 24, 'a.out_org_id', '������֯', 4, 1, '', 0, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (293, 24, 'a.in_org_id', '������֯', 4, 1, '', 1, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (295, 25, 'a.no', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (296, 25, 'a.org_id', '��֯', 4, 1, '', 1, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (297, 25, 'a.item_class_id', '����', 4, 1, '', 2, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (298, 25, 'a.orna_class_id', 'С��', 4, 1, '', 3, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (299, 25, 'a.doom_num', 'Ԥ������', 3, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (300, 25, 'a.request_date', 'Ҫ�󵽻�ʱ��', 5, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (301, 25, 'a.createuserid', '������', 4, 1, '', 6, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (302, 25, 'a.createdate', '����ʱ��', 5, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (303, 25, 'a.updateuserid', '������', 4, 1, '', 8, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (304, 25, 'a.updatedate', '����ʱ��', 5, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (306, 26, 'a.no', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (307, 26, 'a.org_id', '��֯', 4, 1, '', 1, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (308, 26, 'a.item_class_id', '����', 4, 1, '', 2, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (309, 26, 'a.orna_class_id', 'С��', 4, 1, '', 3, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (310, 26, 'a.doom_num', 'Ԥ������', 3, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (311, 26, 'a.request_date', 'Ҫ�󵽻�ʱ��', 5, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (312, 26, 'a.createuserid', '������', 4, 1, '', 6, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (313, 26, 'a.createdate', '����ʱ��', 5, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (314, 26, 'a.updateuserid', '������', 4, 1, '', 8, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (315, 26, 'a.updatedate', '����ʱ��', 5, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (316, 26, 'a.state', '״̬', 1, 3, '[{value:''45'', text:''�ɹ�������ͬ��''},{value:''69'', text:''�����ɲɹ���''},{value:''8'', text:''�ر�''}]', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (331, 27, 'a.item_class_id', '����', 4, 1, '', 0, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (332, 27, 'a.orna_class_id', 'С��', 4, 1, '', 1, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (333, 27, 'b.summarydescription', '������Χ', 1, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (334, 27, 'c.stylename', '��ʽ', 1, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (335, 27, 'a.safe_num', '����׼', 3, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (336, 27, 'a.create_date', '����ʱ��', 5, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (337, 27, 'a.create_id', '������', 4, 1, '', 6, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (338, 27, 'a.update_date', '����ʱ��', 5, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (339, 27, 'a.update_id', '������', 4, 1, '', 8, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (345, 29, 'a.pur_emp_id', '�ɹ�Ա', 4, 1, '', 0, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (346, 29, 'a.article_type_id', '��Ʒ���', 2, 2, 'select a.article_type_id as "value", a.article_type_dsc as "text" from jas_bd_articletype a order by a.sort', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (347, 29, 'a.create_date', '����ʱ��', 5, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (348, 29, 'a.create_id', '������', 4, 1, '', 3, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1000, 31, 'a.VENDOR_ID', '��Ӧ��', 4, 1, '', 0, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1001, 31, 'a.COEF', 'ϵ��', 3, 1, '', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1002, 32, 'a.ITEM_CLASS_ID', '����', 4, 1, '', 0, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1003, 32, 'a.ORNACLASS_ID', 'С��', 4, 1, '', 1, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1004, 32, 'a.UNIT_ID', '������λ', 4, 1, '', 2, 'select a.unitid from jas_bd_unit a where a.unitname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1005, 32, 'a.LABEL_TYPE_NAME', '��ǩ����', 1, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86494, 32, 'a.CREATE_ID', '������', 4, 1, '', 4, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86495, 32, 'a.UPDATE_DATE', '����ʱ��', 5, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86496, 32, 'a.UPDATE_ID', '�޸���', 4, 1, '', 6, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86497, 32, 'a.UPDATE_DATE', '�޸�ʱ��', 5, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1006, 33, 'a.COLOR_GRADE_ID', 'ɫ��', 1, 5, 'diacolorgrade', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1007, 33, 'a.GRADE_ID', 'Ʒ��', 1, 5, 'gradelevel', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86336, 33, 'a.CREATE_DATE', '����ʱ��', 5, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86337, 33, 'a.CREATE_ID', '������', 4, 1, '', 3, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86338, 33, 'a.UPDATE_DATE', '�޸�ʱ��', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86339, 33, 'a.UPDATE_ID', '�޸���', 4, 1, '', 5, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86436, 33, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''}]', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86437, 33, 'a.memo', '��ע', 1, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1008, 34, 'a.CLEAN_ID', '����', 1, 5, 'diaclean', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1009, 34, 'a.GRADE_ID', 'Ʒ��', 1, 5, 'gradelevel', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86332, 34, 'a.CREATE_DATE', '����ʱ��', 5, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86333, 34, 'a.CREATE_ID', '������', 4, 1, '', 3, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86334, 34, 'a.UPDATE_DATE', '�޸�ʱ��', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86335, 34, 'a.UPDATE_ID', '�޸���', 4, 1, '', 5, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86434, 34, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''}]', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86435, 34, 'a.memo', '��ע', 1, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1010, 35, 'a.item_class_id', '����', 4, 1, '', 0, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1011, 35, 'a.quality_id', '����', 4, 1, '', 1, 'select a.quality_id from jas_bd_quality a where a.quality_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1012, 35, 'a.START_WEIGHT', '��ʼ����', 3, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1013, 35, 'a.END_WEIGHT', '��ֹ����', 3, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86349, 35, 'a.COEFFICIENT', 'ϵ��', 3, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86350, 35, 'a.CREATE_DATE', '����ʱ��', 5, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86351, 35, 'a.CREATEUSERID', '������', 4, 1, '', 6, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86352, 35, 'a.UPDATE_DATE', '�޸�ʱ��', 5, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86353, 35, 'a.UPDATEUSERID', '�޸���', 4, 1, '', 8, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1014, 36, 'a.item_class_id', '����', 4, 1, '', 0, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1015, 36, 'a.quality_id', '����', 4, 1, '', 1, 'select a.quality_id from jas_bd_quality a where a.quality_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1016, 36, 'a.START_WEIGHT', '��ʼ����', 3, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1017, 36, 'a.END_WEIGHT', '��ֹ����', 3, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86344, 36, 'a.COEFFICIENT', 'ϵ��', 3, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86345, 36, 'a.CREATE_DATE', '����ʱ��', 5, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86346, 36, 'a.CREATEUSERID', '������', 4, 1, '', 6, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86347, 36, 'a.UPDATE_DATE', '�޸�ʱ��', 5, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86348, 36, 'a.UPDATEUSERID', '�޸���', 4, 1, '', 8, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1018, 37, 'a.ACCESSORY_ID', '����', 1, 5, 'accessories', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86340, 37, 'a.createdate', '����ʱ��', 5, 1, '', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86341, 37, 'a.CREATEUSERID', '������', 4, 1, '', 2, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86342, 37, 'a.UPDATE_DATE', '�޸�ʱ��', 5, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86343, 37, 'a.UPDATEUSERID', '�޸���', 4, 1, '', 4, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1019, 38, 'a.ITEM_CLASS_ID', '����', 4, 1, '', 0, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1020, 38, 'a.QUALITY_ID', '����', 4, 1, '', 1, 'select a.quality_id from jas_bd_quality a where a.quality_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1021, 38, 'a.START_WEIGHT', '��ʼ����', 3, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1022, 38, 'a.END_WEIGHT', '��ֹ����', 3, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86354, 38, 'a.COEFFICIENT', 'ϵ��', 3, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86355, 38, 'a.CREATE_DATE', '����ʱ��', 5, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86356, 38, 'a.CREATEUSERID', '������', 4, 1, '', 6, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86357, 38, 'a.UPDATE_DATE', '�޸�ʱ��', 5, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86358, 38, 'a.UPDATEUSERID', '�޸���', 4, 1, '', 8, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1023, 39, 'a.QUALITY_ID', '����', 4, 1, '', 0, 'select a.quality_id from jas_bd_quality a where a.quality_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1024, 39, 'a.MARKET_PRICE', '����', 3, 1, '', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86359, 39, 'a.CREATE_DATE', '����ʱ��', 5, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86360, 39, 'a.CREATEUSERID', '������', 4, 1, '', 3, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86361, 39, 'a.UPDATE_DATE', '�޸�ʱ��', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86362, 39, 'a.UPDATEUSERID', '�޸���', 4, 1, '', 5, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1025, 40, 'a.SBRA_TYPE', '�������', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86372, 40, 'a.COEFFICIENT', 'ϵ��', 3, 1, '', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86373, 40, 'a.ARCHIVEFLAG', '�Ƿ���', 1, 5, 'yesorno', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86374, 40, 'a.IS_FEE', '�Ƿ񹤷�', 1, 5, 'yesorno', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86375, 40, 'a.createdate', '����ʱ��', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86376, 40, 'a.CREATEUSERID', '������', 4, 1, '', 5, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86377, 40, 'a.updatedate', '�޸�ʱ��', 5, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86378, 40, 'a.UPDATEUSERID', '�޸���', 4, 1, '', 7, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1026, 42, 'a.item_class_id', '����', 1, 1, '', 0, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1027, 42, 'a.PRICE_STR', '��ʼ����', 3, 1, '', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (1028, 42, 'a.PRICE_END', '��ֹ����', 3, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86379, 42, 'a.COEFFICIENT', 'ϵ��', 3, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86380, 42, 'a.create_date', '����ʱ��', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86381, 42, 'a.CREATE_ID', '������', 4, 1, '', 5, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86382, 42, 'a.update_DATE', '�޸�ʱ��', 5, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86383, 42, 'a.update_Id', '�޸���', 4, 1, '', 7, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5000, 100, 'a.wl_code', '��Ʒ���', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5001, 100, 'a.cacl_type', '�˼�����', 1, 5, 'cacltype', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5002, 100, 'a.HANDOVER_NAME', '���ӱ��', 1, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5003, 100, 'a.createuserid', '�˼���', 4, 1, '', 3, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5004, 100, 'a.item_class_id', '����', 4, 1, '', 4, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5005, 100, 'a.orna_class_id', 'С��', 4, 1, '', 5, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5006, 100, 'a.vender', '��Ӧ��', 4, 1, '', 6, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5007, 100, 'a.no', '�˼۱��', 1, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5008, 100, 'a.BASIC_PRICE', '������', 3, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5009, 100, 'a.createdate', '����ʱ��', 5, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6276, 100, 'a.QUALITY_ID', '����', 4, 1, '', 10, 'select q.QUALITY_ID from jas_bd_quality q where q.QUALITY_DSC like''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6277, 100, 'a.STATE', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''13'',text:''������''}]', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6278, 100, 'a.IS_DOUBLE_LABEL', '�Ƿ�˫��ǩ', 1, 5, 'yesorno', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6279, 100, 'a.ALL_WEIGHT', '��Ʒ����', 3, 1, '', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5010, 101, 'a.ITEM_CLASS_ID', '����', 4, 1, '', 0, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5011, 101, 'a.STONE_TYPE', 'ʯͷ����', 1, 5, 'stonetype', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5012, 101, 'a.START_WEIGHT', '��ʼ����', 3, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5013, 101, 'a.END_WEIGHT', '��ֹ����', 3, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5014, 101, 'a.SHAPE_ID', '��״', 1, 5, 'diashape', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5015, 101, 'a.COLOR_ID', '��ɫ', 1, 5, 'diacolor', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5016, 101, 'a.COLOR_GRADE_ID', 'ɫ��', 1, 5, 'diacolorgrade', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5017, 101, 'a.CLEAN_ID', '����', 1, 5, 'diaclean', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5018, 101, 'a.MARKET_PRICE', '�۸�', 3, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5019, 101, 'a.CREATEUSERID', '������', 4, 1, '', 9, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5020, 101, 'a.CUT_ID', '�й�', 1, 5, 'diacut', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86364, 101, 'a.STYLE_STANDARD', '���', 1, 1, '', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86365, 101, 'a.createdate', '����ʱ��', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86366, 101, 'a.updatedate', '�޸�ʱ��', 5, 1, '', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86367, 101, 'a.UPDATEUSERID', '������', 4, 1, '', 14, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5021, 102, 'a.ITEM_CLASS_ID', '����', 4, 1, '', 0, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5022, 102, 'a.STONE_TYPE', 'ʯͷ����', 1, 5, 'stonetype', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5023, 102, 'a.START_WEIGHT', '��ʼ����', 3, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5024, 102, 'a.END_WEIGHT', '��ֹ����', 3, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5025, 102, 'a.SHAPE_ID', '��״', 1, 5, 'diashape', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5026, 102, 'a.COLOR_ID', '��ɫ', 1, 5, 'diacolor', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5027, 102, 'a.COLOR_GRADE_ID', 'ɫ��', 1, 5, 'diacolorgrade', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5028, 102, 'a.CLEAN_ID', '����', 1, 5, 'diaclean', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5029, 102, 'a.MARKET_PRICE', '�۸�', 3, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5030, 102, 'a.CREATEUSERID', '������', 4, 1, '', 9, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5031, 102, 'a.CUT_ID', '�й�', 1, 5, 'diacut', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86368, 102, 'a.STYLE_STANDARD', '���', 1, 1, '', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86369, 102, 'a.createdate', '����ʱ��', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86370, 102, 'a.updatedate', '�޸�ʱ��', 5, 1, '', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86371, 102, 'a.UPDATEUSERID', '�޸���', 4, 1, '', 14, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5032, 103, 'a.orna_code', '��Ʒ���', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5033, 103, 'a.allow_iniv_type', '�������', 1, 5, 'inivtype', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5034, 103, 'a.src_bill_no', '���ӱ��', 1, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5035, 103, 'a.createuserid', '�����Ա', 4, 1, '', 3, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5036, 103, 'a.item_class_id', '����', 4, 1, '', 4, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5037, 103, 'a.orna_class_id', 'С��', 4, 1, '', 5, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5038, 103, 'a.vender_id', '��Ӧ��', 4, 1, '', 6, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5039, 103, 'a.calcprice_no', '�˼۵���', 1, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5040, 103, 'a.INIV_DATE', '����ʱ��', 5, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5041, 103, 'a.all_qty', '��Ʒ����', 3, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6271, 103, 'a.ORNA_NAME', '��Ʒ����', 1, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6272, 103, 'a.COST_PRICE', '�ɱ�����', 3, 1, '', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6273, 103, 'a.BASIC_PRICE', '������', 2, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6274, 103, 'a.IS_DBL_LABEL', '�Ƿ�˫��ǩ', 1, 5, 'yesorno', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6275, 103, 'a.QUALITY_ID', '����', 4, 1, '', 14, 'select q.QUALITY_ID from jas_bd_quality q where q.QUALITY_DSC like''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5042, 104, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5043, 104, 'a.org_id', '��֯', 4, 1, '', 2, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5044, 104, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''������''},{value:''5'',text:''�������''},{value:''8'',text:''�ر�''},{value:''0'',text:''����''}]', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5045, 104, 'a.create_date', '����ʱ��', 5, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6210, 104, 'a.vendor_id', '��Ӧ��', 4, 1, '', 1, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6211, 104, 'a.create_id', '������', 4, 1, '', 9, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6212, 104, '(select wm_concat(c.orna_code) from jat_out_vendor_line c where c.billid = a.billid)', '��Ʒ����', 1, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6213, 104, 'a.stock_id', '�ֿ�', 1, 5, 'invcode', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6288, 104, 'a.update_id', '�޸���', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6289, 104, 'a.update_date', '�޸�ʱ��', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6290, 104, 'a.dodate', '��������', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6291, 104, 'a.sum_count', '�ܼ���', 2, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6292, 104, 'a.sum_weight', '������', 3, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6293, 104, 'a.sum_grains', '������', 2, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6294, 104, 'a.sum_money', '�ܽ��', 3, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (25042, 105, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (25043, 105, 'a.org_id', '��֯', 4, 1, '', 2, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (25044, 105, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''������''},{value:''5'',text:''�������''},{value:''8'',text:''�ر�''},{value:''0'',text:''����''}]', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (25045, 105, 'a.create_date', '����ʱ��', 5, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (26210, 105, 'a.vendor_id', '��Ӧ��', 4, 1, '', 1, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (26211, 105, 'a.create_id', '������', 4, 1, '', 9, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (26212, 105, '(select wm_concat(c.orna_code) from jat_out_vendor_line c where c.billid = a.billid)', '��Ʒ����', 1, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (26213, 105, 'a.stock_id', '�ֿ�', 1, 5, 'invcode', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (26288, 105, 'a.update_id', '�޸���', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (26289, 105, 'a.update_date', '�޸�ʱ��', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (26290, 105, 'a.dodate', '��������', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (26291, 105, 'a.sum_count', '�ܼ���', 2, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (26292, 105, 'a.sum_weight', '������', 3, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (26293, 105, 'a.sum_grains', '������', 2, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (26294, 105, 'a.sum_money', '�ܽ��', 3, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (75042, 106, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (75043, 106, 'a.org_id', '��֯', 4, 1, '', 2, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (75044, 106, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''������''},{value:''5'',text:''�������''},{value:''8'',text:''�ر�''},{value:''0'',text:''����''}]', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (75045, 106, 'a.create_date', '����ʱ��', 5, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (76210, 106, 'a.vendor_id', '��Ӧ��', 4, 1, '', 1, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (76211, 106, 'a.create_id', '������', 4, 1, '', 9, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (76212, 106, '(select wm_concat(c.orna_code) from jat_out_vendor_line c where c.billid = a.billid)', '��Ʒ����', 1, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (76213, 106, 'a.stock_id', '�ֿ�', 1, 5, 'invcode', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (76288, 106, 'a.update_id', '�޸���', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (76289, 106, 'a.update_date', '�޸�ʱ��', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (76290, 106, 'a.dodate', '��������', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (76291, 106, 'a.sum_count', '�ܼ���', 2, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (76292, 106, 'a.sum_weight', '������', 3, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (76293, 106, 'a.sum_grains', '������', 2, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (76294, 106, 'a.sum_money', '�ܽ��', 3, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15042, 107, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15043, 107, 'a.org_id', '��֯', 4, 1, '', 2, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15044, 107, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''������''},{value:''5'',text:''�������''},{value:''8'',text:''�ر�''},{value:''0'',text:''����''}]', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15045, 107, 'a.create_date', '����ʱ��', 5, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (16210, 107, 'a.vendor_id', '��Ӧ��', 4, 1, '', 1, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (16211, 107, 'a.create_id', '������', 4, 1, '', 9, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (16212, 107, '(select wm_concat(c.orna_code) from jat_out_vendor_line c where c.billid = a.billid)', '��Ʒ����', 1, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (16213, 107, 'a.stock_id', '�ֿ�', 1, 5, 'invcode', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (16288, 107, 'a.update_id', '�޸���', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (16289, 107, 'a.update_date', '�޸�ʱ��', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (16290, 107, 'a.dodate', '��������', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (16291, 107, 'a.sum_count', '�ܼ���', 2, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (16292, 107, 'a.sum_weight', '������', 3, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (16293, 107, 'a.sum_grains', '������', 2, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (16294, 107, 'a.sum_money', '�ܽ��', 3, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (35042, 108, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (35043, 108, 'a.org_id', '��֯', 4, 1, '', 2, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (35044, 108, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''������''},{value:''5'',text:''�������''},{value:''8'',text:''�ر�''},{value:''0'',text:''����''}]', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (35045, 108, 'a.create_date', '����ʱ��', 5, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (36210, 108, 'a.vendor_id', '��Ӧ��', 4, 1, '', 1, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (36211, 108, 'a.create_id', '������', 4, 1, '', 9, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (36212, 108, '(select wm_concat(c.orna_code) from jat_out_vendor_line c where c.billid = a.billid)', '��Ʒ����', 1, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (36213, 108, 'a.stock_id', '�ֿ�', 1, 5, 'invcode', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (36288, 108, 'a.update_id', '�޸���', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (36289, 108, 'a.update_date', '�޸�ʱ��', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (36290, 108, 'a.dodate', '��������', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (36291, 108, 'a.sum_count', '�ܼ���', 2, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (36292, 108, 'a.sum_weight', '������', 3, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (36293, 108, 'a.sum_grains', '������', 2, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (36294, 108, 'a.sum_money', '�ܽ��', 3, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (45042, 109, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (45043, 109, 'a.org_id', '��֯', 4, 1, '', 2, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (45044, 109, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''������''},{value:''5'',text:''�������''},{value:''8'',text:''�ر�''},{value:''0'',text:''����''}]', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (45045, 109, 'a.create_date', '����ʱ��', 5, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (46210, 109, 'a.vendor_id', '��Ӧ��', 4, 1, '', 1, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (46211, 109, 'a.create_id', '������', 4, 1, '', 9, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (46212, 109, '(select wm_concat(c.orna_code) from jat_out_vendor_line c where c.billid = a.billid)', '��Ʒ����', 1, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (46213, 109, 'a.stock_id', '�ֿ�', 1, 5, 'invcode', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (46288, 109, 'a.update_id', '�޸���', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (46289, 109, 'a.update_date', '�޸�ʱ��', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (46290, 109, 'a.dodate', '��������', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (46291, 109, 'a.sum_count', '�ܼ���', 2, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (46292, 109, 'a.sum_weight', '������', 3, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (46293, 109, 'a.sum_grains', '������', 2, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (46294, 109, 'a.sum_money', '�ܽ��', 3, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (50042, 110, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (50043, 110, 'a.org_id', '��֯', 4, 1, '', 2, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (50044, 110, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''������''},{value:''5'',text:''�������''},{value:''8'',text:''�ر�''},{value:''0'',text:''����''}]', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (50045, 110, 'a.create_date', '����ʱ��', 5, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (62010, 110, 'a.vendor_id', '��Ӧ��', 4, 1, '', 1, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (62011, 110, 'a.create_id', '������', 4, 1, '', 9, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (62012, 110, '(select wm_concat(c.orna_code) from jat_out_vendor_line c where c.billid = a.billid)', '��Ʒ����', 1, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (62013, 110, 'a.stock_id', '�ֿ�', 1, 5, 'invcode', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (62088, 110, 'a.update_id', '�޸���', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (62089, 110, 'a.update_date', '�޸�ʱ��', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (62090, 110, 'a.dodate', '��������', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (62091, 110, 'a.sum_count', '�ܼ���', 2, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (62092, 110, 'a.sum_weight', '������', 3, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (62093, 110, 'a.sum_grains', '������', 2, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (62094, 110, 'a.sum_money', '�ܽ��', 3, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (65042, 111, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (65043, 111, 'a.org_id', '��֯', 4, 1, '', 2, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (65044, 111, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''������''},{value:''5'',text:''�������''},{value:''8'',text:''�ر�''},{value:''0'',text:''����''}]', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (65045, 111, 'a.create_date', '����ʱ��', 5, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (66210, 111, 'a.vendor_id', '��Ӧ��', 4, 1, '', 1, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (66211, 111, 'a.create_id', '������', 4, 1, '', 9, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (66212, 111, '(select wm_concat(c.orna_code) from jat_out_vendor_line c where c.billid = a.billid)', '��Ʒ����', 1, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (66213, 111, 'a.stock_id', '�ֿ�', 1, 5, 'invcode', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (66288, 111, 'a.update_id', '�޸���', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (66289, 111, 'a.update_date', '�޸�ʱ��', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (66290, 111, 'a.dodate', '��������', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (66291, 111, 'a.sum_count', '�ܼ���', 2, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (66292, 111, 'a.sum_weight', '������', 3, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (66293, 111, 'a.sum_grains', '������', 2, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (66294, 111, 'a.sum_money', '�ܽ��', 3, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (55042, 112, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (55043, 112, 'a.org_id', '��֯', 4, 1, '', 2, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (55044, 112, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''������''},{value:''5'',text:''�������''},{value:''8'',text:''�ر�''},{value:''0'',text:''����''}]', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (55045, 112, 'a.create_date', '����ʱ��', 5, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (56210, 112, 'a.vendor_id', '��Ӧ��', 4, 1, '', 1, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (56211, 112, 'a.create_id', '������', 4, 1, '', 9, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (56212, 112, '(select wm_concat(c.orna_code) from jat_out_vendor_line c where c.billid = a.billid)', '��Ʒ����', 1, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (56213, 112, 'a.stock_id', '�ֿ�', 1, 5, 'invcode', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (56288, 112, 'a.update_id', '�޸���', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (56289, 112, 'a.update_date', '�޸�ʱ��', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (56290, 112, 'a.dodate', '��������', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (56291, 112, 'a.sum_count', '�ܼ���', 2, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (56292, 112, 'a.sum_weight', '������', 3, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (56293, 112, 'a.sum_grains', '������', 2, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (56294, 112, 'a.sum_money', '�ܽ��', 3, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5078, 114, 'org_id', '��֯', 4, 1, '', 0, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5079, 114, 'stock_id', '�ֿ�', 1, 5, 'invcode', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5080, 114, 'stock_id', '״̬', 1, 3, '[{value:''900'',text:''��Ч''},{value:''901'',text:''����''}]', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5081, 114, 'article_type_id', '��Ʒ���', 4, 1, '', 3, 'select at.ARTICLE_TYPE_ID from jas_bd_articletype at where at.ARTICLE_TYPE_DSC like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5082, 114, 'item_class_id', '����', 4, 1, '', 4, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5083, 114, 'orna_class_id', 'С��', 4, 1, '', 5, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5084, 114, 'styleitemclass', '��ʽ����', 4, 1, '', 6, 'select si.ITEM_CLASS_ID from jas_bd_style_item_class si where si.ITEM_CLASS_NAME like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5085, 114, 'stylemiddleclass', '��ʽ����', 4, 1, '', 7, 'select mc.MIDDLE_CLASS_ID from jas_bd_style_middle_class mc where mc.MIDDLE_CLASS_NAME like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5086, 114, 'styleornaclass', '��ʽС��', 4, 1, '', 8, 'select oc.ORNA_CLASS_ID from jas_bd_style_orna_class oc where oc.ORNA_CLASS_NAME like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5087, 114, 'style_id', '��ʽ', 4, 1, '', 9, 'select bs.STYLEID from jas_bd_style bs where bs.STYLENAME like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5088, 114, 'pingz', '��ʯƷ��', 1, 5, 'gradelevel', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5089, 114, 'main_color_grade_id', 'ɫ��', 1, 5, 'diacolorgrade', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5090, 114, 'clean_id', '����', 1, 5, 'diaclean', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5091, 114, 'main_shape_id', '��״', 1, 5, 'diashape', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5092, 114, 'handover_bill_id', '���ӵ���', 4, 1, '', 14, 'select hh.billid from jat_pur_handover_head hh  where hh.billno like ''%$%'' ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5093, 114, 'is_consign', '�Ƿ����', 1, 5, 'yesorno', 15, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5094, 114, 'all_qty', '������', 3, 1, '', 16, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5095, 114, 'pos_amount', '������', 3, 1, '', 17, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5096, 114, 'ORNA_CODE', '��Ʒ����', 1, 1, '', 18, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5097, 114, 'SIZE_ID', '�ߴ�', 2, 1, '', 19, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (5098, 114, 'theme_id', '����', 4, 1, '', 20, 'select t.THEME_ID from jas_pl_bd_style_theme t where t.THEME_NAME like ''%$%'' ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6252, 114, 'alaysis_id', '������Χ', 4, 1, '', 21, 'select a.ANALYSIS_ARANGE_ID from jas_bd_analysis_arange a where a.ANALYSIS_ARANGE_CODE = ''$''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6253, 114, 'ORNA_DSC', '��Ʒ����', 1, 1, '', 22, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6254, 114, 'is_consign', '�Ƿ����', 1, 5, 'yesorno', 23, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6255, 114, 'quality_id', '�мܲ���', 4, 1, '', 24, 'select q.QUALITY_ID from jas_bd_quality q where q.QUALITY_DSC like''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6256, 114, 'IDENT_ID', '����֤��', 1, 1, '', 26, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6257, 114, 'HRD_CERT', 'HRD����֤��', 1, 1, '', 27, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6258, 114, 'GIA_CERT', 'GIA����֤��', 1, 1, '', 28, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6259, 114, 'IGI_CERT', 'IGI����֤��', 1, 1, '', 29, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6260, 114, 'AGS_CERT', 'AGS����֤��', 1, 1, '', 30, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86385, 114, 'GROUPS', '����', 1, 5, 'group', 30, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86386, 114, 'NOW_QTY', '������', 3, 1, '', 31, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86387, 114, 'TAG_TYPE', '��ǩ����', 1, 1, '', 32, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86388, 114, 'SALE_UNIT_ID', '������λ', 4, 1, '', 33, 'select t.UNITID from jas_bd_unit t where t.UNITNAME like ''%$%'' ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86389, 114, 'PRICE_ATTR_GROUP', '�۸�������', 1, 1, '', 34, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86390, 114, 'STORE_COLOR', '�ŵ���ɫ', 1, 5, 'diacolor', 35, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86391, 114, 'COM_STORE', '��˾��ɫ', 1, 5, 'diacolor', 36, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86392, 114, 'COLOR_ID', '��ʯ��ɫ', 1, 5, 'diacolor', 37, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86393, 114, 'MAIN_WEIGHT', '��ʯ����', 3, 1, '', 38, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86394, 114, 'PART_WEIGHT', '��ʯ����', 3, 1, '', 39, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86395, 114, 'PART_CONTENT', '��ʯ����', 1, 1, '', 40, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86396, 114, 'STONE_TOTAL_NUM', '��ʯ������', 2, 1, '', 41, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86397, 114, 'STONE_NOW_NUM', '��������', 2, 1, '', 42, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86398, 114, 'SPECIAL_WEIGHT', '��������', 3, 1, '', 43, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86402, 114, 'CUT_ID', '�й�', 1, 5, 'diacut', 44, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86403, 114, 'CUT_WIDE_SCALE', '�й�̨���', 3, 1, '', 45, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86404, 114, 'CUT_DEEP_SCALE', '�й�ͤ���', 3, 1, '', 46, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86405, 114, 'SYMMETRY_ID', '�Գ���', 1, 5, 'diasymm', 47, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86406, 114, 'POLISHINE_ID', '�׹�', 1, 5, 'diapoli', 48, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86407, 114, 'FLUORESCENCE_ID', 'ӫ��', 1, 5, 'diafluor', 49, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86408, 114, 'WAISTLINE_ID', '��Χ', 1, 5, 'diawais', 50, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86409, 114, 'VERTEX_ID', '�׼�', 1, 5, 'diavertex', 51, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86410, 114, 'BRACKETCOLOR_ID', '�м���ɫ', 1, 1, '', 52, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86411, 114, 'WEAR_ID', '�������', 1, 5, 'wearId', 53, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86412, 114, 'IS_MUTI_PART', '�Ƿ����', 1, 5, 'yesoron', 54, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86413, 114, 'MAIN_NUM', '��ʯ����', 2, 1, '', 55, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86414, 114, 'PART_NUM', '��ʯ����', 2, 1, '', 56, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86415, 114, 'TOSHOPDATE', '��������', 5, 1, '', 59, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86416, 114, 'STORAGEDATE', '�������', 5, 1, '', 60, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15078, 300, 'org_id', '��֯', 4, 1, '', 0, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15079, 300, 'stock_id', '�ֿ�', 1, 5, 'invcode', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15080, 300, 'stock_id', '״̬', 1, 3, '[{value:''900'',text:''��Ч''},{value:''901'',text:''����''}]', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15081, 300, 'article_type_id', '��Ʒ���', 4, 1, '', 3, 'select at.ARTICLE_TYPE_ID from jas_bd_articletype at where at.ARTICLE_TYPE_DSC like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15082, 300, 'item_class_id', '����', 4, 1, '', 4, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15083, 300, 'orna_class_id', 'С��', 4, 1, '', 5, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15084, 300, 'styleitemclass', '��ʽ����', 4, 1, '', 6, 'select si.ITEM_CLASS_ID from jas_bd_style_item_class si where si.ITEM_CLASS_NAME like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15085, 300, 'stylemiddleclass', '��ʽ����', 4, 1, '', 7, 'select mc.MIDDLE_CLASS_ID from jas_bd_style_middle_class mc where mc.MIDDLE_CLASS_NAME like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15086, 300, 'styleornaclass', '��ʽС��', 4, 1, '', 8, 'select oc.ORNA_CLASS_ID from jas_bd_style_orna_class oc where oc.ORNA_CLASS_NAME like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15087, 300, 'style_id', '��ʽ', 4, 1, '', 9, 'select bs.STYLEID from jas_bd_style bs where bs.STYLENAME like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15088, 300, 'pingz', '��ʯƷ��', 1, 5, 'gradelevel', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15089, 300, 'main_color_grade_id', 'ɫ��', 1, 5, 'diacolorgrade', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15090, 300, 'clean_id', '����', 1, 5, 'diaclean', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15091, 300, 'main_shape_id', '��״', 1, 5, 'diashape', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15092, 300, 'handover_bill_id', '���ӵ���', 4, 1, '', 14, 'select hh.billid from jat_pur_handover_head hh  where hh.billno like ''%$%'' ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15093, 300, 'is_consign', '�Ƿ����', 1, 5, 'yesorno', 15, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15094, 300, 'all_qty', '������', 3, 1, '', 16, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15095, 300, 'pos_amount', '������', 3, 1, '', 17, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15096, 300, 'ORNA_CODE', '��Ʒ����', 1, 1, '', 18, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15097, 300, 'SIZE_ID', '�ߴ�', 2, 1, '', 19, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (15098, 300, 'theme_id', '����', 4, 1, '', 20, 'select t.THEME_ID from jas_pl_bd_style_theme t where t.THEME_NAME like ''%$%'' ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (16252, 300, 'alaysis_id', '������Χ', 4, 1, '', 21, 'select a.ANALYSIS_ARANGE_ID from jas_bd_analysis_arange a where a.ANALYSIS_ARANGE_CODE = ''$''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (16253, 300, 'ORNA_DSC', '��Ʒ����', 1, 1, '', 22, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (16254, 300, 'is_consign', '�Ƿ����', 1, 5, 'yesorno', 23, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (16255, 300, 'quality_id', '�мܲ���', 4, 1, '', 24, 'select q.QUALITY_ID from jas_bd_quality q where q.QUALITY_DSC like''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (16256, 300, 'IDENT_ID', '����֤��', 1, 1, '', 26, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (16257, 300, 'HRD_CERT', 'HRD����֤��', 1, 1, '', 27, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (16258, 300, 'GIA_CERT', 'GIA����֤��', 1, 1, '', 28, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (16259, 300, 'IGI_CERT', 'IGI����֤��', 1, 1, '', 29, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (16260, 300, 'AGS_CERT', 'AGS����֤��', 1, 1, '', 30, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186385, 300, 'GROUPS', '����', 1, 5, 'group', 30, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186386, 300, 'NOW_QTY', '������', 3, 1, '', 31, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186387, 300, 'TAG_TYPE', '��ǩ����', 1, 1, '', 32, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186388, 300, 'SALE_UNIT_ID', '������λ', 4, 1, '', 33, 'select t.UNITID from jas_bd_unit t where t.UNITNAME like ''%$%'' ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186389, 300, 'PRICE_ATTR_GROUP', '�۸�������', 1, 1, '', 34, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186390, 300, 'STORE_COLOR', '�ŵ���ɫ', 1, 5, 'diacolor', 35, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186391, 300, 'COM_STORE', '��˾��ɫ', 1, 5, 'diacolor', 36, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186392, 300, 'COLOR_ID', '��ʯ��ɫ', 1, 5, 'diacolor', 37, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186393, 300, 'MAIN_WEIGHT', '��ʯ����', 3, 1, '', 38, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186394, 300, 'PART_WEIGHT', '��ʯ����', 3, 1, '', 39, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186395, 300, 'PART_CONTENT', '��ʯ����', 1, 1, '', 40, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186396, 300, 'STONE_TOTAL_NUM', '��ʯ������', 2, 1, '', 41, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186397, 300, 'STONE_NOW_NUM', '��������', 2, 1, '', 42, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186398, 300, 'SPECIAL_WEIGHT', '��������', 3, 1, '', 43, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186402, 300, 'CUT_ID', '�й�', 1, 5, 'diacut', 44, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186403, 300, 'CUT_WIDE_SCALE', '�й�̨���', 3, 1, '', 45, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186404, 300, 'CUT_DEEP_SCALE', '�й�ͤ���', 3, 1, '', 46, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186405, 300, 'SYMMETRY_ID', '�Գ���', 1, 5, 'diasymm', 47, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186406, 300, 'POLISHINE_ID', '�׹�', 1, 5, 'diapoli', 48, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186407, 300, 'FLUORESCENCE_ID', 'ӫ��', 1, 5, 'diafluor', 49, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186408, 300, 'WAISTLINE_ID', '��Χ', 1, 5, 'diawais', 50, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186409, 300, 'VERTEX_ID', '�׼�', 1, 5, 'diavertex', 51, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186410, 300, 'BRACKETCOLOR_ID', '�м���ɫ', 1, 1, '', 52, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186411, 300, 'WEAR_ID', '�������', 1, 5, 'wearId', 53, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186412, 300, 'IS_MUTI_PART', '�Ƿ����', 1, 5, 'yesoron', 54, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186413, 300, 'MAIN_NUM', '��ʯ����', 2, 1, '', 55, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186414, 300, 'PART_NUM', '��ʯ����', 2, 1, '', 56, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186415, 300, 'TOSHOPDATE', '��������', 5, 1, '', 59, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186416, 300, 'STORAGEDATE', '�������', 5, 1, '', 60, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6000, 302, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6001, 302, 'a.lock_flag', '�Ƿ�����', 1, 5, 'yesorno', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6002, 302, 'a.sum_count', '�����ϼ�', 2, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6003, 302, 'a.create_date', '����ʱ��', 5, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6004, 302, 'a.create_id', '������', 4, 1, '', 4, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6005, 302, 'a.update_date', '�޸�ʱ��', 5, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6006, 302, 'a.update_id', '�޸���', 4, 1, '', 6, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6007, 302, 'a.status', '״̬', 1, 2, 'select distinct b.item_order as "value", b.item_value as "text" from jat_basic_price_lock_head a, jat_sys_dict_item b where a.status = b.item_key and b.entry_code = ''status'' order by b.item_order', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6010, 305, 'a.billno', '���ݱ���', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6011, 305, 'a.STATUS', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''������''},{value:''5'',text:''�������''},{value:''10'',text:''�ѽ���''},{value:''8'',text:''�ر�''}]', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6012, 305, 'a.create_id', '������', 4, 1, '', 8, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6013, 305, 'a.verdor_id', '��Ӧ��', 4, 1, '', 1, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6014, 305, 'a.source_no', '��Դ����', 1, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6015, 305, 'a.dodate', 'ҵ��ʱ��', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6016, 305, 'a.dotype', 'ҵ������', 1, 5, 'purbiztype', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6017, 305, 'a.sum_num', '������', 3, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6018, 305, 'a.create_date', '����ʱ��', 5, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6019, 305, 'a.IS_CHECK', '�Ƿ����', 1, 5, 'yesorno', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6020, 305, 'a.is_bill', '�Ƿ���Ʊ', 1, 5, 'yesorno', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86296, 305, 'a.update_id', '�޸���', 4, 1, '', 10, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86297, 305, 'a.update_date', '�޸�ʱ��', 5, 1, '', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6050, 306, 'a.bill_no', '��Դ���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6051, 306, 'a.bill_type', '��Դ��������', 1, 5, 'billcode', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6052, 306, 'a.dotype', 'ҵ������', 1, 3, '[{value:''-1'', text:''���ӵ�''},{value:''1'',text:''ί�ⷢ��''}]', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6053, 306, 'a.voder_id', '��Ӧ��', 4, 1, '', 3, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6054, 306, 'a.memo', '��Ʒ����', 1, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6055, 306, 'a.material_type', 'ԭ������', 4, 1, '', 5, 'select a.item_key from jat_sys_dict_item a where a.entry_code=''materialtype'' and a.item_value like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6056, 306, 'a.item_class_id', '��Ʒ����', 4, 1, '', 6, 'select b.ITEM_CLASS_ID from jas_bd_articletype e left join jas_bd_item_class b on b.article_type_id = e.article_type_id where  e.article_type_dsc like ''%$%'';');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6057, 306, 'a.item_class_id', '����', 4, 1, '', 7, 'select b.ITEM_CLASS_ID from jas_bd_item_class b where  b.ITEM_CLASS_DSC like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6058, 306, 'a.bill_nums', '��������', 3, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6059, 306, 'a.cash_nums', '�Ѻ�������', 3, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6060, 306, 'a.no_nums', 'δ��������', 3, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6061, 306, 'a.is_checked', '�������', 1, 5, 'yesorno', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6062, 306, 'a.create_id', '������', 4, 1, '', 12, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6063, 306, 'a.create_date', '����ʱ��', 5, 1, '', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6064, 306, 'a.STATUS', '״̬', 1, 3, '[{value:''2'',text:''����''},{value:''12'',text:''������''}]', 16, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86304, 306, 'a.update_id', '�޸���', 4, 1, '', 14, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86305, 306, 'a.update_date', '�޸�ʱ��', 5, 1, '', 15, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6065, 307, 'a.bill_no', '��Դ���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6066, 307, 'a.bill_type', '��Դ��������', 1, 5, 'billcode', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6067, 307, 'a.dotype', 'ҵ������', 1, 3, '[{value:''-1'',text:''Ӧ���˿�''},{value:''1'',text:''Ӧ���˿�''}]', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6068, 307, 'a.voder_id', '��Ӧ��', 4, 1, '', 3, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6069, 307, 'a.bill_date', 'ҵ��ʱ��', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6070, 307, 'a.moneys', '������', 3, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6071, 307, 'a.loss_money', '���', 3, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6072, 307, 'a.create_id', '������', 4, 1, '', 7, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6073, 307, 'a.create_date', '����ʱ��', 5, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6074, 307, 'a.status', '״̬', 1, 3, '[{value:''2'',text:''����''},{value:''12'',text:''������''}]', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86306, 307, 'a.update_id', '�޸���', 4, 1, '', 9, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86307, 307, 'a.update_date', '�޸�ʱ��', 5, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6076, 308, 'a.bill_no', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6077, 308, 'a.voder_id', '��Ӧ��', 4, 1, '', 1, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6078, 308, 'a.bill_date', 'ҵ��ʱ��', 5, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6079, 308, 'a.pay_money', '������', 3, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6080, 308, 'a.create_id', '������', 4, 1, '', 4, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6081, 308, 'a.create_date', '����ʱ��', 5, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6082, 308, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''8'',text:''�ر�''}]', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86298, 308, 'a.update_id', '�޸���', 4, 1, '', 6, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86299, 308, 'a.update_date', '�޸�ʱ��', 5, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6084, 310, 'a.bill_no', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6085, 310, 'a.voder_id', '��Ӧ��', 4, 1, '', 1, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6086, 310, 'a.bill_date', '��������', 5, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6087, 310, 'a.cash_nums', '��������', 3, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6088, 310, 'a.create_id', '������', 4, 1, '', 4, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6089, 310, 'a.create_date', '����ʱ��', 5, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6090, 310, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''������''},{value:''5'',text:''�������''},{value:''8'',text:''�ر�''}]', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86300, 310, 'a.update_id', '�޸���', 4, 1, '', 6, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86301, 310, 'a.update_date', '�޸�ʱ��', 5, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6091, 311, 'a.bill_no', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6092, 311, 'a.voder_id', '��Ӧ��', 4, 1, '', 1, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6093, 311, 'a.bill_date', '��������', 5, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6094, 311, 'a.material_type_old', 'ԭ����', 4, 1, '', 3, 'select t.item_key from jat_sys_dict_item t where t.entry_code = ''materialtype'' and t.item_value like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6095, 311, 'a.weight_old', 'ԭ����', 3, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6096, 311, 'a.material_type_new', '������', 4, 1, '', 5, 'select t.item_key from jat_sys_dict_item t where t.entry_code = ''materialtype'' and t.item_value like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6097, 311, 'a.weight_new', '������', 3, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6098, 311, 'a.memo', 'ת��˵��', 1, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6099, 311, 'a.create_id', '������', 4, 1, '', 8, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6100, 311, 'a.create_date', '����ʱ��', 5, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6101, 311, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''������''},{value:''5'',text:''�������''},{value:''8'',text:''�ر�''}]', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86302, 311, 'a.update_id', '�޸���', 4, 1, '', 10, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86303, 311, 'a.update_date', '�޸�ʱ��', 5, 1, '', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6102, 312, 'a.bill_no', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6103, 312, 'a.voder_id', '��Ӧ��', 4, 1, '', 1, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6104, 312, 'a.bill_date', '��������', 5, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6105, 312, 'a.item_class_id', '��Ʒ���', 4, 1, '', 3, 'select b.item_class_id from jas_bd_articletype e left join jas_bd_item_class b on b.article_type_id = e.article_type_id where e.ARTICLE_TYPE_DSC like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6106, 312, 'a.item_class_id', '����', 4, 1, '', 4, 'select b.ITEM_CLASS_ID from jas_bd_item_class b where  b.ITEM_CLASS_DSC like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6107, 312, 'a.cha_nums', '����', 1, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6108, 312, 'a.dotype', 'ҵ������', 1, 3, '[{value:''0'',text:''�������''}, {value:''1'', text:''��������''}]', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6109, 312, 'a.bill_type', '��������', 1, 3, '[{value:''-1'',text:''���ӵ�''}, {value:''1'', text:''ί�ⷢ��''}]', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6110, 312, 'a.create_id', '������', 4, 1, '', 8, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6111, 312, 'a.create_date', '����ʱ��', 5, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6112, 312, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''������''},{value:''5'',text:''�������''},{value:''8'',text:''�ر�''}]', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86308, 312, 'a.update_id', '�޸���', 4, 1, '', 10, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86309, 312, 'a.update_date', '�޸�ʱ��', 5, 1, '', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6113, 313, 'a.bill_no', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6114, 313, 'a.voder_id', '��Ӧ��', 4, 1, '', 1, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6115, 313, 'a.bill_date', '��������', 5, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6116, 313, 'a.dotype', 'ҵ������', 1, 3, '[{value:''0'',text:''�������''}, {value:''1'', text:''��������''}]', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6117, 313, 'a.bill_type', '��������', 1, 3, '[{value:''-1'',text:''Ӧ��''}, {value:''1'', text:''Ӧ��''}]', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6118, 313, 'a.cha_money', '���', 3, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6119, 313, 'a.create_id', '������', 4, 1, '', 6, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6120, 313, 'a.create_date', '����ʱ��', 5, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6121, 313, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''������''},{value:''5'',text:''�������''},{value:''8'',text:''�ر�''}]', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86310, 313, 'a.update_id', '�޸���', 4, 1, '', 8, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86311, 313, 'a.update_date', '�޸�ʱ��', 5, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6122, 314, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6123, 314, 'a.dotype', 'ҵ������', 1, 5, 'packagetype', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6124, 314, 'a.stock_id', '�ֿ�', 1, 5, 'invcode', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6125, 314, 'a.dodate', '��������', 5, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6126, 314, 'a.old_weight', 'ԭ�����ϼ�', 3, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6127, 314, 'a.old_grains', 'ԭ�����ϼ�', 2, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6128, 314, 'a.new_weight', '�������ϼ�', 3, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6129, 314, 'a.new_grains', '�������ϼ�', 2, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6130, 314, 'a.create_id', '������', 4, 1, '', 8, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6131, 314, 'a.create_date', '����ʱ��', 5, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6132, 314, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''������''},{value:''5'',text:''�������''},{value:''8'',text:''�ر�''}]', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86312, 314, 'a.update_id', '�޸���', 4, 1, '', 10, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86313, 314, 'a.update_date', '�޸�ʱ��', 5, 1, '', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6133, 315, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6134, 315, 'a.stock_id', '�ֿ�', 1, 5, 'invcode', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6135, 315, 'a.dodate', '��������', 5, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6136, 315, 'a.sum_count', '�����ϼ�', 2, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6137, 315, 'a.sum_weight', '�����ϼ�', 3, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6138, 315, 'a.sum_grains', '�����ϼ�', 2, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6139, 315, 'a.create_id', '������', 4, 1, '', 6, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6140, 315, 'a.create_date', '����ʱ��', 5, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6141, 315, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''������''},{value:''5'',text:''�������''},{value:''8'',text:''�ر�''}]', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86314, 315, 'a.update_id', '�޸���', 4, 1, '', 8, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86315, 315, 'a.update_date', '�޸�ʱ��', 5, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86522, 315, 'a.vendor_id', '��Ӧ��', 4, 1, '', 11, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6142, 316, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6143, 316, 'a.stock_id', '�ֿ�', 1, 5, 'invcode', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6144, 316, 'a.dodate', '��������', 5, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6145, 316, 'a.sum_grains', 'ԭ�����ϼ�', 2, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6146, 316, 'a.sum_weight', 'ԭ�����ϼ�', 3, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6147, 316, 'a.sum_count', 'ԭ�����ϼ�', 2, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6148, 316, 'a.sum_money', 'ԭ���ϼ�', 3, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6149, 316, 'a.create_id', '������', 4, 1, '', 7, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6150, 316, 'a.create_date', '����ʱ��', 5, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6151, 316, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''������''},{value:''5'',text:''�������''},{value:''8'',text:''�ر�''}]', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86316, 316, 'a.update_id', '�޸���', 4, 1, '', 9, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86317, 316, 'a.update_date', '�޸�ʱ��', 5, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6152, 317, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6153, 317, 'a.org_id', '��֯', 4, 1, '', 1, 'select t.ORGID from jas_bd_emp_org t left join jas_sm_org b on t.orgid = b.org_id where b.org_type = 1 and b.ORG_NAME like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6154, 317, 'a.dodate', '��������', 5, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6155, 317, 'a.sum_count', '�̵����', 2, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6156, 317, 'a.stock_id', '�̵�ֿ�', 1, 5, 'invcode', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6157, 317, 'a.ismain', '�Ƿ�����', 1, 5, 'yesorno', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6158, 317, 'a.create_id', '������', 4, 1, '', 6, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6159, 317, 'a.create_date', '����ʱ��', 5, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6160, 317, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''8'',text:''�ر�''}]', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86318, 317, 'a.update_id', '�޸���', 4, 1, '', 8, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86319, 317, 'a.update_date', '�޸�ʱ��', 5, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6161, 318, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6162, 318, 'a.source_no', '��Դ����', 1, 1, '', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6163, 318, 'a.source_type', '��Դ����', 1, 5, 'billcode', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6164, 318, 'a.dodate', 'ҵ��ʱ��', 5, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6165, 318, 'a.verdor_id', '��Ӧ��', 4, 1, '', 4, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6166, 318, 'a.create_id', '������', 4, 1, '', 5, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6167, 318, 'a.create_date', '����ʱ��', 5, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6168, 318, 'a.STATUS', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''������''},{value:''5'',text:''�������''},{value:''10'',text:''�ѽ���''},{value:''8'',text:''�ر�''}]', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86320, 318, 'a.update_id', '�޸���', 4, 1, '', 7, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86321, 318, 'a.update_date', '�޸�ʱ��', 5, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6174, 320, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6175, 320, 'a.vendor_id', '��Ӧ��', 4, 1, '', 1, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6176, 320, 'a.dodate', '��������', 5, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6177, 320, 'a.invoice_no', '��Ʊ����', 1, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6178, 320, 'a.invoice_money', '��Ʊ���', 3, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6179, 320, 'a.create_id', '������', 4, 1, '', 5, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6180, 320, 'a.create_date', '����ʱ��', 5, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6181, 320, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''8'',text:''�ر�''}]', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86324, 320, 'a.update_id', '�޸���', 4, 1, '', 7, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86325, 320, 'a.update_date', '�޸�ʱ��', 5, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6182, 321, 'a.billno', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6183, 321, 'a.vendor_id', '��Ӧ��', 4, 1, '', 1, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6184, 321, 'a.dodate', '��������', 5, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6185, 321, 'a.invoice_no', '��Ʊ����', 1, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6186, 321, 'a.invoice_money', '��Ʊ���', 3, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6187, 321, 'a.create_id', '������', 4, 1, '', 5, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6188, 321, 'a.create_date', '����ʱ��', 5, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6189, 321, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''8'',text:''�ر�''}]', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86322, 321, 'a.update_id', '�޸���', 4, 1, '', 7, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86323, 321, 'a.update_date', '�޸�ʱ��', 5, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6190, 322, 'a.no', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6191, 322, 'a.org_id', '��֯', 4, 1, '', 1, 'select t.ORGID from jas_bd_emp_org t left join jas_sm_org b on t.orgid=b.org_id where b.org_name like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6192, 322, 'a.num_total', '�����ϼ�', 2, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6193, 322, 'a.tag_print_bill_id', '��ӡ����', 4, 1, '', 3, 'select t.id  from jat_lable_tag_print t where t.no like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6194, 322, 'a.changelabel_id', '����ǩԭ��', 4, 1, '', 4, 'select t.changelabel_id from jat_basic_sale_changelabel t where t.changelabel_reason like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6195, 322, 'a.createuserid', '������', 4, 1, '', 6, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6196, 322, 'a.createdate', '����ʱ��', 5, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6197, 322, 'a.state', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''������''},{value:''5'',text:''�������''},{value:''15'',text:''�Ѻϲ�''},{value:''14'',text:''������''},{value:''8'',text:''�ر�''}]', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6198, 322, 'a.id', '��Ʒ����', 4, 1, '', 5, 'select b.head_id from jat_lable_tag_apply_line b where b.orna_code like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86327, 322, 'a.updatedate', '�޸�ʱ��', 5, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6199, 323, 'a.no', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6200, 323, 'a.org_id', '��֯', 4, 1, '', 1, 'select t.ORGID from jas_bd_emp_org t left join jas_sm_org b on t.orgid=b.org_id where b.org_name like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6201, 323, 'a.print_type', '��ӡ����', 1, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6202, 323, 'a.num_total', '�����ϼ�', 2, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6203, 323, 'a.receive_num', '���պϼ�', 2, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6204, 323, 'a.label_receive_type', '���շ�ʽ', 1, 5, 'labelTagApplyPrint', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6205, 323, 'a.receive_userid', '������Ա', 4, 1, '', 6, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6206, 323, 'a.createuserid', '������', 4, 1, '', 8, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6207, 323, 'a.createdate', '����ʱ��', 5, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6208, 323, 'a.state', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''15'',text:''�Ѻϲ�''},{value:''14'',text:''������''},{value:''8'',text:''�ر�''}]', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6209, 323, 'a.id', '��Ʒ����', 4, 1, '', 7, 'select b.head_id from jat_lable_tag_print_line b where b.orna_code like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86328, 323, 'a.updateuserid', '�޸���', 4, 1, '', 10, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86329, 323, 'a.updatedate', '�޸�ʱ��', 5, 1, '', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6214, 324, 'a.no', '���ݱ��', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6215, 324, 'a.print_type', '��ӡ����', 1, 1, '', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6216, 324, 'a.num_total', '�����ϼ�', 2, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6217, 324, 'a.receive_num', '���պϼ�', 2, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6218, 324, 'a.label_receive_type', '���շ�ʽ', 1, 5, 'labelTagApplyPrint', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6219, 324, 'a.receive_userid', '������Ա', 4, 1, '', 5, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6220, 324, 'a.id', '��Ʒ����', 4, 1, '', 6, 'select b.head_id from jat_lable_tag_print_line b where b.orna_code like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6221, 324, 'a.createuserid', '������', 4, 1, '', 7, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (6222, 324, 'a.createdate', '����ʱ��', 5, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86330, 324, 'a.updateuserid', '�޸���', 4, 1, '', 9, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86331, 324, 'a.updatedate', '�޸�ʱ��', 5, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86384, 326, 'a.headquarter_gather_no', '�ܵ����', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86400, 326, 'a.region_id', '����', 1, 2, 'select a.region_id as "value", a.region_name as "text" from jas_pl_region a order by a.region_name', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86401, 326, 'e.cycle_type_id', '��������', 1, 2, 'select a.CYCLE_TYPE_ID as "value", a.CYCLE_TYPE_CODE as "text" from jas_pl_cycle_type a where a.isorno_sealed = 0 order by a.default_arrival_days', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86417, 326, 'decode(e.order_calender_id, null, (select h.article_type_dsc from jas_bd_articletype h where h.article_type_id = a.article_type_id), (select wm_concat(distinct h.article_type_dsc) from jas_pl_calender_articletype g, jas_bd_articletype h where g.order_calender_id = e.order_calender_id and g.article_type_id = h.article_type_id))', '��Ʒ���', 1, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86418, 326, 'a.order_type', '��������', 1, 5, 'ordertype', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86419, 326, 'a.order_kind', 'Ҫ������', 1, 5, 'orderkind', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86420, 326, 'a.gather_user', '������', 4, 1, '', 6, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86421, 326, 'a.gather_date', '��������', 5, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86422, 326, 'a.state', '״̬', 1, 3, '[{value:"55", text:"�ѻ���"}, {value:"69", text:"�����ɲɹ���"}, {value:"8", text:"�ر�"}]', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86423, 326, 'a.dotype', 'ִ�й���', 1, 5, 'dispatchdotype', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86424, 326, '(select case when count(1) > 0 then 1 else 0 end from jat_pl_dispatch_condition a2 where a.headquarter_gather_head_id = a2.gather_head_id)', '�����', 1, 5, 'yesorno', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86426, 328, 'a.org_id', '��֯', 4, 1, '', 0, 'select b.ORG_ID from jas_sm_org b where b.ORG_NAME like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86427, 328, 'a.item_class_id', '����', 4, 1, '', 1, 'select b.ITEM_CLASS_ID from jas_bd_item_class b where b.ITEM_CLASS_DSC like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86428, 328, 'a.orna_class_id', 'С��', 4, 1, '', 2, 'select b.ORNA_CLASS_ID from jas_bd_orna_class b where b.ORNA_CLASS_DSC like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86429, 328, 'a.groups', '����', 4, 1, '', 3, 'select b.item_key from jat_sys_dict_item b where b.entry_code=''group'' and b.item_value like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86430, 328, 'a.create_id', '������', 4, 1, '', 4, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86431, 328, 'a.create_date', '����ʱ��', 5, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86432, 328, 'a.update_id', '�޸���', 4, 1, '', 6, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86433, 328, 'a.update_date', '�޸�ʱ��', 5, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86438, 330, 'a.billno', '���ݱ���', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86439, 330, 'a.org_id', '��֯', 4, 1, '', 1, 'select t.ORGID from jas_bd_emp_org t left join jas_sm_org b on t.orgid=b.org_id where b.org_name like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86440, 330, 'a.createid', '������', 4, 1, '', 3, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86441, 330, 'a.createdate', '����ʱ��', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86442, 330, 'a.updateid', '�޸���', 4, 1, '', 5, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86443, 330, 'a.updatedate', '�޸�ʱ��', 4, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86444, 330, 'a.status', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''������''},{value:''5'',text:''�������''},{value:''8'',text:''�ر�''}]', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86445, 330, 'a.headid', '��Ʒ����', 4, 1, '', 2, 'select b.headid from jat_lable_tag_import_line b where b.orna_code like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86446, 332, 'a.item_class_id', '����', 4, 1, '', 0, 'select b.ITEM_CLASS_ID from jas_bd_item_class b where b.ITEM_CLASS_DSC like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86447, 332, 'a.weight_str', '����ʼ', 3, 1, '', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86448, 332, 'a.weight_end', '����ֹ', 3, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86449, 332, 'a.coefficient', 'ϵ��', 3, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86450, 332, 'a.memo', '��ע', 1, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86451, 332, 'a.create_id', '������', 4, 1, '', 5, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86452, 332, 'a.create_date', '����ʱ��', 5, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86453, 332, 'a.update_id', '�޸���', 4, 1, '', 7, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86454, 332, 'a.update_date', '�޸�ʱ��', 5, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86455, 333, 'a.material_type', 'ԭ������', 1, 5, 'materialtype', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86456, 333, 'a.item_class_id', '����', 4, 1, '', 1, 'select b.ITEM_CLASS_ID from jas_bd_item_class b where b.ITEM_CLASS_DSC like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86457, 333, 'a.pur_unit', '���㵥λ', 1, 5, 'purunit', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86458, 333, 'a.memo', '��ע', 1, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86459, 333, 'a.create_id', '������', 4, 1, '', 4, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86460, 333, 'a.create_date', '����ʱ��', 5, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86461, 333, 'a.update_id', '�޸���', 4, 1, '', 6, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86462, 333, 'a.update_date', '�޸�ʱ��', 5, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86464, 334, 'a.article_type_id', '��Ʒ���', 4, 1, '', 0, 'select b.ARTICLE_TYPE_ID from jas_bd_articletype b where b.ARTICLE_TYPE_DSC like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86465, 334, 'a.item_class_id', '����', 4, 1, '', 1, 'select b.ITEM_CLASS_ID from jas_bd_item_class b where b.ITEM_CLASS_DSC like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86466, 334, 'a.orna_class_id', 'С��', 4, 1, '', 2, 'select b.ORNA_CLASS_ID from jas_bd_orna_class b where b.ORNA_CLASS_DSC like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86467, 334, 'a.quality_id', '����', 4, 1, '', 3, 'select b.QUALITY_ID from jas_bd_quality b where b.QUALITY_DSC like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86468, 334, 'a.orna_dsc', '��Ʒ����', 1, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86469, 334, 'a.createid', '������', 4, 1, '', 5, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86470, 334, 'a.createdate', '����ʱ��', 5, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86471, 334, 'a.updateid', '�޸���', 4, 1, '', 7, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86472, 334, 'a.updatedate', '�޸�ʱ��', 5, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86474, 336, 'a.STATUS', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''�����''},{value:''5'',text:''������''},{value:''8'',text:''�ر�''}]', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86475, 336, 'a.BILLNO', '���ݱ��', 1, 1, '', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86476, 336, 'a.ORG_ID', '��֯', 1, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86477, 336, 'a.UPDATE_REASON', '�޸�ԭ��', 1, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86478, 336, 'a.CREATE_ID', '������', 4, 1, '', 5, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86479, 336, 'a.CREATE_DATE', '����ʱ��', 5, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86480, 336, 'a.update_id', '�޸���', 4, 1, '', 7, 'select b.empid from jas_bd_emp b where b.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86481, 336, 'a.update_date', '�޸�ʱ��', 5, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86482, 337, 'a.STATUS', '״̬', 1, 3, '[{value:''1'',text:''����''},{value:''4'',text:''������''},{value:''5'',text:''�������''},{value:''8'',text:''�ر�''}]', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86483, 337, 'a.BILLNO', '���ݱ��', 1, 1, '', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86484, 337, 'a.VENDOR_ID', '��Ӧ��', 4, 1, '', 2, 'select a.custid from jas_bd_cust a where a.custname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86485, 337, 'a.MAINTAIN_TYPE', '����', 1, 5, 'maintaintype', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86486, 337, 'a.UNIT_ID', '��λ', 4, 1, '', 4, 'select a.unitid from jas_bd_unit a where a.unitname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86487, 337, 'a.NUM', '����', 3, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86488, 337, 'a.MATERIAL_TYPE', 'ԭ������', 1, 5, 'materialtype', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86489, 337, 'a.AMOUNT', '���', 3, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86490, 337, 'a.CREATE_ID', '������', 4, 1, '', 8, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86491, 337, 'a.CREATE_DATE', '����ʱ��', 5, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86492, 337, 'a.UPDATE_ID', '�޸���', 4, 1, '', 10, 'select ');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86493, 337, 'a.UPDATE_DATE', '�޸�ʱ��', 5, 1, '', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86498, 338, 'a.item_class_id', '����', 4, 1, '', 0, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86499, 338, 'a.orna_class_id', '����', 4, 1, '', 1, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86500, 338, 'a.START_WEIGHT', '��ʯ����ʼ', 3, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86501, 338, 'a.END_WEIGHT', '��ʯ����ֹ', 3, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86502, 338, 'a.CREATEUSERID', '������', 4, 1, '', 4, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86503, 338, 'a.createdate', '�޸�ʱ��', 5, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86504, 338, 'a.updateuserid', '�޸���', 4, 1, '', 6, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86505, 338, 'a.updatedate', '�޸�ʱ��', 5, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86506, 339, 'a.basic_price', '������', 3, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86507, 339, 'a.CREATEUSERID', '������', 4, 1, '', 1, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86508, 339, 'a.createdate', '����ʱ��', 5, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86509, 339, 'a.updateuserid', '�޸���', 4, 1, '', 3, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86510, 339, 'a.updatedate', '�޸�ʱ��', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86511, 340, 'a.item_class_id', '����', 4, 1, '', 0, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86512, 340, 'a.START_PRICE', '��ʼ�۸�', 3, 1, '', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86513, 340, 'a.END_PRICE', '��ֹ�۸�', 3, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86514, 340, 'a.big_PRICE', '��۸�', 1, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86515, 340, 'a.SMALL_PRICE', 'С�۸�', 1, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86516, 340, 'a.DECIMAL_NUM', 'ȡֵ��λ��', 1, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86517, 340, 'a.createdate', '����ʱ��', 5, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86518, 340, 'a.CREATEUSERID', '������', 4, 1, '', 7, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86519, 340, 'a.updatedate', '�޸�ʱ��', 5, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (86520, 340, 'a.updateuserid', '�޸���', 4, 1, '', 9, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

