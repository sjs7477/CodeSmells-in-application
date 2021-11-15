package com.android_mvc.nakisousou.activities.func_db;

import java.util.List;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;

import com.android_mvc.nakisousou.common.Util;
import com.android_mvc.nakisousou.controller.FuncDBController;
import com.android_mvc.nakisousou.db.dao.NakisouDAO;
import com.android_mvc.nakisousou.db.entity.Nakisou;
import com.android_mvc.framework.activities.base.BaseNormalActivity;
import com.android_mvc.framework.ui.UIBuilder;
import com.android_mvc.framework.ui.view.MButton;
import com.android_mvc.framework.ui.view.MLinearLayout;
import com.android_mvc.framework.ui.view.MTextView;

/**
 * サンプルのDB参照アクティビティ。
 * @author id:language_and_engineering
 *
 */
public class DBListActivity extends BaseNormalActivity {

    MLinearLayout layout1;
    MTextView tv1;
    MTextView tv2;


    // 全友達のリスト
    List<Nakisou> friends;


    @Override
    public boolean requireProcBeforeUI(){
        // UI構築前に処理を要求する
        return true;
    }


    // UI構築前に別スレッドで実行される処理
    @Override
    public void procAsyncBeforeUI(){
        //
        Util.d("UI構築前に実行される処理です。");

        // 全FriendをDBからロード
        friends = new NakisouDAO(this).findAll();
    }


    @Override
    public void defineContentView() {
        final DBListActivity activity = this;

        // まず親レイアウトを定義
        new UIBuilder(context)
        .add(
            layout1 = new MLinearLayout(context)
              .orientationVertical()
              .widthFillParent()
              .heightWrapContent()
              .add(

                tv1 = new MTextView(context)
                  .text("ここにDBの中身が列挙されます。" )
                  .widthWrapContent()
                  .paddingPx(10)
                ,

                tv2 = new MTextView(context)
                  .invisible()
                  .textColor(Color.RED)
                  .widthWrapContent()
                  .paddingPx(10)

              )
        )
        .display();


        // レイアウト内に動的に全友達の情報を表示。Adapterは不要。
        for( final Nakisou f : friends )
        {
            // ※↑friendをfinal宣言してるのは，Clickイベントの中から参照する事が目的

            // お気に入り状態を反転するボタン
            String naitesou_button_text = "この子、泣いてそう";
            String usoppoi_button_text = "この子、泣いてないわ";

            layout1.add(
            		new MLinearLayout(context)
            		.orientationVertical()
            		.widthFillParent()
            		.heightWrapContent()
            		.paddingPx(10)
            		.add(
                        new MTextView(context)
                        .text( f.getDescription() ) // この友達の説明を取得
                        .widthWrapContent()
                        ,
	
		                // 水平方向のレイアウトを１個追加
		                new MLinearLayout(context)
		                    .orientationHorizontal()
		                    .widthFillParent()
		                    .heightWrapContent()
		                    .paddingPx(10)
		                    .add(
	
		                        new MButton(context)
		                            .text(naitesou_button_text)
		                            .click(new OnClickListener(){
		
		                                  @Override
		                                  public void onClick(View v) {
		                                      // DB更新へ
		                                      FuncDBController.submit(activity, "UPDATE_NAITESOU", f.getId());
		                                  }
		
		                            })
		                        ,
		                        new MButton(context)
		                        .text(usoppoi_button_text)
		                        .click(new OnClickListener(){
		
		                              @Override
		                              public void onClick(View v) {
		                                  // DB更新へ
		                                  FuncDBController.submit(activity, "UPDATE_USOPPOI", f.getId());
		                              }
		
		                        })
		                    ,
		
		                        new MButton(context)
		                            .text("削除")
		                            .click(new OnClickListener(){
		
		                                  @Override
		                                  public void onClick(View v) {
		                                      // DBから削除へ
		                                      FuncDBController.submit(activity, "DELETE_FRIEND", f.getId());
		                                  }
		
		                            })
		                    )
		            )
		            );
        }

        // 戻るボタン
        layout1.add(
            new MButton(context)
              .text("トップに戻る")
              .click(new OnClickListener(){

                  @Override
                  public void onClick(View v) {
                      FuncDBController.submit(activity, "BACK_TO_TOP", null);
                  }

              })
        );

        // 描画
        layout1.inflateInside();


        // 友達登録操作の直後の場合
        if( $.hasActionResult() )
        {
            showRegisteredNewFriend();
        }
    }


    // 新規登録されたばかりの新しい友達情報を表示
    private void showRegisteredNewFriend() {
        if( $.actionResultHasKey( "new_friend_obj" ) )
        {
            // Intentから情報を取得
            Nakisou f = (Nakisou)($.getActionResult().get("new_friend_obj"));

            // UIに表示
            tv2.text(f.getName() + "さんが，たった今新規登録されました。").visible();
        }
    }


}