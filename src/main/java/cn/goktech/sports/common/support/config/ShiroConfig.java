package cn.goktech.sports.common.support.config;

import cn.goktech.sports.common.support.properties.GlobalProperties;
import cn.goktech.sports.modules.sys.shiro.UserFilter;
import cn.goktech.sports.modules.sys.shiro.UserRealm;
import cn.goktech.sports.common.support.properties.GlobalProperties;
import cn.goktech.sports.common.support.shiro.listener.UserSessionListener;
import cn.goktech.sports.common.support.shiro.session.UserSessionDAO;
import cn.goktech.sports.common.support.shiro.session.UserSessionFactory;
import cn.goktech.sports.modules.sys.shiro.ShiroPermsFilterFactoryBean;
import cn.goktech.sports.modules.sys.shiro.UserFilter;
import cn.goktech.sports.modules.sys.shiro.UserPermFilter;
import cn.goktech.sports.modules.sys.shiro.UserRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.*;

/**
 * shiro配置
 * @author zcl<yczclcn@163.com>
 */
@DependsOn("springContextUtils")
@Configuration
public class ShiroConfig {

    /**
     * 安全管理器
     * @param sessionManager
     * @return
     */
    @Bean
    public SecurityManager securityManager(SessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);
        securityManager.setRealm(this.userRealm());
        return securityManager;
    }

    /**
     * session管理器
     * @return
     */
    @Bean
    public SessionManager sessionManager(GlobalProperties globalProperties){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setDeleteInvalidSessions(true);
        if (globalProperties.isRedisSessionDao()) {
            // 开启redis会话管理器
            sessionManager.setSessionFactory(new UserSessionFactory());
            sessionManager.setSessionDAO(new UserSessionDAO());
            List<SessionListener> sessionListeners = new ArrayList<>();
            sessionListeners.add(new UserSessionListener());
            sessionManager.setSessionListeners(sessionListeners);
        }
        return sessionManager;
    }

    /**
     * 用户realm
     * @return
     */
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    /**
     * shiro过滤器
     * /rest/**，请求采用token验证（cn.goktech.sports.common.support.interceptor.RestApiInterceptor）
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroPermsFilterFactoryBean shiroFilter = new ShiroPermsFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        shiroFilter.setLoginUrl("/login");

        shiroFilter.setSuccessUrl("/");

        shiroFilter.setUnauthorizedUrl("/error/403");

        //user过滤器，处理ajax请求超时不跳转情况
        Map<String, Filter> filters = new HashMap<>(2);
        filters.put("user", new UserFilter());
        filters.put("perms", new UserPermFilter());
        shiroFilter.setFilters(filters);

        Map<String, String> filterMap = new LinkedHashMap<>(5);
        filterMap.put("/static/**", "anon");
        filterMap.put("/error/**", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/payment", "anon");
        filterMap.put("/captcha.jpg", "anon");
        filterMap.put("/rest/**", "anon");
        filterMap.put("/data/**", "anon");
        filterMap.put("/img/**", "anon");
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }

    /**
     * shiro生命周期处理器
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 使用cglib方式创建代理对象
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    /**
     * 启用注解
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
