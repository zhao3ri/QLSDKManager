package com.item.web.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.StrutsStatics;

import com.item.utils.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.opensymphony.xwork2.util.ValueStack;

/**
 * 关键字与特殊字符拦截器
 *
 * @author liuxh
 * @since 2012-03-01
 */
public class KeywordInterceptor extends AbstractInterceptor {

    private static final long serialVersionUID = -6136383560927216463L;
    //排除不需要拦截的方法
    private String[] excludeMethods = {"login"};

    @SuppressWarnings("unchecked")
    @Override
    public String intercept(ActionInvocation invocation) {
        //获取请求的Action值栈及请求的方法
        ActionContext ctx = invocation.getInvocationContext();
        ValueStack valueStack = ctx.getValueStack();
        String methodName = ctx.getName();
        System.out.println("method:" + methodName);
        try {
            //如果不是排除拦截的方法
            if (!this.isExcludeMethod(methodName)) {
                //取页面传过来的参数名列表
                HttpServletRequest request = (HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);
                Enumeration<String> enums = request.getParameterNames();

                while (enums.hasMoreElements()) {
                    String parameterName = (String) enums.nextElement();
                    Object parameterValue = request.getAttribute(parameterName);
//					System.out.println("parameterName:"+parameterName);
//					System.out.println("parameterValues:"+parameterValue);
                    //只过滤非数字类型的内容
                    if (parameterValue != null && parameterValue.getClass() == String.class) {
                        //过滤特殊字符
                        parameterValue = StringUtil.filtrateString(parameterValue.toString());
                        //修改参数的值，并保持至Struts2值栈中
                        valueStack.setValue(parameterName, parameterValue);

                        invocation.getInvocationContext()
                                .getParameters().put(parameterName, parameterValue);
                    }
                }
            }
            return invocation.invoke();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 判断是否为排除的方法
     *
     * @param methodName 方法名称
     * @return
     */
    private boolean isExcludeMethod(String methodName) {
        for (String excludeMethod : excludeMethods) {
            if (excludeMethod.equals(methodName)) {
                return true;
            }
        }
        return false;
    }

}
