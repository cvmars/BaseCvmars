package com.cvmars.baseapp.business;

/**
 * Created by hehaifeng on 2018/6/1.
 */

public interface IBusinessHandler {

    //根据类型来判断是否执行
    public boolean enabled(BaseItemModel model);

    //执行具体业务操作
    public void doHandler(BaseItemModel model);
}
