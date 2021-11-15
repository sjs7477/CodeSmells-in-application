package com.android_mvc.nakisousou.domain;

import android.app.Activity;

import com.android_mvc.nakisousou.activities.func_db.DBListActivity;
import com.android_mvc.nakisousou.db.dao.NakisouDAO;
import com.android_mvc.nakisousou.db.entity.Nakisou;
import com.android_mvc.framework.controller.action.ActionResult;
import com.android_mvc.framework.controller.action.BaseAction;
import com.android_mvc.framework.ui.UIUtil;

/**
 * DB更新に関するBL。
 * @author id:language_and_engineering
 *
 */
public class DBUpdateAction extends BaseAction
{

    private DBListActivity activity;
    private Long friend_id;


    public DBUpdateAction(DBListActivity activity, Long friend_id) {
        this.activity = activity;
        this.friend_id = friend_id;
    }


    // BL本体
    @Override
    public ActionResult exec()
    {
        // DB更新を実行
        Nakisou f = new NakisouDAO(activity).naitesou(friend_id);

        // 実行結果を返す
        DBUpdateActionResult ares = new DBUpdateActionResult();
        ares.setRouteId("success");
        ares.friend = f;

        return ares;
    }
        // NOTE: setRouteIdはBaseで定義したメソッドなので，返り値が合わないためメソッドチェインができず…


    // 実行結果オブジェクト
    static class DBUpdateActionResult extends ActionResult
    {
        private static final long serialVersionUID = 1L;

        protected Nakisou friend;

        @Override
        public void onNextActivityStarted(Activity activity)
        {
            UIUtil.longToast(activity, friend.getName() + "さんがより一層泣いてそうになりました。");
        }
    }

}
