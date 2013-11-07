--组织  库存  在途  已分配  最小量  最大量
--如果调入组织是专卖店，则直接取基础价，
--如果调入组织是专柜类型， 则根据饰品调拨前的网点金额 属于 该类别（大类、小类、分析范围）对应的基础价区间范围，
--则调拨单时根据调入组织的网点金额取商场店价格；如果不在范围内的则取饰品基础价为网点金额。
select (case
         when aaa.dispatch_flag = 1 then
          (aaa.stock_num + aaa.onway_num + aaa. dispatch_num) / (case
            when (aaa.stock_num + aaa.onway_num + aaa. dispatch_num) <
                 aaa.disp_min then
             aaa.disp_min
            else
             aaa.disp_max
          end)
         else
          0
       end) as coef,
       aaa.org_id,
       aaa.dispatch_flag,
       aaa.disp_min,
       aaa.disp_max,
       aaa.stock_num,
       aaa.onway_num,
       aaa.dispatch_num,
       aaa.org_name
  from (select aa.org_id,
               aa.org_name,
               --aa.new_pos_money,
               --aa.style_flag,
               --aa.disp_min as style_min,
               --aa.disp_max as style_max,
               --nvl2(bb.billid, 1, 0) as price_flag,
               --bb.disp_min as price_min,
               --bb.disp_max as price_max,
               case
                 when aa.style_flag = 1 or nvl2(bb.billid, 1, 0) = 1 then
                  1
                 else
                  0
               end as dispatch_flag,
               case
                 when aa.style_flag = 1 then
                  aa.disp_min
                 when nvl2(bb.billid, 1, 0) = 1 then
                  bb.disp_min
                 else
                  0
               end as disp_min,
               case
                 when aa.style_flag = 1 then
                  aa.disp_max
                 when nvl2(bb.billid, 1, 0) = 1 then
                  bb.disp_max
                 else
                  0
               end as disp_max,
               (select nvl(sum(cc.stock_num), 0)
                  from jat_pl_stock_statistics_grade cc
                 where cc.org_id = aa.org_id
                   and cc.item_class_id = 146
                   and cc.orna_class_id = 52
                   and cc.analysis_id = 1176
                   and cc.style_id = 7591) as stock_num,
               (select nvl(sum(dd.onway_num), 0)
                  from jat_pl_onway_statistics_grade dd
                 where dd.org_id = aa.org_id
                   and dd.item_class_id = 146
                   and dd.orna_class_id = 52
                   and dd.analysis_id = 1176
                   and dd.style_id = 7591) as onway_num,
               (select count(1)
                  from jat_pl_dispatch_orna_log ee
                 where ee.org_id = aa.org_id
                   and ee.item_class_id = 146
                   and ee.orna_class_id = 52
                   and ee.analysis_id = 1176
                   and ee.style_id = 7591
                   and ee.status = 2
                   and ee.src_bill_code = 'SD') as dispatch_num
          from (select a.org_id,
                       a.org_name,
                       nvl2(b.billid, 1, 0) as style_flag,
                       b.disp_min,
                       b.disp_max,
                       (case
                         when nvl((select d.org_type
                                    from jat_pl_basic_special_diam_org d
                                   where d.org_id = a.org_id),
                                  1) = 1 then
                          1234
                         else
                          nvl((select e.store_price
                                from jat_pl_basic_special_diam e
                               where e.item_class_id = 146
                                 and e.orna_class_id = 52
                                 and e.analysis_id = 1176
                                 and e.start_basic_price <= 1111
                                 and e.end_basic_price >= 1111),
                              1234)
                       end) as new_pos_money
                  from jas_sm_org a
                  left join jat_pl_basic_dispatch_style b
                    on b.org_id = a.org_id
                   and b.item_class_id = 146
                   and b.orna_class_id = 52
                   and b.analysis_id = 1176
                   and b.style_id = 7591
                 where a.org_type = 1
                   and not exists (select 1
                          from jat_sys_dict_item c
                         where c.entry_code = 'hqorgs'
                           and a.org_id = c.item_key)) aa
          left join jat_pl_basic_dispatch_price bb
            on bb.org_id = aa.org_id
           and bb.item_class_id = 146
           and bb.orna_class_id = 52
           and bb.analysis_id = 1176
           and bb.price_start <= aa.new_pos_money
           and bb.price_end >= aa.new_pos_money) aaa
 order by 1;
/*
select * from jat_pl_stock_statistics_grade;
select * from jat_pl_basic_dispatch_style a;
select * from jat_pl_basic_dispatch_price a;
*/
