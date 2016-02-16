import Calculator.*;


class AddServant extends _AddImplBase
{
    public int add (int num1, int num2)
    {
      System.out.println("Request Received.");
      int result = num1 + num2;
    		return result;
    }
    
    
}