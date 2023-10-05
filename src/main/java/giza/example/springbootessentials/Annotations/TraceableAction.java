package giza.example.springbootessentials.Annotations;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TraceableAction {

    @Around("@annotation(giza.example.springbootessentials.Annotations.Traceable)")
    public Object trace(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println(joinPoint.getSignature().getName());

        Object result = joinPoint.proceed();

        System.out.println(result);

        return result;
    }
}
