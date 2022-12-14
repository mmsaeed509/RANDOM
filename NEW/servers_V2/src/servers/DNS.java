package servers;

import java.util.ArrayList;

public class DNS {

    public String domain    = "";
    public String domain_ip= "";

    public DNS() {
    }

    public DNS(String DOMAIN, String IP_DOMAIN) {
        this.domain = DOMAIN;
        this.domain_ip = IP_DOMAIN;
    }

}

