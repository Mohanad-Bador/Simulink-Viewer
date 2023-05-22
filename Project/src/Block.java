public class Block {
	private String BlockType;
	private String Name;
	private String SID;
	private String position;
	private int[] p;
	private String zOrder;
	
	
	public Block(String BlockType,String Name,String SID,String position,String zOrder)
	{
		this.BlockType=BlockType;
		this.Name=Name;
		this.SID=SID;
		this.position=position;
		this.zOrder=zOrder;
	}
	public String getPosition()
	{
		return position;
	}
	private void PositionFormat()
	{
    	String s=position.substring(1,position.indexOf("]"));
    	String[] a=s.split(",");
    	p=new int[a.length];
    	for(int i=0;i<a.length;i++) 
		{
    		p[i]=Integer.parseInt(a[i].trim());
    	}
	}
	public int getX()
	{
		PositionFormat();
		return p[0];
	}
	public int getY()
	{
		PositionFormat();
		return p[1];
	}
	public int getWidth()
	{
		PositionFormat();
		return p[2]-p[0];
	}
	public int getLength()
	{
		PositionFormat();
		return p[3]-p[1];
	}
	public String getBlockType()
	{
		return BlockType;
	}
	public String getSID() 
	{
		return SID;
	}
	public String getName()
	{
		return Name;
	}
	public int getZ() 
	{
  		int z;
		z=Integer.parseInt(zOrder);
		return z;
	}
	
}