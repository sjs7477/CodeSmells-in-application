package com.android_mvc.nakisousou.db.entity;

import android.content.ContentValues;
import android.database.Cursor;

import com.android_mvc.nakisousou.db.entity.lib.LPUtil;
import com.android_mvc.nakisousou.db.entity.lib.LogicalEntity;

/**
 * 友達１人を表す論理エンティティ。
 * @author id:language_and_engineering
 *
 */
public class Nakisou extends LogicalEntity<Nakisou>
{
    // Intent経由でエンティティを運搬可能にするために
    private static final long serialVersionUID = 1L;

    @Override
    public String tableName(){return "nakisou";}

    @Override
    public final String[] columns(){
        return new String[]{ "id", "name", "nakisoucount" };
    }


    // メンバ
    private String name = null;
    private Integer nakisoucount = null;


    // IDEが自動生成したG&S


    public String getName() {
        return name;
    }

    public Integer getNakisoucount() {
        return nakisoucount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNakisoucount(Integer nakisoucount) {
        this.nakisoucount = nakisoucount;
    }


    // カスタムG&S


    /**
     * 友達に自分の説明をさせる
     */
    public String getDescription()
    {
    	StringBuilder sb = new StringBuilder();
    	sb.append("id:").append(getId()).append(",").append(getName()).append("は");
    	if( getNakisoucount() > 10){
    		sb.append("なきそう");
    	}else{
    		sb.append("なきそうでもない");    	
    	}
        return sb.toString();
    }


    // ----- LP変換(Logical <-> Physical) -----


    /**
     * DBの格納値から論理エンティティを構成
     */
    @Override
    public Nakisou logicalFromPhysical(Cursor c)
    {
        setId(c.getLong(0));
        setName(c.getString(1));
        setNakisoucount(c.getInt(2) );
        return this;
    }


    /**
     * 自身をDBに新規登録可能なデータ型に変換して返す
     */
    @Override
    protected ContentValues toPhysicalEntity(ContentValues values)
    {
        // entityをContentValueに変換

        if( getId() != null)
        {
            values.put("id", getId());
        }
        values.put("name", getName());
        values.put("nakisoucount", getNakisoucount());

        return values;
    }


}
