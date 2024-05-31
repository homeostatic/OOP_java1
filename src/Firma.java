public class Firma extends AbstractContact {
    private Contact founder;
    private String FirmName;

    public Firma(Contact founder, String firmName) {
        this.founder = founder;
        FirmName = firmName;
    }

    public Contact getFounder() {
        return founder;
    }
    public void setFounder(Contact founder) {
        this.founder = founder;
    }
    public String getFirmName() {
        return FirmName;
    }
    public void setFirmName(String firmName) {
        FirmName = firmName;
    }


    
}
