----------------------------------------------
-- Export file for user MCLON_KC            --
-- Created by liulh on 2012-09-24, 10:22:02 --
----------------------------------------------

spool view_bak20120924.log

prompt
prompt Creating view JK_BZYSPZ_CAIBAODAN
prompt =================================
prompt
create or replace view jk_bzyspz_caibaodan as
select --拆包单
       ch.billid as ID,
       ch.billno as bianhao,
       ch.dodate as yewufssj,
       ch.update_date as zuihougxsj,
       ch.org_id as caiwuzzid,
       0 as zuofeibz --作废标志
  from jat_proc_package_head ch
 where ch.status=8
/

prompt
prompt Creating view JK_BZYSPZ_CAIBAODCKMX
prompt ===================================
prompt
create or replace view jk_bzyspz_caibaodckmx as
select --拆包单出库明细
       ch.billid as chaibaodid,
       cl.orna_code as shangpinbh,
       cl.unit_id as jiliangdwid,
       decode(cl.item_class_id,
              156,
              cl.grains,
              173,
              cl.grains,
              178,
              cl.grains,
              171,
              cl.grains,
              cl.now_qty) as shuliang
  from jat_proc_package_head ch
 inner join jat_proc_package_oldline cl on ch.billid = cl.billid
 where ch.status=8
/

prompt
prompt Creating view JK_BZYSPZ_CAIBAODRKMX
prompt ===================================
prompt
create or replace view jk_bzyspz_caibaodrkmx as
select --拆包单入库明细
       ph.billid as chaibaodid,
       mi.orna_code as shangpinbh,
       mi.unit_id as jiliangdwid,
       decode(mi.item_class_id,
              156,
              mi.TOTAL_NUM,
              173,
              mi.TOTAL_NUM,
              178,
              mi.TOTAL_NUM,
              171,
              mi.TOTAL_NUM,
              mi.quantity) as shuliang,
       mi.total_cost as zangucb,
       mi.item_class_id as daleiid,
       mi.orna_class_id as xiaoleiid,
       mi.style_id as kuanshiid,
       mi.ARTICLE_TYPE_ID as shangpinlbid,
       mi.quality_id as chengseid,
       mi.old_orna_code as guanliandxspbh
  from jat_proc_package_head ch
 inner join jat_pur_handover_head ph on ch.billid = ph.source_id
                                    and ph.source_type = 'CF'
 inner join jat_mater_iniv mi on ph.billid = mi.src_bill_id
                             and ph.org_id = mi.iniv_org
 where ch.status=8
/

prompt
prompt Creating view JK_BZYSPZ_CAIGOURKBHGPMX
prompt ======================================
prompt
create or replace view jk_bzyspz_caigourkbhgpmx as
select
--核算--采购入库单--采购入库单不合格品明细
       ph.billid as caigourkdid,
       pl.item_class_id as daleiid,
       decode(pl.unit_no, 'K', 609, 'J', 612, 'CT', 610) as jiliangdwid,
       pl.no_num_now as shuliang,
       pl.hand_money - pl.yes_money as zangucb
  from jat_pur_handover_head ph
 inner join jat_pur_handover_line pl on ph.billid = pl.billid
 where nvl(ph.dotype, 0) = 0
   and nvl(ph.is_psale, 0) = 0
   and ph.source_type = 'CM'
   and ph.status=8
/

prompt
prompt Creating view JK_BZYSPZ_CAIGOURKD
prompt =================================
prompt
create or replace view jk_bzyspz_caigourkd as
select distinct
--核算--采购入库单--头
 ph.billid as ID,
 ph.billno ||
 to_char(to_date(ph.update_date, 'yyyy-mm-dd hh24:mi:ss'), 'yyyymmdd') as bianhao,
 ph.billno as jiaojiedbh,
 to_date(ph.dodate, 'yyyy-mm-dd') as yewufssj,
 to_date(mi.updatedate, 'yyyy-mm-dd hh24:mi:ss') as yewusxsj,
 to_date(mi.updatedate, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj,

 ph.org_id    as caiwuzzid,
 ph.verdor_id as gongyingsid,
 0            as zuofeibz --作废标志
  from jat_pur_handover_head ph
 inner join jat_mater_iniv mi on ph.billid = mi.src_bill_id
                             and ph.org_id = mi.iniv_org
 where nvl(ph.dotype, 0) = 0
   and nvl(ph.is_psale, 0) = 0
   and ph.source_type = 'CM'
   and mi.state = 11
/

prompt
prompt Creating view JK_BZYSPZ_CAIGOURKDMX
prompt ===================================
prompt
create or replace view jk_bzyspz_caigourkdmx as
select
----核算--采购入库单--采购入库单明细
 ph.billid as caigourkdid,
       mi.orna_code as shangpinbh,
       mi.unit_id as jiliangdwid,
       decode(mi.item_class_id,
              156,
              mi.TOTAL_NUM,
              173,
              mi.TOTAL_NUM,
              178,
              mi.TOTAL_NUM,
              171,
              mi.TOTAL_NUM,
              mi.quantity) as shuliang,
       mi.total_cost/1.17 as zangucb,
       mi.item_class_id as daleiid,
       mi.orna_class_id as xiaoleiid,
       mi.style_id as kuanshiid,
       mi.ARTICLE_TYPE_ID as shangpinlbid,
       mi.quality_id as chengseid,
       to_date(mi.updatedate, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj
  from jat_pur_handover_head ph

 inner join jat_mater_iniv mi on ph.billid = mi.src_bill_id
                             and ph.org_id = mi.iniv_org

 where nvl(ph.dotype, 0) = 0
   and nvl(ph.is_psale, 0) = 0
   and ph.source_type = 'CM'
   --and ph.status=8
   and mi.state=11
/

prompt
prompt Creating view JK_BZYSPZ_CAIGOUSP
prompt ================================
prompt
create or replace view jk_bzyspz_caigousp as
select
--采购收票
       distinct
       ph.billid as ID,
       ph.billno as bianhao,
       to_date(ph.dodate,'yyyy-mm-dd') as yewufssj,
       to_date(rl.create_date,'yyyy-mm-dd hh24:mi:ss')  as yewusxsj,
       to_date(ph.update_date,'yyyy-mm-dd hh24:mi:ss')  as zuihougxsj,
       ph.org_id as caiwuzzid,
       ph.verdor_id as gongyingsid,
       0 as zuofeibz, --作废标志
       rl.moneys as gehangzcb,
       rl.all_money as zengzhise
  from jat_pur_handover_head ph
 inner join jat_bill_receive_line rl on ph.billno = rl.hand_no
 where nvl(ph.dotype, 0) = 0
   and ph.source_type = 'CM'
   and ph.status=8
/

prompt
prompt Creating view JK_BZYSPZ_CAIGOUSPBHGPMX
prompt ======================================
prompt
create or replace view jk_bzyspz_caigouspbhgpmx as
select

--采购收票不合格品明细
 ph.billid as caigouspid,
 ph.billno as jiaojiedbh,
 1 as dikoulx, --本次不合格品
 pl.item_class_id as daleiid,
 decode(pl.unit_no, 'K', 609, 'J', 612, 'CT', 610) jiliangdwid,
 pl.no_num_now as shuliang,
 round(round(to_number(nvl(pl.hand_money, 0)) -
             to_number(nvl(pl.yes_money, 0)),
             0) / 1.17,
       2) as zangucb
  from jat_pur_handover_head ph
 inner join jat_pur_handover_line pl on ph.billid = pl.billid
 where nvl(ph.dotype, 0) = 0
   and ph.source_type = 'CM'
   and ph.status = 8
   and nvl(pl.no_num_now, 0) > 0
union all
select

 ph.billid as caigouspid,
 ph.billno as jiaojiedbh,
 2 as dikoulx, --上次不合格品
 pl.item_class_id as daleiid,
 decode(pl.unit_no, 'K', 609, 'J', 612, 'CT', 610) jiliangdwid,
 pl.no_num_last as shuliang,
 0 as zangucb
  from jat_pur_handover_head ph
 inner join jat_pur_handover_line pl on ph.billid = pl.billid
 where nvl(ph.dotype, 0) = 0
   and ph.source_type = 'CM'
   and ph.status = 8
   and nvl(pl.no_num_last, 0) > 0
/

prompt
prompt Creating view JK_BZYSPZ_CAIGOUSPGHZCB
prompt =====================================
prompt
create or replace view jk_bzyspz_caigouspghzcb as
select
--采购收票各行总成本
       ph.billid as caigouspid,
       ph.billno as jiaojiedbh,
       rl.item_class_id as daleiid,
       rl.moneys as zongchengben,
       rl.tax_money as zengzhise
  from jat_pur_handover_head ph
 inner join jat_bill_receive_line rl on ph.billno = rl.hand_no
 where nvl(ph.dotype, 0) = 0
   and ph.source_type = 'CM'
   and ph.status=8
/

prompt
prompt Creating view JK_BZYSPZ_CAIGOUSPJJDMX
prompt =====================================
prompt
create or replace view jk_bzyspz_caigouspjjdmx as
select
--采购收票交接单明细
       ph.billid as caigouspid,
       ph.billno as jiaojiedbh
  from jat_pur_handover_head ph
 inner join jat_bill_receive_line rl on ph.billno = rl.hand_no
 where nvl(ph.dotype, 0) = 0
   and ph.source_type = 'CM'
   and ph.status=8
/

prompt
prompt Creating view JK_BZYSPZ_CAIGOUSPSPMX
prompt ====================================
prompt
create or replace view jk_bzyspz_caigouspspmx as
select
--采购收票明细
       ph.billid as caigouspid,
       ph.billno as jiaojiedbh,
       mi.orna_code as shangpinbh
  from jat_pur_handover_head ph
 inner join jat_bill_receive_line rl on ph.billno = rl.hand_no
 inner join jat_mater_iniv mi on ph.billid=mi.src_bill_id
 where nvl(ph.dotype, 0) = 0
   and ph.source_type = 'CM'
   and ph.status=8
/

prompt
prompt Creating view JK_BZYSPZ_CAILIAOZHD
prompt ==================================
prompt
create or replace view jk_bzyspz_cailiaozhd as
select --材料转换单
 ch.billid as ID,
 ch.billno as bianhao,
 ph.billno as jiaojiedbh,
 to_date(ch.dodate, 'yyyy-mm-dd') as yewufssj,
 (select to_date(max(mt.create_date), 'yyyy-mm-dd hh24:mi:ss')
    from jat_mater_trans mt
   where ch.billno = mt.trans_source_no) as yewusxsj,
 to_date(ch.update_date, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj,
 ch.org_id as caiwuzzid,
 0 as zuofeibz --作废标志
  from jat_proc_change_head ch
 inner join jat_pur_handover_head ph on ch.billid = ph.source_id
                                    and ph.source_type = 'LT'
 inner join jat_mater_trans mt on ch.billno = mt.trans_source_no
 where ch.dotype = 1
   and ch.status = 8
/

prompt
prompt Creating view JK_BZYSPZ_CAILIAOZHDCKMX
prompt ======================================
prompt
create or replace view jk_bzyspz_cailiaozhdckmx as
select --材料转换单出库明细
       ch.billid as cailiaozhdid,
       cl.orna_code as shangpinbh,
       cl.unit_id as jiliangdwid,
       decode(cl.item_class_id,
              156,
              cl.grains,
              173,
              cl.grains,
              178,
              cl.grains,
              171,
              cl.grains,
              cl.now_qty) as shuliang
  from jat_proc_change_head ch
 inner join jat_proc_change_line cl on ch.billid = cl.billid
 where ch.dotype = 1
   and ch.status=8
/

prompt
prompt Creating view JK_BZYSPZ_CAILIAOZHDRKMX
prompt ======================================
prompt
create or replace view jk_bzyspz_cailiaozhdrkmx as
select --材料转换单入库明细
       ch.billid as cailiaozhdid,
       mi.orna_code as shangpinbh,
       mi.unit_id as jiliangdwid,
       decode(mi.item_class_id,
              156,
              mi.TOTAL_NUM,
              173,
              mi.TOTAL_NUM,
              178,
              mi.TOTAL_NUM,
              171,
              mi.TOTAL_NUM,
              mi.quantity) as shuliang,
       mi.total_cost as zangucb,
       mi.item_class_id as daleiid,
       mi.orna_class_id as xiaoleiid,
       mi.style_id as kuanshiid,
       mi.ARTICLE_TYPE_ID as shangpinlbid,
       mi.quality_id as chengseid,
       nvl(mi.old_orna_code,'N/A') as guanliandxspbh
  from jat_proc_change_head ch
 inner join jat_pur_handover_head ph on ch.billid = ph.source_id
                                    and ph.source_type = 'LT'
 inner join jat_mater_iniv mi on ph.billid = mi.src_bill_id
                             and ph.org_id = mi.iniv_org
 where ch.dotype = 1
   and ch.status=8
/

prompt
prompt Creating view JK_BZYSPZ_CHAIHUNBD
prompt =================================
prompt
create or replace view jk_bzyspz_chaihunbd as
select --拆包单

 ch.billid as ID,
 ch.billno as bianhao,
 ph.billno as jiaojiedbh,
 to_date(ch.dodate, 'yyyy-mm-dd') as yewufssj,
 (select to_date(max(mt.create_date), 'yyyy-mm-dd hh24:mi:ss')
    from jat_mater_trans mt
   where ch.billno = mt.trans_source_no) as yewusxsj,
 to_date(ch.update_date, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj,
 ch.org_id as caiwuzzid,
 0 as zuofeibz --作废标志
  from jat_proc_package_head ch
 inner join jat_pur_handover_head ph on ch.billid = ph.source_id
                                    and ph.source_type = 'CF'

 where ch.status = 8
/

prompt
prompt Creating view JK_BZYSPZ_CHAIHUNBDCKMX
prompt =====================================
prompt
create or replace view jk_bzyspz_chaihunbdckmx as
select --拆包单出库明细
       ch.billid as CHAIHUNBDID,
       cl.orna_code as shangpinbh,
       cl.unit_id as jiliangdwid,
       decode(cl.item_class_id,
              156,
              cl.grains,
              173,
              cl.grains,
              178,
              cl.grains,
              171,
              cl.grains,
              cl.now_qty) as shuliang
  from jat_proc_package_head ch
 inner join jat_proc_package_oldline cl on ch.billid = cl.billid
 where ch.status=8
/

prompt
prompt Creating view JK_BZYSPZ_CHAIHUNBDRKMX
prompt =====================================
prompt
create or replace view jk_bzyspz_chaihunbdrkmx as
select --拆包单入库明细
       ch.billid as CHAIHUNBDID,
       mi.orna_code as shangpinbh,
       mi.unit_id as jiliangdwid,
       decode(mi.item_class_id,
              156,
              mi.TOTAL_NUM,
              173,
              mi.TOTAL_NUM,
              178,
              mi.TOTAL_NUM,
              171,
              mi.TOTAL_NUM,
              mi.quantity) as shuliang,
       mi.total_cost as zangucb,
       mi.item_class_id as daleiid,
       mi.orna_class_id as xiaoleiid,
       mi.style_id as kuanshiid,
       mi.ARTICLE_TYPE_ID as shangpinlbid,
       mi.quality_id as chengseid,
       nvl(mi.old_orna_code,'N/A') as guanliandxspbh
  from jat_proc_package_head ch
 inner join jat_pur_handover_head ph on ch.billid = ph.source_id
                                    and ph.source_type = 'CF'
 inner join jat_mater_iniv mi on ph.billid = mi.src_bill_id
                             and ph.org_id = mi.iniv_org
 where ch.status=8
/

prompt
prompt Creating view JK_BZYSPZ_CHAIXIEDAN
prompt ==================================
prompt
create or replace view jk_bzyspz_chaixiedan as
select --拆卸单

 ch.billid as ID,
 ch.billno as bianhao,
 ph.billno as jiaojiedbh,
 to_date(ch.dodate, 'yyyy-mm-dd') as yewufssj,
 (select to_date(max(mt.create_date), 'yyyy-mm-dd hh24:mi:ss')
    from jat_mater_trans mt
   where ch.billno = mt.trans_source_no) as yewusxsj,
 to_date(ch.update_date, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj,
 ch.org_id as caiwuzzid,
 0 as zuofeibz --作废标志
  from jat_proc_change_head ch
 inner join jat_pur_handover_head ph on ch.billid = ph.source_id
                                    and ph.source_type = 'CS'

 where ch.dotype = 2
   and ch.status = 8
/

prompt
prompt Creating view JK_BZYSPZ_CHAIXIEDCKMX
prompt ====================================
prompt
create or replace view jk_bzyspz_chaixiedckmx as
select --拆卸单出库明细
       ch.billid as CHAIXIEDID,
       cl.orna_code as shangpinbh,
       cl.unit_id as jiliangdwid,
       decode(cl.item_class_id,
              156,
              cl.grains,
              173,
              cl.grains,
              178,
              cl.grains,
              171,
              cl.grains,
              cl.now_qty) as shuliang
  from jat_proc_change_head ch
 inner join jat_proc_change_line cl on ch.billid = cl.billid
 where ch.dotype = 2
   and ch.status=8
/

prompt
prompt Creating view JK_BZYSPZ_CHAIXIEDRKMX
prompt ====================================
prompt
create or replace view jk_bzyspz_chaixiedrkmx as
select--拆卸单入库明细
        ch.billid as CHAIXIEDID,
       mi.orna_code as shangpinbh,
       mi.unit_id as jiliangdwid,
       decode(mi.item_class_id,
              156,
              mi.TOTAL_NUM,
              173,
              mi.TOTAL_NUM,
              178,
              mi.TOTAL_NUM,
              171,
              mi.TOTAL_NUM,
              mi.quantity) as shuliang,
       mi.total_cost as zangucb,
       mi.item_class_id as daleiid,
       mi.orna_class_id as xiaoleiid,
       mi.style_id as kuanshiid,
       mi.ARTICLE_TYPE_ID as shangpinlbid,
       mi.quality_id as chengseid,
       nvl(mi.old_orna_code,'N/A') guanliandxspbh
  from jat_proc_change_head ch
 inner join jat_pur_handover_head ph on ch.billid = ph.source_id
                                    and ph.source_type = 'CS'
 inner join jat_mater_iniv mi on ph.billid = mi.src_bill_id
                             and ph.org_id = mi.iniv_org
 where ch.dotype = 2
   and ch.status=8
/

prompt
prompt Creating view JK_BZYSPZ_DAIXIAORKD
prompt ==================================
prompt
create or replace view jk_bzyspz_daixiaorkd as
select
--代销入库单
distinct ph.billid as ID,
         ph.billno as bianhao,
         ph.billno as jiaojiedbh,
         to_date(ph.dodate, 'yyyy-mm-dd') as yewufssj,
         to_date(mi.updatedate, 'yyyy-mm-dd hh24:mi:ss') as yewusxsj,
         to_date(mi.updatedate, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj,
         ph.org_id as caiwuzzid,
         ph.verdor_id as gongyingsid,
         0 as zuofeibz --作废标志
  from jat_pur_handover_head ph
 inner join jat_mater_iniv mi on ph.billid = mi.src_bill_id
                             and ph.org_id = mi.iniv_org
 where nvl(ph.is_psale, 0) = 1
   and ph.source_type = 'CM'
   and mi.state = 11
/

prompt
prompt Creating view JK_BZYSPZ_DAIXIAORKDMX
prompt ====================================
prompt
create or replace view jk_bzyspz_daixiaorkdmx as
select
--代销入库单明细
 ph.billid as daixiaorkdid,
 mi.orna_code as shangpinbh,
 mi.unit_id as jiliangdwid,
 decode(mi.item_class_id,
        156,
        mi.TOTAL_NUM,
        173,
        mi.TOTAL_NUM,
        178,
        mi.TOTAL_NUM,
        171,
        mi.TOTAL_NUM,
        mi.quantity) as shuliang,
 mi.total_cost/1.17 as zangucb,
 mi.item_class_id as daleiid,
 mi.orna_class_id as xiaoleiid,
 mi.style_id as kuanshiid,
 mi.ARTICLE_TYPE_ID as shangpinlbid,
 mi.quality_id as chengseid,
 to_date(mi.updatedate, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj

  from jat_pur_handover_head ph
 inner join jat_mater_iniv mi on ph.billid = mi.src_bill_id
                             and ph.org_id = mi.iniv_org
 where nvl(ph.is_psale, 0) = 1
   and ph.source_type = 'CM'
   --and ph.status = 8
   and mi.state=11
/

prompt
prompt Creating view JK_BZYSPZ_DAIXIAOZCGD
prompt ===================================
prompt
create or replace view jk_bzyspz_daixiaozcgd as
select --代销转采购单
 ph.billid as ID,
 ph.billno as bianhao,
 to_date(ph.dodate, 'yyyy-mm-dd') as yewufssj,
 to_date(ph.update_date, 'yyyy-mm-dd hh24:mi:ss') as yewusxsj,
 to_date(ph.update_date, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj,
 ph.org_id,
 0 as zuofeibz --作废标志

  from jat_plsale_head ph
 where ph.status = 8
/

prompt
prompt Creating view JK_BZYSPZ_DAIXIAOZCGDMX
prompt =====================================
prompt
create or replace view jk_bzyspz_daixiaozcgdmx as
select --代销转采购单明细
 pl.billid as daixiaozcgdid,
 pl.orna_code as shangpinbh,
 pl.unit_id as jiliangdwid,
 decode(pl.item_class_id,
        156,
        pl.grains,
        173,
        pl.grains,
        178,
        pl.grains,
        171,
        pl.grains,
        pl.now_qty) as shuliangfrom
  from jat_plsale_head ph
 inner join jat_plsale_line pl on ph.billid = pl.billid
 where ph.status = 8
/

prompt
prompt Creating view JK_BZYSPZ_DIAOBODAN
prompt =================================
prompt
create or replace view jk_bzyspz_diaobodan as
select --调拔单
distinct mh.headid as ID,
         mh.billno as bianhao,
         to_date(mh.dodate, 'yyyy-mm-dd') as yewufssj,
         to_date(ml.update_date, 'yyyy-mm-dd hh24:mi:ss') as yewusxsj,
         to_date(ml.update_date, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj,
         mh.out_org_id as diaochucwzzid,
         mh.out_stock_id as diaochucklx,
         mh.in_org_id as diaorucwzzid,
         mh.in_stock_id as diaorucklx,
         0 as zuofeibz
  from jat_move_head mh
 inner join jat_move_line ml on mh.headid = ml.headid
 where mh.bill_type in (1, 2, 3)
   --and mh.status in (7, 8,25,26)
   and ml.status = 11
/

prompt
prompt Creating view JK_BZYSPZ_DIAOBODMX
prompt =================================
prompt
create or replace view jk_bzyspz_diaobodmx as
select
--调拔单明细
       mh.headid as diaobodid,
       ml.orna_code as shangpinbh,
       1.17 as zengzhisl,
       ml.unit_id as jiliangdwid ,
       decode(ml.item_class_id,
              156,
              ml.grains,
              173,
              ml.grains,
              178,
              ml.grains,
              171,
              ml.grains,
              ml.now_qty) as shuliang,
              ml.pos_cost as jine,
              to_date(ml.update_date, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj
  from jat_move_head mh
 inner join jat_move_line ml on mh.headid = ml.headid
 where mh.bill_type in (1,2,3)
 --and mh.status in (7,8,25,26)
 and ml.status =11
/

prompt
prompt Creating view JK_BZYSPZ_DINGDANXSCKD
prompt ====================================
prompt
create or replace view jk_bzyspz_dingdanxsckd as
select --订单销售出库单
 oh.billid as ID,
 oh.billno as bianhao,
 to_date(oh.dodate, 'yyyy-mm-dd') as yewufssj,
 (select to_date(max(mt.create_date), 'yyyy-mm-dd hh24:mi:ss')
    from jat_mater_trans mt
   where oh.billno = mt.trans_source_no) as yewusxsj,
 to_date(oh.update_date, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj,
 oh.org_id as caiwuzzid,
 0 as zuofeibz,
 oh.sum_cost as zongchengben,
 round(oh.sum_cost * 1.17, 2) zengzhise
  from jat_out_vendor_head oh

 where oh.dotype = 0
   and oh.org_id = 49901
   and oh.status = 8
/

prompt
prompt Creating view JK_BZYSPZ_DINGDANXSCKDMX
prompt ======================================
prompt
create or replace view jk_bzyspz_dingdanxsckdmx as
select --订单销售出库单明细
 oh.billid as dingdanxsckdid,
 ol.orna_code as shangpinbh,
 ol.unit_id as jiliangdwid,
 decode(ol.item_class_id,
        156,
        ol.grains,
        173,
        ol.grains,
        178,
        ol.grains,
        171,
        ol.grains,
        ol.now_qty) shuliang
  from jat_out_vendor_head oh
 inner join jat_out_vendor_line ol on oh.billid = ol.billid
 where oh.dotype = 0
   and oh.org_id = 49901
   and oh.status = 8
/

prompt
prompt Creating view JK_BZYSPZ_MATER_ACTIVE
prompt ====================================
prompt
create or replace view jk_bzyspz_mater_active as
select --现有量
 ma.org_id,
 ma.stock_id,
 ma.orna_code,
 ma.orna_dsc,
 ma.style_id,
 decode(ma.item_class_id,
        156,
        ma.stone_now_num,
        173,
        ma.stone_now_num,
        178,
        ma.stone_now_num,
        171,
        ma.stone_now_num,
        ma.now_qty) now_qty,
 ma.state
  from ic_mater_active ma
/

prompt
prompt Creating view JK_BZYSPZ_MOVE_NET
prompt ================================
prompt
create or replace view jk_bzyspz_move_net as
select --调拔路径
 mn.net_id, mn.out_org_id, mn.in_org_id, mn.status
  from jat_move_net mn
/

prompt
prompt Creating view JK_BZYSPZ_QITACKD
prompt ===============================
prompt
create or replace view jk_bzyspz_qitackd as
select --其他出库单

 88888 + oh.billid as ID,
 oh.billno as bianhao,
 to_date(oh.dodate, 'yyyy-mm-dd') as yewufssj,
 (select to_date(max(mt.create_date), 'yyyy-mm-dd hh24:mi:ss')
    from jat_mater_trans mt
   where oh.billno = mt.trans_source_no) as yewusxsj,
 to_date(oh.update_date, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj,
 decode(oh.dotype, 0, 67, 1, 74, 2, 47, 3, 41, 4, 85) as shoufalbid,
 oh.org_id as caiwuzzid,
 0 as zuofeibz
  from jat_out_head oh
 where oh.status = 8
union all
select 77777 + oh.billid,
       oh.billno,
       to_date(oh.dodate, 'yyyy-mm-dd') as yewufssj,
       (select to_date(max(mt.create_date), 'yyyy-mm-dd hh24:mi:ss')
          from jat_mater_trans mt
         where oh.billno = mt.trans_source_no) as yewusxsj,
       to_date(oh.update_date, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj,
       82 as shoufalbid,
       oh.org_id,
       0 as zuofeibz
  from jat_out_vendor_head oh

 where oh.dotype = 1
   and oh.status = 8
/

prompt
prompt Creating view JK_BZYSPZ_QITACKDMX
prompt =================================
prompt
create or replace view jk_bzyspz_qitackdmx as
select --其他出库单明细
 88888+oh.billid as qitackdid,
 decode(oh.dotype, 0, 67, 1, 74, 2, 47, 3, 41, 4, 85) as shoufalbid,
 ol.orna_code as shangpinbh,
 1.17 as zengzhisl,
 ol.unit_id as jiliangdwid,
 decode(ol.item_class_id,
        156,
        ol.grains,
        173,
        ol.grains,
        178,
        ol.grains,
        171,
        ol.grains,
        ol.now_qty) as shuliang
  from jat_out_head oh
 inner join jat_out_line ol on oh.billid = ol.billid
 where oh.status = 8

union all
select 77777+oh.billid,
       decode(oh.dotype, 0, 67, 1, 74, 2, 47, 3, 41, 4, 85) as shoufalbid,
       ol.orna_code,
        1.17 as zengzhisl,
       ol.unit_id,
       decode(ol.item_class_id,
              156,
              ol.grains,
              173,
              ol.grains,
              178,
              ol.grains,
              171,
              ol.grains,
              ol.now_qty) as shuliang
  from jat_out_vendor_head oh
 inner join jat_out_vendor_line ol on oh.billid = ol.billid
 where oh.dotype = 1
   and oh.status = 8
/

prompt
prompt Creating view JK_BZYSPZ_QITARKD
prompt ===============================
prompt
create or replace view jk_bzyspz_qitarkd as
select --其他入库单
distinct ph.billid AS ID,
         ph.billno AS bianhao,
         ph.billno as jiaojiedbh,
         to_date(ph.dodate, 'yyyy-mm-dd') as yewufssj,
         to_date(mi.updatedate, 'yyyy-mm-dd hh24:mi:ss') as yewusxsj,
         to_date(mi.updatedate, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj,
         66 as shoufalbid,
         ph.org_id as caiwuzzid,
         0 as zuofeibz --作废标志
  from jat_pur_handover_head ph
 inner join jat_mater_iniv mi on ph.billid = mi.src_bill_id
                             and ph.org_id = mi.iniv_org
 where ph.source_type = 'TT'
   and mi.state = 11
/

prompt
prompt Creating view JK_BZYSPZ_QITARKDMX
prompt =================================
prompt
create or replace view jk_bzyspz_qitarkdmx as
select --其他入库单明细
       ph.billid as qitarkdid,
       mi.orna_code as shangpinbh,
       mi.unit_id as jiliangdwid,
       decode(mi.item_class_id,
              156,
              mi.TOTAL_NUM,
              173,
              mi.TOTAL_NUM,
              178,
              mi.TOTAL_NUM,
              171,
              mi.TOTAL_NUM,
              mi.quantity) as shuliang,
       mi.total_cost/1.17 as zangucb,
       mi.item_class_id as daleiid,
       mi.orna_class_id as xiaoleiid,
       mi.style_id as kuanshiid,
       mi.ARTICLE_TYPE_ID as shangpinlbid,
       mi.quality_id as chengseid,
       round(mi.total_cost /1.17 * 0.17, 2) as zengzhise,
       to_date(mi.updatedate, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj
  from jat_pur_handover_head ph

 inner join jat_mater_iniv mi on ph.billid = mi.src_bill_id
                             and ph.org_id = mi.iniv_org

 where ph.source_type='TT'
  --and ph.status=8
  and mi.state=11
/

prompt
prompt Creating view JK_BZYSPZ_WEIWAIJGCPRKD
prompt =====================================
prompt
create or replace view jk_bzyspz_weiwaijgcprkd as
select
--委外加工成品入库单
distinct ph.billid as ID,
         ph.billno as bianhao,
         ph.billno as jiaojiedbh,
         to_date(ph.dodate, 'yyyy-mm-dd') as yewufssj,
         to_date(mi.updatedate, 'yyyy-mm-dd hh24:mi:ss') as yewusxsj,
         to_date(mi.updatedate, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj,
         ph.org_id as caiwuzzid,
         ph.verdor_id as gongyingsid,
         0 as zuofeibz --作废标志
  from jat_pur_handover_head ph
 inner join jat_mater_iniv mi on ph.billid = mi.src_bill_id
                             and ph.org_id = mi.iniv_org
 where nvl(ph.dotype, 0) = 1
   and nvl(ph.is_psale, 0) = 0
   and ph.source_type = 'CM'
   and mi.state = 11
/

prompt
prompt Creating view JK_BZYSPZ_WEIWAIJGCPRKDMX
prompt =======================================
prompt
create or replace view jk_bzyspz_weiwaijgcprkdmx as
select --委外加工成品入库单
 ph.billid as weiwaijgcprkdid,
 mi.orna_code as shangpinbh,
 mi.unit_id as jiliangdwid,
 decode(mi.item_class_id,
        156,
        mi.TOTAL_NUM,
        173,
        mi.TOTAL_NUM,
        178,
        mi.TOTAL_NUM,
        171,
        mi.TOTAL_NUM,
        mi.quantity) as shuliang,
 mi.total_cost/1.17 as zangucb,
 0 as zangujgf, --加工费
 mi.item_class_id as daleiid,
 mi.orna_class_id as xiaoleiid,
 mi.style_id as kuanshiid,
 mi.ARTICLE_TYPE_ID as shangpinlbid,
 mi.quality_id as chengseid,
 to_date(mi.updatedate, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj

  from jat_pur_handover_head ph

 inner join jat_mater_iniv mi on ph.billid = mi.src_bill_id
                             and ph.org_id = mi.iniv_org

 where nvl(ph.dotype, 0) = 1
   and nvl(ph.is_psale, 0) = 0
   and ph.source_type = 'CM'
  -- and ph.status = 8
   and mi.state=11
/

prompt
prompt Creating view JK_BZYSPZ_WEIWAIJGFLCKD
prompt =====================================
prompt
create or replace view jk_bzyspz_weiwaijgflckd as
select --委外加工发料出库单

 oh.billid as ID,
 oh.billno as bianhao,
 to_date(oh.dodate, 'yyyy-mm-dd') as yewufssj,
 (select to_date(max(mt.create_date), 'yyyy-mm-dd hh24:mi:ss')
    from jat_mater_trans mt
   where oh.billno = mt.trans_source_no) as yewusxsj,
 to_date(oh.update_date, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj,
 oh.org_id as caiwuzzid,
 0 as zuofeibz,
 oh.vendor_id as gongyingsid,
 0 as zonggongfei
  from jat_out_vendor_head oh

 where oh.dotype = 2
   and oh.status = 8
/

prompt
prompt Creating view JK_BZYSPZ_WEIWAIJGFLCKDMX
prompt =======================================
prompt
create or replace view jk_bzyspz_weiwaijgflckdmx as
select
--委外加工发料出库单明细
 oh.billid as weiwaijgflckdid,
 ol.orna_code as shangpinbh,
 ol.unit_id as jiliangdwid,
 decode(ol.item_class_id,
        156,
        ol.grains,
        173,
        ol.grains,
        178,
        ol.grains,
        171,
        ol.grains,
        ol.now_qty) as shuliang
  from jat_out_vendor_head oh
 inner join jat_out_vendor_line ol on oh.billid = ol.billid
 where oh.dotype = 2
   and oh.status = 8
/

prompt
prompt Creating view JK_BZYSPZ_WEIWAIJGJJD
prompt ===================================
prompt
create or replace view jk_bzyspz_weiwaijgjjd as
select --委外加工交接单
 ph.billid as ID,
 ph.billno as bianhao,
 to_date(ph.dodate, 'yyyy-mm-dd') as yewufssj,
 (select to_date(max(mt.create_date), 'yyyy-mm-dd hh24:mi:ss')
    from jat_mater_trans mt
   where ph.billno = mt.trans_source_no) as yewusxsj,
 to_date(ph.update_date, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj,
 ph.verdor_id as gongyingsid,
 ph.org_id as caiwuzzid,
 0 as zuofeibz --作废标志
  from jat_pur_handover_head ph

 where nvl(ph.dotype, 0) = 1
   and nvl(ph.is_psale, 0) = 0
   and ph.source_type = 'CM'
   and ph.status = 8
   and exists (select 1
          from jat_mater_iniv mi
         where ph.billid = mi.src_bill_id
           and ph.org_id = mi.iniv_org
           and mi.state = 11)
/

prompt
prompt Creating view JK_BZYSPZ_WEIWAIJGJJDCPMX
prompt =======================================
prompt
create or replace view jk_bzyspz_weiwaijgjjdcpmx as
select
--委外加工交接单成品明细
       ph.billid as weiwaijgjjdid,
       mi.orna_code as shangpinbh,
       mi.unit_id as jiliangdwid,
       decode(mi.item_class_id,
              156,
              mi.TOTAL_NUM,
              173,
              mi.TOTAL_NUM,
              178,
              mi.TOTAL_NUM,
              171,
              mi.TOTAL_NUM,
              mi.quantity) as shuliang
  from jat_pur_handover_head ph

 inner join jat_mater_iniv mi on ph.billid = mi.src_bill_id
                             and ph.org_id = mi.iniv_org
 where nvl(ph.dotype, 0) = 1
   and nvl(ph.is_psale, 0) = 0
   and ph.source_type = 'CM'
   and ph.status=8
   and mi.state=11
/

prompt
prompt Creating view JK_BZYSPZ_WEIWAIJGJJDCPPJMX
prompt =========================================
prompt
create or replace view jk_bzyspz_weiwaijgjjdcppjmx as
select
--委外加工交接单成品配件明细
--配件
       ph.billid as weiwaijgjjdid,
       mi.orna_code as weiwaijgspbh,
       cl.quality_id as chengseid,
        nvl(cl.STYLE_ID,0) as kuanshiid,
        612 as jiliangdwid,
        cl.acs_quantity as shuliang,
        cl.ACS_WITH_TAX_MONEY as zangucb,
        nvl(mi.old_orna_code,'N/A') as guanliandxspbh

  from jat_pur_handover_head ph

 inner join jat_mater_iniv mi on ph.billid = mi.src_bill_id
                             and ph.org_id = mi.iniv_org
 inner join jat_calc_price_head cp on cp.wl_code = mi.orna_code
 inner join jat_calc_price_acs_line cl on cp.id = cl.head_id
 where nvl(ph.dotype, 0) = 1
   and nvl(ph.is_psale, 0) = 0
   and ph.source_type = 'CM'
   and ph.status=8
   and mi.state=11
/

prompt
prompt Creating view JK_BZYSPZ_WEIWAIJGJJDCPZYJYLMX
prompt ============================================
prompt
create or replace view jk_bzyspz_weiwaijgjjdcpzyjylmx as
select
--PZ17-委外加工交接单成品自有金原料明细
 ph.billid as weiwaijgjjdid,
 mi.orna_code as weiwaijgspbh,
 cl.quality_id as chengseid,
 nvl(cl.STYLE_ID, 0) as kuanshiid,
 609 as jiliangdwid,
 cl.SBRA_WEIGHT as shuliang,
 cl.SBRA_WITH_TAX_MONEY as zangucb,
 nvl(mi.old_orna_code, 'N/A') as guanliandxspbh
  from jat_pur_handover_head ph
 inner join jat_mater_iniv mi on ph.billid = mi.src_bill_id
                             and ph.org_id = mi.iniv_org
 inner join jat_calc_price_head cp on cp.wl_code = mi.orna_code
 inner join JAT_CALC_PRICE_SBRA_LINE cl on cp.id = cl.head_id
 where nvl(ph.dotype, 0) = 1
   and nvl(ph.is_psale, 0) = 0
   and ph.source_type = 'CM'
   and ph.status = 8
   and mi.state = 11
union all
select
--素金核价单作为金原料
 ph.billid as weiwaijgjjdid,
 mi.orna_code as weiwaijgspbh,
 cp.quality_id as chengseid,
 nvl(cp.STYLE_ID, 0) as kuanshiid,
 609 as jiliangdwid,
 cp.all_weight as shuliang,
 cp.ALL_WIDTH_TAX_PRICE as zangucb,
 nvl(mi.old_orna_code, 'N/A') as guanliandxspbh
  from jat_pur_handover_head ph
 inner join jat_mater_iniv mi on ph.billid = mi.src_bill_id
                             and ph.org_id = mi.iniv_org
 inner join jat_calc_price_head cp on cp.wl_code = mi.orna_code
 where nvl(ph.dotype, 0) = 1
   and nvl(ph.is_psale, 0) = 0
   and ph.source_type = 'CM'
   and ph.status = 8
   and nvl(cp.cacl_type, 0) = 1
   and mi.state = 11
union all
select
--素金入库单作金原料
 ph.billid as weiwaijgjjdid,
 mi.orna_code as weiwaijgspbh,
 mi.quality_id as chengseid,
 mi.style_id as kuanshiid,
 609 as jiliangdwid,
 mi.all_qty as shuliang,
 mi.total_cost as zangucb,
 nvl(mi.old_orna_code, 'N/A') as guanliandxspbh
  from jat_pur_handover_head ph
 inner join jat_mater_iniv mi on ph.billid = mi.src_bill_id
                             and ph.org_id = mi.iniv_org

 where nvl(ph.dotype, 0) = 1
   and nvl(ph.is_psale, 0) = 0
   and ph.source_type = 'CM'
   and ph.status = 8
   and mi.state = 11
   and not exists
 (select 1 from jat_calc_price_head cp where cp.wl_code = mi.orna_code)
/

prompt
prompt Creating view JK_BZYSPZ_WEIWAIJGJJDCPZYYLMX
prompt ===========================================
prompt
create or replace view jk_bzyspz_weiwaijgjjdcpzyylmx as
select --委外加工交接单成品自有原料明细
       ph.billid as weiwaijgjjdid,
       mi.orna_code as weiwaijgspbh,
       cl.luodan_code as shangpinbh,
       610 as jiliangdwid,
       cl.stone_nums as shuliang,
       nvl(mi.old_orna_code,'N/A') as guanliandxspbh

  from jat_pur_handover_head ph

 inner join jat_mater_iniv mi on ph.billid = mi.src_bill_id
                             and ph.org_id = mi.iniv_org
 left join jat_calc_price_head cp on cp.wl_code = mi.orna_code
 left join jat_calc_price_stone_line cl on cp.id = cl.head_id
 where nvl(ph.dotype, 0) = 1
   and nvl(ph.is_psale, 0) = 0
   and ph.source_type = 'CM'
   and nvl(cl.private_type,0)=1
   and ph.status=8
   and mi.state=11
/

prompt
prompt Creating view JK_BZYSPZ_WEIWAIJGSP
prompt ==================================
prompt
create or replace view jk_bzyspz_weiwaijgsp as
select --委外加工收票
distinct ph.billid as ID,
         ph.billno as bianhao,
         to_date(ph.dodate, 'yyyy-mm-dd') as yewufssj,
         to_date(rl.create_date, 'yyyy-mm-dd hh24:mi:ss') as yewusxsj,
         to_date(ph.update_date, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj,
         ph.org_id as caiwuzzid,
         ph.verdor_id as gongyingsid,
         0 as zuofeibz, --作废标志
         rl.moneys as zonggongfei,
         rl.all_money as zengzhise
  from jat_pur_handover_head ph
 inner join jat_bill_receive_line rl on ph.billno = rl.hand_no
 where nvl(ph.dotype, 0) = 1
   and ph.source_type = 'CM'
   and ph.status = 8
/

prompt
prompt Creating view JK_BZYSPZ_WEIWAIJGSPJJDMX
prompt =======================================
prompt
create or replace view jk_bzyspz_weiwaijgspjjdmx as
select --委外加工收票交接单明细
 ph.billid as weiwaijgspid,ph.billno as jiaojiedbh,
  rl.moneys as zonggongfei,
       rl.tax_money as zengzhise
  from jat_pur_handover_head ph
 inner join jat_bill_receive_line rl on ph.billno = rl.hand_no
 where nvl(ph.dotype, 0) = 1
   and ph.source_type = 'CM'
   and ph.status = 8
/

prompt
prompt Creating view JK_BZYSPZ_WEIWAIJGSPMX
prompt ====================================
prompt
create or replace view jk_bzyspz_weiwaijgspmx as
select --委外加工收票单明细
 ph.billid as weiwaijgspid,ph.billno as jiaojiedbh, mi.orna_code as shangpinbh
  from jat_pur_handover_head ph
 inner join jat_mater_iniv mi on ph.billid = mi.src_bill_id
                             and ph.org_id = mi.iniv_org

 where nvl(ph.dotype, 0) = 1
   and ph.source_type = 'CM'
   and ph.status = 8
/

prompt
prompt Creating view JK_BZYSPZ_WEIWAIJGTLDDYFLMX
prompt =========================================
prompt
create or replace view jk_bzyspz_weiwaijgtlddyflmx as
select --委外加工退料入库单发料明细
       ch.billid as weiwaijgtlrukdid,
       cl.orna_no as shangpinbh,
       cl.unit_id as jiliangdwid,
       cl.exit_nums as shuliang
  from jat_proc_exit_head ch
 inner join jat_proc_exit_line cl on ch.billid = cl.billid
 where ch.status=8
/

prompt
prompt Creating view JK_BZYSPZ_WEIWAIJGTLRKD
prompt =====================================
prompt
create or replace view jk_bzyspz_weiwaijgtlrkd as
select --委外加工退料入库单

 ch.billid as ID,
 ch.billno as bianhao,
 ph.billno as jiaojiedbh,
 to_date(ch.dodate, 'yyyy-mm-dd') as yewufssj,
 (select to_date(max(mt.create_date), 'yyyy-mm-dd hh24:mi:ss')
    from jat_mater_trans mt
   where ch.billno = mt.trans_source_no) as yewusxsj,
 to_date(ch.update_date, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj,
 ch.org_id as caiwuzzid,
 ch.vendor_id as gongyingsid,
 0 as zuofeibz, --作废标志
 0 as zonggongfei
  from jat_proc_exit_head ch
 inner join jat_pur_handover_head ph on ch.billid = ph.source_id
                                    and ph.source_type = 'TL'

 where ch.status = 8
/

prompt
prompt Creating view JK_BZYSPZ_WEIWAIJGTLRKDMX
prompt =======================================
prompt
create or replace view jk_bzyspz_weiwaijgtlrkdmx as
select --委外加工退料入库单入库明细
       ch.billid as weiwaijgtlrukdid,
       mi.orna_code as shangpinbh,
       mi.unit_id as jiliangdwid,
       decode(mi.item_class_id,
              156,
              mi.TOTAL_NUM,
              173,
              mi.TOTAL_NUM,
              178,
              mi.TOTAL_NUM,
              171,
              mi.TOTAL_NUM,
              mi.quantity) as shuliang,
       mi.total_cost as zangucb,
       mi.item_class_id as daleiid,
       mi.orna_class_id as xiaoleiid,
       mi.style_id as kuanshiid,
       mi.ARTICLE_TYPE_ID as shangpinlbid,
       mi.quality_id as chengseid,
       mi.old_orna_code as guanliandxspbh
  from jat_proc_exit_head ch
 inner join jat_pur_handover_head ph on ch.billid = ph.source_id
                                    and ph.source_type = 'TL'
 inner join jat_mater_iniv mi on ph.billid = mi.src_bill_id
                             and ph.org_id = mi.iniv_org
 where ch.status=8
/

prompt
prompt Creating view JK_BZYSPZ_XINGTAIZHD
prompt ==================================
prompt
create or replace view jk_bzyspz_xingtaizhd as
select --形态转换单
 ch.billid as ID,
 ch.billno as bianhao,
 PH.BILLNO as jiaojiedbh,
 to_date(ch.dodate, 'yyyy-mm-dd') as yewufssj,
 (select to_date(max(mt.create_date), 'yyyy-mm-dd hh24:mi:ss')
    from jat_mater_trans mt
   where ch.billno = mt.trans_source_no) as yewusxsj,
 to_date(ch.update_date, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj,
 ch.org_id as caiwuzzid,
 0 as zuofeibz --作废标志
  from jat_proc_change_head ch
  LEFT join jat_pur_handover_head ph on ch.billid = ph.source_id
                                    and ph.source_type = 'XT'

 where ch.dotype = 0
   and ch.status = 8
/

prompt
prompt Creating view JK_BZYSPZ_XINGTAIZHDCKMX
prompt ======================================
prompt
create or replace view jk_bzyspz_xingtaizhdckmx as
select
--形态转换单出库明细
       ch.billid as xingtaizhdid,
       cl.orna_code as shangpinbh,
       cl.unit_id as jiliangdwid,
       decode(cl.item_class_id,
              156,
              cl.grains,
              173,
              cl.grains,
              178,
              cl.grains,
              171,
              cl.grains,
              cl.now_qty) as shuliang
  from jat_proc_change_head ch
 inner join jat_proc_change_line cl on ch.billid = cl.billid
 where ch.dotype = 0
   and ch.status=8
/

prompt
prompt Creating view JK_BZYSPZ_XINGTAIZHDRKMX
prompt ======================================
prompt
create or replace view jk_bzyspz_xingtaizhdrkmx as
select --形态转换单入库明细
 ch.billid as xingtaizhdid,
 mi.orna_code as shangpinbh,
 mi.unit_id as jiliangdwid,
 decode(mi.item_class_id,
        156,
        mi.TOTAL_NUM,
        173,
        mi.TOTAL_NUM,
        178,
        mi.TOTAL_NUM,
        171,
        mi.TOTAL_NUM,
        mi.quantity) as shuliang,
 mi.total_cost as zangucb,
 mi.item_class_id as daleiid,
 mi.orna_class_id as xiaoleiid,
 mi.style_id as kuanshiid,
 mi.ARTICLE_TYPE_ID as shangpinlbid,
 mi.quality_id as chengseid,
 nvl(mi.old_orna_code,'N/A') as guanliandxspbh
  from jat_proc_change_head ch
 inner join jat_pur_handover_head ph on ch.billid = ph.source_id
                                    and ph.source_type = 'XT'
 inner join jat_mater_iniv mi on ph.billid = mi.src_bill_id
                             and ph.org_id = mi.iniv_org
 where ch.dotype = 0
   and ch.status = 8
/

prompt
prompt Creating view JK_BZYSPZ_YIKUDAN
prompt ===============================
prompt
create or replace view jk_bzyspz_yikudan as
select --移库单
 mh.headid as ID,
 mh.billno as bianhao,
 to_date(mh.dodate, 'yyyy-mm-dd') as yewufssj,
 (select to_date(max(mt.create_date), 'yyyy-mm-dd hh24:mi:ss')
    from jat_mater_trans mt
   where mh.billno = mt.trans_source_no) as yewusxsj,
 to_date(mh.update_date, 'yyyy-mm-dd hh24:mi:ss') as zuihougxsj,
 mh.out_org_id as caiwuzzid,
 mh.out_stock_id as diaochucklx,
 mh.in_stock_id as diaorucklx,
 0 as zuofeibz
  from jat_move_head mh

 where mh.bill_type = 4
   and mh.status = 8
/

prompt
prompt Creating view JK_BZYSPZ_YIKUDMX
prompt ===============================
prompt
create or replace view jk_bzyspz_yikudmx as
select --移库单明细
 mh.headid as yikudid,
 ml.orna_code as shangpinbh,
 ml.unit_id as jiliangdwid,
 decode(ml.item_class_id,
        156,
        ml.grains,
        173,
        ml.grains,
        178,
        ml.grains,
        171,
        ml.grains,
        ml.now_qty) as shuliang
  from jat_move_head mh
 inner join jat_move_line ml on mh.headid = ml.headid
 where mh.bill_type = 4
   and mh.status = 8
/


spool off
