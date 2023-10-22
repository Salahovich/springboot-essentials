package giza.example.springbootessentials.Aspects;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Aspect
@Component
@Log4j2
public class SystemAspect{
    @Around("execution(* giza.example.springbootessentials.Services.*.save*(..)) && args(object,..)")
    public Object logNewEntity(ProceedingJoinPoint pjp, Object object) throws Throwable {
         log.info("New Entity is going to be recorded");
         var obj = pjp.proceed();
         log.info("New Entity has been recorded");
         return obj;
    }
    @After("execution(* giza.example.springbootessentials.Services.*.delete*(..)) && args(id,..)")
    public void logDeletingEntityById(UUID id){
        log.info("Entity with the ID " + id.toString() + " has been deleted");
    }

    @After("execution(* giza.example.springbootessentials.Services.*.update*(..)) && args(object,..)")
    public void updateStudent(Object object){
        log.info("Entity has been updated successfully.");
    }
}
