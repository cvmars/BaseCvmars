package com.cvmars.baseapp.business.implement;

import com.cvmars.baseapp.business.BaseItemModel;
import com.cvmars.baseapp.business.IBusinessHandler;

/**
 * Created by hehaifeng on 2018/6/1.
 */

public class TestHandler implements IBusinessHandler {
    @Override
    public boolean enabled(BaseItemModel model) {
        return false;
    }

    @Override
    public void doHandler(BaseItemModel model) {

    }
}
