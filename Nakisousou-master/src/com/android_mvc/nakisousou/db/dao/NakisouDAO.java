package com.android_mvc.nakisousou.db.dao;

import java.util.List;

import com.android_mvc.nakisousou.db.entity.Nakisou;
import com.android_mvc.framework.db.DBHelper;
import com.android_mvc.framework.db.dao.BaseDAO;

import android.content.Context;

/**
 * 友達を読み書きするクラス。
 * @author id:language_and_engineering
 */
public class NakisouDAO extends BaseDAO<Nakisou>
{

    public NakisouDAO(Context context) {
        helper = new DBHelper(context);
    }


    // ------------ C --------------


    /**
     * 1人の友達を保存。
     */
    public Nakisou create(String name, Integer nakisoucount)
    {
        // 論理エンティティを構築
        Nakisou f = new Nakisou();
        f.setName(name);
        f.setNakisoucount(nakisoucount );

        // DB登録
        f.save(helper);

        return f;
    }


    // ------------ R --------------


    /**
     * 友達を全て新しい順に返す。
     */
    public List<Nakisou> findAll()
    {
        return findAll(helper, Nakisou.class);
    }


    /**
     * 特定のIDの友達を１人返す。
     */
    public Nakisou findById(Long friend_id)
    {
        return findById( helper, Nakisou.class, friend_id );
    }

        // NOTE: 細かい条件で検索したい場合は，Finderを利用すること。
        // findAllやfindByIdの実装を参照。


    // ------------ U --------------


    /**
     * 既存の友達のお気に入り状態を反転させる。
     */
    public Nakisou naitesou( Long friend_id )
    {
        // idをもとに検索
        Nakisou f = findById( friend_id );

        // フラグを反転する
        f.setNakisoucount(f.getNakisoucount() + 1);

        // DB更新
        f.save(helper);

        return f;
    }

    public Nakisou usoppoi( Long friend_id )
    {
        // idをもとに検索
        Nakisou f = findById( friend_id );

        // フラグを反転する
        f.setNakisoucount(f.getNakisoucount() - 1);

        // DB更新
        f.save(helper);

        return f;
    }

    // ------------ D --------------


    /**
     * 特定のIDの友達を削除。
     */
    public void deleteById( Long friend_id )
    {
        Nakisou f = findById(friend_id);

        // DBからの削除を実行
        f.delete(helper);
    }


}
