package Calculator;

/**
* Calculator/AddHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from add.idl
* Tuesday, February 16, 2016 12:07:25 PM UTC
*/

public final class AddHolder implements org.omg.CORBA.portable.Streamable
{
  public Calculator.Add value = null;

  public AddHolder ()
  {
  }

  public AddHolder (Calculator.Add initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = Calculator.AddHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    Calculator.AddHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return Calculator.AddHelper.type ();
  }

}
