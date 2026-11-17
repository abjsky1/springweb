package example.personal_training.record_enum_261117;

public record DtoVo(String name , int price){
    
    public int calculateTotal(int count){

        return this.price*count;

    }

}
