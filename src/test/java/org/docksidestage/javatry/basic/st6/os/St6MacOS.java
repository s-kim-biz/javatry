package org.docksidestage.javatry.basic.st6.os;

/**
 * @author s.kim
 */
public class St6MacOS extends St6OperationSystem{
    // Constructor
    public St6MacOS(String loginId) {
        super(loginId);
        setOsType("Mac");
    }

    @Override
    protected String getFileSeparator(){
        return "/";
    }

    @Override
    protected String getUserDirectory(){
        return "/Users/" + super.getLoginId();
    }
}
