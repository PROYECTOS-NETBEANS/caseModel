/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Control.control;
import Negocio.clsColumna;
import Negocio.clsRelacion;
import Negocio.clsTabla;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public class frmRelacion extends JDialog {

    private control objcontrol = null;
    private clsRelacion objrelacion = null;
    private boolean esNuevo = false; 
    private LinkedList<clsTabla> tablas = null;
    
    public frmRelacion() {
        initComponents();
    }
    public frmRelacion(Frame parent, String title, boolean modal){
        super(parent, title, modal);
        initComponents();
        cmborigentbl.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    cargarColumnsOrigen(cmborigentbl.getSelectedIndex());
                }
            }
        }) ;
        cmbdesttbl.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    cargarColumnsDestino(cmbdesttbl.getSelectedIndex());
                }                
            }
        }) ;
    }
    /**
     * Metodo que carga las columnas de la tabla del index
     * @param indexTabla 
     */
    public void cargarColumnsOrigen(int indexTabla){
        cmborigencol.removeAllItems();
        LinkedList<clsColumna> columnas = tablas.get(indexTabla).getColumnas();        
        for (clsColumna columna : columnas) {
            cmborigencol.addItem(columna.getNombre());
        }
    }
    /**
     * Metodo que carga las columnas de la tabla del index
     * @param indexTabla 
     */
    public void cargarColumnsDestino(int indexTabla){
        cmbdestcol.removeAllItems();
        LinkedList<clsColumna> columnas = tablas.get(indexTabla).getColumnas();
        for (clsColumna columna : columnas) {
            cmbdestcol.addItem(columna.getNombre());
        }
    }

    public void cargarRelacion(control control, clsRelacion relacion, boolean esNuevo){
        this.objcontrol = control;
        this.objrelacion = relacion;
        this.esNuevo = esNuevo;
        cargarTablas(this.objcontrol.getObjds().getTablas());        
        
        if(!this.esNuevo)
            mostrarDatos();        
    }
    private void mostrarDatos(){
        //origen
        for (int i = 0; i < tablas.size(); i++) {
            if(tablas.get(i).getId() == objrelacion.getOrigen()){ // si la id del origen es igual a una de las tablas
                cmborigentbl.setSelectedIndex(i);
                cmborigencol.setSelectedItem(objrelacion.getCol_nombre_origen());
            }
        }
        
        for (int i = 0; i < tablas.size(); i++) {
            if(tablas.get(i).getId() == objrelacion.getDestino()){ // si la id del destino es igual a una de las tablas
                cmbdesttbl.setSelectedIndex(i);
                cmbdestcol.setSelectedItem(objrelacion.getCol_nombre_destino());                
            }
        }
        txtorigencard.setText(objrelacion.getCardinalidad_origen());
        txtdestcard.setText(objrelacion.getCardinalidad_destino());
    }
    private void cargarTablas(LinkedList<clsTabla> tbls){
        tablas = new LinkedList<>();
        cmborigentbl.removeAllItems();
        cmbdesttbl.removeAllItems();
        for (clsTabla tabla : tbls) {
            tablas.add(tabla);
            cmborigentbl.addItem(tabla.getNombreTabla());
            cmbdesttbl.addItem(tabla.getNombreTabla());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmborigentbl = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cmborigencol = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cmbdesttbl = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbdestcol = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtorigencard = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtdestcard = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        jLabel1.setText("Columna:");

        cmborigentbl.setModel(new javax.swing.DefaultComboBoxModel<>());

        jLabel2.setText("Tabla:");

        cmborigencol.setModel(new javax.swing.DefaultComboBoxModel<>());

        jLabel3.setText("Tabla:");

        cmbdesttbl.setModel(new javax.swing.DefaultComboBoxModel<>());

        jLabel4.setText("Columna:");

        cmbdestcol.setModel(new javax.swing.DefaultComboBoxModel<>());

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setText("TABLA ORIGEN");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("TABLA DESTINO");

        jLabel7.setText("Card.:");

        txtorigencard.setText("*");

        jLabel8.setText("Card.:");

        txtdestcard.setText("1");

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmborigentbl, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtorigencard, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmborigencol, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnAceptar)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel5)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbdesttbl, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(22, 22, 22))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtdestcard, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbdestcol, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(btnCancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbdesttbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbdestcol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmborigentbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cmborigencol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtorigencard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtdestcard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(61, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try {
            validar();
            this.guardarRelacion();
            this.setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAceptarActionPerformed
    private void guardarRelacion(){
        clsTabla objorigen = tablas.get(cmborigentbl.getSelectedIndex());
        clsTabla objdestino = tablas.get(cmbdesttbl.getSelectedIndex());
        int origen = objorigen.getId();
        int destino = objdestino.getId();
        
        int newoX = objorigen.getSuperior().getLocation().x + (((int) objorigen.getSuperior().getWidth()/2));
        int newoY = objorigen.getSuperior().getLocation().y + (((int) objorigen.getSuperior().getHeight()/2));
        Point o = new Point(newoX, newoY);
        
        int newdX = objdestino.getSuperior().getLocation().x + (((int) objdestino.getSuperior().getWidth()/2));
        int newdY = objdestino.getSuperior().getLocation().y + (((int) objdestino.getSuperior().getHeight()/2));
        Point d = new Point(newdX, newdY);

        String col_origen = cmborigencol.getSelectedItem().toString();
        String col_destino = cmbdestcol.getSelectedItem().toString();
        if(esNuevo){
            objrelacion = new clsRelacion(0, origen, destino, o, d, col_origen, col_destino, txtorigencard.getText(), txtdestcard.getText());
            objcontrol.addRelacion(objrelacion);
        }else{ // es relacion para editar
            objrelacion.setOrigen(origen);
            objrelacion.setDestino(destino);
            objrelacion.setPuntoO(o);
            objrelacion.setPuntoD(d);
            objrelacion.setCol_nombre_origen(col_origen); 
            objrelacion.setCol_nombre_destino(col_destino); 
            objrelacion.setCardinalidad_origen(txtorigencard.getText()); 
            objrelacion.setCardinalidad_destino(txtdestcard.getText());
            objcontrol.enviarActualizarRelacion(objrelacion);            
        }
    }
    private void validar() throws Exception{
        String m = "";
        if(cmborigentbl.getSelectedIndex() == cmbdesttbl.getSelectedIndex()){
            m += "Tabla origen y destino no pueden ser iguales \n";
        }else{
            if((cmborigentbl.getSelectedIndex() < 0) || (cmbdesttbl.getSelectedIndex() < 0))
                m += "Por favor seleccione una tabla \n";
            else
                if((cmborigencol.getSelectedIndex() < 0 ) || (cmbdestcol.getSelectedIndex() < 0))
                    m += "Por favor seleccione una columna \n";
        }        
        
        if(m.length() > 0)
            throw new Exception(m);
    }
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox<String> cmbdestcol;
    private javax.swing.JComboBox<String> cmbdesttbl;
    private javax.swing.JComboBox<String> cmborigencol;
    private javax.swing.JComboBox<String> cmborigentbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtdestcard;
    private javax.swing.JTextField txtorigencard;
    // End of variables declaration//GEN-END:variables

}
