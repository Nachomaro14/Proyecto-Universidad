package Controlador;

import ModeloLetras.ModeloMySQL;
import ModeloCiencias.ModeloSQLite;
import Vista.Interfaz;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Controlador implements ActionListener, MouseListener{
    
    Interfaz vista;
    ModeloMySQL modeloMySQL = new ModeloMySQL();
    ModeloSQLite modeloSQLite = new ModeloSQLite();
    int ciencias = 0;
    int letras = 1;
    
    public Controlador(Interfaz i){
        vista = i;
    }
    
    public enum AccionMVC{
        btnLetras,
        btnCiencias,
        btnSalirInicio,
        
        btnNuevaMatricula,
        btnConsultas,
        btnSalir,
        
        btnAnadirAsignaturaMatricula,
        btnCompletarMatricula,
        btnCancelarMatricula,
        
        btnVolver,
        
        btnModificarNota,
        
        btnAnadirAsignatura,
        btnModificarAsignatura,
        btnEliminarAsignatura,
        
        btnAnadirProfesor,
        btnModificarProfesor,
        btnEliminarProfesor,
        
        btnAnadirAula,
        btnModificarAula,
        btnEliminarAula,
        
        btnAsignar,
        btnEliminarAsignacion
    }
    
    public void iniciar(){
        
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vista);
            
            vista.pack();
            vista.setLocationRelativeTo(null);
            vista.setVisible(true);
            vista.setTitle("Gestión de matrículas e información");
            
        } catch (UnsupportedLookAndFeelException ex){}
          catch (ClassNotFoundException ex){}
          catch (InstantiationException ex){}
          catch (IllegalAccessException ex){}
        
        this.vista.btnLetras.setActionCommand("btnLetras");
        this.vista.btnLetras.addActionListener(this);
        this.vista.btnCiencias.setActionCommand("btnCiencias");
        this.vista.btnCiencias.addActionListener(this);
        this.vista.btnSalirInicio.setActionCommand("btnSalirInicio");
        this.vista.btnSalirInicio.addActionListener(this);
        
        this.vista.btnNuevaMatricula.setActionCommand("btnNuevaMatricula");
        this.vista.btnNuevaMatricula.addActionListener(this);
        this.vista.btnConsultas.setActionCommand("btnConsultas");
        this.vista.btnConsultas.addActionListener(this);
        this.vista.btnSalir.setActionCommand("btnSalir");
        this.vista.btnSalir.addActionListener(this);
        
        this.vista.btnAnadirAsignaturaMatricula.setActionCommand("btnAnadirAsignaturaMatricula");
        this.vista.btnAnadirAsignaturaMatricula.addActionListener(this);
        this.vista.btnCompletarMatricula.setActionCommand("btnCompletarMatricula");
        this.vista.btnCompletarMatricula.addActionListener(this);
        this.vista.btnCancelarMatricula.setActionCommand("btnCancelarMatricula");
        this.vista.btnCancelarMatricula.addActionListener(this);
        
        this.vista.btnVolver.setActionCommand("btnVolver");
        this.vista.btnVolver.addActionListener(this);
        
        this.vista.btnModificarNota.setActionCommand("btnModificarNota");
        this.vista.btnModificarNota.addActionListener(this);
        
        this.vista.btnAnadirAsignatura.setActionCommand("btnAnadirAsignatura");
        this.vista.btnAnadirAsignatura.addActionListener(this);
        this.vista.btnModificarAsignatura.setActionCommand("btnModificarAsignatura");
        this.vista.btnModificarAsignatura.addActionListener(this);
        this.vista.btnEliminarAsignatura.setActionCommand("btnEliminarAsignatura");
        this.vista.btnEliminarAsignatura.addActionListener(this);
        
        this.vista.btnAnadirProfesor.setActionCommand("btnAnadirProfesor");
        this.vista.btnAnadirProfesor.addActionListener(this);
        this.vista.btnModificarProfesor.setActionCommand("btnModificarProfesor");
        this.vista.btnModificarProfesor.addActionListener(this);
        this.vista.btnEliminarProfesor.setActionCommand("btnEliminarProfesor");
        this.vista.btnEliminarProfesor.addActionListener(this);
        
        this.vista.btnAnadirAula.setActionCommand("btnAnadirAula");
        this.vista.btnAnadirAula.addActionListener(this);
        this.vista.btnModificarAula.setActionCommand("btnModificarAula");
        this.vista.btnModificarAula.addActionListener(this);
        this.vista.btnEliminarAula.setActionCommand("btnEliminarAula");
        this.vista.btnEliminarAula.addActionListener(this);
        
        this.vista.tablaMatriculas.addMouseListener(this);
        this.vista.tablaMatriculas.getTableHeader().setReorderingAllowed(false);
        this.vista.tablaMatriculas.getTableHeader().setResizingAllowed(false);
        
        this.vista.tablaAsigMat.addMouseListener(this);
        this.vista.tablaAsigMat.getTableHeader().setReorderingAllowed(false);
        this.vista.tablaAsigMat.getTableHeader().setResizingAllowed(false);
        this.vista.tablaAsigMat.setModel(modeloSQLite.getTablaAsignaturasMatriculadas(null));
        
        this.vista.tablaAlumnos.addMouseListener(this);
        this.vista.tablaAlumnos.getTableHeader().setReorderingAllowed(false);
        this.vista.tablaAlumnos.getTableHeader().setResizingAllowed(false);
        
        this.vista.tablaAsigMatAlum.addMouseListener(this);
        this.vista.tablaAsigMatAlum.getTableHeader().setReorderingAllowed(false);
        this.vista.tablaAsigMatAlum.getTableHeader().setResizingAllowed(false);
        this.vista.tablaAsigMatAlum.setModel(modeloSQLite.getTablaAsignaturasMatriculadas(null));
        
        this.vista.tablaProfesores.addMouseListener(this);
        this.vista.tablaProfesores.getTableHeader().setReorderingAllowed(false);
        this.vista.tablaProfesores.getTableHeader().setResizingAllowed(false);
        this.vista.tablaProfesores.setModel(modeloSQLite.getTablaProfesores());
        
        this.vista.tablaAsignaturas.addMouseListener(this);
        this.vista.tablaAsignaturas.getTableHeader().setReorderingAllowed(false);
        this.vista.tablaAsignaturas.getTableHeader().setResizingAllowed(false);
        
        this.vista.tablaAulas.addMouseListener(this);
        this.vista.tablaAulas.getTableHeader().setReorderingAllowed(false);
        this.vista.tablaAulas.getTableHeader().setResizingAllowed(false);
        
        this.vista.tablaAsignaciones.addMouseListener(this);
        this.vista.tablaAsignaciones.getTableHeader().setReorderingAllowed(false);
        this.vista.tablaAsignaciones.getTableHeader().setResizingAllowed(false);
        
        this.vista.comboEdificio.addItemListener( new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                String edificio = vista.comboEdificio.getSelectedItem().toString();
                if(ciencias == 1){
                    vista.comboAula.setModel(new DefaultComboBoxModel(modeloSQLite.getNumerosAulasAAsignar(edificio)));
                }else if(letras == 1){
                    vista.comboAula.setModel(new DefaultComboBoxModel(modeloMySQL.getNumerosAulasAAsignar(edificio)));
                }
            }
        });
        
        this.vista.btnAsignar.setActionCommand("btnAsignar");
        this.vista.btnAsignar.addActionListener(this);
        this.vista.btnEliminarAsignacion.setActionCommand("btnEliminarAsignacion");
        this.vista.btnEliminarAsignacion.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        switch(AccionMVC.valueOf(e.getActionCommand())){
            case btnLetras:
                ciencias = 0;
                letras = 1;
                vista.setVisible(false);
                if(letras == 1){
                    vista.tablaMatriculas.setModel(modeloMySQL.getTablaMatriculas());
                    vista.tablaAsigMat.setModel(modeloMySQL.getTablaAsignaturas());
                }else{
                    vista.tablaMatriculas.setModel(modeloSQLite.getTablaMatriculas());
                    vista.tablaAsigMat.setModel(modeloSQLite.getTablaAsignaturas());
                }
                vista.matriculas.pack();
                vista.matriculas.setLocationRelativeTo(null);
                vista.matriculas.setVisible(true);
                break;
            case btnCiencias:
                letras = 0;
                ciencias = 1;
                vista.setVisible(false);
                if(ciencias == 1){
                    vista.tablaMatriculas.setModel(modeloSQLite.getTablaMatriculas());
                    vista.tablaAsigMat.setModel(modeloSQLite.getTablaAsignaturas());
                    vista.tablaAsigMat.setVisible(false);
                }else{
                    vista.tablaMatriculas.setModel(modeloMySQL.getTablaMatriculas());
                    vista.tablaAsigMat.setModel(modeloMySQL.getTablaAsignaturas());
                    vista.tablaAsigMat.setVisible(false);
                }
                vista.matriculas.pack();
                vista.matriculas.setLocationRelativeTo(null);
                vista.matriculas.setVisible(true);
                break;
            case btnSalirInicio:
                System.exit(0);
                break;
                
                
            case btnNuevaMatricula:
                vista.setVisible(false);
                if(ciencias == 1){
                    vista.comboAsignaturasAMatricular.setModel(new DefaultComboBoxModel(modeloSQLite.getNombreAsignaturasAMatricular()));
                }else if(letras == 1){
                    vista.comboAsignaturasAMatricular.setModel(new DefaultComboBoxModel(modeloMySQL.getNombreAsignaturasAMatricular()));
                }
                DefaultListModel lista = new DefaultListModel();
                vista.listaAsignaturasAMatricular.setModel(lista);
                vista.nuevaMatricula.pack();
                vista.nuevaMatricula.setLocationRelativeTo(null);
                vista.nuevaMatricula.setVisible(true);
                break;
            case btnConsultas:
                vista.matriculas.setVisible(false);
                if(ciencias == 1){
                    vista.tablaAlumnos.setModel(modeloSQLite.getTablaAlumnos());
                    vista.tablaAsigMatAlum.setVisible(false);
                    vista.tablaAsignaturas.setModel(modeloSQLite.getTablaAsignaturas());
                    vista.tablaProfesores.setModel(modeloSQLite.getTablaProfesores());
                    vista.tablaAulas.setModel(modeloSQLite.getTablaAulas());
                    vista.tablaAsignaciones.setModel(modeloSQLite.getTablaAsignaciones());
                    vista.comboProfesor.setModel(new DefaultComboBoxModel(modeloSQLite.getNombresProfesoresAAsignar()));
                    vista.comboAsignatura.setModel(new DefaultComboBoxModel(modeloSQLite.getNombreAsignaturasAMatricular()));
                    vista.comboEdificio.setModel(new DefaultComboBoxModel(modeloSQLite.getNombresEdificiosAAsignar()));
                    String edificio = vista.comboEdificio.getSelectedItem().toString();
                    vista.comboAula.setModel(new DefaultComboBoxModel(modeloSQLite.getNumerosAulasAAsignar(edificio)));
                }else if(letras == 1){
                    vista.tablaAlumnos.setModel(modeloMySQL.getTablaAlumnos());
                    vista.tablaAsigMatAlum.setVisible(false);
                    vista.tablaAsignaturas.setModel(modeloMySQL.getTablaAsignaturas());
                    vista.tablaProfesores.setModel(modeloMySQL.getTablaProfesores());
                    vista.tablaAulas.setModel(modeloMySQL.getTablaAulas());
                    vista.tablaAsignaciones.setModel(modeloMySQL.getTablaAsignaciones());
                    vista.comboProfesor.setModel(new DefaultComboBoxModel(modeloMySQL.getNombresProfesoresAAsignar()));
                    vista.comboAsignatura.setModel(new DefaultComboBoxModel(modeloMySQL.getNombreAsignaturasAMatricular()));
                    vista.comboEdificio.setModel(new DefaultComboBoxModel(modeloMySQL.getNombresEdificiosAAsignar()));
                }
                vista.consultas.pack();
                vista.consultas.setLocationRelativeTo(null);
                vista.consultas.setVisible(true);
                break;
            case btnSalir:
                vista.matriculas.setVisible(false);
                vista.setVisible(true);
                letras = 0;
                ciencias = 0;
                break;
                
            case btnAnadirAsignaturaMatricula:
                try{
                    String asignatura = "";
                    asignatura = vista.comboAsignaturasAMatricular.getSelectedItem().toString();
                    if(asignatura.equals("")){
                        JOptionPane.showMessageDialog(null, "Seleccione una asignatura a añadir.");
                    }else if(compruebaAsignaturaYaSeleccionada(asignatura) == false){
                        DefaultListModel listaAAM = (DefaultListModel) vista.listaAsignaturasAMatricular.getModel();
                        listaAAM.addElement((Object) asignatura);
                    }else if(compruebaAsignaturaYaSeleccionada(asignatura) == true){
                        JOptionPane.showMessageDialog(null, "La asignatura ya ha sido seleccionada.");
                    }
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al añadir asignatura\n" + ex.getMessage());
                    ex.printStackTrace();
                }
                break;
            case btnCompletarMatricula:
                try{
                    String dni = vista.txtNuevoDni.getText();
                    String apellidos = vista.txtNuevoApellidos.getText();
                    String nombre = vista.txtNuevoNombre.getText();
                    String domicilio = vista.txtNuevoDomicilio.getText();
                    String telefono = vista.txtNuevoTelefono.getText();
                    String acceso = vista.txtNuevoAcceso.getText();
                    if(ciencias == 1){
                        if(dni.equals("") || apellidos.equals("") || nombre.equals("") || domicilio.equals("") || telefono.equals("") || acceso.equals("")){
                            JOptionPane.showMessageDialog(null, "Introduzca todos los datos del alumno");
                        }else{
                            modeloSQLite.nuevaMatricula(dni, apellidos, nombre, domicilio, telefono, acceso);
                            modeloSQLite.nuevoAlumno(dni, apellidos, nombre, domicilio, telefono, acceso);
                        }
                    }else if(letras == 1){
                        if(dni.equals("") || apellidos.equals("") || nombre.equals("") || domicilio.equals("") || telefono.equals("") || acceso.equals("")){
                            JOptionPane.showMessageDialog(null, "Introduzca todos los datos del alumno");
                        }else{
                            modeloMySQL.nuevaMatricula(dni, apellidos, nombre, domicilio, telefono, acceso);
                        }
                    }
                    int nel = vista.listaAsignaturasAMatricular.getModel().getSize();
                    if(nel != 0){
                        for(int i = 0; i < nel; i++){
                            String titulo = vista.listaAsignaturasAMatricular.getModel().getElementAt(i).toString();
                            if(ciencias == 1){
                                modeloSQLite.nuevaAsignaturaMatriculada(dni, titulo);                            
                            }else if(letras == 1){
                                modeloMySQL.nuevaAsignaturaMatriculada(dni, titulo);                            
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "Introduzca al menos una asignatura");
                    }
                    vista.nuevaMatricula.setVisible(false);
                    vista.txtNuevoDni.setText("");
                    vista.txtNuevoApellidos.setText("");
                    vista.txtNuevoNombre.setText("");
                    vista.txtNuevoDomicilio.setText("");
                    vista.txtNuevoTelefono.setText("");
                    if(ciencias == 1){
                       vista.tablaMatriculas.setModel(modeloSQLite.getTablaMatriculas()); 
                    }else if(letras == 1){
                        vista.tablaMatriculas.setModel(modeloMySQL.getTablaMatriculas());
                    }
                    vista.matriculas.setVisible(true);
                    DefaultListModel listaAAM = new DefaultListModel();
                    vista.listaAsignaturasAMatricular.setModel(listaAAM);
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al completar matrícula\n" + ex.getMessage());
                    ex.printStackTrace();
                }
                break;
            case btnCancelarMatricula:
                vista.nuevaMatricula.setVisible(false);
                vista.txtNuevoDni.setText("");
                vista.txtNuevoApellidos.setText("");
                vista.txtNuevoNombre.setText("");
                vista.txtNuevoDomicilio.setText("");
                vista.txtNuevoTelefono.setText("");
                if(ciencias == 1){
                    vista.tablaMatriculas.setModel(modeloSQLite.getTablaMatriculas()); 
                }else if(letras == 1){
                    vista.tablaMatriculas.setModel(modeloMySQL.getTablaMatriculas());
                }
                vista.matriculas.setVisible(true);
                DefaultListModel listaAAM = new DefaultListModel();
                vista.listaAsignaturasAMatricular.setModel(listaAAM);
                break;
                
                
            case btnVolver:
                vista.consultas.setVisible(false);
                if(ciencias == 1){
                    vista.tablaMatriculas.setModel(modeloSQLite.getTablaMatriculas()); 
                }else if(letras == 1){
                    vista.tablaMatriculas.setModel(modeloMySQL.getTablaMatriculas());
                }
                vista.matriculas.setVisible(true);
                break;
                
            case btnModificarNota:
                int notaMN = Integer.parseInt(vista.txtNotaAsignatura.getText());
                String dniMN = vista.txtDniAlumno.getText();
                String tituloMN = vista.txtTituloAsignatura.getText();
                if(ciencias == 1){
                    if(notaMN >= 0){
                        modeloSQLite.modificarNota(dniMN, tituloMN, notaMN);
                        vista.tablaAsigMatAlum.setModel(modeloSQLite.getTablaAsignaturasMatriculadas(dniMN));
                    }else{
                        JOptionPane.showMessageDialog(null, "Introduzca una nueva nota mayor o igual que 0");
                    }
                }else if(letras == 1){
                    if(notaMN >= 0){
                        modeloMySQL.modificarNota(dniMN, tituloMN, notaMN);
                        vista.tablaAsigMatAlum.setModel(modeloMySQL.getTablaAsignaturasMatriculadas(dniMN));
                    }else{
                        JOptionPane.showMessageDialog(null, "Introduzca una nueva nota mayor o igual que 0");
                    }
                }
                break;
                
                
            case btnAnadirAsignatura:
                if(vista.txtNuevoCodigoAsignatura.getText().equals("") || vista.txtTitulo.getText().equals("") || vista.txtNumCreditos.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Introduzca los datos necesarios para crear una nueva asignatura");
                }else{
                    int codigo = Integer.parseInt(vista.txtNuevoCodigoAsignatura.getText());
                    String titulo = vista.txtTitulo.getText();
                    int creditos = Integer.parseInt(vista.txtNumCreditos.getText());
                    if(ciencias == 1){
                        if(modeloSQLite.comprobarExistenciaAsignatura(codigo, titulo) == true){
                            JOptionPane.showMessageDialog(null, "Esta asignatura ya existe");
                        }else{
                            modeloSQLite.nuevaAsignatura(codigo, titulo, creditos);
                            vista.tablaAsignaturas.setModel(modeloSQLite.getTablaAsignaturas());
                            vista.txtNuevoCodigoAsignatura.setText("");
                            vista.txtTitulo.setText("");
                            vista.txtNumCreditos.setText("");
                        }
                    }else if(letras == 1){
                        if(modeloMySQL.comprobarExistenciaAsignatura(codigo, titulo) == true){
                            JOptionPane.showMessageDialog(null, "Esta asignatura ya existe");
                        }else{
                            modeloMySQL.nuevaAsignatura(codigo, titulo, creditos);
                            vista.tablaAsignaturas.setModel(modeloMySQL.getTablaAsignaturas());
                            vista.txtNuevoCodigoAsignatura.setText("");
                            vista.txtTitulo.setText("");
                            vista.txtNumCreditos.setText("");
                        }
                    }
                }
                break;
            case btnModificarAsignatura:
                int codigo = Integer.parseInt(vista.txtCodigo.getText());
                int nCodigo = Integer.parseInt(vista.txtNuevoCodigoAsignatura.getText());
                String titulo = vista.txtTitulo.getText();
                int creditos = Integer.parseInt(vista.txtNumCreditos.getText());
                if(ciencias == 1){
                    if(modeloSQLite.comprobarExistenciaAsignatura(nCodigo, titulo) == true){
                        JOptionPane.showMessageDialog(null, "Introduzca unos datos inexistentes");
                    }else{
                        modeloSQLite.modificarAsignatura(codigo, nCodigo, titulo, creditos);
                        vista.tablaAsignaturas.setModel(modeloSQLite.getTablaAsignaturas());
                        vista.txtCodigo.setText("");
                        vista.txtNuevoCodigoAsignatura.setText("");
                        vista.txtTitulo.setText("");
                        vista.txtNumCreditos.setText("");
                    }
                }else if(letras == 1){
                    if(modeloMySQL.comprobarExistenciaAsignatura(nCodigo, titulo) == true){
                        JOptionPane.showMessageDialog(null, "Si quiere modificar los datos introduzca unos diferentes");
                    }else{
                        modeloMySQL.modificarAsignatura(codigo, nCodigo, titulo, creditos);
                    }
                }
                break;
            case btnEliminarAsignatura:
                int codigoEA = Integer.parseInt(vista.txtCodigo.getText());
                String tituloEA = vista.txtTitulo.getText();
                if(ciencias == 1){
                    modeloSQLite.eliminarAsignatura(codigoEA, tituloEA);
                    vista.tablaAsignaturas.setModel(modeloSQLite.getTablaAsignaturas());
                    vista.txtCodigo.setText("");
                    vista.txtNuevoCodigoAsignatura.setText("");
                    vista.txtTitulo.setText("");
                    vista.txtNumCreditos.setText("");
                }else if(letras == 1){
                    modeloMySQL.eliminarAsignatura(codigoEA, tituloEA);
                }
                break;
                
                
            case btnAnadirProfesor:
                if(vista.txtNuevoDniProfesor.getText().equals("") || vista.txtApellidosProfesor.getText().equals("")
                        || vista.txtNombreProfesor.getText().equals("") || vista.txtDomicilioProfesor.getText().equals("")
                        || vista.txtTelefonoProfesor.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Introduzca los datos necesarios para crear un nuevo profesor");
                }else{
                    String nDni = vista.txtNuevoDniProfesor.getText();
                    String apellidos = vista.txtApellidosProfesor.getText();
                    String nombre = vista.txtNombreProfesor.getText();
                    String domicilio = vista.txtDomicilioProfesor.getText();
                    String telefono = vista.txtTelefonoProfesor.getText();
                    String supervisor = vista.txtSupervisorProfesor.getText();
                    if(ciencias == 1){
                        if(supervisor.equals("")){
                            if(modeloSQLite.comprobarExistenciaProfesor(nDni) == true){
                                JOptionPane.showMessageDialog(null, "El profesor ya existe");
                            }else{
                                modeloSQLite.nuevoProfesor(nDni, apellidos, nombre, domicilio, telefono, supervisor);
                            }
                        }else{
                            if(modeloSQLite.comprobarExistenciaProfesor(supervisor) == false){
                                JOptionPane.showMessageDialog(null, "El supervisor no existe");
                            }else{
                                if(modeloSQLite.comprobarExistenciaProfesor(nDni) == true){
                                    JOptionPane.showMessageDialog(null, "El profesor ya existe");
                                }else{
                                    modeloSQLite.nuevoProfesor(nDni, apellidos, nombre, domicilio, telefono, supervisor);
                                }
                            }
                        }
                        vista.txtDniProfesor.setText("");
                        vista.txtNuevoDniProfesor.setText("");
                        vista.txtApellidosProfesor.setText("");
                        vista.txtNombreProfesor.setText("");
                        vista.txtDomicilioProfesor.setText("");
                        vista.txtTelefonoProfesor.setText("");
                        vista.txtSupervisorProfesor.setText("");
                        vista.tablaProfesores.setModel(modeloSQLite.getTablaProfesores());
                    }else if(letras == 1){
                        if(supervisor.equals("")){
                            if(modeloMySQL.comprobarExistenciaProfesor(nDni) == true){
                                JOptionPane.showMessageDialog(null, "El profesor ya existe");
                            }else{
                                modeloMySQL.nuevoProfesor(nDni, apellidos, nombre, domicilio, telefono, supervisor);
                            }
                        }else{
                            if(modeloMySQL.comprobarExistenciaProfesor(supervisor) == false){
                                JOptionPane.showMessageDialog(null, "El supervisor no existe");
                            }else{
                                if(modeloMySQL.comprobarExistenciaProfesor(nDni) == true){
                                    JOptionPane.showMessageDialog(null, "El profesor ya existe");
                                }else{
                                    modeloMySQL.nuevoProfesor(nDni, apellidos, nombre, domicilio, telefono, supervisor);
                                }
                            }
                        }
                        vista.txtDniProfesor.setText("");
                        vista.txtNuevoDniProfesor.setText("");
                        vista.txtApellidosProfesor.setText("");
                        vista.txtNombreProfesor.setText("");
                        vista.txtDomicilioProfesor.setText("");
                        vista.txtTelefonoProfesor.setText("");
                        vista.txtSupervisorProfesor.setText("");
                        vista.tablaProfesores.setModel(modeloMySQL.getTablaProfesores());
                    }
                }
                break;
            case btnModificarProfesor:
                String dni = vista.txtDniProfesor.getText();
                String nDni = vista.txtNuevoDniProfesor.getText();
                String apellidos = vista.txtApellidosProfesor.getText();
                String nombre = vista.txtNombreProfesor.getText();
                String domicilio = vista.txtDomicilioProfesor.getText();
                String telefono = vista.txtTelefonoProfesor.getText();
                String supervisor = vista.txtSupervisorProfesor.getText();
                if(ciencias == 1){
                    if(supervisor.equals("")){
                        if(modeloSQLite.comprobarExistenciaProfesor(nDni) == true){
                            JOptionPane.showMessageDialog(null, "El profesor ya existe");
                        }else{
                            modeloSQLite.modificarProfesor(dni, nDni, apellidos, nombre, domicilio, telefono, supervisor);
                        }
                    }else{
                        if(modeloSQLite.comprobarExistenciaProfesor(supervisor) == false){
                            JOptionPane.showMessageDialog(null, "El supervisor no existe");
                        }else{
                            if(modeloSQLite.comprobarExistenciaProfesor(nDni) == true){
                                JOptionPane.showMessageDialog(null, "El profesor ya existe");
                            }else{
                                modeloSQLite.modificarProfesor(dni, nDni, apellidos, nombre, domicilio, telefono, supervisor);
                            }
                        }
                    }
                    vista.txtDniProfesor.setText("");
                    vista.txtNuevoDniProfesor.setText("");
                    vista.txtApellidosProfesor.setText("");
                    vista.txtNombreProfesor.setText("");
                    vista.txtDomicilioProfesor.setText("");
                    vista.txtTelefonoProfesor.setText("");
                    vista.txtSupervisorProfesor.setText("");
                    vista.tablaProfesores.setModel(modeloSQLite.getTablaProfesores());
                }else if(letras == 1){
                    if(supervisor.equals("")){
                        if(modeloMySQL.comprobarExistenciaProfesor(nDni) == true){
                            JOptionPane.showMessageDialog(null, "El profesor ya existe");
                        }else{
                            modeloMySQL.modificarProfesor(dni, nDni, apellidos, nombre, domicilio, telefono, supervisor);
                        }
                    }else{
                        if(modeloMySQL.comprobarExistenciaProfesor(supervisor) == false){
                            JOptionPane.showMessageDialog(null, "El supervisor no existe");
                        }else{
                            if(modeloMySQL.comprobarExistenciaProfesor(nDni) == true){
                                JOptionPane.showMessageDialog(null, "El profesor ya existe");
                            }else{
                                modeloMySQL.modificarProfesor(dni, nDni, apellidos, nombre, domicilio, telefono, supervisor);
                            }
                        }
                    }
                    vista.txtDniProfesor.setText("");
                    vista.txtNuevoDniProfesor.setText("");
                    vista.txtApellidosProfesor.setText("");
                    vista.txtNombreProfesor.setText("");
                    vista.txtDomicilioProfesor.setText("");
                    vista.txtTelefonoProfesor.setText("");
                    vista.txtSupervisorProfesor.setText("");
                    vista.tablaProfesores.setModel(modeloMySQL.getTablaProfesores());
                }
                break;
            case btnEliminarProfesor:
                String dniEP = vista.txtDniProfesor.getText();
                if(ciencias == 1){
                    modeloSQLite.eliminarProfesor(dniEP);
                    vista.txtDniProfesor.setText("");
                    vista.txtNuevoDniProfesor.setText("");
                    vista.txtApellidosProfesor.setText("");
                    vista.txtNombreProfesor.setText("");
                    vista.txtDomicilioProfesor.setText("");
                    vista.txtTelefonoProfesor.setText("");
                    vista.txtSupervisorProfesor.setText("");
                    vista.tablaProfesores.setModel(modeloSQLite.getTablaProfesores());
                }else if(letras == 1){
                    modeloMySQL.eliminarProfesor(dniEP);
                    vista.txtDniProfesor.setText("");
                    vista.txtNuevoDniProfesor.setText("");
                    vista.txtApellidosProfesor.setText("");
                    vista.txtNombreProfesor.setText("");
                    vista.txtDomicilioProfesor.setText("");
                    vista.txtTelefonoProfesor.setText("");
                    vista.txtSupervisorProfesor.setText("");
                    vista.tablaProfesores.setModel(modeloMySQL.getTablaProfesores());
                }
                break;
                
                
            case btnAnadirAula:
                String edificio = vista.txtNuevoEdificio.getText();
                int numero = Integer.parseInt(vista.txtNuevoNumero.getText());
                if(edificio.equals("") || vista.txtAula.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Introduzca los datos necesarios");
                }else{
                    if(ciencias == 1){
                        if(modeloSQLite.comprobarExistenciaAula(edificio, numero) == true){
                            JOptionPane.showMessageDialog(null, "Ese aula ya existe");
                        }else{
                            modeloSQLite.nuevoAula(edificio, numero);
                            vista.txtEdificio.setText("");
                            vista.txtAula.setText("");
                            vista.txtNuevoEdificio.setText("");
                            vista.txtNuevoNumero.setText("");
                            vista.tablaAulas.setModel(modeloSQLite.getTablaAulas());
                        }
                    }else if(letras == 1){
                        if(modeloMySQL.comprobarExistenciaAula(edificio, numero)== true){
                            JOptionPane.showMessageDialog(null, "Ese aula ya existe");
                        }else{
                            modeloMySQL.nuevoAula(edificio, numero);
                            vista.txtEdificio.setText("");
                            vista.txtAula.setText("");
                            vista.txtNuevoEdificio.setText("");
                            vista.txtNuevoNumero.setText("");
                            vista.tablaAulas.setModel(modeloSQLite.getTablaAulas());
                        }
                    }
                }
                break;
            case btnModificarAula:
                String nEdificioMA = vista.txtNuevoEdificio.getText();
                int nNumeroMA = Integer.parseInt(vista.txtNuevoNumero.getText());
                String edificioMA = vista.txtEdificio.getText();
                int numeroMA = Integer.parseInt(vista.txtAula.getText());
                if(ciencias == 1){
                    if(modeloSQLite.comprobarExistenciaAula(nEdificioMA, nNumeroMA) == true){
                        JOptionPane.showMessageDialog(null, "Ese aula ya existe");
                    }else{
                        modeloSQLite.modificarAula(edificioMA, numeroMA, nEdificioMA, nNumeroMA);
                        vista.txtEdificio.setText("");
                        vista.txtAula.setText("");
                        vista.txtEdificio.setText("");
                        vista.txtAula.setText("");
                        vista.tablaAulas.setModel(modeloSQLite.getTablaAulas());
                    }
                }else if(letras == 1){
                    if(modeloMySQL.comprobarExistenciaAula(nEdificioMA, nNumeroMA) == true){
                        JOptionPane.showMessageDialog(null, "Ese aula ya existe");
                    }else{
                        modeloMySQL.modificarAula(edificioMA, numeroMA, nEdificioMA, nNumeroMA);
                        vista.txtEdificio.setText("");
                        vista.txtAula.setText("");
                        vista.txtNuevoEdificio.setText("");
                        vista.txtNuevoNumero.setText("");
                        vista.tablaAulas.setModel(modeloMySQL.getTablaAulas());
                    }
                }
                break;
            case btnEliminarAula:
                String edificioE = vista.txtEdificio.getText();
                int numeroE = Integer.parseInt(vista.txtAula.getText());
                if(ciencias == 1){
                    modeloSQLite.eliminarAula(edificioE, numeroE);
                    vista.txtEdificio.setText("");
                    vista.txtAula.setText("");
                    vista.txtNuevoEdificio.setText("");
                    vista.txtNuevoNumero.setText("");
                    vista.tablaAulas.setModel(modeloSQLite.getTablaAulas());
                }else if(letras == 1){
                    modeloMySQL.eliminarAula(edificioE, numeroE);
                    vista.txtEdificio.setText("");
                    vista.txtAula.setText("");
                    vista.txtNuevoEdificio.setText("");
                    vista.txtNuevoNumero.setText("");
                    vista.tablaAulas.setModel(modeloMySQL.getTablaAulas());
                }
                break;
                
                
            case btnAsignar:
                String dniAsig = vista.comboProfesor.getSelectedItem().toString();
                String tituloAsig = vista.comboAsignatura.getSelectedItem().toString();
                String edificioAsig = vista.comboEdificio.getSelectedItem().toString();
                int aulaAsig = Integer.parseInt(vista.comboAula.getSelectedItem().toString());
                if(ciencias == 1){
                    String apellidosAsig = modeloSQLite.getInfoProfesor(dniAsig)[0];
                    String nombreAsig = modeloSQLite.getInfoProfesor(dniAsig)[1];
                    if(modeloSQLite.comprobarExistenciaAsignacion(dniAsig, apellidosAsig, nombreAsig, tituloAsig, edificioAsig, aulaAsig) == true){
                        JOptionPane.showMessageDialog(null, "Ya existe esa asignación");
                    }else{
                        modeloSQLite.nuevaAsignacion(dniAsig, apellidosAsig, nombreAsig, tituloAsig, edificioAsig, aulaAsig);
                        vista.tablaAsignaciones.setModel(modeloSQLite.getTablaAsignaciones());
                    }
                }else if(letras == 1){
                    String apellidosAsig = modeloMySQL.getInfoProfesor(dniAsig)[0];
                    String nombreAsig = modeloMySQL.getInfoProfesor(dniAsig)[1];
                    if(modeloMySQL.comprobarExistenciaAsignacion(dniAsig, apellidosAsig, nombreAsig, tituloAsig, edificioAsig, aulaAsig) == true){
                        JOptionPane.showMessageDialog(null, "Ya existe esa asignación");
                    }else{
                        modeloMySQL.nuevaAsignacion(dniAsig, apellidosAsig, nombreAsig, tituloAsig, edificioAsig, aulaAsig);
                        vista.tablaAsignaciones.setModel(modeloMySQL.getTablaAsignaciones());
                    }
                }
                break;
            case btnEliminarAsignacion:
                String cod = vista.txtCodigoAsignacion.getText();
                if(cod.equals("")){
                    JOptionPane.showMessageDialog(null, "Seleccione una asignación");
                }else{
                    if(ciencias == 1){
                        modeloSQLite.eliminarAsignacion(cod);
                        vista.txtCodigoAsignacion.setText("");
                        vista.tablaAsignaciones.setModel(modeloSQLite.getTablaAsignaciones());
                    }else if(letras == 1){
                        modeloMySQL.eliminarAsignacion(Integer.parseInt(cod));
                        vista.txtCodigoAsignacion.setText("");
                        vista.tablaAsignaciones.setModel(modeloMySQL.getTablaAsignaciones());
                    }
                }
                break;
        }
    }
    
    public boolean compruebaAsignaturaYaSeleccionada(String titulo){
        boolean res = false;
        int nel = vista.listaAsignaturasAMatricular.getModel().getSize();
        for(int i = 0; i < nel; i++){
            if(vista.listaAsignaturasAMatricular.getModel().getElementAt(i).toString().equals(titulo)){
                res = true;
            }
        }
        return res;
    }

    public void mouseClicked(MouseEvent e) {
        if(e.getButton()== 1){
            int matricula = vista.tablaMatriculas.rowAtPoint(e.getPoint());
            int alumno = vista.tablaAlumnos.rowAtPoint(e.getPoint());
            int asigMatAlum = vista.tablaAsigMatAlum.rowAtPoint(e.getPoint());
            int asignatura = vista.tablaAsignaturas.rowAtPoint(e.getPoint());
            int profesor = vista.tablaProfesores.rowAtPoint(e.getPoint());
            int aula = vista.tablaAulas.rowAtPoint(e.getPoint());
            int asignacion = vista.tablaAsignaciones.rowAtPoint(e.getPoint());
            if (matricula > -1){
                try{
                    String dni = String.valueOf(vista.tablaMatriculas.getValueAt(matricula, 0));
                    if(ciencias == 1){
                        vista.tablaAsigMat.setModel(modeloSQLite.getTablaAsignaturasMatriculadasSinNota(dni));
                        vista.tablaAsigMat.setVisible(true);
                    }else if(letras == 1){
                        vista.tablaAsigMat.setModel(modeloMySQL.getTablaAsignaturasMatriculadasSinNota(dni));
                        vista.tablaAsigMat.setVisible(true);
                    }
                    matricula = -1;
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al intentar actualizar la tabla de Asignaturas"+ex.getMessage());
                    ex.printStackTrace();
                }
            }
            if(alumno > -1){
                try{
                    String dni = String.valueOf(vista.tablaAlumnos.getValueAt(alumno, 0));
                    if(ciencias == 1){
                        vista.tablaAsigMatAlum.setModel(modeloSQLite.getTablaAsignaturasMatriculadas(dni));
                        vista.tablaAsigMatAlum.setVisible(true);
                        vista.txtDniAlumno.setText(dni);
                    }else if(letras == 1){
                        vista.tablaAsigMatAlum.setModel(modeloMySQL.getTablaAsignaturasMatriculadas(dni));
                        vista.tablaAsigMatAlum.setVisible(true);
                        vista.txtDniAlumno.setText(dni);
                    }
                    alumno = -1;
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al intentar actualizar la tabla de Asignaturas"+ex.getMessage());
                    ex.printStackTrace();
                }
            }
            if(asigMatAlum > -1){
                try{
                    vista.txtTituloAsignatura.setText(String.valueOf(vista.tablaAsigMatAlum.getValueAt(asigMatAlum, 2)));
                    vista.txtNotaAsignatura.setText(String.valueOf(vista.tablaAsigMatAlum.getValueAt(asigMatAlum, 4)));
                    asigMatAlum = -1;
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al intentar obtener los datos"+ex.getMessage());
                    ex.printStackTrace();
                }
            }
            if(asignatura > -1){
                try{
                    vista.txtCodigo.setText(String.valueOf(vista.tablaAsignaturas.getValueAt(asignatura, 0)));
                    vista.txtTitulo.setText(String.valueOf(vista.tablaAsignaturas.getValueAt(asignatura, 1)));
                    vista.txtNumCreditos.setText(String.valueOf(vista.tablaAsignaturas.getValueAt(asignatura, 2)));
                    asignatura = -1;
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al intentar obtener los datos"+ex.getMessage());
                    ex.printStackTrace();
                }
            }
            if(profesor > -1){
                try{
                    vista.txtDniProfesor.setText(String.valueOf(vista.tablaProfesores.getValueAt(profesor, 0)));
                    vista.txtApellidosProfesor.setText(String.valueOf(vista.tablaProfesores.getValueAt(profesor, 1)));
                    vista.txtNombreProfesor.setText(String.valueOf(vista.tablaProfesores.getValueAt(profesor, 2)));
                    vista.txtDomicilioProfesor.setText(String.valueOf(vista.tablaProfesores.getValueAt(profesor, 3)));
                    vista.txtTelefonoProfesor.setText(String.valueOf(vista.tablaProfesores.getValueAt(profesor, 4)));
                    vista.txtSupervisorProfesor.setText(String.valueOf(vista.tablaProfesores.getValueAt(profesor, 5)));
                    profesor = -1;
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al intentar obtener los datos"+ex.getMessage());
                    ex.printStackTrace();
                }
            }
            if(aula > -1){
                try{
                    vista.txtEdificio.setText(String.valueOf(vista.tablaAulas.getValueAt(aula, 0)));
                    vista.txtAula.setText(String.valueOf(vista.tablaAulas.getValueAt(aula, 1)));
                    vista.txtNuevoEdificio.setText(String.valueOf(vista.tablaAulas.getValueAt(aula, 0)));
                    vista.txtNuevoNumero.setText(String.valueOf(vista.tablaAulas.getValueAt(aula, 1)));
                    aula = -1;
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al intentar obtener los datos"+ex.getMessage());
                    ex.printStackTrace();
                }
            }
            if(asignacion > -1){
                try{
                    vista.txtCodigoAsignacion.setText(String.valueOf(vista.tablaAsignaciones.getValueAt(asignacion, 0)));
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al intentar obtener los datos"+ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }
}