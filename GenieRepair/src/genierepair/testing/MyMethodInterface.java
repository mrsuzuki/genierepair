package genierepair.testing;

import java.util.ArrayList;
import java.util.List;


import org.eclipse.jdt.core.Signature;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;

public class MyMethodInterface {
	
	private String identifier;
	private ITypeBinding returnType;
	private ITypeBinding[] paramsType;
	private ITypeBinding parentName;
	private String solrQuery="";
	
	public void setSolrQuery(String s){
		solrQuery=s;
	}
	
	public String getSolrQuery(){return solrQuery;}

	public MyMethodInterface(IMethodBinding iMethodBinding, ITypeBinding rturn) throws InterfaceCreationException{
		if(iMethodBinding == null){
			throw new InterfaceCreationException("Unresolved IMethodBinding");
		}
		this.identifier = iMethodBinding.getName();
		this.returnType = rturn==null?iMethodBinding.getReturnType():rturn;
		this.paramsType = iMethodBinding.getParameterTypes();
		this.parentName = iMethodBinding.getDeclaringClass();
	}
	
	
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof MyMethodInterface)){
			return super.equals(obj);
		}
		MyMethodInterface other = (MyMethodInterface) obj;
		if(!other.identifier.equals(identifier))
			return false;
		if(!other.identifier.equals(identifier))
			return false;
		if(!other.parentName.equals(parentName))
			return false;
		if(!other.returnType.equals(this.returnType))
			return false;
		return true;
	}

	private String paramToString(){
		if(paramsType.length==0) return "";
		StringBuilder ret = new StringBuilder();
		for(ITypeBinding t : paramsType){
			ret.append(t.getName()+",");
		}
		return ret.substring(0, ret.length()-1);
	}
	
	public String getID(){return identifier;}
	public ITypeBinding getReturnType(){return returnType;}
	public ITypeBinding[] getParamsType(){return paramsType;}
	public String toString(){return returnType.getName()+ " "
			+this.parentName.getQualifiedName()+"."
			+identifier+"("+this.paramToString()+")";
	}

	public String getMethodName() {
		return identifier;
	}

	public String getParentsName() {
		return parentName.getQualifiedName();
	}
	
	public List<String> getParamsClasses(){
		List<String> ret = new ArrayList<String>();
		for(ITypeBinding param : paramsType){
			ret.add(param.getName());
			//ret.add(param.getQualifiedName());
		}
		return ret;
	}

	public String getProject() {
		return null;
	}

	public boolean equalsParams(String[] parameterTypes) {
		if(parameterTypes.length!=paramsType.length) return false;
		int len = paramsType.length;
		for(int i=0;i<len;++i){
			if(!paramsType[i].getName().equals(
					Signature.toString(parameterTypes[i]))) return false;
		}
		return true;
	}
	
	

}
