package com.item.domain.authority;

public class Auth extends SystemModule {
    private Long moduleID;
    private Long functionID;

    public Auth() {

    }

    public Long getModuleID() {
        return moduleID;
    }

    public void setModuleID(Long moduleID) {
        this.moduleID = moduleID;
    }

    public Long getFunctionID() {
        return functionID;
    }

    public void setFunctionID(Long functionID) {
        this.functionID = functionID;
    }

    @Override
    public String toString() {
        return "moduleId===" + moduleID + ",functionId===" + functionID;
    }
}
