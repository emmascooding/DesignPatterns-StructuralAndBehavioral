package problemafter;

abstract class ExtraDecorator implements MenuItem{
    protected final MenuItem inner;
    ExtraDecorator(MenuItem inner){
        this.inner = inner;
    }
    public String getItem(){
        return inner.getItem();
    }
    public double getPrice(){
        return inner.getPrice();
    }
}
