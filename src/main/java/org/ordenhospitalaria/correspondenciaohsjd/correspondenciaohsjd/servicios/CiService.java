package org.ordenhospitalaria.correspondenciaohsjd.correspondenciaohsjd.servicios;

import org.ordenhospitalaria.correspondenciaohsjd.correspondenciaohsjd.modelos.Ci;
import org.ordenhospitalaria.correspondenciaohsjd.correspondenciaohsjd.repositorios.CiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CiService {

    @Autowired
    private CiRepository ciRepository;

    public Page<Ci> paginaCi(Pageable pageable) {
        return ciRepository.findAll(pageable);
    }

    public void crearCi(Ci ci) {
        ciRepository.save(ci);
    }
}
