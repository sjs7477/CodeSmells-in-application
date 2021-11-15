package com.android_mvc.nakisousou.db.schema;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.android_mvc.framework.db.schema.AbstractSchemaDefinition;
import com.android_mvc.framework.db.schema.RDBColumn;
import com.android_mvc.framework.db.schema.RDBTable;
import com.android_mvc.nakisousou.db.entity.Nakisou;
import com.android_mvc.nakisousou.db.entity.LocationLog;

/**
 * AP側のテーブル構造と初期データを定義。
 * @author id:language_and_engineering
 *
 */
public class SchemaDefinition extends AbstractSchemaDefinition
{
    // NOTE:
    // ・各テーブルの主キーは「id」。
    // ・SQLiteのカラムで定義可能な型については，下記を参照
    //     http://www.sqlite.org/datatype3.html
    // ・アプリのインストール（初期化）アクティビティから呼び出される。

    @Override
    public void define_tables()
    {

        // サンプル用の友達テーブル
        new RDBTable(this)
            .nameIs( new Nakisou().tableName() )
            .columns(new RDBColumn[]{
                new RDBColumn().nameIs("id").primaryKey(),
                new RDBColumn().nameIs("name").comment("名前").typeIs("text").notNull(),
                new RDBColumn().nameIs("nakisoucount").comment("泣きそう回数").typeIs("integer")
            })
            .create()
        ;
    }


    @Override
    public void init_db_data(SQLiteDatabase db, Context context)
    {
    }

}
