package com.android_mvc.nakisousou.activities.main;

import android.view.View;
import android.view.View.OnClickListener;

import com.android_mvc.nakisousou.bat.SamplePeriodicService;
import com.android_mvc.nakisousou.controller.MainController;
import com.android_mvc.nakisousou.R;
import com.android_mvc.framework.activities.base.BaseNormalActivity;
import com.android_mvc.framework.ui.UIBuilder;
import com.android_mvc.framework.ui.UIUtil;
import com.android_mvc.framework.ui.menu.OptionMenuBuilder;
import com.android_mvc.framework.ui.menu.OptionMenuDescription;
import com.android_mvc.framework.ui.view.MButton;
import com.android_mvc.framework.ui.view.MTextView;

/**
 * サンプルのトップ画面。
 * @author id:language_and_engineering
 *
 */
public class TopActivity extends BaseNormalActivity
{

    // これらのメンバ宣言は，書かなくても動作する。
    MTextView tv1;
    MTextView tv2;
    MButton button1;
    MButton button2;
    MButton button3;

    @Override
    public void defineContentView() {
        final TopActivity activity = this;

        // ここに，画面上のUI部品の定義を記述する。

        new UIBuilder(context)
          .add(

              tv1 = new MTextView(context)
                .text("泣きそう管理画面" )
                .widthWrapContent()
              ,

              tv2 = new MTextView(context)
                .text("このアプリの名称：" + $._(R.string.app_name) + "\n" )
                .widthWrapContent()
              ,

              button1 = new MButton(context)
                .text("Toastを表示")
                .click(new OnClickListener(){
                      @Override
                      public void onClick(View v) {
                          UIUtil.longToast(context, "ギリギリ泣きそうです。");
                      }
                })
              ,

              new MTextView(context)
                .text("\n泣きそうな情報をDBで管理するサンプル：" )
                .widthWrapContent()
              ,

              button2 = new MButton(context)
                  .text("泣きそう情報登録画面へ")
                  .click(new OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            MainController.submit(activity, "EDIT_DB");
                        }
                  })
              ,

              button3 = new MButton(context)
                  .text("泣きそう情報閲覧画面へ")
                  .click(new OnClickListener(){
                        @Override
                        public void onClick(View v) {
                            MainController.submit(activity, "VIEW_DB");
                        }
                  })
        )
        .display();
    }


    @Override
    public OptionMenuBuilder defineMenu()
    {
        final TopActivity activity = this;

        // オプションメニューを構築
        return new OptionMenuBuilder(context)
            .add(
                new OptionMenuDescription()
                {
                    @Override
                    protected String displayText() {return "DB登録";}

                    @Override
                    protected void onSelected() {
                        // 画面遷移
                        MainController.submit(activity, "EDIT_DB");
                    }
                }
            )
            .add(
                new OptionMenuDescription()
                {
                    @Override
                    protected String displayText() {return "DB閲覧";}

                    @Override
                    protected void onSelected() {
                        // 画面遷移
                        MainController.submit(activity, "VIEW_DB");
                    }
                }
            )
        ;
    }


    @Override
    public void onBackPressed()
    {
        // 戻るキーが押されたら終了
        moveTaskToBack(true);
    }


}
