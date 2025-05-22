package com.unifor.estuda_facil.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Aspect
@Component
public class ValidationAspect {

    @Before("@annotation(com.unifor.estuda_facil.aspects.Validate)")
    public void validarCamposObrigatorios(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();

        for (Object arg : args) {
            if (arg == null) continue;

            for (Field field : arg.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(arg);
                    if (field.getName().equals("titulo") || field.getName().equals("dataEvento")) {
                        if (value == null || value.toString().isBlank()) {
                            throw new IllegalArgumentException("Campo obrigatório faltando: " + field.getName());
                        }
                    }
                } catch (IllegalAccessException ignored) {}
            }
        }
    }
}
