package DHCP_DNS;

public class DNS {

    String DOMAIN    = "";
    String IP_DOMAIN = "";


    public DNS(String DOMAIN, String IP_DOMAIN) {
        this.DOMAIN = DOMAIN;
        this.IP_DOMAIN = IP_DOMAIN;
    }

    public String getDOMAIN() {
        return DOMAIN;
    }

    public void setDOMAIN(String DOMAIN) {
        this.DOMAIN = DOMAIN;
    }

    public String getIP_DOMAIN() {
        return IP_DOMAIN;
    }

    public void setIP_DOMAIN(String IP_DOMAIN) {
        this.IP_DOMAIN = IP_DOMAIN;
    }



}
