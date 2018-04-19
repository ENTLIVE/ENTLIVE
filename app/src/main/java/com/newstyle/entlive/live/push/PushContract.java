package com.newstyle.entlive.live.push;

import com.newstyle.entlive.base.BasePresenter;
import com.newstyle.entlive.base.BaseView;

/**
 * Created by wangdong on 2018/4/19.
 */

public class PushContract {
    //MVP设计的V层接口
    interface View extends BaseView<Presenter> {

    }

    //MVP设计的P层接口
    interface Presenter extends BasePresenter {

    }
}
