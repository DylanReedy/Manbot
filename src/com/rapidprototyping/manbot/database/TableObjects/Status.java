package TableObjects;

public enum Status{

    NEW ("new"),ACTIVE ("active"),CLOSED ("closed");

    private final String status;
    Status(String status){
        this.status = status;
    }

    @Override
    public String toString(){
        return status;
    }

}
