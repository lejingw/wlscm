insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186420, 353, 'a.create_id', '������', 4, 1, '', 1, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186421, 353, 'a.create_date', '��������', 5, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186422, 353, 'a.update_id', '�޸���', 4, 1, '', 3, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186423, 353, 'a.update_date', '�޸�����', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186556, 352, 'a.orgtype_name', '��֯�������', 1, 1, '', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186557, 351, 'b.region_name', '����', 1, 1, '', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186558, 351, 'a.sale_dis_rate', '�������̻�������', 3, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186559, 351, 'a.sale_turn_rate', '��������ת������', 3, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186560, 351, 'a.unsale_dis_rate', '�ǳ������̻�������', 3, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186561, 351, 'a.unsale_turn_rate', '�ǳ�������ת������', 3, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186562, 350, 'a.start_date', '��ʼ����', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186563, 350, 'a.end_date', '��ֹ����', 5, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186564, 350, 'a.org_id', '��֯', 4, 1, '', 6, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186565, 350, 'a.item_class_id', '����', 4, 1, '', 7, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186566, 350, 'a.orna_class_id', 'С��', 4, 1, '', 8, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186567, 350, 'b.summarydescription', '������Χ', 1, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186568, 350, 'a.style_item_class_id', '��ʽ����', 4, 1, '', 10, 'select si.ITEM_CLASS_ID from jas_bd_style_item_class si where si.ITEM_CLASS_NAME like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186569, 350, 'a.turnover_num', '��ת��', 2, 1, '', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186570, 349, 'a.org_id', '��֯', 4, 1, '', 1, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186576, 348, 'a.item_class_id', '����', 4, 1, '', 1, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186577, 348, 'a.orna_class_id', 'С��', 4, 1, '', 2, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186578, 348, 'b.summarydescription', '������Χ', 1, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186579, 348, 'a.color_grade_id', 'ɫ��', 1, 5, 'diacolorgrade', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186580, 348, 'a.clean_id', '����', 1, 5, 'diaclean', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186584, 347, 'a.item_class_id', '����', 4, 1, '', 1, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186582, 348, 'a.rate', '����(%)', 3, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186585, 347, 'a.orna_class_id', 'С��', 1, 1, '', 2, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186586, 347, 'a.style_item_class_id', '��ʽ����', 1, 1, '', 3, 'select si.ITEM_CLASS_ID from jas_bd_style_item_class si where si.ITEM_CLASS_NAME like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186587, 347, 'e.wear_name', '�������', 1, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186588, 347, 'b.size_name', '�ߴ���', 1, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186589, 347, 'c.size_name', '�ߴ�ֹ', 1, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186571, 349, 'a.item_class_id', '����', 4, 1, '', 2, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186572, 349, 'a.orna_class_id', 'С��', 4, 1, '', 3, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186573, 349, 'b.summarydescription', '������Χ', 1, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186574, 349, 'a.style_item_class_id', '��ʽ����', 4, 1, '', 5, 'select si.ITEM_CLASS_ID from jas_bd_style_item_class si where si.ITEM_CLASS_NAME like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186575, 349, 'a.distribute_num', '�̻���', 2, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186590, 347, 'd.size_name', '�ߴ�����', 1, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186591, 347, 'a.rate', '����(%)', 3, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186419, 353, 'a.orgtype_name', '��֯�������', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186512, 342, 'a.update_date', '�޸�����', 5, 1, '', 15, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186513, 342, 'a.update_id', '�޸���', 4, 1, '', 14, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186514, 342, 'a.create_date', '��������', 5, 1, '', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186515, 342, 'a.create_id', '������', 4, 1, '', 12, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186516, 343, 'a.update_date', '�޸�����', 5, 1, '', 15, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186517, 343, 'a.update_id', '�޸���', 4, 1, '', 14, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186518, 343, 'a.create_date', '��������', 5, 1, '', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186519, 343, 'a.create_id', '������', 4, 1, '', 12, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186520, 344, 'a.update_date', '�޸�����', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186521, 344, 'a.update_id', '�޸���', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186522, 344, 'a.create_date', '��������', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186523, 344, 'a.create_id', '������', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186524, 345, 'a.update_date', '�޸�����', 5, 1, '', 19, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186525, 345, 'a.update_id', '�޸���', 4, 1, '', 18, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186526, 345, 'a.create_date', '��������', 5, 1, '', 17, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186527, 345, 'a.create_id', '������', 4, 1, '', 16, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186528, 346, 'a.update_date', '�޸�����', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186529, 346, 'a.update_id', '�޸���', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186530, 346, 'a.create_date', '��������', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186531, 346, 'a.create_id', '������', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186532, 347, 'a.update_date', '�޸�����', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186533, 347, 'a.update_id', '�޸���', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186534, 347, 'a.create_date', '��������', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186535, 347, 'a.create_id', '������', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186536, 348, 'a.update_date', '�޸�����', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186537, 348, 'a.update_id', '�޸���', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186538, 348, 'a.create_date', '��������', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186539, 348, 'a.create_id', '������', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186540, 349, 'a.update_date', '�޸�����', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186541, 349, 'a.update_id', '�޸���', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186542, 349, 'a.create_date', '��������', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186543, 349, 'a.create_id', '������', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186544, 350, 'a.update_date', '�޸�����', 5, 1, '', 15, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186545, 350, 'a.update_id', '�޸���', 4, 1, '', 14, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186546, 350, 'a.create_date', '��������', 5, 1, '', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186547, 350, 'a.create_id', '������', 4, 1, '', 12, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186548, 351, 'a.update_date', '�޸�����', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186549, 351, 'a.update_id', '�޸���', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186550, 351, 'a.create_date', '��������', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186551, 351, 'a.create_id', '������', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186552, 352, 'a.update_date', '�޸�����', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186553, 352, 'a.update_id', '�޸���', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186554, 352, 'a.create_date', '��������', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186555, 352, 'a.create_id', '������', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186583, 348, '(select aa.item_value from jat_sys_dict_item aa where aa.entry_code = ''gradelevel'' and aa.item_key = a.color_grade_grade_id) || (select bb.item_value from jat_sys_dict_item bb where bb.entry_code = ''gradelevel'' and bb.item_key = a.clean_grade_id)', 'Ʒ��', 1, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186592, 346, 'a.item_class_id', '����', 4, 1, '', 1, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186593, 346, 'a.orna_class_id', 'С��', 4, 1, '', 2, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186594, 346, 'a.style_item_class_id', '��ʽ����', 1, 1, '', 3, 'select si.ITEM_CLASS_ID from jas_bd_style_item_class si where si.ITEM_CLASS_NAME like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186595, 346, 'b.size_name', '�ߴ���', 1, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186596, 346, 'c.size_name', '�ߴ�ֹ', 1, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186597, 346, 'd.size_name', '�ߴ�����', 1, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186598, 346, 'a.rate', '����(%)', 3, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186625, 343, 'a.status', '״̬', 1, 3, '[{value:"1", text:"����"}, {value:"31", text:"�������ܵ�"}, {value:"32", text:"�����ɲɹ���"}, {value:"8", text:"�ر�"}]', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186626, 344, 'a.status', '״̬', 1, 3, '[{value:"1", text:"����"}, {value:"31", text:"�������ܵ�"}, {value:"32", text:"�����ɲɹ���"}, {value:"8", text:"�ر�"}]', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186627, 343, 'a.bill_no', '�ܵ����', 1, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186628, 343, 'b.region_name', '����', 1, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186629, 343, 'c.cycle_type_code', '��������', 1, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186630, 343, '(select wm_concat(aa.article_type_dsc) from jas_bd_articletype aa where instr(a.article_type_ids, aa.article_type_id) > 0)', '��Ʒ���', 1, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186631, 343, 'a.pur_date_start', '���￪ʼ����', 1, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186632, 343, 'a.pur_date_end', '�����������', 1, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186633, 343, 'a.sale_date_start', '���ۿ�ʼ����', 1, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186634, 343, 'a.sale_date_end', '���۽�������', 1, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186635, 343, 'a.pur_arrive_date_end', '��󵽻�����', 1, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186636, 343, 'a.org_id', '��֯', 4, 1, '', 11, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186637, 344, 'a.bill_no', '�ܵ����', 1, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186638, 344, 'b.region_name', '����', 1, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186639, 344, 'c.cycle_type_code', '��������', 1, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186640, 344, '(select wm_concat(aa.article_type_dsc) from jas_bd_articletype aa where instr(a.article_type_ids, aa.article_type_id) > 0)', '��Ʒ���', 1, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186642, 344, 'a.dotype', 'ִ�й���', 1, 5, 'dispatchdotype', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186643, 344, '(select case when count(1) > 0 then 1 else 0 end from jat_pl_dispatch_condition d where a.headid = d.gather_head_id and d.src_bill_code = ''PU'')', '�����', 1, 5, 'yesorno', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186599, 345, 'a.orna_code', '��Ʒ����', 1, 1, '', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186600, 345, 'b.orna_dsc', '��Ʒ����', 1, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186601, 345, 'b.state', '״̬', 1, 3, '[{value:"900", text:"��Ч"}, {value:"901", text:"����"}, {value:"902", text:"��;"}]', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186602, 345, 'b.org_id', '��֯', 4, 1, '', 4, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186603, 345, 'b.item_class_id', '����', 4, 1, '', 5, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186604, 345, 'b.orna_class_id', 'С��', 4, 1, '', 6, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186605, 345, 'c.summarydescription', '������Χ', 1, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186606, 345, 'd.stylename', '��ʽ', 1, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186607, 345, 'b.main_color_grade_id', 'ɫ��', 1, 5, 'diacolorgrade', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186608, 345, 'b.clean_id', '����', 1, 5, 'diaclean', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186609, 345, '(select aa.item_value from jat_sys_dict_item aa where aa.entry_code = ''gradelevel'' and aa.item_key = b.color_grade_grade_id) || (select bb.item_value from jat_sys_dict_item bb where bb.entry_code = ''gradelevel'' and bb.item_key = b.clean_grade_id)', 'Ʒ��', 1, 1, '', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186610, 345, 'e.size_name', '�ߴ�', 1, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186611, 345, 'b.quality_id', '��Ʒ����', 4, 1, '', 13, 'select a.quality_id from jas_bd_quality a where a.quality_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186612, 345, 'b.bracketcolor_id', '�м���ɫ', 1, 5, '', 14, 'bracketcolor');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186613, 345, 'b.sale_unit_id', '������λ', 4, 1, '', 15, 'select a.unitid from jas_bd_unit a where a.unitname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186614, 342, 'a.status', '״̬', 1, 3, '[{value:"1", text:"����"}, {value:"31", text:"�������ܵ�"}, {value:"32", text:"�����ɲɹ���"}, {value:"8", text:"�ر�"}]', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186615, 342, 'a.bill_no', '�ܵ����', 1, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186616, 342, 'b.region_name', '����', 1, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186617, 342, 'c.cycle_type_code', '��������', 1, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186618, 342, '(select wm_concat(aa.article_type_dsc) from jas_bd_articletype aa where instr(a.article_type_ids, aa.article_type_id) > 0)', '��Ʒ���', 1, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186619, 342, 'a.pur_date_start', '���￪ʼ����', 1, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186620, 342, 'a.pur_date_end', '�����������', 1, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186621, 342, 'a.sale_date_start', '���ۿ�ʼ����', 1, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186622, 342, 'a.sale_date_end', '���۽�������', 1, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186623, 342, 'a.pur_arrive_date_end', '��󵽻�����', 1, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186624, 342, 'a.org_id', '��֯', 4, 1, '', 11, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

