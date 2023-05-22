import java.io.BufferedReader;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class SimulinkParser {
	private static ArrayList<Block> Blocks=new ArrayList<Block>();
	private static ArrayList<Lines> lines=new ArrayList<Lines>();
	
	public static void parse(String filepath) {
		
		try 
		{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        String xml= ExtractBlock(filepath);
	        InputSource is =new InputSource(new StringReader(xml));
	        Document doc = dBuilder.parse(is);
	        doc.getDocumentElement().normalize();
            NodeList BlockList = doc.getElementsByTagName("Block");
			for (int temp = 0; temp < BlockList.getLength(); temp++)
			{
	            Node block = BlockList.item(temp);
	            if (block.getNodeType() == Node.ELEMENT_NODE)
	            {
					Element eElement = (Element) block;
	                String blocktype= eElement.getAttribute("BlockType");
	                String name= eElement.getAttribute("Name");
					String SID=eElement.getAttribute("SID");
					String position="";
	                String zOrder="";
					//to get child nodes
					NodeList blockdetails=block.getChildNodes();
					for(int i=0;i<blockdetails.getLength();i++)
					{
						Node detail =blockdetails.item(i);
						if(detail.getNodeType()==Node.ELEMENT_NODE)
						{
							Element detailElement=(Element)detail;
							if(detailElement.getAttribute("Name").equals("Position")) 
							{
								position=detailElement.getTextContent();
							}
							if(detailElement.getAttribute("Name").equals("ZOrder")) 
							{
								zOrder=detailElement.getTextContent();
							}
						}
					} 
					Blocks.add(new Block(blocktype,name,SID,position,zOrder));
				}
			}
			NodeList lineList = doc.getElementsByTagName("Line");
			for (int temp=0; temp <lineList.getLength();temp++)
			{
				Node line = lineList.item(temp);
				if(line.getNodeType() == Node.ELEMENT_NODE)
				{
					String zOrder=""; 
					String src="";
					String dst="";
					String points="";
					NodeList linedetails=line.getChildNodes();
					for(int i=0;i<linedetails.getLength();i++)
					{
						Node detail =linedetails.item(i);
						if(detail.getNodeType()==Node.ELEMENT_NODE)
						{
							Element detailElement=(Element)detail;
							if(detailElement.getAttribute("Name").equals("ZOrder")) 
							{
								zOrder=detailElement.getTextContent();
							}
							if(detailElement.getAttribute("Name").equals("Src")) 
							{
								src=detailElement.getTextContent();
							}
							if(detailElement.getAttribute("Name").equals("Dst")) 
							{
								dst=detailElement.getTextContent();
							}
							if(detailElement.getAttribute("Name").equals("Points")) 
							{
								points=detailElement.getTextContent();
							}
							
						}
					}
					ArrayList<Branch> branches=new ArrayList<Branch>();
					Element lineB =(Element)lineList.item(temp);
					NodeList branchList =lineB.getElementsByTagName("Branch");
					for (int tem=0; tem <branchList.getLength();tem++)
					{
						Node branch = branchList.item(tem);
						if(branch.getNodeType() == Node.ELEMENT_NODE)
						{
							String ZOrder=""; 
							String Dst="";
							String Points="";
							NodeList branchdetails=branch.getChildNodes();
							for(int j=0;j<branchdetails.getLength();j++)
							{
								Node Detail =branchdetails.item(j);
								if(Detail.getNodeType()==Node.ELEMENT_NODE)
								{
									Element DetailElement=(Element)Detail;
									if(DetailElement.getAttribute("Name").equals("ZOrder")) 
									{
										ZOrder=DetailElement.getTextContent();
									}
									if(DetailElement.getAttribute("Name").equals("Dst")) 
									{
										Dst=DetailElement.getTextContent();
									}
									if(DetailElement.getAttribute("Name").equals("Points")) 
									{
										Points=DetailElement.getTextContent();
									}
								}
							}
							branches.add(new Branch(ZOrder, Dst, Points));
						}
					}				
					lines.add(new Lines(zOrder, src, dst, points, branches)); 					
				}
			}
	        
			
	    } 	catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<Block> getBlocks()
	{
		return Blocks;
	}
	public static List<Lines> getLines()
	{
		return lines;
	}
	public static String ExtractBlock(String filepath) throws Exception{

		
		InputStream is = new FileInputStream(filepath);
		try (BufferedReader buf = new BufferedReader(new InputStreamReader(is))) {
			String line = buf.readLine();
			StringBuilder sb = new StringBuilder();
			
			while(line != null){
			   sb.append(line).append("\n");
			   line = buf.readLine();
			}
			
			String fileAsString = sb.toString();
			StringBuffer filee=new StringBuffer(fileAsString);
			fileAsString=filee.substring(filee.lastIndexOf("system_root.xml"),filee.lastIndexOf("__MWOPC_PART_BEGIN__ /simulink/windowsInfo.xml"));
			fileAsString=fileAsString.substring(16);
			return fileAsString;
		}
			
	}	



}
