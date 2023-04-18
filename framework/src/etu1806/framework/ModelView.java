package etu1806.framework;

import java.util.HashMap;

public class ModelView {
    String view;
    HashMap<String,Object> data;

    public ModelView(String view) {
        setView(view);
    }

    public void addItems(String key ,Object value){
        this.data = new HashMap<>();
        this.data.put(key, value);
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public HashMap<String,Object> getData() {
        return data;
    }

    public void setData(HashMap<String,Object> data) {
        this.data = data;
    }
    

    
}
