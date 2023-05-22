public class Branch {
    private String zOrder;
	protected String dst;
    protected String points;
    private int[] p;

    public Branch(String zOrder, String dst, String points) {
        this.zOrder = zOrder;
        this.dst = dst;
        this.points = points;
    }
    
    public int getZ() {
        return Integer.parseInt(zOrder);
    }

    public int getDestination() {
        int destination;
        destination=Integer.parseInt(dst.substring(0, 1));
        return destination;
    }
    
    public String getPoints() {
        return points;
    }

    private void pointsFormat()
	{
        String s=points.substring(1,points.indexOf("]"));
        String[] a=s.split(",");
        p=new int[a.length];
        for(int i=0;i<a.length;i++) 
        {
            p[i]=Integer.parseInt(a[i].trim());
        } 
	}
    public int getX()
	{
		pointsFormat();
		return p[0];
	}
	public int getY()
	{
		pointsFormat();
		return p[1];
	}
}
