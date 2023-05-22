import java.util.ArrayList;

public class Lines {
    private String zOrder;
	private String src;
	protected String dst;
    protected String points;
    private int[] p;
    private int[] q;
    protected ArrayList<Branch> branches=new ArrayList<Branch>();


    
    public Lines(String zOrder, String src, String dst, String points, ArrayList<Branch> branches) {
        this.zOrder = zOrder;
        this.src = src;
        this.dst = dst;
        this.points = points;
        this.branches = branches;
    }

    /*public Lines(String zOrder, String src, String dst, String points) {
        this.zOrder = zOrder;
        this.src = src;
        this.dst = dst;
        this.points = points;
    }*/

    public int getZ() {
        return Integer.parseInt(zOrder);
    }

    public int getSource() {
        return Integer.parseInt(src.substring(0, 1));
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
        if (points.contains(";"))
        {
            String a=points.substring(1,points.indexOf(";"));
            String b=points.substring(points.indexOf(";")+1,points.indexOf("]"));
            String[] a1=a.split(",");
            p=new int[a1.length];
            for(int i=0;i<a1.length;i++) 
            {
                p[i]=Integer.parseInt(a1[i].trim());
            }
            String[] b1=b.split(",");
            q=new int[b1.length];
            for(int i=0;i<b1.length;i++) 
            {
                q[i]=Integer.parseInt(b1[i].trim());
            }
        }
        else
        {
            String s=points.substring(1,points.indexOf("]"));
            String[] a=s.split(",");
            p=new int[a.length];
            for(int i=0;i<a.length;i++) 
            {
                p[i]=Integer.parseInt(a[i].trim());
            }
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
    public int getX2()
	{
		pointsFormat();
		return q[0];
	}
	public int getY2()
	{
		pointsFormat();
		return q[1];
	}

    public ArrayList<Branch> getBranches() {
        return branches;
    }

    
}