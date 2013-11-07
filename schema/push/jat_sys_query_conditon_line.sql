insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186420, 353, 'a.create_id', '创建人', 4, 1, '', 1, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186421, 353, 'a.create_date', '创建日期', 5, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186422, 353, 'a.update_id', '修改人', 4, 1, '', 3, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186423, 353, 'a.update_date', '修改日期', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186556, 352, 'a.orgtype_name', '组织类别名称', 1, 1, '', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186557, 351, 'b.region_name', '区域', 1, 1, '', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186558, 351, 'a.sale_dis_rate', '畅销款铺货量比例', 3, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186559, 351, 'a.sale_turn_rate', '畅销款周转量比例', 3, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186560, 351, 'a.unsale_dis_rate', '非畅销款铺货量比例', 3, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186561, 351, 'a.unsale_turn_rate', '非畅销款周转量比例', 3, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186562, 350, 'a.start_date', '起始日期', 5, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186563, 350, 'a.end_date', '截止日期', 5, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186564, 350, 'a.org_id', '组织', 4, 1, '', 6, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186565, 350, 'a.item_class_id', '大类', 4, 1, '', 7, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186566, 350, 'a.orna_class_id', '小类', 4, 1, '', 8, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186567, 350, 'b.summarydescription', '分析范围', 1, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186568, 350, 'a.style_item_class_id', '款式大类', 4, 1, '', 10, 'select si.ITEM_CLASS_ID from jas_bd_style_item_class si where si.ITEM_CLASS_NAME like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186569, 350, 'a.turnover_num', '周转量', 2, 1, '', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186570, 349, 'a.org_id', '组织', 4, 1, '', 1, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186576, 348, 'a.item_class_id', '大类', 4, 1, '', 1, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186577, 348, 'a.orna_class_id', '小类', 4, 1, '', 2, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186578, 348, 'b.summarydescription', '分析范围', 1, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186579, 348, 'a.color_grade_id', '色级', 1, 5, 'diacolorgrade', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186580, 348, 'a.clean_id', '净度', 1, 5, 'diaclean', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186584, 347, 'a.item_class_id', '大类', 4, 1, '', 1, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186582, 348, 'a.rate', '比例(%)', 3, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186585, 347, 'a.orna_class_id', '小类', 1, 1, '', 2, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186586, 347, 'a.style_item_class_id', '款式大类', 1, 1, '', 3, 'select si.ITEM_CLASS_ID from jas_bd_style_item_class si where si.ITEM_CLASS_NAME like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186587, 347, 'e.wear_name', '佩戴对象', 1, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186588, 347, 'b.size_name', '尺寸起', 1, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186589, 347, 'c.size_name', '尺寸止', 1, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186571, 349, 'a.item_class_id', '大类', 4, 1, '', 2, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186572, 349, 'a.orna_class_id', '小类', 4, 1, '', 3, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186573, 349, 'b.summarydescription', '分析范围', 1, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186574, 349, 'a.style_item_class_id', '款式大类', 4, 1, '', 5, 'select si.ITEM_CLASS_ID from jas_bd_style_item_class si where si.ITEM_CLASS_NAME like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186575, 349, 'a.distribute_num', '铺货量', 2, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186590, 347, 'd.size_name', '尺寸名称', 1, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186591, 347, 'a.rate', '比例(%)', 3, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186419, 353, 'a.orgtype_name', '组织类别名称', 1, 1, '', 0, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186512, 342, 'a.update_date', '修改日期', 5, 1, '', 15, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186513, 342, 'a.update_id', '修改人', 4, 1, '', 14, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186514, 342, 'a.create_date', '创建日期', 5, 1, '', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186515, 342, 'a.create_id', '创建人', 4, 1, '', 12, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186516, 343, 'a.update_date', '修改日期', 5, 1, '', 15, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186517, 343, 'a.update_id', '修改人', 4, 1, '', 14, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186518, 343, 'a.create_date', '创建日期', 5, 1, '', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186519, 343, 'a.create_id', '创建人', 4, 1, '', 12, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186520, 344, 'a.update_date', '修改日期', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186521, 344, 'a.update_id', '修改人', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186522, 344, 'a.create_date', '创建日期', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186523, 344, 'a.create_id', '创建人', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186524, 345, 'a.update_date', '修改日期', 5, 1, '', 19, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186525, 345, 'a.update_id', '修改人', 4, 1, '', 18, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186526, 345, 'a.create_date', '创建日期', 5, 1, '', 17, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186527, 345, 'a.create_id', '创建人', 4, 1, '', 16, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186528, 346, 'a.update_date', '修改日期', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186529, 346, 'a.update_id', '修改人', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186530, 346, 'a.create_date', '创建日期', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186531, 346, 'a.create_id', '创建人', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186532, 347, 'a.update_date', '修改日期', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186533, 347, 'a.update_id', '修改人', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186534, 347, 'a.create_date', '创建日期', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186535, 347, 'a.create_id', '创建人', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186536, 348, 'a.update_date', '修改日期', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186537, 348, 'a.update_id', '修改人', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186538, 348, 'a.create_date', '创建日期', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186539, 348, 'a.create_id', '创建人', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186540, 349, 'a.update_date', '修改日期', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186541, 349, 'a.update_id', '修改人', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186542, 349, 'a.create_date', '创建日期', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186543, 349, 'a.create_id', '创建人', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186544, 350, 'a.update_date', '修改日期', 5, 1, '', 15, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186545, 350, 'a.update_id', '修改人', 4, 1, '', 14, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186546, 350, 'a.create_date', '创建日期', 5, 1, '', 13, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186547, 350, 'a.create_id', '创建人', 4, 1, '', 12, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186548, 351, 'a.update_date', '修改日期', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186549, 351, 'a.update_id', '修改人', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186550, 351, 'a.create_date', '创建日期', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186551, 351, 'a.create_id', '创建人', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186552, 352, 'a.update_date', '修改日期', 5, 1, '', 14, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186553, 352, 'a.update_id', '修改人', 4, 1, '', 13, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186554, 352, 'a.create_date', '创建日期', 5, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186555, 352, 'a.create_id', '创建人', 4, 1, '', 11, 'select a.empid from jas_bd_emp a where a.empname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186583, 348, '(select aa.item_value from jat_sys_dict_item aa where aa.entry_code = ''gradelevel'' and aa.item_key = a.color_grade_grade_id) || (select bb.item_value from jat_sys_dict_item bb where bb.entry_code = ''gradelevel'' and bb.item_key = a.clean_grade_id)', '品质', 1, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186592, 346, 'a.item_class_id', '大类', 4, 1, '', 1, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186593, 346, 'a.orna_class_id', '小类', 4, 1, '', 2, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186594, 346, 'a.style_item_class_id', '款式大类', 1, 1, '', 3, 'select si.ITEM_CLASS_ID from jas_bd_style_item_class si where si.ITEM_CLASS_NAME like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186595, 346, 'b.size_name', '尺寸起', 1, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186596, 346, 'c.size_name', '尺寸止', 1, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186597, 346, 'd.size_name', '尺寸名称', 1, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186598, 346, 'a.rate', '比例(%)', 3, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186625, 343, 'a.status', '状态', 1, 3, '[{value:"1", text:"保存"}, {value:"31", text:"已生成总单"}, {value:"32", text:"已生成采购单"}, {value:"8", text:"关闭"}]', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186626, 344, 'a.status', '状态', 1, 3, '[{value:"1", text:"保存"}, {value:"31", text:"已生成总单"}, {value:"32", text:"已生成采购单"}, {value:"8", text:"关闭"}]', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186627, 343, 'a.bill_no', '总单编号', 1, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186628, 343, 'b.region_name', '区域', 1, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186629, 343, 'c.cycle_type_code', '周期类型', 1, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186630, 343, '(select wm_concat(aa.article_type_dsc) from jas_bd_articletype aa where instr(a.article_type_ids, aa.article_type_id) > 0)', '商品类别', 1, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186631, 343, 'a.pur_date_start', '购物开始日期', 1, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186632, 343, 'a.pur_date_end', '购物结束日期', 1, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186633, 343, 'a.sale_date_start', '销售开始日期', 1, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186634, 343, 'a.sale_date_end', '销售结束日期', 1, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186635, 343, 'a.pur_arrive_date_end', '最后到货日期', 1, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186636, 343, 'a.org_id', '组织', 4, 1, '', 11, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186637, 344, 'a.bill_no', '总单编号', 1, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186638, 344, 'b.region_name', '区域', 1, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186639, 344, 'c.cycle_type_code', '周期类型', 1, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186640, 344, '(select wm_concat(aa.article_type_dsc) from jas_bd_articletype aa where instr(a.article_type_ids, aa.article_type_id) > 0)', '商品类别', 1, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186642, 344, 'a.dotype', '执行功能', 1, 5, 'dispatchdotype', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186643, 344, '(select case when count(1) > 0 then 1 else 0 end from jat_pl_dispatch_condition d where a.headid = d.gather_head_id and d.src_bill_code = ''PU'')', '已配货', 1, 5, 'yesorno', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186599, 345, 'a.orna_code', '饰品编码', 1, 1, '', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186600, 345, 'b.orna_dsc', '饰品名称', 1, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186601, 345, 'b.state', '状态', 1, 3, '[{value:"900", text:"有效"}, {value:"901", text:"保留"}, {value:"902", text:"在途"}]', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186602, 345, 'b.org_id', '组织', 4, 1, '', 4, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186603, 345, 'b.item_class_id', '大类', 4, 1, '', 5, 'select a.item_class_id from jas_bd_item_class a where a.item_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186604, 345, 'b.orna_class_id', '小类', 4, 1, '', 6, 'select a.orna_class_id from jas_bd_orna_class a where a.orna_class_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186605, 345, 'c.summarydescription', '分析范围', 1, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186606, 345, 'd.stylename', '款式', 1, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186607, 345, 'b.main_color_grade_id', '色级', 1, 5, 'diacolorgrade', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186608, 345, 'b.clean_id', '净度', 1, 5, 'diaclean', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186609, 345, '(select aa.item_value from jat_sys_dict_item aa where aa.entry_code = ''gradelevel'' and aa.item_key = b.color_grade_grade_id) || (select bb.item_value from jat_sys_dict_item bb where bb.entry_code = ''gradelevel'' and bb.item_key = b.clean_grade_id)', '品质', 1, 1, '', 11, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186610, 345, 'e.size_name', '尺寸', 1, 1, '', 12, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186611, 345, 'b.quality_id', '商品材质', 4, 1, '', 13, 'select a.quality_id from jas_bd_quality a where a.quality_dsc like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186612, 345, 'b.bracketcolor_id', '托架颜色', 1, 5, '', 14, 'bracketcolor');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186613, 345, 'b.sale_unit_id', '计量单位', 4, 1, '', 15, 'select a.unitid from jas_bd_unit a where a.unitname like ''%$%''');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186614, 342, 'a.status', '状态', 1, 3, '[{value:"1", text:"保存"}, {value:"31", text:"已生成总单"}, {value:"32", text:"已生成采购单"}, {value:"8", text:"关闭"}]', 1, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186615, 342, 'a.bill_no', '总单编号', 1, 1, '', 2, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186616, 342, 'b.region_name', '区域', 1, 1, '', 3, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186617, 342, 'c.cycle_type_code', '周期类型', 1, 1, '', 4, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186618, 342, '(select wm_concat(aa.article_type_dsc) from jas_bd_articletype aa where instr(a.article_type_ids, aa.article_type_id) > 0)', '商品类别', 1, 1, '', 5, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186619, 342, 'a.pur_date_start', '购物开始日期', 1, 1, '', 6, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186620, 342, 'a.pur_date_end', '购物结束日期', 1, 1, '', 7, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186621, 342, 'a.sale_date_start', '销售开始日期', 1, 1, '', 8, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186622, 342, 'a.sale_date_end', '销售结束日期', 1, 1, '', 9, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186623, 342, 'a.pur_arrive_date_end', '最后到货日期', 1, 1, '', 10, '');

insert into jat_sys_query_conditon_line (LINEID, HEADID, FIELD_NAME, FIELD_LABEL, FIELD_TYPE, INPUT_TYPE, INPUT_VALUE, SORT, TO_ID_SQL)
values (186624, 342, 'a.org_id', '组织', 4, 1, '', 11, 'select a.org_id from jas_sm_org a where a.org_name like ''%$%'' and a.org_type = 1');

