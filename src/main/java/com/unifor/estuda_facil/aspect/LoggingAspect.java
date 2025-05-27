package com.unifor.estuda_facil.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.unifor.estuda_facil.service..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("🔍 [LOG] Iniciando: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* com.unifor.estuda_facil.service..*(..))", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        System.out.println("✅ [LOG] Finalizado: " +
                joinPoint.getSignature().getName() + ", retornou: " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.unifor.estuda_facil.service..*(..))", throwing = "ex")
    public void logError(JoinPoint joinPoint, Throwable ex) {
        System.out.println("❌ [LOG] Erro em: " +
                joinPoint.getSignature().getName() + " - Mensagem: " + ex.getMessage());
    }
    // Aplica o aspecto para todos os métodos em pacotes de serviços
    @Around("execution(* com.unifor.estuda_facil.service..*(..))")
    public Object logServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        Object[] args = joinPoint.getArgs();

        log.info("🔍 Entrando: {}.{} com argumentos {}", className, methodName, Arrays.toString(args));

        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;

        log.info("✅ Saída: {}.{} retornou {} (executado em {} ms)", className, methodName, summarize(result), duration);
        return result;
    }

    // Evita imprimir objetos grandes no log completo
    private String summarize(Object result) {
        if (result == null) return "null";
        String str = result.toString();
        return str.length() > 150 ? str.substring(0, 150) + "..." : str;
    }
    @Around("@annotation(com.unifor.estuda_facil.aop.Loggable)")
    public Object logAnnotatedMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        Object[] args = joinPoint.getArgs();

        log.info("🔍 [@Loggable] Entrando: {}.{} com argumentos {}", className, methodName, Arrays.toString(args));

        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long duration = System.currentTimeMillis() - start;

        log.info("✅ [@Loggable] Saída: {}.{} retornou {} (executado em {} ms)", className, methodName, summarize(result), duration);
        return result;
    }




}
