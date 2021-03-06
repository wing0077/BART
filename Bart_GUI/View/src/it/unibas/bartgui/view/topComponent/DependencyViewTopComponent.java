/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unibas.bartgui.view.topComponent;

import bart.model.EGTask;
import bart.model.dependency.Dependency;
import it.unibas.bartgui.egtaskdataobject.EGTaskDataObjectDataObject;
import it.unibas.bartgui.egtaskdataobject.notifier.VioGenQueryNodeNotify;
import it.unibas.bartgui.resources.R;
import it.unibas.bartgui.view.ViewResource;
import it.unibas.bartgui.view.panel.editor.Dependency.DependencyOpenPanel;
import it.unibas.bartgui.view.panel.editor.Dependency.parseHtml.ParseDependency;
import it.unibas.bartgui.view.panel.editor.Dependency.parseHtml.ParseVioGenQueries;
import it.unibas.bartgui.view.panel.editor.Dependency.tableModel.VioGenQueryTableModel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(
        dtd = "-//it.unibas.bartgui.view.topComponent//DependencyView//EN",
        autostore = false
)
@TopComponent.Description(
        preferredID = "DependencyViewTopComponent",
        iconBase= R.IMAGE_NODE_DCS, 
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "it.unibas.bartgui.view.topComponent.DependencyViewTopComponent")
@ActionReference(path = "Menu/Window" , position = 20 )
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_DependencyViewAction",
        preferredID = "DependencyViewTopComponent"
)
@Messages({
    "CTL_DependencyViewAction=Dependency View",
    "CTL_DependencyViewTopComponent=Dependency View",
    "HINT_DependencyViewTopComponent=This is a DependencyView window"
})
public final class DependencyViewTopComponent extends TopComponent {

    private DependencyOpenPanel panel;
    private JScrollPane scrolDependency;
    private Dependency dependency = null;
    
    private Lookup.Result<Dependency> result;
    private final LookupListenerDependency listener = new LookupListenerDependency();
    
    public DependencyViewTopComponent() {
        setDisplayName(Bundle.CTL_DependencyViewAction());
        setName(ViewResource.TOP_NAME_DependencyViewTopComponent);
        setToolTipText(Bundle.HINT_DependencyViewTopComponent());
        setLayout(new BorderLayout());
        scrolDependency = new JScrollPane();
        add(scrolDependency,BorderLayout.CENTER);
        associateLookup(Lookup.EMPTY);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        result = Utilities.actionsGlobalContext().lookupResult(Dependency.class);
        result.addLookupListener(listener);
        listener.resultChanged(null);
    }

    @Override
    public void componentClosed() {
        result.removeLookupListener(listener);
    }

    @Override
    protected void componentActivated() {
        super.componentActivated();
    }

    
    @Override
    protected void componentShowing() {
        super.componentShowing();
    }
    
    

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
    
    private class LookupListenerDependency implements LookupListener   {
        @Override
        public void resultChanged(LookupEvent ev) {
            Dependency tmp = Utilities.actionsGlobalContext().lookup(Dependency.class);
            if(tmp != null)   {
                if(dependency != null)  {
                    if(dependency.equals(tmp))return;
                }
                dependency = tmp;
                EGTaskDataObjectDataObject dto = Utilities.actionsGlobalContext().lookup(EGTaskDataObjectDataObject.class);
                if(dto == null)return;
                EGTask egt = dto.getEgtask();
                ParseDependency parser = new ParseDependency(dependency);
                panel = new  DependencyOpenPanel();
                panel.getPanelDependecy().setTextLabelDependency(parser.parse());
                
                ParseVioGenQueries parseVGQ = new ParseVioGenQueries(parser.getVariablesColorMap(), dependency, egt);
                VioGenQueryTableModel model = new VioGenQueryTableModel(dto,parseVGQ.getVioGenQueriesData().getVioGQVector());
                model.addTableModelListener(new TableListener());
                panel.getPanelVioGenQueriesWPanel().bind(model);
                scrolDependency.setViewportView(panel);
                panel.getPanelDependecy().animateBackground();  
                panel.getPanelVioGenQueriesWPanel().fade();
            }
        }       
    }
    
    private class TableListener implements TableModelListener   {
        @Override
        public void tableChanged(TableModelEvent e) {
            VioGenQueryNodeNotify.fire();
            if(panel != null)   {
                panel.getPanelVioGenQueriesWPanel().race();
            }
        }
        
    }
}
