package com.d3if0028.hitungbmi.db;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class BmiDao_Impl implements BmiDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<BmiEntity> __insertionAdapterOfBmiEntity;

  public BmiDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfBmiEntity = new EntityInsertionAdapter<BmiEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `bmi` (`id`,`tanggal`,`berat`,`tinggi`,`isMale`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, BmiEntity value) {
        stmt.bindLong(1, value.getId());
        stmt.bindLong(2, value.getTanggal());
        stmt.bindDouble(3, value.getBerat());
        stmt.bindDouble(4, value.getTinggi());
        final int _tmp;
        _tmp = value.isMale() ? 1 : 0;
        stmt.bindLong(5, _tmp);
      }
    };
  }

  @Override
  public void insert(final BmiEntity bmi) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfBmiEntity.insert(bmi);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public LiveData<List<BmiEntity>> getLastBmi() {
    final String _sql = "SELECT * FROM bmi ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"bmi"}, false, new Callable<List<BmiEntity>>() {
      @Override
      public List<BmiEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTanggal = CursorUtil.getColumnIndexOrThrow(_cursor, "tanggal");
          final int _cursorIndexOfBerat = CursorUtil.getColumnIndexOrThrow(_cursor, "berat");
          final int _cursorIndexOfTinggi = CursorUtil.getColumnIndexOrThrow(_cursor, "tinggi");
          final int _cursorIndexOfIsMale = CursorUtil.getColumnIndexOrThrow(_cursor, "isMale");
          final List<BmiEntity> _result = new ArrayList<BmiEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final BmiEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpTanggal;
            _tmpTanggal = _cursor.getLong(_cursorIndexOfTanggal);
            final float _tmpBerat;
            _tmpBerat = _cursor.getFloat(_cursorIndexOfBerat);
            final float _tmpTinggi;
            _tmpTinggi = _cursor.getFloat(_cursorIndexOfTinggi);
            final boolean _tmpIsMale;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsMale);
            _tmpIsMale = _tmp != 0;
            _item = new BmiEntity(_tmpId,_tmpTanggal,_tmpBerat,_tmpTinggi,_tmpIsMale);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }
}
