package net.pangu.provider;

public class ServiceResponse implements Response {

    private static final long serialVersionUID = -8551517393493336551L;
    private Long id;
    private Object result;

   
    public Long getId() {
	return this.id;
    }

   
    public void setId(Long id) {
	this.id = id;
    }

   
    public Object getResult() {
	return result;
    }

   
    public void setResult(Object result) {
	this.result = result;
    }

}
