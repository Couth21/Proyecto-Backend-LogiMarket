package com.springboot.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "REPORTE")
public class ReporteEntity 
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReporte;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "mes")
    private Mes mes;

    @Column(name = "anio")
    private Integer anio;

    @Column(name = "ventas_totales")
    private BigDecimal ventasTotales;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario") 
    private UsuarioEntity usuarioEntity;


    
    
    
    
    
    
    public ReporteEntity(Mes mes, Integer anio, BigDecimal ventasTotales, UsuarioEntity usuarioEntity) {
		super();
		this.mes = mes;
		this.anio = anio;
		this.ventasTotales = ventasTotales;
		this.usuarioEntity = usuarioEntity;
	}
    
    

	public UsuarioEntity getUsuarioEntity() {
		return usuarioEntity;
	}



	public void setUsuarioEntity(UsuarioEntity usuarioEntity) {
		this.usuarioEntity = usuarioEntity;
	}



	// Getters y setters
    public Long getIdReporte() {
        return idReporte;
    }

    public void setIdReporte(Long idReporte) {
        this.idReporte = idReporte;
    }

    public Mes getMes() {
        return mes;
    }

    public void setMes(Mes mes) {
        this.mes = mes;
    }

    public void setMes(int numeroMes) {
        this.mes = Mes.fromNumero(numeroMes);
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public BigDecimal getVentasTotales() {
        return ventasTotales;
    }

    public void setVentasTotales(BigDecimal ventasTotales) {
        this.ventasTotales = ventasTotales;
    }

    
    
    public ReporteEntity(Mes mes, Integer anio, BigDecimal ventasTotales) {
		//super();
		this.mes = mes;
		this.anio = anio;
		this.ventasTotales = ventasTotales;
	}

    public ReporteEntity() {
		// TODO Auto-generated constructor stub
	}


	// Enum anidado para los Meses
    public enum Mes {
        ENERO(1),
        FEBRERO(2),
        MARZO(3),
        ABRIL(4),
        MAYO(5),
        JUNIO(6),
        JULIO(7),
        AGOSTO(8),
        SEPTIEMBRE(9),
        OCTUBRE(10),
        NOVIEMBRE(11),
        DICIEMBRE(12);

        private final int numero;

        Mes(int numero) {
            this.numero = numero;
        }

        public int getNumero() {
            return numero;
        }

        public static Mes fromNumero(int numero) {
            for (Mes mes : Mes.values()) {
                if (mes.getNumero() == numero) {
                    return mes;
                }
            }
            throw new IllegalArgumentException("Número de mes inválido: " + numero);
        }
    }
}