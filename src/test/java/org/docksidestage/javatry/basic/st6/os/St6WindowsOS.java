package org.docksidestage.javatry.basic.st6.os;

public class St6WindowsOS extends St6OperationSystem{
    // Constructor
    public St6WindowsOS(String loginId) {
        super(loginId);
        setOsType("Windows");
    }

    @Override
    protected String getFileSeparator(){
        return "\\";
    }

    @Override
    protected String getUserDirectory(){
        return "/Users/" + super.getLoginId();
    }
}
