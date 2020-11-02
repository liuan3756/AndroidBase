package com.liuan.android.base.mvp;

import java.util.HashMap;

/**
 * @author Peach Parrot
 * @date 2020年09月28日 17:17
 */
public class ModelManager
{
    private static ModelManager instance;

    private HashMap<Class<? extends BaseModel>, BaseModel> models = new HashMap<>();

    private ModelManager()
    {

    }

    public static ModelManager getInstance()
    {
        if (instance == null)
        {
            instance = new ModelManager();
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public <Model extends BaseModel> Model getModel(Class<Model> modelClass)
    {
        BaseModel model = models.get(modelClass);
        if (model == null)
        {
            try
            {
                model = modelClass.newInstance();
                models.put(modelClass, (BaseModel) model);
            }
            catch (IllegalAccessException | InstantiationException e)
            {
                e.printStackTrace();
            }
        }
        return (Model) model;
    }
} 