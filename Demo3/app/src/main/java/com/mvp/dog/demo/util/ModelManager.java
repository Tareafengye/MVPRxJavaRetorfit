package com.mvp.dog.demo.util;




import com.mvp.dog.demo.base.IBaseModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * aouthor: 天天
 * Github: https://github.com/Tareafengye
 * Date: 2019/3/19
 * ModelManager: Model 创建管理
 */
public final class ModelManager {

    private final ConcurrentHashMap<Class<? extends IBaseModel>, ? extends IBaseModel> DATA_CACHE;

    private ModelManager() {
        DATA_CACHE = new ConcurrentHashMap<>(8);
    }

    /**
     * @return ModelManager单例实例
     */
    public static ModelManager getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final ModelManager INSTANCE = new ModelManager();
    }

    /**
     * 创建获取 Model 层实例
     * @param clazz IBaseModel 子类 class
     */
    public <M extends IBaseModel> M create(final Class<M> clazz) {

        IBaseModel model = DATA_CACHE.get(clazz);
        if (model != null) {
            return (M) model;
        }

        synchronized (DATA_CACHE) {
            model = DATA_CACHE.get(clazz);
            if (model == null) {
                try {
                    Constructor<M> constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    model = constructor.newInstance();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return (M) model;
    }

}
