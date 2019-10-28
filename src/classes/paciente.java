/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author Kino
 */
public class paciente extends dados {
    private int id_pac;
    private String plano_saude;
//    private String dt_consulta;
//    private String especialidade;
//    private String nome_med;
    //private String diagnostico;
    //private boolean acompanhante;
    //private String nome_acomp;
    //private String cpf_acomp;

    public int getId_pac() {
        return id_pac;
    }

    public void setId_pac(int id_pac) {
        this.id_pac = id_pac;
    }
    
    public String getPlano_saude() {
        return plano_saude;
    }

    public void setPlano_saude(String plano_saude) {
        this.plano_saude = plano_saude;
    }    
}
