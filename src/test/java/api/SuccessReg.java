package api;

public  class SuccessReg{

    private Integer id;
    private String token;

    public SuccessReg(Integer id, String token) {
        super();
        this.id = id;
        this.token = token;
    }

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }
public SuccessReg(){}

    public void setId(Integer id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
