package cn.goktech.sports.common.aspect;

import cn.goktech.sports.common.annotation.SysLog;
import cn.goktech.sports.common.utils.CommonUtils;
import cn.goktech.sports.common.utils.JSONUtils;
import cn.goktech.sports.common.utils.ShiroUtils;
import cn.goktech.sports.common.utils.WebUtils;
import cn.goktech.sports.modules.sys.dao.SysLogMapper;
import cn.goktech.sports.modules.sys.entity.SysLogEntity;
import cn.goktech.sports.modules.sys.entity.SysUserEntity;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * 系统日志，切面处理类
 * @author zcl<yczclcn@163.com>
 */
@Aspect
@Component
public class SysLogAspect {
	
	@Autowired
	private SysLogMapper sysLogMapper;
	
	@Pointcut("@annotation(cn.goktech.sports.common.annotation.SysLog)")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;
		//保存日志
		saveSysLog(point, time);
		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		SysLogEntity sysLog = new SysLogEntity();
		SysLog syslog = method.getAnnotation(SysLog.class);
		if(syslog != null){
			//注解上的描述
			sysLog.setOperation(syslog.value());
		}
		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");
		//请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			String params = JSONUtils.beanToJson(args[0]);
			sysLog.setParams(params);
		}catch (Exception e){

		}
		//设置IP地址
		sysLog.setIp(WebUtils.getIpAddr());
		//用户名
		SysUserEntity currUser = ShiroUtils.getUserEntity();
		if(CommonUtils.isNullOrEmpty(currUser)) {
			if(CommonUtils.isNullOrEmpty(sysLog.getParams())) {
				sysLog.setUserId(-1L);
				sysLog.setUsername(sysLog.getParams());
			} else {
				sysLog.setUserId(-1L);
				sysLog.setUsername("获取用户信息为空");
			}
		} else {
			sysLog.setUserId(ShiroUtils.getUserId());
			sysLog.setUsername(ShiroUtils.getUserEntity().getUsername());
		}
		sysLog.setTime(time);
		//保存系统日志
		sysLogMapper.save(sysLog);
	}
	
}
