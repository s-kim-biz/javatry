package org.docksidestage.javatry.basic.st6.os;

public class St6OldWindowsOS extends St6OperationSystem {
    // Constructor
    public St6OldWindowsOS(String loginId) {
        super(loginId);
        setOsType("OldWindows");
    }

    @Override
    protected String getFileSeparator(){
        return "\\";
    }

    @Override
    protected String getUserDirectory(){
        return "/Documents and Settigs/" + super.getLoginId();
    }
}
