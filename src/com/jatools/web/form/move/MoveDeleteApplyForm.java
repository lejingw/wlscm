package com.jatools.web.form.move;

import com.jatools.vo.move.MoveDeleteApply;
import com.jatools.vo.move.MoveHeadLog;
import com.jatools.vo.move.MoveLineLog;
import com.jatools.web.form.BaseForm;

import java.util.List;

/**
 * Created by 任明.
 * User: Marx
 * Date: 12-10-12
 * Time: 下午1:25
 */
public class MoveDeleteApplyForm extends BaseForm {
    private MoveDeleteApply moveDeleteApply;

    private MoveHeadLog moveHeadLog;

    private List<MoveLineLog> moveLineLogList;

    private String dodate;

    public String getDodate() {
        return dodate;
    }

    public void setDodate(String dodate) {
        this.dodate = dodate;
    }

    public MoveDeleteApply getMoveDeleteApply() {
        return moveDeleteApply;
    }

    public void setMoveDeleteApply(MoveDeleteApply moveDeleteApply) {
        this.moveDeleteApply = moveDeleteApply;
    }

    public MoveHeadLog getMoveHeadLog() {
        return moveHeadLog;
    }

    public void setMoveHeadLog(MoveHeadLog moveHeadLog) {
        this.moveHeadLog = moveHeadLog;
    }

    public List<MoveLineLog> getMoveLineLogList() {
        return moveLineLogList;
    }

    public void setMoveLineLogList(List<MoveLineLog> moveLineLogList) {
        this.moveLineLogList = moveLineLogList;
    }
}
