/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.QueryParam;

/**
 *
 * @author caesar
 */
@ValidRange
public class FindRangeParamDTO {

    @NotNull
    @QueryParam("offset")
    private Integer offset;

    @NotNull
    @QueryParam("limit")
    private Integer limit;

    public Integer getOffset() {
        return offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

}
