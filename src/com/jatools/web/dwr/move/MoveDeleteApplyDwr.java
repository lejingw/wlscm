package com.jatools.web.dwr.move;

import com.jatools.common.CommonUtil;
import com.jatools.manager.move.MoveDeleteApplyManager;
import com.jatools.vo.move.MoveDeleteApply;
import com.jatools.web.util.StringUtil;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 任明.
 * User: Marx
 * Date: 12-10-12
 * Time: 下午1:21
 */
public class MoveDeleteApplyDwr {
    private static Logger logger = Logger.getLogger(MoveDeleteApplyDwr.class);

    private MoveDeleteApplyManager moveDeleteApplyManager;

    public void setMoveDeleteApplyManager(MoveDeleteApplyManager moveDeleteApplyManager) {
        this.moveDeleteApplyManager = moveDeleteApplyManager;
    }

    public String saveOrUpdateMoveDeleteApply(MoveDeleteApply moveDeleteApply, HttpSession session) {
        try{
            this.moveDeleteApplyManager.saveOrUpdateMoveDeleteApply(moveDeleteApply, CommonUtil.getSessionUserId(session));
        } catch (Exception e) {
            logger.error("失败", e);
            if(StringUtil.isNotBlank(e.getMessage())){
                return e.getMessage();
            }
            return "保存失败";
        }
        return null;
    }

    public Map<String, String> saveAndCheckMoveDeleteApply(MoveDeleteApply moveDeleteApply, HttpSession session) {
        Map<String, String> res = new HashMap<String, String>();
        try{
            this.moveDeleteApplyManager.saveAndCheckMoveDeleteApply(moveDeleteApply, CommonUtil.getSessionUserId(session));
            res.put("isSuccess", "true");
            res.put("headid", moveDeleteApply.getHeadid());
        } catch (Exception e) {
            logger.error("失败", e);
            res.put("isSuccess", "false");
            if(StringUtil.isNotBlank(e.getMessage())){
                res.put("msg", e.getMessage());
            } else {
                res.put("msg", "审批失败");
            }
        }
        return res;
    }

    public String deleteMoveDeleteApply(List<String> headids, HttpSession session) {
        try {
            for (String headid : headids) {
                if(StringUtil.isNotBlank(headid)){
                    this.moveDeleteApplyManager.deleteMoveDeleteApply(headid, CommonUtil.getSessionUserId(session));
                }
            }
        } catch (Exception e) {
            logger.error("删除失败", e);
            return "删除失败";
        }
        return null;
    }
}
