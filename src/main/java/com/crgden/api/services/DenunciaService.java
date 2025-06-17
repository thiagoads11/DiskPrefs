package com.crgden.api.services;

import com.crgden.api.dto.DenunciaDTO;
import com.crgden.api.entities.Denuncia;
import com.crgden.api.repositories.DenunciaRepository;
import com.crgden.api.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    // Lógica para recuperar todas as denúncias no banco de dados
    @Transactional(readOnly = true)
    public List<DenunciaDTO> selecionarDenuncias() {
        List<Denuncia> registros = denunciaRepository.findAll();
        return registros.stream().map(DenunciaDTO::new).toList();
    }

    // Lógica para recuperar uma denúncia pelo protocolo
    @Transactional(readOnly = true)
    public DenunciaDTO selecionarDenunciaPorProtocolo( Long id) {
        Denuncia denuncia = denunciaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Denuncia não encontrada"));
        return new DenunciaDTO(denuncia);
    }

    // Lógica para inserir / registrar uma denúncia
    @Transactional
    public DenunciaDTO registrarDenuncia(DenunciaDTO dto) {
        Denuncia denuncia = new Denuncia();
        dtoParaEntidade(dto, denuncia);
        denuncia = denunciaRepository.save(denuncia);
        return new DenunciaDTO(denuncia);
    }

    // Lógica para atualizar uma denúncia feita pelo o usuário
    @Transactional
    public DenunciaDTO atualizarDenuncia(Long protocolo, DenunciaDTO dto) {
        try {
            Denuncia denuncia = denunciaRepository.getReferenceById(protocolo);
            dtoParaEntidade(dto, denuncia);
            denuncia = denunciaRepository.save(denuncia);
            return new DenunciaDTO(denuncia);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Denuncia não encontrada");
        }
    }

    // Lógica para apagar um registro de denúncia no banco de dados
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deletarDenuncia(Long protocolo) {
        if (!denunciaRepository.existsById(protocolo)) {
            throw new ResourceNotFoundException("Denuncia não encontrada");
        }
        denunciaRepository.deleteById(protocolo);
    }

    // Método auxiliar para trasformar DTO para entidade
    private void dtoParaEntidade(DenunciaDTO dto, Denuncia denuncia) {
        denuncia.setNomeUsuario(dto.getNomeUsuario());
        denuncia.setEmail(dto.getEmail());
        denuncia.setTelefoneUsuario(dto.getTelefoneUsuario());
        denuncia.setTituloDenuncia(dto.getTituloDenuncia());
        denuncia.setCategoriaDenuncia(dto.getCategoriaDenuncia());
        denuncia.setDescricaoDenuncia(dto.getDescricaoDenuncia());
        denuncia.setEnderecoDenuncia(dto.getEnderecoDenuncia());
        denuncia.setDenunciaAnonima(dto.isDenunciaAnonima());
    }
}