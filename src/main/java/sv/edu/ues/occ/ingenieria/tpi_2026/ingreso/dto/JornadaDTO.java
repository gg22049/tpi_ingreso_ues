package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto;

import jakarta.json.bind.annotation.JsonbDateFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author usermein
 */
public record JornadaDTO(
        Long idJornada,
        @NotBlank
        String nombre,
        @NotNull
        Date fechaInicio,
        @NotNull
        Date fechaFin,
        String observaciones
        ) {

}
