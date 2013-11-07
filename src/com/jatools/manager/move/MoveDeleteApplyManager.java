package com.jatools.manager.move;

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
 * Time: 下午1:02
 */
public interface MoveDeleteApplyManager {

    Pager getMoveDeleteApplyPageData(Map<String, String> condition);


    MoveDeleteApply getMoveDeleteApply(String headid);

    MoveHeadLog getMoveHeadByMoveDelete(String moveDeleteId);

    MoveHeadLog getMoveHeadByMoveId(String moveId);

    List<MoveLineLog> getMoveLineByMoveDelete(String moveDeleteId);

    List<MoveLineLog> getMoveLineByMoveId(String moveId);

    void saveOrUpdateMoveDeleteApply(MoveDeleteApply moveDeleteApply, String userId);

    void saveAndCheckMoveDeleteApply(MoveDeleteApply moveDeleteApply, String userId);

    Pager getMoveBillpagerData(Map<String, String> condition);

    void deleteMoveDeleteApply (String headid, String userId);
}
