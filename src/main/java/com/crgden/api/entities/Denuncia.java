package com.crgden.api.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "denuncias")
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long protocolo;
    private String nomeUsuario;
    private String email;
    private String telefoneUsuario;
    private String tituloDenuncia;
    private String categoriaDenuncia;
    private String descricaoDenuncia;
    private String enderecoDenuncia;
    private boolean denunciaAnonima;

    public Denuncia() {
    }

    public Denuncia(Long protocolo, String nomeUsuario, String email, String telefoneUsuario, String tituloDenuncia, String categoriaDenuncia, String descricaoDenuncia, String enderecoDenuncia, boolean denunciaAnonima) {
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

    public Long getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(Long protocolo) {
        this.protocolo = protocolo;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoneUsuario() {
        return telefoneUsuario;
    }

    public void setTelefoneUsuario(String telefoneUsuario) {
        this.telefoneUsuario = telefoneUsuario;
    }

    public String getTituloDenuncia() {
        return tituloDenuncia;
    }

    public void setTituloDenuncia(String tituloDenuncia) {
        this.tituloDenuncia = tituloDenuncia;
    }

    public String getCategoriaDenuncia() {
        return categoriaDenuncia;
    }

    public void setCategoriaDenuncia(String categoriaDenuncia) {
        this.categoriaDenuncia = categoriaDenuncia;
    }

    public String getDescricaoDenuncia() {
        return descricaoDenuncia;
    }

    public void setDescricaoDenuncia(String descricaoDenuncia) {
        this.descricaoDenuncia = descricaoDenuncia;
    }

    public String getEnderecoDenuncia() {
        return enderecoDenuncia;
    }

    public void setEnderecoDenuncia(String enderecoDenuncia) {
        this.enderecoDenuncia = enderecoDenuncia;
    }

    public boolean isDenunciaAnonima() {
        return denunciaAnonima;
    }

    public void setDenunciaAnonima(boolean denunciaAnonima) {
        this.denunciaAnonima = denunciaAnonima;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Denuncia denuncia = (Denuncia) o;
        return Objects.equals(protocolo, denuncia.protocolo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(protocolo);
    }
}