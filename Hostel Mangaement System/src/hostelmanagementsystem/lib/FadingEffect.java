package hostelmanagementsystem.lib;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class FadingEffect implements ActionListener{
    float factor;
    private float alpha=0.0f;
    private String type=null,panelName=null;
    private long start=0;
    private LoginPanel loginPanel;
    private boolean glassPanelFading=false;
    private StartUpPanelWithAdminLogin startUpPanelWithAdminLogin;
    private StartUpPanelWithoutLogin startUpPanelWithoutLogin=null;
    private StartUpPanelWithStudentLogin startUpPanelWithStudentLogin=null;
    private scrollablePanel scrlPanel=null;
    private IndividualAccounts individualAccounts=null;
    private AdminAccountsPanel adminAccountsPanel=null;
    public FadingEffect(float factor,String type,LoginPanel pane,String panelName){
        this(factor,type);
        this.loginPanel=pane;
        this.panelName=panelName;
    }
    public FadingEffect(float factor,String type,StartUpPanelWithAdminLogin pane,String panelName){
        this(factor,type);
        this.startUpPanelWithAdminLogin=pane;
        this.panelName=panelName;
    }
    public FadingEffect(float factor,String type,StartUpPanelWithStudentLogin pane,String panelName){
        this(factor,type);
        this.startUpPanelWithStudentLogin=pane;
        this.panelName=panelName;
    }
    public FadingEffect(float factor,String type,StartUpPanelWithoutLogin pane,String panelName){
        this(factor,type);
        this.startUpPanelWithoutLogin=pane;
        this.panelName=panelName;
    }
    public FadingEffect(float factor,String type,scrollablePanel pane,String panelName){
        this(factor,type);
        this.scrlPanel=pane;
        this.panelName=panelName;
    }
    public FadingEffect(float factor,String type,IndividualAccounts pane,String panelName){
        this(factor,type);
        this.individualAccounts=pane;
        this.panelName=panelName;
    }
    public FadingEffect(float factor,String type,AdminAccountsPanel pane,String panelName){
        this(factor,type);
        this.adminAccountsPanel=pane;
        this.panelName=panelName;
    }
    
    
    public void setGlassPaneFading(boolean val){
        glassPanelFading=val;
    }
    public FadingEffect(float factor,String type){
        this.factor=factor;
        this.type=type;
        if(type.equals("IN")){
            alpha=0.0f;
        }
        else{
            alpha=1.0f;
        }
    }
    public void actionPerformed(ActionEvent acev){
        if(start==0){
            start=System.currentTimeMillis();
        }
        if(type.equals("IN")){
            alpha=(System.currentTimeMillis()-start)/factor;
            if(alpha>1.0f){
                alpha=1.0f;
            }
            if(panelName.equals("LoginPanel")){
                loginPanel.alphaLevel=alpha;
                loginPanel.repaint();
            }
            if(panelName.equals("StartUpPanelWithAdminLogin")){
                startUpPanelWithAdminLogin.alphaLevel=alpha;
                startUpPanelWithAdminLogin.repaint();
            }
            else if(panelName.equals("StartUpPanelWithStudentLogin")){
                startUpPanelWithStudentLogin.alphaLevel=alpha;
                startUpPanelWithStudentLogin.repaint();
            }
            else if(panelName.equals("StartUpPanelWithoutLogin")){
                startUpPanelWithoutLogin.alphaLevel=alpha;
                startUpPanelWithoutLogin.repaint();
            }
            else if(panelName.equals("scrollablePanel")){
                scrlPanel.alphaLevel=alpha;
                scrlPanel.repaint();
            }
            else if(panelName.equals("IndividualAccountsPanel")){
                individualAccounts.alphaLevel=alpha;
                individualAccounts.repaint();
            }
            else if(panelName.equals("AdminAccountsPanel")){
                adminAccountsPanel.alphaLevel=alpha;
                adminAccountsPanel.repaint();
            }
            if(glassPanelFading && Globals.currentGlassPane.isVisible()){
                Globals.currentGlassPane.alphaLevel=alpha/2.0f;
                Globals.currentGlassPane.repaint();
            }
        }
        else{
            alpha=1-(System.currentTimeMillis()-start)/factor;
            if(alpha<0.0f){
                alpha=0.0f;
            }
            if(panelName.equals("LoginPanel")){
                loginPanel.alphaLevel=alpha;
                loginPanel.repaint();
                Globals.startUpPanelWithoutLogin.alphaLevel=alpha;
                Globals.startUpPanelWithoutLogin.repaint();
                
                if(glassPanelFading && Globals.currentGlassPane.isVisible()){
                    Globals.currentGlassPane.alphaLevel=alpha/2.0f;
                    if(alpha==0.0f){
                        if(Globals.TypeOfLogin.equals("ADL")){
                            Globals.mainApp.remove(Globals.startUpPanelWithoutLogin);
                            Globals.mainApp.ToggleStartUpPaneltoWithAdminLogin();
                        }
                        else if(Globals.TypeOfLogin.equals("STL")){
                            Globals.mainApp.remove(Globals.startUpPanelWithoutLogin);
                            Globals.mainApp.ToggleStartUpPaneltoWithStudentLogin();
                        }
                    }
                    else
                        Globals.currentGlassPane.repaint();
                }
            }
            if(panelName.equals("StartUpPanelWithAdminLogin")){
                startUpPanelWithAdminLogin.alphaLevel=alpha;
                startUpPanelWithAdminLogin.repaint();
                if(alpha==0.0f)
                    Globals.startUpPanelWithoutLogin.startFading("OUT");
            }
            if(panelName.equals("StartUpPanelWithStudentLogin")){
                startUpPanelWithStudentLogin.alphaLevel=alpha;
                startUpPanelWithStudentLogin.repaint();
            }
            if(panelName.equals("StartUpPanelWithoutLogin")){
                startUpPanelWithoutLogin.alphaLevel=alpha;
                startUpPanelWithoutLogin.repaint();
            }
            else if(panelName.equals("scrollablePanel")){
                scrlPanel.alphaLevel=alpha;
                scrlPanel.repaint();
            }
            else if(panelName.equals("IndividualAccountsPanel")){
                individualAccounts.alphaLevel=alpha;
                individualAccounts.repaint();
                if(glassPanelFading && Globals.currentGlassPane.isVisible()){
                    Globals.currentGlassPane.alphaLevel=alpha/2.0f;
                    Globals.currentGlassPane.repaint();
                }
            }
            else if(panelName.equals("AdminAccountsPanel")){
                adminAccountsPanel.alphaLevel=alpha;
                adminAccountsPanel.repaint();
            }
        }
    }
}
