package com.raizlabs.android.dbflow.rx.language;

import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.list.FlowQueryList;
import com.raizlabs.android.dbflow.sql.language.BaseModelQueriable;
import com.raizlabs.android.dbflow.sql.language.CursorResult;
import com.raizlabs.android.dbflow.sql.queriable.Queriable;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;

import java.util.List;
import java.util.concurrent.Callable;

import rx.Observable;

import static rx.Observable.fromCallable;

/**
 * Description:
 */

public abstract class BaseRXModelQueriable<T> extends BaseRXQueriable<T> implements RXModelQueriable<T> {

    public BaseRXModelQueriable(Class<T> table) {
        super(table);
    }

    protected abstract BaseModelQueriable<T> getInnerModelQueriable();

    @Override
    protected Queriable getInnerQueriable() {
        return getInnerModelQueriable();
    }

    @Override
    public Observable<CursorResult<T>> queryResults() {
        return fromCallable(new Callable<CursorResult<T>>() {
            @Override
            public CursorResult<T> call() throws Exception {
                return getInnerModelQueriable().queryResults();
            }
        });
    }

    @Override
    public Observable<List<T>> queryList() {
        return fromCallable(new Callable<List<T>>() {
            @Override
            public List<T> call() throws Exception {
                return getInnerModelQueriable().queryList();
            }
        });
    }

    @Override
    public Observable<List<T>> queryList(final DatabaseWrapper wrapper) {
        return fromCallable(new Callable<List<T>>() {
            @Override
            public List<T> call() throws Exception {
                return getInnerModelQueriable().queryList(wrapper);
            }
        });
    }

    @Override
    public Observable<T> querySingle() {
        return fromCallable(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return getInnerModelQueriable().querySingle();
            }
        });
    }

    @Override
    public Observable<T> querySingle(final DatabaseWrapper wrapper) {
        return fromCallable(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return getInnerModelQueriable().querySingle(wrapper);
            }
        });
    }

    @Override
    public Class<T> getTable() {
        return getInnerModelQueriable().getTable();
    }

    @Override
    public Observable<FlowCursorList<T>> cursorList() {
        return fromCallable(new Callable<FlowCursorList<T>>() {
            @Override
            public FlowCursorList<T> call() throws Exception {
                return getInnerModelQueriable().cursorList();
            }
        });
    }

    @Override
    public Observable<FlowQueryList<T>> flowQueryList() {
        return fromCallable(new Callable<FlowQueryList<T>>() {
            @Override
            public FlowQueryList<T> call() throws Exception {
                return getInnerModelQueriable().flowQueryList();
            }
        });
    }

    @Override
    public <TQueryModel> Observable<List<TQueryModel>> queryCustomList(
            final Class<TQueryModel> tQueryModelClass) {
        return fromCallable(new Callable<List<TQueryModel>>() {
            @Override
            public List<TQueryModel> call() throws Exception {
                return getInnerModelQueriable().queryCustomList(tQueryModelClass);
            }
        });
    }

    @Override
    public <TQueryModel> Observable<TQueryModel> queryCustomSingle(
            final Class<TQueryModel> tQueryModelClass) {
        return fromCallable(new Callable<TQueryModel>() {
            @Override
            public TQueryModel call() throws Exception {
                return getInnerModelQueriable().queryCustomSingle(tQueryModelClass);
            }
        });
    }

    @Override
    public RXModelQueriable<T> disableCaching() {
        getInnerModelQueriable().disableCaching();
        return this;
    }

}
