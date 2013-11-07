package com.jatools.dao.move;

import com.jatools.common.Pager;
import com.jatools.vo.move.MoveDeleteApply;
import com.jatools.vo.move.MoveHeadLog;
import com.jatools.vo.move.MoveLineLog;

import java.util.List;
import java.util.Map;

/**
 * Created by 任明.
 * User: Marx
 * Date: 12-10-12
 * Time: 上午10:44
 */
public interface MoveDeleteApplyDao {

    Pager getMoveDeleteApplyPageData(Map<String, String> condition);

    MoveDeleteApply getMoveDeleteApply(String headid);

    MoveHeadLog getMoveHeadByMoveDelete(String moveDeleteId);

    MoveHeadLog getMoveHeadByMoveId(String moveId);

    List<MoveLineLog> getMoveLineByMoveDelete(String moveDeleteId);

    List<MoveLineLog> getMoveLineByMoveId(String moveId);

    void saveMoveDeleteApply(MoveDeleteApply moveDeleteApply);

    void updateMoveDeleteApply(MoveDeleteApply moveDeleteApply);

    void deleteMoveDeleteApply(String moveDeleteId);

    void saveMoveHeadLog(String moveDeleteId, String moveId);

    void saveMoveLineLog(String moveDeleteId, String moveId);

    Pager getMoveBillpagerData(Map<String, String> condition);

    void deleteMoveHeadAndLine(String moveId);

    void modifyMoveDeleteApplyStatus(String headid, String status);

    void modifyMoveBillStatus(String headid, String status);

    boolean isExistsMoveHeadLog(String moveId);
}
