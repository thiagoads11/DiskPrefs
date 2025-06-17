package com.crgden.api.dto;

import com.crgden.api.entities.Denuncia;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class DenunciaDTO {

    private Long protocolo;

    @Size(min = 10, max = 30, message = "O nome precisa ter de 10 a 30 caracteres")
    @NotBlank(message = "Campo requerido")
    private String nomeUsuario;

    private String email;

    @Size(min = 10, max = 30, message = "O telefone precisa ter de 10 a 30 caracteres")
    @NotBlank(message = "Campo requerido")
    private String telefoneUsuario;

    @Size(min = 6, max = 30, message = "O titulo da denúncia precisa ter de 10 a 30 caracteres")
    @NotBlank(message = "Campo requerido")
    private String tituloDenuncia;

    @NotEmpty(message = "Deve ter pelo menos uma categoria")
    private String categoriaDenuncia;

    @Size(min = 10, message = "Descrição precisa ter no mínimo 10 caracteres")
    @NotBlank(message = "Campo requerido")
    private String descricaoDenuncia;
    private String enderecoDenuncia;
    private boolean denunciaAnonima;

    public DenunciaDTO() {
    }

    public DenunciaDTO(Long protocolo, String nomeUsuario, String email, String telefoneUsuario, String tituloDenuncia, String categoriaDenuncia, String descricaoDenuncia, String enderecoDenuncia, boolean denunciaAnonima) {
        this.protocolo = protocolo;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.telefoneUsuario = telefoneUsuario;
        this.tituloDenuncia = tituloDenuncia;
        this.categoriaDenuncia = categoriaDenuncia;
        this.descricaoDenuncia = descricaoDenuncia;
        this.enderecoDenuncia = enderecoDenuncia;
        this.denunciaAnonima = denunciaAnonima;
    }

    public DenunciaDTO(Denuncia denuncia) {
        this.protocolo = denuncia.getProtocolo();
        this.nomeUsuario = denuncia.getNomeUsuario();
        this.email = denuncia.getEmail();
        this.telefoneUsuario = denuncia.getTelefoneUsuario();
        this.tituloDenuncia = denuncia.getTituloDenuncia();
        this.categoriaDenuncia = denuncia.getCategoriaDenuncia();
        this.descricaoDenuncia = denuncia.getDescricaoDenuncia();
        this.enderecoDenuncia = denuncia.getEnderecoDenuncia();
        this.denunciaAnonima = denuncia.isDenunciaAnonima();
    }

    public Long getProtocolo() {
        return protocolo;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefoneUsuario() {
        return telefoneUsuario;
    }

    public String getTituloDenuncia() {
        return tituloDenuncia;
    }

    public String getCategoriaDenuncia() {
        return categoriaDenuncia;
    }

    public String getDescricaoDenuncia() {
        return descricaoDenuncia;
    }

    public String getEnderecoDenuncia() {
        return enderecoDenuncia;
    }

    public boolean isDenunciaAnonima() {
        return denunciaAnonima;
    }
}
