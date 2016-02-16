import Calculator.*;
import org.omg.CORBA.*;
import java.io.*;
import org.omg.CosNaming.* ;
import org.omg.CosNaming.NamingContextPackage.*;


public class AddServer{
    public static void main (String args[]) {
        try{
        
        NameComponent nc[] = new NameComponent[1];

	    	// create and initialize the ORB
	   		ORB orb = ORB.init(args, null);
	   		
            //Instantiate the HelloServant on the server
			AddServant addRef = new AddServant();

			//connecting the servant to the orb
			orb.connect(addRef);
	   		System.out.println("Orb connected." + orb);
	   		
	   		
			//You need to locate the naming service. The naming serivce helps you locate other objects.
			//The CORBA orb lets you locate certain services by name. The call
			// String[] services = orb.list_initial_services();
			// System.out.println ("Listing Services");
			//lists the names of the standard services that the ORB can connect to. The Naming service
			//has the standard name NameService. To obtain an object reference to the service you use
			//resolve_initial_reference. It returns a generic CORBA object. Note you have to use
			//org.omg.CORBA.Object otherwise the compiler assumes that you mean java.lang.Object
			
	   		org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
	   		System.out.println("Found NameService.");
	   		
	   		//Next you need to convert this reference into a NamingContext reference
			//so that you can invoke the methods of the NamingContext interface.
			//This is achieved by using the narrow function of the helper class
			
			NamingContext rootCtx = NamingContextHelper.narrow(objRef);
			
		    //Now that we have the naming context we can use it to place a server object
			//Names are nested sequences of name components. You can use the nesting levels
			//to organize hierarchies of names much like you use directories in a file system.
			//A Name Component consists of an ID and a kind. The ID is a name for the component
			//that is unique among all names with the same parent component. The kind is some indication
			//of the type of the component. Use
			//-"Context" for name components that have nested names; and
			//-"Object" for object names
			
			
			
			//Task 1: Implement the Naming Graph 
			//1) Context1/Sub-Context2/ Object1
			//2) Context1/Object1
			
			nc[0] = new NameComponent("Context1", "Context");
			NamingContext Context1Ctx = rootCtx.bind_new_context(nc);
			System.out.println("Context 'Context1' added to Name Space.");
			
			nc[0] = new NameComponent("Object1", "Object");
			Context1Ctx.rebind(nc, Context1Ctx);
			System.out.println("Object 'Object1' added to Context1 Context.");
			
			nc[0] = new NameComponent("Sub-Context2", "Context");
			NamingContext Context4Ctx = Context1Ctx.bind_new_context(nc);
			System.out.println("Context 'Sub-Context2' added to Context1 context."); 
			
			nc[0] = new NameComponent("Object1", "Object");
			Context4Ctx.rebind(nc, Context1Ctx);
			System.out.println("Object 'Object1' added to Sub-Context2 Context.");
			
			
			//1) Context2/Object2
			//2) Context2/Sub-Context1/Object3
			//3) Context2/Object4
		
			nc[0] = new NameComponent("Context2", "Context");
			NamingContext Context2Ctx = rootCtx.bind_new_context(nc);
			System.out.println("Context 'Context2' added to Name Space.");
			
			nc[0] = new NameComponent("Object2", "Object");
			Context2Ctx.rebind(nc, Context2Ctx);
			System.out.println("Object 'Object2' added to Context2 Context.");
			
		
			nc[0] = new NameComponent("Sub-Context1", "Context");
			NamingContext Context5Ctx = Context2Ctx.bind_new_context(nc);
			System.out.println("Context 'Sub-Context1' added to Context2 context."); 
			
			nc[0] = new NameComponent("Object3", "Object");
			Context5Ctx.rebind(nc, Context2Ctx);
			System.out.println("Object 'Object3' added to Sub-Context1 Context.");
			
			
			// Bind Object4 to the AddServant object
			nc[0] = new NameComponent("Object4", "Object");
			Context2Ctx.rebind(nc, addRef);
			System.out.println("Object 'Object4' added to Context2 Context.");
		
			//Context3/Object5
		
			nc[0] = new NameComponent("Context3", "Context");
			NamingContext Context3Ctx = rootCtx.bind_new_context(nc);
			System.out.println("Context 'Context3' added to Name Space.");
			
			nc[0] = new NameComponent("Object5", "Object");
			Context3Ctx.rebind(nc, Context3Ctx);
			System.out.println("Object 'Object5' added to Context3 Context.");
			
			
           // wait for invocations from clients
				orb.run();
				
        } catch (Exception e) {
			System.err.println("Error: "+e);
			e.printStackTrace(System.out);
        }
    }
}