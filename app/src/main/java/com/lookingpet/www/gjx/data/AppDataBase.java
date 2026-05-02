package com.lookingpet.www.gjx.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.lookingpet.www.gjx.cache.Cache;
import com.lookingpet.www.gjx.cache.CacheDao;
import com.lookingpet.www.gjx.cache.SearchDao;
import com.lookingpet.www.gjx.cache.SearchHistory;
import com.lookingpet.www.gjx.cache.StorageDrive;
import com.lookingpet.www.gjx.cache.StorageDriveDao;
import com.lookingpet.www.gjx.cache.VodCollect;
import com.lookingpet.www.gjx.cache.VodCollectDao;
import com.lookingpet.www.gjx.cache.VodRecord;
import com.lookingpet.www.gjx.cache.VodRecordDao;


/**
 * 类描述:
 *
 * @author pj567
 * @since 2020/5/15
 */
@Database(entities = {Cache.class, VodRecord.class, VodCollect.class, StorageDrive.class, SearchHistory.class}, version = 3)
public abstract class AppDataBase extends RoomDatabase {
    public abstract CacheDao getCacheDao();

    public abstract VodRecordDao getVodRecordDao();

    public abstract VodCollectDao getVodCollectDao();

    public abstract StorageDriveDao getStorageDriveDao();

    public abstract SearchDao getSearchDao();
}
