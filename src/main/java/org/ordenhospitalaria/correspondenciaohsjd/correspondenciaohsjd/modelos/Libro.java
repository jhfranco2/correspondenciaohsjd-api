package org.ordenhospitalaria.correspondenciaohsjd.correspondenciaohsjd.modelos;

import java.util.Date;
import jakarta.persistence.*;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class Libro {

    @Column(length = 1)
    private String tipoDocumento;

    @Column(length = 28)
    private String proveedor;

    @Column(length = 48)
    private String numeroDocumento;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date fechaEntrada;

    @Column(length = 1)
    private String remitenteDestinatario;

    @Column(length = 1)
    private String importancia;

    @Column(length = 48)
    private String direccion;

    @Column(length = 60)
    private String nombreDestinatarioRemitente;

    @Column(length = 72)
    private String asunto;

    @ManyToOne
    @JoinColumn(name = "proceso_id", nullable = false)
    private Proceso proceso;

    @Column(length = 48)
    private String quienFirma;

    @Column(nullable = false, length = 48)
    private String quienSolicitaRecibe;

    @Temporal(TemporalType.TIMESTAMP)
    private Date salida;

    @Column(length = 70)
    private String registroDeEntrega;

    @Column(length = 120)
    private String observaciones;
    
}