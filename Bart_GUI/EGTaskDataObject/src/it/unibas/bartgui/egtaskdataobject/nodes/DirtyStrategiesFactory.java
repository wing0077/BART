/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unibas.bartgui.egtaskdataobject.nodes;

import bart.model.EGTask;
import bart.model.errorgenerator.operator.valueselectors.IDirtyStrategy;
import it.unibas.bartgui.egtaskdataobject.EGTaskDataObjectDataObject;
import it.unibas.bartgui.egtaskdataobject.notifier.DirtyStrategiesFactoryNotifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.openide.nodes.ChildFactory;
import org.openide.nodes.Node;
import speedy.model.database.AttributeRef;

/**
 *
 * @author Grandinetti Giovanni <grandinetti.giovanni13@gmail.com>
 */
public class DirtyStrategiesFactory extends ChildFactory.Detachable<String>  {
    
    private ChangeListener listener;
    
    private Map<String,Map<String,IDirtyStrategy>> map = new HashMap<>();
    private EGTaskDataObjectDataObject dto;
    private EGTask egt;
    

    public DirtyStrategiesFactory(EGTaskDataObjectDataObject dto, EGTask egt) {
        this.dto = dto;
        this.egt = egt;
    } 

    @Override
    protected boolean createKeys(List<String> list) {
        if(egt.getConfiguration().getDirtyStrategiesMap()== null)return true;
        Iterator<AttributeRef> it = egt.getConfiguration().getDirtyStrategiesMap().keySet().iterator();
        while(it.hasNext())   {
            AttributeRef att = it.next();
            if(map.containsKey(att.getTableName()) )   {
                map.get(att.getTableName()).put(att.getName(), egt.getConfiguration().getDirtyStrategy(att));
            }else{
                Map<String,IDirtyStrategy> tmpMap = new HashMap<>();
                tmpMap.put(att.getName(), egt.getConfiguration().getDirtyStrategy(att));
                map.put(att.getTableName(),tmpMap);
            }
        }
        list.addAll(map.keySet());
        return true;
    }
    
    @Override
    protected Node createNodeForKey(String key) {
        return new DirtyStrategyTableNode(egt, dto, key,map.get(key));
    }

    @Override
    protected void removeNotify() {
        DirtyStrategiesFactoryNotifier.removeChangeListener(listener);
    }

    @Override
    protected void addNotify() {
        DirtyStrategiesFactoryNotifier.addChangeListener(listener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                refresh(true);
            }
        });
    }
   
}
