package com.jatools.web.form.push;

import com.jatools.vo.push.GatherSetHead;
import com.jatools.vo.push.GatherSetLine;
import com.jatools.vo.push.Salable;
import com.jatools.web.form.BaseForm;

import java.util.List;

public class SalableForm extends BaseForm {
    private Salable salable;

    public Salable getSalable() {
        return salable;
    }

    public void setSalable(Salable salable) {
        this.salable = salable;
    }
}
