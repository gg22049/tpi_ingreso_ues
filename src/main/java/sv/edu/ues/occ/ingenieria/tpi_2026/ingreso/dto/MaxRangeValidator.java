/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sv.edu.ues.occ.ingenieria.tpi_2026.ingreso.dto;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 *
 * @author caesar
 */
class MaxRangeValidator implements ConstraintValidator<ValidMaxRange, FindRangeParamDTO> {

    @Override
    public boolean isValid(FindRangeParamDTO value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        Integer offset = value.getOffset();
        Integer limit = value.getLimit();
        if (offset == null || limit == null) {
            return true;
        }
        if ((limit - offset) <= 50) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context
                .buildConstraintViolationWithTemplate("max diference between 'offset' and 'limit' must be 50")
                .addPropertyNode("limit")
                .addConstraintViolation();
        return false;
    }

}
